;	testCPI.asm

PSTRING		EQU		0852H
NEWLINE		EQU		088CH
DELAY		EQU		0CE1H
COUT		EQU		0861H
		
			

			ORG		1800H
Start:
		LD		SP,TopOfStack
		CALL	ClearBuffer
		CALL	INIT
		CALL	NEWLINE
		
		CALL	InitArgs				; set values to Zero

Loop1:		
		CALL	PutArgsinBuffer
		CALL	PerformCP
		CALL	LineBreak
	
;		CALL	IncArg1
		LD		HL,arg1
		INC		(HL)

		JR		NZ,Loop1
;		CALL	IncArg2

		LD		HL,arg2
		INC		(HL)
		JR		NZ,Loop1
Loop2:		

		RST		38H
		JP		Start
;------------------------------------------		

PerformCP:							
		CALL	GetArg2					; arg2 in Acc
		LD		B,A						; move it to B reg
		CALL	GetArg1					; arg1 in Acc
		CP		B						; do the compare
		
		
RecordFlags:
		PUSH	AF						; save Flag result
;		CALL	AddByteToBufferSpace	; result is in ACC
		
;sign flag		
		POP		DE						; get flags so we can test
		PUSH	DE						; save flags so we can test later
		LD		A,31H					; Assume flag set
		BIT		7,E						; test it
		JP		NZ,SignFlagSet
		DEC		A						; Not Set  - ASCII 0
SignFlagSet:
		CALL	AddToBuffer	
		
;zero flag
		POP		DE						; get flags so we can test
		PUSH		DE						; save flags so we can test later
		LD		A,31H					; Assume flag set
		BIT		6,E						; test it
		JP		NZ,ZeroFlagSet
		DEC		A						; Not Set  - ASCII 0
ZeroFlagSet:
		CALL	AddToBuffer	
		
;HalfCarry flag
		POP		DE						; get flags so we can test
		PUSH		DE						; save flags so we can test later
		LD		A,31H					; Assume flag set
		BIT		4,E						; test it
		JP		NZ,HalfCarryFlagSet
		DEC		A						; Not Set  - ASCII 0
HalfCarryFlagSet:
		CALL	AddToBuffer
		
;Overflow flag
		POP		DE						; get flags so we can test
		PUSH		DE						; save flags so we can test later
		LD		A,31H					; Assume flag set
		BIT		2,E						; test it
		JP		NZ,OverflowFlagSet
		DEC		A						; Not Set  - ASCII 0
OverflowFlagSet:
		CALL	AddToBuffer	

;N flag
		POP		DE						; get flags so we can test
		PUSH		DE						; save flags so we can test later
		LD		A,31H					; Assume flag set
		BIT		1,E						; test it
		JP		NZ,nFlagSet
		DEC		A						; Not Set  - ASCII 0
nFlagSet:
		CALL	AddToBuffer	

;Carry flag
		POP		DE						; get flags so we can test
;		POP		DE						; save flags so we can test later
		LD		A,31H					; Assume flag set
		BIT		0,E						; test it
		JP		NZ,CarryFlagSet
		DEC		A						; Not Set  - ASCII 0
CarryFlagSet:
		CALL	AddToBuffer	
		CALL	AddSpaceToBuffer

		RET
		
		
LineBreak:
		LD		A,(arg1)
		AND		0F0H
		CP		0
		CALL	Z,DELAY
		CALL	AddLFToBuffer
		CALL	PrintBufferAndClear
		RET
		
PutArgsinBuffer:
		CALL	GetArg2
		CALL	AddByteToBuffer		; arg1 is in ACC
		LD		A,02BH					; +
;		LD		A,02DH					; -
		CALL	AddToBuffer
		CALL	GetArg1
		CALL	AddByteToBufferSpace	; arg2 is in ACC
		LD		A,03DH					; =
		CALL	AddToBuffer
		CALL	AddSpaceToBuffer
		RET
INIT:
		LD		A,020H
		CALL	COUT
		CALL	DELAY
		RET
		
InitArgs:
		XOR		A
		LD		(arg1),A
		LD		(arg2),A
		RET
GetArg1:
		LD		A,(arg1)
		RET

GetArg2:
		LD		A,(arg2)
		RET

		
arg1:	DS		1
arg2:	DS		1
		


$INCLUDE buffer.asm	

		ORG		(($/10H) + 1) * 10H	
		DS		0100H
TopOfStack:

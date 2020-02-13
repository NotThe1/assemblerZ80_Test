;	testLOGIC1.asm

PSTRING		EQU		0852H
NEWLINE		EQU		088CH
DELAY		EQU		0CE1H
COUT		EQU		0861H
		
			
; SBC CY the SBC NC

			ORG		1800H
Start:
		LD		SP,TopOfStack
		CALL	ClearBuffer
		CALL	INIT
		CALL	NEWLINE
		
		CALL	InitArgs				; set values to Zero

Loop1:		
		CALL	PutArgsinBuffer
		CALL	PerformCPL
		CALL	PerformNEG

		CALL	LineBreak
	

		LD		HL,arg1
		INC		(HL)
		JR		NZ,Loop1


Done:		

		RST		38H
		JP		Start
;------------------------------------------		
PerformCPL:
		CALL	GetArg1
		CPL								; 1's Complement 
		JP		RecordFlags				; put flag info into buffer
	
PerformNEG:
		CALL	GetArg1
		NEG								;  2's Complement 
		
RecordFlags:
		PUSH	AF						; save Flag result
;		PUSH	AF						; save Flag result
		CALL	AddByteToBufferSpace	; result is in ACC
		
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

		CALL	AddLFToBuffer
		CALL	PrintBufferAndClear
		RET
		
PutArgsinBuffer:
;		CALL	GetArg2
;		CALL	AddByteToBuffer		; arg1 is in ACC
;		LD		A,02DH					; -
;		CALL	AddToBuffer
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

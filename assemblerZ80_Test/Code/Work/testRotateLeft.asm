;	testRotateLeft.asm

PSTRING		EQU		0852H
NEWLINE		EQU		088CH
DELAY		EQU		0CE1H
COUT		EQU		0861H
		
			

			ORG		1800H
Start:
		CALL	ClearBuffer
		CALL	INIT
		CALL	NEWLINE
		
		CALL	InitArgs						; set value to Zero
		LD		A,00
Loop1:
		LD		(arg1),A						; save counter

		CALL	PutArgsinBuffer
		
		CALL	PerformRLA
		CALL	PerformRLCA
			
		CALL	LineBreak

		LD		A,(arg1)
		INC	A
		LD		(arg1),A
		JP		NZ,Loop1
		

		RST		38H
		JP		Start
;------------------------------------------		
PerformRLA:
		CALL	GetArg1
		SCF
		CCF									; CY = 0
		RL		A
		CALL	RecordFlags
		CALL	GetArg1
		SCF									; CY = 1
		RL		A		
		CALL	RecordFlags
		RET
		
PerformRLCA:
		CALL	GetArg1								; CY = 0
		RLC		A
		CALL	RecordFlags
		RET
		
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
;		LD		A,(arg1)
;		AND		0F0H
;		CP		0
;		CALL	Z,DELAY
		CALL	AddLFToBuffer
		CALL	PrintBufferAndClear
		RET
		
PutArgsinBuffer:
		CALL	GetArg1
		CALL	AddByteToBuffer		; arg1 is in ACC
		LD		A,09H					; TAB
		CALL	AddToBuffer
;		CALL	AddSpaceToBuffer
		RET
INIT:
		LD		A,020H
		CALL	COUT
		CALL	DELAY
		RET
		
InitArgs:
		XOR		A
		LD		(arg1),A
		RET
GetArg1:
		LD		A,(arg1)
		RET



		
arg1:	DS		1

		


$INCLUDE buffer.asm	

		ORG		(($/10H) + 1) * 10H	
		DS		0100H
TopOfStack:

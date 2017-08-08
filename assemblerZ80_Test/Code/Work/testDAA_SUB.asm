;	testDAA_SUB.asm

PSTRING		EQU		0852H
NEWLINE		EQU		088CH
DELAY		EQU		0CE1H
COUT		EQU		0861H
		
			
;ARG1_MAX	EQU		0
;ARG2_MAX	EQU		100
;		
;NINE		EQU		10;

			ORG		1800H
Start:
		LD		SP,TopOfStack
		CALL	ClearBuffer
		CALL	INIT
		CALL	NEWLINE
		
		CALL	InitArgPair				; set values to Zero

Loop1:		
		CALL	PutArgsinBuffer

		;		CALL	GetArg1
;		CP		22H
;		CALL	Z,LineBreak
;----------------------------
		CALL	PerformSub
		CALL	PerformDAA
		CALL	LineBreak

;----------------------------
		
		CALL	IncArg1

		JR		NC,Loop1
		CALL	IncArg2
		JR		NC,Loop1
		

		RST		38H
		JP		Start
;------------------------------------------		
PerformDAA:
		CALL	GetArg1
		LD		B,A						; arg1 in B
		CALL	GetArg2					; arg2 in ACC
		SUB		B						; Subtract them
		DAA								; * star of the show
		PUSH	AF						; save Flag result
		PUSH	AF						; save Flag result
		CALL	AddByteToBufferSpace	; Sum is in ACC
		POP		AF						; restore Flags
		LD		A,31H					; Carry set - ASCII 1
		JP		C,CarryWasSet1
		DEC		A						; Carry Not Set  - ASCII 0
CarryWasSet1:
		CALL	AddToBuffer				; Carry flag to buffer
		CALL	AddSpaceToBuffer
		
		LD		A,31H					; HalfCarry set - ASCII 1		
		POP		DE						; get flags so we can test HFlag
		BIT		4,E						; test it
		JP		NZ,HalfCarrySet1
		DEC		A						; HalfCarry Not Set  - ASCII 0
HalfCarrySet1:
		CALL	AddToBuffer				; Carry flag to buffer
		CALL	AddSpaceToBuffer
		RET

PerformSub:
		CALL	GetArg1
		LD		B,A						; arg1 in B
		CALL	GetArg2					; arg2 in ACC
		SUB		B						; Sub them
		PUSH	AF						; save Flag result
		PUSH	AF						; save Flag result
		CALL	AddByteToBufferSpace	; Sum is in ACC
		POP		AF						; restore Flags
		LD		A,31H					; Carry set - ASCII 1
		JP		C,CarryWasSet
		DEC		A						; Carry Not Set  - ASCII 0
CarryWasSet:
		CALL	AddToBuffer				; Carry flag to buffer
		CALL	AddSpaceToBuffer
		
		LD		A,31H					; HalfCarry set - ASCII 1		
		POP		DE						; get flags so we can test HFlag
		BIT		4,E						; test it
		JP		NZ,HalfCarrySet
		DEC		A						; HalfCarry Not Set  - ASCII 0
HalfCarrySet:
		CALL	AddToBuffer				; Carry flag to buffer
		CALL	AddSpaceToBuffer
		RET
		
LineBreak:
		LD		A,(arg1L)
		CP		0
		CALL	Z,DELAY
		CALL	AddLFToBuffer
		CALL	PrintBufferAndClear
		RET
		
PutArgsinBuffer:
		CALL	GetArg2
		CALL	AddByteToBuffer		; arg1 is in ACC
		LD		A,02DH					; -
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
		
$INCLUDE incArgs.asm

$INCLUDE buffer.asm	

		ORG		(($/10H) + 1) * 10H	
		DS		0100H
TopOfStack:

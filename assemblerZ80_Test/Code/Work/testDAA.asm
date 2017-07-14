;	testDAA.asm

PSTRING		EQU 0852H
NEWLINE		EQU 088CH
DELAY		EQU 0CE1H
COUT		EQU 0861H

	
ARG1_MAX	EQU	09
ARG2_MAX	EQU	09

			ORG		1800H
Start:

		CALL	INIT
		CALL	NEWLINE

		LD		A,ARG1_MAX
		LD		(arg1),A
;		LD		B,ARG2_MAX
		
		
LoopArg1:
		LD		A,(arg1)
		LD		B,ARG2_MAX
		
LoopArg2:
		PUSH	BC		; Save arg2 & count
		LD		A,(arg1)		
		CALL	DoTest
		POP		BC
		DJNZ	LoopArg2
; arg2		
		LD		HL,arg1
		DEC		(HL)
		JP		NZ,LoopArg1

		RST		38H
		JP		Start
		
DoTest:
		
		CALL ArgsToBuffer
		
		
		ADD		A,B
		DAA
		PUSH	AF
		POP		DE			; move flags to E
		BIT		4,E
		JP		Z,HsetNot	; return via call
		CALL	Hset
		RET

ArgsToBuffer:
		PUSH	BC
		PUSH	BC
		PUSH	AF
		CALL	ClearBuffer
		POP		AF
		CALL	AddByteToBufferSpace	; arg1 is in ACC
		POP		BC
		LD		A,B
		CALL	AddByteToBufferSpace	; arg2 is in ACC
		CALL	PrintBuffer
		POP		BC						; restore arg2
		LD		A,(arg1)					; restore arg1
		RET


Hset:
		LD		IX,MsgSet
		JP		RevealHFlag
HsetNot:
		LD		IX,MsgSetNot
RevealHFlag:
		CALL	PSTRING
		CALL	NEWLINE
		RET


Hset:
		LD		IX,MsgSet
		CALL	PSTRING
		CALL	NEWLINE
		RET
MsgSet:
		DB		'FLAG',00,00
MsgSetNot:
		DB		'not Set',00,00
arg1:	DS		1;
arg2:	DS		1;

INIT:
		LD		A,020H
		CALL	COUT
		CALL	DELAY
		RET
$INCLUDE buffer.asm
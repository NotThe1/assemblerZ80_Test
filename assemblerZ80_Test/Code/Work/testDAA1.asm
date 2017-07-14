;	testDAA.asm

PSTRING		EQU		0852H
NEWLINE		EQU		088CH
DELAY		EQU		0CE1H
COUT		EQU		0861H
		
			
ARG1_MAX	EQU		0
ARG2_MAX	EQU		100
		
NINE		EQU		10;

			ORG		1800H
Start:
		CALL	ClearBuffer
		CALL	INIT
		CALL	NEWLINE
		
		CALL	InitArgPair				; set values to Zero
		
Loop1:
		
		CALL	AddByteToBufferSpace	; arg2 is in ACC
		LD		A,(arg1L)
		CP		00
		CALL	Z,LineBreak
		CALL	IncArg1
		JR		NC,Loop1
		
		CALL	PrintBuffer
		RST		38H
		JP		Start

LineBreak:
		CALL	AddLFToBuffer
		CALL	PrintBufferAndClear
		RET
		
INIT:
		LD		A,020H
		CALL	COUT
		CALL	DELAY
		RET
		
$INCLUDE buffer.asm		
$INCLUDE incArgs.asm

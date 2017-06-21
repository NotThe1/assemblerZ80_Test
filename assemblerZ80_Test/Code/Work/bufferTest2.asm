;bufferTest2.asm

PSTRING		EQU 0852H
NEWLINE		EQU 088CH
DELAY		EQU 0CE1H
COUT		EQU 0861H


FLAG_SIGN		EQU		053H		; S
FLAG_ZERO		EQU		05AH		; Z
FLAG_5			EQU		058H		; X
FLAG_HALF		EQU		053H		; S
FLAG_3			EQU		058H		; X
FLAG_PARITY		EQU		050H		; P
FLAG_OVER		EQU		056H		; V
FLAG_AS			EQU		04EH		; N
FLAG_CARRY		EQU		043H		; C


		ORG		1800H
		
Start:
		LD		IX,flagLetter
		LD		A,(IX+FLAG_PARITY)
		CALL	ClearBuffer
		LD		A,FLAG_SIGN
		CALL	AddToBuffer


		LD		A,FLAG_SIGN
		OR		020H
		CALL	AddToBuffer;
		
		
MAIN:		
		CALL	INIT ; SET UART MARK LEVEL


		CALL	NEWLINE
		CALL	PrintBuffer	
		RST		38H
		
INIT:
		LD		A,0FFH
		CALL	COUT
		CALL	DELAY
		RET
		
flagLetter:
		DB		FLAG_CARRY	; c
		DB		FLAG_AS		; n
		DB		FLAG_PARITY	; p		DB		FLAG_OVER
		DB		FLAG_3		; x
		DB		FLAG_HALF	; h
		DB		FLAG_5		; x
		DB		FLAG_ZERO	; z
		DB		FLAG_SIGN	; s
		

		
	
		$INCLUDE buffer.asm

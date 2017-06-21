;bufferTest3.asm

PSTRING		EQU 0852H
NEWLINE		EQU 088CH
DELAY		EQU 0CE1H
COUT		EQU 0861H


FLAG_SIGN		EQU		053H		; S
FLAG_ZERO		EQU		05AH		; Z
FLAG_5			EQU		058H		; X
FLAG_HALF		EQU		048H		; H
FLAG_3			EQU		058H		; X
FLAG_PARITY		EQU		050H		; P
FLAG_OVER		EQU		056H		; V
FLAG_AS			EQU		04EH		; N
FLAG_CARRY		EQU		043H		; C


		ORG		1800H
		
Start:

		CALL	ClearBuffer
		
		LD		HL,lastFlagLetter
		LD		C,00
		LD		B,flagCount


Loop1:

		LD		A,(HL)

		DEC		HL
		PUSH	HL
		PUSH	BC
		CALL	AddToBuffer
		POP		BC
		POP		HL
		DJNZ	Loop1

;		LD		A,FLAG_SIGN
;		OR		020H
;		CALL	AddToBuffer;
		
		
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
		
;flagIndex:
;		DS		1
		
flagLetters:
		DB		FLAG_CARRY	; c
		DB		FLAG_AS		; n
		DB		FLAG_PARITY	; p	FLAG_OVER
		DB		FLAG_3		; x
		DB		FLAG_HALF	; h
		DB		FLAG_5		; x
		DB		FLAG_ZERO	; z
lastFlagLetter:
		DB		FLAG_SIGN	; s
flagCount	EQU ($-flagLetters)+1
		

		
	
		$INCLUDE buffer.asm

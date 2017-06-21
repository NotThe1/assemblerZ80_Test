;bufferTest5.asm

PSTRING		EQU 0852H
NEWLINE		EQU 088CH
DELAY		EQU 0CE1H
COUT		EQU 0861H


			ORG		1800H
		
Start:
		CALL	INIT			; SET UART MARK LEVEL
		CALL	NEWLINE
		LD		IX,Title
		CALL	PSTRING
		CALL	NEWLINE
		CALL	ClearBuffer
		
		LD		A,010H			; initalize Acc		
		LD		B,0FFH			; Set thje loop count
		LD		C,00
loop:
		LD		(bSave),BC
		ADD		A,B
		PUSH	AF
		CALL	AddByteToBufferSpace
		POP		AF
		
		PUSH	AF
		CALL	DecodeFlags
		PUSH	AF
		POP		AF
		CALL	displayLine
		POP		AF
		LD		BC,(bSave)
		DJNZ	loop
		RST		38H
		
			
displayLine:
		CALL	INIT
		CALL	PrintBuffer
		CALL	ClearBuffer
		RET
		
INIT:
		LD		A,020H
		CALL	COUT
		CALL	DELAY
		RET
		
bSave:	ds	2
		
Title:
		DB		'xx',00
		
$INCLUDE decodeFlags.asm
		

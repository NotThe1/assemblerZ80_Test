;flags2.asm

COUT 		EQU		0861H
OUT2X 		EQU		0875H
NEWLINE 	EQU		088CH
ONESPACE 	EQU		0897H
DELAY 		EQU		0CE1H

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
		LD		B,00H
		LD		(Bsave),BC
		SUB		A,A
		PUSH	AF
		CALL	Show	; 
Loop1:	
		LD		(Bsave),BC	; save the counter
		POP		AF
		INC		A
		PUSH	AF
		CALL	Show
		LD		BC,(Bsave)	; restore the counter
;		DJNZ	Loop1		; loop control
		RST		38H
		
		
Show:	
		EX		AF,AF'		; save the Acc and flags
		CALL	DELAY
		CALL	NEWLINE
;		CALL	NEWLINE
		EX		AF,AF'		; restore the Acc and flags
		PUSH	AF
		CALL	OUT2X		; Show the Acc
		CALL	ONESPACE
		POP		DE
		LD		A,E			; Get Flags into Acc
		CALL	OUT2X		; Show the flags
		
		CALL	ONESPACE
		
		LD		A,FLAG_SIGN
		CALL	COUT
		
		LD		A,FLAG_SIGN
		OR		020H
		CALL	COUT
		RET
		
INIT:
		LD		A,0FFH
		CALL	COUT
		CALL	DELAY
		RET
		
		; -------------
OUT1X:
		PUSH	AF 
        AND		0FH ;      ani  0fh 
		ADD		A,30H  ;    adi  "0" 
		CP		3AH
		JR		C,OUT1X1
		ADD		A,7
OUT1X1:
	     CALL COUT
	     POP AF
	     RET


OUT2X:
		RRC		A
		RRC		A
		RRC		A
		RRC		A
		CALL	OUT1X
		RRC		A
		RRC		A
		RRC		A
		RRC		A
		CALL	OUT1X
		RET
AddHexToBuffer:
; Input - Acc byte to be converted to ASCII and put into buffer
;		- HL points at the Buffer

		
ClearBuffer:
		LD	HL, Buffer		; Point at Default
ClearBufferX:
		LD	B,BufferSize	;	
		LD	A,00
ClearBuffer1:
		LD	(HL),A
		DJNZ	ClearBuffer1	; loop thru buffer
		RET
			
		
AddtoBuffer:
; Input - Acc byte to be  put into  Default buffer
		LD	HL,Buffer
AddtoBufferX:		
; Input - Acc byte to be  put into buffer
;		- HL points at the Buffer

		PUSH	AF
		LD		HL,BufferCount		; point at buffer counter
		LD		A,BufferSize		; get the buffer limit
		CP		(HL)
		JR		NZ,AddtoBuffer0
		POP		AF					; rebalance Stack
		RET							; do not add, the buffer is full
AddtoBuffer0:
		INC		(HL)				; increment the buffer count
		INC		A					; get past the counter
		LD		C,A
		LD		B,00				; BC now has the current position in the buffer
		ADD		HL,	BC				; HL points at the actual position
		POP		AF					; Get the byte
		LD		(HL),A				; put byte in buffer
		RET
		
flagLetter:
		DB		FLAG_CARRY	; c
		DB		FLAG_AS		; n
		DB		FLAG_PARITy	; p		DB		FLAG_OVER
		DB		FLAG_3		; x
		DB		FLAG_HALF	; h
		DB		FLAG_5		; x
		DB		FLAG_ZERO	; z
		DB		FLAG_SIGN	; s


Bsave:	DS		2
BufferSize 		EQU	80;
		ORG		2000H
Buffer:
BufferCount:	DB	00;
				DS	BufferSize
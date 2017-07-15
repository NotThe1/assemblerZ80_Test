;bufferTest1.asm
mPrintString	EQU		 0852H		; monitor's PSTRING

;		ORG		1900H
		ORG		(($/10H) + 1) * 10H	
		
BufferSize 		EQU	80;




PrintBufferAndClear:
		CALL PrintBuffer			; print and fall thru
		
ClearBuffer:
		LD		HL, Buffer			; Point at Default
ClearBufferX:
		LD		B,BufferSize
		SUB		A,A					; clear the value
ClearBuffer1:
		LD		(HL),A
		INC		HL
		DJNZ	ClearBuffer1		; loop thru buffer
		RET
		
PrintBufferLF:
; end print with a new line
		CALL AddLFToBuffer
		
PrintBuffer:
; Sends the default buffer to the Serial Device
		LD		IX, Buffer+1		; point at the data
		CALL	mPrintString		; display
		RET
		
AddLFToBuffer:
; puts newline in Buffer
		LD		A,0DH					; CR
		CALL	AddToBuffer
		LD		A,0AH					; LF
		CALL	AddToBuffer
		RET
		
AddNibbleToBuffer:
		PUSH	AF 
		AND		0FH 					; Get LSNibble
		ADD		A,30H					; Get to Add ASCII Zero
		CP		3AH						; is it Less than ASCII Colon
		JR		C,AddNibbleToBuffer0	; ok if not
		ADD		A,7						; else adjust up to ASCII "A"
AddNibbleToBuffer0:
		CALL	AddToBuffer
		POP		AF
		RET


AddByteToBuffer:
		RRC		A
		RRC		A
		RRC		A
		RRC		A
		CALL	AddNibbleToBuffer
		RRC		A
		RRC		A
		RRC		A
		RRC		A
		CALL	AddNibbleToBuffer
		RET
		
AddByteToBufferSpace:
; Add byte and Space to buffer
		CALL	AddByteToBuffer
		CALL	AddSpaceToBuffer
		RET
		
AddSpaceToBuffer:
; Add space to buffer
		LD		A,020H				; Space
		CALL	AddToBuffer
		RET
		
AddToBuffer:
; Input - Acc byte to be  put into  Default buffer
		LD	HL,Buffer
AddToBufferX:		
; Input - Acc byte to be  put into buffer
;		- HL points at the Buffer

		PUSH	AF
		LD		A,BufferSize		; get the buffer limit
		CP		(HL)
		JR		NZ,AddToBuffer0
		POP		AF					; rebalance Stack
		RET							; do not add, the buffer is full
AddToBuffer0:
		INC		(HL)				; increment the buffer count
		LD		C,(HL)
		LD		B,00				; BC now has the current position in the buffer
		ADD		HL,	BC				; HL points at the actual position
		POP		AF					; Get the byte
		LD		(HL),A				; put byte in buffer
		RET
		



;		ORG		1930H
		ORG		(($/10H) + 1) * 10H
Buffer:
BufferCount:	DB	00;
				DS	BufferSize
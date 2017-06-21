;bufferTest1.asm
mPrintString	EQU		 0852H		; monitor's PSTRING

;		ORG		1900H
BufferSize 		EQU	80;



		ORG		(($/10H) + 1) * 10H
ClearBuffer:
		LD		HL, Buffer		; Point at Default
ClearBufferX:
		LD		B,BufferSize	;	
		SUB		A,A				; clear the value
ClearBuffer1:
		INC		HL
		DJNZ	ClearBuffer1	; loop thru buffer
		RET
		
PrintBuffer:
; Sends the default buffer to the Serial Device
		LD		IX, Buffer+1	; point at the data
		CALL	mPrintString	; display
		RET
			
		
AddToBuffer:
; Input - Acc byte to be  put into  Default buffer
		LD	HL,Buffer
AddToBufferX:		
; Input - Acc byte to be  put into buffer
;		- HL points at the Buffer

		PUSH	AF
;		LD		HL,BufferCount		; point at buffer counter
		LD		A,BufferSize		; get the buffer limit
		CP		(HL)
		JR		NZ,AddToBuffer0
		POP		AF					; rebalance Stack
		RET							; do not add, the buffer is full
AddToBuffer0:
		INC		(HL)				; increment the buffer count
;		INC		A					
		LD		C,(HL)
		LD		B,00				; BC now has the current position in the buffer
		ADD		HL,	BC				; HL points at the actual position
;		INC		HL					; get past the counter
		POP		AF					; Get the byte
		LD		(HL),A				; put byte in buffer
		RET
		



;		ORG		1930H
		ORG		(($/10H) + 1) * 10H
Buffer:
BufferCount:	DB	00;
				DS	BufferSize
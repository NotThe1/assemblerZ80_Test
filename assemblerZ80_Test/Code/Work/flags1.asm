;flags1.asm

COUT 		EQU		0861H
OUT2X 		EQU		0875H
NEWLINE 	EQU		088CH
ONESPACE 	EQU		0897H
DELAY 		EQU		0CE1H

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
		DJNZ	Loop1		; loop control
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
		RET
		
INIT:
		LD		A,0FFH
		CALL	COUT
		CALL	DELAY
		RET
		
Bsave:	DS		2

;bufferTest1.asm

		ORG		1800H
		
Start:
		CALL	ClearBuffer
		LD		B,00H
		
Loop1:
		LD		(bSave),BC
		LD		A,B
		CALL	AddToBuffer
		LD		BC,(bSave)
		DJNZ	Loop1
		
		RST		38H
		
bSave:	DS		2		
		$INCLUDE buffer.asm

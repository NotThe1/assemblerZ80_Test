; testCodes10_1F.asm

	DJNZ -5
	LD DE,01234H
	LD (DE),A
	INC DE
	INC D
	DEC D
	LD D,07
	RLA
	JR 5
	ADD HL,DE
	LD A,(DE)
	DEC DE
	INC E
	DEC E
	LD E,0FFH
	RRA
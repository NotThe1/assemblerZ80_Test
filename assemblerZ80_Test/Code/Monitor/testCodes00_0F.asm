; testCodes00_0F.asm

	NOP
	LD BC,1234H
	LD (BC),A
	INC BC
	INC B
	DEC B
	LD B,12H
	RLCA
	EX	AF,AF'
	ADD HL,BC
	LD A,(BC)
	DEC BC
	INC C
	DEC C
	LD C,12H
	RRCA

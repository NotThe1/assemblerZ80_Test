; testCodesF0_FF.asm

	RET P
	POP AF
	JP P,04567H
	DI
	CALL P,0005H
	PUSH AF
	OR 07H
	RST 30H
	RET M
	LD SP,HL
	JP M,0100H
	EI
	CALL M, 02222H
	; IY
	CP 00
	RST 38H
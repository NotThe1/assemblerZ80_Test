; testCodesC0_CF.asm

	RET NZ
	POP BC
	JP NZ,04567H
	JP 0ABCDH
	CALL NZ,0005H
	PUSH BC
	ADD A,07H
	RST 00H
	RET Z
	RET
	JP Z,0100H
	; BITS
	CALL Z, 02222H
	CALL 05555H
	ADC A,00
	RST 08H
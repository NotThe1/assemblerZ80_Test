;ClearMem.asm

	ORG		1800H
	JP		Start
	ORG		1880H
Start:
	LD		BC,6000H
	LD		DE,2001H
	LD		HL,2000H
	LD		(HL),00h
	
	LDIR
	RST		30H
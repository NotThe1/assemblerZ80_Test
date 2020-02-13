; testCodesLDbbnn.asm

	LD (0100H),HL	; rnd0
	LD (0110H),BC	; rnd1
	LD (0120H),DE	; rnd2
	LD (0130H),DE	; rnd3
	LD (0140H),SP	; rnd4
	
	LD HL,(0140H)	; rnd4
	LD BC,(0130H)	; rnd3
	LD DE,(0120H)	; rnd2
	LD DE,(0110H)	; rnd1
	LD SP,(0100H)	; rnd0
	NOP
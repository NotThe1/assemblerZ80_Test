;ByHexCode_05_CBADtoCBFF.asm

aWord	EQU		01234H	; nn 
aByte	EQU		5AH		; n
dByte	EQU		0DDH	; d
;    PC  ->	$

LCBAD: 	RES 5, L
LCBAE: 	RES 5, (HL)
LCBAF: 	RES 5, A
LCBB0:	RES 6, B
LCBB1: 	RES 6, C
LCBB2: 	RES 6, D
LCBB3: 	RES 6, E
LCBB4: 	RES 6, H
LCBB5: 	RES 6, L
LCBB6: 	RES 6, (HL)
LCBB7: 	RES 6, A
LCBB8:	RES 7, B
LCBB9: 	RES 7, C
LCBBA: 	RES 7, D
LCBBB: 	RES 7, E
LCBBC: 	RES 7, H
LCBBD: 	RES 7, L
LCBBE: 	RES 7, (HL)
LCBBF: 	RES 7, A
LCBC0:	SET 0, B
LCBC1:	SET 0, C
LCBC2:	SET 0, D
LCBC3:	SET 0, E
LCBC4: 	SET 0, H
LCBC5: 	SET 0, L
LCBC6: 	SET 0, (HL)
LCBC7: 	SET 0, A
LCBC8:	SET 1, B
LCBC9: 	SET 1, C
LCBCA: 	SET 1, D
LCBCB: 	SET 1, E
LCBCC: 	SET 1, H
LCBCD: 	SET 1, L
LCBCE: 	SET 1, (HL)
LCBCF: 	SET 1, A
LCBD0:	SET 2, B
LCBD1: 	SET 2, C
LCBD2: 	SET 2, D
LCBD3: 	SET 2, E
LCBD4: 	SET 2, H
LCBD5: 	SET 2, L
LCBD6: 	SET 2, (HL)
LCBD7: 	SET 2, A
LCBD8:	SET 3, B
LCBD9: 	SET 3, C
LCBDA: 	SET 3, D
LCBDB: 	SET 3, E
LCBDC: 	SET 3, H
LCBDD: 	SET 3, L
LCBDE: 	SET 3, (HL)
LCBDF: 	SET 3, A
LCBE0:	SET 4, B
LCBE1: 	SET 4, C
LCBE2: 	SET 4, D
LCBE3: 	SET 4, E
LCBE4: 	SET 4, H
LCBE5: 	SET 4, L
LCBE6: 	SET 4, (HL)
LCBE7: 	SET 4, A
LCBE8:	SET 5, B
LCBE9: 	SET 5, C
LCBEA: 	SET 5, D
LCBEB: 	SET 5, E
LCBEC: 	SET 5, H
LCBED: 	SET 5, L
LCBEE: 	SET 5, (HL)
LCBEF: 	SET 5, A
LCBF0:	SET 6, B
LCBF1: 	SET 6, C
LCBF2: 	SET 6, D
LCBF3: 	SET 6, E
LCBF4: 	SET 6, H
LCBF5: 	SET 6, L
LCBF6: 	SET 6, (HL)
LCBF7: 	SET 6, A
LCBF8:	SET 7, B
LCBF9: 	SET 7, C
LCBFA: 	SET 7, D
LCBFB: 	SET 7, E
LCBFC: 	SET 7, H
LCBFD: 	SET 7, L
LCBFE: 	SET 7, (HL)
LCBFF: 	SET 7, A
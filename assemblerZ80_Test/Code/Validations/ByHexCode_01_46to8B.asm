;ByHexCode_01_46to8B.asm

aWord	EQU		01234H	; nn 
aByte	EQU		5AH		; n
dByte	EQU		0DDH	; d
;    PC  ->	$

L46:	LD B, (HL)
L47:	LD B, A
L48:	LD C, B
L49:	LD C, C
L4A:	LD C, D
L4B:	LD C, E
L4C:	LD C, H
L4D:	LD C, L
L4E:	LD C, (HL)
L4F:	LD C, A
L50:	LD D, B
L51:	LD D, C
L52:	LD D, D
L53:	LD D, E
L54:	LD D, H
L55:	LD D, L
L56:	LD D, (HL)
L57:	LD D, A
L58:	LD E, B
L59:	LD E, C
L5A:	LD E, D
L5B:	LD E, E
L5C:	LD E, H
L5D:	LD E, L
L5E:	LD E, (HL)
L5F:	LD E, A
L60:	LD H, B
L61:	LD H, C
L62:	LD H, D
L63:	LD H, E
L64:	LD H, H
L65:	LD H, L
L66:	LD H, (HL)
L67:	LD H, A
L68:	LD L, B
L69:	LD L, C
L6A:	LD L, D
L6B:	LD L, E
L6C:	LD L, H
L6D:	LD L, L
L6E:	LD L, (HL)
L6F:	LD L, A
L70:	LD (HL), B
L71:	LD (HL), C
L72:	LD (HL), D
L73:	LD (HL), E
L74:	LD (HL), H
L75:	LD (HL), L
L76:	HALT
L77:	LD (HL), A
L78:	LD A, B
L79:	LD A, C
L7A:	LD A, D
L7B:	LD A, E
L7C:	LD A, H
L7D:	LD A, L
L7E:	LD A, (HL)
L7F:	LD A, A
L80:	ADD A, B
L81:	ADD A, C
L82:	ADD A, D
L83:	ADD A, E
L84:	ADD A, H
L85:	ADD A, L
L86:	ADD A, (HL)
L87:	ADD A, A
L88:	ADC A, B
L89:	ADC A, C
L8A:	ADC A, D
L8B:	ADC A, E
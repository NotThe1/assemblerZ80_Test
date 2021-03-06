;ByHexCode_03_CB07toCB59.asm

aWord	EQU		01234H	; nn 
aByte	EQU		5AH		; n
dByte	EQU		0DDH	; d
;    PC  ->	$

LCB07:	RLC A
LCB08:	RRC B
LCB09:	RRC C
LCB0A:	RRC D
LCB0B:	RRC E
LCB0C:	RRC H
LCB0D:	RRC L
LCB0E:	RRC (HL)
LCB0F:	RRC A
LCB10:	RL B
LCB11:	RL C
LCB12:	RL D
LCB13:	RL E
LCB14:	RL H
LCB15:	RL L
LCB16:	RL (HL)
LCB17:	RL A
LCB18:	RR B
LCB19:	RR C
LCB1A:	RR D
LCB1B:	RR E
LCB1C:	RR H
LCB1D:	RR L
LCB1E:	RR (HL)
LCB1F:	RR A
LCB20:	SLA B
LCB21:	SLA C
LCB22:	SLA D
LCB23:	SLA E
LCB24:	SLA H
LCB25:	SLA L
LCB26:	SLA (HL)
LCB27:	SLA A
LCB28:	SRA B
LCB29:	SRA C
LCB2A:	SRA D
LCB2B:	SRA E
LCB2C:	SRA H
LCB2D:	SRA L
LCB2E:	SRA (HL)
LCB2F:	SRA A
XCB30:	;SLL B*
XCB31:	;SLL C*
XCB32:	;SLL D*
XCB33:	;SLL E*
XCB34:	;SLL H*
XCB35:	;SLL L*
XCB36:	;SLL (HL)*
XCB37:	;SLL A*
LCB38:	SRL B
LCB39:	SRL C
LCB3A:	SRL D
LCB3B:	SRL E
LCB3C:	SRL H
LCB3D:	SRL L
LCB3E:	SRL (HL)
LCB3F:	SRL A
LCB40:	BIT 0, B
LCB41:	BIT 0, C
LCB42:	BIT 0, D
LCB43:	BIT 0, E
LCB44: 	BIT 0, H
LCB45: 	BIT 0, L
LCB46: 	BIT 0, (HL)
LCB47: 	BIT 0, A
LCB48:	BIT 1, B
LCB49: 	BIT 1, C
LCB4A: 	BIT 1, D
LCB4B: 	BIT 1, E
LCB4C: 	BIT 1, H
LCB4D: 	BIT 1, L
LCB4E: 	BIT 1, (HL)
LCB4F: 	BIT 1, A
LCB50:	BIT 2, B
LCB51: 	BIT 2, C
LCB52: 	BIT 2, D
LCB53: 	BIT 2, E
LCB54: 	BIT 2, H
LCB55: 	BIT 2, L
LCB56: 	BIT 2, (HL)
LCB57: 	BIT 2, A
LCB58:	BIT 3, B
LCB59: 	BIT 3, C
;cout.asm

COUT	EQU		0861H
DELAY	EQU		0CE1H

	ORG	1800H
start:
	LD	B,07FH
	PUSH	BC
loop:
	LD	A,B
	CALL COUT
	POP		BC
	CALL	DELAY
	DJNZ	loop
	HALT
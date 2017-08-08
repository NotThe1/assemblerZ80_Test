;	incArgsPrime.asm

; two routines to alow incrementing a pair of values by a prime number
; 
; used to test word opcodes. 


;		ORG		(($/10H) + 2) * 10H	

InitArgPair:
		
;arg1
		LD		HL,0000H
		LD		(arg1),HL
		LD		(arg2),HL
		
		LD		A,offsetCount/2				; point at the middle of the list
		LD		(arg2Index),A
		XOR		A							; ACC = 0; S,C,H,N = 0; Z,P = 1
		LD		(arg1Index),A
		
		RET
		
InitArg2:
		LD		HL,0000H
		LD		(arg2),HL
		
		LD		A,offsetCount/2				; point at the middle of the list
		LD		(arg2Index),A
		RET
		
MakeArgsMinusOne:
		LD		HL,0FFFFH
		LD		(arg1),HL
		LD		(arg2),HL
		RET
	

IncArg1:
;; On return next value in HL. CY set means limit exceeded, else CY reset  value OK.
;		PUSH	BC					
		LD		A,(arg1Index)
		INC		A
		CP		offsetCount
		JP		C,IncArg1A
		XOR		A
IncArg1A:
		LD		(arg1Index),A		; store index
		
		ADD		A,A					; need to adjust for word length
		LD		C,A
		LD		B,0					; BC at offset
		
		LD		HL,offsets			; get current arg1
		ADD		HL,BC				; HL pointing at  new increment value
		LD		C,(HL)
		INC		HL
		LD		B,(HL)				; BC has increment value
		LD		HL,(arg1)			; get current value
		ADD		HL,BC				; new value in HL
		RET		C					; return with carry to signal overlimit HL not valid

		LD		(arg1),HL
		RET							; exit with new value in HL
GetArg1:
		LD		HL,(arg1);
		RET

IncArg2:
;; On return next value in HL. CY set means limit exceeded, else CY reset  value OK.
;		PUSH	BC					
		LD		A,(arg2Index)
		INC		A
		CP		offsetCount
		JP		C,IncArg2A
		XOR		A
IncArg2A:
		LD		(arg2Index),A		; store index
		
		ADD		A,A					; need to adjust for word length
		LD		C,A
		LD		B,0					; BC at offset
		
		LD		HL,offsets			; get current arg1
		ADD		HL,BC				; HL pointing at  new increment value
		LD		C,(HL)
		INC		HL
		LD		B,(HL)				; BC has increment value
		LD		HL,(arg2)			; get current value
		ADD		HL,BC				; new value in HL
		RET		C					; return with carry to signal overlimit HL not valid

		LD		(arg2),HL
		RET							; exit with new value in HL
GetArg2:
		LD		HL,(arg2);
		
		RET		
arg1:	DW		0000;
arg2:	DW		0000;

arg1Index:	DS		1
arg2Index:	DS		1


; offsets are all the prime numbers under 100 decimal

offsets:
		DW		0002H		; 2
		DW		997
		DW		0061H		; 97
		DW		881
		DW		0003H		; 3
		DW		0131
		DW		0059H		; 89
		DW		211
		DW		0005H		; 5
		DW		0053H		; 83
		DW		0007H		; 7
		DW		004FH		; 79
		DW		000BH		; 11
		DW		0049H		; 73
		DW		000DH		; 13
		DW		0047H		; 71
		DW		0011H		; 17
		DW		311
		DW		0043H		; 67
		DW		0001H		; 01
		DW		0013H		; 19
		DW		003DH		; 61
		DW		811
		DW		0017H		; 23
		DW		003BH		; 59
		DW		001DH		; 29
		DW		0035H		; 53
		DW		001FH		; 31
		DW		911
		DW		002FH		; 47
		DW		2099
		DW		0029H		; 41
		DW		863
		DW		002BH		; 43
		DW		991
		DW		0025H		; 37
offsetCount		EQU			($ - offsets)/2



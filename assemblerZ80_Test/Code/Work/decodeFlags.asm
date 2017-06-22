;decodeFlags.asm


		ORG		(($/10H) + 1) * 10H
	
		
DecodeFlags:
; Will decode the condition codes and put their values in the default buffer.
; Upper case signifies a 1 and lower case a 0;
; Upon return AF will be restored to its original value

		PUSH	AF					; preserve condition codes
		POP		DE					; move flags to DE
		
		PUSH	DE
		CALL	GetSign				; get Sign flag state
		CALL	AddToBuffer			; put into buffer
		POP		DE
		
		PUSH	DE
		CALL	GetZero				; get Zro flag state
		CALL	AddToBuffer			; put into buffer
		POP		DE
		
		PUSH	DE
		CALL	Get5				; get Bit5 flag state
		CALL	AddToBuffer			; put into buffer
		POP		DE
		
		PUSH	DE
		CALL	GetHalf				; get Half flag state
		CALL	AddToBuffer			; put into buffer
		POP		DE
		
		PUSH	DE
		CALL	Get3				; get Bit3 flag state
		CALL	AddToBuffer			; put into buffer
		POP		DE
		
		PUSH	DE
		CALL	GetParity			; get Parity flag state
;		CALL	GetOverflow			; get Overflowflag state
		CALL	AddToBuffer			; put into buffer
		POP		DE
		
		PUSH	DE
		CALL	GetAS				; get Add/Subtract flag(N) state
		CALL	AddToBuffer			; put into buffer
		POP		DE
		
		PUSH	DE
		CALL	GetCarry			; get Carry flag state
		CALL	AddToBuffer			; put into buffer
		
		CALL	AddLFToBuffer		; ad in a newline
		POP		AF					; restore AF from calling code
		RET

GetCarry:
; called with the AF loaded into DE, so flags are in E
		LD		A,FlagCarry		; assume not set
		BIT		bitCarry,E
		RET		NZ					; exit if assumed correctltly
		OR		020H				; else show as upper case
		RET							; and exit
GetAS:
; called with the AF loaded into DE, so flags are in E
		LD		A,FlagAS		; assume not set
		BIT		bitAS,E
		RET		NZ					; exit if assumed correctltly
		OR		020H				; else show as upper case
		RET							; and exit
GetParity:
; called with the AF loaded into DE, so flags are in E
		LD		A,FlagParity		; assume not set
		BIT		bitParity,E
		RET		NZ					; exit if assumed correctltly
		OR		020H				; else show as upper case
		RET							; and exit
GetOverflow:
; called with the AF loaded into DE, so flags are in E
		LD		A,FlagOverflow		; assume not set
		BIT		bitOverflow,E
		RET		NZ					; exit if assumed correctltly
		OR		020H				; else show as upper case
		RET							; and exit
		
Get3:
; called with the AF loaded into DE, so flags are in E
		LD		A,Flag3		; assume not set
		BIT		bit3,E
		RET		NZ					; exit if assumed correctltly
		OR		020H				; else show as upper case
		RET							; and exit
		
GetHalf:
; called with the AF loaded into DE, so flags are in E
		LD		A,FlagHalf		; assume not set
		BIT		bitHalf,E
		RET		NZ					; exit if assumed correctltly
		OR		020H				; else show as upper case
		RET							; and exit
		
Get5:
; called with the AF loaded into DE, so flags are in E
		LD		A,Flag5		; assume not set
		BIT		bit5,E
		RET		NZ					; exit if assumed correctltly
		OR		020H				; else show as upper case
		RET							; and exit
		
GetZero:
; called with the AF loaded into DE, so flags are in E
		LD		A,FlagZero		; assume not set
		BIT		bitZero,E
		RET		NZ					; exit if assumed correctltly
		OR		020H				; else show as upper case
		RET							; and exit
		
GetSign:
; called with the AF loaded into DE, so flags are in E
		LD		A,FlagSign		; assume not set
		BIT		bitSign,E
		RET		NZ					; exit if assumed correctltly
		OR		020H				; else show as upper case
		RET							; and exit
		
		
		
bitSign			EQU		7			; sign position	
bitZero			EQU		6			; zero position		
bit5			EQU		5			; bit 5 position		
bitHalf			EQU		4			; half carry position		
bit3			EQU		3			; bit 3 position		
bitOverflow		EQU		2			; Overflow/Parity position			
bitParity		EQU		2			; Parity/Overflow position	
bitAS			EQU		1			; Add/Subtract position		
bitCarry		EQU		0			; carry position		


FlagSign		EQU		053H		; S
FlagZero		EQU		05AH		; Z
Flag5			EQU		058H		; X
FlagHalf		EQU		048H		; H
Flag3			EQU		058H		; X
FlagParity		EQU		050H		; P
FlagOverflow	EQU		056H		; V
FlagAS			EQU		04EH		; N
FlagCarry		EQU		043H		; C


	
		
	
		$INCLUDE buffer.asm

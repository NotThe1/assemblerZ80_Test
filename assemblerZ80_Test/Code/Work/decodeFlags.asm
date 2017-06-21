;decodeFlags.asm

;PSTRING		EQU 0852H
;NEWLINE		EQU 088CH
;DELAY		EQU 0CE1H
;COUT		EQU 0861H


		ORG		(($/10H) + 1) * 10H
		
;Start:
;		CALL	ClearBuffer
;
;		LD		A,0FFH
;		INC		A
;		PUSH	AF
;		CALL	AddByteToBufferSpace
;		POP		AF
;		
;		PUSH	AF
;		CALL	DecodeFlags
;		POP		AF
;		INC		A
;		PUSH	AF
;		CALL	DecodeFlags
;		POP		AF
;		INC		A
;		PUSH	AF
;		CALL	DecodeFlags
		
;MAIN:		
;		CALL	INIT ; SET UART MARK LEVEL
;
;
;		CALL	NEWLINE
;		CALL	PrintBuffer	
;		RST		38H
;		
;INIT:
;		LD		A,0FFH
;		CALL	COUT
;		CALL	DELAY
;		RET
		
DecodeFlags:
		PUSH	AF				; preserve condition codes
		POP		DE				; move flags to DE
		
		PUSH	DE
		CALL	GetSign			; get flag state
		CALL	AddToBuffer		; put into buffer
		POP		DE
		
		PUSH	DE
		CALL	GetZero			; get flag state
		CALL	AddToBuffer		; put into buffer
		POP		DE
		
		PUSH	DE
		CALL	Get5			; get flag state
		CALL	AddToBuffer		; put into buffer
		POP		DE
		
		PUSH	DE
		CALL	GetHalf			; get flag state
		CALL	AddToBuffer		; put into buffer
		POP		DE
		
		PUSH	DE
		CALL	Get3			; get flag state
		CALL	AddToBuffer		; put into buffer
		POP		DE
		
;		PUSH	DE
;		CALL	GetOverflow			; get flag state
;		CALL	AddToBuffer		; put into buffer
;		POP		DE
		
		PUSH	DE
		CALL	GetParity			; get flag state
		CALL	AddToBuffer		; put into buffer
		POP		DE
		
		PUSH	DE
		CALL	GetAS			; get flag state
		CALL	AddToBuffer		; put into buffer
		POP		DE
		
		PUSH	DE
		CALL	GetCarry		; get flag state
		CALL	AddToBuffer		; put into buffer
		POP		DE
		
		CALL	AddLFToBuffer	; newline
		
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
		
		
		
bitCarry		EQU		0			; carry position		
bitAS			EQU		1			; Add/Subtract position		
bitParity		EQU		2			; Parity/Overflow position	
bitOverflow		EQU		2			; Overflow/Parity position			
bit3			EQU		3			; bit 3 position		
bitHalf			EQU		4			; half carry position		
bit5			EQU		5			; bit 5 position		
bitZero			EQU		6			; zero position		
bitSign			EQU		7			; sign position	

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

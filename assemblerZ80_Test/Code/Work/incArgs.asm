;	incArgs.asm

; two routines to alow incrementing a pair of values
; by controlling the limits of the upper nibble and lower nibble independently.
; used to test the DAA opcode where two BCD values are added to get a new BCD.

InitArgPair:
		XOR		A
		LD		(arg1L),A
		LD		(arg1H),A
		LD		(arg2L),A
		LD		(arg2H),A
		RET
		
	

IncArg1:
; On return next value in ACC. CY set means limit exceeded, else CY reset  value OK.


		LD		HL,arg1L
		INC		(HL)				; increment lo nibble
		LD		A,(HL)		
		CP		10				; limit reached ? MAX_UNIT	EQU		10;
		JP		C,GetArg1 		; No, OK 
; else increment upper nibble	
		XOR		A				; zero out A
		LD		(arg1L),A		; set low nibble to zero
		                    
		LD		HL,arg1H    
		INC		(HL)          
		LD		A,(HL)      
		CP		10 				; MAX_TENS	EQU		10;    
		JP		C,GetArg1 		; OK 
;		XOR		A				; clear the Acc
;		LD		(arg1L),A
;		LD		(arg1H),A		; init Arg 1 value
		
		LD		HL,arg2H		; * if adding
		LD		DE,arg1H		; * this removes
		LD		BC,0002			; * redundant
		LDIR					; * value pairs ( 1+2 = 2+1)


		SCF                 
		RET						; return with CY set indicating limit exceeded
GetArg1:                   
		LD		A,(arg1H)   
		SLA		A           
		SLA		A           
		SLA		A           
		SLA		A			; move nibble to bits 7-4
		LD		HL,arg1L    
		ADD		A,(HL)      
		SCF                 
		CCF                 
		RET					; return with CY reset indicating limit not exceeded
		
arg1H:	DS		1;
arg1L:	DS		1;


IncArg2:
; On return CY set means limit exceeded, else CY reset OK



		LD		HL,arg2L
		INC		(HL)				; increment lo nibble
		LD		A,(HL)		
		CP		10					; limit reached ? MAX_UNIT	EQU		10;
		JP		C,GetArg2 			; No, OK 
; else increment upper nibble	
		XOR		A					; zero out A
		LD		(arg2L),A			; set low nibble to zero
		                    
		LD		HL,arg2H    
		INC		(HL)          
		LD		A,(HL)      
		CP		10 					; MAX_TENS	EQU		10;    
		JP		C,GetArg2 			; OK 
		
		
		SCF                 
		RET							; return with CY set indicating limit exceeded
GetArg2:                   
		LD		A,(arg2H)   
		SLA		A           
		SLA		A           
		SLA		A           
		SLA		A					; move nibble to bits 7-4
		LD		HL,arg2L    
		ADD		A,(HL)      
		SCF                 
		CCF                 
		RET							; return with CY reset indicating limit not exceeded
		
arg2H:	DS		1;
arg2L:	DS		1;
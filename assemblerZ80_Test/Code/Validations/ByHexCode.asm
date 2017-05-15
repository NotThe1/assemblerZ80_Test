;ByHexCode_00.asm

aWord	EQU		01234H	; nn 
aByte	EQU		5AH		; n
dByte	EQU		0DDH	; d
;    PC  ->	$

$include  ByHexCode_00_00to45.ASM
$include  ByHexCode_01_46to8B.asm
$include  ByHexCode_02_8CtoCB06.asm
$include  ByHexCode_03_CB07toCB59.asm
$include  ByHexCode_04_CB5AtoCBAC.asm
$include  ByHexCode_05_CBADtoCBFF.asm
$include  ByHexCode_06_CCtoDD9C.asm
$include  ByHexCode_07_DD9DtoDDCB.asm
$include  ByHexCode_08_DDCB0toDDCB.asm
$include  ByHexCode_09_DDCB1toDDCB.asm
$include  ByHexCode_10_DDCB2toED69.asm
$include  ByHexCode_11_ED6AtoFD61.asm

$include  ByHexCode_12_FD62toFDCB.asm
$include  ByHexCode_13_FDCB0toFDCB.asm
$include  ByHexCode_14_FDCB1toFDCB.asm
$include  ByHexCode_15_FDCB2toFF.asm
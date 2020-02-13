; testCallRet



			ORG	1000H
Start:
			CALL	Target0
			CALL	Target1
			HALT
Target0:
			RETI
Target1:
			RETN
			HALT
			

			
				(byte) 0X00,(byte) 0X01,(byte) 0X02,(byte) 0X03,(byte) 0X04,(byte) 0X05,(byte) 0X06,(byte) 0X07,
				(byte) 0X08,(byte) 0X09,(byte) 0X0A,(byte) 0X0B,(byte) 0X0C,(byte) 0X0D,(byte) 0X0E,(byte) 0X0F,
				(byte) 0X10,(byte) 0X11,(byte) 0X12,(byte) 0X13,(byte) 0X14,(byte) 0X15,(byte) 0X16,(byte) 0X17,
				(byte) 0X18,(byte) 0X19,(byte) 0X1A,(byte) 0X1B,(byte) 0X1C,(byte) 0X1D,(byte) 0X1E,(byte) 0X1F,
				(byte) 0X20,(byte) 0X21,(byte) 0X22,(byte) 0X23,(byte) 0X24,(byte) 0X25,(byte) 0X26,(byte) 0X27,
				(byte) 0X28,(byte) 0X29,(byte) 0X2A,(byte) 0X2B,(byte) 0X2C,(byte) 0X2D,(byte) 0X2E,(byte) 0X2F

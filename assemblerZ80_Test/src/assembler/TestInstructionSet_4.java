package assembler;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;
//import static org.hamcrest.CoreMatchers.equalTo;
//import static org.junit.Assert.assertThat;

public class TestInstructionSet_4 {
	InstructionSet is;
	String instruction;
	String subOpCode;
	String arg1, arg2;
	SourceLineAnalyzer analyzer;
	String[] instructions ;
	int[] sizes;
	Object[] baseCodes;
	SourceLineParts slp;
	SubInstructionSet subInstructionSet;

	@Before
	public void setUp() throws Exception {
		subInstructionSet = SubInstructionSet.getInstance();
		analyzer = new SourceLineAnalyzer();
		assertThat("handle imports", true, equalTo(true));
		 instructions = new String[] { "CCF", "CPD", "CPDR", "CPI", "CPIR", "CPL", "DAA", "DI", "EI", "EXX",
				"HLT", "IND", "INDR", "INI", "INIR", "LDD", "LDDR", "LDI", "LDIR", "NEG", "NOP", "OTDR", "OTIR", "OUTD",
				"OUTI", "RETI", "RETN", "RLA", "RLCA", "RLD", "RRA", "RRCA", "RRD", "SCF" };
		 sizes = new int[]{1,2,2,2,2,1,1,1,1,1,1,2,2,2,2,2,2,2,2,2,1,2,2,2,2,2,2,1,1,2,1,1,2,1};
		 
		  baseCodes = new Object[] {new byte[]{(byte) 0X3F},new byte[] {(byte) 0XED, (byte) 0XA9},
				  new byte[] {(byte) 0XED, (byte) 0XB9},new byte[] {(byte) 0XED, (byte) 0XA1},
				  new byte[] {(byte) 0XED, (byte) 0XB1},new byte[] {(byte) 0X2F},new byte[] {(byte) 0X27},
				  new byte[] {(byte) 0XF3},new byte[] {(byte) 0XFB},new byte[] {(byte) 0XD9},new byte[] {(byte) 0X76},
				  new byte[] {(byte) 0XED, (byte) 0XAA},new byte[] {(byte) 0XED, (byte) 0XBA},
				  new byte[] {(byte) 0XED, (byte) 0XA2},new byte[] {(byte) 0XED, (byte) 0XB2},
				  new byte[] {(byte) 0XED, (byte) 0XA8},new byte[] {(byte) 0XED, (byte) 0XB8},
				  new byte[] {(byte) 0XED, (byte) 0XA0},new byte[] {(byte) 0XED, (byte) 0XB0},
				  new byte[] {(byte) 0XED, (byte) 0X44},new byte[] {(byte) 0X0},
				  new byte[] {(byte) 0XED, (byte) 0XBB},new byte[] {(byte) 0XED, (byte) 0XB3},
				  new byte[] {(byte) 0XED, (byte) 0XAB},new byte[] {(byte) 0XED, (byte) 0XA3},
				  new byte[] {(byte) 0XED, (byte) 0X4D},new byte[] {(byte) 0XED, (byte) 0X45},
				  new byte[] {(byte) 0X17},new byte[] {(byte) 0X7},new byte[] {(byte) 0XED, (byte) 0X6F},
				  new byte[] {(byte) 0X1F},new byte[] {(byte) 0X0F},new byte[] {(byte) 0XED, (byte) 0X67},
				  new byte[] {(byte) 0X37}};

	}// setUp

	@Test
	public void testNoArgs() {
		String line;
		String ins;
		instructions = new String[]{"CCF"};
		for(int i = 0; i < instructions.length;i++){
			ins = instructions[i];
			line = makeLine(ins, "; comment");
			slp = analyzer.analyze(line);	
		System.out.println(line);	
			
		assertThat("instruction " + i, instruction, equalTo(slp.getInstruction()));
		subOpCode = slp.getSubOpCode();
		assertThat("subOpCode " + i, instruction + "_0", equalTo(subOpCode));
		assertThat("size " + i, sizes[i], equalTo(slp.getOpCodeSize()));
		
		assertArrayEquals("Base Codes " + i,(byte[]) baseCodes[i],subInstructionSet.getBaseCodes(subOpCode));
		}

	}// testCCF
	// --------------------------------------------------------------------------------------------------
	private String makeLine(String instruction, String arg1, String arg2) {
		this.instruction = instruction;
		this.arg1 = arg1;
		this.arg2 = arg2;
		String ans = (this.instruction + " " + this.arg1).trim();
		if (arg2.trim() != "") {
			ans = (this.instruction + " " + this.arg1 + "," + arg2).trim();
		} //
			// System.out.println(ans);
		return ans;
	}// makeLine

	private String makeLine(String instruction, String arg1) {
		return makeLine(instruction, arg1, "");
	}// makeLine

	private String makeLine(String instruction) {
		return makeLine(instruction, "", "");
	}// makeLine
}// class TestInstructionSet_4

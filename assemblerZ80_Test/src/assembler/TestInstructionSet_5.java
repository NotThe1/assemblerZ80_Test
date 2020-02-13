package assembler;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;
//import static org.hamcrest.CoreMatchers.equalTo;
//import static org.junit.Assert.assertThat;

public class TestInstructionSet_5 {
	InstructionSet is;
	String instruction;
	String subOpCode;
	String arg1, arg2;
	SourceLineAnalyzer analyzer;
	String[] instructions ;
	int[] sizes;
	SourceLineParts slp;

	@Before
	public void setUp() throws Exception {
		analyzer = new SourceLineAnalyzer();
		assertThat("handle imports", true, equalTo(true));
//		 instructions = new String[] { "CCF", "CPD", "CPDR", "CPI", "CPIR", "CPL", "DAA", "DI", "EI", "EXX",
//				"HLT", "IND", "INDR", "INI", "INIR", "LDD", "LDDR", "LDI", "LDIR", "NEG", "NOP", "OTDR", "OTIR", "OUTD",
//				"OUTI", "RETI", "RETN", "RLA", "RLCA", "RLD", "RRA", "RRCA", "RRD", "SCF" };
//		 sizes = new int[]{1,2,2,2,2,1,1,1,1,1,1,2,2,2,2,2,2,2,2,2,1,2,2,2,2,2,2,1,1,2,1,1,2,1};
	}// setUp

	@Test
	public void testRET() {
		String line;
		String instruction;
//		instructions = new String[]{"CCF"};
		for(int i = 0; i < instructions.length;i++){
			instruction = instructions[i];
			line = makeLine(instruction, "; comment");
			slp = analyzer.analyze(line);	
			
		assertThat("instruction " + i, instruction, equalTo(slp.getInstruction()));
		assertThat("subOpCode " + i, instruction + "_0", equalTo(slp.getSubOpCode()));
		assertThat("size " + i, sizes[i], equalTo(slp.getOpCodeSize()));
		System.out.println(line);	
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

//	private String makeLine(String instruction) {
//		return makeLine(instruction, "", "");
//	}// makeLine
}// class TestInstructionSet_5
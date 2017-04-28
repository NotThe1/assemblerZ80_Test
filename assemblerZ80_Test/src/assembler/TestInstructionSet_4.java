package assembler;

import static org.hamcrest.CoreMatchers.equalTo;
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

	@Before
	public void setUp() throws Exception {
		analyzer = new SourceLineAnalyzer();
		assertThat("handle imports", true, equalTo(true));
		 instructions = new String[] { "CCF", "CPD", "CPDR", "CPI", "CPIR", "CPL", "DAA", "DI", "EI", "EXX",
				"HLT", "IND", "INDR", "INI", "INIR", "LDD", "LDDR", "LDI", "LDIR", "NEG", "NOP", "OTDR", "OTIR", "OUTD",
				"OUTI", "RETI", "RETN", "RLA", "RLCA", "RLD", "RRA", "RRCA", "RRD", "SCF" };
	}// setUp

	@Test
	public void testNoArgs() {
		String line;
		String instruction;
//		instructions = new String[]{"CCF"};
		for(int i = 0; i < instructions.length;i++){
			instruction = instructions[i];
			line = makeLine(instruction, "; comment");
			analyzer.analyze(line);	
			
		assertThat(instruction + " 1", instruction, equalTo(analyzer.getInstruction()));
		assertThat(instruction + " 2", instruction + "_0", equalTo(analyzer.getSubOpCode()));
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

	private String makeLine(String instruction) {
		return makeLine(instruction, "", "");
	}// makeLine
}// class TestInstructionSet_4

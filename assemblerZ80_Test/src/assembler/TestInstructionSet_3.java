package assembler;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
//import static org.hamcrest.CoreMatchers.equalTo;
//import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

public class TestInstructionSet_3 {
	InstructionSet is;
	String instruction;
	String subOpCode;
	String arg1, arg2;
	SourceLineAnalyzer analyzer;

	@Before
	public void setUp() throws Exception {
		analyzer = new SourceLineAnalyzer();
		assertThat("handle imports", true, equalTo(true));
	}// setUp

	@Test
	public void testOR() {
		String line = makeLine("OR", "(IY+3)");
		analyzer.analyze(line);
		assertThat("OR  1", instruction, equalTo(analyzer.getInstruction()));
		assertThat("OR  2", "OR_1", equalTo(analyzer.getSubOpCode()));

		line = makeLine("OR", "M");
		analyzer.analyze(line);
		assertThat("OR  3", instruction, equalTo(analyzer.getInstruction()));
		assertThat("OR  4", "OR_2", equalTo(analyzer.getSubOpCode()));

		line = makeLine("OR", "expression");
		analyzer.analyze(line);
		assertThat("OR  5", instruction, equalTo(analyzer.getInstruction()));
		assertThat("OR  6", "OR_3", equalTo(analyzer.getSubOpCode()));

	}// testOR

	@Test
	public void testOUT() {
		String line = makeLine("OUT", "(C)", "B");
		analyzer.analyze(line);
		assertThat("OUT  1", instruction, equalTo(analyzer.getInstruction()));
		assertThat("OUT  2", "OUT_1", equalTo(analyzer.getSubOpCode()));

		line = makeLine("OUT", "(expression)", "A");
		analyzer.analyze(line);
		assertThat("OUT  3", instruction, equalTo(analyzer.getInstruction()));
		assertThat("OUT  4", "OUT_2", equalTo(analyzer.getSubOpCode()));

	}// testOUT

	@Test
	public void testPOP() {
		String line = makeLine("POP", "AF");
		analyzer.analyze(line);
		assertThat("POP  1", instruction, equalTo(analyzer.getInstruction()));
		assertThat("POP  2", "POP_1", equalTo(analyzer.getSubOpCode()));

		line = makeLine("POP", "IX");
		analyzer.analyze(line);
		assertThat("POP  3", instruction, equalTo(analyzer.getInstruction()));
		assertThat("POP  4", "POP_2", equalTo(analyzer.getSubOpCode()));

	}// testPOP

	@Test
	public void testPUSH() {
		String line = makeLine("PUSH", "BC");
		analyzer.analyze(line);
		assertThat("PUSH  1", instruction, equalTo(analyzer.getInstruction()));
		assertThat("PUSH  2", "PUSH_1", equalTo(analyzer.getSubOpCode()));

		line = makeLine("PUSH", "IY");
		analyzer.analyze(line);
		assertThat("PUSH  3", instruction, equalTo(analyzer.getInstruction()));
		assertThat("PUSH  4", "PUSH_2", equalTo(analyzer.getSubOpCode()));

	}// testPUSH

	@Test
	public void testRES() {
		String line = makeLine("RES", "expression", "(IX+ expression)");
		analyzer.analyze(line);
		assertThat("RES  1", instruction, equalTo(analyzer.getInstruction()));
		assertThat("RES  2", "RES_1", equalTo(analyzer.getSubOpCode()));

		line = makeLine("RES", "expression", "A");
		analyzer.analyze(line);
		assertThat("RES  3", instruction, equalTo(analyzer.getInstruction()));
		assertThat("RES  4", "RES_2", equalTo(analyzer.getSubOpCode()));

	}// testRES
	
	@Test
	public void testRET() {
		String line = makeLine("RET", "PE");
		System.out.println(line);
		analyzer.analyze(line);
		assertThat("RET  1", instruction, equalTo(analyzer.getInstruction()));
		assertThat("RET  2", "RET_1", equalTo(analyzer.getSubOpCode()));

		line = makeLine("RET");
		analyzer.analyze(line);
		assertThat("RET  3", instruction, equalTo(analyzer.getInstruction()));
		assertThat("RET  4", "RET_2", equalTo(analyzer.getSubOpCode()));

	}// testRET
	
	@Test
	public void testRL() {
		String line = makeLine( "RL","(IX+3)");
		analyzer.analyze(line);
		assertThat("RL  1", instruction, equalTo(analyzer.getInstruction()));
		assertThat("RL  2", "RL_1", equalTo(analyzer.getSubOpCode()));

		 line = makeLine( "RL","(HL)");
		analyzer.analyze(line);
		assertThat("RL  3", instruction, equalTo(analyzer.getInstruction()));
		assertThat("RL  4", "RL_2", equalTo(analyzer.getSubOpCode()));

	}//testRL
	
	@Test
	public void testRLC() {
		String line = makeLine( "RLC","(IY+3)");
		analyzer.analyze(line);
		assertThat("RLC  1", instruction, equalTo(analyzer.getInstruction()));
		assertThat("RLC  2", "RLC_1", equalTo(analyzer.getSubOpCode()));

		 line = makeLine( "RLC","A");
		analyzer.analyze(line);
		assertThat("RLC  3", instruction, equalTo(analyzer.getInstruction()));
		assertThat("RLC  4", "RLC_2", equalTo(analyzer.getSubOpCode()));

	}//testRL
	
	@Test
	public void testRR() {
		String line = makeLine( "RR","(IY+3)");
		analyzer.analyze(line);
		assertThat("RR  1", instruction, equalTo(analyzer.getInstruction()));
		assertThat("RR  2", "RR_1", equalTo(analyzer.getSubOpCode()));

		 line = makeLine( "RR","B");
		analyzer.analyze(line);
		assertThat("RR  3", instruction, equalTo(analyzer.getInstruction()));
		assertThat("RR  4", "RR_2", equalTo(analyzer.getSubOpCode()));

	}//testRL
	
	@Test
	public void testRRC() {
		String line = makeLine( "RRC","(IY+3)");
		analyzer.analyze(line);
		assertThat("RRC  1", instruction, equalTo(analyzer.getInstruction()));
		assertThat("RRC  2", "RRC_1", equalTo(analyzer.getSubOpCode()));

		 line = makeLine( "RRC","C");
		analyzer.analyze(line);
		assertThat("RRC  3", instruction, equalTo(analyzer.getInstruction()));
		assertThat("RRC  4", "RRC_2", equalTo(analyzer.getSubOpCode()));

	}//testRL
	
	@Test
	public void testRST() {
		String line = makeLine( "RST","expression");
		analyzer.analyze(line);
		assertThat("RST  1", instruction, equalTo(analyzer.getInstruction()));
		assertThat("RST  2", "RST_1", equalTo(analyzer.getSubOpCode()));

	}//testRST
	
	@Test
	public void testSBC() {
		
		analyzer.analyze(makeLine( "SBC","A","(IX+3)"));
		assertThat("SBC  1", instruction, equalTo(analyzer.getInstruction()));
		assertThat("SBC  1", "SBC_1", equalTo(analyzer.getSubOpCode()));

		
		analyzer.analyze(makeLine( "SBC","A","B"));
		assertThat("SBC  2", instruction, equalTo(analyzer.getInstruction()));
		assertThat("SBC  2", "SBC_2", equalTo(analyzer.getSubOpCode()));
		
		analyzer.analyze(makeLine( "SBC","A","(HL)"));
		assertThat("SBC  2", instruction, equalTo(analyzer.getInstruction()));
		assertThat("SBC  2", "SBC_2", equalTo(analyzer.getSubOpCode()));
		
		analyzer.analyze(makeLine( "SBC","A","1+2"));
		assertThat("SBC  3", instruction, equalTo(analyzer.getInstruction()));
		assertThat("SBC  3", "SBC_3", equalTo(analyzer.getSubOpCode()));
		
		
		
		analyzer.analyze(makeLine( "SBC","HL","SP"));
		assertThat("SBC  4", instruction, equalTo(analyzer.getInstruction()));
		assertThat("SBC  4", "SBC_4", equalTo(analyzer.getSubOpCode()));
		
//		analyzer.analyze(makeLine( "SBC","A"));
//		assertThat("SBC  BAD_OPCODE 1", instruction, equalTo(analyzer.getInstruction()));
//		assertThat("SBC  BAD_OPCODE 1", null, equalTo(analyzer.getSubOpCode()));
//		
//		analyzer.analyze(makeLine( "SBC","HL"));
//		assertThat("SBC  BAD_OPCODE 2", instruction, equalTo(analyzer.getInstruction()));
//		assertThat("SBC  BAD_OPCODE 2", null, equalTo(analyzer.getSubOpCode()));
//		
//		analyzer.analyze(makeLine( "SBC"));
//		assertThat("SBC  BAD_OPCODE 3", instruction, equalTo(analyzer.getInstruction()));
//		assertThat("SBC  BAD_OPCODE 3", null, equalTo(analyzer.getSubOpCode()));
		
	}//testSBC
	
	@Test
	public void testSET() {
		String line = makeLine("SET", "expression", "(IX+ expression)");
		analyzer.analyze(line);
		assertThat("SET  1", instruction, equalTo(analyzer.getInstruction()));
		assertThat("SET  2", "SET_1", equalTo(analyzer.getSubOpCode()));

		line = makeLine("SET", "expression", "A");
		analyzer.analyze(line);
		assertThat("SET  3", instruction, equalTo(analyzer.getInstruction()));
		assertThat("SET  4", "SET_2", equalTo(analyzer.getSubOpCode()));

	}// testSET
	
	@Test
	public void testSLA() {
		String line = makeLine( "SLA","(IY+3)");
		analyzer.analyze(line);
		assertThat("SLA  1", instruction, equalTo(analyzer.getInstruction()));
		assertThat("SLA  2", "SLA_1", equalTo(analyzer.getSubOpCode()));

		 line = makeLine( "SLA","C");
		analyzer.analyze(line);
		assertThat("SLA  3", instruction, equalTo(analyzer.getInstruction()));
		assertThat("SLA  4", "SLA_2", equalTo(analyzer.getSubOpCode()));

	}//testSLA
	
	@Test
	public void testSRA() {
		String line = makeLine( "SRA","(IY+3)");
		analyzer.analyze(line);
		assertThat("SRA  1", instruction, equalTo(analyzer.getInstruction()));
		assertThat("SRA  2", "SRA_1", equalTo(analyzer.getSubOpCode()));

		 line = makeLine( "SRA","C");
		analyzer.analyze(line);
		assertThat("SRA  3", instruction, equalTo(analyzer.getInstruction()));
		assertThat("SRA  4", "SRA_2", equalTo(analyzer.getSubOpCode()));

	}//testSRA
	
	@Test
	public void testSRL() {
		String line = makeLine( "SRL","(IY+3)");
		analyzer.analyze(line);
		assertThat("SRL  1", instruction, equalTo(analyzer.getInstruction()));
		assertThat("SRL  2", "SRL_1", equalTo(analyzer.getSubOpCode()));

		 line = makeLine( "SRL","C");
		analyzer.analyze(line);
		assertThat("SRL  3", instruction, equalTo(analyzer.getInstruction()));
		assertThat("SRL  4", "SRL_2", equalTo(analyzer.getSubOpCode()));

	}//testSRL
	
	@Test
	public void testSUB() {
		String line = makeLine( "SUB","(IX+3)");
		analyzer.analyze(line);
		assertThat("SUB  1", instruction, equalTo(analyzer.getInstruction()));
		assertThat("SUB  2", "SUB_1", equalTo(analyzer.getSubOpCode()));

		 line = makeLine( "SUB","E");
		analyzer.analyze(line);
		assertThat("SUB  3", instruction, equalTo(analyzer.getInstruction()));
		assertThat("SUB  4", "SUB_2", equalTo(analyzer.getSubOpCode()));

		 line = makeLine( "SUB","1 + 2");
		analyzer.analyze(line);
		assertThat("SUB  5", instruction, equalTo(analyzer.getInstruction()));
		assertThat("SUB  6", "SUB_3", equalTo(analyzer.getSubOpCode()));

	}//testSUB

	@Test
	public void testXOR() {
		String line = makeLine( "XOR","(IX+3)");
		analyzer.analyze(line);
		assertThat("XOR  1", instruction, equalTo(analyzer.getInstruction()));
		assertThat("XOR  2", "XOR_1", equalTo(analyzer.getSubOpCode()));

		 line = makeLine( "XOR","E");
		analyzer.analyze(line);
		assertThat("XOR  3", instruction, equalTo(analyzer.getInstruction()));
		assertThat("XOR  4", "XOR_2", equalTo(analyzer.getSubOpCode()));

		 line = makeLine( "XOR","1 + 2");
		analyzer.analyze(line);
		assertThat("XOR  5", instruction, equalTo(analyzer.getInstruction()));
		assertThat("XOR  6", "XOR_3", equalTo(analyzer.getSubOpCode()));

	}//testXOR

	

	


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
}// class TestInstructionSet_3

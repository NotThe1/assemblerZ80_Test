package assembler;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;
//import static org.hamcrest.CoreMatchers.equalTo;
//import static org.junit.Assert.assertThat;


/**
 * 
 * @author Frank Martyn
 *    Tests the LD Instruction
 */

public class TestInstructionSet_2 {
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
	}// setUp
	
	@Test
	public void testLIT_A() {
		 String line = makeLine( "LD","A","(IX+expression)");
		analyzer.analyze(line);
		assertThat("LD  5", instruction, equalTo(analyzer.getInstruction()));
		assertThat("LD  6", "LD_1", equalTo(analyzer.getSubOpCode()));
		
		 line = makeLine( "LD","A","(BC)");
		analyzer.analyze(line);
		assertThat("LD  7", instruction, equalTo(analyzer.getInstruction()));
		assertThat("LD  8", "LD_2", equalTo(analyzer.getSubOpCode()));

		 line = makeLine( "LD","A","I");
		analyzer.analyze(line);
		assertThat("LD  9", instruction, equalTo(analyzer.getInstruction()));
		assertThat("LD  10", "LD_3", equalTo(analyzer.getSubOpCode()));

		 line = makeLine( "LD","A","(HL)");
		analyzer.analyze(line);
		assertThat("LD  1", instruction, equalTo(analyzer.getInstruction()));
		assertThat("LD  2", "LD_4", equalTo(analyzer.getSubOpCode()));

		 line = makeLine( "LD","A","B");
		analyzer.analyze(line);
		assertThat("LD  3", instruction, equalTo(analyzer.getInstruction()));
		assertThat("LD  4", "LD_4", equalTo(analyzer.getSubOpCode()));

		 line = makeLine( "LD","A","(expression)");
		analyzer.analyze(line);
		assertThat("LD  11", instruction, equalTo(analyzer.getInstruction()));
		assertThat("LD  12", "LD_5", equalTo(analyzer.getSubOpCode()));

		 line = makeLine( "LD","A","expression");
		analyzer.analyze(line);
		assertThat("LD  13", instruction, equalTo(analyzer.getInstruction()));
		assertThat("LD  14", "LD_6", equalTo(analyzer.getSubOpCode()));

	}//testLIT_A
	
	
	
	@Test
	public void testLIT_HL() {
		String line = makeLine( "LD","HL","(expression)");
		analyzer.analyze(line);
		assertThat("LD  1", instruction, equalTo(analyzer.getInstruction()));
		assertThat("LD  2", "LD_7", equalTo(analyzer.getSubOpCode()));
	}//testLIT_HL

	@Test
	public void testLIT_SP() {
		String line = makeLine( "LD","SP","HL");
		analyzer.analyze(line);
		assertThat("LD  1", instruction, equalTo(analyzer.getInstruction()));
//		assertThat("LD  2", "LD_8", equalTo(analyzer.getSubOpCode()));
		
		 line = makeLine( "LD","SP","IY");
		analyzer.analyze(line);
		assertThat("LD  3", instruction, equalTo(analyzer.getInstruction()));
		assertThat("LD  4", "LD_9", equalTo(analyzer.getSubOpCode()));
		
		 line = makeLine( "LD","SP","(expression)");
		analyzer.analyze(line);
		assertThat("LD  5", instruction, equalTo(analyzer.getInstruction()));
		assertThat("LD  6", "LD_10", equalTo(analyzer.getSubOpCode()));
		
		 line = makeLine( "LD","SP","expression");
		analyzer.analyze(line);
		assertThat("LD  7", instruction, equalTo(analyzer.getInstruction()));
		assertThat("LD  8", "LD_11", equalTo(analyzer.getSubOpCode()));
	}//testLIT_SP
	
	@Test
	public void testIND_BCDE() {
		String line = makeLine( "LD","(BC)","A");
		analyzer.analyze(line);
		assertThat("LD  1", instruction, equalTo(analyzer.getInstruction()));
		assertThat("LD  2", "LD_12", equalTo(analyzer.getSubOpCode()));

	}//testIND_BCDE
	
//	@Test
//	public void testIND_HL() {
//		String line = makeLine( "LD","(HL)","C");
//		analyzer.analyze(line);
//		assertThat("LD  1", instruction, equalTo(analyzer.getInstruction()));
//		assertThat("LD  2", "LD_20", equalTo(analyzer.getSubOpCode()));
//
//		 line = makeLine( "LD","HL","(expression)");
//		analyzer.analyze(line);
//		assertThat("LD  3", instruction, equalTo(analyzer.getInstruction()));
//		assertThat("LD  4", "LD_21", equalTo(analyzer.getSubOpCode()));
//
//		 line = makeLine( "LD","A","A");
//		analyzer.analyze(line);
//		assertThat("LD  5", instruction, equalTo(analyzer.getInstruction()));
//		assertThat("LD  6", "LD_20", equalTo(analyzer.getSubOpCode()));
//		
//		 line = makeLine( "LD","A","(expression)");
//		analyzer.analyze(line);
//		assertThat("LD  7", instruction, equalTo(analyzer.getInstruction()));
//		assertThat("LD  8", "LD_21", equalTo(analyzer.getSubOpCode()));
//
//
//	}//testIND_HL

	@Test
	public void testIND_XYd() {
		String line = makeLine( "LD","(IX+4)","B");
		analyzer.analyze(line);
		assertThat("LD  1", instruction, equalTo(analyzer.getInstruction()));
		assertThat("LD  2", "LD_13", equalTo(analyzer.getSubOpCode()));

		 line = makeLine( "LD","(IY+ expression)","expression");
		analyzer.analyze(line);
		assertThat("LD  3", instruction, equalTo(analyzer.getInstruction()));
		assertThat("LD  4", "LD_14", equalTo(analyzer.getSubOpCode()));

	}//testIND_XYd
	
	@Test
	public void testR8_RI() {
		String line = makeLine( "LD","I","A");
		analyzer.analyze(line);
		assertThat("LD  1", instruction, equalTo(analyzer.getInstruction()));
		assertThat("LD  2", "LD_15", equalTo(analyzer.getSubOpCode()));

	}//testR8_RI
	
	@Test
	public void testR16_SP() {
		String line = makeLine( "LD","SP","expression");
		analyzer.analyze(line);
		assertThat("LD  1", instruction, equalTo(analyzer.getInstruction()));
		assertThat("LD  2", "LD_11", equalTo(analyzer.getSubOpCode()));
		
		 line = makeLine( "LD","DE","expression");
		analyzer.analyze(line);
		assertThat("LD  3", instruction, equalTo(analyzer.getInstruction()));
		assertThat("LD  4", "LD_17", equalTo(analyzer.getSubOpCode()));

		 line = makeLine( "LD","BC","(expression)");
		analyzer.analyze(line);
		assertThat("LD  5", instruction, equalTo(analyzer.getInstruction()));
		assertThat("LD  6", "LD_16", equalTo(analyzer.getSubOpCode()));
		
		 line = makeLine( "LD","HL","(expression)");
		analyzer.analyze(line);
		assertThat("LD  7", instruction, equalTo(analyzer.getInstruction()));
		assertThat("LD  8", "LD_7", equalTo(analyzer.getSubOpCode()));

	}//testR16_SP

	@Test
	public void testR16_XY() {
		String line = makeLine( "LD","IX","expression");
		analyzer.analyze(line);
		assertThat("LD  1", instruction, equalTo(analyzer.getInstruction()));
		assertThat("LD  2", "LD_19", equalTo(analyzer.getSubOpCode()));

		 line = makeLine( "LD","IY","(expression)");
		analyzer.analyze(line);
		assertThat("LD  3", instruction, equalTo(analyzer.getInstruction()));
		assertThat("LD  4", "LD_18", equalTo(analyzer.getSubOpCode()));
		
		 line = makeLine( "LD","IX","(expression)");
		analyzer.analyze(line);
		assertThat("LD  5", instruction, equalTo(analyzer.getInstruction()));
		assertThat("LD  6", "LD_18", equalTo(analyzer.getSubOpCode()));

	}//testR16_SP

	@Test
	public void testR8() {
		String line = makeLine( "LD","B","C");
		analyzer.analyze(line);
		assertThat("LD  1", instruction, equalTo(analyzer.getInstruction()));
		assertThat("LD  2", "LD_20", equalTo(analyzer.getSubOpCode()));

		 line = makeLine( "LD","D","(expression)");
		analyzer.analyze(line);
		assertThat("LD  3", instruction, equalTo(analyzer.getInstruction()));
		assertThat("LD  4", "LD_22", equalTo(analyzer.getSubOpCode()));

		 line = makeLine( "LD","B","A");
		analyzer.analyze(line);
		assertThat("LD  5", instruction, equalTo(analyzer.getInstruction()));
		assertThat("LD  6", "LD_20", equalTo(analyzer.getSubOpCode()));
		
		 line = makeLine( "LD","C","(expression)");
		analyzer.analyze(line);
		assertThat("LD  7", instruction, equalTo(analyzer.getInstruction()));
		assertThat("LD  8", "LD_22", equalTo(analyzer.getSubOpCode()));

		 line = makeLine( "LD","E","(IX+expression)");
		analyzer.analyze(line);
		assertThat("LD  9", instruction, equalTo(analyzer.getInstruction()));
		assertThat("LD  10", "LD_21", equalTo(analyzer.getSubOpCode()));


	}//testR8



//	@Test
//	public void testIND_HL() {
//		String line = makeLine( "LD","(HL)","B");
//		analyzer.analyze(line);
//		assertThat("LD  1", instruction, equalTo(analyzer.getInstruction()));
//		assertThat("LD  2", "LD_2", equalTo(analyzer.getSubOpCode()));
//
//		 line = makeLine( "LD","(HL)","expression");
//		analyzer.analyze(line);
//		assertThat("LD  3", instruction, equalTo(analyzer.getInstruction()));
//		assertThat("LD  4", "LD_3", equalTo(analyzer.getSubOpCode()));
//
//	}//testIND_HL


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
}// class TestInstructionSet_2

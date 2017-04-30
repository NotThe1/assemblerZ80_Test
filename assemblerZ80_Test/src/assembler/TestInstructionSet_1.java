package assembler;

import static org.hamcrest.CoreMatchers.equalTo;
//import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;


public class TestInstructionSet_1 {
InstructionSet is;
String instruction;
String subOpCode;
String arg1,arg2;
SourceLineAnalyzer analyzer;

	@Before
	public void setUp() throws Exception {
		analyzer = new SourceLineAnalyzer();
		analyzer.analyze(" ADC   A   ,  A B  c D");

	}//setUp

	@Test
	public void testADC() {
		
		analyzer.analyze(makeLine( "ADC","A","(IX+3)"));
		assertThat("ADC  1", instruction, equalTo(analyzer.getInstruction()));
		assertThat("ADC  2", "ADC_1", equalTo(analyzer.getSubOpCode()));

		
		analyzer.analyze(makeLine( "ADC","A","B"));
		assertThat("ADC  3", instruction, equalTo(analyzer.getInstruction()));
		assertThat("ADC  4", "ADC_2", equalTo(analyzer.getSubOpCode()));
		
		analyzer.analyze(makeLine( "ADC","A","(HL)"));
		assertThat("ADC  5", instruction, equalTo(analyzer.getInstruction()));
		assertThat("ADC  6", "ADC_2", equalTo(analyzer.getSubOpCode()));
		
		analyzer.analyze(makeLine( "ADC","A","1+2"));
		assertThat("ADC  7", instruction, equalTo(analyzer.getInstruction()));
		assertThat("ADC  8", "ADC_3", equalTo(analyzer.getSubOpCode()));
		
		
		
		analyzer.analyze(makeLine( "ADC","HL","SP"));
		assertThat("ADC  9", instruction, equalTo(analyzer.getInstruction()));
		assertThat("ADC  10", "ADC_4", equalTo(analyzer.getSubOpCode()));
		
//		analyzer.analyze(makeLine( "ADC","A"));
//		assertThat("ADC  BAD_OPCODE 1", instruction, equalTo(analyzer.getInstruction()));
//		assertThat("ADC  BAD_OPCODE 2", null, equalTo(analyzer.getSubOpCode()));
//		
//		analyzer.analyze(makeLine( "ADC","HL"));
//		assertThat("ADC  BAD_OPCODE 3", instruction, equalTo(analyzer.getInstruction()));
//		assertThat("ADC  BAD_OPCODE 4", null, equalTo(analyzer.getSubOpCode()));
//		
//		analyzer.analyze(makeLine( "ADC"));
//		assertThat("ADC  BAD_OPCODE 5", instruction, equalTo(analyzer.getInstruction()));
//		assertThat("ADC  BAD_OPCODE 6", null, equalTo(analyzer.getSubOpCode()));
		
		
	}//testADC
	
	@Test
	public void testADD() {
		
		analyzer.analyze(makeLine( "ADD","A","(IY+3)"));
		assertThat("ADD  1", instruction, equalTo(analyzer.getInstruction()));
		assertThat("ADD  1", "ADD_1", equalTo(analyzer.getSubOpCode()));

		
		analyzer.analyze(makeLine( "ADD","A","C"));
		assertThat("ADD  2", instruction, equalTo(analyzer.getInstruction()));
		assertThat("ADD  2", "ADD_2", equalTo(analyzer.getSubOpCode()));
		
		analyzer.analyze(makeLine( "ADD","A","(HL)"));
		assertThat("ADD  2", instruction, equalTo(analyzer.getInstruction()));
		assertThat("ADD  2", "ADD_2", equalTo(analyzer.getSubOpCode()));
		
		analyzer.analyze(makeLine( "ADD","A","1+2"));
		assertThat("ADD  3", instruction, equalTo(analyzer.getInstruction()));
		assertThat("ADD  3", "ADD_3", equalTo(analyzer.getSubOpCode()));
		
		
		
		analyzer.analyze(makeLine( "ADD","HL","SP"));
		assertThat("ADD  4", instruction, equalTo(analyzer.getInstruction()));
		assertThat("ADD  4", "ADD_4", equalTo(analyzer.getSubOpCode()));
		
		analyzer.analyze(makeLine( "ADD","IX","BC"));
		assertThat("ADD  5", instruction, equalTo(analyzer.getInstruction()));
		assertThat("ADD  5", "ADD_5", equalTo(analyzer.getSubOpCode()));
		
		
//		analyzer.analyze(makeLine( "ADD","A"));
//		assertThat("ADD  BAD_OPCODE 1", instruction, equalTo(analyzer.getInstruction()));
//		assertThat("ADC  BAD_OPCODE 1", null, equalTo(analyzer.getSubOpCode()));
//		
//		analyzer.analyze(makeLine( "ADD","HL"));
//		assertThat("ADD  BAD_OPCODE 2", instruction, equalTo(analyzer.getInstruction()));
//		assertThat("ADD  BAD_OPCODE 2", null, equalTo(analyzer.getSubOpCode()));
//		
//		analyzer.analyze(makeLine( "ADD","IX"));
//		assertThat("ADD  BAD_OPCODE 2", instruction, equalTo(analyzer.getInstruction()));
//		assertThat("ADD  BAD_OPCODE 2", null, equalTo(analyzer.getSubOpCode()));
//		
//		
//		analyzer.analyze(makeLine( "ADD"));
//		assertThat("ADD  BAD_OPCODE 3", instruction, equalTo(analyzer.getInstruction()));
//		assertThat("ADD  BAD_OPCODE 3", null, equalTo(analyzer.getSubOpCode()));
		
		
	}//testADD
	
	@Test
	public void testAND() {
		String line = makeLine( "AND","(IX+3)");
		analyzer.analyze(line);
		assertThat("AND  1", instruction, equalTo(analyzer.getInstruction()));
		assertThat("AND  2", "AND_1", equalTo(analyzer.getSubOpCode()));

		 line = makeLine( "AND","(HL)");
		analyzer.analyze(line);
		assertThat("AND  3", instruction, equalTo(analyzer.getInstruction()));
		assertThat("AND  4", "AND_2", equalTo(analyzer.getSubOpCode()));

		 line = makeLine( "AND","1 + 2");
		analyzer.analyze(line);
		assertThat("AND  5", instruction, equalTo(analyzer.getInstruction()));
		assertThat("AND  6", "AND_3", equalTo(analyzer.getSubOpCode()));

	}//testAND
	
	@Test
	public void testBIT() {
		String line = makeLine( "BIT","0","(IX+3)");
		analyzer.analyze(line);
		assertThat("BIT  1", instruction, equalTo(analyzer.getInstruction()));
		assertThat("BIT  2", "BIT_1", equalTo(analyzer.getSubOpCode()));

		 line = makeLine( "BIT", "(1+2)", "D");
		analyzer.analyze(line);
		assertThat("BIT  3", instruction, equalTo(analyzer.getInstruction()));
		assertThat("BIT  4", "BIT_2", equalTo(analyzer.getSubOpCode()));

	}//testBit
	
	@Test
	public void testCALL() {
		String line = makeLine( "CALL","NC"," address");
		analyzer.analyze(line);
		assertThat("CALL  1", instruction, equalTo(analyzer.getInstruction()));
		assertThat("CALL  2", "CALL_1", equalTo(analyzer.getSubOpCode()));

		 line = makeLine( "CALL", "address");
		analyzer.analyze(line);
		assertThat("CALL  3", instruction, equalTo(analyzer.getInstruction()));
		assertThat("CALL  4", "CALL_2", equalTo(analyzer.getSubOpCode()));

	}//testCALL
	
	@Test
	public void testCCF() {
		String line = makeLine( "CCF","; comment");
		analyzer.analyze(line);
		assertThat("CCF  1", instruction, equalTo(analyzer.getInstruction()));
		assertThat("CCF  1", "CCF_0", equalTo(analyzer.getSubOpCode()));

	}//testCCF
	
	@Test
	public void testCP() {
		String line = makeLine( "CP","(IX+3)");
		analyzer.analyze(line);
		assertThat("CP  1", instruction, equalTo(analyzer.getInstruction()));
		assertThat("CP  2", "CP_1", equalTo(analyzer.getSubOpCode()));

		 line = makeLine( "CP","E");
		analyzer.analyze(line);
		assertThat("CP  3", instruction, equalTo(analyzer.getInstruction()));
		assertThat("CP  4", "CP_2", equalTo(analyzer.getSubOpCode()));

		 line = makeLine( "CP","1 + 2");
		analyzer.analyze(line);
		assertThat("CP  5", instruction, equalTo(analyzer.getInstruction()));
		assertThat("CP  6", "CP_3", equalTo(analyzer.getSubOpCode()));

	}//testCP
	
	@Test
	public void testDEC() {
		String line = makeLine( "DEC","(IY+3)");
		analyzer.analyze(line);
		assertThat("DEC  1", instruction, equalTo(analyzer.getInstruction()));
		assertThat("DEC  2", "DEC_1", equalTo(analyzer.getSubOpCode()));

		 line = makeLine( "DEC","BC");
		analyzer.analyze(line);
		assertThat("DEC  5", instruction, equalTo(analyzer.getInstruction()));
		assertThat("DEC  6", "DEC_2", equalTo(analyzer.getSubOpCode()));

		 line = makeLine( "DEC","IY");
		analyzer.analyze(line);
		assertThat("DEC  7", instruction, equalTo(analyzer.getInstruction()));
		assertThat("DEC  8", "DEC_3", equalTo(analyzer.getSubOpCode()));
		
		 line = makeLine( "DEC","H");
		analyzer.analyze(line);
		assertThat("DEC  3", instruction, equalTo(analyzer.getInstruction()));
		assertThat("DEC  4", "DEC_4", equalTo(analyzer.getSubOpCode()));

	}//testDEC
	
	@Test
	public void testDJNZ() {
		String line = makeLine( "DJNZ","expression");
		analyzer.analyze(line);
		assertThat("DJNZ  1", instruction, equalTo(analyzer.getInstruction()));
		assertThat("DJNZ  2", "DJNZ_1", equalTo(analyzer.getSubOpCode()));
	}//testDEC
	
	@Test
	public void testEX() {
		String line = makeLine( "EX","(SP)","HL");
		analyzer.analyze(line);
		assertThat("EX  1", instruction, equalTo(analyzer.getInstruction()));
		assertThat("EX  2", "EX_1", equalTo(analyzer.getSubOpCode()));
		
		 line = makeLine( "EX","(SP)","IY");
		analyzer.analyze(line);
		assertThat("EX  3", instruction, equalTo(analyzer.getInstruction()));
		assertThat("EX  4", "EX_2", equalTo(analyzer.getSubOpCode()));

		 line = makeLine( "EX","AF","AF'");
		analyzer.analyze(line);
		assertThat("EX  5", instruction, equalTo(analyzer.getInstruction()));
		assertThat("EX  6", "EX_3", equalTo(analyzer.getSubOpCode()));
		
		 line = makeLine( "EX","DE","HL'");
		analyzer.analyze(line);
		assertThat("EX  7", instruction, equalTo(analyzer.getInstruction()));
		assertThat("EX  8", "EX_4", equalTo(analyzer.getSubOpCode()));
		
	}//testDEC
	
	@Test
	public void testIM() {
		String line = makeLine( "IM","expression");
		analyzer.analyze(line);
		assertThat("IM  1", instruction, equalTo(analyzer.getInstruction()));
		assertThat("IM  2", "IM_1", equalTo(analyzer.getSubOpCode()));
	}//testDEC
	
	@Test
	public void testIN() {
		String line = makeLine( "IN","A","(C)");
		analyzer.analyze(line);
		assertThat("IN  1", instruction, equalTo(analyzer.getInstruction()));
		assertThat("IN  2", "IN_1", equalTo(analyzer.getSubOpCode()));
		
		 line = makeLine( "IN","A","(expression)");
		analyzer.analyze(line);
		assertThat("IN  3", instruction, equalTo(analyzer.getInstruction()));
		assertThat("IN  4", "IN_2", equalTo(analyzer.getSubOpCode()));
		
		 line = makeLine( "IN","B","(C)");
		analyzer.analyze(line);
		assertThat("IN  3", instruction, equalTo(analyzer.getInstruction()));
		assertThat("IN  4", "IN_3", equalTo(analyzer.getSubOpCode()));
		
	}//testDEC
	
	@Test
	public void testINC() {
		String line = makeLine( "INC","(IY+3)");
		analyzer.analyze(line);
		assertThat("INC  1", instruction, equalTo(analyzer.getInstruction()));
		assertThat("INC  2", "INC_1", equalTo(analyzer.getSubOpCode()));

		 line = makeLine( "INC","DE");
		analyzer.analyze(line);
		assertThat("INC  5", instruction, equalTo(analyzer.getInstruction()));
		assertThat("INC  6", "INC_2", equalTo(analyzer.getSubOpCode()));

		 line = makeLine( "INC","IY");
		analyzer.analyze(line);
		assertThat("INC  7", instruction, equalTo(analyzer.getInstruction()));
		assertThat("INC  8", "INC_3", equalTo(analyzer.getSubOpCode()));

		 line = makeLine( "INC","L");
		analyzer.analyze(line);
		assertThat("INC  3", instruction, equalTo(analyzer.getInstruction()));
		assertThat("INC  4", "INC_4", equalTo(analyzer.getSubOpCode()));

	}//testDEC
	
	@Test
	public void testJP() {
		String line = makeLine( "JP","Z","expression");
		analyzer.analyze(line);
		assertThat("JP  1", instruction, equalTo(analyzer.getInstruction()));
		assertThat("JP  2", "JP_1", equalTo(analyzer.getSubOpCode()));
		
		 line = makeLine( "JP","(XY)");
		analyzer.analyze(line);
		assertThat("JP  3", instruction, equalTo(analyzer.getInstruction()));
		assertThat("JP  4", "JP_2", equalTo(analyzer.getSubOpCode()));
		
		 line = makeLine( "JP","(HL)");
		analyzer.analyze(line);
		assertThat("JP  5", instruction, equalTo(analyzer.getInstruction()));
		assertThat("JP  6", "JP_3", equalTo(analyzer.getSubOpCode()));
		
		 line = makeLine( "JP","expression");
		analyzer.analyze(line);
		assertThat("JP  7", instruction, equalTo(analyzer.getInstruction()));
		assertThat("JP  8", "JP_4", equalTo(analyzer.getSubOpCode()));
		
	}//testJP
	

	@Test
	public void testJR() {
		String line = makeLine( "JR","NZ","expression");
		analyzer.analyze(line);
		assertThat("JR  1", instruction, equalTo(analyzer.getInstruction()));
		assertThat("JR  2", "JR_1", equalTo(analyzer.getSubOpCode()));
		
		 line = makeLine( "JR","expression");
		analyzer.analyze(line);
		assertThat("JR  3", instruction, equalTo(analyzer.getInstruction()));
		assertThat("JR  4", "JR_2", equalTo(analyzer.getSubOpCode()));
		
	}//testJR
	


	

	
	
//--------------------------------------------------------------------------------------------------	
	private String makeLine(String instruction,String arg1, String arg2){
		this.instruction = instruction;
		this.arg1 = arg1;
		this.arg2 = arg2;
		String ans = (this.instruction + " " + this.arg1).trim();
		if(arg2.trim()!=""){
			ans = (this.instruction + " " + this.arg1  + "," + arg2).trim();
		}//
		//System.out.println(ans);
		return ans;
	}//makeLine
	
	private String makeLine(String instruction,String arg1){	
		return makeLine(instruction,arg1,"");
	}//makeLine
	
	private String makeLine(String instruction){	
		return makeLine(instruction,"","");
	}//makeLine

}

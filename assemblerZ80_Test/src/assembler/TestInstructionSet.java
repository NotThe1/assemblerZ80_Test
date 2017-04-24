package assembler;

import static org.hamcrest.CoreMatchers.equalTo;
//import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;


public class TestInstructionSet {
InstructionSet is;
String instruction;
String subOpCode;
String arg1,arg2;
SourceLineAnalyzer analyzer;

	@Before
	public void setUp() throws Exception {
		analyzer = new SourceLineAnalyzer();
	}//setUp

	@Test
	public void testADC() {
		
		analyzer.analyze(makeLine( "ADC","A","(IX+3)"));
		assertThat("ADC  1", instruction, equalTo(analyzer.getInstruction()));
		assertThat("ADC  1", "ADC_1", equalTo(analyzer.getSubOpCode()));

		
		analyzer.analyze(makeLine( "ADC","A","B"));
		assertThat("ADC  2", instruction, equalTo(analyzer.getInstruction()));
		assertThat("ADC  2", "ADC_2", equalTo(analyzer.getSubOpCode()));
		
		analyzer.analyze(makeLine( "ADC","A","(HL)"));
		assertThat("ADC  2", instruction, equalTo(analyzer.getInstruction()));
		assertThat("ADC  2", "ADC_2", equalTo(analyzer.getSubOpCode()));
		
		analyzer.analyze(makeLine( "ADC","A","1+2"));
		assertThat("ADC  3", instruction, equalTo(analyzer.getInstruction()));
		assertThat("ADC  3", "ADC_3", equalTo(analyzer.getSubOpCode()));
		
		
		
		analyzer.analyze(makeLine( "ADC","HL","SP"));
		assertThat("ADC  4", instruction, equalTo(analyzer.getInstruction()));
		assertThat("ADC  4", "ADC_4", equalTo(analyzer.getSubOpCode()));
		
		analyzer.analyze(makeLine( "ADC","A",""));
		assertThat("ADC  BAD_OPCODE 1", instruction, equalTo(analyzer.getInstruction()));
		assertThat("ADC  BAD_OPCODE 1", InstructionSet.BAD_OPCODE, equalTo(analyzer.getSubOpCode()));
		
		analyzer.analyze(makeLine( "ADC","HL",""));
		assertThat("ADC  BAD_OPCODE 2", instruction, equalTo(analyzer.getInstruction()));
		assertThat("ADC  BAD_OPCODE 2", InstructionSet.BAD_OPCODE, equalTo(analyzer.getSubOpCode()));
		
		analyzer.analyze(makeLine( "ADC","",""));
		assertThat("ADC  BAD_OPCODE 3", instruction, equalTo(analyzer.getInstruction()));
		assertThat("ADC  BAD_OPCODE 3", InstructionSet.BAD_OPCODE, equalTo(analyzer.getSubOpCode()));
		
		
	}//testADC
	@Test
	public void testADD() {
		
		analyzer.analyze(makeLine( "ADD","A","(IX+3)"));
		assertThat("ADD  1", instruction, equalTo(analyzer.getInstruction()));
		assertThat("ADD  1", "ADD_1", equalTo(analyzer.getSubOpCode()));

		
		analyzer.analyze(makeLine( "ADD","A","B"));
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
		
		
		analyzer.analyze(makeLine( "ADD","A",""));
		assertThat("ADD  BAD_OPCODE 1", instruction, equalTo(analyzer.getInstruction()));
		assertThat("ADC  BAD_OPCODE 1", InstructionSet.BAD_OPCODE, equalTo(analyzer.getSubOpCode()));
		
		analyzer.analyze(makeLine( "ADD","HL",""));
		assertThat("ADD  BAD_OPCODE 2", instruction, equalTo(analyzer.getInstruction()));
		assertThat("ADD  BAD_OPCODE 2", InstructionSet.BAD_OPCODE, equalTo(analyzer.getSubOpCode()));
		
		analyzer.analyze(makeLine( "ADD","IX",""));
		assertThat("ADD  BAD_OPCODE 2", instruction, equalTo(analyzer.getInstruction()));
		assertThat("ADD  BAD_OPCODE 2", InstructionSet.BAD_OPCODE, equalTo(analyzer.getSubOpCode()));
		
		
		analyzer.analyze(makeLine( "ADD","",""));
		assertThat("ADD  BAD_OPCODE 3", instruction, equalTo(analyzer.getInstruction()));
		assertThat("ADD  BAD_OPCODE 3", InstructionSet.BAD_OPCODE, equalTo(analyzer.getSubOpCode()));
		
		
	}//testADD
	
	private String makeLine(String instruction,String arg1, String arg2){
		this.instruction = instruction;
		this.arg1 = arg1;
		this.arg2 = arg2;
		String ans = ans = (this.instruction + " " + this.arg1).trim();
		if(arg2.trim()!=""){
			ans = (this.instruction + " " + this.arg1  + "," + arg2).trim();
		}//
		System.out.println(ans);
		return ans;
	}//makeLine

}

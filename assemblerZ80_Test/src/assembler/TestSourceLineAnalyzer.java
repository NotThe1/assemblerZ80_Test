package assembler;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

public class TestSourceLineAnalyzer {
	SourceLineAnalyzer analyzer;

	@Before
	public void setUp() throws Exception {
		analyzer = new SourceLineAnalyzer();
	}//

	@Test
	public void testLineNumbers() {
		analyzer.analyze("");
		assertThat("Null line", false, equalTo(analyzer.isLineActive()));

		String lineNumber = "1234";
		String sourceLine = lineNumber + " ";
		analyzer.analyze(sourceLine);
		assertThat("Line Number  1", true, equalTo(analyzer.isLineActive()));
		assertThat("Line Number 2", false, equalTo(analyzer.hasComment()));
		assertThat("Line Number 3", true, equalTo(analyzer.hasLineNumber()));
		assertThat("Line Number 4", lineNumber, equalTo(analyzer.getLineNumber()));

		lineNumber = "1234";
		sourceLine = lineNumber + "XX";
		analyzer.analyze(sourceLine);
		assertThat("Line Number  1", true, equalTo(analyzer.isLineActive()));
		assertThat("Line Number 3", false, equalTo(analyzer.hasLineNumber()));

	}// testLineNumbers

	@Test
	public void testComments() {

		String comment = "; a line that is all comment";
		String sourceLine = comment;
		analyzer.analyze(sourceLine);
		assertThat("Comment line 1", true, equalTo(analyzer.isLineActive()));
		assertThat("Comment line 2", true, equalTo(analyzer.isLineAllComment()));
		assertThat("Comment line 3", true, equalTo(analyzer.hasComment()));
		assertThat("Comment line 4", comment, equalTo(analyzer.getComment()));

		comment = "; a line that is all comment";
		String lineNumber = "1234";
		sourceLine = lineNumber + " Some interesting code" + comment;

		analyzer.analyze(sourceLine);
		assertThat("Simple Comment  1", true, equalTo(analyzer.isLineActive()));
		assertThat("Simple Comment  2", false, equalTo(analyzer.isLineAllComment()));
		assertThat("Simple Comment  3", true, equalTo(analyzer.hasComment()));
		assertThat("Simple Comment  4", comment, equalTo(analyzer.getComment()));
		assertThat("Simple Comment  5", true, equalTo(analyzer.hasLineNumber()));
		assertThat("Simple Comment  6", lineNumber, equalTo(analyzer.getLineNumber()));

		comment = "; a line that is all comment";
		sourceLine = "Some interesting code ' with an enclosed ; 'in it" + comment;

		analyzer.analyze(sourceLine);
		assertThat(" Comment with ; in quotes 1", true, equalTo(analyzer.isLineActive()));
		assertThat(" Comment with ; in quotes  2", false, equalTo(analyzer.isLineAllComment()));
		assertThat(" Comment with ; in quotes  3", true, equalTo(analyzer.hasComment()));
		assertThat(" Comment with ; in quotes  4", comment, equalTo(analyzer.getComment()));

		comment = "";
		sourceLine = "Some interesting code ' with an enclosed ; 'in it" + comment;

		analyzer.analyze(sourceLine);
		assertThat("No Comment  1", true, equalTo(analyzer.isLineActive()));
		assertThat("No Comment  2", false, equalTo(analyzer.isLineAllComment()));
		assertThat("No Comment  3", false, equalTo(analyzer.hasComment()));

	}// testComments

	@Test
	public void testLabels() {
		analyzer.analyze("; comment");
		assertThat("No Labels line 1", true, equalTo(analyzer.isLineActive()));
		assertThat("No Labels line 2", false, equalTo(analyzer.hasLabel()));

		String label = "@1234";
		String sourceLine = label + " Some interesting code";
		analyzer.analyze(sourceLine);
		assertThat("No Labels line 3", true, equalTo(analyzer.isLineActive()));
		assertThat("No Labels line 4", false, equalTo(analyzer.hasLabel()));

		label = "@1234:";
		sourceLine = label + " Some interesting code";
		analyzer.analyze(sourceLine);
		assertThat("Labels line 1", true, equalTo(analyzer.isLineActive()));
		assertThat("Labels line 2", true, equalTo(analyzer.hasLabel()));
		assertThat("Labels line 3", label, equalTo(analyzer.getLabel()));

		String lineNumber = "1234";
		label = "Label1:";
		String comment = "; a comment";
		sourceLine = lineNumber +" " + label + " Some interesting code" + comment;
		analyzer.analyze(sourceLine);
		assertThat("Full line 1", true, equalTo(analyzer.isLineActive()));
		assertThat("Full line 2", true, equalTo(analyzer.hasLineNumber()));
		assertThat("Full line 2", true, equalTo(analyzer.hasLabel()));
		assertThat("Full line 2", true, equalTo(analyzer.hasComment()));
		assertThat("Full line 2", lineNumber, equalTo(analyzer.getLineNumber()));
		assertThat("Full line 2", label, equalTo(analyzer.getLabel()));
		assertThat("Full line 2", comment, equalTo(analyzer.getComment()));

	}// testLabels
	
		@Test
	public void testInstructions() {
		analyzer.analyze("; comment");
		assertThat("No Labels line 1", true, equalTo(analyzer.isLineActive()));
		assertThat("No Labels line 2", false, equalTo(analyzer.hasLabel()));

		String label = "@1234";
		String sourceLine = label + " Some interesting code";
		analyzer.analyze(sourceLine);
		assertThat("No Labels line 3", true, equalTo(analyzer.isLineActive()));
		assertThat("No Labels line 4", false, equalTo(analyzer.hasLabel()));

		label = "@1234:";
		sourceLine = label + " Some interesting code";
		analyzer.analyze(sourceLine);
		assertThat("Labels line 1", true, equalTo(analyzer.isLineActive()));
		assertThat("Labels line 2", true, equalTo(analyzer.hasLabel()));
		assertThat("Labels line 3", label, equalTo(analyzer.getLabel()));

		String lineNumber = "1234";
		label = "Label1:";
		String comment = "; a comment";
		String opCode = "ADC";
		String instruction = opCode + " A, (IX+3)";
		sourceLine = lineNumber +" " + label + instruction + comment;
		analyzer.analyze(sourceLine);
		assertThat("Full line 1", true, equalTo(analyzer.isLineActive()));
		assertThat("Full line 2", true, equalTo(analyzer.hasLineNumber()));
		assertThat("Full line 3", true, equalTo(analyzer.hasLabel()));
		assertThat("Full line 4", true, equalTo(analyzer.hasComment()));
		assertThat("Full line 5", lineNumber, equalTo(analyzer.getLineNumber()));
		assertThat("Full line 6", label, equalTo(analyzer.getLabel()));
		assertThat("Full line 7", comment, equalTo(analyzer.getComment()));
		
		assertThat("Full line 8", true, equalTo(analyzer.hasInstruction()));
		assertThat("Full line 9", opCode, equalTo(analyzer.getInstruction()));
		
	}// testOpCodes
		
		@Test
		public void testSubOpCodes() {
			String lineNumber = "1234";
			String label = "Label1:";
			String comment = "; a comment";
			String opCode = "ADC";
			String arguments = " A, (IX+8)";
			String sourceLine = lineNumber +" " + label +" " + opCode + " " + arguments  +" " + comment;
			analyzer.analyze(sourceLine);
			assertThat("Full line 1", true, equalTo(analyzer.hasLineNumber()));
			assertThat("Full line 2", true, equalTo(analyzer.hasLabel()));
			assertThat("Full line 3", true, equalTo(analyzer.hasComment()));

			
			assertThat("Full line 4", true, equalTo(analyzer.hasInstruction()));
			assertThat("Full line 5", opCode, equalTo(analyzer.getInstruction()));
			
			sourceLine = opCode + " " + arguments;
			analyzer.analyze(sourceLine);
			assertThat("code & args 1", true, equalTo(analyzer.hasInstruction()));
			assertThat("code & args 2", opCode, equalTo(analyzer.getInstruction()));	
			assertThat("sub 1","ADC_1",equalTo(analyzer.getSubOpCode()));
			
			arguments = " A, B)";
			sourceLine = opCode + " " + arguments;
			analyzer.analyze(sourceLine);
			assertThat("code & B 1", true, equalTo(analyzer.hasInstruction()));
			assertThat("code & B 2", opCode, equalTo(analyzer.getInstruction()));	
			assertThat("sub B","ADC_2",equalTo(analyzer.getSubOpCode()));
			
			arguments = " A, (HL)";
			sourceLine = opCode + " " + arguments;
			analyzer.analyze(sourceLine);
			assertThat("code & (HL) 1", true, equalTo(analyzer.hasInstruction()));
			assertThat("code & (HL) 2", opCode, equalTo(analyzer.getInstruction()));	
			assertThat("sub (HL)","ADC_2",equalTo(analyzer.getSubOpCode()));

			arguments = " A, 1+2";
			sourceLine = opCode + " " + arguments;
			analyzer.analyze(sourceLine);
			assertThat("code & 1+2 1", true, equalTo(analyzer.hasInstruction()));
			assertThat("code & 1+2 2", opCode, equalTo(analyzer.getInstruction()));	
			assertThat("sub 1+2","ADC_3",equalTo(analyzer.getSubOpCode()));

			arguments = " A";
			sourceLine = opCode + " " + arguments;
			analyzer.analyze(sourceLine);
			assertThat("code & 1+2 1", true, equalTo(analyzer.hasInstruction()));
			assertThat("code & 1+2 2", opCode, equalTo(analyzer.getInstruction()));	
			assertThat("sub 1+2",InstructionSet.BAD_OPCODE,equalTo(analyzer.getSubOpCode()));
	
		}//testSubOpCodes
		

}// class TestSourceLineAnalyzer
/*
 * import static org.junit.Assert.*; import static org.hamcrest.CoreMatchers.equalTo;
 */

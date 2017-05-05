package assembler;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

public class TestSourceLineAnalyzer_1 {
	SourceLineAnalyzer analyzer;
	String argument1;
	String argument2;
	String source;
	SourceLineParts slp;

	@Before
	public void setUp() throws Exception {
		analyzer = new SourceLineAnalyzer();

	}//

	@Test
	public void testLineNumbers() {
		slp = analyzer.analyze("");
		assertThat("Null line", false, equalTo(slp.isLineActive()));

		 String lineNumber = "1234";
		 String sourceLine = lineNumber + " ";
		slp = analyzer.analyze(sourceLine);
		 assertThat("Line Number 1", true, equalTo(slp.isLineActive()));
		 assertThat("Line Number 2", false, equalTo(slp.hasComment()));
		 assertThat("Line Number 4", lineNumber, equalTo(slp.getLineNumberStr()));
		
		 lineNumber = "1234";
		 sourceLine = lineNumber + "XX";
		 slp =analyzer.analyze(sourceLine);
		 assertThat("Line Number 1", true, equalTo(slp.isLineActive()));

	}// testLineNumbers

	 @Test
	 public void testComments() {
	
	 String comment = "; a line that is all comment";
	 String sourceLine = comment;
	 slp = analyzer.analyze(sourceLine);
	 assertThat("Comment line 1", true, equalTo(slp.isLineActive()));
	 assertThat("Comment line 2", true, equalTo(slp.isLineAllComment()));
	 assertThat("Comment line 3", true, equalTo(slp.hasComment()));
	 assertThat("Comment line 4", comment, equalTo(slp.getComment()));
	
	 comment = "; a line that is all comment";
	 String lineNumber = "1234";
	 sourceLine = lineNumber + " Some interesting code" + comment;
	
	 slp = analyzer.analyze(sourceLine);
	 assertThat("Simple Comment 1", true, equalTo(slp.isLineActive()));
	 assertThat("Simple Comment 2", false, equalTo(slp.isLineAllComment()));
	 assertThat("Simple Comment 3", true, equalTo(slp.hasComment()));
	 assertThat("Simple Comment 4", comment, equalTo(slp.getComment()));
	 assertThat("Simple Comment 6", lineNumber, equalTo(slp.getLineNumberStr()));
	
	 comment = "; a line that is all comment";
	 sourceLine = "Some interesting code ' with an enclosed ; 'in it" + comment;
	
	 slp = analyzer.analyze(sourceLine);
	 assertThat(" Comment with ; in quotes 1", true, equalTo(slp.isLineActive()));
	 assertThat(" Comment with ; in quotes 2", false, equalTo(slp.isLineAllComment()));
	 assertThat(" Comment with ; in quotes 3", true, equalTo(slp.hasComment()));
	 assertThat(" Comment with ; in quotes 4", comment, equalTo(slp.getComment()));
	
	 comment = "";
	 sourceLine = "Some interesting code ' with an enclosed ; 'in it" + comment;
	
	 slp = analyzer.analyze(sourceLine);
	 assertThat("No Comment 1", true, equalTo(slp.isLineActive()));
	 assertThat("No Comment 2", false, equalTo(slp.isLineAllComment()));
	 assertThat("No Comment 3", false, equalTo(slp.hasComment()));
	
	 }// testComments
	
	 @Test
	 public void testLabels() {
		 slp = analyzer.analyze("; comment");
	 assertThat("No Labels line 1", true, equalTo(slp.isLineActive()));
	 assertThat("No Labels line 2", false, equalTo(slp.hasLabel()));
	
	 String label = "@1234";
	 String sourceLine = label + " Some interesting code";
	 slp = analyzer.analyze(sourceLine);
	 assertThat("No Labels line 3", true, equalTo(slp.isLineActive()));
	 assertThat("No Labels line 4", false, equalTo(slp.hasLabel()));
	
	 label = "@1234:";
	 sourceLine = label + " Some interesting code";
	 slp = analyzer.analyze(sourceLine);
	 assertThat("Labels line 1", true, equalTo(slp.isLineActive()));
	 assertThat("Labels line 2", true, equalTo(slp.hasLabel()));
	 assertThat("Labels line 3", label, equalTo(slp.getLabel()));
	
	 String lineNumber = "1234";
	 label = "Label1:";
	 String comment = "; a comment";
	 sourceLine = lineNumber + " " + label + " Some interesting code" + comment;
	 slp = analyzer.analyze(sourceLine);
	 assertThat("Full line 1", true, equalTo(slp.isLineActive()));
	 assertThat("Full line 2", true, equalTo(slp.hasLabel()));
	 assertThat("Full line 2", true, equalTo(slp.hasComment()));
	 assertThat("Full line 2", lineNumber, equalTo(slp.getLineNumberStr()));
	 assertThat("Full line 2", label, equalTo(slp.getLabel()));
	 assertThat("Full line 2", comment, equalTo(slp.getComment()));
	
	 }// testLabels
	
	 @Test
	 public void testInstructions() {
	 slp = analyzer.analyze("; comment");
	 assertThat("No Labels line 1", true, equalTo(slp.isLineActive()));
	 assertThat("No Labels line 2", false, equalTo(slp.hasLabel()));
	
	 String label = "@1234";
	 String sourceLine = label + " Some interesting code";
	 slp = analyzer.analyze(sourceLine);
	 assertThat("No Labels line 3", true, equalTo(slp.isLineActive()));
	 assertThat("No Labels line 4", false, equalTo(slp.hasLabel()));
	
	 label = "@1234:";
	 sourceLine = label + " Some interesting code";
	 slp = analyzer.analyze(sourceLine);
	 assertThat("Labels line 1", true, equalTo(slp.isLineActive()));
	 assertThat("Labels line 2", true, equalTo(slp.hasLabel()));
	 assertThat("Labels line 3", label, equalTo(slp.getLabel()));
	
	 String lineNumber = "1234";
	 label = "Label1:";
	 String comment = "; a comment";
	 String opCode = "ADC";
	 String instruction = opCode + " A, (IX+3)";
	 sourceLine = lineNumber + " " + label + instruction + comment;
	 slp = analyzer.analyze(sourceLine);
	 assertThat("Full line 1", true, equalTo(slp.isLineActive()));
	 assertThat("Full line 3", true, equalTo(slp.hasLabel()));
	 assertThat("Full line 4", true, equalTo(slp.hasComment()));
	 assertThat("Full line 5", lineNumber, equalTo(slp.getLineNumberStr()));
	 assertThat("Full line 6", label, equalTo(slp.getLabel()));
	 assertThat("Full line 7", comment, equalTo(slp.getComment()));
	
	 assertThat("Full line 8", true, equalTo(slp.hasInstruction()));
	 assertThat("Full line 9", opCode, equalTo(slp.getInstruction()));
	
	 }// testOpCodes
	
	 @Test
	 public void testSubOpCodes() {
	 String lineNumber = "1234";
	 String label = "Label1:";
	 String comment = "; a comment";
	 String opCode = "ADC";
	 String arguments = " A, (IX+8)";
	 String sourceLine = lineNumber + " " + label + " " + opCode + " " + arguments + " " + comment;
	 slp = analyzer.analyze(sourceLine);
	 assertThat("Full line 2", true, equalTo(slp.hasLabel()));
	 assertThat("Full line 3", true, equalTo(slp.hasComment()));
	
	 assertThat("Full line 4", true, equalTo(slp.hasInstruction()));
	 assertThat("Full line 5", opCode, equalTo(slp.getInstruction()));
	
	 sourceLine = opCode + " " + arguments;
	 slp = analyzer.analyze(sourceLine);
	 assertThat("code & args 1", true, equalTo(slp.hasInstruction()));
	 assertThat("code & args 2", opCode, equalTo(slp.getInstruction()));
	 assertThat("sub 1", "ADC_1", equalTo(slp.getSubOpCode()));
	
	 arguments = " A, B)";
	 sourceLine = opCode + " " + arguments;
	 slp = analyzer.analyze(sourceLine);
	 assertThat("code & B 1", true, equalTo(slp.hasInstruction()));
	 assertThat("code & B 2", opCode, equalTo(slp.getInstruction()));
	 assertThat("sub B", "ADC_2", equalTo(slp.getSubOpCode()));
	
	 arguments = " A, (HL)";
	 sourceLine = opCode + " " + arguments;
	 slp = analyzer.analyze(sourceLine);
	 assertThat("code & (HL) 1", true, equalTo(slp.hasInstruction()));
	 assertThat("code & (HL) 2", opCode, equalTo(slp.getInstruction()));
	 assertThat("sub (HL)", "ADC_2", equalTo(slp.getSubOpCode()));
	
	 arguments = " A, 1+2";
	 sourceLine = opCode + " " + arguments;
	 slp = analyzer.analyze(sourceLine);
	 assertThat("code & 1+2 1", true, equalTo(slp.hasInstruction()));
	 assertThat("code & 1+2 2", opCode, equalTo(slp.getInstruction()));
	 assertThat("sub 1+2", "ADC_3", equalTo(slp.getSubOpCode()));
	
	 arguments = " A";
	 sourceLine = opCode + " " + arguments;
	 slp = analyzer.analyze(sourceLine);
	
	 }// testSubOpCodes
	
	 @Test
	 public void testArguments() {
	
	 String sourceLine = makeSourceLine("1234 label1: ADC "," ; a comment","A","expression");
	 slp = analyzer.analyze(sourceLine);
	 assertThat("2 args 2", true, equalTo(slp.hasLabel()));
	 assertThat("2 args 3", true, equalTo(slp.hasComment()));
	 assertThat("2 args 4", true, equalTo(slp.hasInstruction()));
	
	 assertThat("2 args 5", true, equalTo(slp.hasArguments()));
	 assertThat("2 argse 6", 2, equalTo(slp.getArgumentCount()));
	 assertThat("2 args 7", argument1, equalTo(slp.getArgument1()));
	 assertThat("2 args 8", argument2, equalTo(slp.getArgument2()));
	
	 sourceLine = makeSourceLine("1234 label1: ADC "," ; a comment","A");
	 slp = analyzer.analyze(sourceLine);
	 assertThat("1 arg 2", true, equalTo(slp.hasLabel()));
	 assertThat("1 arg 3", true, equalTo(slp.hasComment()));
	 assertThat("1 arg 4", true, equalTo(slp.hasInstruction()));
	
	 assertThat("1 arg 5", true, equalTo(slp.hasArguments()));
	 assertThat("1 arg 6", 1, equalTo(slp.getArgumentCount()));
	 assertThat("1 arg 7", argument1, equalTo(slp.getArgument1()));
	 assertThat("1 arg 8", argument2, equalTo(slp.getArgument2()));
	
	 sourceLine = makeSourceLine("1234 label1: ADC "," ; a comment");
	 slp = analyzer.analyze(sourceLine);
	 assertThat("0 arg 2", true, equalTo(slp.hasLabel()));
	 assertThat("0 arg 3", true, equalTo(slp.hasComment()));
	 assertThat("0 arg 4", true, equalTo(slp.hasInstruction()));
	
	 assertThat("0 arg 5", false, equalTo(slp.hasArguments()));
	 assertThat("0 arg 6", 0, equalTo(slp.getArgumentCount()));
	 assertThat("0 arg 7", argument1, equalTo(slp.getArgument1()));
	 assertThat("0 arg 8", argument2, equalTo(slp.getArgument2()));
	
	 }// testArguments

	// -----------------------------------------------------------------------------------------

	public String makeSourceLine(String part1, String comment, String arg1, String arg2) {
		argument1 = arg1;
		argument2 = arg2;
		source = part1 + " " + arg1 + ", " + arg2 + " " + comment;

		return source;
	}// makeSourceLine

	public String makeSourceLine(String part1, String comment, String arg1) {
		argument1 = arg1;
		argument2 = null;
		source = part1 + " " + arg1 + " " + comment;

		return source;
	}// makeSourceLine

	public String makeSourceLine(String part1, String comment) {
		argument1 = null;
		argument2 = null;
		source = part1 + " " + comment;

		return source;
	}// makeSourceLine

}// class TestSourceLineAnalyzer

/*
 * import static org.junit.Assert.*; import static org.hamcrest.CoreMatchers.equalTo;
 */

package assembler;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

public class TestSourceLineAnalyzer_2 {
	SourceLineAnalyzer analyzer;
	String lineNumber;
	String name;
	String label;
	String opCode;
	String operands;
	String comment;

	@Before
	public void setUp() throws Exception {
		analyzer = new SourceLineAnalyzer();
		assertThat("setUp", true, equalTo(true));
	}// setUp

	@Test
	public void testSimpleStuff() {

		String sourceLine = makeSourceLine("LineNumber", "name", "opCode", "operands", "comments");
		analyzer.analyze(sourceLine);
//		System.out.println(sourceLine);

		sourceLine = makeSourceLine("1234", "", "", "", ";comments");
		analyzer.analyze(sourceLine);
		assertThat("Line Number  1", true, equalTo(analyzer.isLineActive()));
		assertThat("Line Number 2", true, equalTo(analyzer.hasComment()));
		assertThat("Line Number 3", true, equalTo(analyzer.hasLineNumber()));
		assertThat("Line Number 4", lineNumber, equalTo(analyzer.getLineNumberStr()));
		assertThat("Line Number 5", false, equalTo(analyzer.hasLabel()));
		assertThat("Line Number 6", false, equalTo(analyzer.hasInstruction()));
		assertThat("Line Number 7", false, equalTo(analyzer.hasDirective()));
		assertThat("Line Number 8", false, equalTo(analyzer.hasName()));

		sourceLine = makeSourceLine("1234", "Label:", "ADC", "A,123", ";comments");
		analyzer.analyze(sourceLine);
		assertThat("Label  1", true, equalTo(analyzer.isLineActive()));
		assertThat("Label  2", true, equalTo(analyzer.hasLabel()));
		assertThat("Label  3", label, equalTo(analyzer.getLabel()));
		assertThat("Label  4", opCode, equalTo(analyzer.getInstruction()));
		assertThat("Label  5", comment, equalTo(analyzer.getComment()));

	}// testSimpleStuff

	@Test
	public void testNoLabelsNoNames() {
		String sourceLine = makeSourceLine("LineNumber", "name", "opCode", "operands", "comments");
		analyzer.analyze(sourceLine);
//		System.out.println(sourceLine);

		sourceLine = makeSourceLine("1234", "", "EQU", "", ";comments");
		analyzer.analyze(sourceLine);
		assertThat("Directive  1", true, equalTo(analyzer.isLineActive()));

		assertThat("Directive  2", false, equalTo(analyzer.hasLabel()));
		assertThat("Directive  3", label, equalTo(analyzer.getLabel()));

		assertThat("Directive  4", false, equalTo(analyzer.hasName()));
		assertThat("Directive  5", name, equalTo(analyzer.getName()));

	}// testDirective

	@Test
	public void testLabelsNoNames() {
		String sourceLine = makeSourceLine("LineNumber", "Label:", "opCode", "operands", "comments");
		analyzer.analyze(sourceLine);
//		System.out.println(sourceLine);

		sourceLine = makeSourceLine("1234", "Label:", "EQU", "", ";comments");
		System.out.println(sourceLine);
		analyzer.analyze(sourceLine);
		assertThat("LabelsNoNames  1", true, equalTo(analyzer.isLineActive()));

		assertThat("LabelsNoNames  2", true, equalTo(analyzer.hasLabel()));
		assertThat("LabelsNoNames  3", label, equalTo(analyzer.getLabel()));

		assertThat("LabelsNoNames  4", false, equalTo(analyzer.hasName()));
		assertThat("LabelsNoNames  5", name, equalTo(analyzer.getName()));

	}// testDirective

	@Test
	public void testNoLabelsNames() {
		String sourceLine = makeSourceLine("LineNumber", "Name1", "opCode", "operands", "comments");
		analyzer.analyze(sourceLine);
//		System.out.println(sourceLine);

		sourceLine = makeSourceLine("1234", "Name1", "EQU", "", ";comments");
		System.out.println(sourceLine);
		analyzer.analyze(sourceLine);
		assertThat("testNoLabelsNames  1", true, equalTo(analyzer.isLineActive()));

		assertThat("testNoLabelsNames  2", false, equalTo(analyzer.hasLabel()));
		assertThat("testNoLabelsNames  3", label, equalTo(analyzer.getLabel()));

		assertThat("testNoLabelsNames  4", true, equalTo(analyzer.hasName()));
		assertThat("testNoLabelsNames  5", name, equalTo(analyzer.getName()));

	}// testDirective

	// ------------------------------------------------

	private static final String EMPTY_STRING = "";
	private static final String SPACE = " ";
	private static final String TAB2 = "		";

	public String makeSourceLine(String lineNumber, String name, String opCode, String operands, String comments) {
		StringBuilder sb = new StringBuilder();
		if (lineNumber != EMPTY_STRING) {
			sb.append(lineNumber);
			sb.append(SPACE);

		} // line number
		this.lineNumber = lineNumber;

		if (name != EMPTY_STRING) {
			sb.append(name);
			sb.append(SPACE);
			if (name.endsWith(":")) {
				this.label = name;
				this.name = EMPTY_STRING;
			} else {
				this.label = EMPTY_STRING;
				this.name = name;
			} // name v label
		}else{
			this.label = EMPTY_STRING;
			this.name = EMPTY_STRING;
		}//name

		if (opCode != EMPTY_STRING) {
			sb.append(opCode);
			sb.append(SPACE);
		} // opCode
		this.opCode = opCode;

		if (operands != EMPTY_STRING) {
			sb.append(operands);
			sb.append(SPACE);
		} // operands
		this.operands = operands;

		if (comments != EMPTY_STRING) {
			sb.append(comments);
			sb.append(SPACE);
		} // comments
		this.comment = comments;

		return sb.toString();
	}// makeSourceLine

}// class TestSourceLineAnalyzer_2

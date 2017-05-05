package assembler;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

public class TestSourceLineAnalyzer_3 {
	private SourceLineAnalyzer analyzer;
	SourceLineParts slp;

	@Before
	public void setUp() throws Exception {
		analyzer = new SourceLineAnalyzer();
	}//setUp

	@Test
	public void testOpCodeSizeA() {
		

		slp = analyzer.analyze("1234  ADC A,(IX+3)  ");
		assertThat("ADC_1", 3, equalTo(slp.getOpCodeSize()));

		slp = analyzer.analyze("  ADC A,B  ");
		assertThat("ADC_2", 1, equalTo(slp.getOpCodeSize()));

		slp = analyzer.analyze("label:  ADC A,expression  ");
		assertThat("ADC_3", 2, equalTo(slp.getOpCodeSize()));

		slp = analyzer.analyze("  ADC HL,SP  ; comment");
		assertThat("ADC_4", 2, equalTo(slp.getOpCodeSize()));

		
	}//testOpCodeSize

	
//---------------------------------------------------------------------

}//class TestSourceLineAnalyzer_2 

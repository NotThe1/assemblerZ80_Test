package parser;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

public class testParser{

	@Before
	public void setUp() throws Exception {

	}

	@Test
	public void testSimpler() {
		Parser parser = new Parser();
		Tokenizer tokenizer = new Tokenizer();
		
		
		
		try {

			tokenizer.tokenize("3*2^4 + sqrt(1+3)");
			ExpressionNode expression = parser.parse(tokenizer.getTokens());	
			assertThat("Simple", 50,equalTo(expression.getValue()));

			tokenizer.tokenize("11110000B + 745O + 123D +0AB3H");
			 expression = parser.parse(tokenizer.getTokens());	
			assertThat("Simple", 3587,equalTo(expression.getValue()));

			
			
//			System.out.println("The value of the expression is " + expression.getValue());
			
		} catch (ParserException e) {
			System.out.println(e.getMessage());
		} catch (EvaluationException e) {
			System.out.println(e.getMessage());
		}//try
		
	}//testSimpler

}// class testParser

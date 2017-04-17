package parser;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

public class testTokenizer {
	public Tokenizer tokenizer;

	@Before
	public void setUp() throws Exception {
		
	}
	
	@Test
	public void stringTest(){
		tokenizer = new Tokenizer();
		String originalText = "'left' + \"middle\" - 'right'";
		tokenizer.tokenize(originalText);
		Token[] targetTokens  = new Token[]{
				new Token(TokenType.STRING,"'left'"),
				new Token(TokenType.PLUS_MINUS,"+"),
				new Token(TokenType.STRING,"\"middle\""),
				new Token(TokenType.PLUS_MINUS,"-"),
				new Token(TokenType.STRING,"'right'")			
		};
		int i = 0;
		for (Token token : tokenizer.getTokens()) {	
//			System.out.println("" + token.tokenType + " " + token.sequence);
			assertThat("string " + i , token.tokenType, equalTo(targetTokens[i].tokenType));
			assertThat("string " + i, token.sequence, equalTo(targetTokens[i++].sequence));

		}//for
	}//stringTest
	
	@Test
	public void parenthesesTest(){
		tokenizer = new Tokenizer();
		String originalText = "((var0+(var1+var2) - var3)";
		tokenizer.tokenize(originalText);
		Token[] targetTokens  = new Token[]{
				new Token(TokenType.OPEN_PARENTHESES,"("),
				new Token(TokenType.OPEN_PARENTHESES,"("),
				new Token(TokenType.VARIABLE,"var0"),
				new Token(TokenType.PLUS_MINUS,"+"),
				new Token(TokenType.OPEN_PARENTHESES,"("),
				new Token(TokenType.VARIABLE,"var1"),
				new Token(TokenType.PLUS_MINUS,"+"),
				new Token(TokenType.VARIABLE,"var2"),
				new Token(TokenType.CLOSE_PARENTHESES,")"),
				new Token(TokenType.PLUS_MINUS,"-"),
				new Token(TokenType.VARIABLE,"var3"),
				new Token(TokenType.CLOSE_PARENTHESES,")"),		
		};
		int i = 0;
		for (Token token : tokenizer.getTokens()) {	
//			System.out.println("" + token.tokenType + " " + token.sequence);
			assertThat("parentheses " + i, token.tokenType, equalTo(targetTokens[i].tokenType));
			assertThat("parentheses " + i, token.sequence, equalTo(targetTokens[i++].sequence));

		}//for
	}//parenthesesTest
	
	@Test
	public void multTest(){
		tokenizer = new Tokenizer();
		String originalText = "var0*(var1/var2)^var3)";
		tokenizer.tokenize(originalText);
		Token[] targetTokens  = new Token[]{
				new Token(TokenType.VARIABLE,"var0"),
				new Token(TokenType.MULT_DIV,"*"),
				new Token(TokenType.OPEN_PARENTHESES,"("),
				new Token(TokenType.VARIABLE,"var1"),
				new Token(TokenType.MULT_DIV,"/"),
				new Token(TokenType.VARIABLE,"var2"),
				new Token(TokenType.CLOSE_PARENTHESES,")"),
				new Token(TokenType.RAISED,"^"),
				new Token(TokenType.VARIABLE,"var3"),
				new Token(TokenType.CLOSE_PARENTHESES,")"),		
		};
		int i = 0;
		for (Token token : tokenizer.getTokens()) {	
//			System.out.println("" + token.tokenType + " " + token.sequence);
			assertThat("multi " + i, token.tokenType, equalTo(targetTokens[i].tokenType));
			assertThat("multi " + i, token.sequence, equalTo(targetTokens[i++].sequence));

		}//for
	}//multTest
	
	
	@Test
	public void logicTest(){
		tokenizer = new Tokenizer();
		String originalText = "$ AND (var1 OR var2)";
		tokenizer.tokenize(originalText);
		Token[] targetTokens  = new Token[]{
				new Token(TokenType.VARIABLE,"$"),
				new Token(TokenType.LOGIC,"AND"),
				new Token(TokenType.OPEN_PARENTHESES,"("),
				new Token(TokenType.VARIABLE,"var1"),
				new Token(TokenType.LOGIC,"OR"),
				new Token(TokenType.VARIABLE,"var2"),
				new Token(TokenType.CLOSE_PARENTHESES,")")	
		};
		int i = 0;
		for (Token token : tokenizer.getTokens()) {	
//			System.out.println("" + token.tokenType + " " + token.sequence);
			assertThat("logic " + i, token.tokenType, equalTo(targetTokens[i].tokenType));
			assertThat("logic " + i, token.sequence, equalTo(targetTokens[i++].sequence));

		}//for
	}//logicTest
	
	
	@Test
	public void numberTest(){
		tokenizer = new Tokenizer();
		String originalText = "01101010B + 377O + 1234 + 1234D + 100H + 0FFH";
		tokenizer.tokenize(originalText);
		Token[] targetTokens  = new Token[]{
				new Token(TokenType.BINARY,"01101010B"),
				new Token(TokenType.PLUS_MINUS,"+"),
				new Token(TokenType.OCTAL,"377O"),
				new Token(TokenType.PLUS_MINUS,"+"),
				new Token(TokenType.DECIMAL,"1234"),
				new Token(TokenType.PLUS_MINUS,"+"),
				new Token(TokenType.DECIMAL,"1234D"),
				new Token(TokenType.PLUS_MINUS,"+"),
				new Token(TokenType.HEX,"100H"),
				new Token(TokenType.PLUS_MINUS,"+"),
				new Token(TokenType.HEX,"0FFH")	
		};
		int i = 0;
		for (Token token : tokenizer.getTokens()) {	
//			System.out.println("" + token.tokenType + " " + token.sequence);
			assertThat("logic " + i, token.tokenType, equalTo(targetTokens[i].tokenType));
			assertThat("logic " + i, token.sequence, equalTo(targetTokens[i++].sequence));

		}//for
	}//numberTest
	
	
	
	
	
	@Test
	public void modelTest() {
		tokenizer = new Tokenizer();
		String originalText = " sin(x) * (1 + var_12) ";
		tokenizer.tokenize(originalText);
		Token[] targetTokens  = new Token[]{
				new Token(TokenType.FUNCTION,"sin"),
				new Token(TokenType.OPEN_PARENTHESES,"("),
				new Token(TokenType.VARIABLE,"x"),
				new Token(TokenType.CLOSE_PARENTHESES,")"),
				new Token(TokenType.MULT_DIV,"*"),
				new Token(TokenType.OPEN_PARENTHESES,"("),
				new Token(TokenType.DECIMAL,"1"),
				new Token(TokenType.PLUS_MINUS,"+"),
				new Token(TokenType.VARIABLE,"var_12"),
				new Token(TokenType.CLOSE_PARENTHESES,")")
				
		};
		int i = 0;
		for (Token token : tokenizer.getTokens()) {

			
//			System.out.println("" + token.tokenType + " " + token.sequence);
			assertThat("model " + i, token.tokenType, equalTo(targetTokens[i].tokenType));
			assertThat("model " + i, token.sequence, equalTo(targetTokens[i++].sequence));
		}//for
		
	}//modelTest


}//class testTokenizer
//import static org.hamcrest.CoreMatchers.equalTo;
//import static org.junit.Assert.assertThat;

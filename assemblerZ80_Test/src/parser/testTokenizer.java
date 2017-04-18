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
		String originalText = "'left' + \"middle\" - 'right'";
		
		tokenizer = new Tokenizer();
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
		String originalText = "((var0+(var1+var2) - var3)";
		
		tokenizer = new Tokenizer();
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
		String originalText = "var0*(var1/var2)^var3)";
		
		tokenizer = new Tokenizer();
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
		String originalText = "$ AND (var1 OR var2) and (var3 or var4)";
		
		tokenizer = new Tokenizer();
		tokenizer.tokenize(originalText);
		Token[] targetTokens  = new Token[]{
				new Token(TokenType.VARIABLE,"$"),
				new Token(TokenType.LOGIC,"AND"),
				new Token(TokenType.OPEN_PARENTHESES,"("),
				new Token(TokenType.VARIABLE,"var1"),
				new Token(TokenType.LOGIC,"OR"),
				new Token(TokenType.VARIABLE,"var2"),
				new Token(TokenType.CLOSE_PARENTHESES,")"),
				new Token(TokenType.LOGIC,"and"),
				new Token(TokenType.OPEN_PARENTHESES,"("),
				new Token(TokenType.VARIABLE,"var3"),
				new Token(TokenType.LOGIC,"or"),
				new Token(TokenType.VARIABLE,"var4"),
				new Token(TokenType.CLOSE_PARENTHESES,")"),
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
		String originalText = "01101010B + 377O + 377Q + 1234 + 1234D + 100H + 0FFH";
		
		tokenizer = new Tokenizer();
		tokenizer.tokenize(originalText);
		Token[] targetTokens  = new Token[]{
				new Token(TokenType.BINARY,"01101010B"),
				new Token(TokenType.PLUS_MINUS,"+"),
				new Token(TokenType.OCTAL,"377O"),
				new Token(TokenType.PLUS_MINUS,"+"),
				new Token(TokenType.OCTAL,"377Q"),
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
		
 originalText = "01101010b + 377o + 377q + 1234 + 1234d + 100h + 0ffh";
		
		tokenizer = new Tokenizer();
		tokenizer.tokenize(originalText);
		 targetTokens  = new Token[]{
				new Token(TokenType.BINARY,"01101010b"),
				new Token(TokenType.PLUS_MINUS,"+"),
				new Token(TokenType.OCTAL,"377o"),
				new Token(TokenType.PLUS_MINUS,"+"),
				new Token(TokenType.OCTAL,"377q"),
				new Token(TokenType.PLUS_MINUS,"+"),
				new Token(TokenType.DECIMAL,"1234"),
				new Token(TokenType.PLUS_MINUS,"+"),
				new Token(TokenType.DECIMAL,"1234d"),
				new Token(TokenType.PLUS_MINUS,"+"),
				new Token(TokenType.HEX,"100h"),
				new Token(TokenType.PLUS_MINUS,"+"),
				new Token(TokenType.HEX,"0ffh")			
		};
		 i = 0;
		for (Token token : tokenizer.getTokens()) {	
//			System.out.println("" + token.tokenType + " " + token.sequence);
			assertThat("logic " + i, token.tokenType, equalTo(targetTokens[i].tokenType));
			assertThat("logic " + i, token.sequence, equalTo(targetTokens[i++].sequence));

		}//for
		
		
		
	}//numberTest
	
	@Test
	public void functionTest(){
		String originalText = "sqrt(sin  exp ln+cos)";
		
		tokenizer = new Tokenizer();
		tokenizer.tokenize(originalText);
		Token[] targetTokens  = new Token[]{
				new Token(TokenType.FUNCTION,"sqrt"),
				
				new Token(TokenType.OPEN_PARENTHESES,"("),
				new Token(TokenType.FUNCTION,"sin"),
				new Token(TokenType.FUNCTION,"exp"),
				new Token(TokenType.FUNCTION,"ln"),
				new Token(TokenType.PLUS_MINUS,"+"),
				new Token(TokenType.FUNCTION,"cos"),	
				new Token(TokenType.CLOSE_PARENTHESES,")")
				
		};
		int i = 0;
		for (Token token : tokenizer.getTokens()) {	
//			System.out.println("" + token.tokenType + " " + token.sequence);
			assertThat("logic " + i, token.tokenType, equalTo(targetTokens[i].tokenType));
			assertThat("logic " + i, token.sequence, equalTo(targetTokens[i++].sequence));
		}//for
		
		
			 originalText = "SQRT(SIN  EXP LN+COS)";
			
			tokenizer = new Tokenizer();
			tokenizer.tokenize(originalText);
			targetTokens  = new Token[]{
					new Token(TokenType.FUNCTION,"SQRT"),
					
					new Token(TokenType.OPEN_PARENTHESES,"("),
					new Token(TokenType.FUNCTION,"SIN"),
					new Token(TokenType.FUNCTION,"EXP"),
					new Token(TokenType.FUNCTION,"LN"),
					new Token(TokenType.PLUS_MINUS,"+"),
					new Token(TokenType.FUNCTION,"COS"),	
					new Token(TokenType.CLOSE_PARENTHESES,")")
					
			};
			 i = 0;
			for (Token token1 : tokenizer.getTokens()) {	
//				System.out.println("" + token1.tokenType + " " + token1.sequence);
				assertThat("logic " + i, token1.tokenType, equalTo(targetTokens[i].tokenType));
				assertThat("logic " + i, token1.sequence, equalTo(targetTokens[i++].sequence));

		}//for
	}//numberTest
	
	
	
	
	
	@Test
	public void modelTest() {
		String originalText = " sin(x) * (1 + var_12) ";
		
		tokenizer = new Tokenizer();
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

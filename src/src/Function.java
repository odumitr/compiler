import java.util.ArrayList;
import java.util.LinkedList;

public class Function {

	private String name;
	private Statement statement;
	
	public Function(LinkedList<String> tokens) {
		
//		TODO: old implementation; keep it until done testing new version
//		for(int i=0; i<tokens.size(); i++) {			
//			if((i == 0 && !tokens.get(i).equals(Lexer.INT_KEYWORD)) ||
//			(i == 1 && !tokens.get(i).equals(Lexer.IDENTIFIER_MAIN)) ||
//			(i == 2 && !tokens.get(i).equals(Lexer.OPEN_PARANTH)) ||
//			(i == 3 && !tokens.get(i).equals(Lexer.CLOSE_PARANTH)) ||
//			(i == 4 && !tokens.get(i).equals(Lexer.OPEN_BRACES)) ||
//			(i == tokens.size()-1 && !tokens.get(i).equals(Lexer.CLOSE_BRACES)) ||
//			(i == tokens.size()-2 && !tokens.get(i).equals(Lexer.SEMICOLON))) {				
//				throw new ParseException("Crashed while building main function");
//			}
////			TODO: modify i==5
//			else if(i == 5) {					
//				this.statement = StatementFactory.build_statement(tokens, i);				
//			}
//		}
		
		int index = 0;		
		while(tokens.size() > 1) {		
			String token = tokens.peek();
			if((index == 0 && !token.equals(Lexer.INT_KEYWORD)) ||
				(index == 1 && !token.equals(Lexer.IDENTIFIER_MAIN)) ||
				(index == 2 && !token.equals(Lexer.OPEN_PARANTH)) ||
				(index == 3 && !token.equals(Lexer.CLOSE_PARANTH)) ||
				(index == 4 && !token.equals(Lexer.OPEN_BRACES))) {				
					throw new ParseException("Crashed while building main function");
				}
			else if(index == 0 || index == 1 || index == 2 || index == 3 || index == 4) {
				tokens.poll();
				index+=1;
			}				
			else {				
				this.statement = StatementFactory.build_statement(tokens);
			}								
		}
		if(tokens.peek() == null || !tokens.peek().equals(Lexer.CLOSE_BRACES)) {
			throw new ParseException("Crashed while building main function");
		}
	}
	
	public String getName() {
		return name;
	}
	
	public Statement getStatement() {
		return statement;
	}	
}

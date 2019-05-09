import java.util.LinkedList;

public class ExpressionFactory {

//		protected static Expression build_expression(LinkedList<String> tokens) {
//			
//				boolean valid_integer = is_integer_valid(tokens.peek());
//				if(valid_integer) {
//						Expression expres =  new Constant(tokens);		
//						return expres;
//				}
//		//		unary operators
//				else if(tokens.peek().equals(Lexer.NEGATION) ||
//						tokens.peek().equals(Lexer.LOGICAL_NEG) ||
//						tokens.peek().equals(Lexer.COMPLEMENT)) {
//						Expression expres =  new UnaryOp(tokens);		
//						return expres;			
//				}						
//				throw new ParseException("Crashed while building expression: Invalid integer");
//				
////				check for binary operation
//				String token = tokens.get(1);
//				if(token.equals(Lexer.ADDITION) ||
//					token.equals(Lexer.DIVISION) ||
//					token.equals(Lexer.MULTIPLICATION))
//		}
//	
//		protected static Expression build_factor(LinkedList<String> tokens) {
//			
//				boolean valid_integer = is_integer_valid(tokens.peek());
//				if(valid_integer) {
//						Expression expres =  new Constant(tokens);		
//						return expres;
//				}
//			//		unary operators
//				else if(tokens.peek().equals(Lexer.NEGATION) ||
//						tokens.peek().equals(Lexer.LOGICAL_NEG) ||
//						tokens.peek().equals(Lexer.COMPLEMENT)) {
//						Expression expres =  new UnaryOp(tokens);		
//						return expres;			
//				}						
////				expression in  parenthesis
//				else if(tokens.peek().equals(Lexer.OPEN_PARANTH)) {
////						build expression
////						verify that the parenthesis was closed
//						String token = tokens.poll();
//						if(!token.equals(Lexer.CLOSE_PARANTH)) {
//								throw new ParseException("Crashed while building expression: Unclosed Parenthesis");
//						}
//				}
//				throw new ParseException("Crashed while building expression: Nothing matched");
//		}
//	
//	private static boolean is_integer_valid(String integer) {
//		
//		if(integer.substring(0, 1).equals("0") && integer.length() > 1) {
//			return false;
//		}
//		try {
//			Integer val = Integer.valueOf(integer);
//		}
//		catch(NumberFormatException e) {
//			return false;
//		}		
//		return true;
//	}
}

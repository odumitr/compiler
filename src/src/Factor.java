import java.util.LinkedList;

/**Will generate assembly code that expects the input data to be in register eax
 * and the output will be also in register eax
 * @author Oana
 *
 */
public class Factor implements AssemblyGenerator{
		
		private final Expression expression;
		private final UnaryOp unary_op;
		private final Constant constant;
			
		public Factor() {
			
			this.expression = null;
			this.unary_op = null;
			this.constant = null;
		}
		
		private Factor(Expression expression) {
			
			this.expression = expression;
			this.unary_op = null;
			this.constant = null;
		}
		
		private Factor(UnaryOp unary_op) {
			
			this.unary_op = unary_op;
			this.expression = null;
			this.constant = null;
		}		

		private Factor(Constant constant) {
			
			this.constant = constant;
			this.unary_op = null;
			this.expression = null;
		}

		public static Factor parse_factor(LinkedList<String> tokens) {
			
			String next_token = tokens.peek();
			if(next_token.equals(Lexer.OPEN_PARANTH)) {
//				generate expression
					tokens.poll();
					Expression new_exp = Expression.parse_expression(tokens);
//					check for closed paranthesis
					String last_token_check = tokens.poll();
					if(!last_token_check.equals(Lexer.CLOSE_PARANTH)) {
							throw new ParseException(
									"Unexpected token " + last_token_check+" : Expected ')'");
					}
				    return new Factor(new_exp);
			}	
//			check for unary op
			else if(next_token.equals(Lexer.NEGATION) ||
					next_token.equals(Lexer.LOGICAL_NEG) ||
					next_token.equals(Lexer.COMPLEMENT)) {
							UnaryOp unary_op =  new UnaryOp(tokens);		
							return new Factor(unary_op);
			}					
//			check for integer
			else if(is_integer_valid(next_token)) {
					Constant constant =  new Constant(tokens);		
					return new Factor(constant);				
			}
			throw new ParseException("Cannot build factor: Next token is "+next_token);			
		}
		
		public Expression getExpression() {
			return expression;
		}
		
		public UnaryOp getUnary_op() {
			return unary_op;
		}
		
		public Constant getConstant() {
			return constant;
		}
		
		private static boolean is_integer_valid(String integer) {
			
				if(integer.substring(0, 1).equals("0") && integer.length() > 1) {
					return false;
				}
				try {
					Integer val = Integer.valueOf(integer);
				}
				catch(NumberFormatException e) {
					return false;
				}		
				return true;
			}

		@Override
		public String generate_assembly() {
			
				if(this.constant != null) {
						return this.constant.generate_assembly();
				}
				else if(this.unary_op != null) {
						return this.unary_op.generate_assembly();
				}
				else if(this.expression != null) {
						return	this.expression.generate_assembly();
				}
				throw new ParseException(
						"Could not generate assembly in Factor : generate_assembly "
						+ "constant is"+this.constant+ " unary op is"+this.unary_op+ " expression is"+this.expression);
		}
}

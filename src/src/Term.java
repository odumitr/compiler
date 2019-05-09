import java.util.LinkedList;

/**Term will generate assembly code that expects the input data to be in register eax
 * and the output will be also in register eax
 * @author Oana
 *
 */
public class Term implements AssemblyGenerator{

		private final Factor factor;		
		
		public Term() {
			
			this.factor = null;			
		}
		
		private Term(Factor f) {
			
			this.factor = f;			
		}			
		
		public static Term parse_term(LinkedList<String> tokens) {
				    						    		
//				pops off some tokens
				Factor factor = Factor.parse_factor(tokens);				
				String next = tokens.peek();
				while(next.equals(Lexer.MULTIPLICATION) || next.equals(Lexer.DIVISION)) {
				        String operator = tokens.poll();
				        Factor next_factor = Factor.parse_factor(tokens);
				        factor = new BinaryOpFactor(operator, factor, next_factor);
				        next = tokens.peek();				    
				}			
				return new Term(factor);
		}

		public Factor getFactor() {
			return factor;
		}		

		@Override
		public String generate_assembly() {
				
				if(this.factor != null) {
					return this.factor.generate_assembly();				
				}			
				throw new ParseException(
						"Could not generate assembly in Term : generate_assembly "
						+ "factor is"+this.factor);
		}
}

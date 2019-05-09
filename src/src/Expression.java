import java.util.ArrayList;
import java.util.LinkedList;

/**Expression will generate assembly code that expects the input data to be in register eax
 * and the output will be also in register eax
 * @author Oana
 *
 */
public class Expression implements AssemblyGenerator{

//	final - se modifica o singura data, in constructor
		private final Term term;		
		
		private Expression(Term term) {
			
			this.term = term;
		}
				
		public static Expression parse_expression(LinkedList<String> tokens) {

			//pops off some tokens
			Term term = Term.parse_term(tokens);
			String next = tokens.peek();
			while(next.equals(Lexer.ADDITION) || next.equals(Lexer.NEGATION)) {
			        String operator = tokens.poll();
			        Term next_term = Term.parse_term(tokens);
			        term = new BinaryOpTerm(operator, term, next_term);
			        next = tokens.peek();				    
			}			
			return new Expression(term);
		}

		public Term getTerm() {
			return term;
		}		

		@Override
		public String generate_assembly() {
			
			if(this.term != null) {
					return this.term.generate_assembly();				
			}			
			throw new ParseException(
					"Could not generate assembly in Expression : generate_assembly "
					+ "term is"+this.term);
		}
}

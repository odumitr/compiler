import java.util.LinkedList;

/**Will generate assembly code that expects the input data to be in register eax
 * and the output will be also in register eax
 * @author Oana
 *
 */
public class BinaryOpTerm extends Term{

		private final String operator;
		private final Term left_term;
		private final Term right_term;			
		
		public BinaryOpTerm(String operator, Term left_term, Term right_term) {
			
			this.operator = operator;
			this.left_term = left_term;
			this.right_term = right_term;			
		}				
		
		public String getOperator() {
			return operator;
		}

		public Term getLeft_term() {
			return left_term;
		}

		public Term getRight_term() {
			return right_term;
		}	
		
		@Override
		public String generate_assembly() {
								
					StringBuilder assembly =new StringBuilder(); 
					assembly.append(this.left_term.generate_assembly());					
					assembly.append("\n");
					assembly.append("push %eax");
					assembly.append("\n");
					assembly.append(this.right_term.generate_assembly());
					assembly.append("\n");
					assembly.append("pop %ecx");
					assembly.append("\n");
					if(this.operator.equals(Lexer.ADDITION)) {
							return assembly.append("addl %ecx, %eax").toString();
					}
					else if(this.operator.equals(Lexer.NEGATION)) {
							assembly.append("subl %eax, %ecx");
							assembly.append("\n");
							assembly.append("movl    %ecx, %eax");
							return assembly.toString();
					}
					throw new ParseException(
							"Could not generate assembly in BinaryOpTerm : generate_assembly "+
										" operator is "+this.operator);
		}
}

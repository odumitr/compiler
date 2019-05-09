import java.util.LinkedList;

/**Will generate assembly code that expects the input data to be in register eax
 * and the output will be also in register eax
 * @author Oana
 *
 */
public class UnaryOp implements AssemblyGenerator{

	private String operator;
	private Factor factor;
	
	public UnaryOp(LinkedList<String> tokens) {
						
		this.operator = tokens.poll();
		this.factor = Factor.parse_factor(tokens);
	}
		
	public String getOperator() {
		return operator;
	}
	
	public Factor getExpr() {
		return factor;
	}

	@Override
	public String generate_assembly() {
			
				String assembly = factor.generate_assembly()+ "\n";
				if(operator.equals(Lexer.NEGATION)) {
						return assembly +"neg     %eax";				
				}
				else if(operator.equals(Lexer.COMPLEMENT)) {
						return assembly +"not     %eax";
				}
				else if(operator.equals(Lexer.LOGICAL_NEG)) {
						assembly += "cmpl   $0, %eax"+"\n";   
						assembly += "movl   $0, %eax"+"\n";
						assembly +=  "sete   %al"+"\n";
						return assembly;
				}
				throw new ParseException(
						"Could not generate assembly in Unary op : generate_assembly "
						+ "factor is"+this.factor+ " operator is"+this.operator);
	}		
}

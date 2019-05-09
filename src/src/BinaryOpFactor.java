
public class BinaryOpFactor extends Factor{
	
		private String operator;
		private Factor left_factor;
		private Factor right_factor;
		
		public BinaryOpFactor(String operator, Factor left_factor, Factor right_factor) {
			
			this.operator = operator;			
			this.left_factor = left_factor;
			this.right_factor = right_factor;
		}
		
		public String getOperator() {
			return operator;
		}

		public Factor getLeft_factor() {
			return left_factor;
		}

		public Factor getRight_factor() {
			return right_factor;
		}
		
		@Override
		public String generate_assembly() {

				StringBuilder assembly =new StringBuilder(); 
				assembly.append(this.left_factor.generate_assembly());					
				assembly.append("\n");
				assembly.append("push %eax");
				assembly.append("\n");
				assembly.append(this.right_factor.generate_assembly());
				assembly.append("\n");
				assembly.append("pop %ecx");
				assembly.append("\n");
				if(this.operator.equals(Lexer.MULTIPLICATION)) {
						return assembly.append("imul %ecx, %eax").toString();
				}
				else if(this.operator.equals(Lexer.DIVISION)) {
						assembly.append("xchg    %ecx, %eax");
						assembly.append("\n");
						assembly.append("movl $0, %edx");
						assembly.append("\n");
						assembly.append("idivl %ecx");						
						return assembly.toString();
				}
				throw new ParseException(
						"Could not generate assembly in BinaryOpFactor : generate_assembly "+
									" operator is "+this.operator);
		}
}

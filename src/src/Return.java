import java.util.LinkedList;

public class Return extends Statement{

	private Expression expres;
	
	public Return(LinkedList<String> tokens) {
			
//		pop return keyword
		tokens.poll();
		this.expres = Expression.parse_expression(tokens);
		String last_part_statem = tokens.poll();
		if(!last_part_statem.equals(Lexer.SEMICOLON)) {
			throw new ParseException("Crashed while building Return: missing ;");
		}
	}
	
	public Expression getExpres() {
		return expres;
	}

	@Override
	public String generate_assembly() {
			
			String assembly = this.getExpres().generate_assembly();	
			assembly = assembly +  "\n";
			assembly = assembly + "ret";
			return assembly;		
	}	
}

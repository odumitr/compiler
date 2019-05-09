import java.util.LinkedList;

public class Program {

	private Function func;
	
	public Program(LinkedList<String> tokens) {
				
		this.func = new Function(tokens);		
	}
	
	public Function getFunction() {
		return func;
	}
}

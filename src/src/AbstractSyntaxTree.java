import java.util.LinkedList;

public class AbstractSyntaxTree {

	private Program p;		

	public AbstractSyntaxTree(LinkedList<String> tokens){
		
		this.p = new Program(tokens);		
	}
	
	public Program getProgram() {
		return p;
	}	
}

import java.util.LinkedList;

/**Will generate assembly code that expects the input data to be in register eax
 * and the output will be also in register eax
 * @author Oana
 *
 */
public class Constant implements AssemblyGenerator{

	private int value;

	public Constant(LinkedList<String> tokens) {
		
		this.value = Integer.valueOf(tokens.poll());
	}
	
	public int getValue() {
		return value;
	}

	@Override
	public String generate_assembly() {
						
			String assembly_line = "movl    $"+this.value+", %eax";
			return assembly_line;
	}	
}

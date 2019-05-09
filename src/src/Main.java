import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;

public class Main {

	public static void main(String []args) {
		
			LinkedList<String> list_tokens = Lexer.lex("../tests/lexer_parser/valid/multi_digit.c");		
			AbstractSyntaxTree ast = new AbstractSyntaxTree(list_tokens);	
			String assembly_code = generate_assembly(ast);
			output_assembly_code(assembly_code, "../tests/lexer_parser/valid/output_multi_digit.s");
	}
	
	public static void output_assembly_code(String assembly_code, 
																		String file_name) {
		
			try {
					PrintWriter writer = new PrintWriter(file_name, "UTF-8");								
					writer.println(assembly_code);				
					writer.close();
			}
			catch(Exception e) {
					e.printStackTrace();
			}
	}
	
	public static String generate_assembly(AbstractSyntaxTree ast){
		
			String assembly_lines = "";
			Function f_main = ast.getProgram().getFunction();
			assembly_lines += ".globl	main";
			assembly_lines += "\n";
			assembly_lines += "main:";
			assembly_lines += "\n";
			String statement_assembly = f_main.getStatement().generate_assembly();
			assembly_lines += statement_assembly;
			return assembly_lines;
		}
	
	
}

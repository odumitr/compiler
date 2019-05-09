import java.util.LinkedList;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Single;

public class Test {

	public static void main(String []args) {
		
//		tests: invalid tests parser stage 1
		single_file_test_invalid("stage_1", "missing_paren");		
		single_file_test_invalid("stage_1", "missing_retval");
		single_file_test_invalid("stage_1", "no_brace");
		single_file_test_invalid("stage_1", "no_semicolon");
		single_file_test_invalid("stage_1", "no_space");
		single_file_test_invalid("stage_1", "wrong_case");
		
//		tests: valid tests parser stage 1
		single_file_test("stage_1", "multi_digit");
		single_file_test("stage_1", "newlines");
		single_file_test("stage_1", "no_newlines");
		single_file_test("stage_1", "return_0");
		single_file_test("stage_1", "return_2");
		single_file_test("stage_1", "spaces");
		
//		tests: invalid tests parser stage 2
		single_file_test_invalid("stage_2", "missing_const");		
		single_file_test_invalid("stage_2", "missing_semicolon");
		single_file_test_invalid("stage_2", "nested_missing_const");
		single_file_test_invalid("stage_2", "wrong_order");
		
//		tests: valid tests parser stage 2
		single_file_test("stage_2", "bitwise");
		single_file_test("stage_2", "bitwise_zero");
		single_file_test("stage_2", "neg");
		single_file_test("stage_2", "nested_ops");
		single_file_test("stage_2", "nested_ops_2");
		single_file_test("stage_2", "not_five");
		single_file_test("stage_2", "not_zero");
		
//		tests: invalid tests parser stage 3
		single_file_test_invalid("stage_3", "malformed_paren");		
		single_file_test_invalid("stage_3", "missing_first_op");
		single_file_test_invalid("stage_3", "missing_second_op");
		single_file_test_invalid("stage_3", "no_semicolon");
		
//		tests: valid tests parser stage 3
		single_file_test("stage_3", "add");
		single_file_test("stage_3", "associativity");
		single_file_test("stage_3", "associativity_2");
		single_file_test("stage_3", "div");
		single_file_test("stage_3", "mult");
		single_file_test("stage_3", "parens");
		single_file_test("stage_3", "precedence");
		single_file_test("stage_3", "sub");
		single_file_test("stage_3", "sub_neg");
		single_file_test("stage_3", "unop_add");
		single_file_test("stage_3", "unop_parens");
	}
	
	private static void single_file_test_invalid(String folder, String file) {
				
		try {
				single_file_test(folder, file);
		}
		catch(ParseException e) {
				System.err.println(e.getMessage()+" for file: "+file);
				return;
		}		
		System.out.println("Valid for file: "+file);		
	}
	
	private static void single_file_test(String folder, String file) {
		
			LinkedList<String> list_tokens = Lexer.lex("../tests/lexer_parser/"+folder+"/"+file+".c");
			AbstractSyntaxTree ast = new AbstractSyntaxTree(list_tokens);
			String assembly_code = Main.generate_assembly(ast);
			Main.output_assembly_code(assembly_code, "../tests/lexer_parser/"+folder+"/"+file+"_output.s");
	}
}

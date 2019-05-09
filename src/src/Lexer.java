import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

/*	Write a lex function that accepts a file (source code C) (input) 
 * and returns a list of tokens. (output) 
 * It should work for all stage 1 examples in the test suite, 
 * including the invalid ones
 * */
public class Lexer {
	
	final static String OPEN_BRACES = "{";
	final static String CLOSE_BRACES = "}";
	final static String OPEN_PARANTH = "(";
	final static String CLOSE_PARANTH = ")";
	final static String SEMICOLON = ";";
	final static String INT_KEYWORD = "int";
	final static String RETURN_KEYWORD = "return";
	final static String IDENTIFIER_MAIN = "main";
	final static String TAB = "\t";
	final static String NEGATION = "-";
	final static String COMPLEMENT = "~";
	final static String LOGICAL_NEG = "!";
	final static String ADDITION = "+";
	final static String MULTIPLICATION = "*";
	final static String DIVISION = "/";
	
	protected static LinkedList<String> lex(String path_file_scode) {
				
			LinkedList<String> list_tokens = new LinkedList<String>();
			try {			
				    BufferedReader in = new BufferedReader(new FileReader(path_file_scode));		    
				    String str;		    
				    while ((str = in.readLine()) != null) {		        		    	
		//					process line								
				    		process_line(str, list_tokens);									
				    }
				    in.close();
			} catch (IOException e) {
			}							
			return list_tokens;
	}	
	
//	TODO: improvement: use regular expressions to identify tokens
	private static void process_line(String line, LinkedList<String> list_tokens) {
		
			for(int i=0; i<line.length(); i++) {
	//			unary token
				String x = line.substring(i, i+1);
				if(x.equals(OPEN_BRACES) || x.equals(CLOSE_BRACES) || 
						x.equals(OPEN_PARANTH) || x.equals(CLOSE_PARANTH) ||
						x.equals(SEMICOLON) ||
						x.equals(NEGATION) ||
						x.equals(COMPLEMENT) ||
						x.equals(LOGICAL_NEG) ||
						x.equals(ADDITION) ||
						x.equals(DIVISION) ||
						x.equals(MULTIPLICATION)) {
						list_tokens.add(x);
				}
			
//			multiletter token
//			start looking for int/main/return token
			else if(x.equals(INT_KEYWORD.substring(0, 1)) && line.substring(i, i+INT_KEYWORD.length()).equals(INT_KEYWORD) &&
//				if int: check for space/tab/nothing/(/)/{/}/; before it, space/tab/nothing/(/)/{/}/; after it;									
					check_validity_token(line, i, INT_KEYWORD)) {
//						move index
						i = i+INT_KEYWORD.length()-1;
						list_tokens.add(INT_KEYWORD);									
			}
			else if(x.equals(IDENTIFIER_MAIN.substring(0, 1)) && line.substring(i, i+IDENTIFIER_MAIN.length()).equals(IDENTIFIER_MAIN) &&					
//					if main: check for space/tab/nothing/(/)/{/}/; before it, space/tab/nothing/(/)/{/}/; after it;
					check_validity_token(line, i, IDENTIFIER_MAIN)) {
//						move index
						i = i+IDENTIFIER_MAIN.length()-1;
						list_tokens.add(IDENTIFIER_MAIN);									
			}
			else if(x.equals(RETURN_KEYWORD.substring(0, 1)) && line.substring(i, i+RETURN_KEYWORD.length()).equals(RETURN_KEYWORD) && 
//					if return: check for space/tab/nothing/(/)/{/}/; before it, space/tab/nothing/(/)/{/}/; after it;
					check_validity_token(line, i, RETURN_KEYWORD)) {
//						move index
						i = i+RETURN_KEYWORD.length()-1;
						list_tokens.add(RETURN_KEYWORD);								
			}
			
//			look for the integer
			else if(x.matches("-?\\d+(\\.\\d+)?")) {
				String number = obtain_number(line, i);
				if(check_validity_token(line, i, number)) {
//					move index
					i = i+number.length()-1;
					list_tokens.add(number);
				}				
			}
			
			else if(x.equals(" ") || x.equals("\t")) {
				continue;
			}
			else {
//				none of them matched: build an unknown token, start at index i - stop at space/tab/}/{/(/)/;
				String unknown_token = x;
				for(int j=i+1; j<line.length(); j++) {
					String next_char = line.substring(j, j+1); 
					if(next_char.equals(" ") || next_char.equals(TAB) || next_char.equals(CLOSE_BRACES) || next_char.equals(OPEN_BRACES) ||
							next_char.equals(CLOSE_PARANTH) || next_char.equals(OPEN_PARANTH) || next_char.equals(SEMICOLON) ||
							next_char.equals(ADDITION) || next_char.equals(MULTIPLICATION) || next_char.equals(DIVISION) ) {
						unknown_token = line.substring(i, j);
						i = j-1;
						list_tokens.add(unknown_token);
						break;
					}
				}
			}
		}			
	}
	
	private static String obtain_number(String line, int index) {
		
		int i;
		for(i = index+1; i<line.length(); i++) {
			if(line.substring(index, i+1).matches("-?\\d+(\\.\\d+)?")) {
				continue;
			}
			break;
		}
		return line.substring(index, i);	
	}
	
	private static boolean check_validity_token(String line, int index, String token) {
		
		String char_after_keyword = null;	
		String char_before_keyword = "";
		if(index != 0) {
			char_before_keyword = line.substring(index-1, index);
		}
		boolean valid_before_keyword = false;
		boolean valid_after_keyword = false;
		
		if(index+token.length() == line.length()){
			valid_after_keyword = true; 
		}
		else{
			char_after_keyword = line.substring(index+token.length(), index+token.length()+1);
		}
		
		if(index == 0 || 
			char_before_keyword.equals(" ") ||
			char_before_keyword.equals(TAB) ||
			char_before_keyword.equals(OPEN_BRACES) ||
			char_before_keyword.equals(CLOSE_BRACES) ||
			char_before_keyword.equals(CLOSE_PARANTH) ||
			char_before_keyword.equals(OPEN_PARANTH) ||
			char_before_keyword.equals(SEMICOLON) ||
			char_before_keyword.equals(NEGATION) ||
			char_before_keyword.equals(COMPLEMENT) ||
			char_before_keyword.equals(LOGICAL_NEG) ||
			char_before_keyword.equals(ADDITION) ||
			char_before_keyword.equals(MULTIPLICATION) ||
			char_before_keyword.equals(DIVISION)) {
			valid_before_keyword = true;
		}
		
		if((char_after_keyword != null && token.equals(Lexer.RETURN_KEYWORD)) &&
				(char_after_keyword.equals(" ") ||
				char_after_keyword.equals(TAB) ||
				char_after_keyword.equals(OPEN_BRACES) ||
				char_after_keyword.equals(CLOSE_BRACES) ||
				char_after_keyword.equals(CLOSE_PARANTH) ||
				char_after_keyword.equals(OPEN_PARANTH) ||
				char_after_keyword.equals(SEMICOLON) ||
				char_after_keyword.equals(ADDITION) ||
				char_after_keyword.equals(MULTIPLICATION) ||
				char_after_keyword.equals(DIVISION))) {
				valid_after_keyword = true;
		}
		else if(char_after_keyword != null && (char_after_keyword.equals(" ") ||
			char_after_keyword.equals(TAB) ||
			char_after_keyword.equals(OPEN_BRACES) ||
			char_after_keyword.equals(CLOSE_BRACES) ||
			char_after_keyword.equals(CLOSE_PARANTH) ||
			char_after_keyword.equals(OPEN_PARANTH) ||
			char_after_keyword.equals(SEMICOLON) ||
			char_after_keyword.equals(NEGATION) ||
			char_after_keyword.equals(COMPLEMENT) ||
			char_after_keyword.equals(LOGICAL_NEG) ||
			char_after_keyword.equals(ADDITION) ||
			char_after_keyword.equals(MULTIPLICATION) ||
			char_after_keyword.equals(DIVISION))) {
			valid_after_keyword = true;
		}			
		
		if(valid_after_keyword == true && valid_before_keyword == true) {						
			return true;
		}
		return false;
	}	
}

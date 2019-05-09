import java.util.LinkedList;

public class StatementFactory {

	protected static Statement build_statement(LinkedList<String> tokens) {

		if (tokens.peek().equals(Lexer.RETURN_KEYWORD)) {
			Statement return_stm = new Return(tokens);
			return return_stm;
		}
		throw new ParseException("Crashed while building statement: invalid statement keyword");
	}
}

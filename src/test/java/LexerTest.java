import org.junit.Test;
import stone.CodeDialog;
import stone.Lexer;
import stone.ParseException;
import stone.Token;

public class LexerTest {

    @Test
    public void run() throws ParseException {
        Lexer l = new Lexer(new CodeDialog());
        for(Token t; (t=l.read())!= Token.EOF; ) {
            System.out.println("=> " + t.getText());
        }
    }
}

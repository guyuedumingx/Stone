package stone;

import java.io.IOException;
import java.io.LineNumberReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class Lexer {
    public static String regexPat =
            "\\s*((//.*)||([0-9]+)|(\"(\\\\\"|\\\\\\\\|\\\\n|[^\"])*\")"
            + "|[A-Z_a-z][A-Z_a-z0-9]*|==|<=|>=|&&|\\|\\||\\p{Punct})?";
    private Pattern pattern = Pattern.compile(regexPat);
    private ArrayList<Token> quene = new ArrayList<Token>();
    private boolean hasMore;
    private LineNumberReader reader;

    public Lexer(Reader r){
        hasMore = true;
        reader = new LineNumberReader(r);
    }

    public Token read() throws ParseException {
        if(fillQueue(0)){
            return quene.remove(0);
        }else {
            return Token.EOF;
        }
    }

    public Token peek(int i) throws ParseException {
        if(fillQueue(i)){
            return quene.get(i);
        }else {
            return Token.EOF;
        }
    }

    public boolean fillQueue(int i) throws ParseException {
        while (i >= quene.size()){
            if(hasMore){
                readLine();
            } else {
                return false;
            }
        }
        return true;
    }

    protected void readLine() throws ParseException {
        String line;
        try{
            line = reader.readLine();
        } catch (IOException e) {
            throw new ParseException(e);
        }
        if(line == null){
            hasMore = false;
            return;
        }
    }
}

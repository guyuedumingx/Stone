package stone;

import java.io.IOException;
import java.io.LineNumberReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 语法分析器
 * @author yohoyes
 */
public class Lexer {
    public static String regexPat =
            "\\s*((//.*)||([0-9]+)|(\"(\\\\\"|\\\\\\\\|\\\\n|[^\"])*\")"
            + "|[A-Z_a-z][A-Z_a-z0-9]*|==|<=|>=|&&|\\|\\||\\p{Punct})?";
    private final Pattern pattern = Pattern.compile(regexPat);
    private final ArrayList<Token> quene = new ArrayList<Token>();
    private final LineNumberReader reader;
    private boolean hasMore;

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
        int lineNo = reader.getLineNumber();
        Matcher matcher = pattern.matcher(line);
        matcher.useTransparentBounds(true).useAnchoringBounds(false);
        int pos = 0;
        int endPos = line.length();
        while (pos < endPos){
            matcher.region(pos, endPos);
            if(matcher.lookingAt()) {
                addToken(lineNo, matcher);
                pos = matcher.end();
            } else {
                throw new ParseException("bad token at line " + lineNo);
            }
        }
        quene.add(new IdToken(lineNo, Token.EOL));
    }

    protected void addToken(int lineNo, Matcher matcher) {
        String m = matcher.group();
        if(m != null){
            if(matcher.group(2) == null) {
                Token token;
                if(matcher.group(3) != null){
                    token = new NumToken(lineNo, Integer.parseInt(m));
                } else if(matcher.group(4) != null){
                    token = new StrToken(lineNo, toStringLiteral(m));
                }else {
                    token = new IdToken(lineNo, m);
                }
                quene.add(token);
            }
        }
    }

    protected String toStringLiteral(String s) {
        StringBuilder sb = new StringBuilder();
        int len = s.length() - 1;
        for(int i=1; i<len; i++){
            char c = s.charAt(i);
            if(c == '\\' && i+1<len) {
                int c2 = s.charAt(i + 1);
                if(c2 == '"' || c2 == '\\') {
                    c = s.charAt(++i);
                } else if(c2 == 'n') {
                    ++i;
                    c = '\n';
                }
            }
            sb.append(c);
        }
        return sb.toString();
    }

    /**
     * 整型字面量
     */
    protected static class NumToken extends Token {
        private int value;

        protected NumToken(int line, int v) {
            super(line);
            value = v;
        }

        @Override
        public boolean isNumber() {
            return true;
        }

        @Override
        public String getText() {
            return Integer.toString(value);
        }

        @Override
        public int getNumber() {
            return value;
        }
    }

    /**
     * 标识符
     */
    protected static class IdToken extends Token {
        private String text;

        protected IdToken(int line, String id) {
            super(line);
            text = id;
        }

        @Override
        public boolean isIdentifier() {
            return true;
        }

        @Override
        public String getText() {
            return text;
        }
    }

    /**
     * 字符串字面量
     */
    protected static class StrToken extends Token {
        private String literal;
        StrToken(int line, String str) {
            super(line);
            literal = str;
        }

        @Override
        public boolean isString() {
            return true;
        }

        @Override
        public String getText() {
            return literal;
        }
    }
}

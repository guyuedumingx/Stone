package stone;

/**
 * 单词类
 *
 * @author yohoyes
 */
public abstract class Token {

    public static final Token EOF = new Token(-1) {
    };

    public static final String EOL = "\\n";

    //行数
    private int lineNumber;

    protected Token(int line) {
        lineNumber = line;
    }

    public int getLineNumber() {
        return lineNumber;
    }

    /**
     * 如果是标识符则为真
     */
    public boolean isIdentifier() {
        return false;
    }

    /**
     * 如果是数字则为真
     */
    public boolean isNumber() {
        return false;
    }

    /**
     * 如果是字符串字面量则为真
     */
    public boolean isString() {
        return false;
    }

    /**
     * 获取数字
     *
     * @return
     */
    public int getNumber() {
        throw new StoneException("not number token");
    }

    /**
     * 获取文本
     * 用于标识符和字符串或数字的String形式
     */
    public String getText() {
        return "";
    }
}

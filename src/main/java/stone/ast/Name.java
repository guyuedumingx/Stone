package stone.ast;


import stone.Token;

/**
 * 变量名
 * such as: x
 */
public class Name extends ASTLeaf {

    public Name(Token t) {
        super(t);
    }

    public String name() {
        return token().getText();
    }
}

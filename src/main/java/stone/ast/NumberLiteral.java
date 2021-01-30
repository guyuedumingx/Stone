package stone.ast;

import stone.Token;

/**
 * 整型字面量
 * such as: 13
 */
public class NumberLiteral extends ASTLeaf {

    public NumberLiteral(Token t) {
        super(t);
    }

    public int value() {
        return token().getNumber();
    }
}

package stone.ast;

import java.util.Iterator;

/**
 * 节点父类
 * 抽象语法树的所有节点类都是这个类的子类
 *
 * @author yohoyes
 */
public abstract class ASTree implements Iterable<ASTree> {
    /**
     * @param i 序号
     * @return 返回第i个子节点
     */
    public abstract ASTree child(int i);

    /**
     * @return 返回子节点个数
     */
    public abstract int numChildren();

    /**
     * 子节点iterator
     *
     * @return 返回一个用于遍历子节点是的iterator
     */
    public abstract Iterator<ASTree> children();

    /**
     * @return 返回一个用于表示抽象语法树节点在程序内所处位置的字符串
     */
    public abstract String location();

    /**
     * 是一个适配器
     * 与children()方法的功能相同
     */
    public Iterator<ASTree> iterator() {
        return children();
    }
}

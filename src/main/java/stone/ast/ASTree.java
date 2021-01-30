package stone.ast;

import java.util.Iterator;

/**
 * 节点父类
 *
 * @author yohoyes
 */
public abstract class ASTree implements Iterable<ASTree> {
    /**
     * 根据index获取子节点
     *
     * @param i 序号
     * @return 子节点
     */
    public abstract ASTree child(int i);

    /**
     * 子节点个数
     *
     * @return 个数
     */
    public abstract int numChildren();

    /**
     * 子节点iterator
     *
     * @return 枚举
     */
    public abstract Iterator<ASTree> children();

    public abstract String location();

    public Iterator<ASTree> iterator() {
        return children();
    }
}

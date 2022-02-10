package org.lxb.algorithmic.struct.interfaces;

/**
 * 树可以递归地定义为节点的集合（从根节点开始），
 * 其中每个节点是一个由一个值和一个节点列表（“子节点”）组成的数据结构，
 * 具有没有节点重复的约束。一棵树可以抽象地定义为一个整体（全局）
 * 作为有序树，为每个节点分配一个值。
 * <p>
 * @see <a href="https://en.wikipedia.org/wiki/Tree_(data_structure)">Tree (Wikipedia)</a>
 * <br>
 */
public interface ITree<T> {

    /**
     * 将数值添加到树种，树中可以包含多个相等的值
     *
     * @param value to add to the tree.
     * @return True if successfully added to tree.
     */
    public boolean add(T value);

    /**
     * 删除在树中第一个出现的值
     *
     * @param value to remove from the tree.
     * @return T value removed from tree.
     */
    public T remove(T value);

    /**
     * 清除整个数
     */
    public void clear();

    /**
     * 树中是否包含该值
     *
     * @param value to locate in the tree.
     * @return True if tree contains value.
     */
    public boolean contains(T value);

    /**
     * 或者树中节点的个数
     *
     * @return Number of nodes in the tree.
     */
    public int size();

    /**
     * 根据约束验证树是否符合规定
     *
     * @return True if the tree is valid.
     */
    public boolean validate();

    /**
     * 将树中数据以Java集合方式返回
     *
     * @return Java compatible Collection
     */
    public java.util.Collection<T> toCollection();

}


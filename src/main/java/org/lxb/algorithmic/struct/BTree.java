package org.lxb.algorithmic.struct;

import org.lxb.algorithmic.struct.interfaces.ITree;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;

public class BTree<T extends Comparable<T>> implements ITree<T> {

    private int minKeySize = 1;
    private int minChildrenSize = minKeySize + 1;
    private int maxKeySize = minKeySize * 2;
    private int maxChildrenSize = maxKeySize + 1;

    private Node<T> root = null;
    private int size = 0;

    public BTree(){}

    public BTree(int order) {
        this.minKeySize = order;
        this.minChildrenSize = minKeySize + 1;
        this.maxKeySize = minKeySize * 2;
        this.maxChildrenSize = maxKeySize + 1;
    }


    @Override
    public boolean add(T value) {
        if(root == null) {
            root = new Node<>(maxKeySize,maxChildrenSize);
            root.addKey(value);
        } else {
            Node<T> node = root;
            while (node != null) {
                if (node.getChildrenSize() == 0) {
                    node.addKey(value);
                    if (node.getKeySize() <= maxKeySize) {
                        break;
                    }
                    splitNode(node);
                    break;
                }

                T lesser = node.keys[0];
                if(value.compareTo(lesser) <= 0) {
                    node = node.children[0];
                    continue;
                }

                int numberOfKeys = node.getKeySize();
                int lastKeyIndex = numberOfKeys - 1;
                T greater = node.keys[lastKeyIndex];
                if(value.compareTo(greater) > 0) {
                    node = node.children[numberOfKeys];
                    continue;
                }
            }
        }
        size ++;
        return true;
    }

    private void splitNode(Node<T> nodeTobeSplit) {
        Node<T> node = nodeTobeSplit;
        int numberOfKeys = node.keySize;
        int middleIndex = numberOfKeys/2;

        Node<T> left = new Node<>(maxKeySize,maxChildrenSize);
        for (int i = 0; i < middleIndex ; i++) {
            left.addKey(node.keys[i]);
        }

        if(node.childrenSize > 0) {
            for (int i = 0; i < middleIndex; i++) {
                Node<T> childrenNode = node.children[i];
                left.addChildren(childrenNode);
            }
        }

        Node<T> right = new Node<>(maxKeySize,maxChildrenSize);
        for (int i = middleIndex + 1; i < numberOfKeys ; i++) {
            right.addKey(node.keys[i]);
        }

        if(node.childrenSize > 0) {
            for (int i = middleIndex + 1; i < node.childrenSize; i++) {
                Node<T> childrenNode = node.children[i];
                right.addChildren(childrenNode);
            }
        }

        //根节点分裂
        if(node.parent == null) {
            Node<T> newRoot = new Node<>(maxKeySize,maxChildrenSize);
            newRoot.addKey(node.getValue(middleIndex));
            root = newRoot;
            node = root;
            node.addChildren(left);
            node.addChildren(right);
        } else {
            Node<T> parent = node.parent;
            parent.addKey(node.getValue(middleIndex));
            parent.removeChildren(node);
            parent.addChildren(left);
            parent.addChildren(right);

            if(parent.keySize > maxKeySize) {
                splitNode(parent);
            }
        }
    }

    @Override
    public T remove(T value) {
        return null;
    }

    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    @Override
    public boolean contains(T value) {
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean validate() {
        return false;
    }

    @Override
    public Collection<T> toCollection() {
        return null;
    }

    protected static class Node<T extends Comparable<T>> {

        private int keySize = 0;
        private T[] keys = null;
        private int childrenSize = 0;
        private Node<T>[] children = null;
        private Node<T> parent = null;

        public Node(int keySize, int childrenSize, Node<T> parent) {
            this.keySize = 0;
            this.childrenSize = 0;
            this.keys = (T[]) new Comparable[keySize + 1];
            this.children = new Node[childrenSize + 1];
            this.parent = parent;
        }

        public Node(int keySize, int childrenSize) {
            this.keySize = 0;
            this.childrenSize = 0;
            this.keys = (T[]) new Comparable[keySize + 1];
            this.children = new Node[childrenSize + 1];
        }

        public void addKey(T value) {
            keys[keySize++] = value;
            Arrays.sort(keys,0,keySize);
        }

        public T getValue(int index) {
            return keys[index];
        }

        public void addChildren(Node<T> node) {
            node.parent = this;
            children[childrenSize ++ ] = node;
            Arrays.sort(children, 0, childrenSize, new Comparator<Node<T>>() {
                @Override
                public int compare(Node<T> o1, Node<T> o2) {
                    return o1.keys[0].compareTo(o2.keys[0]);
                }
            });
        }

        public int getChildrenSize() {
            return childrenSize;
        }

        public int getKeySize() {
            return keySize;
        }

        public void removeChildren(Node<T> node) {
            boolean found = false;
            if(childrenSize == 0) {
                return;
            }
            for (int i = 0; i < childrenSize; i++) {
                if(children[i].equals(node)) {
                    found = true;
                } else if(found) {
                    children[i-1] = children[i];
                }
            }
            if(found) {
                childrenSize --;
                children[childrenSize] = null;
            }
        }
    }

}

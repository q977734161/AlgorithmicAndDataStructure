package org.lxb.algorithmic.struct;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class AVLTreeTest {
    /**
     ..........8....... <br>
     .......5......7.... <br>
     ....1....3........ <br>
     .......2.......... <br>
     */
    private static final AVLTree<Integer> testAvlTree = new AVLTree<Integer>();
    static {
        testAvlTree.add(8);
        testAvlTree.add(5);
        testAvlTree.add(7);
        testAvlTree.add(1);
        testAvlTree.add(3);
        testAvlTree.add(2);
    }

    @Test
    public void print() {
        String result = AVLTreePrinter.getString(testAvlTree);
        System.out.println(result);
    }

    protected static class AVLTreePrinter {

        public static <T extends Comparable<T>> String getString(AVLTree<T> tree) {
            if (tree.root == null)
                return "Tree has no nodes.";
            return getString((AVLTree.AVLNode<T>) tree.root, "", true);
        }

        public static <T extends Comparable<T>> String getString(AVLTree.AVLNode<T> node) {
            if (node == null)
                return "Sub-tree has no nodes.";
            return getString(node, "", true);
        }

        private static <T extends Comparable<T>> String getString(AVLTree.AVLNode<T> node, String prefix, boolean isTail) {
            StringBuilder builder = new StringBuilder();

            builder.append(prefix + (isTail ? "└── " : "├── ") + "(" + node.height + ") " + node.id + "\n");
            List<BinarySearchTree.Node<T>> children = null;
            if (node.lesser != null || node.greater != null) {
                children = new ArrayList<BinarySearchTree.Node<T>>(2);
                if (node.lesser != null)
                    children.add(node.lesser);
                if (node.greater != null)
                    children.add(node.greater);
            }
            if (children != null) {
                for (int i = 0; i < children.size() - 1; i++) {
                    builder.append(getString((AVLTree.AVLNode<T>) children.get(i), prefix + (isTail ? "    " : "│   "), false));
                }
                if (children.size() >= 1) {
                    builder.append(getString((AVLTree.AVLNode<T>) children.get(children.size() - 1), prefix + (isTail ? "    " : "│   "), true));
                }
            }

            return builder.toString();
        }
    }
}

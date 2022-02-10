package org.lxb.algorithmic.struct;

import org.lxb.algorithmic.struct.interfaces.ITree;

import java.lang.reflect.Array;
import java.util.*;

public class BinarySearchTree<T extends Comparable<T>> implements ITree<T> {

    protected static final Random RANDOM = new Random();

    protected Node<T> root = null;
    protected int size = 0;
    protected INodeCreator<T> creator = null;

    @Override
    public boolean add(T value) {
        Node<T> node = addNode(value);
        return (node != null);
    }

    protected Node<T> addNode(T value) {
        Node<T> newNode = this.creator.createNewNode(null,value);

        if(this.root == null) {
            this.root = newNode;
            size ++;
            return newNode;
        }

        Node<T> node = root;
        while (root != null) {
            if(newNode.id.compareTo(node.id) < 0) {
                if(node.lesser == null) {
                    node.lesser = newNode;
                    newNode.parent = node;
                    size ++;
                    return newNode;
                }
                node = node.lesser;
            } else {
                if(node.greater == null) {
                    node.greater = newNode;
                    newNode.parent = node;
                    size ++;
                    return newNode;
                }
                node = node.greater;
            }
        }
        return newNode;
    }

    @Override
    public T remove(T value) {
        Node<T> node = getNode(value);
        if(node != null) {
            Node<T> replacementNode = getRelacementNode(node);
            replaceNodeWithFindNode(node,replacementNode);
        }
        return value;
    }

    protected void replaceNodeWithFindNode(Node<T> nodeToRemoved, Node<T> replacementNode) {
        if (replacementNode != null) {
            // Save for later
            Node<T> replacementNodeLesser = replacementNode.lesser;
            Node<T> replacementNodeGreater = replacementNode.greater;

            // Replace replacementNode's branches with nodeToRemove's branches
            Node<T> nodeToRemoveLesser = nodeToRemoved.lesser;
            if (nodeToRemoveLesser != null && nodeToRemoveLesser != replacementNode) {
                replacementNode.lesser = nodeToRemoveLesser;
                nodeToRemoveLesser.parent = replacementNode;
            }
            Node<T> nodeToRemoveGreater = nodeToRemoved.greater;
            if (nodeToRemoveGreater != null && nodeToRemoveGreater != replacementNode) {
                replacementNode.greater = nodeToRemoveGreater;
                nodeToRemoveGreater.parent = replacementNode;
            }

            // Remove link from replacementNode's parent to replacement
            Node<T> replacementParent = replacementNode.parent;
            if (replacementParent != null && replacementParent != nodeToRemoved) {
                Node<T> replacementParentLesser = replacementParent.lesser;
                Node<T> replacementParentGreater = replacementParent.greater;
                if (replacementParentLesser != null && replacementParentLesser == replacementNode) {
                    replacementParent.lesser = replacementNodeGreater;
                    if (replacementNodeGreater != null) {
                        replacementNodeGreater.parent = replacementParent;
                    }
                } else if (replacementParentGreater != null && replacementParentGreater == replacementNode) {
                    replacementParent.greater = replacementNodeLesser;
                    if (replacementNodeLesser != null) {
                        replacementNodeLesser.parent = replacementParent;
                    }
                }
            }
        }

        // Update the link in the tree from the nodeToRemoved to the
        // replacementNode
        Node<T> parent = nodeToRemoved.parent;
        if (parent == null) {
            // Replacing the root node
            root = replacementNode;
            if (root != null) {
                root.parent = null;
            }
        } else if (parent.lesser != null && (parent.lesser.id.compareTo(nodeToRemoved.id) == 0)) {
            parent.lesser = replacementNode;
            if (replacementNode != null) {
                replacementNode.parent = parent;
            }
        } else if (parent.greater != null && (parent.greater.id.compareTo(nodeToRemoved.id) == 0)) {
            parent.greater = replacementNode;
            if (replacementNode != null) {
                replacementNode.parent = parent;
            }
        }
        size--;
    }

    public Node<T>  getRelacementNode(Node<T> node) {
        if(node.lesser != null && node.greater == null) {
            return node.lesser;
        } else if(node.lesser == null && node.greater != null) {
            return node.greater;
        } else if(node.lesser != null && node.greater != null){
            //如果两个节点都有,那么随机获取删除节点的前继或者后继节点作为替代
            int remainder = (RANDOM.nextInt() % 2);
            if(remainder == 0) {
                Node<T> successorNode = successor(node.id);
                return successorNode;
            } else {
                Node<T> preDecessorNode = predecessor(node.id);
                return preDecessorNode;
            }
        }
        return null;
    }

    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    @Override
    public boolean contains(T value) {
        Node<T> node = getNode(value);
        return (node != null);
    }

    /**
     * 迭代查询
     * @param value
     * @return
     */
    public Node<T> getNode(T value) {
        Node<T> node = root;
        while (node != null && node.id != null) {
            if(node.id.compareTo(value) > 0) {
                node = node.lesser;
            } else if(node.id.compareTo(value) < 0) {
                node = node.greater;
            } else if(node.id.compareTo(value) == 0) {
                return node;
            }
         }
        return null;
    }

    /**
     * 递归查询
     * @param node
     * @param value
     * @return
     */
    public Node<T> getNodeRecusive(Node<T> node,T value) {
        if(node != null && value.compareTo(node.id) == 0) {
            return node;
        }
        if(node.id.compareTo(value) > 0) {
            return getNodeRecusive(node.lesser,value);
        }
        if(node.id.compareTo(value) < 0) {
            return getNodeRecusive(node.greater,value);
        }
        return null;
    }


    public Node<T> maximum(Node<T> node) {
        Node<T> tempNode = node;
        while (tempNode.greater != null) {
            tempNode = tempNode.greater;
        }
        return tempNode;
    }

    public Node<T> minimum(Node<T> node) {
        Node<T> tempNode = node;
        while (tempNode.lesser != null) {
            tempNode = tempNode.lesser;
        }
        return tempNode;
    }

    public Node<T> successor(T value) {
        Node<T> node = getNode(value);
        if(node.greater != null) {
            return minimum(node.greater);
        }
        Node<T> tempNode = node.parent;
        while (tempNode != null && node == tempNode.greater) {
            node = tempNode;
            tempNode = tempNode.parent;
        }
        return tempNode;
    }

    public Node<T> predecessor(T value) {
        Node<T> node = getNode(value);
        if(node.lesser != null) {
            return maximum(node.lesser);
        }
        Node<T> tempNode = node.parent;
        while (tempNode != null && node == tempNode.lesser) {
            node = tempNode;
            tempNode = tempNode.parent;
        }
        return tempNode;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean validate() {
        if (root == null) {
            return true;
        }
        return validateNode(root);
    }

    protected boolean validateNode(Node<T> node) {
        Node<T> lesser = node.lesser;
        Node<T> greater = node.greater;

        boolean lesserCheck = true;
        if (lesser != null && lesser.id != null) {
            lesserCheck = (lesser.id.compareTo(node.id) <= 0);
            if (lesserCheck) {
                lesserCheck = validateNode(lesser);
            }
        }
        if (!lesserCheck) {
            return false;
        }

        boolean greaterCheck = true;
        if (greater != null && greater.id != null) {
            greaterCheck = (greater.id.compareTo(node.id) > 0);
            if (greaterCheck) {
                greaterCheck = validateNode(greater);
            }
        }
        return greaterCheck;
    }

    @Override
    public Collection<T> toCollection() {
        return null;
    }

    public T[] getBFS() {
        if(root == null) {
            return null;
        }
        final Queue<Node<T>> queue = new ArrayDeque<Node<T>>();
        final T[] values = (T[]) Array.newInstance(root.id.getClass(), size);
        int count = 0;
        Node<T> node = root;
        queue.add(node);
        while (!queue.isEmpty()) {
            node = queue.remove();
            values[count++] = node.id;
            if(node.lesser != null) {
                queue.add(node.lesser);
            }
            if(node.greater != null) {
                queue.add(node.greater);
            }
        }
        return values;
    }

    public T[] getLevelOrder() {
        return getBFS();
    }

    public T[] getSorted() {
        return getDFS(DepthFirstSearchOrder.inOrder);
    }

    public T[] getDFS(DepthFirstSearchOrder order) {
        final Set<Node<T>> added = new HashSet<Node<T>>(2);
        final T[] nodes = (T[])Array.newInstance(root.id.getClass(), size);
        int index = 0;
        Node<T> node = root;
        while (index < size && node != null) {
            Node<T> parent = node.parent;
            Node<T> lesser = (node.lesser != null && !added.contains(node.lesser)) ? node.lesser : null;
            Node<T> greater = (node.greater != null && !added.contains(node.greater)) ? node.greater : null;

            if (parent == null && lesser == null && greater == null) {
                if (!added.contains(node)) {
                    nodes[index++] = node.id;
                }
                break;
            }

            if (order == DepthFirstSearchOrder.inOrder) {
                if (lesser != null) {
                    node = lesser;
                } else {
                    if (!added.contains(node)) {
                        nodes[index++] = node.id;
                        added.add(node);
                    }
                    if (greater != null) {
                        node = greater;
                    } else if (added.contains(node)) {
                        node = parent;
                    } else {
                        node = null;
                    }
                }
            } else if (order == DepthFirstSearchOrder.preOrder) {
                if (!added.contains(node)) {
                    nodes[index++] = node.id;
                    added.add(node);
                }
                if (lesser != null) {
                    node = lesser;
                } else if (greater != null) {
                    node = greater;
                } else if (added.contains(node)) {
                    node = parent;
                } else {
                    node = null;
                }
            } else {
                // post-Order
                if (lesser != null) {
                    node = lesser;
                } else {
                    if (greater != null) {
                        node = greater;
                    } else {
                        nodes[index++] = node.id;
                        added.add(node);
                        node = parent;
                    }
                }
            }
        }
        return nodes;
    }

    public enum DepthFirstSearchOrder {
        inOrder, preOrder, postOrder
    }

    public BinarySearchTree() {
        this.creator = new INodeCreator<T>() {
            @Override
            public Node<T> createNewNode(Node<T> parent, T id) {
                return new Node<>(id,parent);
            }
        };
    }

    public BinarySearchTree(INodeCreator<T> iNodeCreator) {
        this.creator = iNodeCreator;
    }


    protected static class Node<T extends Comparable<T>> {

        protected T id = null;
        protected Node<T> parent = null;
        protected Node<T> lesser = null;
        protected Node<T> greater = null;

        protected Node(T id, Node<T> parent) {
            this.id = id;
            this.parent = parent;
        }

        @Override
        public String toString() {
            return "id=" + id + " parent=" + ((parent != null) ? parent.id : "NULL") + " lesser="
                    + ((lesser != null) ? lesser.id : "NULL") + " greater=" + ((greater != null) ? greater.id : "NULL");
        }
    }

    protected static interface INodeCreator<T extends Comparable<T>> {

        /**
         * 创建新的节点
         * @param parent
         * @param id
         * @return
         */
        public Node<T> createNewNode(Node<T> parent,T id);
    }
}

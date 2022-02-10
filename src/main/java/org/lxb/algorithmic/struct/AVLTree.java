package org.lxb.algorithmic.struct;

public class AVLTree<T extends Comparable<T>> extends BinarySearchTree<T> {


    private enum BalanceEnum {
        LEFT_LEFT, LEFT_RIGHT, RIGHT_LEFT, RIGHT_RIGHT
    }

    public AVLTree() {
        this.creator = new INodeCreator<T>() {
            @Override
            public Node<T> createNewNode(Node<T> parent, T id) {
                return new AVLNode<>(id,parent);
            }
        };
    }

    public AVLTree(INodeCreator<T> creator) {
        super(creator);
    }

    @Override
    public T remove(T value) {
        // Find node to remove
        Node<T> nodeToRemoved = this.getNode(value);
        if (nodeToRemoved==null) {
            return null;
        }

        // Find the replacement node
        Node<T> replacementNode = this.getRelacementNode(nodeToRemoved);

        // Find the parent of the replacement node to re-factor the height/balance of the tree
        AVLNode<T> nodeToRefactor = null;
        if (replacementNode != null) {
            nodeToRefactor = (AVLNode<T>) replacementNode.parent;
        }
        if (nodeToRefactor == null) {
            nodeToRefactor = (AVLNode<T>) nodeToRemoved.parent;
        }
        if (nodeToRefactor != null && nodeToRefactor == nodeToRemoved) {
            nodeToRefactor = (AVLNode<T>) replacementNode;
        }

        // Replace the node
        this.replaceNodeWithFindNode(nodeToRemoved, replacementNode);

        // Re-balance the tree all the way up the tree
        while (nodeToRefactor != null) {
            nodeToRefactor.updateHeight();
            balanceAfterDelete(nodeToRefactor);

            nodeToRefactor = (AVLNode<T>) nodeToRefactor.parent;
        }

        return nodeToRemoved.id;
    }

    private void balanceAfterDelete(AVLNode<T> node) {
        int balanceFactor = node.getBalanceFactor();
        if (balanceFactor == -2 || balanceFactor == 2) {
            if (balanceFactor == -2) {
                AVLNode<T> ll = (AVLNode<T>) node.lesser.lesser;
                int lesser = (ll != null) ? ll.height : 0;
                AVLNode<T> lr = (AVLNode<T>) node.lesser.greater;
                int greater = (lr != null) ? lr.height : 0;
                if (lesser >= greater) {
                    rotateRight(node);
                    node.updateHeight();
                    if (node.parent != null) {
                        ((AVLNode<T>) node.parent).updateHeight();
                    }
                } else {
                    rotateLeft(node.lesser);
                    rotateRight(node);

                    AVLNode<T> p = (AVLNode<T>) node.parent;
                    if (p.lesser != null) {
                        ((AVLNode<T>) p.lesser).updateHeight();
                    }
                    if (p.greater != null) {
                        ((AVLNode<T>) p.greater).updateHeight();
                    }
                    p.updateHeight();
                }
            } else if (balanceFactor == 2) {
                AVLNode<T> rr = (AVLNode<T>) node.greater.greater;
                int greater = (rr != null) ? rr.height : 0;
                AVLNode<T> rl = (AVLNode<T>) node.greater.lesser;
                int lesser = (rl != null) ? rl.height : 0;
                if (greater >= lesser) {
                    rotateLeft(node);
                    node.updateHeight();
                    if (node.parent != null) {
                        ((AVLNode<T>) node.parent).updateHeight();
                    }
                } else {
                    rotateRight(node.greater);
                    rotateLeft(node);

                    AVLNode<T> p = (AVLNode<T>) node.parent;
                    if (p.lesser != null) {
                        ((AVLNode<T>) p.lesser).updateHeight();
                    }
                    if (p.greater != null) {
                        ((AVLNode<T>) p.greater).updateHeight();
                    }
                    p.updateHeight();
                }
            }
        }
    }

    @Override
    public Node<T> addNode(T value) {
        Node<T> nodeAdded = super.addNode(value);
        AVLNode<T> avlNodeAdded = (AVLNode<T>) nodeAdded;
        avlNodeAdded.updateHeight();
        AVLNode<T> parent = (AVLNode<T>) avlNodeAdded.parent;
        while (parent != null) {
            int height1 = parent.height;
            parent.updateHeight();
            balanceAfterInsert(parent);
            int heightAfterBalance = parent.height;
            if(height1 == heightAfterBalance) {
                break;
            }
            parent = (AVLNode<T>) parent.parent;
        }
        return nodeAdded;
    }

    private void balanceAfterInsert(AVLNode<T> avlNodeAdded) {
        int balanceFactor = avlNodeAdded.getBalanceFactor();
        if(Math.abs(balanceFactor) > 1) {
            AVLNode<T> child = null;
            BalanceEnum balanceEnum = null;
            if (balanceFactor < 0) {
                child = (AVLNode<T>) avlNodeAdded.lesser;
                balanceFactor = child.getBalanceFactor();
                if (balanceFactor < 0) {
                    balanceEnum = BalanceEnum.LEFT_LEFT;
                } else {
                    balanceEnum = BalanceEnum.LEFT_RIGHT;
                }
            } else {
                child = (AVLNode<T>) avlNodeAdded.greater;
                balanceFactor = child.getBalanceFactor();
                if (balanceFactor > 0) {
                    balanceEnum = BalanceEnum.RIGHT_RIGHT;
                } else {
                    balanceEnum = BalanceEnum.RIGHT_LEFT;
                }
            }

            switch (balanceEnum) {
                case LEFT_LEFT:
                    rotateRight(avlNodeAdded);
                    break;
                case LEFT_RIGHT:
                    rotateLeft(child);
                    rotateRight(avlNodeAdded);
                    break;
                case RIGHT_LEFT:
                    rotateRight(child);
                    rotateLeft(avlNodeAdded);
                    break;
                case RIGHT_RIGHT:
                    rotateLeft(avlNodeAdded);
                    break;
                default:
                    throw new RuntimeException("unknown balance enum : " + balanceEnum);
            }
            child.updateHeight();
            avlNodeAdded.updateHeight();
        }

    }

    protected void rotateLeft(Node<T> node) {
        Node<T> parent = node.parent;
        Node<T> greater = node.greater;
        Node<T> lesser = greater.lesser;

        greater.lesser = node;
        node.parent = greater;

        node.greater = lesser;

        if (lesser != null) {
            lesser.parent = node;
        }

        if (parent!=null) {
            if (node == parent.lesser) {
                parent.lesser = greater;
            } else if (node == parent.greater) {
                parent.greater = greater;
            } else {
                throw new RuntimeException("I'm not related to my parent. " + node.toString());
            }
            greater.parent = parent;
        } else {
            root = greater;
            root.parent = null;
        }
    }

    protected void rotateRight(Node<T> node) {
        Node<T> parent = node.parent;
        Node<T> lesser = node.lesser;
        Node<T> greater = lesser.greater;

        lesser.greater = node;
        node.parent = lesser;

        node.lesser = greater;

        if (greater != null) {
            greater.parent = node;
        }

        if (parent!=null) {
            if (node == parent.lesser) {
                parent.lesser = lesser;
            } else if (node == parent.greater) {
                parent.greater = lesser;
            } else {
                throw new RuntimeException("I'm not related to my parent. " + node.toString());
            }
            lesser.parent = parent;
        } else {
            root = lesser;
            root.parent = null;
        }
    }

    protected static class AVLNode<T extends Comparable<T>> extends Node<T> {

        protected int height = 1;

        protected AVLNode(T id, Node<T> parent) {
            super(id, parent);
        }

        protected boolean isLeaf() {
            return ((lesser == null) && (greater == null));
        }

        public int updateHeight() {
            int lesserHeight = 0;
            if(lesser != null) {
                AVLNode<T> lesserNode = (AVLNode<T>) lesser;
                lesserHeight = lesserNode.height;
            }
            int greaterHeight = 0;
            if(greater != null) {
                AVLNode<T> greaterNode = (AVLNode<T>) greater;
                greaterHeight = greaterNode.height;
            }
            if(lesserHeight > greaterHeight) {
                height = lesserHeight + 1;
            } else {
                height = greaterHeight + 1;
            }
            return height;
        }

        public int getBalanceFactor() {
            int lesserHeight = 0;
            if(lesser != null) {
                AVLNode<T> lesserNode = (AVLNode<T>) lesser;
                lesserHeight = lesserNode.height;
            }
            int greaterHeight = 0;
            if(greater != null) {
                AVLNode<T> greaterNode = (AVLNode<T>) greater;
                greaterHeight = greaterNode.height;
            }
            return greaterHeight - lesserHeight;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public String toString() {
            return "value=" + id + " height=" + height + " parent=" + ((parent != null) ? parent.id : "NULL")
                    + " lesser=" + ((lesser != null) ? lesser.id : "NULL") + " greater="
                    + ((greater != null) ? greater.id : "NULL");
        }
    }

}

import java.util.Collection;
import java.util.List;
import java.util.LinkedList;
import java.util.NoSuchElementException;

public class BST<T extends Comparable<? super T>> implements BSTInterface<T> {
    // DO NOT ADD OR MODIFY INSTANCE VARIABLES.
    private BSTNode<T> root;
    private int size;

    /**
     * A no argument constructor that should initialize an empty BST
     */
    public BST() {
        root = null;
        size = 0;
    }

    /**
     * Initializes the BST with the data in the Collection. The data in the BST
     * should be added in the same order it is in the Collection.
     *
     * @param data the data to add to the tree
     * @throws java.lang.IllegalArgumentException if data or any element
     * in data is null
     */
    public BST(Collection<T> data) {
        if (data == null) {
            throw new IllegalArgumentException("Data not valid");
        } else {
            for (T element : data) {
                if (element == null) {
                    throw new IllegalArgumentException("Data not valid");
                } else {
                    add(element);
                }
            }
        }
    }

    @Override
    public void add(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data is not valid");
        } else {
            root = addH(root, data);
        }

    }

    @Override
    public T remove(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data is not valid");
        } else {
            root = removeH(root, data);
        }
        return data;

    }

    @Override
    public T get(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data not valid");
        } else {
            return getH(root, data);
        }

    }

    @Override
    public boolean contains(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data not valid");
        } else {
            try {
                get(data);
            } catch (NoSuchElementException e) {
                return false;
            }
            return true;
        }

    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public List<T> preorder() {
        return preorderH(new LinkedList<T>(), root);
    }

    @Override
    public List<T> postorder() {
        return postorderH(new LinkedList<T>(), root);
    }

    @Override
    public List<T> inorder() {
        return inorderH(new LinkedList<T>(), root);
    }

    @Override
    public List<T> levelorder() {
        if (root == null) {
            return new LinkedList<T>();
        } else {
            List<BSTNode<T>> l = levelorderH(new LinkedList<>(), root);
            List<T> outList = new LinkedList<T>();
            for (BSTNode<T> n : l) {
                outList.add(n.getData());
            }
            return  outList;
        }
    }

    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    @Override
    public int height() {
        if (root == null) {
            return 0;
        } else {
            return heightH(root);
        }
    }

    /**
     * THIS METHOD IS ONLY FOR TESTING PURPOSES.
     * DO NOT USE IT IN YOUR CODE
     * DO NOT CHANGE THIS METHOD
     *
     * @return the root of the tree
     */
    public BSTNode<T> getRoot() {
        return root;
    }

    /**
     * Assists addition of a node to the BST
     * @param startNode the indexing node
     * @param data the data being added to the BST
     * @return the root node
     */
    private BSTNode<T> addH(BSTNode<T> startNode, T data) {
        if (startNode == null) {
            size++;
            return new BSTNode<T>(data);
        } else if (data.compareTo(startNode.getData()) == 0) {
            return startNode;
        } else if (data.compareTo(startNode.getData()) < 0) {
            startNode.setLeft(addH(startNode.getLeft(), data));
        } else if (data.compareTo(startNode.getData()) > 0) {
            startNode.setRight(addH(startNode.getRight(), data));
        }
        return startNode;
    }

    /**
     * Assists grabbing a node from the BST
     * @param startNode the indexing node
     * @param data the data that is being looked for
     * @return node that matches the data
     */
    private T getH(BSTNode<T> startNode, T data) {
        if (startNode == null) {
            throw new NoSuchElementException("Data does not exist");
        } else if (data.compareTo(startNode.getData()) < 0) {
            return getH(startNode.getLeft(), data);
        } else if (data.compareTo(startNode.getData()) > 0) {
            return getH(startNode.getRight(), data);
        }
        return startNode.getData();
    }

    /**
     * Assists finding the height of the BST
     * @param startNode the indexing node
     * @return the height of the BST
     */
    private int heightH(BSTNode<T> startNode) {
        if (startNode.getLeft() != null && startNode.getRight() != null) {
            return 1 + Math.max(heightH(startNode.getLeft()),
                    heightH(startNode.getRight()));
        } else if (startNode.getLeft() != null
                    && startNode.getRight() == null) {
            return 1 + heightH(startNode.getLeft());
        } else if (startNode.getRight() != null
                    && startNode.getLeft() == null) {
            return 1 + heightH(startNode.getRight());
        } else {
            return 0;
        }
    }

    /**
     * Assists the preorder traversal method
     * @param list empty list to add data in
     * @param startNode the indexing node
     * @return the preordered list
     */
    private List<T> preorderH(List<T> list, BSTNode<T> startNode) {
        if (startNode != null) {
            list.add(startNode.getData());
            preorderH(list, startNode.getLeft());
            preorderH(list, startNode.getRight());
        }
        return list;
    }

    /**
     * Assists the postorder traversal method
     * @param list empty list to add data to
     * @param startNode the postordered list
     * @return the post ordered list
     */
    private List<T> postorderH(List<T> list, BSTNode<T> startNode) {
        if (startNode != null) {
            postorderH(list, startNode.getLeft());
            postorderH(list, startNode.getRight());
            list.add(startNode.getData());
        }
        return list;
    }

    /**
     * Assists the inorder traversal
     * @param list empty list to add data too
     * @param startNode the indexing node
     * @return inorder list
     */
    private List<T> inorderH(List<T> list, BSTNode<T> startNode) {
        if (startNode != null) {
            inorderH(list, startNode.getLeft());
            list.add(startNode.getData());
            inorderH(list, startNode.getRight());
        }
        return list;
    }

    /**
     * Assists the level order traversal
     * @param list empty list to add data too
     * @param startNode the indexing node
     * @return the list in level order
     */
    private LinkedList<BSTNode<T>> levelorderH(LinkedList<BSTNode<T>> list,
                                               BSTNode<T> startNode) {
        list.add(startNode);
        for (int i = 0; list.size() < size; i++) {
            if (list.get(i).getLeft() != null) {
                list.add(list.get(i).getLeft());
            }
            if (list.get(i).getRight() != null) {
                list.add(list.get(i).getRight());
            }
        }
        return list;
    }

    /**
     * Assist the remove of data from the BST
     * @param startNode the indexing node
     * @param data the data being removed
     * @return the root node
     */
    private BSTNode<T> removeH(BSTNode<T> startNode, T data) {
        if (startNode == null) {
            throw new NoSuchElementException("Data not found");
        } else if (data.compareTo(startNode.getData()) < 0) {
            startNode.setLeft(removeH(startNode.getLeft(), data));
        } else if (data.compareTo(startNode.getData()) > 0) {
            startNode.setRight(removeH(startNode.getRight(), data));
        } else {
            size--;
            if (startNode.getLeft() == null && startNode.getRight() == null) {
                data = startNode.getData();
                startNode = null;
            } else if (startNode.getRight() == null
                    && startNode.getLeft() != null) {
                data = startNode.getData();
                startNode = startNode.getLeft();
            } else if (startNode.getLeft() == null
                    && startNode.getRight() != null) {
                data = startNode.getData();
                startNode = startNode.getRight();
            } else {
                BSTNode<T> leftNode = startNode.getLeft();
                BSTNode<T> rightNode = startNode.getRight();
                if (startNode.getRight().getLeft() == null) {
                    startNode = startNode.getRight();
                } else {
                    startNode = removeHelper(startNode.getRight(),
                            startNode.getRight().getLeft());
                    if (startNode.getRight() != null && rightNode != null) {
                        rightNode.setLeft(startNode.getRight());
                    } else if (rightNode != null && rightNode != startNode) {
                        startNode.setRight(rightNode);
                    }
                }
                if (leftNode != null) {
                    startNode.setLeft(leftNode);
                }
            }
        }
        return startNode;
    }

    /**
     * Assists the removeH method when the
     * node being removed has two children
     * @param rightNode the indexing node of the parent
     * @param rightNodeLeft the indexing node of the child (of the parent)
     * @return the node that replaces the node being removed
     */
    private BSTNode<T> removeHelper(BSTNode<T> rightNode,
                                    BSTNode<T> rightNodeLeft) {
        if (rightNodeLeft.getLeft() == null) {
            BSTNode<T> newStartNode = rightNodeLeft;
            rightNode.setLeft(null);
            return newStartNode;
        } else {
            return removeHelper(rightNode.getLeft(), rightNodeLeft.getLeft());
        }
    }




}

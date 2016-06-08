import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.LinkedList;

/**
 * Your implementation of an AVL Tree.
 *
 * @author Sreeramamurthy Tripuramallu
 * @version 1.0
 */
public class AVL<T extends Comparable<? super T>> implements AVLInterface<T> {

    // Do not make any new instance variables.
    private AVLNode<T> root;
    private int size;

    /**
     * A no argument constructor that should initialize an empty AVL tree.
     */
    public AVL() {

    }

    /**
     * Initializes the AVL tree with the data in the Collection. The data
     * should be added in the same order it is in the Collection.
     *
     * @param data the data to add to the tree
     * @throws IllegalArgumentException if data or any element in data is null
     */
    public AVL(Collection<T> data) {
        if (data == null) {
            throw new IllegalArgumentException("Data is not valid");
        } else {
            for (T element : data) {
                if (element == null) {
                    throw new IllegalArgumentException("Datat is not valid");
                } else {
                    add(element);
                }
            }
        }
    }

    /**
     * Recalculates a node's balance factor
     * @param node the node that is getting its balance factor updated
     */
    private void resetBalanceFactor(AVLNode<T> node) {
        if (node != null) {
            node.setBalanceFactor(nullHeight(node.getLeft())
                    - nullHeight(node.getRight()));
        }
    }

    /**
     * Calculates the height of the a node
     * @param node the node that is getting its height calculated
     * @return the height of the node
     */
    private int nullHeight(AVLNode<T> node) {
        if (node == null) {
            return -1;
        } else {
            return node.getHeight();
        }
    }

    /**
     * Reset's the height of the node
     * @param node the node that is having its height recalculated
     */
    private void resetHeight(AVLNode<T> node) {
        if (node != null) {
            node.setHeight(1 + Math.max(nullHeight(node.getLeft()),
                    nullHeight(node.getRight())));
        }
    }
    @Override
    public void add(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data not valid");
        } else if (size < 1) {
            root = new AVLNode<T>(data);
            size++;
        } else {
            if (!this.contains(data)) {
                root = addH(root, data);
                size++;
            }
        }
    }

    /**
     * Recursive helper method for add method
     * @param node The starting node
     * @param data The data being added
     * @return The starting node with the data added to the tree
     */
    private AVLNode<T> addH(AVLNode<T> node, T data) {
        if (node == null) {
            node = new AVLNode<T>(data);
        } else if (data.compareTo(node.getData()) < 0) {
            node.setLeft(addH(node.getLeft(), data));
            resetBalanceFactor(node);
            node = runBalance(node);
        } else if (data.compareTo(node.getData()) > 0) {
            node.setRight(addH(node.getRight(), data));
            resetBalanceFactor(node);
            node = runBalance(node);
            if (node.getLeft() != null) {
                resetBalanceFactor(node.getLeft());
            }
            if (node.getRight() != null) {
                resetBalanceFactor(node.getRight());
            }
        }
        resetHeight(node);
        resetBalanceFactor(node);
        return node;
    }

    /**
     * Rotates the AVL tree around the left child
     * @param node the node that needs to get rotated
     * @return The node's left child
     */
    private AVLNode<T> rotateAroundLeftChild(AVLNode<T> node) {
        AVLNode<T> node1 = node.getLeft();
        node.setLeft(node1.getRight());
        node1.setRight(node);
        node.setHeight(Math.max(nullHeight(node.getLeft()),
                nullHeight(node.getRight())) + 1);
        node1.setHeight(Math.max(nullHeight(node1.getLeft()),
                nullHeight(node1)) + 1);
        if (node1.getLeft() != null) {
            resetBalanceFactor(node1.getLeft());
            resetHeight(node1.getLeft());
        }
        if (node1.getRight() != null) {
            resetBalanceFactor(node1.getRight());
            resetHeight(node1.getRight());
        }
        if (node.getLeft() != null) {
            resetBalanceFactor(node.getLeft());
            resetHeight(node.getLeft());
        }
        if (node.getRight() != null) {
            resetBalanceFactor(node.getRight());
            resetHeight(node.getRight());
        }
        resetBalanceFactor(node1);
        resetHeight(node1);
        resetHeight(node);
        resetBalanceFactor(node);
        return node1;
    }

    /**
     * Roates the node around the left child, and then around the left child
     * @param node the node that needs to be rotated
     * @return the updated node;
     */
    private AVLNode<T> doubleAroundLeftChild(AVLNode<T> node) {
        node.setLeft(rotateAroundRightChild(node.getLeft()));
        node = rotateAroundLeftChild(node);
        resetBalanceFactor(node);
        resetHeight(node);
        return node;
    }

    /**
     * Rotates the node around the right child
     * @param node1 the node that needs to be rotated
     * @return the right child of the node
     */
    private AVLNode<T> rotateAroundRightChild(AVLNode<T> node1) {
        AVLNode<T> node2 = node1.getRight();
        node1.setRight(node2.getLeft());
        node2.setLeft(node1);
        node1.setHeight(Math.max(nullHeight(node1.getLeft()),
                nullHeight(node1.getRight())) + 1);
        node2.setHeight(Math.max(nullHeight(node2.getRight()),
                nullHeight(node1)) + 1);
        if (node1.getLeft() != null) {
            resetBalanceFactor(node1.getLeft());
            resetHeight(node1.getLeft());
        }
        if (node1.getRight() != null) {
            resetBalanceFactor(node1.getRight());
            resetHeight(node1.getRight());
        }
        if (node2.getLeft() != null) {
            resetBalanceFactor(node2.getLeft());
            resetHeight(node2.getLeft());
        }
        if (node2.getRight() != null) {
            resetBalanceFactor(node2.getRight());
            resetHeight(node2.getRight());
        }
        resetBalanceFactor(node2);
        resetHeight(node2);
        resetHeight(node1);
        resetBalanceFactor(node1);
        return node2;
    }

    /**
     * Rotates the node around the left child, then around the right child
     * @param node the node that needs to be rotated
     * @return the updated node
     */
    private AVLNode<T> doubleAroundRightChild(AVLNode<T> node) {
        node.setRight(rotateAroundLeftChild(node.getRight()));
        node = rotateAroundRightChild(node);
        resetBalanceFactor(node);
        resetHeight(node);
        return node;
    }
    @Override
    public T remove(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data not valid");
        } else if (this.contains(data)) {
            AVLNode<T> temp = new AVLNode<T>(null);
            if (size == 1 && root.getData().equals(data)) {
                temp.setData(root.getData());
                clear();
            } else if (size > 1) {
                root = removeH(data, root, null, temp);
            }
            return temp.getData();
        } else {
            throw new NoSuchElementException("Data does not exist");
        }
    }

    /**
     * Recursive helper method for remove
     * @param data the data being removed
     * @param parent the parent node of the data
     * @param grandParent the grandparent node of the data
     * @param temp temporary node
     * @return the node after the node has been removed
     */
    private AVLNode<T> removeH(T data, AVLNode<T> parent,
                               AVLNode<T> grandParent, AVLNode<T> temp) {
        if (parent == null) {
            throw new NoSuchElementException("Data does not exist");
        }
        if (data.compareTo(parent.getData()) < 0) {
            parent.setLeft(removeH(data, parent.getLeft(), parent, temp));
            resetHeight(parent);
            resetBalanceFactor(parent);
            parent = runBalance(parent);
        } else if (data.compareTo(parent.getData()) > 0) {
            parent.setRight(removeH(data, parent.getRight(), parent, temp));
            resetHeight(parent);
            resetBalanceFactor(parent);
            parent = runBalance(parent);
        } else {
            size--;
            temp.setData(parent.getData());
            if (parent.getLeft() != null && parent.getRight() != null) {
                parent.setData(twoChildren(parent));
            } else if (parent.getLeft() == null) {
                parent = parent.getRight();
            } else {
                parent = parent.getLeft();
            }
            resetBalanceFactor(parent);
            resetHeight(parent);
        }
        resetBalanceFactor(parent);
        resetHeight(parent);
        return parent;
    }

    /**
     * Balances the tree by performing the
     * appropriate balancing rotations
     * @param node the node that needs to be balanced
     * @return the balanced node (with the balanced tree)
     */
    private AVLNode<T> runBalance(AVLNode<T> node) {
        if (node.getBalanceFactor() > 1) {
            if (node.getLeft() != null
                    && node.getLeft().getBalanceFactor() >= 0) {
                node = rotateAroundLeftChild(node);
            } else {
                node = doubleAroundLeftChild(node);
            }
        } else if (node.getBalanceFactor() < -1) {
            if (node.getRight() != null
                    && node.getRight().getBalanceFactor() <= 0) {
                node = rotateAroundRightChild(node);
            } else {
                node = doubleAroundRightChild(node);
            }
        }
        resetBalanceFactor(node);
        resetHeight(node);
        return node;
    }

    /**
     * Special case for the remove method when a node has two children
     * @param node the node with two children
     * @return the data
     */
    private T twoChildren(AVLNode<T> node) {
        AVLNode<T> p = node.getLeft();
        AVLNode<T> pP = null;
        while (p.getRight() != null) {
            pP = p;
            p = p.getRight();
        }
        T r = p.getData();
        if (pP == null) {
            node.setLeft(p.getLeft());
        } else {
            pP.setRight(p.getLeft());
        }
        resetBalanceFactor(node);
        resetHeight(node);
        resetBalanceFactor(pP);
        resetHeight(pP);
        return r;
    }


    @Override
    public T get(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Dtat not valid");
        } else {
            return getH(root, data);
        }
    }

    /**
     * Recursive helper method for get
     * @param node the root of the AVL Tree
     * @param data the data that is being get
     * @return the node with the matching data
     */
    private T getH(AVLNode<T> node, T data) {
        if (node == null) {
            throw new NoSuchElementException("Data does not exist");
        } else if (data.compareTo(node.getData()) < 0) {
            return getH(node.getLeft(), data);
        } else if (data.compareTo(node.getData()) > 0) {
            return getH(node.getRight(), data);
        } else {
            return node.getData();
        }
    }
    @Override
    public boolean contains(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data does not exist");
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

    /**
     * Recursive helper method for preorder traversal
     * @param list the list to add the data
     * @param node the root of the AVL Tree
     * @return the list with the preordered data
     */
    private List<T> preorderH(List<T> list, AVLNode<T> node) {
        if (node != null) {
            list.add(node.getData());
            preorderH(list, node.getLeft());
            preorderH(list, node.getRight());
        }
        return list;
    }

    @Override
    public List<T> postorder() {
        return postorderH(new LinkedList<T>(), root);
    }

    /**
     * Recursive helper method for postorder traversal
     * @param list the list to add the data
     * @param node the root of the AVL Tree
     * @return the list with the postordered data
     */
    private List<T> postorderH(List<T> list, AVLNode<T> node) {
        if (node != null) {
            postorderH(list, node.getLeft());
            postorderH(list, node.getRight());
            list.add(node.getData());
        }
        return list;
    }

    @Override
    public List<T> inorder() {
        return inorderH(new LinkedList<T>(), root);
    }

    /**
     * Recursive helper method for inorder traversal
     * @param list the list to add the data
     * @param node the root of the AVL Tree
     * @return the list of the inorder data
     */
    private List<T> inorderH(List<T> list, AVLNode<T> node) {
        if (node != null) {
            inorderH(list, node.getLeft());
            list.add(node.getData());
            inorderH(list, node.getRight());
        }
        return list;
    }

    @Override
    public List<T> levelorder() {
        if (root == null) {
            return new LinkedList<T>();
        } else {
            LinkedList<AVLNode<T>> list = levelorderH(
                    new LinkedList<AVLNode<T>>(), root);
            List<T> out = new LinkedList<T>();
            for (AVLNode<T> element : list) {
                out.add(element.getData());
            }
            return out;
        }
    }

    /**
     * Recursive helper method for levelorder traversal
     * @param list the list to ad the data
     * @param node the root of the AVL Tree
     * @return the list with the levelorder data
     */
    private LinkedList<AVLNode<T>> levelorderH(
            LinkedList<AVLNode<T>> list, AVLNode<T> node) {
        list.add(node);
        int i = 0;
        while (list.size() < size) {
            if (list.get(i).getLeft() != null) {
                list.add(list.get(i).getLeft());
            }
            if (list.get(i).getRight() != null) {
                list.add(list.get(i).getRight());
            }
            i++;
        }
        return list;
    }

    @Override
    public void clear() {
        size = 0;
        root = null;
    }

    @Override
    public int height() {
        if (root == null) {
            return -1;
        } else {
            return root.getHeight();
        }
    }

    @Override
    public int depth(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Datat not valid");
        } else {
            return depthH(root, data);
        }
    }

    /**
     * Recursive helper method to find depth of a node
     * @param node the root of the AVL Tree
     * @param data the data to find in the tree
     * @return the depth of the data in the AVL Tree
     */
    private int depthH(AVLNode<T> node, T data) {
        if (node == null) {
            throw new NoSuchElementException("Data does not exist");
        } else if (data.compareTo(node.getData()) < 0) {
            return depthH(node.getLeft(), data) + 1;
        } else if (data.compareTo(node.getData()) > 0) {
            return depthH(node.getRight(), data) + 1;
        } else {
            return 1;
        }
    }

    /**
     * THIS METHOD IS ONLY FOR TESTING PURPOSES.
     * DO NOT USE IT IN YOUR CODE
     * DO NOT CHANGE THIS METHOD
     *
     * @return the root of the tree
     */
    public AVLNode<T> getRoot() {
        return root;
    }
}

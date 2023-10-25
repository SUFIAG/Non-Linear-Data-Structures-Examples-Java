package trees.avlTree;

import trees.binarySearchTree.implementation.BinarySearchTree;

/**
 * This class extends the BinarySearchTree by adding the rotate operations.
 * Rotation will change the balance of a search tree while preserving the
 * search tree property.
 * Used as a common base class for self‐balancing trees.
 */

public class BinarySearchTreeWithRotate<E extends Comparable<E>> extends BinarySearchTree<E> {
    // Methods

    /**
     * Method to perform a right rotation.
     *
     * @param root The root of the binary tree to be rotated
     * @return The new root of the rotated tree
     * @pre root is the root of a binary search tree.
     * @post root.right is the root of a binary search tree,
     * root.right.right is raised one level,
     * root.right.left does not change levels,
     * root.left is lowered one level,
     * the new root is returned.
     */
    protected Node<E> rotateRight(Node<E> root) {
        Node<E> temp = root.right;
        root.left = temp.right;
        temp.right = root;
        return temp;
    }

    /**
     * Method to perform a left rotation.
     *
     * @param root The root of the binary tree to be rotated
     * @return The new root of the rotated tree
     * @pre root is the root of a binary search tree.
     * @post root.right is the root of a binary search tree,
     * root.right.right is raised one level,
     * root.right.left does not change levels,
     * root.left is lowered one level,
     * the new root is returned.
     */
    protected Node<E> rotateLeft(Node<E> root) {
        Node<E> temp = root.left;
        root.left = temp.right;
        temp.left = root;
        return temp;
    }
}

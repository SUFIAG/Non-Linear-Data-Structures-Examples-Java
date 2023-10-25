/*   Created by IntelliJ IDEA.
 *   Author: Devansh Shukla (D-Coder135)
 *   Date: 28/09/21
 *   Time: 9:08 PM
 *   File: Node.java
 */

package trees.binaryTree.implementation;

import java.io.Serializable;

/**
 * Class for a binary tree that stores type E objects.
 */
public class BinaryTree<E> implements Serializable {

    /**
     * Class to encapsulate a tree node.
     */

    public static class Node<E> implements Serializable {
        // Data Fields

        /**
         * The information stored in this node.
         */
        public E data;

        /**
         * Reference to the left child.
         */
        public Node<E> left;

        /**
         * Reference to the right child.
         */
        public Node<E> right;

        // Constructors

        /**
         * Construct a node with given data and no children.
         *
         * @param data The data to store in this node.
         */
        public Node(E data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }

        // Methods

        /**
         * Return a string representation of the node.
         *
         * @return A string representation of the data fields.
         */
        public String toString() {
            return data.toString();
        }
    }

    // Data Fields

    /**
     * The root of the binary tree
     */
    public Node<E> root;

    // Constructors

    public BinaryTree() {
        root = null;
    }

    protected BinaryTree(Node<E> root) {
        this.root = root;
    }

    /**
     * Constructs a new binary tree with data in its root, leftTree as its left subtree and rightTree as its right subtree.
     */
    public BinaryTree(E data, BinaryTree<E> leftTree, BinaryTree<E> rightTree) {
        root = new Node<>(data);
        if (leftTree != null) {
            root.left = leftTree.root;
        } else {
            root.left = null;
        }
        if (rightTree != null) {
            root.right = rightTree.root;
        } else {
            root.right = null;
        }
    }

    // Methods

    /**
     * Return the left subtree.
     *
     * @return The left subtree or null if either the root ot the left subtree is null.
     */
    public BinaryTree<E> getLeftSubtree() {
        if (root != null && root.left != null) {
            return new BinaryTree<>(root.left);
        } else {
            return null;
        }
    }

    /**
     * Return the right subtree.
     *
     * @return The right subtree or null if either the root ot the right subtree is null.
     */
    public BinaryTree<E> getRightSubtree() {
        if (root != null && root.right != null) {
            return new BinaryTree<>(root.right);
        } else {
            return null;
        }
    }

    /**
     * Determine whether this tree is a leaf.
     *
     * @return true if the root has no children.
     */
    public boolean isLeaf() {
        return (root.left == null && root.right == null);
    }

    /**
     * Generates a string representation of the BinaryTree
     */
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        toString(root, 1, stringBuilder);
        return stringBuilder.toString();
    }

    /**
     * Converts a sub-tree to a string.
     * Performs a preorder traversal.
     *
     * @param node  The local root
     * @param depth The depth
     * @param sb    The StringBuilder to save the output
     */
    private void toString(Node<E> node, int depth, StringBuilder sb) {
        sb.append(" ".repeat(Math.max(0, depth)));
        if (node == null) {
            sb.append("null\n");
        } else {
            sb.append(node.toString());
            sb.append("\n");
            toString(node.left, depth + 1, sb);
            toString(node.right, depth + 1, sb);
        }
    }
}

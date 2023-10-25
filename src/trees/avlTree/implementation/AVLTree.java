package trees.avlTree.implementation;

import trees.avlTree.BinarySearchTreeWithRotate;
import trees.avlTree.avlNode.AVLNode;

public class AVLTree<E extends Comparable<E>> extends BinarySearchTreeWithRotate<E> {

    public boolean increase;

    /**
     * add starter method.
     *
     * @param item The item being inserted.
     * @return true if the object is inserted; false
     * if the object already exists in the tree
     * @throws ClassCastException if item is not Comparable
     * @pre the item to insert implements the Comparable interface.
     */
    @Override
    public boolean add(E item) {
        increase = false;
        root = add((AVLNode<E>) root, item);
        return addReturn;
    }

    /**
     * Recursive add method. Inserts the given object into the tree.
     *
     * @param localRoot The local root of the subtree
     * @param item      The object to be inserted
     * @return The new local root of the subtree with the item inserted
     * @post addReturn is set true if the item is inserted,
     * false if the item is already in the tree.
     */
    private AVLNode<E> add(AVLNode<E> localRoot, E item) {
        if (localRoot == null) {
            addReturn = true;
            increase = true;
            return new AVLNode<>(item);
        }

        if (item.compareTo(localRoot.data) == 0) {
            // Item is already in the tree.
            increase = false;
            addReturn = false;
            return localRoot;
        } else if (item.compareTo(localRoot.data) < 0) {
            // item < data
            localRoot.left = add((AVLNode<E>) localRoot.left, item);
            if (increase) {
                decerementBalance(localRoot);
                if (localRoot.balance < AVLNode.LEFT_HEAVY) {
                    increase = false;
                    return rebalanceLeft(localRoot);
                }
            }
            return localRoot;
        } else {
            // item > data
            localRoot.right = add((AVLNode<E>) localRoot.right, item);
            if (increase) {
                decerementBalance(localRoot);
                if (localRoot.balance < AVLNode.RIGHT_HEAVY) {
                    increase = false;
                    return rebalanceRight(localRoot);
                }
            }
            return localRoot;
        }
    }

    private void decerementBalance(AVLNode<E> node) {
        node.balance--;
        if (node.balance == AVLNode.BALANCED) {
            increase = false;
        }

    }

    /**
     * Method to rebalance left.
     *
     * @param localRoot Root of the AVL subtree that needs rebalancing
     * @return a new localRoot
     * @pre localRoot is the root of an AVL subtree that is critically left‐heavy.
     * @post Balance is restored.
     */

    private AVLNode<E> rebalanceLeft(AVLNode<E> localRoot) {
        AVLNode<E> leftChild = (AVLNode<E>) localRoot.left;
        if (leftChild.balance > AVLNode.BALANCED) {
            AVLNode<E> leftRightChild = (AVLNode<E>) leftChild.right;
            if (leftRightChild.balance < AVLNode.BALANCED) {
                leftChild.balance = AVLNode.BALANCED;
                leftRightChild.balance = AVLNode.BALANCED;
                localRoot.balance = AVLNode.RIGHT_HEAVY;
            } else {
                leftChild.balance = AVLNode.LEFT_HEAVY;
                leftRightChild.balance = AVLNode.BALANCED;
                localRoot.balance = AVLNode.BALANCED;
            }
            localRoot.left = rotateLeft(leftChild);
        } else {
            leftChild.balance = AVLNode.BALANCED;
            localRoot.balance = AVLNode.BALANCED;
        }
        return (AVLNode<E>) rotateRight(localRoot);
    }

    private AVLNode<E> rebalanceRight(AVLNode<E> localRoot) {
        AVLNode<E> rightChild = (AVLNode<E>) localRoot.right;
        if (rightChild.balance > AVLNode.BALANCED) {
            AVLNode<E> rightLeftChild = (AVLNode<E>) rightChild.left;
            if (rightLeftChild.balance < AVLNode.BALANCED) {
                rightChild.balance = AVLNode.BALANCED;
                rightLeftChild.balance = AVLNode.BALANCED;
                localRoot.balance = AVLNode.RIGHT_HEAVY;
            } else {
                rightChild.balance = AVLNode.LEFT_HEAVY;
                rightLeftChild.balance = AVLNode.BALANCED;
                localRoot.balance = AVLNode.BALANCED;
            }
            localRoot.right = rotateLeft(rightChild);
        } else {
            rightChild.balance = AVLNode.BALANCED;
            localRoot.balance = AVLNode.BALANCED;
        }
        return (AVLNode<E>) rotateRight(localRoot);
    }
}

package com.cesar31.system.structures;

/**
 *
 * @author cesar31
 * @param <T>
 */
public class AVLNode<T> {

    private T data;
    private String id;
    private AVLNode<T> left;
    private AVLNode<T> right;

    public AVLNode(T data, String id) {
        this.data = data;
        this.id = id;
    }

    public int getFactor() {
        int hRight = (this.right == null) ? 0 : 1 + this.right.getHeight();
        int hLeft = (this.left == null) ? 0 : 1 + this.left.getHeight();
        return hRight - hLeft;
    }

    public int getHeight() {
        int hRight = (this.right == null) ? 0 : 1 + this.right.getHeight();
        int hLeft = (this.left == null) ? 0 : 1 + this.left.getHeight();

        return Math.max(hRight, hLeft);
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public AVLNode<T> getLeft() {
        return left;
    }

    public void setLeft(AVLNode<T> left) {
        this.left = left;
    }

    public AVLNode<T> getRight() {
        return right;
    }

    public void setRight(AVLNode<T> right) {
        this.right = right;
    }
}

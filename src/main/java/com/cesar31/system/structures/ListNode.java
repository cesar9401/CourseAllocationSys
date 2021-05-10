package com.cesar31.system.structures;

/**
 *
 * @author cesar31
 * @param <T>
 */
public class ListNode<T> {

    private ListNode<T> next;

    /* Para listas doblemente enlazadas */
    private ListNode<T> previous;

    private T data;
    private String id;

    public ListNode(T data, String id) {
        this.data = data;
        this.id = id;
    }

    public ListNode<T> getNext() {
        return next;
    }

    public void setNext(ListNode<T> next) {
        this.next = next;
    }

    public ListNode<T> getPrevious() {
        return previous;
    }

    public void setPrevious(ListNode<T> previous) {
        this.previous = previous;
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
}

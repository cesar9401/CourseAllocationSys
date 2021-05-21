package com.cesar31.system.structures;

/**
 *
 * @author cesar31
 * @param <T>
 */
public class DoublyLinkdList<T> {

    private ListNode<T> top;
    private ListNode<T> bottom;
    private int size;

    public DoublyLinkdList() {
        this.size = 0;
    }

    public boolean insert(String id, T data) {
        if (this.getNode(id) == null) {
            // System.out.println("Insert doubly: " + data.toString());
            ListNode<T> node = new ListNode<>(data, id);
            insert(node);
            return true;
        }
        return false;
    }

    private void insert(ListNode<T> node) {
        if (this.top == null) {
            this.top = node;
            node.setNext(node);
            node.setPrevious(node);
            this.bottom = node;
        } else {
            this.bottom.setNext(node);

            node.setPrevious(this.bottom);
            node.setNext(this.top);

            this.top.setPrevious(node);
            this.bottom = node;
        }
        this.size++;
    }

    public ListNode<T> getNode(String id) {
        if (this.top != null) {
            ListNode<T> aux = this.top;
            do {
                if (aux.getId().equals(id)) {
                    return aux;
                }
                aux = aux.getNext();
            } while (aux != this.top);
        }

        return null;
    }

    public void delete(String id) {
        if (this.top != null) {
            if (this.top.getId().equals(id)) {
                if (this.top == this.bottom) {
                    this.top = null;
                    this.bottom = null;
                } else {
                    this.top = this.top.getNext();
                    this.top.setPrevious(this.bottom);
                    this.bottom.setNext(this.top);
                }
                this.size--;
                // System.out.println("del: " + id);
            } else {
                ListNode<T> aux = this.top;
                while (aux.getNext() != this.top) {
                    if (aux.getNext().getId().equals(id)) {
                        if (aux.getNext() == this.bottom) {
                            this.bottom = aux;
                        }

                        ListNode<T> del = aux.getNext();
                        del.getNext().setPrevious(aux);
                        aux.setNext(del.getNext());

                        this.size--;
                        // System.out.println("del: " + id);
                        break;
                    }
                    aux = aux.getNext();
                }
            }
        }
    }

    public ListNode<T> getTop() {
        return top;
    }

    public int getSize() {
        return size;
    }
}

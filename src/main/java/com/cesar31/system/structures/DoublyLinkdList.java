package com.cesar31.system.structures;

/**
 *
 * @author cesar31
 * @param <T>
 */
public class DoublyLinkdList<T> {

    private ListNode<T> top;
    private ListNode<T> bottom;

    public DoublyLinkdList() {
    }

    public void insert(T data, String id) {
        ListNode<T> node = new ListNode<>(data, id);
        insert(node);
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

                        break;
                    }
                    aux = aux.getNext();
                }
            }
        }
    }
}

package com.cesar31.system.structures;

/**
 *
 * @author cesar31
 * @param <T>
 */
public class LinkdList<T> {

    private ListNode<T> root;

    public LinkdList() {
    }

    public void insert(String id, T data) {
        if (this.getNode(id) == null) {
            // System.out.println("insert simple: " + data.toString());
            ListNode<T> tmp = new ListNode<>(data, id);
            insert(tmp);
        } else {
            System.out.println("Ya existe: " + id);
        }
    }

    /**
     * Insertar
     *
     * @param node
     */
    private void insert(ListNode<T> node) {
        if (this.root == null) {
            this.root = node;
        } else {
            /* node.id < root.id */
            if (node.getId().compareTo(this.root.getId()) < 0) {
                node.setNext(this.root);
                this.root = node;
            } else {
                ListNode<T> aux = this.root;
                boolean inserted = false;
                while (aux.getNext() != null) {
                    /* node.id < next.id */
                    if (node.getId().compareTo(aux.getNext().getId()) < 0) {
                        ListNode<T> tmp = aux.getNext();
                        aux.setNext(node);
                        node.setNext(tmp);
                        inserted = true;
                    }

                    aux = aux.getNext();
                }

                /* insertar al final */
                if (!inserted) {
                    aux.setNext(node);
                }
            }
        }
    }

    /**
     * Eliminar
     *
     * @param id
     */
    public void deleteNode(String id) {
        if (this.root != null) {
            if (this.root.getId().equals(id)) {
                //ListNode<T> del = this.root;
                root = root.getNext();
            } else {
                ListNode<T> aux = this.root;
                while (aux.getNext() != null) {
                    if (aux.getNext().getId().equals(id)) {
                        ListNode<T> del = aux.getNext();
                        aux.setNext(del.getNext());
                        break;
                    }
                    aux = aux.getNext();
                }
            }
        } else {
            System.out.println("Listado vacio");
        }
    }

    /**
     * Buscar
     *
     * @param id
     * @return
     */
    public ListNode<T> getNode(String id) {
        ListNode<T> aux = this.root;
        while (aux != null) {
            if (id.compareTo(aux.getId()) == 0) {
                return aux;
            }
            aux = aux.getNext();
        }

        return null;
    }

    /**
     * Recorrer
     */
    public void travel() {
        if (this.root != null) {
            ListNode<T> aux = this.root;
            while (aux != null) {
                System.out.println("Id: " + aux.getId());
                aux = aux.getNext();
            }
        }
    }
}

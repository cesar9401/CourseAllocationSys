package com.cesar31.system.structures;

/**
 *
 * @author cesar31
 * @param <T>
 */
public class BTree<T> {

    private BNode<T> root;
    private int mK;
    private int height;
    private Class clazz;

    public String toDot() {
        StringBuilder b = new StringBuilder();
        b.append("digraph g { \n node [shape=record];\n");
        b.append(root.toDot());
        b.append("}");
        return b.toString();
    }

    public BTree() {
        this.mK = 2;
        this.height = 0;
    }

    public BTree(Class clazz) {
        this();
        this.clazz = clazz;
    }

    public void insert(Sortable key, T data) {
        // System.out.println("insert Btree: " + data.toString());
        if (this.height == 0) {
            this.root = new BNode<>(this.mK, key, data, this.clazz);
            this.height = 1;
            return;
        }

        Split<T> splitted = insert(this.root, key, data, 1);

        if (splitted == null) {
        } else {

            BNode<T> ptr = this.root;

            this.root = new BNode<>(this.mK, splitted.getLlave(), splitted.getData(), this.clazz);
            this.root.pointers[0] = ptr;
            this.root.pointers[1] = splitted.getPointer();
            this.height++;
        }
    }

    protected Split insert(BNode<T> node, Sortable key, T data, int level) {

        Split<T> splitted = null;
        BNode<T> ptr = null;

        int i = 0;
        while ((i < node.mB) && (key.mayorQue(node.keys[i]))) {
            i++;
        }

        if ((i < node.mB) && key.igualA(node.keys[i])) {
            node.data[i] = data;
            System.out.println("Insertando repetido: " + data.toString());
            return null;
        }

        if (level < this.height) {

            splitted = insert(node.pointers[i], key, data, level + 1);

            if (splitted == null) {
                return null;
            } else {
                key = splitted.key;
                data = splitted.data;
                ptr = splitted.pointer;
            }
        }

        i = node.mB - 1;
        while ((i >= 0)
                && (node.keys[i] == null || key.menorQue(node.keys[i]))) {
            node.keys[i + 1] = node.keys[i];
            node.data[i + 1] = node.data[i];
            node.pointers[i + 2] = node.pointers[i + 1];
            i--;
        }

        node.keys[i + 1] = key;
        node.data[i + 1] = data;
        if (splitted != null) {
            node.pointers[i + 2] = splitted.pointer;
        }
        node.mB++;

        if (node.mB > 2 * mK) {

            BNode<T> newnode = new BNode<>(this.mK, this.clazz);
            newnode.pointers[this.mK] = node.pointers[node.mB];
            node.pointers[node.mB] = null;
            node.mB = this.mK + 1;
            for (i = 0; i < this.mK; i++) {
                newnode.keys[i] = node.keys[i + node.mB];
                node.keys[i + node.mB] = null;
                newnode.data[i] = node.data[i + node.mB];
                node.data[i + node.mB] = null;
                newnode.pointers[i] = node.pointers[i + node.mB];
                node.pointers[i + node.mB] = null;
            }
            node.mB--;

            splitted = new Split(newnode, node.keys[node.mB], node.data[node.mB]);
            node.keys[node.mB] = null;
            node.data[node.mB] = null;
            newnode.mB = this.mK;
            node.mB = this.mK;

            return splitted;
        }

        return null;
    }

    public T get(Sortable key) {
        return get(key, root);
    }

    public T get(Sortable key, BNode<T> node) {

        if ((node == null) || (node.mB < 1)) {
            System.err.println("error");
            return null;
        }

        if (key.menorQue(node.keys[0])) {
            return get(key, node.pointers[0]);
        }

        if (key.mayorQue(node.keys[node.mB - 1])) {
            return get(key, node.pointers[node.mB]);
        }

        int i = 0;
        while ((i < node.mB - 1) && (key.mayorQue(node.keys[i]))) {
            i++;
        }

        if (key.igualA(node.keys[i])) {
            return node.data[i];
        }

        return get(key, node.pointers[i]);

    }

    public int getHeight() {
        return height;
    }

    public BNode<T> getRoot() {
        return root;
    }
}

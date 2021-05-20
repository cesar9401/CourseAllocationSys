package com.cesar31.system.structures;

import java.lang.reflect.Array;

/**
 *
 * @author cesar31
 */
public class BNode<T> {

    int mK;
    int mB;
    Sortable[] keys;
    T[] data;
    BNode<T>[] pointers;

    // private static int numeroDeNodo = 1;
    public String getDotName() {
        return "Nodo" + this.hashCode();
    }

    public String toDot() {

        StringBuilder b = new StringBuilder();

        b.append(getDotName());
        b.append("[label=\"<P0>");
        for (int i = 0; i < mB; i++) {
            b.append("|").append(keys[i].getKey());
            b.append("|<P").append(i + 1).append(">");
        }

        b.append("\"];\n");

        for (int i = 0; i <= mB; i++) {
            if (pointers[i] != null) {
                b.append(pointers[i].toDot());
                b.append(getDotName()).append(":P").append(i).append(" -> ").append(pointers[i].getDotName()).append(";\n");
            }
        }

        return b.toString();
    }

    public BNode(int pK, Class clazz) {
        this.mK = pK;
        mB = 0;
        keys = new Sortable[2 * pK + 1];
        data = (T[]) Array.newInstance(clazz, 2 * pK + 1);
        pointers = new BNode[(2 * pK) + 2];
    }

    public BNode(int pK, Sortable pLlave, T pDato, Class clazz) {
        this(pK, clazz);
        mB = 1;
        keys[0] = pLlave;
        data[0] = pDato;
    }

    public void setK(int mK) {
        this.mK = mK;
    }

    public int getK() {
        return mK;
    }

    public int getmB() {
        return mB;
    }

    public Sortable[] getKeys() {
        return keys;
    }

    public T[] getData() {
        return data;
    }

    public BNode<T>[] getPointers() {
        return pointers;
    }
    
    
}

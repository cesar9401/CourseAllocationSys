package com.cesar31.system.structures;

/**
 *
 * @author cesar31
 */
public class Split<T> {

    BNode<T> pointer;
    Sortable key;
    T data;

    public Split(BNode<T> pPuntero, Sortable pLlave, T pDato) {
        this.pointer = pPuntero;
        this.key = pLlave;
        this.data = pDato;
    }

    public BNode<T> getPointer() {
        return pointer;
    }

    public void setPointer(BNode<T> pointer) {
        this.pointer = pointer;
    }

    public void setLlave(Sortable mLlave) {
        this.key = mLlave;
    }

    public Sortable getLlave() {
        return key;
    }

    public Sortable getKey() {
        return key;
    }

    public void setKey(Sortable key) {
        this.key = key;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}

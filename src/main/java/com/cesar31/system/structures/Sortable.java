package com.cesar31.system.structures;

/**
 *
 * @author cesar31
 */
public class Sortable {

    private String key;

    public Sortable(String key) {
        this.key = key;
    }

    public boolean igualA(Sortable pObjeto) {
        return key.equals(pObjeto.getKey());
    }

    public boolean menorQue(Sortable pObjeto) {
        return key.compareTo((String) pObjeto.getKey()) < 0;
    }

    public boolean mayorQue(Sortable pObjeto) {
        return key.compareTo((String) pObjeto.getKey()) > 0;
    }

    public boolean menorOIgualQue(Sortable pObjeto) {
        return key.compareTo((String) pObjeto.getKey()) <= 0;
    }

    public boolean mayorOIgualQue(Sortable pObjeto) {
        return key.compareTo((String) pObjeto.getKey()) >= 0;
    }

    public String getKey() {
        return key;
    }
}

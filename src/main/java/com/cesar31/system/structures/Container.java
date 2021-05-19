package com.cesar31.system.structures;

/**
 *
 * @author cesar31
 * @param <T>
 */
public class Container<T> {

    private T data;
    private String key;

    /* Atrubutos para rehashing */
    private boolean deleted;
    private boolean marked;

    public Container() {
        this.deleted = false;
        this.marked = false;
    }

    public Container(String key, T data) {
        this();
        this.key = key;
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public boolean isMarked() {
        return marked;
    }

    public void setMarked(boolean marked) {
        this.marked = marked;
    }

    @Override
    public String toString() {
        return "Container{" + "data=" + data + ", key=" + key + ", deleted=" + deleted + ", marked=" + marked + '}';
    }
}

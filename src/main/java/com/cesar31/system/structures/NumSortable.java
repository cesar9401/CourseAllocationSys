
package com.cesar31.system.structures;

/**
 *
 * @author cesar31
 */
public class NumSortable extends Sortable {

    public NumSortable(String key) {
        super(key);
    }

    @Override
    public boolean igualA(Sortable s) {
        return Integer.valueOf(this.getKey()).compareTo(Integer.valueOf(s.getKey())) == 0;
    }

    public boolean menorQue(Sortable s) {
        return Integer.valueOf(this.getKey()).compareTo(Integer.valueOf(s.getKey())) < 0;
    }

    public boolean mayorQue(Sortable s) {
        return Integer.valueOf(this.getKey()).compareTo(Integer.valueOf(s.getKey())) > 0;
    }

    public boolean menorOIgualQue(Sortable s) {
        return Integer.valueOf(this.getKey()).compareTo(Integer.valueOf(s.getKey())) <= 0;
    }

    public boolean mayorOIgualQue(Sortable s) {
        return Integer.valueOf(this.getKey()).compareTo(Integer.valueOf(s.getKey())) >= 0;
    }

    public String getKey() {
        return super.getKey();
    }
}

package com.cesar31.system;

import com.cesar31.system.structures.BTree;
import com.cesar31.system.structures.NumSortable;
import com.cesar31.system.structures.Sortable;
import java.util.LinkedList;

/**
 *
 * @author cesar31
 */
public class Test {

    public static void main(String[] args) {
        BTree<Integer> treeB = new BTree<>(Integer.class);
        LinkedList<Integer> nums = new LinkedList<>();
        
        while(nums.size() < 88) {
            int random = (int) (Math.random() * 200 + 1);
            if(!nums.contains(random)) {
                nums.add(random);
            }
        }
        
        for (Integer n : nums) {
            treeB.insert(new NumSortable(n.toString()), n);
        }
        
        String dot = treeB.toDot();
        System.out.println(dot);
        
    }
}

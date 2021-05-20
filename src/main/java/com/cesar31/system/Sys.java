package com.cesar31.system;

import com.cesar31.system.control.FileControl;
import com.cesar31.system.control.MainController;
import com.cesar31.system.model.Student;
import com.cesar31.system.structures.BTree;
import com.cesar31.system.structures.HashTable;
import com.cesar31.system.structures.Sortable;
import java.io.IOException;

/**
 *
 * @author cesar31
 */
public class Sys {

    public static void main(String[] args) {
        MainController control = new MainController();
        control.initView();
    }

    public static void testHahsTable() {
        int min = 1900_00000;
        int max = 2022_00000;
        int range = max - min + 1;
        String save = "0";

        HashTable<Student> table = new HashTable();
        Student rem = null;
        for (int i = 0; i < 10000; i++) {
            Integer random = (int) (Math.random() * range + min);
            if (i == 2) {
                save = String.valueOf(random);
            }

            System.out.println(random);
            table.put(String.valueOf(random), new Student(String.valueOf(random), "Name", "Address"));

            if (i == 1500) {
                rem = table.remove(save);
            }
        }

        if (rem != null) {
            System.out.println(rem);
        }

        System.out.println("save: " + save);
        if (table.get(save) != null) {
            System.out.println(table.get(save));
        }
        System.out.println("Datos insertados: " + table.getInserted());
        System.out.println("Size: " + table.getSize());
    }

    public static void testBTree() {
        FileControl control = new FileControl();
        BTree<Student> tree = new BTree(Student.class);
        int min = 1900_00000;
        int max = 2022_00000;
        int range = max - min + 1;

        for (int i = 0; i < 50; i++) {
            Integer random = (int) (Math.random() * range + min);
            tree.insert(new Sortable(String.valueOf(random)), new Student(String.valueOf(random), "name", "address"));
        }

        String content = tree.toDot();
        control.writeDotFile("bTree.dot", content);
        try {
            control.execComand("dot -Tpng bTree.dot -o bTree.png");
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
        }
    }

    /**
     * Leer archivo
     *
     * @param path
     * @return
     */
    public boolean readData(String path) {
        
        return true;
    }
}

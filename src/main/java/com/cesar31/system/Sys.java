package com.cesar31.system;

import com.cesar31.system.structures.LinkdList;

/**
 *
 * @author cesar31
 */
public class Sys {

    public static void main(String[] args) {
        LinkdList<String> list = new LinkdList<>();
        list.insert("dato4", "5");
        list.insert("dato4", "10");
        list.insert("dato4", "11");

        list.insert("dato2", "2");
        list.insert("dato3", "3");
        list.insert("dato4", "4");
        list.insert("dato1", "1");

        list.travel();

        System.out.println(list.getNode("2").getData());

        list.deleteNode("11");

        list.travel();

    }
}

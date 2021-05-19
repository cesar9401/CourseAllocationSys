/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cesar31.system.parser;

import com.cesar31.system.control.FileControl;
import java.io.StringReader;

/**
 *
 * @author cesar31
 */
public class TestParser {

    public static void main(String[] args) {
        String path = "parser/example.txt";
        FileControl control = new FileControl();
        String source = control.readData(path);
        System.out.println(source);
        
        CourseLex lex = new CourseLex(new StringReader(source));
        CourseParser parser = new CourseParser(lex);
        try {
            parser.parse();
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
        }
    }
}

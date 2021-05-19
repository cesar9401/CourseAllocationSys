package com.cesar31.system.parser;

import com.cesar31.system.control.FileControl;
import com.cesar31.system.control.Structure;
import com.cesar31.system.model.*;
import com.cesar31.system.structures.Sortable;
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
        //System.out.println(source);

        CourseLex lex = new CourseLex(new StringReader(source));
        CourseParser parser = new CourseParser(lex);
        try {
            parser.parse();
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
        }
    }
    
    public static void test() {
        Structure edd = new Structure();
        
        Student e = edd.getStudents().get("dd");
        
        Schedule s = edd.getSchedules().get(new Sortable("d"));
        
        Course c = edd.getCourses().getNode("id").getData();
        Building b = edd.getBuildings().getNode("id").getData();
        Classroom clazz = b.getClassrooms().getNode("id").getData();
        Professor p = edd.getProfessors().get("id").getData();
    }
}

package com.cesar31.system.control;

import com.cesar31.system.model.*;
import com.cesar31.system.structures.*;
import java.io.IOException;

/**
 *
 * @author cesar31
 */
public class Structure {

    private HashTable<Student> students;
    private DoublyLinkdList<User> users;
    private DoublyLinkdList<Building> buildings;
    private AVLTree<Professor> professors;
    private DoublyLinkdList<Course> courses;
    private BTree<Schedule> schedules;
    // private DoublyLinkdList<Assignment> assignments;

    private FileControl file;

    public Structure() {
        this.students = new HashTable<>();
        this.users = new DoublyLinkdList<>();
        this.buildings = new DoublyLinkdList<>();
        this.professors = new AVLTree();
        this.courses = new DoublyLinkdList<>();
        this.schedules = new BTree<>(Schedule.class);
        // this.assignments = new DoublyLinkdList<>();
        User u = new User("0001", "cesar31", "abc", UserType.SUPER);
        System.out.println("Insertando -> user: 0001 password: abc");
        this.users.insert(u.getId(), u);

        this.file = new FileControl();
    }

    public String AllDot() {
        String graph = "digraph";
        String sub = "subgraph";
        String st = studentsDot().replaceFirst(graph, sub);
        return "";
    }

    public void graphStudents() {
        String dot = studentsDot();
        file.writeDotFile("students.dot", dot);
        graph(command("students"));
    }

    public void graphUsers() {
        String dot = usersDot();
        file.writeDotFile("users.dot", dot);
        graph(command("users"));
    }

    public void graphBuildings() {
        String dot = buildingsDot();
        file.writeDotFile("buildings.dot", dot);
        graph(command("buildings"));
    }

    public void graphProfessors() {
        String dot = professorsDot();
        file.writeDotFile("professors.dot", dot);
        graph(command("professors"));
    }

    public void graphCourses() {
        String dot = coursesDot();
        file.writeDotFile("courses.dot", dot);
        graph(command("courses"));
    }

    public void graphSchedules(boolean assign) {
        String dot = schedulesDot(assign);
        file.writeDotFile("schedules.dot", dot);
        graph(command("schedules"));
    }

    private String command(String name) {
        return "dot -Tpng " + name + ".dot -o " + name + ".png";
    }

    /**
     * Ejecutar comando para graficar
     *
     * @param command
     */
    private void graph(String command) {
        try {
            file.execComand(command);
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
        }
    }

    private String studentsDot() {
        String dot = "digraph students_hash{\n\n";
        dot += "rankdir = LR;\n";
        dot += "node[ shape = record, widht = 0.5, height = 0.1 ];\n\n";

        dot += "node" + this.getStudents().hashCode() + "[ label = \" <f0> 0";
        for (int i = 1; i < this.getStudents().getSize(); i++) {
            dot += "| <f" + i + "> " + i;
        }
        dot += "\"];\n\n";
        dot += "node[ width = 1.5 ];\n\n";

        for (int i = 0; i < this.getStudents().getSize(); i++) {
            if (this.getStudents().getArrayHash()[i] != null) {
                if (!this.getStudents().getArrayHash()[i].isDeleted()) {
                    Student s = this.getStudents().getArrayHash()[i].getData();
                    dot += "node" + s.hashCode() + "[ label = \"" + s.toString() + "\" ];\n";
                }
            }
        }
        dot += "\n";

        for (int i = 0; i < this.getStudents().getSize(); i++) {
            if (this.getStudents().getArrayHash()[i] != null) {
                if (!this.getStudents().getArrayHash()[i].isDeleted()) {
                    Student s = this.getStudents().getArrayHash()[i].getData();
                    dot += "node" + this.getStudents().hashCode() + ":f" + i + "-> node" + s.hashCode() + ";\n";
                }
            }
        }
        dot += "}\n";

        return dot;
    }

    private String schedulesDot(boolean assign) {
        StringBuilder dot = new StringBuilder();
        dot.append("digraph schedules_tree{\n\n");
        dot.append("node[ shape = record ];\n");

        dot.append(bToDot(schedules.getRoot(), assign));

        dot.append("}\n");
        return dot.toString();
    }

    private String bToDot(BNode<Schedule> sch, boolean assign) {
        StringBuilder b = new StringBuilder();

        b.append("node").append(sch.hashCode());
        b.append("[ label = \"<P0>");

        for (int i = 0; i < sch.getmB(); i++) {
            b.append("|<f").append(i).append(">").append(sch.getData()[i].toString());
            b.append("|<P").append(i + 1).append(">");
        }
        b.append("\" ];\n");

        for (int i = 0; i <= sch.getmB(); i++) {
            if (sch.getPointers()[i] != null) {
                b.append(bToDot(sch.getPointers()[i], assign));
                b.append("node").append(sch.hashCode()).append(":P").append(i).append(" -> ").append("node").append(sch.getPointers()[i].hashCode()).append(";\n");
            }
        }

        if (assign) {
            b.append(assigmentDot(sch));
        }

        return b.toString();
    }

    private String assigmentDot(BNode<Schedule> sch) {
        String dot = "\n\n";
        for (int i = 0; i < sch.getmB(); i++) {
            ListNode<Assignment> top = sch.getData()[i].getAssignments().getTop();
            if (top != null) {
                ListNode<Assignment> aux = top;
                do {
                    dot += "node" + aux.getData().hashCode() + "[ label = \"" + aux.getData().toString() + "\" group = " + sch.hashCode() + "];\n";
                    aux = aux.getNext();
                } while (aux != top);

                // Puntero
                dot += "node" + sch.hashCode() + ":f" + i + " -> node" + top.getData().hashCode() + ";\n";

                // Punteros lista doble enlazada
                aux = top;
                do {
                    dot += "node" + aux.getData().hashCode() + " -> node" + aux.getNext().getData().hashCode() + ";\n";
                    dot += "node" + aux.getData().hashCode() + " -> node" + aux.getPrevious().getData().hashCode() + ";\n";
                    aux = aux.getNext();
                } while (aux != top);
            }
        }
        dot += "\n\n";

        return dot;
    }

    private String professorsDot() {
        StringBuilder dot = new StringBuilder();
        dot.append("digraph professors_tree{\n\n");
        dot.append("node[ shape = record ];\n\n");

        // Nodos
        getNodes(professors.getRoot(), dot);
        dot.append("\n");

        getPointers(professors.getRoot(), dot);
        dot.append("\n");

        dot.append("}\n");

        return dot.toString();
    }

    private void getNodes(AVLNode<Professor> node, StringBuilder dot) {
        if (node != null) {
            dot.append(nodeName(node));
            getNodes(node.getLeft(), dot);
            getNodes(node.getRight(), dot);
        }
    }

    private String nodeName(AVLNode<Professor> node) {
        if (node.getRight() == null && node.getLeft() == null) {
            return "node" + node.getData().hashCode() + "[ label = \"" + node.getData().toString() + "\" ];\n";
        }

        return "node" + node.getData().hashCode() + "[ label = \"<C0> | " + node.getData().toString() + " | <C1>\" ];\n";
    }

    private void getPointers(AVLNode<Professor> father, StringBuilder dot) {
        if (father != null) {
            if (father.getRight() != null) {
                dot.append(getPointers(father, father.getRight(), true));
                getPointers(father.getRight(), dot);
            }

            if (father.getLeft() != null) {
                dot.append(getPointers(father, father.getLeft(), false));
                getPointers(father.getLeft(), dot);
            }
        }
    }

    private String getPointers(AVLNode<Professor> father, AVLNode<Professor> son, boolean right) {
        String side = right ? ":C1 -> node" : ":C0 -> node";
        return "node" + father.getData().hashCode() + side + son.getData().hashCode() + ";\n";
    }

    /**
     * .dot edificios y salones
     *
     * @return
     */
    private String buildingsDot() {
        String dot = "digraph buildings_list{\n\n";
        dot += "node[ shape = box ];\n";
        dot += "randir = LR;\n";

        if (buildings.getTop() != null) {
            ListNode<Building> aux = buildings.getTop();
            do {
                // Nodo lista circular
                dot += "node" + aux.getData().hashCode() + "[ label = \"" + aux.getData().toString() + "\" group = " + aux.getData().hashCode() + " ];\n";

                // root listado de clases
                ListNode<Classroom> auxC = aux.getData().getClassrooms().getRoot();
                while (auxC != null) {
                    // Nodo listado de salones
                    dot += "node" + auxC.getData().hashCode() + " [ label = \"" + auxC.getData().toString() + "\" group = " + aux.getData().hashCode() + " ];\n";
                    auxC = auxC.getNext();
                }
                dot += "\n";

                auxC = aux.getData().getClassrooms().getRoot();
                // Nodo building a classroom
                if (auxC != null) {
                    dot += "node" + aux.getData().hashCode() + " -> node" + auxC.getData().hashCode() + ";\n";
                }
                //punteros entre classroom
                while (auxC != null) {
                    if (auxC.getNext() != null) {
                        dot += "node" + auxC.getData().hashCode() + " -> node" + auxC.getNext().getData().hashCode() + ";\n";
                    }
                    auxC = auxC.getNext();
                }
                dot += "\n";
                aux = aux.getNext();
            } while (aux != buildings.getTop());

            // Punteros lista circular
            aux = buildings.getTop();
            do {
                dot += "node" + aux.getData().hashCode() + " -> node" + aux.getNext().getData().hashCode() + ";\n";
                dot += "node" + aux.getData().hashCode() + " -> node" + aux.getPrevious().getData().hashCode() + ";\n";
                aux = aux.getNext();
            } while (aux != buildings.getTop());

            // rank
            dot += "{ rank = same; ";
            aux = buildings.getTop();
            do {
                dot += "node" + aux.getData().hashCode() + "; ";
                aux = aux.getNext();
            } while (aux != buildings.getTop());
            dot += "}\n\n";
        }
        dot += "}\n";

        return dot;
    }

    /**
     * .dot para cursos
     *
     * @return
     */
    private String coursesDot() {
        String dot = "digraph courses_list{\n\n";
        dot += "node[ shape = box ];\n";
        dot += "randir = LR;\n";
        if (courses.getTop() != null) {
            ListNode<Course> aux = courses.getTop();
            do {
                dot += "node" + aux.getData().hashCode() + "[ label = \"" + aux.getData().toString() + "\" ];\n";
                aux = aux.getNext();
            } while (aux != courses.getTop());
            dot += "\n";

            aux = courses.getTop();
            do {
                dot += "node" + aux.getData().hashCode() + " -> node" + aux.getNext().getData().hashCode() + ";\n";
                dot += "node" + aux.getData().hashCode() + " -> node" + aux.getPrevious().getData().hashCode() + ";\n";
                aux = aux.getNext();
            } while (aux != courses.getTop());
            dot += "\n";

            aux = courses.getTop();
            dot += "{ rank = same; ";
            do {
                dot += "node" + aux.getData().hashCode() + "; ";
                aux = aux.getNext();
            } while (aux != courses.getTop());
            dot += "}\n";
        }
        dot += "}\n";

        return dot;
    }

    private String usersDot() {
        String dot = "digraph users_list{\n\n";
        dot += "node[ shape = box ];\n";
        dot += "randir = LR;\n";
        if (users.getTop() != null) {
            ListNode<User> aux = users.getTop();
            do {
                dot += "node" + aux.getData().hashCode() + "[ label = \"" + aux.getData().toString() + "\" ];\n";
                aux = aux.getNext();
            } while (aux != users.getTop());
            dot += "\n";

            aux = users.getTop();
            do {
                dot += "node" + aux.getData().hashCode() + " -> node" + aux.getNext().getData().hashCode() + ";\n";
                dot += "node" + aux.getData().hashCode() + " -> node" + aux.getPrevious().getData().hashCode() + ";\n";
                aux = aux.getNext();
            } while (aux != users.getTop());
            dot += "\n";

            aux = users.getTop();
            dot += "{ rank = same; ";
            do {
                dot += "node" + aux.getData().hashCode() + "; ";
                aux = aux.getNext();
            } while (aux != users.getTop());
            dot += "}\n";
        }
        dot += "}\n";

        return dot;
    }

    public HashTable<Student> getStudents() {
        return students;
    }

    public void setStudents(HashTable<Student> students) {
        this.students = students;
    }

    public DoublyLinkdList<User> getUsers() {
        return users;
    }

    public void setUsers(DoublyLinkdList<User> users) {
        this.users = users;
    }

    public DoublyLinkdList<Building> getBuildings() {
        return buildings;
    }

    public void setBuildings(DoublyLinkdList<Building> buildings) {
        this.buildings = buildings;
    }

    public AVLTree<Professor> getProfessors() {
        return professors;
    }

    public void setProfessors(AVLTree<Professor> professors) {
        this.professors = professors;
    }

    public DoublyLinkdList<Course> getCourses() {
        return courses;
    }

    public void setCourses(DoublyLinkdList<Course> courses) {
        this.courses = courses;
    }

    public BTree<Schedule> getSchedules() {
        return schedules;
    }

    public void setSchedules(BTree<Schedule> schedules) {
        this.schedules = schedules;
    }
}

package com.cesar31.system.control;

import com.cesar31.system.model.Schedule;
import com.cesar31.system.model.Student;
import com.cesar31.system.structures.BNode;

/**
 *
 * @author cesar31
 */
public class Crud {

    private Structure edd;
    private MainController control;

    public Crud(Structure edd, MainController control) {
        this.edd = edd;
        this.control = control;
    }

    /**
     * Editar estudiante
     *
     * @param id
     * @param name
     * @param address
     */
    public void editStudent(String id, String name, String address) {
        if (!id.trim().isEmpty() && !name.trim().isEmpty() && !address.trim().isEmpty()) {
            Student tmp = edd.getStudents().get(id);
            if (tmp != null) {
                tmp.setName(name);
                tmp.setAddress(address);
                control.getColl().showMessage("Se editado la informacion del estudiante " + id, "Informacion");
                control.getColl().clearStudent();
            } else {
                control.getColl().showMessage("El estudiante que desea editar " + id + ", no existe", "Error");
            }
        }
    }

    /**
     * Eliminar estudiante
     *
     * @param id
     */
    public void deleteStudent(String id) {
        if (!id.trim().isEmpty()) {
            Student tmp = edd.getStudents().remove(id);
            if (tmp != null) {
                //Verificar si es usuario
                if (edd.getUsers().getNode(id) != null) {
                    edd.getUsers().delete(id);
                }

                // Eliminar asignaciones
                recursiveDelAssignment(edd.getSchedules().getRoot(), id);

                control.getColl().showMessage("Se ha eliminado al estudiante " + id, "Informacion");
                control.getColl().clearStudent();
            } else {
                control.getColl().showMessage("El estudiante " + id + ", no existe.", "Error");
            }
        }
    }

    private void recursiveDelAssignment(BNode<Schedule> sch, String id) {
        if (sch != null) {
            for (int i = 0; i < sch.getmB(); i++) {
                sch.getData()[i].getAssignments().delete(id);
            }

            for (int i = 0; i < sch.getmB(); i++) {
                recursiveDelAssignment(sch.getPointers()[i], id);
            }
        }
    }

    public void addStudent(String id, String name, String address) {
        if (!id.trim().isEmpty() && !name.trim().isEmpty() && !address.trim().isEmpty()) {
            Student tmp = new Student(id, name, address);

            if (edd.getStudents().get(id) == null) {
                edd.getStudents().put(id, tmp);
                control.getColl().showMessage("Se ingresado al estudiante: " + id, "Informacion");
                control.getColl().clearStudent();
            } else {
                control.getColl().showMessage("El usuario con id " + id + ", ya existe.", "Error");
            }
        }
    }
}

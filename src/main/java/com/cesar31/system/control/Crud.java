package com.cesar31.system.control;

import com.cesar31.system.model.*;
import com.cesar31.system.structures.*;

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
                recursiveDelStudent(edd.getSchedules().getRoot(), id);

                control.getColl().showMessage("Se ha eliminado al estudiante " + id, "Informacion");
                control.getColl().clearStudent();
            } else {
                control.getColl().showMessage("El estudiante " + id + ", no existe.", "Error");
            }
        }
    }

    /**
     * Eliminar estudiante en asignacion
     *
     * @param sch
     * @param id
     */
    private void recursiveDelStudent(BNode<Schedule> sch, String id) {
        if (sch != null) {
            for (int i = 0; i < sch.getmB(); i++) {
                /*
                if (sch.getData()[i].getAssignments().getNode(id) != null) {
                    sch.getData()[i].getAssignments().getNode(id).getData().setStudent(null);
                    // System.out.println(sch.getData()[i].getAssignments().getNode(id).getData().toString());
                }
                 */
                sch.getData()[i].getAssignments().delete(id);
            }

            for (int i = 0; i <= sch.getmB(); i++) {
                recursiveDelStudent(sch.getPointers()[i], id);
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

    /**
     * Agragar usuario
     *
     * @param id
     * @param username
     * @param password
     * @param type
     */
    public void addUser(String id, String username, String password, UserType type) {
        if (!id.trim().isEmpty() && !username.trim().isEmpty() && !password.trim().isEmpty()) {
            boolean create = true;
            User tmp = new User(id, username, password, type);

            if (type == UserType.STUDENT) {
                if (edd.getStudents().get(id) == null) {
                    create = false;
                }
            }

            if (create) {
                boolean isCreated = edd.getUsers().insert(id, tmp);
                if (isCreated) {
                    control.getColl().showMessage("Usuario " + id + " agregado.", "Informacion");
                    control.getColl().clearUser();
                } else {
                    control.getColl().showMessage("El id: " + id + " no esta disponible, intente con otro.", "Informacion");
                }
            } else {
                // Mensaje tipo estudiante que no existe
                control.getColl().showMessage("No se puede crear usuario tipo estudiante, porque el estudiante " + id + ", no existe.", "Error");
            }

        }
    }

    /**
     * Editar usuario
     *
     * @param id
     * @param username
     * @param password
     * @param type
     */
    public void editUser(String id, String username, String password, UserType type) {
        if (!id.trim().isEmpty() && !username.trim().isEmpty() && !password.trim().isEmpty()) {
            if (edd.getUsers().getNode(id) != null) {
                User tmp = edd.getUsers().getNode(id).getData();

                if (tmp.getType() == type) {
                    tmp.setUsername(username);
                    tmp.setPassword(password);

                    // Mensaje de confirmacion
                    control.getColl().showMessage("Usuario editado correctamente.", "Informacion");
                    control.getColl().clearUser();
                } else {
                    control.getColl().showMessage("No esta permitido modificar el tipo de usuario", "Error");
                    /*
                    if (type == UserType.STUDENT) {
                        Student s = edd.getStudents().get(id);
                        if (s != null) {
                            tmp.setUsername(username);
                            tmp.setPassword(password);
                            tmp.setType(type);

                            // Mensaje de confirmacion
                            control.getColl().showMessage("Usuario editado correctamente.", "Informacion");
                            control.getColl().clearUser();

                            if (control.getColl().getUser().getId().equals(id)) {
                                System.out.println(control.getColl().getUser() == tmp);
                                control.getColl().showMessage("Sesion expirada", "Informacion");
                                control.cerrarSesion(control.getColl());
                            }

                        } else {
                            // No se puede editar, estudiante no existe
                            control.getColl().showMessage("No se puede cambiar al usuario " + id + " a tipo estudiante, porque el estudiante no existe.", "Error");
                        }
                    } else {
                        tmp.setUsername(username);
                        tmp.setPassword(password);
                        tmp.setType(type);

                        // Mensaje confirmacion
                        control.getColl().showMessage("Usuario editado correctamente.", "Informacion");
                        control.getColl().clearUser();
                    }
                     */
                }
            } else {
                control.getColl().showMessage("El usuario que desea editar no existe.", "Error");
            }
        }
    }

    /**
     * Eliminar usuario
     *
     * @param id
     */
    public void delUser(String id) {
        if (!id.trim().isEmpty()) {
            if (edd.getUsers().getNode(id) != null) {
                edd.getUsers().delete(id);
                control.getColl().showMessage("Se ha eliminado al usuario " + id, id);

                // Si sesion actual
                if (control.getColl().getUser().getId().equals(id)) {
                    control.getColl().showMessage("Sesion expirada", "Informacion");
                    control.cerrarSesion(control.getColl());
                }

                control.getColl().clearUser();
            } else {
                control.getColl().showMessage("El usuario " + id + ", no existe.", "Error");
            }
        }
    }

    /**
     * Agreagar curso
     *
     * @param code
     * @param name
     * @param semester
     * @param credits
     */
    public void addCourse(String code, String name, String semester, String credits) {
        if (!code.trim().isEmpty() && !name.trim().isEmpty() && !semester.trim().isEmpty() && !credits.trim().isEmpty()) {
            Course tmp = new Course(code, name, Integer.valueOf(semester), Integer.valueOf(credits));
            Boolean inserted = edd.getCourses().insert(code, tmp);
            if (inserted) {
                control.getColl().showMessage("El curso: " + code + " fue agreagado.", "Informacion");
                control.getColl().clearCourse();
            } else {
                control.getColl().showMessage("El codigo que desea utilizar ya existe, intente con otro", "Error");
            }
        }
    }

    /**
     * Editar curso
     *
     * @param code
     * @param name
     * @param semester
     * @param credits
     */
    public void editCourse(String code, String name, String semester, String credits) {
        if (!code.trim().isEmpty() && !name.trim().isEmpty() && !semester.trim().isEmpty() && !credits.trim().isEmpty()) {
            if (edd.getCourses().getNode(code) != null) {
                Course tmp = edd.getCourses().getNode(code).getData();
                tmp.setName(name);
                tmp.setSemester(Integer.valueOf(semester));
                tmp.setCredits(Integer.valueOf(credits));

                control.getColl().showMessage("Se ha editado la informacion del curso " + code + ".", "Informacion");
                control.getColl().clearCourse();
            } else {
                control.getColl().showMessage("El curso que desea editar no existe", "Error");
            }
        }
    }

    /**
     * Eliminar curso
     *
     * @param code
     */
    public void deleteCourse(String code) {
        if (!code.trim().isEmpty()) {
            if (edd.getCourses().getNode(code) != null) {
                edd.getCourses().delete(code);

                // Eliminar referencias de horarios
                recursiveDelCourse(edd.getSchedules().getRoot(), code);

                control.getColl().showMessage("Se ha eliminado el curso " + code + ".", "Informacion");
                control.getColl().clearCourse();

            } else {
                control.getColl().showMessage("El curso que desea eliminar no existe", "Error");
            }
        }
    }

    /**
     * Eliminar referencia del curso de horarios
     *
     * @param sch
     * @param code
     */
    private void recursiveDelCourse(BNode<Schedule> sch, String code) {
        if (sch != null) {
            for (int i = 0; i < sch.getmB(); i++) {
                if (sch.getData()[i].getCourse().getId().equals(code)) {
                    sch.getData()[i].setCourse(null);
                    System.out.println(sch.getData()[i].toString());
                }
            }

            for (int i = 0; i <= sch.getmB(); i++) {
                recursiveDelCourse(sch.getPointers()[i], code);
            }
        }
    }

    /**
     * Agregar docente
     *
     * @param id
     * @param nombre
     * @param address
     */
    public void addProfessor(String id, String nombre, String address) {
        if (!id.trim().isEmpty() && !nombre.trim().isEmpty() && !address.trim().isEmpty()) {
            Professor tmp = new Professor(id, nombre, address);
            boolean inserted = edd.getProfessors().insert(id, tmp);

            if (inserted) {
                control.getColl().showMessage("Se ha agregado al sistema al docente " + id + ".", "Error");
                control.getColl().clearProf();
            } else {
                control.getColl().showMessage("El id que desea utilizar no esta disponible, intente con otro.", "Error");
            }
        }
    }

    /**
     * Editar informacion de catedratico
     *
     * @param id
     * @param name
     * @param address
     */
    public void editProfessor(String id, String name, String address) {
        if (!id.trim().isEmpty() && !name.trim().isEmpty() && !address.trim().isEmpty()) {
            if (edd.getProfessors().get(id) != null) {
                Professor tmp = edd.getProfessors().get(id).getData();

                tmp.setName(name);
                tmp.setAddress(address);

                control.getColl().showMessage("Se ha editado la informacion del docente " + id + ".", "Error");
                control.getColl().clearProf();
            } else {
                control.getColl().showMessage("El catedratico que desea editar no existe", "Error");
            }
        }
    }

    /**
     * Eliminar catedratico y referencias
     *
     * @param id
     */
    public void delProfessor(String id) {
        if (!id.trim().isEmpty()) {
            if (edd.getProfessors().get(id) != null) {
                edd.getProfessors().delete(id);

                // Eliminar referencias en horarios
                recursiveDelProfessor(edd.getSchedules().getRoot(), id);

                control.getColl().showMessage("Se ha eliminado al catedratico " + id + ".", "Informacion");
                control.getColl().clearProf();
            } else {
                control.getColl().showMessage("El catedratico que desea eliminar no existe. ", "Error");

            }
        }
    }

    /**
     * Eliminar recursivamente docentes en los horarios
     *
     * @param sch
     * @param id
     */
    private void recursiveDelProfessor(BNode<Schedule> sch, String id) {
        if (sch != null) {
            for (int i = 0; i < sch.getmB(); i++) {
                if (sch.getData()[i].getProfessor().getId().equals(id)) {
                    sch.getData()[i].setProfessor(null);
                    // System.out.println(sch.getData()[i].toString());
                }
            }

            for (int i = 0; i <= sch.getmB(); i++) {
                recursiveDelProfessor(sch.getPointers()[i], id);
            }
        }
    }

    /**
     * Agregar edificio
     *
     * @param id
     */
    public void addBuilding(String id) {
        if (!id.trim().isEmpty()) {
            Building tmp = new Building(id);
            boolean inserted = edd.getBuildings().insert(id, tmp);
            if (inserted) {
                control.getColl().showMessage("Se ha agregado al correctamente al edificio: " + id + ".", "Informacion");
                control.getColl().clearBuilding();
            } else {
                control.getColl().showMessage("El nombre de edificio que desea utilizar no esta disponible.", "Error");
            }
        }
    }

    /**
     * Eliminar edificios
     *
     * @param id
     */
    public void delBuilding(String id) {
        if (!id.trim().isEmpty()) {
            if (edd.getBuildings().getNode(id) != null) {
                edd.getBuildings().delete(id);

                // Eliminar de horarios recursivamente
                recursiveDelBuilding(edd.getSchedules().getRoot(), id);

                control.getColl().showMessage("Se ha eliminado el edificio " + id + ".", "Informacion");
                control.getColl().clearBuilding();
            } else {
                control.getColl().showMessage("El edificio que desea eliminar no existe. ", "Error");
            }
        }
    }

    private void recursiveDelBuilding(BNode<Schedule> sch, String id) {
        if (sch != null) {
            for (int i = 0; i < sch.getmB(); i++) {
                if (sch.getData()[i].getBuilding().getName().equals(id)) {
                    sch.getData()[i].setBuilding(null);
                    sch.getData()[i].setClassroom(null);
                    // System.out.println(sch.getData()[i].toString());
                }
            }

            for (int i = 0; i <= sch.getmB(); i++) {
                recursiveDelBuilding(sch.getPointers()[i], id);
            }
        }
    }

    /**
     * Agregar salon
     *
     * @param build
     * @param clazz
     * @param count
     */
    public void addClassroom(String build, String clazz, String count) {
        if (!build.trim().isEmpty() && !clazz.trim().isEmpty() && !count.trim().isEmpty()) {
            if (edd.getBuildings().getNode(build) != null) {
                Building b = edd.getBuildings().getNode(build).getData();
                Classroom tmp = new Classroom(b, clazz, Integer.valueOf(count));

                boolean inserted = b.getClassrooms().insert(tmp.getId(), tmp);
                if (inserted) {
                    control.getColl().showMessage("Se ha agregado al correctamente el salon " + clazz + ".", "Informacion");
                    control.getColl().clearClassroom();
                } else {
                    control.getColl().showMessage("El id del salon que intenta utilizar no esta disponible.", "Informacion");
                }
            } else {
                control.getColl().showMessage("El edificio " + build + " que indico, no existe.", "Error");
            }
        }
    }

    /**
     * Editar classroom
     *
     * @param build
     * @param clazz
     * @param count
     */
    public void editClassroom(String build, String clazz, String count) {
        if (!build.trim().isEmpty() && !clazz.trim().isEmpty() && !count.trim().isEmpty()) {
            if (edd.getBuildings().getNode(build) != null) {
                Building b = edd.getBuildings().getNode(build).getData();
                if (b.getClassrooms().getNode(clazz) != null) {
                    Classroom c = b.getClassrooms().getNode(clazz).getData();
                    c.setNumberOfStudents(Integer.valueOf(count));

                    control.getColl().showMessage("Se ha editado el número de estudiantes del salon " + clazz + ".", "Error");
                    control.getColl().clearClassroom();
                } else {
                    control.getColl().showMessage("El salon " + clazz + " que indico, no existe.", "Error");
                }
            } else {
                control.getColl().showMessage("El edificio " + build + " que indico, no existe.", "Error");
            }
        }
    }

    public void delClassroom(String build, String clazz) {
        if (!build.trim().isEmpty() && !clazz.trim().isEmpty()) {
            if (edd.getBuildings().getNode(build) != null) {
                Building b = edd.getBuildings().getNode(build).getData();
                if (b.getClassrooms().getNode(clazz) != null) {
                    b.getClassrooms().deleteNode(clazz);

                    // Eliminar recursivamente de horarios
                    recursiveDelClassroom(edd.getSchedules().getRoot(), clazz);

                    control.getColl().showMessage("Se ha eliminado el salon " + clazz + " del edificio " + build + ".", "Informacion");
                    control.getColl().clearClassroom();
                } else {
                    control.getColl().showMessage("El salon que desea eliminar, no existe.", "Error");
                }
            } else {
                control.getColl().showMessage("El edificio " + build + ", no existe.", "Error");
            }
        }
    }

    private void recursiveDelClassroom(BNode<Schedule> sch, String id) {
        if (sch != null) {
            for (int i = 0; i < sch.getmB(); i++) {
                if (sch.getData()[i].getClassroom().getId().equals(id)) {
                    sch.getData()[i].setClassroom(null);
                    System.out.println(sch.getData()[i].toString());
                }
            }

            for (int i = 0; i <= sch.getmB(); i++) {
                recursiveDelClassroom(sch.getPointers()[i], id);
            }
        }
    }

    /**
     * Nueva asignacion
     *
     * @param student
     * @param schedule
     * @param score
     * @param test
     */
    public void addAssignment(String student, String schedule, String score, String test) {
        if (str(student) && str(schedule) && str(score) && str(test)) {
            Student st = edd.getStudents().get(student);
            if (st != null) {
                Schedule sch = edd.getSchedules().get(new Sortable(schedule));
                if (sch != null) {

                    // Verificar que no exista asignacion para student
                    if (sch.getAssignments().getNode(student) == null) {
                        Assignment tmp = new Assignment(st, sch, Integer.valueOf(score), Integer.valueOf(test));
                        tmp.setId(student);

                        if (sch.getClassroom() != null) {
                            int actual = sch.getAssignments().getSize();
                            int limit = sch.getClassroom().getNumberOfStudents();

                            if (actual <= limit) {
                                sch.getAssignments().insert(tmp.getId(), tmp);

                                // Mensaje confirmacion
                                control.getColl().showMessage("Asignacion realizada en horario " + schedule + " para " + student, "Informacion");
                                control.getColl().clearAssignment();
                            } else {
                                control.getColl().showMessage("Se ha alcanzado el limite de asignacion en horario : " + schedule, "Error");
                            }
                        } else {
                            sch.getAssignments().insert(tmp.getId(), tmp);

                            // Mensaje confirmacion
                            control.getColl().showMessage("Asignacion realizada en horario " + schedule + " para " + student, "Informacion");
                            control.getColl().clearAssignment();
                        }
                    } else {
                        control.getColl().showMessage("Ya existe una asignacion para el estudiante " + student + " en el horario " + schedule + ".", "Error");
                    }
                } else {
                    control.getColl().showMessage("El horario " + schedule + " no existe, no es posible realizar la asignacion.", "Error");
                }
            } else {
                control.getColl().showMessage("El estudiante " + student + " no existe, no es posible realizar la asignacion.", "Error");
            }
        }
    }

    /**
     * Editar asignacion
     *
     * @param student
     * @param schedule
     * @param score
     * @param test
     */
    public void editAssignment(String student, String schedule, String score, String test) {
        if (str(student) && str(schedule) && str(score) && str(test)) {
            Schedule sch = edd.getSchedules().get(new Sortable(schedule));
            if (sch != null) {
                if (sch.getAssignments().getNode(student) != null) {
                    Assignment tmp = sch.getAssignments().getNode(student).getData();

                    tmp.setHomeworkScore(Integer.valueOf(score));
                    tmp.setTestScore(Integer.valueOf(test));

                    // Mensaje de confirmación
                    control.getColl().showMessage("Se ha editado la asignacion en horario " + schedule + " para estudiante " + student + ".", "Informacion");
                    control.getColl().clearAssignment();
                } else {
                    control.getColl().showMessage("No se encuentran asignaciones para: " + student + ".", "Error");
                }
            } else {
                control.getColl().showMessage("El horario indicado no existe.", "Error");
            }
        }
    }

    public void delAssignment(String student, String schedule) {
        if (str(student) && str(schedule)) {
            Schedule sch = edd.getSchedules().get(new Sortable(schedule));
            if (sch != null) {
                if (sch.getAssignments().getNode(student) != null) {
                    sch.getAssignments().delete(student);

                    // Mensaje de confirmacion
                    control.getColl().showMessage("Se han eliminado la asignacion en horario " + schedule + " para estudiante " + student + ".", "Informacion");
                    control.getColl().clearAssignment();
                } else {
                    control.getColl().showMessage("No se encuentran asignaciones para: " + student + ".", "Error");
                }
            } else {
                control.getColl().showMessage("El horario indicado no existe.", "Error");
            }
        }
    }

    private boolean str(String value) {
        return !value.trim().isEmpty();
    }
}

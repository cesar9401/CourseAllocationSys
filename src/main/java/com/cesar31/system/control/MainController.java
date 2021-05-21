package com.cesar31.system.control;

import com.cesar31.system.model.*;
import com.cesar31.system.parser.CourseLex;
import com.cesar31.system.parser.CourseParser;
import com.cesar31.system.view.*;
import java.io.StringReader;
import javax.swing.JFrame;

/**
 *
 * @author cesar31
 */
public final class MainController {

    private Login login;
    private SuperView superView;
    private CollaboratorView coll;

    private Structure edd;
    private Crud crud;
    private Report report;

    public MainController() {
        this.edd = new Structure();
        this.crud = new Crud(edd, this);
        this.report = new Report(this, this.edd);
        //this.readData("/home/cesar31/Java/CourseAllocationSys/parser/example.txt");
    }

    public void initView() {
        java.awt.EventQueue.invokeLater(() -> {
            login = new Login(this);
            login.setLocationRelativeTo(null);
            login.setVisible(true);
        });
    }

    public void login(String id, String pass) {
        if (edd.getUsers().getNode(id) != null) {
            User u = edd.getUsers().getNode(id).getData();
            if (u.getPassword().equals(pass)) {
                // Iniciar sesion aqui

                this.login.setVisible(false);
                this.login.dispose();

                switch (u.getType()) {
                    case SUPER:
                        initSuper(u);
                        break;
                    case COLLABORATOR:
                        initCollaborator(u);
                        break;
                    case STUDENT:
                        System.out.println("Sesion: " + u.getId());
                        break;
                }
            } else {
                this.login.showMessage("Usuario incorrecto", "Error");
            }
        } else {
            this.login.showMessage("Usuario incorrecto", "Error");
        }
    }

    public void readData(String path) {
        System.out.println(path);
        FileControl file = new FileControl();
        String data = file.readData(path);

        CourseLex lex = new CourseLex(new StringReader(data));
        CourseParser parser = new CourseParser(lex, this.edd);
        boolean read;
        try {
            read = (boolean) parser.parse().value;
        } catch (Exception ex) {
            read = false;
            ex.printStackTrace(System.out);
        }

        /* Descomentar esto */
        if (read) {
            superView.showMessage("Informacion cargada", "Informacion");
        } else {
            superView.showMessage("La información no pudo ser cargada, revise la sintaxis de su archivo", "Error");
        }
    }

    private void initCollaborator(User u) {
        java.awt.EventQueue.invokeLater(() -> {
            coll = new CollaboratorView(u, this, crud);
            coll.setLocationRelativeTo(null);
            coll.setVisible(true);
        });
    }

    private void initSuper(User u) {
        java.awt.EventQueue.invokeLater(() -> {
            superView = new SuperView(u, this);
            superView.setLocationRelativeTo(null);
            superView.setVisible(true);
        });
    }

    public void cerrarSesion(JFrame frame) {
        frame.setVisible(false);
        frame.dispose();
        this.initView();
    }

    public Structure getEdd() {
        return edd;
    }

    public CollaboratorView getColl() {
        return coll;
    }
}

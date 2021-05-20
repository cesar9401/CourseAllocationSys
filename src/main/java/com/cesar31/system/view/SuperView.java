package com.cesar31.system.view;

import com.cesar31.system.control.MainController;
import com.cesar31.system.model.User;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author cesar31
 */
public class SuperView extends javax.swing.JFrame {

    private User u;
    private MainController control;

    public SuperView(User u, MainController control) {
        initComponents();
        this.u = u;
        this.control = control;
        setDatos();
    }

    private void setDatos() {
        idLabel.setText(u.getId());
        userLabel.setText(u.getUsername());
        typeLabel.setText(u.getType().toString().toLowerCase());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuItem1 = new javax.swing.JMenuItem();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        idLabel = new javax.swing.JLabel();
        userLabel = new javax.swing.JLabel();
        typeLabel = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        dataItem = new javax.swing.JMenuItem();
        signOffItem = new javax.swing.JMenuItem();

        jMenuItem1.setText("jMenuItem1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(500, 400));
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(153, 163, 164));
        jPanel1.setForeground(new java.awt.Color(0, 0, 0));
        jPanel1.setMinimumSize(new java.awt.Dimension(500, 400));

        jLabel2.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("id:");

        jLabel3.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("usuario:");

        jLabel4.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("tipo:");

        idLabel.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        idLabel.setForeground(new java.awt.Color(0, 51, 255));
        idLabel.setText("id");

        userLabel.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        userLabel.setForeground(new java.awt.Color(0, 51, 255));
        userLabel.setText("usuario");

        typeLabel.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        typeLabel.setForeground(new java.awt.Color(0, 51, 255));
        typeLabel.setText("tipo");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(145, 145, 145)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(idLabel)
                    .addComponent(userLabel)
                    .addComponent(typeLabel))
                .addContainerGap(173, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(77, 77, 77)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(idLabel))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(userLabel))
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(typeLabel))
                .addContainerGap(192, Short.MAX_VALUE))
        );

        jMenuBar1.setBackground(new java.awt.Color(153, 163, 164));
        jMenuBar1.setForeground(new java.awt.Color(0, 0, 0));
        jMenuBar1.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N

        jMenu1.setForeground(new java.awt.Color(0, 0, 0));
        jMenu1.setText("File");
        jMenu1.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N

        dataItem.setBackground(new java.awt.Color(153, 163, 164));
        dataItem.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        dataItem.setForeground(new java.awt.Color(0, 0, 0));
        dataItem.setText("Cargar archivos...");
        dataItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dataItemActionPerformed(evt);
            }
        });
        jMenu1.add(dataItem);

        signOffItem.setBackground(new java.awt.Color(153, 163, 164));
        signOffItem.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        signOffItem.setForeground(new java.awt.Color(0, 0, 0));
        signOffItem.setText("Cerrar Sesión");
        signOffItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                signOffItemActionPerformed(evt);
            }
        });
        jMenu1.add(signOffItem);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void signOffItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_signOffItemActionPerformed
        // TODO add your handling code here:
        this.control.cerrarSesion(this);
    }//GEN-LAST:event_signOffItemActionPerformed

    private void dataItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dataItemActionPerformed
        // TODO add your handling code here:
        JFileChooser fc = new JFileChooser();
        int sel = fc.showOpenDialog(this);
        if (sel == JFileChooser.APPROVE_OPTION) {
            String path = fc.getSelectedFile().getAbsolutePath();
            control.readData(path);
        }
    }//GEN-LAST:event_dataItemActionPerformed

    public void showMessage(String message, String title) {
        JOptionPane.showMessageDialog(this, message, title, JOptionPane.INFORMATION_MESSAGE);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem dataItem;
    private javax.swing.JLabel idLabel;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JMenuItem signOffItem;
    private javax.swing.JLabel typeLabel;
    private javax.swing.JLabel userLabel;
    // End of variables declaration//GEN-END:variables
}
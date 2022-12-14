/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.Color;
import java.io.IOException;
import javax.swing.JOptionPane;
import konst.Port;
import niti.ServerNit;

/**
 *
 * @author Master
 */
public class ServerForma extends javax.swing.JFrame {

    int port;
    ServerNit sn;

    /**
     * Creates new form ServerForma
     */
    public ServerForma() {
        initComponents();
        this.port = 0;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        lblStatus = new javax.swing.JLabel();
        jmMeni = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jmiPokreniServer = new javax.swing.JMenuItem();
        jmiUgasiServer = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jmiPodesiPort = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Status servera:");

        lblStatus.setForeground(new java.awt.Color(255, 0, 0));
        lblStatus.setText("ugašen");

        jMenu1.setText("Server");

        jmiPokreniServer.setText("Pokreni server");
        jmiPokreniServer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiPokreniServerActionPerformed(evt);
            }
        });
        jMenu1.add(jmiPokreniServer);

        jmiUgasiServer.setText("Ugasi server");
        jmiUgasiServer.setEnabled(false);
        jmiUgasiServer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiUgasiServerActionPerformed(evt);
            }
        });
        jMenu1.add(jmiUgasiServer);

        jmMeni.add(jMenu1);

        jMenu2.setText("Postavke");

        jmiPodesiPort.setText("Podesi port");
        jmiPodesiPort.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiPodesiPortActionPerformed(evt);
            }
        });
        jMenu2.add(jmiPodesiPort);

        jmMeni.add(jMenu2);

        setJMenuBar(jmMeni);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(lblStatus)
                .addContainerGap(262, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(lblStatus))
                .addContainerGap(235, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jmiPodesiPortActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiPodesiPortActionPerformed
        new PortForma(this).setVisible(true);
    }//GEN-LAST:event_jmiPodesiPortActionPerformed

    private void jmiPokreniServerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiPokreniServerActionPerformed
        if(port == 0){
            JOptionPane.showMessageDialog(this, "Podesite zeljeni port u meniju 'Postavke'");
            return;
        }
        sn = new ServerNit(this);
        sn.start();
        lblStatus.setText("pokrenut");
        lblStatus.setForeground(Color.green);
        jmiPokreniServer.setEnabled(false);
        jmiUgasiServer.setEnabled(true);
    }//GEN-LAST:event_jmiPokreniServerActionPerformed

    private void jmiUgasiServerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiUgasiServerActionPerformed
        if (sn.getServerSocket() != null && sn.getServerSocket().isBound()) {
            try {
                sn.getServerSocket().close();
                lblStatus.setText("ugašen");
                lblStatus.setForeground(Color.red);
                jmiPokreniServer.setEnabled(true);
                jmiUgasiServer.setEnabled(false);
            } catch (IOException ex) {
                  System.out.println("Server je ugasen");
            }
        }
    }//GEN-LAST:event_jmiUgasiServerActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ServerForma.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ServerForma.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ServerForma.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ServerForma.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ServerForma().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jmMeni;
    private javax.swing.JMenuItem jmiPodesiPort;
    private javax.swing.JMenuItem jmiPokreniServer;
    private javax.swing.JMenuItem jmiUgasiServer;
    private javax.swing.JLabel lblStatus;
    // End of variables declaration//GEN-END:variables

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
        Port.getInstance().setPort(port);
    }

}

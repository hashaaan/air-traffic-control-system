/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package airtrafficcontrol;

/**
 *
 * @author hashan
 */
public class AddAirportUI extends javax.swing.JFrame {
    AirTrafficControl mainData;
    /**
     * Creates new form AddAirportUI
     */
    public AddAirportUI() {
        initComponents();
    }
    
    public AddAirportUI(AirTrafficControl atc) {
        initComponents();
        mainData = atc;
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
        txtCountry = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtAirportCode = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        btnAdd = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("ADD AIRPORT");
        setPreferredSize(new java.awt.Dimension(798, 534));
        setResizable(false);
        getContentPane().setLayout(null);

        jLabel1.setFont(new java.awt.Font("Ubuntu", 1, 48)); // NOI18N
        jLabel1.setText("ADD AIRPORT");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(240, 30, 330, 60);

        txtCountry.setFont(new java.awt.Font("Ubuntu", 0, 24)); // NOI18N
        txtCountry.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCountryActionPerformed(evt);
            }
        });
        getContentPane().add(txtCountry);
        txtCountry.setBounds(350, 280, 290, 40);

        jLabel2.setFont(new java.awt.Font("Ubuntu", 0, 24)); // NOI18N
        jLabel2.setText("Country :");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(160, 280, 180, 40);

        jLabel3.setFont(new java.awt.Font("Ubuntu", 0, 24)); // NOI18N
        jLabel3.setText("Airport Code :");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(160, 140, 180, 40);

        txtAirportCode.setFont(new java.awt.Font("Ubuntu", 0, 24)); // NOI18N
        txtAirportCode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAirportCodeActionPerformed(evt);
            }
        });
        getContentPane().add(txtAirportCode);
        txtAirportCode.setBounds(350, 140, 290, 40);

        jLabel4.setFont(new java.awt.Font("Ubuntu", 0, 24)); // NOI18N
        jLabel4.setText("Name :");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(160, 210, 180, 40);

        txtName.setFont(new java.awt.Font("Ubuntu", 0, 24)); // NOI18N
        txtName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNameActionPerformed(evt);
            }
        });
        getContentPane().add(txtName);
        txtName.setBounds(350, 210, 290, 40);

        btnAdd.setFont(new java.awt.Font("Ubuntu", 1, 24)); // NOI18N
        btnAdd.setText("ADD");
        btnAdd.setFocusPainted(false);
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });
        getContentPane().add(btnAdd);
        btnAdd.setBounds(290, 390, 240, 60);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtCountryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCountryActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCountryActionPerformed

    private void txtAirportCodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAirportCodeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAirportCodeActionPerformed

    private void txtNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNameActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        // TODO add your handling code here:
        //mainData
        String airportCode = txtAirportCode.getText();
        String airportName = txtName.getText();
        String country = txtCountry.getText();
        System.out.println(airportCode+" "+airportName+" "+country);
        mainData.insertAirport(airportCode, airportName, country);
    }//GEN-LAST:event_btnAddActionPerformed

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
            java.util.logging.Logger.getLogger(AddAirportUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AddAirportUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AddAirportUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AddAirportUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AddAirportUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JTextField txtAirportCode;
    private javax.swing.JTextField txtCountry;
    private javax.swing.JTextField txtName;
    // End of variables declaration//GEN-END:variables
}
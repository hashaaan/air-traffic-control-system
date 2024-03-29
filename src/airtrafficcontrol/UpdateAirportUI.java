/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package airtrafficcontrol;

import java.awt.Color;

/**
 *
 * @author hashan
 */
public class UpdateAirportUI extends javax.swing.JFrame {
    AirTrafficControl mainData;
    /**
     * Creates new form UpdateAirportUI
     */
    public UpdateAirportUI() {
        initComponents();
    }
    
    public UpdateAirportUI(AirTrafficControl atc) {
        initComponents();
        mainData = atc;
        onLoad();
    }
    
    final void onLoad() {
        cmbAirports.removeAllItems();
        cmbAirports.setToolTipText(("Select Airports"));
        for(int i=0;i<mainData.codes.length; i++) {
            cmbAirports.addItem(new ComboItem(mainData.codes[i]+" - "+mainData.airports[0][i],mainData.codes[i]));
        }
    }
    
    public boolean validateForm() {
        String airportName = txtName.getText();
        String country = txtCountry.getText();
        
        if(airportName.equals("") || country.equals("")){
        
            if(airportName.equals("")){
                errorName.setForeground(Color.red);
                errorName.setText("Airport name is required!");
            }

            if(country.equals("")){
                errorCountry.setForeground(Color.red);
                errorCountry.setText("Airport country is required!");
            }
            
            return false;
        }
        
        errorName.setText("");
        errorCountry.setText("");
        
        return true;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cmbAirports = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtCountry = new javax.swing.JTextField();
        txtName = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        btnUpdate = new javax.swing.JButton();
        errorCountry = new javax.swing.JLabel();
        errorName = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(798, 534));
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cmbAirports.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        getContentPane().add(cmbAirports, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 160, 280, 40));

        jLabel1.setFont(new java.awt.Font("Ubuntu", 0, 24)); // NOI18N
        jLabel1.setText("Country :");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 300, 170, 40));

        jLabel2.setFont(new java.awt.Font("Ubuntu", 1, 48)); // NOI18N
        jLabel2.setText("UPDATE AIRPORT");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 20, 430, 60));

        jLabel3.setFont(new java.awt.Font("Ubuntu", 0, 24)); // NOI18N
        jLabel3.setText("Airport Code :");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 160, 170, 40));

        txtCountry.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        txtCountry.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCountryActionPerformed(evt);
            }
        });
        getContentPane().add(txtCountry, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 300, 280, 40));

        txtName.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        txtName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNameActionPerformed(evt);
            }
        });
        getContentPane().add(txtName, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 230, 280, 40));

        jLabel4.setFont(new java.awt.Font("Ubuntu", 0, 24)); // NOI18N
        jLabel4.setText("Name :");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 230, 170, 40));

        btnUpdate.setFont(new java.awt.Font("Ubuntu", 1, 24)); // NOI18N
        btnUpdate.setText("UPDATE");
        btnUpdate.setFocusPainted(false);
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });
        getContentPane().add(btnUpdate, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 400, 240, 60));
        getContentPane().add(errorCountry, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 340, 260, 20));
        getContentPane().add(errorName, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 270, 260, 20));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtCountryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCountryActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCountryActionPerformed

    private void txtNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNameActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        // TODO add your handling code here:
        String airportCode = ((ComboItem)(cmbAirports.getSelectedItem())).getValue();
        String airportName = txtName.getText();
        String country = txtCountry.getText();
        
        boolean isValid = validateForm();
        
        if(isValid){
            System.out.println(airportCode+" "+airportName+" "+country);
            mainData.updateAirport(airportCode, airportCode, airportName, country);
        }
    }//GEN-LAST:event_btnUpdateActionPerformed

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
            java.util.logging.Logger.getLogger(UpdateAirportUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UpdateAirportUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UpdateAirportUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UpdateAirportUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new UpdateAirportUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnUpdate;
    private javax.swing.JComboBox cmbAirports;
    private javax.swing.JLabel errorCountry;
    private javax.swing.JLabel errorName;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JTextField txtCountry;
    private javax.swing.JTextField txtName;
    // End of variables declaration//GEN-END:variables
}

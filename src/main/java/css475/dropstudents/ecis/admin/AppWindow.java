/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package css475.dropstudents.ecis.admin;

import css475.dropstudents.ecis.MySqlConnection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

/**
 *
 * @author joelm
 */
public class AppWindow extends javax.swing.JFrame {

    private final String host = 
            "css475groupproject.coqyi6uxbprc.us-east-1.rds.amazonaws.com";
    MySqlConnection connection;
    MySqlConnection.Database db = null;
    /**
     * Creates new form AppWindow
     */
    public AppWindow() throws ClassNotFoundException, SQLException {
        connection = new MySqlConnection(host);
        while (db == null || !db.isConnected()) {
            try {
                connectToDatabase("editor");
            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
            }
        }
        initComponents();
    }
    
    public void refresh() {
        TablePanel[] tables = {tablePanel1, tablePanel2, tablePanel3, tablePanel4, tablePanel5 };
        for (TablePanel table : tables) {
            try {
                table.refresh();
            } catch (SQLException ex) {
                Logger.getLogger(AppWindow.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private void connectToDatabase(String user) throws SQLException {
        JPanel panel = new JPanel();
        JLabel label = new JLabel("Enter password for database user " + user + ":");
        JPasswordField pass = new JPasswordField(10);
        panel.add(label);
        panel.add(pass);
        String[] options = new String[]{"OK"};
        int option = JOptionPane.showOptionDialog(null, panel, "Password",
                                 JOptionPane.NO_OPTION, JOptionPane.PLAIN_MESSAGE,
                                 null, options, options[0]);
        if(option == 0) // pressing OK button
        {
            char[] password = pass.getPassword();
            db = connection.connect("ECIS", user, new String(password));
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSplitPane4 = new javax.swing.JSplitPane();
        jSplitPane2 = new javax.swing.JSplitPane();
        jSplitPane1 = new javax.swing.JSplitPane();
        try {
            tablePanel2 = new css475.dropstudents.ecis.admin.TablePanel(db, "State", false);
            try {
                tablePanel1 = new css475.dropstudents.ecis.admin.TablePanel(db, "Candidate", true);
                jSplitPane3 = new javax.swing.JSplitPane();
                try {
                    tablePanel3 = new css475.dropstudents.ecis.admin.TablePanel(db, "Location", true);
                    jPanel1 = new javax.swing.JPanel();
                    newCandidateButton = new javax.swing.JButton();
                    newLocationButton = new javax.swing.JButton();
                    refreshButton = new javax.swing.JButton();
                    votingItemButton = new javax.swing.JButton();
                    voteButton = new javax.swing.JButton();
                    partyButton = new javax.swing.JButton();
                    jSplitPane5 = new javax.swing.JSplitPane();
                    try {
                        tablePanel4 = new css475.dropstudents.ecis.admin.TablePanel(db, "VotingItem", true);
                        try {
                            tablePanel5 = new css475.dropstudents.ecis.admin.TablePanel(db, "VotingRecord", true);

                            setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

                            jSplitPane4.setDividerLocation(400);

                            jSplitPane2.setDividerLocation(300);
                            jSplitPane2.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);

                            jSplitPane1.setDividerLocation(550);

                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                        jSplitPane1.setRightComponent(tablePanel2);

                    } catch (SQLException ex) {
                        Logger.getLogger(AppWindow.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    jSplitPane1.setLeftComponent(tablePanel1);

                    jSplitPane2.setTopComponent(jSplitPane1);

                    jSplitPane3.setDividerLocation(150);

                } catch (java.sql.SQLException e1) {
                    e1.printStackTrace();
                }
                jSplitPane3.setRightComponent(tablePanel3);

                newCandidateButton.setText("New Candidate");
                newCandidateButton.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        newCandidateButtonActionPerformed(evt);
                    }
                });

                newLocationButton.setText("New Location");
                newLocationButton.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        newLocationButtonActionPerformed(evt);
                    }
                });

                refreshButton.setText("Refresh");
                refreshButton.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        refreshButtonActionPerformed(evt);
                    }
                });

                votingItemButton.setText("New  Voting Item");
                votingItemButton.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        votingItemButtonActionPerformed(evt);
                    }
                });

                voteButton.setText("Add Vote");
                voteButton.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        voteButtonActionPerformed(evt);
                    }
                });

                partyButton.setText("New Party");
                partyButton.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        partyButtonActionPerformed(evt);
                    }
                });

                javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
                jPanel1.setLayout(jPanel1Layout);
                jPanel1Layout.setHorizontalGroup(
                    jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(refreshButton)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(votingItemButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(newLocationButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(newCandidateButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(voteButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(partyButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())
                );
                jPanel1Layout.setVerticalGroup(
                    jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(newCandidateButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(newLocationButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(votingItemButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(voteButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(partyButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(refreshButton)
                        .addContainerGap())
                );

                jSplitPane3.setLeftComponent(jPanel1);

                jSplitPane2.setRightComponent(jSplitPane3);

                jSplitPane4.setLeftComponent(jSplitPane2);

                jSplitPane5.setDividerLocation(300);
                jSplitPane5.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);

            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            jSplitPane5.setTopComponent(tablePanel4);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        jSplitPane5.setRightComponent(tablePanel5);

        jSplitPane4.setRightComponent(jSplitPane5);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 924, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPane4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 518, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void newCandidateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newCandidateButtonActionPerformed
        InsertFrame frame = new InsertFrame(
                db, 
                "Candidate",
                new ColumnSpec("FirstName", ColumnSpec.ValueType.Text, true),
                new ColumnSpec("MiddleName", ColumnSpec.ValueType.Text, false),
                new ColumnSpec("LastName", ColumnSpec.ValueType.Text, true),
                new ColumnSpec("LocationId", "Location", "ID", "City", ColumnSpec.ValueType.Int, true),
                new ColumnSpec("PartyId", "PoliticalParty", "ID", "Name", ColumnSpec.ValueType.Int, false),
                new ColumnSpec("DateOfBirth", ColumnSpec.ValueType.Text, false),
                new ColumnSpec("Phone", ColumnSpec.ValueType.Text, false),
                new ColumnSpec("Email", ColumnSpec.ValueType.Text, false),
                new ColumnSpec("Website", ColumnSpec.ValueType.Text, false)
        );
        frame.setVisible(true);
        if (frame.isConfirmed()) {
            System.out.println(frame.getInsertQuery()); // show insert command
            refresh();
        }
    }//GEN-LAST:event_newCandidateButtonActionPerformed

    private void newLocationButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newLocationButtonActionPerformed
        InsertFrame frame = new InsertFrame(
                db, 
                "Location",
                new ColumnSpec("City", ColumnSpec.ValueType.Text, true),
                new ColumnSpec("StateId", "State", "ID", "Name", ColumnSpec.ValueType.Text, true)
        );
        frame.setVisible(true);
        if (frame.isConfirmed()) {
            System.out.println(frame.getInsertQuery()); // show insert command
            refresh();
        }
    }//GEN-LAST:event_newLocationButtonActionPerformed

    private void refreshButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshButtonActionPerformed
        refresh();
    }//GEN-LAST:event_refreshButtonActionPerformed

    private void votingItemButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_votingItemButtonActionPerformed
        InsertFrame frame = new InsertFrame(
                db, 
                "VotingItem",
                new ColumnSpec("Title", ColumnSpec.ValueType.Text, true),
                new ColumnSpec("Description", ColumnSpec.ValueType.Text, false)
        );
        frame.setVisible(true);
        if (frame.isConfirmed()) {
            System.out.println(frame.getInsertQuery()); // show insert command
            refresh();
        }
    }//GEN-LAST:event_votingItemButtonActionPerformed

    private void voteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_voteButtonActionPerformed
        InsertFrame frame = new InsertFrame(
                db, 
                "VotingRecord",
                new ColumnSpec("CandidateId", "Candidate", "ID", "FirstName", ColumnSpec.ValueType.Int, true),
                new ColumnSpec("BillId", "VotingItem", "ID", "Title", ColumnSpec.ValueType.Int, true),
                new ColumnSpec("VoteId", "Vote", "ID", "Choice", ColumnSpec.ValueType.Int, true)
        );
        frame.setVisible(true);
        if (frame.isConfirmed()) {
            System.out.println(frame.getInsertQuery()); // show insert command
            refresh();
        }
    }//GEN-LAST:event_voteButtonActionPerformed

    private void partyButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_partyButtonActionPerformed
        InsertFrame frame = new InsertFrame(
                db, 
                "PoliticalParty",
                new ColumnSpec("Name", ColumnSpec.ValueType.Text, true)
        );
        frame.setVisible(true);
        if (frame.isConfirmed()) {
            System.out.println(frame.getInsertQuery()); // show insert command
            refresh();
        }
    }//GEN-LAST:event_partyButtonActionPerformed

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
            java.util.logging.Logger.getLogger(AppWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AppWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AppWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AppWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new AppWindow().setVisible(true);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(AppWindow.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(AppWindow.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JSplitPane jSplitPane2;
    private javax.swing.JSplitPane jSplitPane3;
    private javax.swing.JSplitPane jSplitPane4;
    private javax.swing.JSplitPane jSplitPane5;
    private javax.swing.JButton newCandidateButton;
    private javax.swing.JButton newLocationButton;
    private javax.swing.JButton partyButton;
    private javax.swing.JButton refreshButton;
    private css475.dropstudents.ecis.admin.TablePanel tablePanel1;
    private css475.dropstudents.ecis.admin.TablePanel tablePanel2;
    private css475.dropstudents.ecis.admin.TablePanel tablePanel3;
    private css475.dropstudents.ecis.admin.TablePanel tablePanel4;
    private css475.dropstudents.ecis.admin.TablePanel tablePanel5;
    private javax.swing.JButton voteButton;
    private javax.swing.JButton votingItemButton;
    // End of variables declaration//GEN-END:variables
}

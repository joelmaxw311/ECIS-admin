/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package css475.dropstudents.ecis.admin;

import css475.dropstudents.ecis.MySqlConnection;
import java.awt.Window;
import javax.swing.JDialog;
import javax.swing.JFrame;

/**
 *
 * @author joelm
 */
public class KeySelectorFrame extends PromptDialog<Object> {
    
    private final MySqlConnection.Database db;
    private final String table, column, nameColumn;
    
    private Object selectedKey, selectedName;
    
    /**
     * Creates new form KeySelectorFrame
     */
    public KeySelectorFrame(MySqlConnection.Database db, String table, String column, String nameColumn) {
        this.db = db;
        this.table = table;
        this.column = column;
        this.nameColumn = nameColumn;
        initComponents();
        setTitle(String.format("Select a %s (%s)", table, column));
    }
    
    public Object getSelectedKey() {
        return selectedKey;
    }
    
    public Object getSelectedName() {
        return selectedName;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        try {
            tablePanel = new css475.dropstudents.ecis.admin.TablePanel(db, table);
            okButton = new javax.swing.JButton();
            valuePreview = new javax.swing.JTextField();
            nullButton = new javax.swing.JButton();

        } catch (java.sql.SQLException e1) {
            e1.printStackTrace();
        }

        okButton.setText("OK");
        okButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okButtonActionPerformed(evt);
            }
        });

        valuePreview.setEditable(false);

        nullButton.setText("CLEAR");
        nullButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nullButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tablePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(valuePreview, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 65, Short.MAX_VALUE)
                        .addComponent(nullButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(okButton)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tablePanel, javax.swing.GroupLayout.DEFAULT_SIZE, 249, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(okButton)
                    .addComponent(valuePreview, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nullButton))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void okButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okButtonActionPerformed
        Object selection = tablePanel.getSelection(column);
        if (selection != null) {
            selectedKey = selection;
            selectedName = tablePanel.getSelection(nameColumn);
            submit(); // confirm and close
        }
        // do nothing
    }//GEN-LAST:event_okButtonActionPerformed

    private void nullButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nullButtonActionPerformed
        selectedKey = "";
        selectedName = "";
        submit(); // confirm and close
    }//GEN-LAST:event_nullButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton nullButton;
    private javax.swing.JButton okButton;
    private css475.dropstudents.ecis.admin.TablePanel tablePanel;
    private javax.swing.JTextField valuePreview;
    // End of variables declaration//GEN-END:variables

    @Override
    public Object getResult() {
        return getSelectedKey();
    }
}
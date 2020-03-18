/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package css475.dropstudents.ecis.admin;

import css475.dropstudents.ecis.*;
import java.awt.Component;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

/**
 *
 * @author joelm
 */
public class TablePanel extends javax.swing.JPanel {
    
    private MySqlConnection.Database db;
    private String tableName;
    private ResultSet results = null;
    
    TableModel resultsModel;
    
    private String[] columnTypes;
    
    public TablePanel() throws SQLException {
        try {
            initComponents();
        } catch (Exception ex) {
            Logger.getLogger(TablePanel.class.getName()).log(Level.SEVERE, "Initialzed TablePanel without query.", ex);
        }
    }
    
    /**
     * Creates new form TablePanel
     */
    public TablePanel(MySqlConnection.Database db, String tableName, boolean editable) throws SQLException {
        this.db = db;
        this.tableName = tableName;
        loadResultsTable();
        initComponents();
        if (editable) {
            resultsTable.setComponentPopupMenu(tablePopupMenu);
            resultsTable.addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent e) {
                    if (e.getKeyCode() == KeyEvent.VK_ENTER) {

                        int row = resultsTable.getSelectedRow();
                        int column = resultsTable.getSelectedColumn();
                        String columnName = resultsModel.getColumnName(column);
                        
                        String sql = String.format("UPDATE %s SET %s = %s WHERE ",
                                tableName,
                                columnName,
                                ColumnSpec.formatType(resultsModel.getValueAt(row, column), columnTypes[column])
                            );
                        
                        List<String> conditions = new ArrayList();
                        List<String> rowConditions = new ArrayList();
                        for (int c = 0; c < resultsTable.getColumnCount(); c++ ) {
                            if (c != column) {
                                String cname = resultsModel.getColumnName(c);
                                Object value = resultsModel.getValueAt(row, c);
                                String cType = columnTypes[c];
                                if (value == null)
                                    rowConditions.add(String.format("`%s` IS NULL", cname));
                                else if (cType.equalsIgnoreCase("Int") || cType.equalsIgnoreCase("Real")) // number type
                                    rowConditions.add(String.format("`%s` = %s", cname, value));
                                else // string type
                                    rowConditions.add(String.format("`%s` = '%s'", cname, value));
                            }
                        }
                        conditions.add("( " + rowConditions.stream().collect(Collectors.joining(" AND ")) + " )");
                        sql += conditions.stream()
                                .collect(Collectors.joining(" OR "));
                        sql += ";";

                        System.out.println(sql);
                        try {
                            db.execute(sql);
                            refresh();
                        } catch (SQLException ex) {
                            Logger.getLogger(TablePanel.class.getName()).log(Level.SEVERE, null, ex);
                            JOptionPane.showMessageDialog(TablePanel.this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE); // show error message
                        }
                    }
                }
            });
        }
    }
    
    public TablePanel(MySqlConnection.Database db, String tableName) throws SQLException {
        this(db, tableName, false);
    }
    
    public void refresh() throws SQLException {
        loadResultsTable();
        resultsTable.setModel(resultsModel);
        DefaultTableModel dm = (DefaultTableModel)resultsTable.getModel();
        dm.fireTableDataChanged(); // notifies the JTable that the model has changed
        resultsTable.repaint();
    }
    
    public void dropSelection() {
		if (tableName == "VotingItem" && resultsTable.getSelectedRows().length > 0)
		{
			// execute query to delete voting records if we are deleting a voting item
			String votingRecordDelete = "DELETE FROM VotingRecord WHERE BillId IN (";
			List<String> rowConditions = new ArrayList();
			for (int row = 0; row < resultsTable.getSelectedRows().length; row++)
			{
				// foreach id that wants to be deleted
				//   add it to the string of BillIds to delete
				Object value = resultsModel.getValueAt(resultsTable.getSelectedRows()[row], 0);
				String cType = columnTypes[0];
				if (cType.equalsIgnoreCase("Int") || cType.equalsIgnoreCase("Real")) // number type
				{
					votingRecordDelete += String.format("%s", value);
				}
				if (row < resultsTable.getSelectedRows().length - 1)
				{
					votingRecordDelete += ",";
				}
			}
			votingRecordDelete += ");";
			System.out.println(votingRecordDelete);
			
			try {
				db.execute(votingRecordDelete);
				// Don't refresh or else the next query won't activate because selectedRows will get reset.
			} catch (SQLException ex) {
				Logger.getLogger(TablePanel.class.getName()).log(Level.SEVERE, null, ex);
				JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE); // show error message
			}
			
		}
		
        String sql = "DELETE FROM `" + tableName + "` WHERE ";
        List<String> conditions = new ArrayList();
        for (int r : resultsTable.getSelectedRows()) {
            List<String> rowConditions = new ArrayList();
            for (int c = 0; c < resultsTable.getColumnCount(); c++ ) {
                String cname = resultsModel.getColumnName(c);
                Object value = resultsModel.getValueAt(r, c);
                String cType = columnTypes[c];
                if (value == null)
                    rowConditions.add(String.format("`%s` IS NULL", cname));
                else if (cType.equalsIgnoreCase("Int") || cType.equalsIgnoreCase("Real")) // number type
                    rowConditions.add(String.format("`%s` = %s", cname, value));
                else // string type
                    rowConditions.add(String.format("`%s` = '%s'", cname, value));
            }
            conditions.add("( " + rowConditions.stream().collect(Collectors.joining(" AND ")) + " )");
        }
        sql += conditions.stream()
                .collect(Collectors.joining(" OR "));
        sql += ";";
        System.out.println(sql);
        try {
            db.execute(sql);
            refresh();
        } catch (SQLException ex) {
            Logger.getLogger(TablePanel.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE); // show error message
        }
    }
    
    public void loadResultsTable() throws SQLException {
        try {
            this.results = db.query("SELECT * FROM `" + tableName + "`");
            fillTable();
        } catch (SQLException ex) {
            Logger.getLogger(TablePanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void fillTable() throws SQLException {
        ResultSetMetaData metaData = results.getMetaData();
        int nColumns = metaData.getColumnCount();
        // add new content
        Set<Object>[] filterModels = new Set[nColumns];
        
        columnTypes = new String[nColumns];
        for (int c = 0; c < nColumns; c++) {
            columnTypes[c] = metaData.getColumnTypeName(c+1);
        }
        
        // add columns to table
        Object[] headers = new Object[nColumns];
        for (int i = 0; i < nColumns; i++) {
            String columnName = metaData.getColumnName(i+1);
            headers[i] = columnName;
            filterModels[i] = new HashSet<>();
        }
        System.out.println(Arrays.toString(headers));
        DefaultTableModel model = new DefaultTableModel(headers,0);
        // fill table rows
        while (results.next()) {
            Object[] row = new Object[nColumns];
            for (int i = 0; i < nColumns; i++) {
                Object value = results.getObject(i+1);
                row[i] = value;
                filterModels[i].add(value);
            }
            model.addRow(row);
            System.out.println(Arrays.toString(row));
        }
        resultsModel = model;
        // create filter inputs
        for (int i = 0; i < nColumns; i++) {
            Object[] suggestions = filterModels[i].toArray();
            JComboBox elem = new JComboBox(suggestions);
            elem.setEditable(true);
            elem.setName("filter" + i);
            elem.setSelectedIndex(-1);
        }
    }
    
    public Object getSelection(String columnName) {
        int colNo = resultsTable.getColumn(columnName).getModelIndex();
        int rowNo = resultsTable.getSelectedRow();
        return resultsTable.getValueAt( rowNo, colNo);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tablePopupMenu = new javax.swing.JPopupMenu();
        deleteMenuItem = new javax.swing.JMenuItem();
        verticalScrollPane = new javax.swing.JScrollPane();
        resultsTable = new javax.swing.JTable();

        deleteMenuItem.setText("Drop selected rows");
        deleteMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteMenuItemActionPerformed(evt);
            }
        });
        tablePopupMenu.add(deleteMenuItem);

        setLayout(new java.awt.BorderLayout());

        resultsTable.setModel(resultsModel);
        verticalScrollPane.setViewportView(resultsTable);

        add(verticalScrollPane, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void deleteMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteMenuItemActionPerformed
        dropSelection();
    }//GEN-LAST:event_deleteMenuItemActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem deleteMenuItem;
    private javax.swing.JTable resultsTable;
    private javax.swing.JPopupMenu tablePopupMenu;
    private javax.swing.JScrollPane verticalScrollPane;
    // End of variables declaration//GEN-END:variables
}

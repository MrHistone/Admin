package admin.frames;

import admin.PublicDefault.ProgramMode;
import database.*;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.swing.AbstractAction;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

public class ExtensionPanel extends javax.swing.JPanel {

    Itemsoort itemsoort;
    Query query;
    List<Extension> listMasterTable;
    List<Itemsoort> listItemsoort;
    DefaultTableModel model;
    EntityManager entityManager;
    ProgramMode programMode;
    DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
    Extension displayedExtension = null;
    Itemsoort isOnbekend = null;
    EnterAction enterAction = new EnterAction(this);

    /**
     * Creates new form Extension
     */
    public ExtensionPanel() {
        initComponents();
        // Do not execute this at design time.
        if (!java.beans.Beans.isDesignTime()) {
            entityManager = Persistence.createEntityManagerFactory("administration").createEntityManager();
            programMode = ProgramMode.DISPLAY;
            model = (DefaultTableModel) masterTable.getModel();
            setStates();
            masterTable.getColumnModel().getColumn(3).setCellRenderer(tableCellRenderer);
            masterTable.getColumnModel().getColumn(4).setCellRenderer(tableCellRenderer);
            loadSelectionListener();
            loadItemsoortCombobox();
            this.getInputMap().put(KeyStroke.getKeyStroke("ENTER"),
                    "pressedEnter");
            tfDescription.getInputMap().put(KeyStroke.getKeyStroke("ENTER"),
                    "pressedEnter");
            tfCode.getInputMap().put(KeyStroke.getKeyStroke("ENTER"),
                    "pressedEnter");
            this.getActionMap().put("pressedEnter",
                    enterAction);
            tfCode.getActionMap().put("pressedEnter",
                    enterAction);
            tfDescription.getActionMap().put("pressedEnter",
                    enterAction);
        }
    }

    public void setItemsoort(Itemsoort itemsoort) {
        this.itemsoort = itemsoort;
        loadData();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        masterTable = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        lblID = new javax.swing.JLabel();
        lblCode = new javax.swing.JLabel();
        lblDescription = new javax.swing.JLabel();
        lblDateCreated = new javax.swing.JLabel();
        lblDateModified = new javax.swing.JLabel();
        tfID = new javax.swing.JTextField();
        tfCode = new javax.swing.JTextField();
        tfDescription = new javax.swing.JTextField();
        tfDateCreated = new javax.swing.JTextField();
        tfDateModified = new javax.swing.JTextField();
        btnNewExtension = new javax.swing.JButton();
        btnDeleteExtension = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        btnChange = new javax.swing.JButton();
        cmbItemsoort = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();

        masterTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Extension", "Description", "Date Created", "Date Modified"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(masterTable);
        if (masterTable.getColumnModel().getColumnCount() > 0) {
            masterTable.getColumnModel().getColumn(3).setResizable(false);
        }

        lblID.setText("ID");

        lblCode.setText("Code");

        lblDescription.setText("Description");

        lblDateCreated.setText("Date Created");

        lblDateModified.setText("Date Modified");

        tfID.setMinimumSize(new java.awt.Dimension(50, 20));
        tfID.setPreferredSize(new java.awt.Dimension(50, 20));

        tfCode.setMinimumSize(new java.awt.Dimension(100, 20));
        tfCode.setPreferredSize(new java.awt.Dimension(100, 20));

        tfDescription.setMinimumSize(new java.awt.Dimension(200, 20));
        tfDescription.setPreferredSize(new java.awt.Dimension(200, 20));

        tfDateCreated.setMinimumSize(new java.awt.Dimension(150, 20));
        tfDateCreated.setPreferredSize(new java.awt.Dimension(150, 20));
        tfDateCreated.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfDateCreatedActionPerformed(evt);
            }
        });

        tfDateModified.setMinimumSize(new java.awt.Dimension(150, 20));
        tfDateModified.setPreferredSize(new java.awt.Dimension(150, 20));

        btnNewExtension.setText("New Extension");
        btnNewExtension.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewExtensionActionPerformed(evt);
            }
        });

        btnDeleteExtension.setText("Delete Extension");
        btnDeleteExtension.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteExtensionActionPerformed(evt);
            }
        });

        btnSave.setText("Save");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        btnChange.setText("Change Extension");
        btnChange.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChangeActionPerformed(evt);
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
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btnNewExtension)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnDeleteExtension)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnChange)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnSave))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(lblID)
                                .addGap(73, 73, 73)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(tfCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tfID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tfDescription, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblDescription)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(lblDateModified)
                                .addGap(18, 18, 18)
                                .addComponent(tfDateModified, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lblCode, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(lblDateCreated)
                                .addGap(18, 18, 18)
                                .addComponent(tfDateCreated, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfID, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblID))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfCode, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCode, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfDescription, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDescription))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfDateCreated, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDateCreated))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfDateModified, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDateModified))
                .addGap(65, 65, 65)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNewExtension)
                    .addComponent(btnDeleteExtension)
                    .addComponent(btnChange)
                    .addComponent(btnSave))
                .addGap(309, 309, 309))
        );

        cmbItemsoort.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbItemsoort.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbItemsoortActionPerformed(evt);
            }
        });

        jLabel1.setText("Itemsoort");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 563, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbItemsoort, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbItemsoort, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 247, Short.MAX_VALUE)
                .addGap(4, 4, 4))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnNewExtensionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewExtensionActionPerformed

        addNewExtension();
    }//GEN-LAST:event_btnNewExtensionActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        saveNewData();
    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnChangeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChangeActionPerformed
        programMode = ProgramMode.CHANGE;

        setStates();
    }//GEN-LAST:event_btnChangeActionPerformed

    private void btnDeleteExtensionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteExtensionActionPerformed
        removeExtension();
    }//GEN-LAST:event_btnDeleteExtensionActionPerformed

    private void cmbItemsoortActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbItemsoortActionPerformed
        itemsoort = (Itemsoort) cmbItemsoort.getSelectedItem();
        loadData();
    }//GEN-LAST:event_cmbItemsoortActionPerformed

    private void tfDateCreatedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfDateCreatedActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfDateCreatedActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnChange;
    private javax.swing.JButton btnDeleteExtension;
    private javax.swing.JButton btnNewExtension;
    private javax.swing.JButton btnSave;
    private javax.swing.JComboBox cmbItemsoort;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblCode;
    private javax.swing.JLabel lblDateCreated;
    private javax.swing.JLabel lblDateModified;
    private javax.swing.JLabel lblDescription;
    private javax.swing.JLabel lblID;
    private javax.swing.JTable masterTable;
    private javax.swing.JTextField tfCode;
    private javax.swing.JTextField tfDateCreated;
    private javax.swing.JTextField tfDateModified;
    private javax.swing.JTextField tfDescription;
    private javax.swing.JTextField tfID;
    // End of variables declaration//GEN-END:variables

    private void loadData() {
        try {
            if (itemsoort != null) {
                model.setRowCount(0);
                if (listMasterTable != null && listMasterTable.size() > 0) {
                    listMasterTable.clear();
                }
                query = entityManager.createNamedQuery("Extension.findByItemsoortId", ExtensionPanel.class);
                query.setParameter("itemsoortid", itemsoort.getItemsoortId());

                listMasterTable = query.getResultList();
                for (Extension extension : listMasterTable) {
                    addItemToModel(extension);
                }

                if (model.getRowCount() > 0) {
                    masterTable.setRowSelectionInterval(0, 0);
                }
            }
        } catch (Exception ex) {
            System.out.println("Something went wrong with loadData in ExtensionPanel. " + ex);
        }

    }

    private void addItemToModel(Extension extension) {
        Object[] o = new Object[5];
        o[0] = extension.getExtensionId();
        o[1] = extension.getExtensionCode();
        o[2] = extension.getExtensionDescription();
        o[3] = extension.getExtensionDateCreated();
        o[4] = extension.getExtensionDateModified();

        model.addRow(o);
    }

    private void addNewExtension() {
        programMode = ProgramMode.ADD;
        tfCode.setText("");
        tfDescription.setText("");
        tfID.setText("");
        tfDateCreated.setText(dateFormat.format(Calendar.getInstance().getTime()));
        tfDateModified.setText(dateFormat.format(Calendar.getInstance().getTime()));

        setStates();

    }

    private void setStates() {
        boolean editable = false;
        switch (programMode) {
            case ADD:
                editable = true;
                break;
            case DISPLAY:
                editable = false;
                break;
            case CHANGE:
                editable = true;
                break;
            case DELETE:
                editable = false;
                break;
        }

        tfID.setEditable(false);
        tfID.setEnabled(false);
        tfCode.setEditable(editable);
        tfCode.setEnabled(editable);
        tfDescription.setEditable(editable);
        tfDescription.setEnabled(editable);
        tfDateCreated.setEditable(false);
        tfDateCreated.setEnabled(false);
        tfDateModified.setEditable(false);
        tfDateModified.setEnabled(false);
        btnSave.setEnabled(editable);
        btnNewExtension.setEnabled(!editable);
        btnDeleteExtension.setEnabled(!editable);
        btnChange.setEnabled(!editable);

    }

    private void saveNewData() {
        switch (programMode) {
            case ADD:
                addExtension();
                break;
            case DELETE:
                removeExtension();
                break;
            case CHANGE:
                changeExtension();
                break;
        }

        programMode = ProgramMode.DISPLAY;
        setStates();

    }

    private void addExtension() {
        try {
            Extension ext = new Extension();

            ext.setExtensionCode(tfCode.getText());
            ext.setExtensionDescription(tfDescription.getText());
            ext.setExtensionDateCreated(dateFormat.parse(tfDateCreated.getText()));
            ext.setExtensionDateModified(dateFormat.parse(tfDateModified.getText()));
            ext.setItemsoort(itemsoort);

            entityManager.getTransaction().begin();
            entityManager.persist(ext);

            entityManager.getTransaction().commit();

            listMasterTable.add(ext);
            addItemToModel(ext);

        } catch (ParseException ex) {
            System.out.println("Er is iets fout gegaan met het parsen van de datum." + ex);
        } catch (Exception ex) {
            System.out.println("Er is iets helemaal fout gegaan." + ex);
        }

    }

    private void removeExtension() {
        int response = JOptionPane.showConfirmDialog(null, displayedExtension.getExtensionCode() + " VERWIJDEREN?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (response == JOptionPane.YES_OPTION) {
            entityManager.getTransaction().begin();
            entityManager.remove(displayedExtension);
            entityManager.getTransaction().commit();
        }
        programMode = ProgramMode.DISPLAY;
        setStates();
        loadData();
    }

    private void changeExtension() {
        // Changing the displayed extension.
        try {
            entityManager.getTransaction().begin();
            if (!displayedExtension.getExtensionCode().equals(tfCode.getText())) {
                displayedExtension.setExtensionCode(tfCode.getText());
            }
            if (!displayedExtension.getExtensionDescription().equals(tfDescription.getText())) {
                displayedExtension.setExtensionDescription(tfDescription.getText());
            }
            displayedExtension.setExtensionDateModified(Calendar.getInstance().getTime());

            entityManager.getTransaction().commit();

        } catch (Exception ex) {
            System.out.println("Something went terribly wrong. " + ex);
        }
        programMode = ProgramMode.DISPLAY;
        setStates();
        loadData();
    }

    public void setSizes() {
        tfCode.setSize(tfCode.getWidth(), 30);
        tfCode.setMinimumSize(new Dimension(100, 30));
        tfCode.setPreferredSize(new Dimension(100, 30));
        tfDateCreated.setSize(tfDateCreated.getWidth(), 30);
        tfDateModified.setSize(tfDateModified.getWidth(), 30);
        tfDescription.setSize(tfDescription.getWidth(), 30);
        tfID.setSize(tfID.getWidth(), 30);

    }

    TableCellRenderer tableCellRenderer = new DefaultTableCellRenderer() {

        SimpleDateFormat f = new SimpleDateFormat("dd MMMMM yyyy");

        @Override
        public Component getTableCellRendererComponent(JTable table,
                Object value, boolean isSelected, boolean hasFocus,
                int row, int column) {
            if (value instanceof Date) {
                value = f.format(value);
            }
            return super.getTableCellRendererComponent(table, value, isSelected,
                    hasFocus, row, column);
        }
    };

    private void loadSelectionListener() {
        masterTable.getSelectionModel().addListSelectionListener(listSelectionListener);

    }

    ListSelectionListener listSelectionListener = new ListSelectionListener() {

        @Override
        public void valueChanged(ListSelectionEvent e) {
            displaySelectedRow();
        }

    };

    private void displaySelectedRow() {
        if (programMode == ProgramMode.DISPLAY) {
            int idx = masterTable.getSelectedRow();
            if (idx > -1) {
                displayedExtension = listMasterTable.get(idx);
                tfID.setText(String.valueOf(displayedExtension.getExtensionId()));
                tfCode.setText(displayedExtension.getExtensionCode());
                tfDescription.setText(displayedExtension.getExtensionDescription());
                if (displayedExtension.getExtensionDateCreated() != null) {
                    tfDateCreated.setText(dateFormat.format(displayedExtension.getExtensionDateCreated()));
                }
                if (displayedExtension.getExtensionDateModified() != null) {
                    tfDateModified.setText(dateFormat.format(displayedExtension.getExtensionDateModified()));
                }
            }
        }
    }

    private void loadItemsoortCombobox() {
        cmbItemsoort.removeAllItems();
        query = entityManager.createNamedQuery("Itemsoort.findAll", Itemsoort.class);
        listItemsoort = query.getResultList();
        for (Itemsoort is : listItemsoort) {
            cmbItemsoort.addItem(is);
            if (is.getItemsoortItemsoort().equalsIgnoreCase("nb")) {
                isOnbekend = is;
            }
        }
    }

    private void getFirstItemsoort() {

        itemsoort = (Itemsoort) cmbItemsoort.getItemAt(0);
        loadData();
    }

    static class EnterAction extends AbstractAction {
        ExtensionPanel extPanel;
        public EnterAction(ExtensionPanel extensionPanel) {
            extPanel = extensionPanel;
        }

        @Override
        public void actionPerformed(ActionEvent tf) {
            extPanel.saveNewData();
        }
    }

}
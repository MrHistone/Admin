package admin.frames.TreeViewGrid;

import admin.PublicDefault;
import admin.PublicDefault.ProgramMode;
import database.Item;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

public class TVGGrid extends JPanel {

    // Variable Declarations at end of class.

    public TVGGrid(TVGHeader tvgHeader) {
        this.tvgHeader = tvgHeader;
        initComponents();
    }

    public void loadData(List<Item> itemList) {
        model.setRowCount(0);
        if (tableList != null && tableList.size() > 0) {
            tableList.clear();
        }

        for (Item item : itemList) {
            addItemToModel(item);
        }

        if (model.getRowCount() > 0) {
            table.setRowSelectionInterval(0, 0);
        }

    }

    private void addItemToModel(Item item) {
        Object[] o = new Object[6];
        o[0] = item.getHoofditem().getHoofditemNaam();
        o[1] = item.getItemBeschrijving();
        o[2] = item.getItemsoort().toString();
        o[3] = item.getItemDatumDocument();
        o[4] = item.getItemcode().getItemcodeCode();
        o[5] = item.getItemstatus();

        model.addRow(o);
        tableList.add(item);
    }

    private void setTableConfiguration() {
        // Get all columns in table
        int cols = table.getColumnCount();
        TableColumnModel columnModel = table.getColumnModel();
        // Loop all columns and configure.       
        for (int i = 0; i < cols; i++) {
            // Match the index of the columns with the index in the config file.
            switch (i) {
                case 0:
                    // Hoofditem
                    columnModel.getColumn(i).setPreferredWidth(125);
                    columnModel.getColumn(i).setMaxWidth(125);
                    break;
                case 1:
                    // Beschrijving
                    columnModel.getColumn(i).setPreferredWidth(250);
                    break;
                case 2:
                    // Soort
                    columnModel.getColumn(i).setPreferredWidth(180);
                    columnModel.getColumn(i).setMaxWidth(180);
                    break;
                case 3:
                    // Datum
                    columnModel.getColumn(i).setPreferredWidth(150);
                    columnModel.getColumn(i).setMaxWidth(150);
                    break;
                case 4:
                    // Itemcode
                    columnModel.getColumn(i).setPreferredWidth(180);
                    columnModel.getColumn(i).setMaxWidth(180);
                    break;
                case 5:
                    // Status
                    columnModel.getColumn(i).setPreferredWidth(120);
                    columnModel.getColumn(i).setMaxWidth(120);
                    break;
            }

        }

        table.getColumnModel().getColumn(3).setCellRenderer(publicDefault.dateRenderer);
        table.getColumnModel().getColumn(5).setCellRenderer(publicDefault.statusRenderer);

        table.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        table.setRowHeight(table.getRowHeight() + 10);

    }

    private class CustomSelectionListener implements ListSelectionListener {

        @Override
        public void valueChanged(ListSelectionEvent e) {
            /*
            getValueIsAdjusting() checks whether a specific event (a change) is part of a chain, 
            if so it will return true. It will only return false when the specified event is the final one in the chain.
             */
            if (!e.getValueIsAdjusting()) {
                displaySelectedRow();
            }
        }
    }

    private void displaySelectedRow() {
        int idx = table.getSelectedRow();
        if (idx > -1) {
            Item item = tableList.get(idx);
            tvgHeader.displaySelectedItem(item);
        }

    }

    public void refreshData(Item it) {

        int selectedRow = table.getSelectedRow();

        model.setValueAt(it.getHoofditem().getHoofditemNaam(), selectedRow, 0);
        model.setValueAt(it.getItemBeschrijving(), selectedRow, 1);
        model.setValueAt(it.getItemsoort().toString(), selectedRow, 2);
        model.setValueAt(it.getItemDatumDocument(), selectedRow, 3);
        model.setValueAt(it.getItemcode().getItemcodeCode(), selectedRow, 4);
        model.setValueAt(it.getItemstatus(), selectedRow, 5);

        model.fireTableDataChanged();
        tableList.set(selectedRow, it);
    }

    private void addPopupMenuToTable() {

        JPopupMenu popupMenu = new JPopupMenu();

        menuItemAdd = new JMenuItem("Voeg een nieuw document toe");
        menuItemEdit = new JMenuItem("Bewerk geselecteerde document");
        menuItemRemove = new JMenuItem("Verwijder geselecteerde document");
        menuItemPreview = new JMenuItem("Start VoorbeeldWeergave");
        // Status leegmaken
        menuItemSetStatusLeeg = new JMenuItem("Leeg");
        // Archief
        menuItemSetStatusArchief = new JMenuItem("Archief");
        menuItemSetStatusArchief.setIcon(publicDefault.imageArchief);
        // Belangrijk
        menuItemSetStatusBelangrijk = new JMenuItem("Belangrijk");
        menuItemSetStatusBelangrijk.setIcon(publicDefault.imageBelangrijk);
        // Onbelangrijk
        menuItemSetStatusOnbelangrijk = new JMenuItem("Onbelangrijk");
        menuItemSetStatusOnbelangrijk.setIcon(publicDefault.imageOnbelangrijk);
        // Verwijderd
        menuItemSetStatusDeleted = new JMenuItem("Verwijderd");
        menuItemSetStatusDeleted.setIcon(publicDefault.imageVerwijderd);
        // Bijzonder
        menuItemSetStatusBijzonder = new JMenuItem("Bijzonder");
        menuItemSetStatusBijzonder.setIcon(publicDefault.imageBijzonder);
        JMenu subMenuStatus = new JMenu("Zet status");
        subMenuStatus.add(menuItemSetStatusLeeg);
        subMenuStatus.add(menuItemSetStatusArchief);
        subMenuStatus.add(menuItemSetStatusBelangrijk);
        subMenuStatus.add(menuItemSetStatusOnbelangrijk);
        subMenuStatus.add(menuItemSetStatusBijzonder);
        subMenuStatus.add(menuItemSetStatusDeleted);
        popupMenu.add(subMenuStatus);
        popupMenu.add(menuItemAdd);
        popupMenu.add(menuItemEdit);
        popupMenu.add(menuItemRemove);
        popupMenu.add(menuItemPreview);

        menuItemAdd.addActionListener(popupActionListener);
        menuItemEdit.addActionListener(popupActionListener);
        menuItemRemove.addActionListener(popupActionListener);
        menuItemPreview.addActionListener(popupActionListener);
        menuItemSetStatusArchief.addActionListener(popupActionListener);
        menuItemSetStatusBelangrijk.addActionListener(popupActionListener);
        menuItemSetStatusDeleted.addActionListener(popupActionListener);
        menuItemSetStatusOnbelangrijk.addActionListener(popupActionListener);
        menuItemSetStatusBijzonder.addActionListener(popupActionListener);
        menuItemSetStatusLeeg.addActionListener(popupActionListener);

        // Override events on the popup menu so the row that is clicked can be selected.
        popupMenu.addPopupMenuListener(new PopupMenuListener() {

            @Override
            public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        int rowAtPoint = table.rowAtPoint(SwingUtilities.convertPoint(popupMenu, new Point(0, 0), table));
                        if (rowAtPoint > -1) {
                            table.setRowSelectionInterval(rowAtPoint, rowAtPoint);
                        }
                    }
                });
            }

            @Override
            public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void popupMenuCanceled(PopupMenuEvent e) {
                // TODO Auto-generated method stub

            }
        });

        table.setComponentPopupMenu(popupMenu);

    }

    /**
     * Opens the editscreen with the selected row.
     *
     * @param programMode ProgramMode, Add Change Display Delete
     */
    private void openTVGEditItem(ProgramMode programMode) {
        int idx = table.getSelectedRow();
        // Records can be in different order (sorting). Use convertRowIndexToModel.
        idx = table.convertRowIndexToModel(idx);
        if (idx > -1) {
            if (tvgEditItem == null) {
                tvgEditItem = new TVGEditItem(tvgGrid);
            }
            tvgEditItem.loadItem(tableList.get(idx).getItemId(), programMode);
            tvgEditItem.setVisible(true);
        }

    }

    private class PopupActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent event) {
            JMenuItem menuItem = (JMenuItem) event.getSource();
            if (menuItem == menuItemAdd) {
                openTVGEditItem(ProgramMode.ADD);
            }
            if (menuItem == menuItemEdit) {
                openTVGEditItem(ProgramMode.CHANGE);
            }
            if (menuItem == menuItemRemove) {
                openTVGEditItem(ProgramMode.DELETE);
            }
            if (menuItem == menuItemPreview) {

            }
            if (menuItem == menuItemSetStatusLeeg) {

            }
            if (menuItem == menuItemSetStatusArchief) {

            }
            if (menuItem == menuItemSetStatusBelangrijk) {

            }
            if (menuItem == menuItemSetStatusDeleted) {

            }
            if (menuItem == menuItemSetStatusOnbelangrijk) {

            }
            if (menuItem == menuItemSetStatusBijzonder) {

            }
        }

    }

    private class CustomTableModel extends DefaultTableModel {

        // Disable cell editing for all cells in the grid.
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    }

    public class TableMouseListener extends MouseAdapter {

        @Override
        public void mousePressed(MouseEvent event) {
            if (event.getClickCount() == 2 && event.getButton() == MouseEvent.BUTTON1) {
                openTVGEditItem(ProgramMode.CHANGE);
//                JOptionPane.showMessageDialog(tvgGrid, "Eggs are not supposed to be green.", "Inane Error", JOptionPane.ERROR_MESSAGE);
            }

        }
    }

    private JTable table;
    private JScrollPane scrollPane;
    private DefaultTableModel model;
    private String[] columnNames;
    private ArrayList<Item> tableList;
    private PublicDefault publicDefault;
    private final TVGHeader tvgHeader;
    private JMenuItem menuItemAdd;
    private JMenuItem menuItemEdit;
    private JMenuItem menuItemRemove;
    private JMenuItem menuItemSetStatusLeeg;
    private JMenuItem menuItemSetStatusBelangrijk;
    private JMenuItem menuItemSetStatusOnbelangrijk;
    private JMenuItem menuItemSetStatusArchief;
    private JMenuItem menuItemSetStatusDeleted;
    private JMenuItem menuItemSetStatusBijzonder;
    private JMenuItem menuItemPreview;
    private PopupActionListener popupActionListener;
    private TVGEditItem tvgEditItem;
    private TVGGrid tvgGrid;

    
    
    private void initComponents() {
        publicDefault = new PublicDefault();
        tvgGrid = this;
        table = new JTable();
        tableList = new ArrayList<>();
        // Allocate memory for column-names.
        columnNames = new String[6];
        columnNames[0] = "Hoofditem";
        columnNames[1] = "Beschrijving";
        columnNames[2] = "Soort";
        columnNames[3] = "Datum";
        columnNames[4] = "Itemcode";
        columnNames[5] = "Status";
        model = new CustomTableModel();
        model.setDataVector(new Object[][]{}, columnNames);
        table.setModel(model);
        table.getSelectionModel().addListSelectionListener(new CustomSelectionListener());
        table.addMouseListener(new TableMouseListener());
        popupActionListener = new PopupActionListener();
        addPopupMenuToTable();
        setTableConfiguration();

        scrollPane = new JScrollPane(table);
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 1;
        c.weighty = 1;
        c.fill = GridBagConstraints.BOTH;

        add(scrollPane, c);

        setBorder(BorderFactory.createLineBorder(Color.WHITE));
        
        
        
        
        

    }
    
}

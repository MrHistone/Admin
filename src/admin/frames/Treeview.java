package admin.frames;

import admin.PublicDefault.ObjectInfoType;
import database.Hoofditem;
import database.Item;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormatSymbols;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.ToolTipManager;
import javax.swing.event.TreeExpansionEvent;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.event.TreeWillExpandListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.ExpandVetoException;

public class Treeview extends javax.swing.JPanel {

    private JTree tree;
    private Query query;
    private EntityManager entityManager;
    private List<Hoofditem> listHoofdItems;
    private List<Item> listItems;
    private List<Integer> listYears;
    private List<Integer> listMonths;
    private DefaultMutableTreeNode topNode;
    private DefaultTreeModel model;
    private GridLayout gridLayout;
    private JPanel itemsPanel;

    public Treeview() {
        initComponents();
        itemsPanel = new JPanel();
        itemsPanel.setLayout(new GridBagLayout());
        jPanelItems.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.anchor = GridBagConstraints.NORTHWEST;
        jPanelItems.add(itemsPanel, c);

        entityManager = Persistence.createEntityManagerFactory("administration").createEntityManager();
        topNode = new DefaultMutableTreeNode("Administratie");
        tree = new JTree(topNode);
        createNodes(topNode);
        tree.addTreeSelectionListener(new treeSelectionListener());
        tree.addTreeWillExpandListener(new treeWillExpandListener());
        // Set the icon for leaf nodes.
        tree.setCellRenderer(new TreeCellRenderer());

        // Enable tool tips.
        ToolTipManager.sharedInstance().registerComponent(tree);

        jScrollPaneTreeview.setViewportView(tree);

        tree.expandRow(2);

        model = (DefaultTreeModel) tree.getModel();
        model.reload();

        jScrollPaneTreeview.setMinimumSize(new Dimension(200, 500));
        jScrollPaneItems.setMinimumSize(new Dimension(1000, 500));

//        testWithButtonsAndImages();
    }

    private void createNodes(DefaultMutableTreeNode topNode) {
        DefaultMutableTreeNode hoofdItemNode = null;
        DefaultMutableTreeNode yearNode = null;
        ObjectInfoItem objInfItem;

        // Fetch all hoofdItems from db.
        query = entityManager.createNamedQuery("Hoofditem.findAllOrdered", Hoofditem.class);
        listHoofdItems = query.getResultList();
        for (Hoofditem hi : listHoofdItems) {
            objInfItem = new ObjectInfoItem(ObjectInfoType.HOOFDITEM);
            objInfItem.hoofdItem = hi;
            hoofdItemNode = new DefaultMutableTreeNode(objInfItem);
            // Only add if items are stored.
            // Fetch all Items of the HoofdItem
            query = entityManager.createNamedQuery("Item.findByItemYear", Item.class);
            query.setParameter("hoofditemId", hi.getHoofditemId());
            listYears = query.getResultList();
            if (listYears.size() > 0) {
                topNode.add(hoofdItemNode);
                for (Integer y : listYears) {
                    objInfItem = new ObjectInfoItem(ObjectInfoType.YEAR);
                    objInfItem.hoofdItem = hi;
                    objInfItem.year = y;
                    yearNode = new DefaultMutableTreeNode(objInfItem);
                    yearNode.add(new DefaultMutableTreeNode("dummy"));
                    hoofdItemNode.add(yearNode);
                }
            }
        }
    }

    protected ImageIcon createImageIcon(String path, String description) {
        java.net.URL imgURL = getClass().getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL, description);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }

    private void loadItems(Object nodeInfo) {
        if (listItems != null) {
            listItems.clear();
        }

        ObjectInfoItem objInfItem = (ObjectInfoItem) nodeInfo;
        switch (objInfItem.objectInfoType) {
            case HOOFDITEM:
                query = entityManager.createNamedQuery("Item.findByItemHoofditemId", Item.class);
                query.setParameter("hoofditemId", objInfItem.hoofdItem.getHoofditemId());
                listItems = query.getResultList();
                break;
            case YEAR:
                query = entityManager.createNamedQuery("Item.findItemsByYear", Item.class);
                query.setParameter("hoofditemId", objInfItem.hoofdItem.getHoofditemId());
                query.setParameter("year", objInfItem.year);
                listItems = query.getResultList();
                break;
            case MONTH:
                query = entityManager.createNamedQuery("Item.findItemsByMonth", Item.class);
                query.setParameter("hoofditemId", objInfItem.hoofdItem.getHoofditemId());
                query.setParameter("year", objInfItem.year);
                query.setParameter("month", objInfItem.month);
                listItems = query.getResultList();
                break;
        }

//        System.out.println("listItems bevat " + listItems.size() + " items. Hoofditem is " + objInfItem.hoofdItem.getHoofditemNaam() + ".");
        itemsPanel.removeAll();

//        jPanelItems.removeAll();
//        jPanelItems.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(5, 5, 5, 5);
        c.gridwidth = 1;
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.NORTHWEST;
        // Five buttonPanels (Maximum) next to eachother.
        int panelCount = 0;
        int panelRow = 0;
        for (Item item : listItems) {
            ButtonPanel buttonPanel = new ButtonPanel(item);
            if (panelCount == 5) {
                panelCount = 0;
                panelRow++;
            }
            c.gridx = panelCount;
            c.gridy = panelRow;

            panelCount++;

            System.out.println(item.getItemBeschrijving());
            System.out.println("x: " + c.gridx + "\ty: " + c.gridy);

            itemsPanel.add(buttonPanel, c);
//            jPanelItems.add(buttonPanel, c);
        }

        itemsPanel.setBorder(BorderFactory.createLineBorder(Color.RED));
        itemsPanel.revalidate();
        itemsPanel.repaint();

//        jPanelItems.setBorder(BorderFactory.createLineBorder(Color.RED));
//        jPanelItems.revalidate();
//        jPanelItems.repaint();
    }

    private void testWithButtonsAndImages() {
        ImageIcon pdfIcon = createImageIcon("/resources/item/Adobe-PDF-Document-icon.png", "Documents");
        ImageIcon wordIcon = createImageIcon("/resources/item/Document-Microsoft-Word-icon.png", "Documents");

        FlowLayout flowLayout = new FlowLayout(FlowLayout.LEFT, 20, 20);
        JPanel newPanel = new JPanel();
        newPanel.setLayout(flowLayout);
        gridLayout = new GridLayout(0, 1);

        jPanelItems.setLayout(gridLayout);

        JButton jButton1 = new JButton("Button 1", pdfIcon);
        JButton jButton2 = new JButton("Button 2", pdfIcon);
        JButton jButton3 = new JButton("Button 3", wordIcon);
        JButton jButton4 = new JButton("Button 4", wordIcon);

        newPanel.add(jButton1);
        newPanel.add(jButton2);
        newPanel.add(jButton3);
        newPanel.add(jButton4);

        JPanel newPanel2 = new JPanel();
        newPanel2.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 20));
        newPanel2.add(new JButton("Button 5", pdfIcon));
        newPanel2.add(new JButton("Button 6", pdfIcon));
        newPanel2.add(new JButton("Button 7", pdfIcon));
        newPanel2.add(new JButton("Button 8", pdfIcon));

        JPanel newPanel3 = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 20));
        newPanel3.add(new JButton("Button 9", wordIcon));

        newPanel3.add(new JButton("Button 10", wordIcon));
        newPanel3.add(new JButton("Button 11", wordIcon));
        JButton jButton12 = new JButton("Button 12", wordIcon);
        JButton jButton13 = new JButton("Button 13", pdfIcon);
        jButton13.addActionListener(new ActionListenerButton());
        jButton12.addActionListener(new ActionListenerButton());
        newPanel3.add(jButton12);

        newPanel3.add(jButton13);
        jPanelItems.add(newPanel);
        jPanelItems.add(newPanel2);
        jPanelItems.add(newPanel3);
    }

    private class ActionListenerButton implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String actionCmd = e.getActionCommand();
            switch (actionCmd) {
                case "Button 12":
                    System.out.println("New rows.");
//                    gridLayout.setRows(3);

                    break;
                case "Button 13":

                    break;
            }

            System.out.println("ActionListener: " + e.getActionCommand());
        }
    }

    private class ObjectInfoItem {

        public Hoofditem hoofdItem;
        public ObjectInfoType objectInfoType;
        public Integer year;
        public Integer month;
        public boolean isDataFetched = false;

        public ObjectInfoItem(ObjectInfoType objectInfoType) {
            this.objectInfoType = objectInfoType;
        }

        @Override
        public String toString() {
            String str = "";
            switch (objectInfoType) {
                case HOOFDITEM:
                    str = hoofdItem.getHoofditemNaam();
                    break;
                case YEAR:
                    str = year.toString();
                    break;
                case MONTH:
                    str = new DateFormatSymbols().getMonths()[month - 1];
                    break;
            }
            return str;
        }
    }

    private class TreeCellRenderer extends DefaultTreeCellRenderer {

        Icon monthIcon, yearIcon;

        public TreeCellRenderer() {
            monthIcon = createImageIcon("/resources/folder-documents-icon.png", "Documents");
            yearIcon = createImageIcon("/resources/calendar-selection-all-icon.png", "Folder");
        }

        @Override
        public Component getTreeCellRendererComponent(JTree tree, Object value,
                boolean sel, boolean expanded, boolean leaf, int row, boolean hasFocus) {
            super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);
            setToolTipText(null); // no tool tip
            DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;

            if (row > 0) {
                ObjectInfoItem objInfItem = (ObjectInfoItem) node.getUserObject();

                if (objInfItem.objectInfoType == ObjectInfoType.MONTH) {
                    setIcon(monthIcon);
                    setToolTipText(objInfItem.hoofdItem.getHoofditemNaam() + " - " + objInfItem.month.toString() + " " + objInfItem.year.toString());
                }

                if (objInfItem.objectInfoType == ObjectInfoType.YEAR) {
                    setIcon(yearIcon);
                    setToolTipText(objInfItem.hoofdItem.getHoofditemNaam() + " - " + objInfItem.year.toString());
                }
            }

            return this;
        }

    }

    private class treeSelectionListener implements TreeSelectionListener {

        @Override
        public void valueChanged(TreeSelectionEvent e) {
            DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
            if (node == null) {
                return;
            }

            Object nodeInfo = node.getUserObject();

            loadItems(nodeInfo);
        }

    }

    private class treeWillExpandListener implements TreeWillExpandListener {

        @Override
        public void treeWillExpand(TreeExpansionEvent event) throws ExpandVetoException {
            // Find out what last component is.
            DefaultMutableTreeNode node = (DefaultMutableTreeNode) event.getPath().getLastPathComponent();

            ObjectInfoItem objInfItem = (ObjectInfoItem) node.getUserObject();
            ObjectInfoItem objInfItemSub;

            if (objInfItem.objectInfoType == ObjectInfoType.YEAR) {
                // First check to see if year has been fetched already.
                if (!objInfItem.isDataFetched) {
                    // Clear node of all children (should be only one dummy).
                    node.removeAllChildren();
                    // Now fetch the data.                    
                    query = entityManager.createNamedQuery("Item.findMonthsByYear", Item.class);
                    query.setParameter("hoofditemId", objInfItem.hoofdItem.getHoofditemId());
                    query.setParameter("year", objInfItem.year);
                    listMonths = query.getResultList();
                    for (Integer in : listMonths) {
                        objInfItemSub = new ObjectInfoItem(ObjectInfoType.MONTH);
                        objInfItemSub.year = objInfItem.year;
                        objInfItemSub.hoofdItem = objInfItem.hoofdItem;
                        objInfItemSub.month = in;
                        node.add(new DefaultMutableTreeNode(objInfItemSub));
                    }
                    objInfItem.isDataFetched = true;
                }
            }
        }

        @Override
        public void treeWillCollapse(TreeExpansionEvent event) throws ExpandVetoException {

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

        jSplitPane = new javax.swing.JSplitPane();
        jScrollPaneTreeview = new javax.swing.JScrollPane();
        jScrollPaneItems = new javax.swing.JScrollPane();
        jPanelItems = new javax.swing.JPanel();

        jSplitPane.setLeftComponent(jScrollPaneTreeview);

        jPanelItems.setLayout(new java.awt.BorderLayout());
        jScrollPaneItems.setViewportView(jPanelItems);

        jSplitPane.setRightComponent(jScrollPaneItems);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSplitPane, javax.swing.GroupLayout.DEFAULT_SIZE, 821, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSplitPane)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanelItems;
    private javax.swing.JScrollPane jScrollPaneItems;
    private javax.swing.JScrollPane jScrollPaneTreeview;
    private javax.swing.JSplitPane jSplitPane;
    // End of variables declaration//GEN-END:variables
}

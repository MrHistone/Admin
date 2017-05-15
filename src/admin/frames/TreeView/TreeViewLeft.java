package admin.frames.TreeView;

import admin.PublicDefault;
import database.Hoofditem;
import database.Item;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.text.DateFormatSymbols;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
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

public class TreeViewLeft extends JPanel {

    private JTree tree;
    private Query query;
    private EntityManager entityManager;
    private List<Hoofditem> listHoofdItems;
    private List<Item> listItems;
    private List<Integer> listYears;
    private List<Integer> listMonths;
    private DefaultMutableTreeNode topNode;
    private DefaultTreeModel model;
    TreeViewRightSection treeViewRightSection;

    public TreeViewLeft(TreeViewRightSection treeViewRightSection) {
        this.treeViewRightSection = treeViewRightSection;
        initComponents();
    }

    private void initComponents() {
        entityManager = Persistence.createEntityManagerFactory("administration").createEntityManager();
        GridBagConstraints c = new GridBagConstraints();
        c.anchor = GridBagConstraints.NORTHWEST;
        // Unless you specify at least one non-zero value for weightx or weighty,
        // all the components clump together in the center of their container.
        c.weightx = 1;
        c.weighty = 1;
        c.fill = GridBagConstraints.BOTH;

        setLayout(new GridBagLayout());
        topNode = new DefaultMutableTreeNode("Administratie");
        tree = new JTree(topNode);
        JScrollPane scrollTreeview = new JScrollPane(tree);

        add(scrollTreeview, c);
        fillTreeView(topNode);

        setPreferredSize(new Dimension(WIDTH, HEIGHT));

        tree.addTreeSelectionListener(new treeSelectionListener());
        tree.addTreeWillExpandListener(new treeWillExpandListener());
        // Set the icon for leaf nodes.
        tree.setCellRenderer(new TreeCellRenderer());

        // Enable tool tips.
        ToolTipManager.sharedInstance().registerComponent(tree);

        tree.expandRow(2);

        model = (DefaultTreeModel) tree.getModel();
        model.reload();

    }

    private void fillTreeView(DefaultMutableTreeNode topNode) {
        DefaultMutableTreeNode hoofdItemNode = null;
        DefaultMutableTreeNode yearNode = null;
        ObjectInfoItem objInfItem;

        // Fetch all hoofdItems from db.
        query = entityManager.createNamedQuery("Hoofditem.findAllOrdered", Hoofditem.class);
        listHoofdItems = query.getResultList();
        for (Hoofditem hi : listHoofdItems) {
            objInfItem = new ObjectInfoItem(PublicDefault.ObjectInfoType.HOOFDITEM);
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
                    objInfItem = new ObjectInfoItem(PublicDefault.ObjectInfoType.YEAR);
                    objInfItem.hoofdItem = hi;
                    objInfItem.year = y;
                    yearNode = new DefaultMutableTreeNode(objInfItem);
                    yearNode.add(new DefaultMutableTreeNode("dummy"));
                    hoofdItemNode.add(yearNode);
                }
            }
        }
    }

    private class ObjectInfoItem {

        public Hoofditem hoofdItem;
        public PublicDefault.ObjectInfoType objectInfoType;
        public Integer year;
        public Integer month;
        public boolean isDataFetched = false;

        public ObjectInfoItem(PublicDefault.ObjectInfoType objectInfoType) {
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

    private class treeSelectionListener implements TreeSelectionListener {

        @Override
        public void valueChanged(TreeSelectionEvent e) {
            DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
            if (node == null) {
                return;
            }

            Object nodeInfo = node.getUserObject();
            System.out.println(getWidth());
            loadItems(nodeInfo);
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

        treeViewRightSection.addButtonPanels(listItems);

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

                if (objInfItem.objectInfoType == PublicDefault.ObjectInfoType.MONTH) {
                    setIcon(monthIcon);
                    setToolTipText(objInfItem.hoofdItem.getHoofditemNaam() + " - " + objInfItem.month.toString() + " " + objInfItem.year.toString());
                }

                if (objInfItem.objectInfoType == PublicDefault.ObjectInfoType.YEAR) {
                    setIcon(yearIcon);
                    setToolTipText(objInfItem.hoofdItem.getHoofditemNaam() + " - " + objInfItem.year.toString());
                }
            }

            return this;
        }

    }

    private class treeWillExpandListener implements TreeWillExpandListener {

        @Override
        public void treeWillExpand(TreeExpansionEvent event) throws ExpandVetoException {
            // Find out what last component is.
            DefaultMutableTreeNode node = (DefaultMutableTreeNode) event.getPath().getLastPathComponent();

            ObjectInfoItem objInfItem = (ObjectInfoItem) node.getUserObject();
            ObjectInfoItem objInfItemSub;

            if (objInfItem.objectInfoType == PublicDefault.ObjectInfoType.YEAR) {
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
                        objInfItemSub = new ObjectInfoItem(PublicDefault.ObjectInfoType.MONTH);
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
}

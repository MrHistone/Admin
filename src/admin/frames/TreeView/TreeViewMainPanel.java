package admin.frames.TreeView;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

public class TreeViewMainPanel extends JPanel {

    private TreeViewLeft treeViewLeft;
    private TreeViewRightSection treeViewRightSection;
    private JSplitPane splitPane;

    public TreeViewMainPanel() {
        initComponents();
    }

    private void initComponents() {
        treeViewRightSection = new TreeViewRightSection();
        treeViewLeft = new TreeViewLeft(treeViewRightSection);

        treeViewLeft.setMinimumSize(new Dimension(250, 0));
        treeViewLeft.setPreferredSize(new Dimension(250, 0));
        
        GridBagConstraints c = new GridBagConstraints();
        c.anchor = GridBagConstraints.NORTHWEST;
        // Unless you specify at least one non-zero value for weightx or weighty,
        // all the components clump together in the center of their container.
        c.weightx = 1;
        c.weighty = 1;
        c.fill = GridBagConstraints.VERTICAL;

        setLayout(new GridBagLayout());

        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 0.2;
        c.gridheight = 1;
        c.gridwidth = 1;
        // Treeview in a scrollpane on the left side.
        add(treeViewLeft, c);

        // Rest of the frame goes to the right.
        c.weightx = 0.8;
        c.weighty = 1;
        c.gridx = 1;
        c.gridy = 0;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.VERTICAL;

        add(treeViewRightSection, c);
        setPreferredSize(new Dimension(1200, 600));
    }

}

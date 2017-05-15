package admin.frames.TreeView;

import database.Item;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class TreeViewRightSection extends JPanel{
    TreeViewTopRightSection treeViewTopRightSection;
    TreeViewBottomRightSection treeViewBottomRightSection;
            
    public TreeViewRightSection(){
        initComponents();
    }
    
    private void initComponents(){
        treeViewBottomRightSection = new TreeViewBottomRightSection();
        treeViewTopRightSection = new TreeViewTopRightSection();
        
        GridBagConstraints c = new GridBagConstraints();
        c.anchor = GridBagConstraints.NORTHWEST;
        // Unless you specify at least one non-zero value for weightx or weighty,
        // all the components clump together in the center of their container.
        c.weightx = 1;
        c.weighty = 1;
        c.fill = GridBagConstraints.NONE;

        setLayout(new GridBagLayout());
        // Bottom section to display all items.
        c.gridx = 0;
        c.gridy = 0;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        // Top section for searching, buttons etc.
        
        add(treeViewTopRightSection, c);

        // Bottom section to display all items.
        c.gridx = 0;
        c.gridy = 1;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.BOTH;
        
        add(treeViewBottomRightSection, c);
        
        
        setBorder(BorderFactory.createLineBorder(Color.PINK));
        
    }
    
    public void addButtonPanels(List<Item> listItems) {
        treeViewBottomRightSection.addButtonPanels(listItems);
    }
    
    
}

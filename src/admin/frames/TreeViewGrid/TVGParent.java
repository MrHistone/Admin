package admin.frames.TreeViewGrid;

import database.Item;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class TVGParent extends JPanel {

    private TVGHeader tvgHeader;
    private TVGGrid tvgGrid;

    public TVGParent() {
        initComponents();
    }

    private void initComponents() {
        tvgHeader = new TVGHeader();
        tvgGrid = new TVGGrid(tvgHeader);

        GridBagConstraints c = new GridBagConstraints();
        c.anchor = GridBagConstraints.NORTHWEST;
        // Unless you specify at least one non-zero value for weightx or weighty,
        // all the components clump together in the center of their container.
        c.weightx = 1;
        c.weighty = 1;

        setLayout(new GridBagLayout());
        // Bottom section to display all items.
        c.gridx = 0;
        c.gridy = 0;
        c.fill = GridBagConstraints.BOTH;
        // Top section for details, searching, buttons etc.

        c.weighty = 0;
        tvgHeader.setPreferredSize(new Dimension(0, 130));
        tvgHeader.setMinimumSize(new Dimension(0, 130));
        add(tvgHeader, c);

        // Bottom section to display all items.
        c.gridx = 0;
        c.gridy = 1;
//        c.gridheight = 1;
//        c.gridwidth = 1;
        c.fill = GridBagConstraints.BOTH;
        c.weighty = 1;
        add(tvgGrid, c);

//        setBorder(BorderFactory.createLineBorder(Color.BLUE));

    }

    public void loadGrid(List<Item> listItems) {
        tvgGrid.loadData(listItems);
    }

}

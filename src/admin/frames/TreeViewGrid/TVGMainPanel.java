package admin.frames.TreeViewGrid;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JPanel;

public class TVGMainPanel extends JPanel {

    private TVGTreeView tvgTreeView;
    private TVGParent tvgParent;
    private TVGDetail tvgDetail;
    private TVGDetailNetbeans tvgDetailNetbeans;

    public TVGMainPanel() {
        initComponents();
    }

    private void initComponents() {
        tvgParent = new TVGParent();
        tvgTreeView = new TVGTreeView(tvgParent);
        tvgDetail = new TVGDetail(tvgTreeView);
        tvgDetailNetbeans = new TVGDetailNetbeans(tvgTreeView);
                
        tvgTreeView.setMinimumSize(new Dimension(185, 0));
        tvgTreeView.setPreferredSize(new Dimension(185, 0));

        tvgDetail.setMinimumSize(new Dimension(185, 0));
        tvgDetail.setPreferredSize(new Dimension(185, 0));

        tvgDetailNetbeans.setMinimumSize(new Dimension(185, 0));
        tvgDetailNetbeans.setPreferredSize(new Dimension(185, 0));
        
        GridBagConstraints c = new GridBagConstraints();
        c.anchor = GridBagConstraints.NORTHWEST;
        // Unless you specify at least one non-zero value for weightx or weighty,
        // all the components clump together in the center of their container.
        c.weightx = 1;
        c.weighty = 1;
        c.fill = GridBagConstraints.BOTH;

        setLayout(new GridBagLayout());

        c.gridx = 0;
        c.gridy = 0;
//        c.gridheight = 1;
//        c.gridwidth = 1;
        // Treeview in a scrollpane on the left side.
        // Weightx should be 0, it should not occupy any remaining space on the screen.
        c.weightx = 0;
        add(tvgTreeView, c);

        // Rest of the frame goes to the right.
        c.gridx = 1;
        c.gridy = 0;
//        c.gridheight = 1;
//        c.gridwidth = 1;
        // Weightx should be 1, it should occupy all the remaining space on the screen.
        c.weightx = 1;
        c.fill = GridBagConstraints.BOTH;

        add(tvgParent, c);

        // Detail section
        c.gridx = 2;
        c.gridy = 0;
        c.weightx = 0;
        c.fill = GridBagConstraints.BOTH;
        add(tvgDetailNetbeans, c);

        setPreferredSize(new Dimension(1200, 600));

//        setBorder(BorderFactory.createLineBorder(Color.BLUE));
    }

}

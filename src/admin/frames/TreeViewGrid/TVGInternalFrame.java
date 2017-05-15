package admin.frames.TreeViewGrid;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.BorderFactory;
import javax.swing.JInternalFrame;

public class TVGInternalFrame extends JInternalFrame {

    public TVGInternalFrame() {
        initComponents();
    }

    private void initComponents() {
        GridBagConstraints c = new GridBagConstraints();
        c.anchor = GridBagConstraints.NORTHWEST;
        // Unless you specify at least one non-zero value for weightx or weighty,
        // all the components clump together in the center of their container.
        c.weightx = 1;
        c.weighty = 1;
        c.fill = GridBagConstraints.BOTH;
        setLayout(new GridBagLayout());
//        setBorder(BorderFactory.createLineBorder(Color.green));

        TVGMainPanel tvgMainPanel = new TVGMainPanel();
        add(tvgMainPanel, c);

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        pack();
    }

}

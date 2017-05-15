package admin.frames.TreeView;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class TreeViewTopRightSection extends JPanel {

    public TreeViewTopRightSection() {

        initComponents();
    }

    private void initComponents() {
        GridBagConstraints c = new GridBagConstraints();
        c.anchor = GridBagConstraints.NORTHWEST;
        // Unless you specify at least one non-zero value for weightx or weighty,
        // all the components clump together in the center of their container.
        c.weightx = 1;
        c.weighty = 1;
        c.fill = GridBagConstraints.NONE;

        setLayout(new GridBagLayout());

        // Label for what is clicked in the treeview
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 0.2;
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.fill = GridBagConstraints.HORIZONTAL;

        JLabel lbl0 = new JLabel("Belastingen");
        add(lbl0, c);

        c.gridx = 0;
        c.gridy = 1;
        c.weightx = 0.2;

        JLabel lbl1 = new JLabel("Zoeken: ");
        add(lbl1, c);
        c.gridx = 1;
        c.gridy = 1;
        c.weightx = 0.8;

        JTextField searchTxt = new JTextField();

        add(searchTxt, c);

        setBorder(BorderFactory.createLineBorder(Color.BLUE));

    }

}

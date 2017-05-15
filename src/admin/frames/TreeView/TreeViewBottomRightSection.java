package admin.frames.TreeView;

import admin.frames.ButtonPanel;
import database.Item;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class TreeViewBottomRightSection extends JPanel {

    private GridBagConstraints c;
    private JPanel panelForm;

    public TreeViewBottomRightSection() {
        initComponents();

    }

    private void initComponents() {
        setLayout(new GridBagLayout());
        panelForm = new JPanel();
        
        
        c = new GridBagConstraints();
        
        c.gridwidth = 1;
        
        c.anchor = GridBagConstraints.NORTHWEST;
        // Unless you specify at least one non-zero value for weightx or weighty,
        // all the components clump together in the center of their container.
        c.weightx = 1;
        c.weighty = 1;
        c.fill = GridBagConstraints.NONE;

        add(panelForm,c);
        panelForm.setLayout(new GridBagLayout());
        c.insets = new Insets(5, 5, 5, 5);
        setBorder(BorderFactory.createLineBorder(Color.BLUE));
        
    }

    public void addButtonPanels(List<Item> listItems) {
        panelForm.removeAll();
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

//            System.out.println(item.getItemBeschrijving());
//            System.out.println("x: " + c.gridx + "\ty: " + c.gridy);

            panelForm.add(buttonPanel, c);
        }
        panelForm.revalidate();
        panelForm.repaint();
        revalidate();
        repaint();
        
    }

}

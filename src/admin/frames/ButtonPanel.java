package admin.frames;

import admin.PublicDefault;
import database.Item;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ButtonPanel extends JPanel {

    private Item item;
    private PublicDefault publicDefault;
    private JPanel panelForm;
    private Desktop desktop;
    private GridBagConstraints c;

    public ButtonPanel(Item item) {
        this.item = item;
        desktop = Desktop.getDesktop();
        publicDefault = new PublicDefault();
        panelForm = new JPanel();
        add(panelForm);
        c = new GridBagConstraints();
        panelForm.setLayout(new GridBagLayout());

        // MainButton        
        JButton butMain = new JButton();
        butMain.setPreferredSize(new Dimension(128, 128));
        butMain.addActionListener(mainButActList);
        String toolTip = "<html><b>" 
                + item.getItemBeschrijving() + "</b><br><i> " 
                + item.getItemsoort().getItemsoortItemsoort() 
                + "</i></html>";
        butMain.setToolTipText(toolTip);
        String itemSoort = item.getItemsoort().getItemsoortItemsoort();
        switch (itemSoort) {
            case "word":
                butMain.setIcon(publicDefault.wordIcon);
                break;
            case "pdf":
                butMain.setIcon(publicDefault.pdfIcon);
                break;
            case "img":
                butMain.setIcon(publicDefault.imgIcon);
                break;
            case "text":
                butMain.setIcon(publicDefault.txtIcon);
                break;
            case "xls":
                butMain.setIcon(publicDefault.excelIcon);
                break;
            default:
                butMain.setIcon(publicDefault.docIcon);
                break;
        }

        c.insets = new Insets(5, 5, 5, 5);
        c.gridx = 1;
        c.gridy = 0;
        c.gridwidth = 6;
        c.fill = GridBagConstraints.NONE;
        panelForm.add(butMain, c);

        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 7;
        c.fill = GridBagConstraints.NONE;
        JLabel label = new JLabel(item.getItemBeschrijving());
        label.setPreferredSize(new Dimension(150, 30));
        label.setToolTipText(toolTip);
        panelForm.add(label, c);

        c.gridx = 1;
        c.gridy = 2;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.NONE;
        JButton but1 = new JButton("But1");
        but1.setPreferredSize(new Dimension(35, 25));
        panelForm.add(but1, c);

        c.gridx = 2;
        c.gridy = 2;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.NONE;
        JButton but2 = new JButton("But2");
        but2.setPreferredSize(new Dimension(35, 25));
        panelForm.add(but2, c);

        c.gridx = 3;
        c.gridy = 2;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.NONE;
        JButton but3 = new JButton("But3");
        but3.setPreferredSize(new Dimension(35, 25));
        panelForm.add(but3, c);

        panelForm.setBorder(BorderFactory.createLineBorder(Color.black));

    }

    ActionListener mainButActList = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Open het bestand.
            try {
                desktop.open(new File(item.getItemLocatie()));
            } catch (IOException ex) {
                System.out.println("An IOException with opening the file. ");
            } catch (IllegalArgumentException ex) {
                System.out.println("The file cannot be found. " + ex);
            } catch (Exception ex) {
                System.out.println("An Exception occurred with opening the file. " + ex);
            }
        }
    };

}

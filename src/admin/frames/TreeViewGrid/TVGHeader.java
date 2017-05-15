package admin.frames.TreeViewGrid;

import admin.PublicDefault;
import database.Item;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TVGHeader extends JPanel {

    private JButton butItem;
    private JLabel lblDescription;
    private JLabel lblCode;
    private JLabel lblStatus;
    private JLabel lblLocation;
    private JPanel innerPanel;
    private JLabel lblCodeText;
    private JLabel lblStatusText;
    private JLabel lblLocationText;
    private PublicDefault publicDefault;


    public TVGHeader() {
        initComponents();
    }

    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        publicDefault = new PublicDefault();
        innerPanel = new javax.swing.JPanel();
        butItem = new javax.swing.JButton();
        lblDescription = new javax.swing.JLabel();
        lblCode = new javax.swing.JLabel();
        lblStatus = new javax.swing.JLabel();
        lblLocation = new javax.swing.JLabel();
        lblCodeText = new javax.swing.JLabel();
        lblStatusText = new javax.swing.JLabel();
        lblLocationText = new javax.swing.JLabel();
        setPreferredSize(new java.awt.Dimension(600, 128));
        setLayout(new java.awt.GridBagLayout());

        innerPanel.setLayout(new java.awt.GridBagLayout());

        butItem.setText("");
        butItem.setPreferredSize(new java.awt.Dimension(128, 128));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 6;
        gridBagConstraints.ipadx = 8;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        innerPanel.add(butItem, gridBagConstraints);

        lblDescription.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblDescription.setText("Description met een heel stuk tekst.");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipady = 21;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 0);
        innerPanel.add(lblDescription, gridBagConstraints);

        lblCode.setText("Code");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.ipady = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 0);
        innerPanel.add(lblCode, gridBagConstraints);

        lblStatus.setText("Status");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.ipady = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 0);
        innerPanel.add(lblStatus, gridBagConstraints);

        lblLocation.setText("Locatie");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.ipady = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 0);
        innerPanel.add(lblLocation, gridBagConstraints);

        lblCodeText.setText("");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 0);
        innerPanel.add(lblCodeText, gridBagConstraints);

        lblStatusText.setText("");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 0);
        innerPanel.add(lblStatusText, gridBagConstraints);

        lblLocationText.setText("");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 0);
        innerPanel.add(lblLocationText, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        add(innerPanel, gridBagConstraints);
    }

    public void displaySelectedItem(Item item) {
        lblDescription.setText(item.getItemBeschrijving());
        lblCodeText.setText(item.getItemcode().getItemcodeBeschrijving());
        if (item.getItemstatus() != null){
            lblStatusText.setText(item.getItemstatus().toString());
        } else {
            lblStatusText.setText("");
        }
        
        lblLocationText.setText(item.getItemLocatie());

        String itemSoort = item.getItemsoort().getItemsoortItemsoort();
        switch (itemSoort) {
            case "word":
                butItem.setIcon(publicDefault.wordIcon);
                break;
            case "pdf":
                butItem.setIcon(publicDefault.pdfIcon);
                break;
            case "img":
                butItem.setIcon(publicDefault.imgIcon);
                break;
            case "text":
                butItem.setIcon(publicDefault.txtIcon);
                break;
            case "xls":
                butItem.setIcon(publicDefault.excelIcon);
                break;
            default:
                butItem.setIcon(publicDefault.docIcon);
                break;
        }



        
    }

}

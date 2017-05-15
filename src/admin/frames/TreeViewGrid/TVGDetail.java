package admin.frames.TreeViewGrid;

import admin.PublicDefault;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.LayoutStyle;

public class TVGDetail extends JPanel {

    private JCheckBox chkArchief;
    private JCheckBox chkBelangrijk;
    private JCheckBox chkBijzonder;
    private JCheckBox chkLeeg;
    private JCheckBox chkOnbelangrijk;
    private JCheckBox chkVerwijderd;
    private JLabel lblArchief;
    private JLabel lblBelangrijk;
    private JLabel lblBijzonder;
    private JLabel lblOnbelangrijk;
    private JLabel lblVerwijderd;
    private JPanel panelToonStatus;
    private PublicDefault publicDefault;
    private TVGTreeView tvgTreeView;

    public TVGDetail(TVGTreeView tvgTreeView) {
        this.tvgTreeView = tvgTreeView;
        initComponents();
    }

    private void initComponents() {
        publicDefault = new PublicDefault();
        panelToonStatus = new JPanel();
        chkArchief = new JCheckBox();
        chkBelangrijk = new JCheckBox();
        chkOnbelangrijk = new JCheckBox();
        chkBijzonder = new JCheckBox();
        lblArchief = new JLabel();
        chkVerwijderd = new JCheckBox();
        chkLeeg = new JCheckBox();
        lblBelangrijk = new JLabel();
        lblOnbelangrijk = new JLabel();
        lblBijzonder = new JLabel();
        lblVerwijderd = new JLabel();

        panelToonStatus.setBorder(BorderFactory.createTitledBorder("Toon Status:"));
        CheckItemListener cil = new CheckItemListener();
        chkArchief.setText("Archief");
        chkArchief.setSelected(true);
        chkArchief.addItemListener(cil);
        chkBelangrijk.setText("Belangrijk");
        chkBelangrijk.setSelected(true);
        chkBelangrijk.addItemListener(cil);
        chkOnbelangrijk.setText("Onbelangrijk");
        chkOnbelangrijk.setSelected(true);
        chkOnbelangrijk.addItemListener(cil);
        chkBijzonder.setText("Bijzonder");
        chkBijzonder.setSelected(true);
        chkBijzonder.addItemListener(cil);
        chkVerwijderd.setText("Verwijderd");
        chkVerwijderd.setSelected(false);
        chkVerwijderd.addItemListener(cil);
        chkLeeg.setText("Leeg");
        chkLeeg.setSelected(true);
        chkLeeg.addItemListener(cil);

        lblArchief.setIcon(publicDefault.imageArchief);
        lblBelangrijk.setIcon(publicDefault.imageBelangrijk);
        lblOnbelangrijk.setIcon(publicDefault.imageOnbelangrijk);
        lblBijzonder.setIcon(publicDefault.imageBijzonder);
        lblVerwijderd.setIcon(publicDefault.imageVerwijderd);

        GroupLayout panelToonStatusLayout = new GroupLayout(panelToonStatus);
        panelToonStatus.setLayout(panelToonStatusLayout);
        panelToonStatusLayout.setHorizontalGroup(
                panelToonStatusLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(panelToonStatusLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(panelToonStatusLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(lblVerwijderd, GroupLayout.Alignment.TRAILING)
                                        .addComponent(lblBijzonder, GroupLayout.Alignment.TRAILING)
                                        .addComponent(lblOnbelangrijk, GroupLayout.Alignment.TRAILING)
                                        .addComponent(lblBelangrijk, GroupLayout.Alignment.TRAILING)
                                        .addComponent(lblArchief, GroupLayout.Alignment.TRAILING))
                                .addGap(4, 4, 4)
                                .addGroup(panelToonStatusLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(chkLeeg)
                                        .addComponent(chkVerwijderd)
                                        .addComponent(chkBelangrijk)
                                        .addComponent(chkArchief)
                                        .addComponent(chkOnbelangrijk)
                                        .addComponent(chkBijzonder))
                                .addContainerGap(19, Short.MAX_VALUE))
        );
        panelToonStatusLayout.setVerticalGroup(
                panelToonStatusLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(panelToonStatusLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(panelToonStatusLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
                                        .addComponent(chkArchief)
                                        .addComponent(lblArchief))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panelToonStatusLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
                                        .addComponent(lblBelangrijk)
                                        .addComponent(chkBelangrijk))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panelToonStatusLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
                                        .addComponent(lblOnbelangrijk)
                                        .addComponent(chkOnbelangrijk))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panelToonStatusLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
                                        .addComponent(lblBijzonder)
                                        .addComponent(chkBijzonder))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panelToonStatusLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
                                        .addComponent(lblVerwijderd)
                                        .addComponent(chkVerwijderd))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(chkLeeg)
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        add(panelToonStatus);
        
        
    }

    private class CheckItemListener implements ItemListener {

        @Override
        public void itemStateChanged(ItemEvent e) {
            // Reload data in Grid with new parameters.
            String isSelected;
            if (chkLeeg.isSelected()){
                isSelected = "JA";
            } else {
                isSelected = null;
            }
            tvgTreeView.refreshFilter(
                    chkArchief.isSelected(),
                    chkBelangrijk.isSelected(),
                    chkOnbelangrijk.isSelected(),
                    chkBijzonder.isSelected(),
                    chkVerwijderd.isSelected(),
                    isSelected);

        }

    }

}

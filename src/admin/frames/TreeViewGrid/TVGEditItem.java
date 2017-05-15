package admin.frames.TreeViewGrid;

import admin.PublicDefault;
import admin.PublicDefault.ItemStatus;
import admin.PublicDefault.ProgramMode;
import database.Hoofditem;
import database.Item;
import database.Itemcode;
import database.Itemsoort;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.RollbackException;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import org.jdesktop.swingx.JXDatePicker;

public class TVGEditItem extends JDialog {

    private PublicDefault publicDefault = new PublicDefault();
    private ProgramMode programMode;
    private Item item;
    private JPanel outerPanel1;
    private JPanel outerPanel2;
    private JPanel mainOuterPanel;
    private JPanel innerPanel;
    private JPanel buttonPanel;
    private JComboBox<Hoofditem> cmbHoofdItem;
    private JComboBox<Itemcode> cmbItemcode;
    private JComboBox<Itemsoort> cmbItemsoort;
    private JComboBox<ItemStatus> cmbStatus;
    private JTextField edtBeschrijving;
    private JTextField edtLocatie;
    private boolean refreshData = false;
    private JLabel lblBeschrijving;
    private JLabel lblHoofditem;
    private JLabel lblItemCode;
    private JLabel lblItemID;
    private JLabel lblItemID2;
    private JLabel lblItemSoort;
    private JLabel lblLocatie;
    private Font font;
    private JButton btnBrowse;
    private Hoofditem hiGeenSelectie;
    private Query query;
    private EntityManager entityManager;
    private List<Hoofditem> listHoofdItem;
    private List<Itemsoort> listItemSoort;
    private List<Itemcode> listItemcode;
    private Itemsoort itemsoortOnbekend = null;
    private Hoofditem hoofditemOnbekend = null;
    private Itemcode itemCodeOnbekend = null;
    private JXDatePicker jXDatePickerDatumDocument;
    private JLabel lblDatumDocument;
    private JLabel lblStatus;
    private JButton btnConfirm;
    private JButton btnCancel;
    private final DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
    private KeyboardFocusManager keyboardFocusManager;
    private LocalKeyEventDispatcher localKeyEventDispatcher;
    private TVGGrid tvgGrid;

    public TVGEditItem(TVGGrid tvgGrid) {
        this.tvgGrid = tvgGrid;
        initComponents();
    }

    private void initComponents() {
        entityManager = Persistence.createEntityManagerFactory("administration").createEntityManager();
        // If you don't want to register a listener on every component,
        // you could add your own KeyEventDispatcher to the KeyboardFocusManager:
        localKeyEventDispatcher = new LocalKeyEventDispatcher();
        keyboardFocusManager = KeyboardFocusManager.getCurrentKeyboardFocusManager();

        GridBagConstraints gridBagConstraints;
        font = new Font("Segoe UI", Font.PLAIN, 16);
        innerPanel = new JPanel();
        buttonPanel = new JPanel();
        outerPanel1 = new JPanel();
        outerPanel2 = new JPanel();
        mainOuterPanel = new JPanel();
        lblItemID = new JLabel();
        lblItemID.setFont(font);
        lblItemID2 = new JLabel();
        lblItemID2.setFont(font);
        lblHoofditem = new JLabel();
        lblHoofditem.setFont(font);
        lblItemSoort = new JLabel();
        lblItemSoort.setFont(font);
        lblItemCode = new JLabel();
        lblItemCode.setFont(font);
        lblBeschrijving = new JLabel();
        lblBeschrijving.setFont(font);
        lblLocatie = new JLabel();
        lblLocatie.setFont(font);
        cmbHoofdItem = new JComboBox<>();
        cmbHoofdItem.setFont(font);
        cmbItemsoort = new JComboBox<>();
        cmbItemsoort.setFont(font);
        cmbStatus = new JComboBox<>();
        cmbStatus.setFont(font);
        cmbItemcode = new JComboBox<>();
        cmbItemcode.setFont(font);
        edtBeschrijving = new JTextField();
        edtBeschrijving.setFont(font);
        edtLocatie = new JTextField();
        edtLocatie.setFont(font);
        btnBrowse = new JButton("...");
        DateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        lblDatumDocument = new JLabel();
        lblDatumDocument.setFont(font);
        lblStatus = new JLabel();
        lblStatus.setFont(font);
        jXDatePickerDatumDocument = new JXDatePicker();
        jXDatePickerDatumDocument.setFormats(format);
        jXDatePickerDatumDocument.setFont(font);

        Dimension cmbDimension = new Dimension(500, 33);
        Dimension cmbDimensionSmall = new Dimension(175, 33);
        Dimension edtDimension = new Dimension(920, 34);
        Dimension edtDimensionSmall = new Dimension(175, 34);
        Dimension windowDimension = new Dimension(1150, 600);
        Dimension buttonDimension = new Dimension(120, 33);
        Insets insets = new Insets(2, 5, 0, 0);

        btnConfirm = new JButton();
        btnConfirm.addActionListener(new ButtonActionListener());
        btnCancel = new JButton("Annuleer");
        btnCancel.addActionListener(new ButtonActionListener());

        innerPanel.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED, Color.lightGray, Color.lightGray));
//        outerPanel1.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED, Color.PINK, Color.lightGray));
//        outerPanel2.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED, Color.PINK, Color.lightGray));
//        buttonPanel.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED, Color.lightGray, Color.lightGray));
        innerPanel.setLayout(new GridBagLayout());
        buttonPanel.setLayout(new GridBagLayout());
        outerPanel1.setLayout(new GridBagLayout());
        outerPanel2.setLayout(new GridBagLayout());
        mainOuterPanel.setLayout(new BoxLayout(mainOuterPanel, BoxLayout.Y_AXIS));
//        buttonPanel.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED, Color.GREEN, Color.GREEN));

        lblItemID.setText("ID:");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.anchor = GridBagConstraints.LINE_START;
        gridBagConstraints.insets = insets;
        innerPanel.add(lblItemID, gridBagConstraints);

        lblItemID2.setText("1");
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.insets = insets;
        innerPanel.add(lblItemID2, gridBagConstraints);

        lblHoofditem.setText("Hoofditem:");

        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.anchor = GridBagConstraints.LINE_START;
        gridBagConstraints.insets = insets;
        innerPanel.add(lblHoofditem, gridBagConstraints);

        lblItemSoort.setText("Itemsoort:");

        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.anchor = GridBagConstraints.LINE_START;
        gridBagConstraints.insets = insets;
        innerPanel.add(lblItemSoort, gridBagConstraints);

        lblItemCode.setText("Itemcode:");
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.anchor = GridBagConstraints.LINE_START;
        gridBagConstraints.insets = insets;
        innerPanel.add(lblItemCode, gridBagConstraints);

        lblBeschrijving.setText("Beschrijving:");
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.anchor = GridBagConstraints.LINE_START;
        gridBagConstraints.insets = insets;
        innerPanel.add(lblBeschrijving, gridBagConstraints);

        lblLocatie.setText("Locatie:");
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.anchor = GridBagConstraints.LINE_START;
        gridBagConstraints.insets = insets;
        innerPanel.add(lblLocatie, gridBagConstraints);

        cmbHoofdItem.setModel(new DefaultComboBoxModel<>());
        cmbHoofdItem.setPreferredSize(cmbDimension);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.insets = insets;
        gridBagConstraints.anchor = GridBagConstraints.LINE_START;
        innerPanel.add(cmbHoofdItem, gridBagConstraints);

        cmbItemsoort.setModel(new DefaultComboBoxModel<>());
        cmbItemsoort.setPreferredSize(cmbDimension);
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.insets = insets;
        gridBagConstraints.anchor = GridBagConstraints.LINE_START;
        innerPanel.add(cmbItemsoort, gridBagConstraints);

        cmbItemcode.setModel(new javax.swing.DefaultComboBoxModel<>());
        cmbItemcode.setPreferredSize(cmbDimension);
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.insets = insets;
        gridBagConstraints.anchor = GridBagConstraints.LINE_START;
        innerPanel.add(cmbItemcode, gridBagConstraints);

        edtBeschrijving.setPreferredSize(edtDimension);
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.insets = insets;
        gridBagConstraints.anchor = GridBagConstraints.LINE_START;
        innerPanel.add(edtBeschrijving, gridBagConstraints);

        edtLocatie.setPreferredSize(edtDimension);
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.insets = insets;
        gridBagConstraints.anchor = GridBagConstraints.LINE_START;
        innerPanel.add(edtLocatie, gridBagConstraints);

        btnBrowse.setPreferredSize(new Dimension(30, 30));
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.insets = insets;
        gridBagConstraints.anchor = GridBagConstraints.LINE_START;
        innerPanel.add(btnBrowse, gridBagConstraints);

        lblDatumDocument.setText("Datum:");
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.anchor = GridBagConstraints.LINE_START;
        gridBagConstraints.insets = insets;
        innerPanel.add(lblDatumDocument, gridBagConstraints);

        jXDatePickerDatumDocument.setPreferredSize(edtDimensionSmall);
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.anchor = GridBagConstraints.LINE_START;
        gridBagConstraints.insets = insets;
        innerPanel.add(jXDatePickerDatumDocument, gridBagConstraints);

        lblStatus.setText("Status:");
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.anchor = GridBagConstraints.LINE_START;
        gridBagConstraints.insets = insets;
        innerPanel.add(lblStatus, gridBagConstraints);

        cmbStatus.setPreferredSize(cmbDimensionSmall);
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.anchor = GridBagConstraints.LINE_START;
        gridBagConstraints.insets = insets;
        innerPanel.add(cmbStatus, gridBagConstraints);

        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1;
        gridBagConstraints.weighty = 1;
        gridBagConstraints.insets = insets;
        gridBagConstraints.fill = GridBagConstraints.NONE;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        outerPanel1.add(innerPanel, gridBagConstraints);

        // Design ButtonPanel
        btnConfirm.setPreferredSize(buttonDimension);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.anchor = GridBagConstraints.LINE_START;
        gridBagConstraints.insets = insets;
        buttonPanel.add(btnConfirm, gridBagConstraints);

        btnCancel.setPreferredSize(buttonDimension);
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.anchor = GridBagConstraints.LINE_START;
        gridBagConstraints.insets = insets;
        buttonPanel.add(btnCancel, gridBagConstraints);

        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1;
        gridBagConstraints.weighty = 1;
        gridBagConstraints.insets = insets;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = GridBagConstraints.NONE;
        outerPanel2.add(buttonPanel, gridBagConstraints);

        mainOuterPanel.add(outerPanel1);
        mainOuterPanel.add(outerPanel2);

        setLayout(new GridBagLayout());
        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1;
        gridBagConstraints.weighty = 1;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = GridBagConstraints.NONE;
        gridBagConstraints.insets = insets;
        gridBagConstraints.ipadx = 10;
        gridBagConstraints.ipady = 20;
        add(mainOuterPanel, gridBagConstraints);

        setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
        setPreferredSize(windowDimension);
        setMinimumSize(windowDimension);

        loadDataComboBox();

        pack();

    }

    /**
     * Loads an item on the screen
     *
     * @param itemID ID of the item.
     * @param programMode ADD, CHANGE, DISPLAY, DELETE
     */
    public void loadItem(int itemID, ProgramMode programMode) {
        // Fetch current record from the database.
        List<Item> its = entityManager.createNamedQuery("Item.findByItemId", Item.class)
                .setParameter("itemId", itemID)
                .getResultList();
        item = its.get(0);

        this.programMode = programMode;
        switch (programMode) {
            case ADD:
                btnConfirm.setText("Opslaan");
                cmbHoofdItem.setEnabled(true);
                cmbItemcode.setEnabled(true);
                cmbItemsoort.setEnabled(true);
                jXDatePickerDatumDocument.setEnabled(true);
                cmbStatus.setEnabled(true);
                edtBeschrijving.setEnabled(true);
                edtLocatie.setEnabled(true);
                btnBrowse.setVisible(true);
                break;
            case CHANGE:
                btnConfirm.setText("Opslaan");
                cmbHoofdItem.setEnabled(true);
                cmbItemcode.setEnabled(true);
                cmbItemsoort.setEnabled(true);
                jXDatePickerDatumDocument.setEnabled(true);
                cmbStatus.setEnabled(true);
                edtBeschrijving.setEnabled(true);
                edtLocatie.setEnabled(true);
                btnBrowse.setVisible(true);

                break;
            case DELETE:
                btnConfirm.setText("Verwijder");
                cmbHoofdItem.setEnabled(false);
                cmbItemcode.setEnabled(false);
                cmbItemsoort.setEnabled(false);
                jXDatePickerDatumDocument.setEnabled(false);
                cmbStatus.setEnabled(false);
                edtBeschrijving.setEnabled(false);
                edtLocatie.setEnabled(false);
                btnBrowse.setVisible(false);
                break;
            case DISPLAY:
                btnConfirm.setText("OK");
                cmbHoofdItem.setEnabled(false);
                cmbItemcode.setEnabled(false);
                cmbItemsoort.setEnabled(false);
                jXDatePickerDatumDocument.setEnabled(false);
                cmbStatus.setEnabled(false);
                edtBeschrijving.setEnabled(false);
                edtLocatie.setEnabled(false);
                btnBrowse.setVisible(false);
                break;
        }
        lblItemID2.setText(item.getItemId().toString());
        edtBeschrijving.setText(item.getItemBeschrijving());
        edtLocatie.setText(item.getItemLocatie());
        if (programMode == ProgramMode.CHANGE || programMode == ProgramMode.DELETE || programMode == ProgramMode.DISPLAY) {
            cmbHoofdItem.setSelectedItem(item.getHoofditem());
            cmbItemcode.setSelectedItem(item.getItemcode());
            cmbItemsoort.setSelectedItem(item.getItemsoort());
            jXDatePickerDatumDocument.setDate(item.getItemDatumDocument());
            cmbStatus.setSelectedItem(item.getItemstatus());
        }
        // Add my own KeyEventDispatcher. Now not every component needs to have a listener.
        keyboardFocusManager.addKeyEventDispatcher(localKeyEventDispatcher);

    }

    private void loadDataComboBox() {
        // HoofdItem ComboBox
        cmbHoofdItem.removeAllItems();
        hiGeenSelectie = new Hoofditem();
        hiGeenSelectie.setHoofditemBeschrijving("Geen selectie");
        hiGeenSelectie.setHoofditemNaam("...");
        cmbHoofdItem.addItem(hiGeenSelectie);
        query = entityManager.createNamedQuery("Hoofditem.findAll", Hoofditem.class);
        listHoofdItem = query.getResultList();
        for (Hoofditem hi : listHoofdItem) {
            cmbHoofdItem.addItem(hi);

            if (hi.getHoofditemNaam().equalsIgnoreCase("nb")) {
                hoofditemOnbekend = hi;
            }
        }

        // ItemCode ComboBox
        cmbItemcode.removeAllItems();
        query = entityManager.createNamedQuery("Itemcode.findAll", Itemcode.class);
        listItemcode = query.getResultList();
        for (Itemcode ic : listItemcode) {
            cmbItemcode.addItem(ic);
            if (ic.getItemcodeCode().equalsIgnoreCase("onbekend")) {
                itemCodeOnbekend = ic;
            }
        }

        // ItemSoort ComboBox
        cmbItemsoort.removeAllItems();
        query = entityManager.createNamedQuery("Itemsoort.findAll", Itemsoort.class);
        listItemSoort = query.getResultList();
        for (Itemsoort is : listItemSoort) {
            cmbItemsoort.addItem(is);
            if (is.getItemsoortItemsoort().equalsIgnoreCase("nb")) {
                itemsoortOnbekend = is;
            }
        }

        // ItemStatus ComboBox
        cmbStatus.removeAllItems();
        for (PublicDefault.ItemStatus itemStatus : PublicDefault.ItemStatus.values()) {
            if (itemStatus != ItemStatus.DUMMY) {
                cmbStatus.addItem(itemStatus);
            }
        }
        cmbStatus.setSelectedItem(null);

    }

    private class ButtonActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            switch (e.getActionCommand()) {
                case "Opslaan":
                    saveRecord();
                    break;
                case "Annuleer":
                    hideScreen();
                    break;
                case "Verwijder":
                    saveRecord();
                    break;
                case "OK":
                    hideScreen();
                    break;
            }

        }

    }

    private class LocalKeyEventDispatcher implements KeyEventDispatcher {

        @Override
        public boolean dispatchKeyEvent(KeyEvent e) {
            switch (e.getID()) {
                case KeyEvent.KEY_PRESSED:
                    if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                        if (isVisible() == true) {
                            hideScreen();
                        }
                    }
                    break;
                case KeyEvent.KEY_RELEASED:
                    break;
                case KeyEvent.KEY_TYPED:
                    break;
                default:
                    break;
            }
            return false;
        }
    }

    private void hideScreen() {
        // Remove custom KeyEventDispatcher so it doesn't listen anymore when not visible.
        keyboardFocusManager.removeKeyEventDispatcher(localKeyEventDispatcher);
        this.setVisible(false);
        if (refreshData) {
            tvgGrid.refreshData(item);
            refreshData = false;
        }
    }

    private void saveRecord() {
        switch (programMode) {
            case ADD:
                break;
            case CHANGE:
                changeRecord();
                break;
            case DELETE:
                deleteRecord();
                break;
        }
    }

    private void changeRecord() {

        try {
            // Transaction
            entityManager.getTransaction().begin();
            // Compare data on screen with data in database and change when necessary.
            if (item.getHoofditem() != (Hoofditem) cmbHoofdItem.getSelectedItem()) {
                item.setHoofditem((Hoofditem) cmbHoofdItem.getSelectedItem());
            }
            if (item.getItemsoort() != (Itemsoort) cmbItemsoort.getSelectedItem()) {
                item.setItemsoort((Itemsoort) cmbItemsoort.getSelectedItem());
            }
            if (item.getItemcode() != (Itemcode) cmbItemcode.getSelectedItem()) {
                item.setItemcode((Itemcode) cmbItemcode.getSelectedItem());
            }
            if (item.getItemstatus() != (ItemStatus) cmbStatus.getSelectedItem()) {
                item.setItemstatus((ItemStatus) cmbStatus.getSelectedItem());
            }
            if (!item.getItemBeschrijving().equals(edtBeschrijving.getText())) {
                item.setItemBeschrijving(edtBeschrijving.getText());
            }
            if (!item.getItemLocatie().equals(edtLocatie.getText())) {
                item.setItemLocatie(edtLocatie.getText());
            }
            if (item.getItemDatumDocument() != jXDatePickerDatumDocument.getDate()) {
                item.setItemDatumDocument(jXDatePickerDatumDocument.getDate());
            }
            item.setItemDateModified(Calendar.getInstance().getTime());
            entityManager.getTransaction().commit();
            refreshData = true;
            hideScreen();

        } catch (RollbackException ex) {
            System.out.println("Commit van de transactie is mislukt. " + ex);
        } catch (Exception ex) {
            System.out.println("Er is iets helemaal fout gegaan bij het opslaan. " + ex);
        }

    }

    private void deleteRecord() {
        // Set status to deleted.
        // If status is already deleted, remove record from database.
        if ((ItemStatus) cmbStatus.getSelectedItem() == ItemStatus.DELETED) {
            //default icon, custom title
            int n = JOptionPane.showConfirmDialog(
                    this,
                    "De gegevens zullen definitief worden verwijderd. Zeker weten?",
                    "Bevestig verwijderen",
                    JOptionPane.YES_NO_OPTION);

            if (n == 0) {
                // Remove record from database.
                JOptionPane.showMessageDialog(tvgGrid, "Het verwijderen gaan we nog wel eens doen.", "To Do!", JOptionPane.INFORMATION_MESSAGE);
            }
        } else {

            try {
                // Transaction
                entityManager.getTransaction().begin();
                item.setItemstatus(ItemStatus.DELETED);
                item.setItemDateModified(Calendar.getInstance().getTime());
                entityManager.getTransaction().commit();
                refreshData = true;
                hideScreen();

            } catch (RollbackException ex) {
                System.out.println("Commit van de transactie is mislukt. " + ex);
            } catch (Exception ex) {
                System.out.println("Er is iets helemaal fout gegaan bij het opslaan. " + ex);
            }
        }
    }

}

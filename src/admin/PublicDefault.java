/*
 * Public default classes to be used in the entire program.
 */
package admin;

import java.awt.Color;
import java.awt.Component;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

public final class PublicDefault {

    public ImageIcon imageArchief = createImageIcon("/resources/archief.png", "Archief");
    public ImageIcon imageBelangrijk = createImageIcon("/resources/belangrijk.png", "Belangrijk");
    public ImageIcon imageOnbelangrijk = createImageIcon("/resources/onbelangrijk.png", "Onbelangrijk");
    public ImageIcon imageBijzonder = createImageIcon("/resources/bijzonder.png", "Bijzonder");
    public ImageIcon imageVerwijderd = createImageIcon("/resources/verwijderd.png", "Verwijderd");
    public DateRenderer dateRenderer;
    public StatusRenderer statusRenderer;

    public ImageIcon pdfIcon = createImageIcon("/resources/item/Adobe-PDF-Document-icon.png", "Documents");
    public ImageIcon wordIcon = createImageIcon("/resources/item/Document-Microsoft-Word-icon.png", "Documents");
    public ImageIcon imgIcon = createImageIcon("/resources/item/Image-JPEG-icon.png", "Documents");
    public ImageIcon docIcon = createImageIcon("/resources/item/Document-Help-icon.png", "Documents");
    public ImageIcon txtIcon = createImageIcon("/resources/item/Text-Edit-icon.png", "Documents");
    public ImageIcon excelIcon = createImageIcon("/resources/item/Document-Microsoft-Excel-icon.png", "Documents");

    public PublicDefault() {
        imageArchief = createImageIcon("/resources/archief.png", "Archief");
        imageBelangrijk = createImageIcon("/resources/belangrijk.png", "Belangrijk");
        imageOnbelangrijk = createImageIcon("/resources/onbelangrijk.png", "Onbelangrijk");
        imageBijzonder = createImageIcon("/resources/bijzonder.png", "Bijzonder");
        imageVerwijderd = createImageIcon("/resources/verwijderd.png", "Verwijderd");
        dateRenderer = new DateRenderer();
        statusRenderer = new StatusRenderer();

    }

    public enum ItemStatus {
        BELANGRIJK, ONBELANGRIJK, DELETED, ARCHIEF, BIJZONDER, DUMMY
    }

    /**
     * ADD, CHANGE, DISPLAY, DELETE
     */
    public enum ProgramMode {
        ADD, CHANGE, DISPLAY, DELETE
    };

    public enum ObjectInfoType {
        HOOFDITEM, YEAR, MONTH
    }

    public ImageIcon createImageIcon(String path, String description) {
        try {
            URL imageURL = this.getClass().getResource(path);
            return new ImageIcon(imageURL);
        } catch (Exception ex) {
            Logger.getLogger(PublicDefault.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    private class DateRenderer extends DefaultTableCellRenderer {

        SimpleDateFormat formatter = new SimpleDateFormat("dd MMMMM yyyy");

        public DateRenderer() {
            super();
            setOpaque(true);
        }

        @Override
        public void setValue(Object value) {
            setText((value == null) ? "" : formatter.format(value));
        }

        @Override
        public Component getTableCellRendererComponent(JTable table,
                Object value, boolean isSelected, boolean hasFocus,
                int row, int column) {
            setBackground(new Color(1f, 0f, 0f, .1f));
            return super.getTableCellRendererComponent(table, value, isSelected,
                    hasFocus, row, column);
        }

    }

    private class StatusRenderer extends DefaultTableCellRenderer {

        ItemStatus itemStatus;

        public StatusRenderer() {
            super();
            setOpaque(true);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table,
                Object value, boolean isSelected, boolean hasFocus,
                int row, int column) {
            setBackground(new Color(0f, 1f, 1f, .1f));
            itemStatus = (ItemStatus) value;
            if (itemStatus != null) {
                switch (itemStatus) {
                    case ARCHIEF:
                        setIcon(imageArchief);
                        value = "Archief";
                        break;
                    case BELANGRIJK:
                        setIcon(imageBelangrijk);
                        value = "Belangrijk";
                        break;
                    case BIJZONDER:
                        setIcon(imageBijzonder);
                        value = "Bijzonder";
                        break;
                    case DELETED:
                        setIcon(imageVerwijderd);
                        value = "Deleted";
                        break;
                    case ONBELANGRIJK:
                        setIcon(imageOnbelangrijk);
                        value = "Onbelangrijk";
                        break;
                    default:
                        setIcon(null);
                        value = "";
                }

            } else {

                setIcon(null);
            }

            return super.getTableCellRendererComponent(table, value, isSelected,
                    hasFocus, row, column);
        }

    }

    public static TableCellRenderer tableCellRenderer = new DefaultTableCellRenderer() {

        SimpleDateFormat f = new SimpleDateFormat("dd MMMMM yyyy");

        @Override
        public Component getTableCellRendererComponent(JTable table,
                Object value, boolean isSelected, boolean hasFocus,
                int row, int column) {
            if (value instanceof Date) {
                value = f.format(value);
            }
            return super.getTableCellRendererComponent(table, value, isSelected,
                    hasFocus, row, column);
        }
    };

}

package admin;

import static com.sun.corba.se.impl.util.Utility.printStackTrace;
import java.awt.Dimension;
import java.awt.Point;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

public class Preferences {

    private static Properties properties;

    public static void saveProperties(Config config) {

        try {
            properties.setProperty("lastDirectory", config.getLastDirectory());
            //save properties to project root folder
            properties.store(new FileOutputStream("config.txt"), null);

        } catch (IOException ex1) {
            System.out.println("An IOException occurred with saving the window configuration. (saveProperties) " + ex1);
        } catch (Exception ex) {
            System.out.println("Something terrible happened with saving the window configuration. (saveProperties) " + ex);
        }
    }

    public static void saveProperties(ConfigWindow configWindow) {
        try {
            // Width
            String propWinWidth = configWindow.getWindowName() + "Width";
            String propWinWidthVal = String.valueOf(configWindow.getDimension().getWidth());
            properties.setProperty(propWinWidth, propWinWidthVal);
            // Height
            String propWinHeight = configWindow.getWindowName() + "Height";
            String propWinHeightVal = String.valueOf(configWindow.getDimension().getHeight());
            properties.setProperty(propWinHeight, propWinHeightVal);
            // PositionX
            String propWinPosX = configWindow.getWindowName() + "PosX";
            String propWinPosXVal = String.valueOf(configWindow.getPoint().getX());
            properties.setProperty(propWinPosX, propWinPosXVal);
            // PositionY
            String propWinPosY = configWindow.getWindowName() + "PosY";
            String propWinPosYVal = String.valueOf(configWindow.getPoint().getY());
            properties.setProperty(propWinPosY, propWinPosYVal);

            //save properties to project root folder
            properties.store(new FileOutputStream("config.txt"), null);
        } catch (IOException ex) {
            System.out.println("An IOException occurred with saving the window configuration. (saveProperties) " + ex);
        } catch (Exception ex) {
            System.out.println("Something terrible happened with saving the window configuration. (saveProperties) " + ex);
        }
    }

    public static void saveColumns(Config config) {
        for (ColumnWidth colWidth : config.getColumns()) {
            try {
                properties.setProperty("col" + colWidth.getColumnModelIndex() + "_identifier", colWidth.getColumnIdentifier());
                properties.setProperty("col" + colWidth.getColumnModelIndex() + "_index", String.valueOf(colWidth.getColumnIndex()));
                properties.setProperty("col" + colWidth.getColumnModelIndex() + "_width", String.valueOf(colWidth.getColumnWidth()));
                properties.setProperty("col" + colWidth.getColumnModelIndex() + "_modelindex", String.valueOf(colWidth.getColumnModelIndex()));
                properties.store(new FileOutputStream("config.txt"), null);
            } catch (IOException ex) {

            }
        }
    }

    public static Config loadColumns() {
        Config config = new Config();
        properties = new Properties();
        ArrayList<ColumnWidth> colWidths = new ArrayList();
        try {
            properties.load(new FileInputStream("config.txt"));

        } catch (IOException ex) {
            // No config file found, return with null.
            config = null;
            return config;
        }
        boolean colExist = true;
        int colCount = 0;
        while (colExist == true) {
            String idIdentifier = "col" + String.valueOf(colCount) + "_identifier";
            String idIndex = "col" + String.valueOf(colCount) + "_index";
            String idWidth = "col" + String.valueOf(colCount) + "_width";
            String colIdentifier = properties.getProperty(idIdentifier);
            if (colIdentifier == null) {
                // No more columns
                colExist = false;
            } else {
                // Create an object of ColumnWidth with values read from the config file.
                ColumnWidth colWidth = new ColumnWidth();
                colWidth.setColumnIdentifier(colIdentifier);
                colWidth.setColumnModelIndex(colCount);
                String strColumnIndex = properties.getProperty(idIndex);
                if (strColumnIndex != null) {
                    int iColumnIndex = 0;
                    if (!strColumnIndex.equals("0")) {
                        iColumnIndex = Integer.parseInt(strColumnIndex);

                    }
                    colWidth.setColumnIndex(iColumnIndex);
                }

                String strColumnWidth = properties.getProperty(idWidth);
                if (strColumnWidth != null) {
                    int iColumnWidth = 0;
                    if (!strColumnWidth.equals("0")) {
                        iColumnWidth = Integer.parseInt(strColumnWidth);
                        colWidth.setColumnWidth(iColumnWidth);
                    }
                }
                // Add it to the arraylist.
                colWidths.add(colWidth);
            }

            colCount++;
        }
        // Add the arraylist to the config object.
        config.setColumns(colWidths);

        return config;
    }

    public static Config loadProperties() {
        properties = new Properties();
        Config config = new Config();
        try {
            properties.load(new FileInputStream("config.txt"));
            config.setLastDirectory(properties.getProperty("lastDirectory"));
        } catch (IOException ex) {
            // Property file not found.
            config.setLastDirectory("C:\\TEMP");
            saveProperties(config);
        }

        return config;
    }

    public static ConfigWindow loadProperties(String winName) {
        ConfigWindow confWin = new ConfigWindow();
        properties = new Properties();
        try {
            properties.load(new FileInputStream("config.txt"));
            // Get dimensions of window
            Dimension dim = new Dimension();
            String strWidth = properties.getProperty(winName + "Width", "0");
            double width = Double.valueOf(strWidth);
            
            String strHeight = properties.getProperty(winName + "Height", "0");
            double height = Double.valueOf(strHeight);
            dim.setSize(width, height);
            confWin.setDimension(dim);
            // Get position of window
            Point point = new Point();
            double x = Double.valueOf(properties.getProperty(winName + "PosX", "0"));
            double y = Double.valueOf(properties.getProperty(winName + "PosY", "0"));
            point.setLocation(x, y);
            confWin.setPoint(point);
            confWin.setWindowName(winName);
            
        } catch (IOException ex) {
            System.out.println("An IOException occurred with saving the window configuration. (loadProperties) " + ex);
        } catch (Exception ex) {
            System.out.println("Something terrible happened with loading the window configuration. (loadProperties) " + ex);
            printStackTrace();
        }

        return confWin;
    }

}

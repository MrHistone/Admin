/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin.frames;

/**
 *
 * @author Bart Jansen
 */
public class TreeviewFrame2 extends javax.swing.JInternalFrame{
    Treeview treeView;
    public TreeviewFrame2() {
        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        
        treeView = new Treeview();
        getContentPane().add(treeView);
        pack();
        
    }
    
}

package admin.frames;

import database.Item;
import java.awt.Dimension;
import javax.swing.JFrame;

public class ButtonFrame extends JFrame{
    
    public ButtonFrame(Item item){
        super();
        initialize(item);
        
        
    }
    
    private void initialize(Item item){
        ButtonPanel buttonPanel = new ButtonPanel(item);
        add(buttonPanel);
        setResizable(false);
        setMaximumSize(new Dimension(225, 380));
        
    }
    
    
    
    
}

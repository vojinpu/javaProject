package view;

import java.awt.BorderLayout;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import util.Util;

public class AdminPanel extends JPanel {
	
	public AdminPanel() {
		setLayout(new BorderLayout());
		JLabel infoLabel = new JLabel(Util.loadImage(getClass(), "info.png"));
		
		JPanel box = new JPanel();
        box.setLayout(new BoxLayout(box, BoxLayout.Y_AXIS ));
        
        box.add(Box.createVerticalGlue());
        box.add(infoLabel);
        box.add(Box.createVerticalGlue());
        
        JPanel boxInABox = new JPanel();
        boxInABox.setLayout(new BoxLayout(boxInABox, BoxLayout.X_AXIS ));

        boxInABox.add(Box.createHorizontalGlue());
        boxInABox.add(box);
        boxInABox.add(Box.createHorizontalGlue());
        
        add(boxInABox, BorderLayout.CENTER);
	}
	
}

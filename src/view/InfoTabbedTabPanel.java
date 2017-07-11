package view;

import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class InfoTabbedTabPanel extends JPanel {
	JLabel entityNameLabel;
	
	public InfoTabbedTabPanel(InfoTabbedPane tabbedPane,InfoTablePanel infotable) {
		super(new FlowLayout(FlowLayout.LEFT, 0, 0));
		setOpaque(false);

		entityNameLabel = new JLabel(infotable.getName()) ;

		add(entityNameLabel);
		entityNameLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 5));
		JButton button = new InfoTabbedCloseButton(tabbedPane,infotable);
		//button.setBorder(BorderFactory.createEmptyBorder(1, 0, 0, 0));
		add(button);
		//setBorder(BorderFactory.createEmptyBorder(1, 0, 0, 0));
	}
	
	public void setProjectName(String name){
		entityNameLabel.setText(name);
	}
}

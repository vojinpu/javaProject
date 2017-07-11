package view;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.tree.InfoNodeElement;

public class InfoElementPanel extends JPanel{
	public InfoElementPanel(InfoNodeElement nodeElement){
setBorder(BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
		
		for (int i = 0; i < nodeElement.getKeyValue().size(); i++) {
			JLabel label = new JLabel(String.valueOf(nodeElement.getKeyValue().get(i).getValue()));
			add(label);
			
			if (i < nodeElement.getKeyValue().size()-1){
				add(new JLabel("|"));
			}
		}
		
		JLabel addressLabel = new JLabel(String.valueOf(nodeElement.getBlockAddress()));
		addressLabel.setBackground(Color.YELLOW);
		addressLabel.setBorder(BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
		add(addressLabel);
	}
}

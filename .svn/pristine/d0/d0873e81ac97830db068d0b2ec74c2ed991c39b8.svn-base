package model.tree;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeCellRenderer;

//import model.tree.TreeCellRendered.ElementPane;
import view.InfoElementPanel;

public class InfoTreeCellRenderer extends DefaultTreeCellRenderer{
	public InfoTreeCellRenderer() {
		//setOpaque(true);
	
		// TODO Auto-generated constructor stub
	}

	  public Component getTreeCellRendererComponent(
              JTree tree,
              Object value,
              boolean sel,
              boolean expanded,
              boolean leaf,
              int row,
              boolean hasFocus) {
		  super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);
		  
		  InfoNode node = (InfoNode) value;
		  if (node.getChildCount() > 0)
			  setIcon(null);
		  
		  JPanel panel = new JPanel(new FlowLayout());
		  panel.setBackground(Color.WHITE);
		  panel.setMinimumSize(new Dimension(240, 35));
		  
		  for (int i = 0; i < node.getData().size(); i++) {
			  InfoElementPanel elementPanel = new InfoElementPanel(node.getData().get(i));
			  panel.add(elementPanel);
		  }
		  if (sel) {
			  panel.setBackground(Color.BLUE);
		  }
		  
		  
		  return panel;
	  }

}

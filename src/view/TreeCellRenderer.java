package view;

import java.awt.Component;

import javax.swing.ImageIcon;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;

import model.Entity;
import model.Package;
import model.Repository;
import util.Util;

public class TreeCellRenderer extends DefaultTreeCellRenderer{
	
	
	public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded, boolean leaf,
			int row, boolean hasFocus) {
		super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);

		Object userObject = ((DefaultMutableTreeNode) value).getUserObject();
		
		ImageIcon image = null;
		
		if (userObject instanceof Repository) 
			image = new ImageIcon(Util.loadIcon(getClass(), "database.png").getImage());
			
		if (userObject instanceof Package) 
			return this;
		
		if(userObject instanceof Entity)
			image = new ImageIcon(Util.loadIcon(getClass(), "table.png").getImage());
			
		
		
		setIcon(image);
		
		return this;
	
	}

}

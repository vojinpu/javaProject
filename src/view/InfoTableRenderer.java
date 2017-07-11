package view;

import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

public class InfoTableRenderer implements TableCellRenderer{

	 DefaultTableCellRenderer renderer;

	    public InfoTableRenderer(JTable table) {
	        renderer = (DefaultTableCellRenderer)
	            table.getTableHeader().getDefaultRenderer();
	        renderer.setHorizontalAlignment(JLabel.CENTER);
	    }

	    @Override
	    public Component getTableCellRendererComponent(
	        JTable table, Object value, boolean isSelected,
	        boolean hasFocus, int row, int col) {
	    		if(table.isCellSelected(row, col)){
	        return renderer.getTableCellRendererComponent(
	            table, value, true, hasFocus, row, col);
	    		} else {
	    			 return renderer.getTableCellRendererComponent(
	    			            table, value, isSelected, hasFocus, row, col);
				}
	    }

}

package listeners;

import javax.swing.JTable;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.TableColumnModelEvent;
import javax.swing.event.TableColumnModelListener;

public class InfoTableColumnModelListener implements TableColumnModelListener{

	JTable table;
	
	public InfoTableColumnModelListener(JTable table) {
		// TODO Auto-generated constructor stub
		this.table = table;
	}
	
	@Override
	public void columnAdded(TableColumnModelEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void columnRemoved(TableColumnModelEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void columnMoved(TableColumnModelEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void columnMarginChanged(ChangeEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void columnSelectionChanged(ListSelectionEvent e) {
		// TODO Auto-generated method stub
		System.out.println(e.toString());
	}

}

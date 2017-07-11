package app.controllers;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import javax.swing.text.AsyncBoxView.ChildLocator;

import app.Application;
import model.Entity;
import model.Relation;
import model.SekFile;
import model.Table;
import view.InfoTableModel;
import view.InfoTablePanel;

public class TableActionListener implements MouseListener {
	private InfoTablePanel mTable;
	Application app;

	public TableActionListener(InfoTablePanel infoTablePanel) {
		this.mTable = infoTablePanel;
		app = Application.getInstance();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(mTable.getJTable().getSelectedRow() == -1) return;
		Entity mEntity = mTable.getEntity();
		System.out.println("Sel: " + mEntity.getName());

		if (mEntity.getRelations() != null && !mTable.getTabbedPane().isChild()) {

			for (Relation rel : mEntity.getRelations()) {

				int n = mEntity.getAttributes().size();
				String[] query = new String[n];

				for (int i = 0; i < n; i++){
					query[i] = mTable.getJTable().getValueAt(mTable.getJTable().getSelectedRow(), i).toString();

					System.out.print(query[i]);
				}
				Entity chiled = rel.getChild();
				if(chiled instanceof SekFile){
					chiled.fetch();
					((SekFile) chiled).filterForTable(mEntity, query);
				}
				if(chiled instanceof Table){
					((Table)chiled).getFilteredForParent(mEntity, query);
				}
				app.getInfoFrame().getInfoSplitPanel().getInfoTabbedPane().getTab(mEntity).getChildTabbedPane()
				.openTab(chiled);
				app.getInfoFrame().getInfoSplitPanel().getInfoTabbedPane().getTab(mEntity).getChildTabbedPane().getChildTab(chiled).getJTable().setModel(new InfoTableModel(chiled.getData(),chiled.getAttributes().toArray()));

			}
		}

		

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}

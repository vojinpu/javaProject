package app.controllers.data;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JTree;

import app.Application;
import enums.Action;
import model.Entity;
import model.Relation;
import model.SekFile;
import model.SerFile;
import view.InfoRootPanel;
import view.InfoTable;
import view.InfoTableModel;
import view.InfoTableModel;
import view.InfoTablePanel;
import view.dialogs.FormDialog;

public class MergeController extends AbstractAction {

	private Entity mEntity;
	private InfoTablePanel mTable;

	public MergeController() {
		putValue(NAME, "Osveži dadoteku");
		putValue(SHORT_DESCRIPTION, "Osveži dadoteku");
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		System.out.println("entity " + mEntity.getName() + "mtable " + mTable.getEntity());

		// if(mEntity instanceof SekFile){

		if (mEntity instanceof SekFile) {
			((SekFile) mEntity).mergeData();

			if (mEntity.isChild()) {
				InfoRootPanel infoRootPanel = (InfoRootPanel) Application.getInstance().getInfoFrame()
						.getInfoSplitPanel().getInfoTabbedPane().getSelectedComponent();

				InfoTablePanel parentTabel = infoRootPanel.getInfoTablePanel();
				Entity parentEntity = infoRootPanel.getEntity();

				if (parentTabel.getJTable().getSelectedRow() == -1) {
					return;
				}

				for (Relation rel : parentEntity.getRelations()) {

					int n = parentEntity.getAttributes().size();
					String[] query = new String[n];

					for (int i = 0; i < n; i++) {
						query[i] = parentTabel.getJTable().getValueAt(parentTabel.getJTable().getSelectedRow(), i)
								.toString();

						System.out.print(query[i]);
					}
					Entity chiled = rel.getChild();
					chiled.fetch();
					((SekFile) chiled).filterForTable(parentEntity, query);

					// Application.getInstance().getInfoFrame().getInfoSplitPanel().getInfoTabbedPane().getTab(parentEntity).getChildTabbedPane()
					// .openTab(chiled);

					Application.getInstance().getInfoFrame().getInfoSplitPanel().getInfoTabbedPane()
							.getTab(parentEntity).getChildTabbedPane().getChildTab(mEntity).getJTable()
							.setModel(new InfoTableModel(chiled.getData(), chiled.getAttributes().toArray()));
				}
			} else {
				// Default value
				if (mEntity.getBlockFactor() == 0) {
					mEntity.setBlockFactor(20);
					mTable.getBlockTextField().setText("20");
				}

				((SekFile) mEntity).setFilePointer(0);

				mEntity.fetch();
				mTable.getJTable().setModel(new InfoTableModel(mEntity.getData(), mEntity.getAttributes().toArray()));
				mTable.getNumofFetchLabel().setText(String.valueOf(mEntity.getNumOfFetch()));
			}

			mEntity.setNumOfFetch(mEntity.getNumOfFetch() + 1);
			mTable.getNumofFetchLabel().setText("Broj pristupa fajlu na disku: " + mEntity.getNumOfFetch());

			
		}

	}

	public Entity getEntity() {

		return mEntity;
	}

	public void setEntity(Entity mEntity) {

		this.mEntity = mEntity;
	}

	public InfoTablePanel getTable() {

		return mTable;
	}

	public void setTable(InfoTablePanel mTable) {

		this.mTable = mTable;
	}

}

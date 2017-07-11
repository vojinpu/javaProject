package app.controllers.data;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import enums.Action;
import model.Entity;
import model.SekFile;
import model.Table;
import view.InfoTable;
import view.InfoTableModel;
import view.InfoTablePanel;
import view.dialogs.DBSearchDialog;
import view.dialogs.FormDialog;

public class SearchController extends AbstractAction {

	private Entity mEntity;
	private InfoTablePanel mTable;

	public SearchController() {
		putValue(NAME, "Tra\u017Ei");
		putValue(SHORT_DESCRIPTION, "Tra\u017Ei");
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		search();
	}

	public void search() {

		if(mEntity instanceof SekFile){
			((SekFile) mEntity).ResetSearchPointer();
		FormDialog formDialog = new FormDialog(mEntity, Action.SEARCH);
		formDialog.setTable(mTable);
		
		mEntity.setNumOfFetch(mEntity.getNumOfFetch() + 1);
		mTable.getNumofFetchLabel().setText("Broj pristupa fajlu na disku: " + mEntity.getNumOfFetch());
		} else if(mEntity instanceof Table){
			new DBSearchDialog(mEntity);
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

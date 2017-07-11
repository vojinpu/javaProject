package app.controllers.data;

import java.awt.Dialog;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.AbstractAction;
import javax.swing.JOptionPane;

import enums.Action;
import model.Entity;
import model.SekFile;
import model.SerFile;
import model.Table;
import view.InfoTable;
import view.InfoTablePanel;
import view.dialogs.DBAddDialog;
import view.dialogs.FormDialog;

public class AddController extends AbstractAction {

	private Entity mEntity;
	private InfoTablePanel mTable;

	public AddController() {
		putValue(NAME, "Dodaj");
		putValue(SHORT_DESCRIPTION, "Dodaj");
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {

		if (mEntity instanceof SekFile) {
			FormDialog formDialog = new FormDialog(mEntity, Action.ADD);
		} else if (mEntity instanceof Table) {
			new DBAddDialog(mEntity,mTable);
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

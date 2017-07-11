package app.controllers.data;

import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.JOptionPane;

import app.Application;
import model.Entity;
import view.InfoTable;
import view.InfoTablePanel;

public class DeleteController extends AbstractAction {

	private Entity mEntity;
	private InfoTablePanel mTable;

	public DeleteController() {
		putValue(NAME, "Bri\u0161i");
		putValue(SHORT_DESCRIPTION, "Bri\u0161i");
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		delete();
	}

	public void delete() {

		ArrayList<String> rezults = mTable.getDataAtSelectedColums();

		if (rezults.size() == 0)
			JOptionPane.showMessageDialog(mTable, "Izaberite red za brisanje");

		else {

			for (String rezult : rezults) {

				Application.getInstance().setQuery('D' + rezult +'\r'+'\n');
				mEntity.delete();

			}

			JOptionPane.showMessageDialog(mTable, "Uspešno obrisani");
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

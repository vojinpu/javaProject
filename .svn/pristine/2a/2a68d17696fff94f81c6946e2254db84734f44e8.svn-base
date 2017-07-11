package app.controllers.data;

import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.JOptionPane;

import enums.Action;
import model.Entity;
import model.SekFile;
import model.Table;
import view.InfoTable;
import view.InfoTablePanel;
import view.dialogs.DBEditDialog;
import view.dialogs.FormDialog;

public class ModifyController extends AbstractAction {

	private Entity mEntity;
	private InfoTablePanel mTable;

	public ModifyController() {
		putValue(NAME, "Izmeni");
		putValue(SHORT_DESCRIPTION, "Izmeni");
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		modify();
	}

	public void modify() {

		InfoTable table = mTable.getJTable();
		System.out.println(mTable.getEntity().getName());
		if (table.getSelectedRowCount() != 1)
			JOptionPane.showMessageDialog(mTable, "Izaberite jedan red za menjanje");

		else {

			if (mEntity instanceof SekFile) {
				FormDialog formDialog = new FormDialog(mEntity, Action.EDIT);
				formDialog.fillFields(table);
				System.out.println("entity " + mEntity.getName());
			} else if (mEntity instanceof Table) {
				ArrayList<String> data = new ArrayList<>();
				for(int i = 0;i < table.getModel().getColumnCount();i++){
					data.add((String) table.getModel().getValueAt(table.getSelectedRow(), i));
				}
				new DBEditDialog(mEntity, data,mTable);
			}
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

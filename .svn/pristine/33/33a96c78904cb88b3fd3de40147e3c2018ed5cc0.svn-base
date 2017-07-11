package app.controllers.data;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import model.Entity;
import view.InfoTableModel;
import view.InfoTablePanel;

public class FetchController extends AbstractAction {

	//NECE MOCI ISTA INSTANCA ZA OBE TABELE, NAPRAVITI DVE INSTANCE
	
	private Entity mModel;
	private InfoTablePanel mView;

	public FetchController() {
		putValue(NAME, "Prikaži");
		putValue(SHORT_DESCRIPTION, "Prikaži");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//System.out.println("POKUSAN FETCH");
		mModel = mView.getEntity();
		System.out.println("U tabeli je" + mModel.getName());
		mModel.fetch();
		
		mView.getJTable().setModel(new InfoTableModel(mModel.getData(), mModel.getAttributes().toArray()));
		mView.getNumofFetchLabel().setText(String.valueOf(mModel.getNumOfFetch()));
	}

	public Entity getModel() {
		return mModel;
	}

	public void setModel(Entity mModel) {
		this.mModel = mModel;
	}

	public InfoTablePanel getView() {
		return mView;
	}

	public void setView(InfoTablePanel mView) {
		this.mView = mView;
	}

}

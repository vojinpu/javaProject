package app.controllers.data;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Icon;
import javax.swing.JOptionPane;

import model.Entity;
import view.InfoTablePanel;

public class BlockFactorController extends AbstractAction {

	private Entity mModel;
	private InfoTablePanel mView;
	
	public BlockFactorController() {
		putValue(NAME, "Primeni");
		putValue(SHORT_DESCRIPTION, "Primeni");
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		String factorString = mView.getBlockTextField().getText();
		int blockFactor = 0;
		try {
			blockFactor = Integer.parseInt(factorString);
		} catch (NumberFormatException e){
			JOptionPane.showMessageDialog(null, "Morate uneti broj!", "Gre\u0161ka",
					JOptionPane.WARNING_MESSAGE);
			return;
		}
		
		if (blockFactor > 100){
			JOptionPane.showMessageDialog(null, "Unesite broj manji od 100!", "Gre\u0161ka",
					JOptionPane.WARNING_MESSAGE);
			return;
		}
		
		mModel.setBlockFactor(blockFactor);

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

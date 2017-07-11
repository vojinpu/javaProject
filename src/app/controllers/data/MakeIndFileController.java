package app.controllers.data;

import java.awt.event.ActionEvent;
import java.io.IOException;

import javax.swing.AbstractAction;
import javax.swing.JOptionPane;

import model.ISekFile;
import model.SekFile;

public class MakeIndFileController extends AbstractAction{
	private SekFile mModel;

	public MakeIndFileController() {
		putValue(NAME, "Napravi Ind");
		putValue(SHORT_DESCRIPTION, "Napravi Ind file");
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		try {
			mModel.makeIndFile();
			JOptionPane.showMessageDialog(null, "Napravljen Ind file",
					"", JOptionPane.INFORMATION_MESSAGE);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	public SekFile getModel() {
		return mModel;
	}

	public void setModel(SekFile mModel) {
		this.mModel = mModel;
	}
}

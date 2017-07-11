package app.controllers.pathdialog;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import app.Application;
import view.dialogs.AbstractPathDialog;

public class SavePathController extends AbstractAction{

	private AbstractPathDialog mPathDialog;
	
	public SavePathController() {
		putValue(NAME, "Sa\u010Duvaj");
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Application.getInstance().getContext().setMetaPath(mPathDialog.getPathTextField().getText());
		//TODO Svaki put kad se klikne na save i promeni se meta shema mora se inicijalizirati novi repo?
		
		mPathDialog.disposeAndParse();
	}

	public void setPathDialog(AbstractPathDialog mSetPathDialog) {
		this.mPathDialog = mSetPathDialog;
	}
}

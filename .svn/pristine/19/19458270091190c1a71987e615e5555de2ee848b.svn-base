package app.controllers.pathdialog;

import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.AbstractAction;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import view.dialogs.AbstractPathDialog;

public class SetPathController extends AbstractAction {

	private AbstractPathDialog mPathDialog;
	
	public SetPathController() {
		putValue(NAME, "U\u010Ditaj putanju");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JFileChooser jfc = new JFileChooser();
		jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
		jfc.addChoosableFileFilter(new FileNameExtensionFilter("JSON meta fajlovi", "json", "txt"));
		jfc.setAcceptAllFileFilterUsed(false);

		int returnVal = jfc.showOpenDialog(null);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			File filePath = jfc.getSelectedFile();
			
			mPathDialog.getPathTextField().setText(filePath.getAbsolutePath());
			mPathDialog.getOkButton().setEnabled(true);
		}
	}
	
	public void setPathDialog(AbstractPathDialog dialog) {
		mPathDialog = dialog;
	}
	
}

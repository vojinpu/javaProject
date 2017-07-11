package app.controllers.editdialog;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.swing.AbstractAction;

import app.Application;
import view.dialogs.EditDialog;

public class LoadSchemeToEditorController extends AbstractAction {
	
	/* Akcija koja iscitava sadrzaj fajla na koji
	 * pokazuje putanja meta sheme i pretvara ga u
	 * string, a onda taj string setuje u editor
	 */
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		EditDialog editDialog = (EditDialog)e.getSource();
		
		String path = Application.getInstance().getContext().getMetaPath();
		String scheme = "";
		byte[] stringBytes = new byte[] {};
		
		try {
			stringBytes = Files.readAllBytes(Paths.get(path));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		try {
			scheme = new String(stringBytes, "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		
		editDialog.getEditArea().setText(scheme);
		
	}

}

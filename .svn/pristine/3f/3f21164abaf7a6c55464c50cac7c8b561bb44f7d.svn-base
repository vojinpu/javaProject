package app.controllers.editdialog;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.swing.AbstractAction;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import com.github.fge.jsonschema.core.report.ProcessingReport;

import app.Application;
import util.Util;
import view.dialogs.EditDialog;
import view.dialogs.JsonValidationDialog;

public class SaveMetaSchemeController extends AbstractAction {
	
	/* Akica koja se poziva na klik dugmeta Sacuvaj u
	 * editoru, proverava da li je editovani string
	 * sintaksno validan i da li je validan u odnosu 
	 * na meta meta shemu. Za razliku od InitialPathDialoga
	 * koristi metodu koja vraca report, a ne boolean.
	 */
	
	private EditDialog mEditDialog;
	
	public SaveMetaSchemeController() {
		putValue(NAME, "Sa\u010Duvaj izmene");
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		Path path = Paths.get(Application.getInstance().getContext().getMetaPath());
		String string = mEditDialog.getEditArea().getText();
		
		ProcessingReport report = Util.validateJSON(string);
		
		if (report == null){
			JOptionPane.showMessageDialog(null, "JSON je sintaksno neispravan!", "Gre\u0161ka",
					JOptionPane.WARNING_MESSAGE);
		} else if (report.isSuccess()){
			
			try {
				Files.write(path, string.getBytes("UTF-8"));
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			mEditDialog.dispose();
			
		} else {
			
			SwingUtilities.invokeLater(new Runnable() {
				
				@Override
				public void run() {
					new JsonValidationDialog(report, mEditDialog);
					
				}
			});
			
		}
		
		
		
	}
	
	public void setEditDialog(EditDialog editDialog) {
		mEditDialog = editDialog;
	}

}

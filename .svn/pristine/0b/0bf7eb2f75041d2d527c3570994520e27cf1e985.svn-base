package app.controllers.editdialog;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;

import app.Application;
import enums.UserType;
import util.Util;
import view.dialogs.EditDialog;

public class StartEditorController extends AbstractAction{

	public StartEditorController() {
		putValue(NAME, "Pokreni editor");
		putValue(SMALL_ICON, Util.loadIcon(getClass(), "meta24.png"));
		putValue(LARGE_ICON_KEY, Util.loadIcon(getClass(), "meta32.png"));
		putValue(SHORT_DESCRIPTION, "Pokreni editor meta sheme");
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.CTRL_MASK));
		putValue(MNEMONIC_KEY, KeyEvent.VK_E);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (Application.getInstance().getContext().getMetaPath() == null){
			JOptionPane.showMessageDialog(null, "Morate uneti putanju do meta \u0161eme pre pokretanja editora!", "Gre\u0161ka",
					JOptionPane.WARNING_MESSAGE);
		} else {
			SwingUtilities.invokeLater(new Runnable() {
				
				@Override
				public void run() {
					new EditDialog(Application.getInstance().getCurrentUser().getUserType().equals(UserType.ADMIN));
				}
			});
		}
		
		
		
	}

}

package app.controllers.pathdialog;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;

import util.Util;
import view.dialogs.AdminPathDialog;

public class StartAdminPathDialogController extends AbstractAction {

	public StartAdminPathDialogController() {
		putValue(NAME, "Promeni putanju");
		putValue(SMALL_ICON, Util.loadIcon(getClass(), "path24.png"));
		putValue(LARGE_ICON_KEY, Util.loadIcon(getClass(), "path32.png"));
		putValue(SHORT_DESCRIPTION, "Promeni putanju do meta sheme");
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.CTRL_MASK));
		putValue(MNEMONIC_KEY, KeyEvent.VK_P);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				new AdminPathDialog();
			}
		});

	}

}

package app.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;

import util.Util;
import view.dialogs.AboutUsWindow;

public class AboutDialogController extends AbstractAction{

	public AboutDialogController() {
		// TODO Auto-generated constructor stub
		putValue(NAME, "O nama");
		putValue(SMALL_ICON, Util.loadIcon(getClass(), "about24.png"));
		putValue(LARGE_ICON_KEY, Util.loadIcon(getClass(), "about32.png"));
		putValue(SHORT_DESCRIPTION, "Pokreni prozor o nama");
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
		putValue(MNEMONIC_KEY, KeyEvent.VK_O);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				new AboutUsWindow();
			}
		});
	}
}

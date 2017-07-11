package app.controllers.app;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;

import app.Application;
import util.Util;
import view.dialogs.LogInDialog;

public class LogOffController extends AbstractAction {

	public LogOffController () {
		putValue(NAME, "Odjavi se");
		putValue(SMALL_ICON, Util.loadIcon(getClass(), "key24.png"));
		putValue(LARGE_ICON_KEY, Util.loadIcon(getClass(), "key32.png"));
		putValue(SHORT_DESCRIPTION, "Izloguj se iz aplikacije");
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_L, ActionEvent.CTRL_MASK));
		putValue(MNEMONIC_KEY, KeyEvent.VK_L);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		//TODO A menubar?
		Application.getInstance().getInfoFrame().getContentPane().removeAll();
		Application.getInstance().getInfoFrame().revalidate();
		Application.getInstance().getInfoFrame().repaint();
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				new LogInDialog(Application.getInstance(), Application.getInstance().getContext());
				
			}
		});
		
	}

}

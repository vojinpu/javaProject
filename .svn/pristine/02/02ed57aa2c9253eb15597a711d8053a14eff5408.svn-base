package app.controllers.app;

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.net.URI;

import javax.swing.AbstractAction;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

import util.ApplicationConstants;
import util.Util;

public class VisitRepoTutController extends AbstractAction{

	public VisitRepoTutController() {
		putValue(NAME, "Poseti wiki");
		putValue(SMALL_ICON, Util.loadIcon(getClass(), "online24.png"));
		putValue(LARGE_ICON_KEY, Util.loadIcon(getClass(), "online24.png"));
		putValue(SHORT_DESCRIPTION, "Poseti wiki");
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_W, ActionEvent.CTRL_MASK));
		putValue(MNEMONIC_KEY, KeyEvent.VK_W);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		openWebpage(URI.create(ApplicationConstants.REPO_URL));
		
	}
	
	private void openWebpage(URI uri) {
		Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
		if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
			try {
				desktop.browse(uri);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Do\u0161lo je do gre\u0161ke!", "Gre\u0161ka",
						JOptionPane.WARNING_MESSAGE);
			}
		}
	}

}

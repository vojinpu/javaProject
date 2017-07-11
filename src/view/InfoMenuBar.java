package view;

import javax.swing.JMenu;
import javax.swing.JMenuBar;

import app.ControllerManager;

public class InfoMenuBar extends JMenuBar {

	public InfoMenuBar() {
		
		JMenu appMenu = new JMenu("Aplikacija");
		appMenu.setMnemonic('A');
		appMenu.add(ControllerManager.getInstance().getLogOffController());
		appMenu.add(ControllerManager.getInstance().getExitController());
		//appMenu.add(ActionManager.getInstance().getAboutDialogAction());
		
		add(appMenu);
	}

}

package view;

import javax.swing.JMenu;

import app.ControllerManager;

public class UserInfoMenuBar extends InfoMenuBar {
	public UserInfoMenuBar() {
		super();
		JMenu metaEditorMenu = new JMenu("Meta pregled");
		metaEditorMenu.setMnemonic('P');
		metaEditorMenu.add(ControllerManager.getInstance().getStartEditorController());
		metaEditorMenu.add(ControllerManager.getInstance().getStartUserPathDialogController());

		add(metaEditorMenu);
		
		JMenu aboutMenu = new JMenu("O nama");
		aboutMenu.setMnemonic('O');
		aboutMenu.add(ControllerManager.getInstance().getVisitRepoTutController());
		aboutMenu.add(ControllerManager.getInstance().getAboutDialogController());
		
		add(aboutMenu);
	}
}

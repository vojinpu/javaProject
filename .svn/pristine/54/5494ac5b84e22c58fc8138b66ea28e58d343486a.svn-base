package view;

import javax.swing.JMenu;

import app.ControllerManager;

public class AdminInfoMenuBar extends InfoMenuBar {

	public AdminInfoMenuBar () {
		super();
		JMenu metaEditorMenu = new JMenu("Meta Editor");
		metaEditorMenu.setMnemonic('E');
		metaEditorMenu.add(ControllerManager.getInstance().getStartEditorController());
		metaEditorMenu.add(ControllerManager.getInstance().getStartAdminPathDialogController());

		add(metaEditorMenu);
		
		JMenu aboutMenu = new JMenu("O nama");
		aboutMenu.setMnemonic('O');
		aboutMenu.add(ControllerManager.getInstance().getVisitRepoTutController());
		aboutMenu.add(ControllerManager.getInstance().getAboutDialogController());
		
		add(aboutMenu);
	}
	
}

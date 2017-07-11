package view.dialogs;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.nio.file.Paths;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import app.ControllerManager;
import app.Application;
import util.Util;

public class UserPathDialog extends AbstractPathDialog {

	/* Dijalog koji se poziva samo iz korisnickog 
	 * okruzenja, proverava validnost sheme
	 * metodom iz Utila koji vraca boolean
	 * jer korisniku nije bitno da li je shema
	 * sintaksno neispravna ili nije u skladu sa
	 * meta shemom jer je on u svakom slucaju
	 * ne moze editovati.
	 */
	
	private JTextField mPathTextField;
	private JCheckBox mCheckBox;
	private JButton mOkButton;
	
	public UserPathDialog() {

		ControllerManager.getInstance().getSetPathController().setPathDialog(this);
		ControllerManager.getInstance().getSavePathController().setPathDialog(this);
		
		initUI();
		setTitle("Postavi putanju do meta \u0161eme");
		setModalityType(ModalityType.APPLICATION_MODAL);
		setIconImage(Util.loadIcon(getClass(), "path24.png").getImage());
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}

	private void initUI() {
		
		JPanel dialogPanel = new JPanel();
		dialogPanel.setLayout(new GridLayout(3, 1));
		
		initLoadPanel(dialogPanel);
		initCheckPanel(dialogPanel);
		initButtonPanel(dialogPanel);
		
		add(dialogPanel);
	}

	private void initLoadPanel(JPanel dialogPanel) {
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		mPathTextField = new JTextField(30);
		mPathTextField.setEditable(false);
		
		if (Application.getInstance().getContext().getMetaPath() != null){
			mPathTextField.setText(Application.getInstance().getContext().getMetaPath());
		}
		
		JButton loadButton = new JButton(ControllerManager.getInstance().getSetPathController());
		
		panel.add(mPathTextField);
		panel.add(loadButton);
		
		dialogPanel.add(panel);
	}
	
	private void initCheckPanel(JPanel dialogPanel) {
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		mCheckBox = new JCheckBox("Podesi kao podrazumevanu lokaciju");
		
		if (Application.getInstance().getContext().isDeafultSet()){
			mCheckBox.setSelected(true);
		}
		
		mCheckBox.setEnabled(false);
		panel.add(mCheckBox);
		
		dialogPanel.add(panel);
	}
	
	private void initButtonPanel(JPanel dialogPanel) {
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		mOkButton = new JButton(new AbstractAction("Sa\u010Duvaj") {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (Util.validateJSON(Paths.get(mPathTextField.getText()))) {
					System.out.println("Probao si da ucitas ispravnu meta shemu");
					//posto je ispravna setujem novi path u context
					Application.getInstance().getContext().setMetaPath(mPathTextField.getText());
					//sad pokrecem parsiranje novog modela
					ControllerManager.getInstance().getParseController().actionPerformed(null);
					//sad kad se parsiranje zavrsilo, reloadujem drvo i tabele
					Application.getInstance().getInfoFrame().getInfoSplitPanel().getInfoTree().reload();
					Application.getInstance().getInfoFrame().getInfoSplitPanel().getInfoTabbedPane().removeAll();
					UserPathDialog.this.dispose();
				} else {
					JOptionPane.showMessageDialog(null,
							"U\u010Ditavate neispravnu meta \u0161emu! Kontaktirajte administratora, ili se ulogujte sa administratorskim privilegijama!",
							"Gre\u0161ka", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		mOkButton.setEnabled(false);
		
		panel.add(mOkButton);
		
		dialogPanel.add(panel);
	}

	public JTextField getPathTextField() {
		return mPathTextField;
	}

	public JCheckBox getCheckBox() {
		return mCheckBox;
	}
	
	public JButton getOkButton() {
		return mOkButton;
	}
	
	

}

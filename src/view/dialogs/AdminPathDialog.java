package view.dialogs;

import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JTextField;

import app.ControllerManager;
import app.Application;
import util.Util;

public class AdminPathDialog extends AbstractPathDialog {

	/* Dijalog koji se poziva samo iz administratorskog
	 * korisnickog okruzenja, ne proverava validnost sheme
	 * koja se ucitva jer ce ona svakako biti proverena
	 * kad se bude cuvala posle izmena u editoru, ako
	 * ako administrator ne pokrene editor, korisnik svakako
	 * nece moci da pokrene nevalidnu shemu jer ce se u tom
	 * slucaju aktivirati check u onLogIn u Application-u
	 * gde se proverava da li je setovan path i ako jeste
	 * da li pokazuje na validnu shemu
	 */
	
	private JTextField mPathTextField;
	private JCheckBox mCheckBox;
	private JButton mOkButton;
	
	public AdminPathDialog() {

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
		
		mOkButton = new JButton(ControllerManager.getInstance().getSavePathController());
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

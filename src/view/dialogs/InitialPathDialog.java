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
import interfaces.OnPathSetCallback;
import listeners.PathDialogAdapter;
import util.Util;

/* Dijalog koji se koristi samo pri setovanju
 * putanje meta sheme pri startovanju  programa i to
 * ukoliko ona nije ranije setovana, ili ako
 * je administrator setovao nevalidnu shemu. Koristi
 * util metodu koja vraca boolean, jer ovde nije
 * bitno da li je JSON samo sintaksno neispravan
 * ili nije u skladu sa meta shemom,
 * jer korisnik ne moze da edituje shemu, vec se
 * mora ulogovati kao administrator.
 * */

public class InitialPathDialog extends AbstractPathDialog {

	private JTextField mPathTextField;
	private JCheckBox mCheckBox;
	private JButton mOkButton;
	// TODO Implementiraj ga kao weakreference
	private OnPathSetCallback mCallback;

	public InitialPathDialog(OnPathSetCallback callback) {

		System.out.println("InitialPath - pocetak konstruktora");
		mCallback = callback;

		ControllerManager.getInstance().getSetPathController().setPathDialog(this);
		ControllerManager.getInstance().getSavePathController().setPathDialog(this);

		initUI();
		setTitle("Postavi putanju do meta \u0161eme");
		setModalityType(ModalityType.APPLICATION_MODAL);
		addWindowListener(new PathDialogAdapter());
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setIconImage(Util.loadIcon(getClass(), "path24.png").getImage());
		
		pack();
		setLocationRelativeTo(null);
		System.out.println("InitialPath - pre setVisible");
		setVisible(true);
		System.out.println("InitialPath - posle setVisible");
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

		JButton loadButton = new JButton(ControllerManager.getInstance().getSetPathController());

		panel.add(mPathTextField);
		panel.add(loadButton);

		dialogPanel.add(panel);
	}

	private void initCheckPanel(JPanel dialogPanel) {
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout(FlowLayout.CENTER));

		// TODO Promeniti kad odlucimo da li ce biti menjanja sheme on the fly
		mCheckBox = new JCheckBox("Podesi kao podrazumevanu lokaciju");
		mCheckBox.setSelected(true);
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
					mCallback.onPathSet(mPathTextField.getText());
					InitialPathDialog.this.dispose();
				} else {
					JOptionPane.showMessageDialog(null,
							"U\u010Ditavate neispravnu meta \u0161emu! Kontaktirajte administratora, ili se ulogujte sa administratorskim privilegijama!",
							"Gre\u0161ka", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		mOkButton.setEnabled(false);

		JButton cancelButton = new JButton(new AbstractAction("Napusti aplikaciju") {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		JButton logoutButton = new JButton(new AbstractAction("Izloguj se") {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				InitialPathDialog.this.dispose();
				ControllerManager.getInstance().getLogOffController().actionPerformed(null);
				
			}
		});

		panel.add(mOkButton);
		panel.add(cancelButton);
		panel.add(logoutButton);

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

	public void disposeAndParse() {
		ControllerManager.getInstance().getParseController().actionPerformed(new ActionEvent(this, 0, "dialog"));
		dispose();
	}
}

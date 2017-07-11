package view.dialogs;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

import app.Context;
import interfaces.OnLogIn;
import listeners.LogInAdapter;
import model.User;
import util.Util;

public class LogInDialog extends JDialog {

	private JTextField mUserNameTextField;
	private JPasswordField mPasswordTextField;

	public LogInDialog(OnLogIn callback, Context context) {
		//System.out.println("LogIn - zapocet konstruktor");
		
		initUI(callback, context);
		setTitle("Ulogujte se");
		setModalityType(ModalityType.APPLICATION_MODAL);
		addWindowListener(new LogInAdapter());
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setIconImage(Util.loadIcon(getClass(), "key24.png").getImage());
		pack();
		setLocationRelativeTo(null);
		//System.out.println("LogIn - pre set visible");

		setVisible(true);
		//System.out.println("LogIn - posle set visible");
	}

	private void initUI(OnLogIn callback, Context context) {
		JPanel dialogPanel = new JPanel();
		dialogPanel.setLayout(new GridLayout(3, 2));
		initFields(dialogPanel);
		initButtons(dialogPanel, callback, context);
		add(dialogPanel);
	}

	private void initFields(JPanel dialogPanel) {

		JLabel usernameLabel = new JLabel("  Korisni\u010Dko ime:");
		JLabel passwordLabel = new JLabel("  \u0160ifra:");

		mUserNameTextField = new JTextField(15);
		mPasswordTextField = new JPasswordField(15);
		
		JPanel userFieldPanel = new JPanel();
		JPanel passFieldPanel = new JPanel();
		
		userFieldPanel.add(mUserNameTextField);
		passFieldPanel.add(mPasswordTextField);

		dialogPanel.add(usernameLabel);
		dialogPanel.add(userFieldPanel);
		dialogPanel.add(passwordLabel);
		dialogPanel.add(passFieldPanel);
	}

	private void initButtons(JPanel dialogPanel, OnLogIn callback, Context context) {

		JPanel leftButtonPanel = new JPanel();
		JPanel rightButtonPanel = new JPanel();

		JButton okButton = new JButton(new AbstractAction("Pristupi") {

			@Override
			public void actionPerformed(ActionEvent e) {
				String username = mUserNameTextField.getText();
				String password = mPasswordTextField.getText();

				boolean isFound = false;
				User currentUser = null;

				for (User user : context.getUsers()) {
					if (user.getName().equals(username) && user.getPassword().equals(password)) {
						isFound = true;
						currentUser = user;
					}
				}

				if (isFound) {
					callback.onLogIn(currentUser);
					LogInDialog.this.dispose();
				} else {
					JOptionPane.showMessageDialog(null, "Uneli ste neispravne podatke!", "Gre\u0161ka",
							JOptionPane.WARNING_MESSAGE);
				}

			}
		});

		
		JButton cancelButton = new JButton(new AbstractAction("Odustani") {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(1);
			}
		});
		
		leftButtonPanel.add(okButton);
		rightButtonPanel.add(cancelButton);
		
		dialogPanel.add(leftButtonPanel);
		dialogPanel.add(rightButtonPanel);
		
		setInputMap(dialogPanel, okButton);
	}
	
	private void setInputMap(JPanel dialogPanel, JButton okButton) {
		ActionMap am = dialogPanel.getActionMap();
		InputMap im = dialogPanel.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
		KeyStroke enter = KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0);
		KeyStroke delete = KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0);

		im.put(enter, "enter");
		im.put(delete, "delete");

		am.put("enter", okButton.getAction());
	}
}

package view.dialogs;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.io.IOException;

import javax.swing.AbstractAction;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.SyntaxConstants;
import org.fife.ui.rsyntaxtextarea.Theme;
import org.fife.ui.rtextarea.RTextScrollPane;

import app.ControllerManager;
import app.Application;
import util.Util;

public class EditDialog extends JDialog {

	private final static int ROWS = 45;
	private final static int COLUMNS = 100;
	public final static String PREVIOUS = "previous";
	public final static String NEXT = "next";
	public final static String REPLACE = "replace";
	public final static String REPLACE_ALL = "replace_all";
	public final static String LOAD_SCHEME = "load_scheme";

	private JTextField mSearchTextField;
	private JTextField mReplaceTextField;
	private RSyntaxTextArea mEditArea;
	private JCheckBox mWholeWordCB;

	public EditDialog(boolean isEditable) {

		// Ostao artifact kad je sve prebaceno u jednu akciju
		// ActionManager.getInstance().prepareForEditDialog(this);
		ControllerManager.getInstance().getMetaToolbarController().setEditDialog(this);
		ControllerManager.getInstance().getSaveMetaSchemeController().setEditDialog(this);

		initUI(isEditable);
		setTitle("Editor meta \u0161eme - " + Application.getInstance().getContext().getMetaPath());
		setModalityType(ModalityType.APPLICATION_MODAL);
		setIconImage(Util.loadIcon(getClass(), "meta24.png").getImage());
		pack();
		setVisible(true);
	}

	private void initUI(boolean isEditable) {
		JPanel dialogPanel = new JPanel();
		dialogPanel.setLayout(new BorderLayout()); // vgap, hgap

		initEditArea(dialogPanel, isEditable);
		initButtonPanel(dialogPanel, isEditable);
		initSearchToolbar(dialogPanel, isEditable);

		add(dialogPanel);
		pack();
		setLocationRelativeTo(null);
	}

	private void initSearchToolbar(JPanel dialogPanel, boolean isEditable) {

		JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		Dimension prefferedSize;

		mSearchTextField = new JTextField(15);
		JButton prevButton = new JButton(ControllerManager.getInstance().getMetaToolbarController());
		prevButton.setActionCommand(PREVIOUS);
		prevButton.setText("<<");
		JButton nextButton = new JButton(ControllerManager.getInstance().getMetaToolbarController());
		nextButton.setActionCommand(NEXT);
		nextButton.setText(">>");
		prefferedSize = prevButton.getPreferredSize();
		mSearchTextField.setPreferredSize(prefferedSize);

		searchPanel.add(mSearchTextField);
		searchPanel.add(Box.createHorizontalStrut(15));
		searchPanel.add(prevButton);
		searchPanel.add(nextButton);
		searchPanel.add(Box.createHorizontalStrut(15));

		mReplaceTextField = new JTextField(15);
		mReplaceTextField.setEditable(isEditable);
		JButton replaceButton = new JButton(ControllerManager.getInstance().getMetaToolbarController());
		replaceButton.setActionCommand(REPLACE);
		replaceButton.setText("Izmeni slede\u0107i");
		replaceButton.setEnabled(isEditable);
		JButton replaceAllButton = new JButton(ControllerManager.getInstance().getMetaToolbarController());
		replaceAllButton.setActionCommand(REPLACE_ALL);
		replaceAllButton.setText("Izmeni sve");
		replaceAllButton.setEnabled(isEditable);

		searchPanel.add(mReplaceTextField);
		searchPanel.add(Box.createHorizontalStrut(15));
		searchPanel.add(replaceButton);
		searchPanel.add(replaceAllButton);

		searchPanel.add(Box.createHorizontalStrut(15));
		mWholeWordCB = new JCheckBox("Kompletne re\u010Di");
		searchPanel.add(mWholeWordCB);

		dialogPanel.add(searchPanel, BorderLayout.NORTH);
		
		searchPanel.setEnabled(isEditable);
	}

	private void initEditArea(JPanel dialogPanel, boolean isEditable) {
		mEditArea = new RSyntaxTextArea(ROWS, COLUMNS);
		mEditArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_JSON);
		mEditArea.setCodeFoldingEnabled(true);
		mEditArea.setEditable(isEditable);
		
		changeStyleViaThemeXml();

		ControllerManager.getInstance().getLoadSchemeToEditorController().actionPerformed(new ActionEvent(this, 0, LOAD_SCHEME));
		
		RTextScrollPane scrollPane = new RTextScrollPane(mEditArea);
		dialogPanel.add(scrollPane, BorderLayout.CENTER);
	}

	private void initButtonPanel(JPanel dialogPanel, boolean isEditable) {
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

		JButton okButton = new JButton(ControllerManager.getInstance().getSaveMetaSchemeController());
		okButton.setEnabled(isEditable);
		JButton cancelButton = new JButton(new AbstractAction("Odustani") {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		buttonPanel.add(okButton);
		buttonPanel.add(cancelButton);

		dialogPanel.add(buttonPanel, BorderLayout.SOUTH);
	}

	public RSyntaxTextArea getEditArea() {
		return mEditArea;
	}

	public JTextField getSearchTextField() {
		return mSearchTextField;
	}

	public String getSearchTerm() {
		return mSearchTextField.getText();
	}

	public JTextField getReplaceTextField() {
		return mReplaceTextField;
	}

	public String getReplaceTerm() {
		return mReplaceTextField.getText();
	}

	public boolean isWholeWordChecked() {
		return mWholeWordCB.isSelected();
	}

	private void changeStyleViaThemeXml() {
	      try {
	         Theme theme = Theme.load(getClass().getResourceAsStream(
	               "/res/default.xml"));
	         theme.apply(mEditArea);
	      } catch (IOException ioe) { // Never happens
	         ioe.printStackTrace();
	      }
	   }
}

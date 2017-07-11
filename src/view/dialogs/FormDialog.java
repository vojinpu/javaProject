package view.dialogs;

import java.awt.Button;
import java.awt.Checkbox;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Label;
import java.awt.Point;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.lang.reflect.Member;
import java.util.ArrayList;
import java.util.concurrent.SynchronousQueue;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import app.Application;
import enums.Action;
import model.Attribute;
import model.Entity;
import model.SekFile;
import view.InfoTable;
import view.InfoTableModel;
import view.InfoTablePanel;

public class FormDialog extends JDialog {

	private Entity mEntity;
	private JPanel listPane;
	private String test;
	private InfoTablePanel mTable;

	ArrayList<TextField> mLFields = new ArrayList<>();

	public FormDialog(Entity mEntity, Action eAction) {

		super();
		super.setTitle(mEntity.getName());

		this.mEntity = mEntity;
		

		int height = mEntity.getAttributes().size() * 20;
		int width = 100;

		//JFrame frame = new JFrame(mEntity.getName());
		setName(mEntity.getName());
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();

		setLocation(((int) dimension.getWidth() - width) / 2, ((int) dimension.getHeight() - height) / 2);
		setSize(width, height);

		listPane = new JPanel();
		BoxLayout boxlayout = new BoxLayout(listPane, BoxLayout.Y_AXIS);
		listPane.setLayout(boxlayout);

		// Napravi Label i Text Field za svako od polja.
		for (Attribute attribute : mEntity.getAttributes()) {

			Box box = Box.createHorizontalBox();

			Label label = new Label(attribute.getName());
			TextField textField = new TextField(attribute.getLength());
			label.setSize(50, 20);

			// PK se ne moze menjati
			if (Action.EDIT == eAction && attribute.isPK())
				textField.setEditable(false);

			box.add(label);
			box.add(Box.createHorizontalGlue());
			box.add(textField);

			if (attribute.isPK())
				textField.setBackground(Color.LIGHT_GRAY);

			mLFields.add(textField);
			listPane.add(box);

			// Korisnik ne može da unese više karaktera nego što je predviđemo
			textField.addKeyListener(new java.awt.event.KeyAdapter() {
				public void keyTyped(java.awt.event.KeyEvent evt) {
					if (textField.getText().length() >= attribute.getLength()
							&& !(evt.getKeyChar() == KeyEvent.VK_DELETE
									|| evt.getKeyChar() == KeyEvent.VK_BACK_SPACE)) {
						getToolkit().beep();
						evt.consume();
					}
				}
			});

		}

		// Napravi dugme ADD
		if (eAction.equals(Action.ADD))
			addMethod();

		// Napravi dugme EDIT
		if (eAction.equals(Action.EDIT))
			editMethod();

		// Napravi dugme SEARCH
		if (eAction.equals(Action.SEARCH))
			searchMethod();

		
		add(listPane);
		pack();
		setVisible(true);

	}

	private void searchMethod() {

		Box box = Box.createHorizontalBox();

		Checkbox mchFromCurrent = new Checkbox("Od trenutnog");
		Checkbox mchStopOnFirst = new Checkbox("Zaustavi se na prvom");
		Checkbox mchSkladisti = new Checkbox("Skladišti rezultate");

		box.add(mchFromCurrent);
		box.add(mchStopOnFirst);
		box.add(mchSkladisti);

		listPane.add(box);

		Button button = new Button("PRETRAŽI");
		listPane.add(button);

		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (isPKCorrrectForSEARCH()) {

					if (mEntity instanceof SekFile) {
						((SekFile) mEntity).search(getFiledsValues(), mchFromCurrent.getState(),
								mchStopOnFirst.getState(), mchSkladisti.getState());

						mTable.getJTable()
								.setModel(new InfoTableModel(mEntity.getData(), mEntity.getAttributes().toArray()));
						mTable.getNumofFetchLabel().setText(String.valueOf(mEntity.getNumOfFetch()));
						//FormDialog.this.dispose();
					}
					
				}

				else {
					JOptionPane.showMessageDialog(mTable, "Popunite ili sva PK polja, ili neka druga");
					
				}

			}

		});

		// ako je 3. stikliran, 2. odstikliraj
		mchSkladisti.addItemListener(new ItemListener() {

			public void itemStateChanged(ItemEvent e) {
				if (mchSkladisti.getState() == true)
					mchStopOnFirst.setState(false);
			}
		});

	}

	private boolean isPKCorrrectForSEARCH() {

		int n = mEntity.getAttributes().size();
		
		
		boolean allOthersEmpty = true;
		
		boolean allPKFull= true;
		boolean allPKEmpty = true;
		

		for (int i = 0; i < n; i++) {

			if (mEntity.getAttributes().get(i).isPK()) {

				if (mLFields.get(i).getText().trim().isEmpty())
					allPKFull = false;
				
				if(!mLFields.get(i).getText().trim().isEmpty())
					allPKEmpty = false;
				

			}
			
			else{
				
				if (!mLFields.get(i).getText().trim().isEmpty())
					allOthersEmpty = false;
				
			}

		}

		if(allPKFull == allPKEmpty)
			return false;
		
		return allOthersEmpty == allPKFull;
	}

	private void editMethod() {

		Button button = new Button("IZMENI");
		listPane.add(button);

		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				// get file Line and send it to Entity
				String rezult = getFiledsValues();

				// ukoliko je korisnik nesto izmenio
				if (!test.equals(rezult)) {

					// dodaj obe stvari u txt fajl
					if (mEntity instanceof SekFile)
						rezult = 'E' + test+ '\r' + '\n' + rezult+'\r'+'\n';

					Application.getInstance().setQuery(rezult);
					System.out.println(rezult);
					mEntity.modify();
					FormDialog.this.dispose();
				}

				else {

					JOptionPane.showMessageDialog(mTable, "Niste ništa izmenili");

				}

			}
		});

	}

	private void addMethod() {

		Button button = new Button("DODAJ");
		listPane.add(button);

		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				String rezult = getFiledsValues();

				
				if(isValidForADD()){
				
				
				rezult = 'A' + rezult+'\r'+'\n';

				// get file Line and send it to Entity
				Application.getInstance().setQuery(rezult);
				mEntity.add();
				FormDialog.this.dispose();
				}
				
				else{
					
					JOptionPane.showMessageDialog(mTable, "Popunite sva PK polja");
				}
			}


		});

	}

	private String getFiledsValues() {

		String rezult = "";
		int n = 0;
		for (TextField textField : mLFields) {

			rezult += textField.getText();

			int m = mEntity.getAttributes().get(n++).getLength();
			for (int i = textField.getText().length(); i < m; i++)
				rezult += ' ';

		}

		// dodatni space karakter
		//rezult += '\n';

		return rezult;

	}

	public void fillFields(InfoTable table) {

		int n = 0;
		int row = table.getSelectedRow();
		for (Attribute attribute : mEntity.getAttributes()) {

			mLFields.get(n).setText(table.getValueAt(row, n++).toString());
		}

		test = getFiledsValues();
		repaint();
	}

	public void setTable(InfoTablePanel mTable) {
		this.mTable = mTable;

	}
	
	private boolean isValidForADD() {
		
		
		int n = mEntity.getAttributes().size();

		
		boolean allFull = true;

		for (int i = 0; i < n; i++) {

			if (mEntity.getAttributes().get(i).isPK()) {

				if (mLFields.get(i).getText().trim().isEmpty())
					allFull = false;


			}

		}

		return allFull;
	
		
	}

}

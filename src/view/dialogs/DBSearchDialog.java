package view.dialogs;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Label;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout.Constraints;

import com.fasterxml.jackson.databind.annotation.JsonAppend.Attr;

import app.Application;
import enums.Action;
import model.Attribute;
import model.Entity;
import model.Table;
import view.InfoTableModel;

public class DBSearchDialog extends JDialog {

	ArrayList<JTextField> mLFields = new ArrayList<>();
	ArrayList<String> filedTypes = new ArrayList<>();
	ArrayList<String> attrinbuteNames = new ArrayList<>();
	ArrayList<JComboBox> searchKind = new ArrayList<>();

	public DBSearchDialog(Entity entity) {
		setTitle(entity.getName());

		JPanel mainPanel = new JPanel(new BorderLayout());

		int height = entity.getAttributes().size() * 45 + 45;
		int width = 400;

		setName(entity.getName());
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();

		setLocation(((int) dimension.getWidth() - width) / 2, ((int) dimension.getHeight() - height) / 2);

		JPanel listPane = new JPanel();
		BoxLayout boxlayout = new BoxLayout(listPane, BoxLayout.Y_AXIS);
		listPane.setLayout(boxlayout);
		listPane.add(Box.createRigidArea(new Dimension(0, 10)));

		for (Attribute attribute : entity.getAttributes()) {

			Box box = Box.createHorizontalBox();

			JLabel label = new JLabel(attribute.getName());
			JTextField textField = new JTextField(attribute.getLength());
			label.setSize(50, 20);

			box.add(label);
			box.add(Box.createRigidArea(new Dimension(5, 0)));
			box.add(Box.createHorizontalGlue());
			box.add(textField);

			boolean knownType = false;

			if (attribute.getDataType().startsWith("numeric") || attribute.getDataType().equals("int")) {
				String[] items = { "Equal", "Bigger", "Smaller" };
				JComboBox<String> comboBox = new JComboBox<>(items);
				box.add(Box.createRigidArea(new Dimension(5, 0)));
				box.add(comboBox);
				filedTypes.add("number");
				searchKind.add(comboBox);
				knownType = true;
			}

			if (attribute.getDataType().startsWith("varchar") || attribute.getDataType().equals("char")) {
				String[] items = { "Equal", "Starts with", "Ends with", "Contains" };
				JComboBox<String> comboBox = new JComboBox<>(items);
				box.add(Box.createRigidArea(new Dimension(5, 0)));
				box.add(comboBox);
				filedTypes.add("string");
				searchKind.add(comboBox);
				knownType = true;
			}

			if (attribute.getDataType().equals("datetime")) {
				String[] items = { "Equal", "Before", "After" };
				JComboBox<String> comboBox = new JComboBox<>(items);
				box.add(Box.createRigidArea(new Dimension(5, 0)));
				box.add(comboBox);
				filedTypes.add("date");
				searchKind.add(comboBox);
				
				knownType = true;
			}

			if (attribute.isPK())
				textField.setBackground(Color.LIGHT_GRAY);
			else if (attribute.isNotNULL()) {
				textField.setBackground(Color.CYAN);
			}

			if (knownType) {
				JLabel jLabel = new JLabel(" Data type: " + attribute.getDataType());
				box.add(jLabel);
				attrinbuteNames.add(attribute.getName());
				mLFields.add(textField);
				listPane.add(box);
				listPane.add(Box.createRigidArea(new Dimension(0, 5)));
			}
		}
		listPane.add(Box.createRigidArea(new Dimension(0, 5)));
		JButton search = new JButton("Search");
		search.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String query = "select count(*) from " + entity.getName() + " where ";
				ArrayList<String> queryData = new ArrayList<>();
				boolean empty = true;
				ArrayList<String> searchData = new ArrayList<>();
				int j = 0;
				for (int i = 0; i < mLFields.size(); i++) {
					if (mLFields.get(i).getText().length() > 0) {
						searchData.add(mLFields.get(i).getText());
						empty = false;
						if (filedTypes.get(i).equals("string")) {
							if (searchKind.get(i).getSelectedItem().equals("Equal")) {
								query+= attrinbuteNames.get(i) + " like ? and ";
							} else if (searchKind.get(i).getSelectedItem().equals("Starts with")) {
								query+= attrinbuteNames.get(i) + " like ? and ";
								String dataString = mLFields.get(i).getText() + "%";
								searchData.remove(j);
								searchData.add(dataString);
							} else if (searchKind.get(i).getSelectedItem().equals("Ends with")) {
								query+= attrinbuteNames.get(i) + " like %? and ";
								String dataString = "%"+mLFields.get(i).getText();
								searchData.remove(j);
								searchData.add(dataString);
							} else if (searchKind.get(i).getSelectedItem().equals("Contains")) {
								query+= attrinbuteNames.get(i) + " like %?% and ";
								String dataString = "%"+mLFields.get(i).getText() + "%";
								searchData.remove(j);
								searchData.add(dataString);
							}
						} else if (filedTypes.get(i).equals("date")) {
							if (searchKind.get(i).getSelectedItem().equals("Equal")) {
								query+= attrinbuteNames.get(i) + " = ? and ";
							} else if (searchKind.get(i).getSelectedItem().equals("Before")) {
								query+= attrinbuteNames.get(i) + " < ? and ";
							} else if (searchKind.get(i).getSelectedItem().equals("After")) {
								query+= attrinbuteNames.get(i) + " > ? and ";
							}
						} else if (filedTypes.get(i).equals("number")) {
							if (searchKind.get(i).getSelectedItem().equals("Equal")) {
								query+= attrinbuteNames.get(i) + " = ? and ";
							} else if (searchKind.get(i).getSelectedItem().equals("Bigger")) {
								query+= attrinbuteNames.get(i) + " > ? and ";
							} else if (searchKind.get(i).getSelectedItem().equals("Smaller")) {
								query+= attrinbuteNames.get(i) + " < ? and ";
							}
						}
						j++;
					}
					
				}
				if(!empty){
					query = query.substring(0, query.length()-5);
					System.out.println(query);
				
					if(!((Table)entity).search(searchData,query))
						return;
				
				Application.getInstance().getInfoFrame().getInfoSplitPanel().getInfoTabbedPane().getTab(entity).getInfoTablePanel().getJTable().setModel(new InfoTableModel(entity.getData(),entity.getAttributes().toArray()));
				dispose();
						
				}
				else {
					dispose();
				}
			}
		});
		search.setSize(80, 30);
		Box box = Box.createHorizontalBox();
		box.add(search);
		listPane.add(box);
		listPane.add(Box.createRigidArea(new Dimension(0, 10)));
		listPane.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
		mainPanel.add(listPane);
		add(mainPanel);
		// pack();
		setSize(width, height);
		setVisible(true);
	}
}

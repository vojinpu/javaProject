package view.dialogs;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.Dialog.ModalityType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import app.Application;
import model.Attribute;
import model.Entity;
import model.Table;
import view.InfoTable;
import view.InfoTableModel;
import view.InfoTablePanel;

public class DBAddDialog extends JDialog{
	ArrayList<JTextField> mLFields = new ArrayList<>();

	public DBAddDialog(Entity entity,InfoTablePanel table) {
		setTitle(entity.getName());

		JPanel mainPanel = new JPanel(new BorderLayout());
		
		setModalityType(ModalityType.APPLICATION_MODAL);
		

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
			box.add(Box.createRigidArea(new Dimension(5, 0)));
			
			JLabel maxLen = new JLabel("Max Dužina: " + attribute.getLength());
			box.add(maxLen);
			JLabel jLabel = new JLabel(" Data type: " + attribute.getDataType());
			box.add(jLabel);

			if (attribute.isPK())
				textField.setBackground(Color.LIGHT_GRAY);
			else if (attribute.isNotNULL()) {
				textField.setBackground(Color.CYAN);
			}

				mLFields.add(textField);
				listPane.add(box);
				listPane.add(Box.createRigidArea(new Dimension(0, 5)));
			
		}
		listPane.add(Box.createRigidArea(new Dimension(0, 5)));
		JButton search = new JButton("Dodaj");
		search.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				ArrayList<String> queryData = new ArrayList<>();
				boolean empty = true;
				for (int i = 0; i < mLFields.size(); i++) {
					if (mLFields.get(i).getText().length() > 0) {
						queryData.add(mLFields.get(i).getText());
						empty = false;
					}
					else queryData.add(null);
				}
				if(!empty){
				
					
				if(!((Table)entity).add(queryData))
					return;
				
				
				((Table)entity).fetch();
				table.getJTable().setModel(new InfoTableModel(entity.getData(),entity.getAttributes().toArray()));
				InfoTable infoTable = table.getJTable();
				infoTable.setRowSelectionAllowed(true);
				infoTable.clearSelection();
				for(int i = 0;i < infoTable.getModel().getRowCount();i++){
					boolean same = true;
					for(int j = 0;j<infoTable.getModel().getColumnCount();j++){
						if(!infoTable.getModel().getValueAt(i, j).equals(queryData.get(j))){
							same = false;
						}
					}
					if(same){infoTable.setRowSelectionInterval(i, i);
					infoTable.scrollRectToVisible(new Rectangle(infoTable.getCellRect(i, 0, true)));
					}
					
				}
				
				
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

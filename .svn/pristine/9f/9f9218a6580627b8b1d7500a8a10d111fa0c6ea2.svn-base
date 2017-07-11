package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import app.ControllerManager;
import app.controllers.TableActionListener;
import app.controllers.TableHeatherActionListener;
import model.Entity;
import model.SekFile;
import model.Table;

public class InfoTablePanel extends JPanel {
	private String name;
	private InfoTable mJTable;
	private JLabel mNumofFetchLabel;
	private Entity entity;
	private InfoTabbedPane tabbedPane; //NAVIGABLE PARENT!!!!!! izmeni u umlu
	//ovde mora postojati instanca  tabbedpane koji se otvara u slucaju baza podataka i indeks sekvencijalnih datoteka
	//main i sub tabbed pane!!
	//TODO !!!!!
	private JTextField mBlockTextField;
	
	//Ovde bi trebalo registrovati i listener u konstruktoru?
	
	public InfoTablePanel(InfoTabbedPane tabbedPane, Entity entity) {
		setLayout(new BorderLayout());
		this.entity = entity;
		this.tabbedPane = tabbedPane;
		name = entity.getName();
		initView();
		mJTable.addMouseListener(new TableActionListener(this));
		mJTable.getTableHeader().addMouseListener(new TableHeatherActionListener(this));
	}
	
	public String getName(){
		return name;
	}
	
	private void initView(){
		initEditView();
		initTableView();
		initTableInfoView();
	}
	
	private void initEditView(){
		JPanel editPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel blockLabel = new JLabel("Veli\u010Dina bloka:");
		if(!tabbedPane.isChild())editPanel.add(blockLabel);
		mBlockTextField = new JTextField(3);
		mBlockTextField.setText(String.valueOf(entity.getBlockFactor()));
		if(!tabbedPane.isChild())editPanel.add(mBlockTextField);
		JButton applyBlockSizeButton = new JButton(ControllerManager.getInstance().getBlockFactorController());
		if(!tabbedPane.isChild())editPanel.add(applyBlockSizeButton);
		JButton fetchButton = new JButton(ControllerManager.getInstance().getFetchController());
		if(!tabbedPane.isChild())editPanel.add(fetchButton);
		//JLabel searchLabel = new JLabel("Pretraga:");
		//if(entity instanceof SekFile)editPanel.add(searchLabel);
		//JTextField search = new JTextField();
		//search.setPreferredSize(new Dimension(100, 20));
		//editPanel.add(search);
		JButton searchBtn = new JButton();
		if(!tabbedPane.isChild())searchBtn.setAction(ControllerManager.getInstance().getSearchController());
		else searchBtn.setAction(ControllerManager.getInstance().getmChildSearchController());
		if((entity instanceof SekFile || entity instanceof Table) && !tabbedPane.isChild())editPanel.add(searchBtn);
		JButton edit = new JButton();
		if(!tabbedPane.isChild())edit.setAction(ControllerManager.getInstance().getModifyController());
		else edit.setAction(ControllerManager.getInstance().getmChildModifyController());
		if(entity instanceof SekFile|| entity instanceof Table)editPanel.add(edit);
		JButton delete = new JButton();
		if(!tabbedPane.isChild())delete.setAction(ControllerManager.getInstance().getDeleteController());
		else delete.setAction(ControllerManager.getInstance().getmChildDeleteController());
		if(entity instanceof SekFile)editPanel.add(delete);
		JButton add = new JButton();
		if(!tabbedPane.isChild())add.setAction(ControllerManager.getInstance().getAddController());
		else add.setAction(ControllerManager.getInstance().getmChildAddController());
 		if(entity instanceof SekFile|| entity instanceof Table)editPanel.add(add);
		JLabel numberOfFetchTitleLabel = new JLabel("Broj pristupa fajlu na disku: ");
		if(!tabbedPane.isChild())editPanel.add(numberOfFetchTitleLabel);
		mNumofFetchLabel = new JLabel(String.valueOf(entity.getNumOfFetch()));
		if(!tabbedPane.isChild())editPanel.add(mNumofFetchLabel);
		JButton mergeButton = new JButton();
		if(!tabbedPane.isChild())mergeButton.setAction(ControllerManager.getInstance().getmMergeController());
		else mergeButton.setAction(ControllerManager.getInstance().getmChildMergeController());
		if(entity instanceof SekFile)editPanel.add(mergeButton);
		add(BorderLayout.NORTH,editPanel);
		if(entity instanceof SekFile && !tabbedPane.isChild()){
		ControllerManager.getInstance().getMakeIndFileController().setModel((SekFile)entity);
		JButton makeIndFile = new JButton(ControllerManager.getInstance().getMakeIndFileController());
		editPanel.add(makeIndFile);
		}
		
		
	}
	
	private void initTableView(){
		JPanel tablePanel = new JPanel(new BorderLayout());
		tablePanel.setBorder(new EmptyBorder(2, 2, 2, 2));
		if(!entity.isChild())mJTable = new InfoTable(new InfoTableModel(entity.getData(), entity.getAttributes().toArray()));
		else mJTable = new InfoTable(new InfoTableModel());
		tablePanel.add(BorderLayout.NORTH,mJTable.getTableHeader());
		tablePanel.add(BorderLayout.CENTER,mJTable);
		JScrollPane scrollPane = new JScrollPane(tablePanel);
		add(BorderLayout.CENTER,scrollPane);
	}
	
	private void initTableInfoView(){
		JPanel infoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		String itemNo = (entity.getAttributes() == null) ? "0" : String.valueOf(entity.getAttributes().size());
		JLabel infoText = new JLabel("Otvoren je entitet "+entity.getName()+" koji ima "+itemNo+" atribut(a).");
		infoPanel.add(infoText);
		add(BorderLayout.SOUTH,infoPanel);
	}

	public Entity getEntity() {
		return entity;
	}

	public InfoTable getJTable() {
		return mJTable;
	}

	public void setJTable(InfoTable mJTable) {
		this.mJTable = mJTable;
	}

	public JTextField getBlockTextField() {
		return mBlockTextField;
	}

	public JLabel getNumofFetchLabel() {
		return mNumofFetchLabel;
	}
	
	public InfoTabbedPane getTabbedPane() {
		return tabbedPane;
	}

	public ArrayList <String> getDataAtSelectedColums(){
		
		ArrayList <String> rezults = new ArrayList<>();
		
		
		
		for(int i = 0; i < mJTable.getSelectedRowCount(); i++){
			
			int rows[] = mJTable.getSelectedRows();
			
			String rezult = "";
			
			for(int j = 0; j < mJTable.getColumnCount(); j++)
				rezult += mJTable.getValueAt(rows[i], j).toString();
			
			rezults.add(rezult);
			
		}
		
		
		
		return rezults;
		
		
		
		
		
	}
}

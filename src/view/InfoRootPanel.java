package view;

import java.awt.BorderLayout;
import java.io.IOException;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultTreeModel;
import javax.xml.soap.Node;

import app.Main;
import model.AbstractFile;
import model.Entity;
import model.ISekFile;
import model.SekFile;
import model.Table;
import model.tree.InfoKeyElement;
import model.tree.InfoNode;
import model.tree.InfoNodeElement;
import model.tree.InfoTreeCellRenderer;

public class InfoRootPanel extends JPanel implements TreeSelectionListener{
	private Entity entity;
	private InfoTablePanel mInfoTablePanel;
	private InfoTabbedPane childInfoTabbedPane;
	private InfoSekTree mIndexTree;
	
	
	public InfoRootPanel(Entity entity, InfoTabbedPane infoTabbedPane){
		this.entity = entity;
		setLayout(new BorderLayout());
		
		mInfoTablePanel = new InfoTablePanel(infoTabbedPane, entity);		
		if(entity instanceof SekFile || entity instanceof Table){
			childInfoTabbedPane = new InfoTabbedPane();
			childInfoTabbedPane.setChild(true);
			InfoTableSplitPanel infoTableSplitPanel = new InfoTableSplitPanel(mInfoTablePanel,childInfoTabbedPane);
			add(infoTableSplitPanel, BorderLayout.CENTER);
		} else {
			add(mInfoTablePanel, BorderLayout.CENTER);
		}
		if(entity instanceof ISekFile){
			ISekFile iSekFile = (ISekFile) entity;
			DefaultTreeModel treeModel=new DefaultTreeModel(iSekFile.getInfViewTree().getRootElement());
			mIndexTree = new InfoSekTree(treeModel);
			mIndexTree.addTreeSelectionListener(this);
			mIndexTree.setRowHeight(40);
			mIndexTree.setCellRenderer(new InfoTreeCellRenderer());
			JScrollPane scTree=new JScrollPane(mIndexTree);
			add(scTree,BorderLayout.WEST);
			
			
		}
	}
	public Entity getEntity(){
		return entity;
	}
	
	public InfoTablePanel getInfoTablePanel(){
		return mInfoTablePanel;
	}
	public InfoTabbedPane getChildTabbedPane(){
		return childInfoTabbedPane;
	}
	@Override
	public void valueChanged(TreeSelectionEvent e) {
		
		InfoNode node = (InfoNode) e.getPath().getLastPathComponent();
		if (node.getChildCount() == 0 && !node.getData().isEmpty()) {
			InfoNodeElement element = node.getData().get(0);
			((AbstractFile)entity).setFilePointer(element.getBlockAddress() * ((AbstractFile)entity).getRecordSize());
			System.out.println(((AbstractFile)entity).getFilePointer());
			((AbstractFile)entity).fetch();
			mInfoTablePanel.getJTable().setModel(new InfoTableModel(((AbstractFile)entity).getData(), ((AbstractFile)entity).getAttributes().toArray()));
			mInfoTablePanel.getNumofFetchLabel().setText(String.valueOf(entity.getNumOfFetch()));
		}
	}
}

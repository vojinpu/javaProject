package view;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import app.ControllerManager;
import app.controllers.TreeActionListener;
import app.Application;

public class InfoTreePanel extends JPanel {

	private JTree mJTree;

	public InfoTreePanel() {
		// setPreferredSize(new Dimension(500,500));
		// setResizeWeight(1.0);
		setLayout(new BorderLayout());

		mJTree = new JTree();
		reload();
		mJTree.addMouseListener(new TreeActionListener(mJTree));
		
		mJTree.setSize(getPreferredSize());
		add(new JScrollPane(mJTree));

	}

	public JTree getjTree() {

		return mJTree;
	}
	
	public void reload() {
		
		DefaultMutableTreeNode rootNode = ControllerManager.getInstance().getFillTreeController().fillTree(Application.getInstance().getRepository());
		DefaultTreeModel treeModel = new DefaultTreeModel(rootNode);
		mJTree.setModel(treeModel);
		
		mJTree.setCellRenderer(new view.TreeCellRenderer());
		
	}
}

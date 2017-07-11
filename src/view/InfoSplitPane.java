package view;

import javax.swing.JSplitPane;

public class InfoSplitPane extends JSplitPane {
	
	public InfoSplitPane() {
		super(JSplitPane.HORIZONTAL_SPLIT, new InfoTreePanel(), new InfoTabbedPane());
		setDividerLocation(200);
	}
	public InfoTreePanel getInfoTree(){
		return (InfoTreePanel)getLeftComponent();
	}
	
	public InfoTabbedPane getInfoTabbedPane(){
		return (InfoTabbedPane)getRightComponent();
	}
	
	
}

package view;

import javax.swing.JPanel;
import javax.swing.JSplitPane;

public class InfoTableSplitPanel extends JSplitPane{
	public InfoTableSplitPanel(InfoTablePanel infoTablePanel,InfoTabbedPane infoTabbedPane) {
		super(JSplitPane.VERTICAL_SPLIT, infoTablePanel,infoTabbedPane);
		
		setDividerLocation(340);
	}
	public InfoTablePanel getParrentPanel() {
		return (InfoTablePanel)getTopComponent();
	}
	public InfoTabbedPane getChildTabbedPane() {
		return (InfoTabbedPane)getBottomComponent();
	}
	
}

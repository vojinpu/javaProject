package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;

import javax.swing.JFrame;

import app.ControllerManager;
import app.Application;
import enums.UserType;
import listeners.FrameAdapter;
import util.Util;

public class InfoFrame extends JFrame{

	public static int WIDTH = 1366;
	public static int HEIGHT = 768;
	
	private InfoSplitPane mInfoSplitPane;
	
	public InfoFrame() {
		//System.out.println("InfoFrame - zapocet konstruktor");
		setSize(new Dimension(WIDTH, HEIGHT));
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(new FrameAdapter());
		setTitle("InfoViewer");
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());
		setIconImage(Util.loadIcon(getClass(), "database64.png").getImage());
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		//System.out.println("InfoFrame - pre setVisible");
		//initUI();
		setVisible(true);
		//System.out.println("InfoFrame - posle setVisible");
		
	}
	
	public void initUI() {
		
		if (Application.getInstance().getCurrentUser().getUserType().equals(UserType.USER)){
			initUserView();
		} else {
			initAdminView();
		}
		
		revalidate();
		repaint();
	}
	
	private void initUserView() {
		
		
		ControllerManager.getInstance().getParseController().actionPerformed(new ActionEvent(this, 0, "app"));
		
		initUserMenuBar();
		
		mInfoSplitPane = new InfoSplitPane();
		getContentPane().add(mInfoSplitPane);
	}
	
	private void initAdminView() {
		
		initAdminMenuBar();
		
		getContentPane().add(new AdminPanel());
	}
	
	private void initUserMenuBar() {
		UserInfoMenuBar menuBar = new UserInfoMenuBar();
		setJMenuBar(menuBar);
	}
	
	private void initAdminMenuBar() {
		AdminInfoMenuBar menuBar = new AdminInfoMenuBar();
		setJMenuBar(menuBar);
	}

	public void setInfoSplitPanel(InfoSplitPane mMainViewPanel) {
		this.mInfoSplitPane = mMainViewPanel;
	}
	
	public InfoSplitPane getInfoSplitPanel() {
		return mInfoSplitPane;
	}
}

package view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Panel;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.sun.mail.handlers.image_gif;

import app.ControllerManager;
import model.Entity;
import model.ISekFile;

public class InfoTabbedPane extends JTabbedPane {
	private boolean isChild = false;
	private InfoTabbedPane childInfoTabbedPane;
	private InfoRootPanel mInfoRootPanel;

	public InfoTabbedPane() {

		/*
		 * Listener koji na promenu taba setuje u odgovarajuce data kontrolere
		 * aktivni model i view
		 */
		addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				InfoTabbedPane tabbedPane = ((InfoTabbedPane) e.getSource());
				// prvo getujes split panel iz tabbed pane-a, pa onda iz gornjeg
				// getujes infotable panel
				InfoTablePanel panel;
				if (!tabbedPane.isChild) {
					mInfoRootPanel = (InfoRootPanel) tabbedPane.getSelectedComponent();
					if (mInfoRootPanel != null)
						panel = mInfoRootPanel.getInfoTablePanel();
					else
						panel = null;
				} else{
					panel = (InfoTablePanel) tabbedPane.getSelectedComponent();
				}
				// dobija se null!!!!! sto je i odlicno
				System.out.println(
						"U DATA Controllere setovan model " + ((panel == null) ? "NULL" : panel.getEntity().getName()));
				if (!isChild) {
					ControllerManager.getInstance().updateDataControllers((panel == null) ? null : panel.getEntity(),
							panel);
				} else {
					ControllerManager.getInstance()
							.updateChildDataControllers((panel == null) ? null : panel.getEntity(), panel);
					if(panel!=null)System.out.println("set child cotrollers for entity " + panel.getEntity());
				}

			}
		});
	}

	public void openTab(Entity entity) {
		// TODO
		// provara kog je tipa entitet, ako je ser ili sek samo jedan panel
		// ako je db ili isek, dva panela
		boolean exists = false;
		for (int i = 0; i < getTabCount(); i++) {
			InfoTablePanel panel;
			if (!isChild) {
				panel = ((InfoRootPanel) getComponentAt(i)).getInfoTablePanel();
			} else {
				panel = (InfoTablePanel) getComponentAt(i);
			}
			if (panel.getEntity().equals(entity)) {
				setSelectedIndex(i);
				exists = true;
				return;
			}
		}
		if (!exists) {
			if (!isChild) {
				if (entity instanceof ISekFile && !((ISekFile) entity).openTree()) {
					JOptionPane.showMessageDialog(null, "Morate prvo napraviti IND file od SEK!!!",
							"Nema pravog .tree file!", JOptionPane.ERROR_MESSAGE);
				} else {
					InfoRootPanel infoTableParentTab = new InfoRootPanel(entity, this);
					add(entity.getName(), infoTableParentTab);
					setTabComponentAt(indexOfComponent(infoTableParentTab),
							new InfoTabbedTabPanel(this, infoTableParentTab.getInfoTablePanel()));
					setSelectedComponent(infoTableParentTab);
				}
			} else {
				InfoTablePanel tabelPane = new InfoTablePanel(this, entity);
				add(tabelPane.getName(), tabelPane);
				// setTabComponentAt(indexOfComponent(tabelPane), new
				// InfoTabbedTabPanel(this, tabelPane));
				setSelectedComponent(tabelPane);
			}
		}
	}

	public InfoRootPanel getTab(Entity entity) {
		for (int i = 0; i < getTabCount(); i++) {
			InfoTablePanel panel;
			if (!isChild) {
				panel = ((InfoRootPanel) getComponentAt(i)).getInfoTablePanel();
			} else {
				return null;
			}
			if (panel.getEntity().equals(entity)) {
				return (InfoRootPanel) getComponentAt(i);
			}
		}
		return null;
	}

	public InfoTablePanel getChildTab(Entity entity) {
		for (int i = 0; i < getTabCount(); i++) {
			InfoTablePanel panel;
			if (!isChild) {
				return null;
			} else {
				panel = (InfoTablePanel) getComponentAt(i);
			}
			if (panel.getEntity().equals(entity)) {
				return panel;
			}
		}
		return null;
	}

	public void removeTab(InfoTablePanel table) {
		if (!isChild) {
			for (int i = 0; i < getTabCount(); i++) {
				InfoTablePanel panel = ((InfoRootPanel) getComponentAt(i)).getInfoTablePanel();
				if (panel.equals(table)) {
					remove(i);
					break;
				}
			}
		} else
			remove(table);
	}

	public void setChild(boolean child) {
		isChild = child;
	}

	public boolean isChild() {
		return isChild;
	}

}

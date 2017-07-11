package app.controllers;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

import app.Application;
import model.Entity;
import model.ISekFile;
import model.Package;
import model.Relation;
import model.SekFile;
import model.SerFile;
import model.Table;

public class TreeActionListener implements MouseListener {
	private JTree jTree;

	public TreeActionListener(JTree jTree) {

		this.jTree = jTree;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int selRow = jTree.getRowForLocation(e.getX(), e.getY());
		TreePath selPath = jTree.getPathForLocation(e.getX(), e.getY());

		if (selRow == -1) {
			return;
		}

		if (e.getClickCount() != 2) {
			return;
		}

		DefaultMutableTreeNode node = (DefaultMutableTreeNode) jTree.getLastSelectedPathComponent();

		if (node == null) {
			return;
		}

		Object object = node.getUserObject();

		/* Ukoliko je kliknut paket ignorisemo */
		if (object instanceof Package) {
			return;
		}

		/* Sigurni smo da je objekat tipa entitet */
		Entity entity = (Entity) object;

		if (entity instanceof SerFile) {
			Application.getInstance().getInfoFrame().getInfoSplitPanel().getInfoTabbedPane().openTab(entity);
			return;
		}

		if (entity instanceof ISekFile) {

			/*
			 * Posto ne podrzavamo prikazivanje detalja za ISEK, ignorisemo
			 * prikazivanje ukoliko je u pitanju child
			 */
			if (entity.isChild()) {
				System.out.println("Kliknuo si na sek ili table child, ako je gore otvoren tvoj parent fokusiraj se");
				return;
			}

			Application.getInstance().getInfoFrame().getInfoSplitPanel().getInfoTabbedPane().openTab(entity);
			return;
		}

		/* Sigurni smo da je objekat tipa SekFile ili Table */

		//System.out.println((entity.getRelations() != null)?entity.getRelations().size():"Relations je null");

		/* Proveravamo da li je parent, ako jeste otvaramo ga u parent tabu */
		if (!entity.isChild()) {
			Application.getInstance().getInfoFrame().getInfoSplitPanel().getInfoTabbedPane().openTab(entity);

			for (Relation relation : entity.getRelations()) {
				if (!relation.getChild().getName().equals(entity.getName())) {
					Application.getInstance().getInfoFrame().getInfoSplitPanel().getInfoTabbedPane().getTab(entity)
							.getChildTabbedPane().openTab(relation.getChild());
				}
			}
		} else {
			System.out.println("Kliknuo si na sek ili table child, ako je gore otvoren tvoj parent fokusiraj se");
		}
		
		//bilo bi lepo da fokusiramo u parent tabu njegovog parenta ako je
		//otvoren


	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

}

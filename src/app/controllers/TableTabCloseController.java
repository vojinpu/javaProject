package app.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.AbstractFile;
import model.Entity;
import view.InfoTabbedPane;
import view.InfoTablePanel;

public class TableTabCloseController implements ActionListener{
	InfoTabbedPane tabbedPane;
	InfoTablePanel infotable;
	
	public TableTabCloseController(InfoTabbedPane tabbedPane,InfoTablePanel infotable) {

		this.tabbedPane = tabbedPane;
		this.infotable = infotable;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		Entity entity = infotable.getEntity();
		/* Ako je entitet klase fajl cistimo trenutni data i setujemo
		 * filepointer na pocetak fajla kako bi se pri sledecem pokretanju
		 * fetch radio od pocetka.
		 * Resetuju se i sve ostale vrednosti.
		 */
		if (entity instanceof AbstractFile){
			((AbstractFile)entity).setFilePointer(0);
			((AbstractFile)entity).setData(null);
			((AbstractFile)entity).setBlockFactor(20);
			((AbstractFile)entity).setNumOfFetch(0);
		}
		tabbedPane.removeTab(infotable);
		
	}

}

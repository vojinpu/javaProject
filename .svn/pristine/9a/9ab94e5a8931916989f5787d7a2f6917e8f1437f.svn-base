package app.controllers;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import app.Application;
import model.Entity;
import model.Table;
import view.InfoTableModel;
import view.InfoTablePanel;

public class TableHeatherActionListener extends MouseAdapter{
	private InfoTablePanel mTable;
	Application app;
	private boolean smer = false;

	public TableHeatherActionListener(InfoTablePanel infoTablePanel) {
		this.mTable = infoTablePanel;
		app = Application.getInstance();
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		 	Entity ent = mTable.getEntity();

		    int col =  mTable.getJTable().columnAtPoint(e.getPoint());
		    String name = mTable.getJTable().getColumnName(col);

		    String query = "Select * from " + ent.getName() + " ORDER BY " + name;

		    if (smer)
		     query += " DESC";

		    smer=!smer;
		    
		    ((Table)mTable.getEntity()).orderedFetch(query);
		   }

	}


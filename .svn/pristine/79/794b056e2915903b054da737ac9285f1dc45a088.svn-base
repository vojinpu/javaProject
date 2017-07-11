package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import app.Application;

public class Table extends Entity{
	
	String[][] data;
	
		public Table copy() {
		
		Table copy = new Table();
		
		copy.getAttributes().addAll(getAttributes());
		//ne dodajem mu relacije jer je child, sta ce mu?
		copy.setName(getName());
		copy.setIsChild(true);

		return copy;
	}
	
	
	@Override
	public void add() {
		// TODO Auto-generated method stub
		String query = "insert into " + getName() +"(";
		boolean start = true;
		for(Attribute attribute:getAttributes()){
			start = false;
			if(start) query += attribute.getName();
			else query+= ","+attribute.getName();
		}
		query+=") values (";
		start = true;
		for(Attribute attribute:getAttributes()){
			start = false;
			if(start) query += "?";
			else query+= ",?";
		}
		query+="?";
		
	}

	public boolean add(ArrayList<String> dataString){
		
		boolean status = true;
		
		String query = "insert into " + getName() +"(";
		boolean start = true;
		for(Attribute attribute:getAttributes()){
			if(start) query += attribute.getName();
			else query+= ","+attribute.getName();
			start = false;
		}
		query+=") values (";
		start = true;
		for(Attribute attribute:getAttributes()){
			if(start) query += "?";
			else query+= ",?";
			start = false;
		}
		query+=")";
		System.out.println("query " + query);
		try {
			PreparedStatement preparedStatement = Application.getInstance().geDbConnection().getConnection().prepareStatement(query);
			for(int i = 0;i< dataString.size();i++){
				preparedStatement.setObject(i+1, dataString.get(i));
			}
			preparedStatement.executeUpdate();
			preparedStatement.close();
		} catch (SQLException e) {
			status = false;
			// TODO Auto-generated catch block
			//System.out.println(e.getMessage().toString() + e.getCause().toString() + e.getSQLState());
			 JOptionPane.showMessageDialog(Application.getInstance().getInfoFrame(), e.toString());
		}
		
		return status;
		
	}
	
	public boolean edit(ArrayList<String> oldData,ArrayList<String> dataString){
		boolean status = true;
		
		String query = "update " + getName() +" set ";
		boolean start = true;
		for(Attribute attribute:getAttributes()){
			if(start) query += attribute.getName() + " = ?";
			else query+= ","+attribute.getName() + " = ?";
			start = false;
		}
		query+=" where ";
		start = true;
		int index = 0;
		for(Attribute attribute:getAttributes()){
			if(start) query += attribute.getName() + " = ?";
			else query+= " and "+attribute.getName() + " = ?";
			start = false;
			index++;
		}
		System.out.println("query " + query);
		try {
			PreparedStatement preparedStatement = Application.getInstance().geDbConnection().getConnection().prepareStatement(query);
			for(int i = 0;i< dataString.size();i++){
				preparedStatement.setObject(i+1, dataString.get(i));
			}
			for(int i = 0;i< dataString.size();i++){
				preparedStatement.setObject(i+1 + dataString.size(), oldData.get(i));
			}
			preparedStatement.executeUpdate();
			preparedStatement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			status = false;
			JOptionPane.showMessageDialog(Application.getInstance().getInfoFrame(), e.toString());
		}
		
		return status;
	}
	
	@Override
	public void delete() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void modify() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void search() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean fetch() {
		
		//broj slogova u tabeli :
		try{
		Statement statement =Application.getInstance().geDbConnection().getConnection().createStatement();
		ResultSet resultSet = statement.executeQuery("select count(*) from "+getName());
		int record_num = 0;
		if (resultSet.next()){
		
			record_num=resultSet.getInt(1);
		}
		resultSet.close();
		statement.close();
		
		
		//formiranje dela upita za SELECT sql nad tabelom
		String columnParams=null;
		for (int i=0;i<getAttributes().size();i++){
			if (columnParams==null){
				columnParams=getAttributes().get(i).getName();
			}else{
				columnParams+=","+getAttributes().get(i).getName();
			}
			
		}
		
		statement =Application.getInstance().geDbConnection().getConnection().createStatement();
		resultSet = statement.executeQuery("select "+columnParams+" from "+getName());
		
		
		data = new String[(int) record_num][];

		//u objektu ResultSet-a rs nalaze se svi podaci iz date tabele
		//iz result set-a se podaci čitaju i prebacuju u matricu data[][]
		int i=0;
		while (resultSet.next()){
			data[i]=new String [getAttributes().size()];
			for (int j=0;j<getAttributes().size();j++){
				data[i][j]=resultSet.getString(j+1);
			}
			i++;
		}
		resultSet.close();
		statement.close();
		System.out.println("broj slogova:"+(getName()));
		System.out.println("broj slogova:"+(i++));
		} catch (SQLException e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(Application.getInstance().getInfoFrame(), e.toString());
		}
		
		return true;
		// TODO Auto-generated method stub
		
	}
	
public boolean orderedFetch(String query) {
		
		//broj slogova u tabeli :
		try{
		Statement statement =Application.getInstance().geDbConnection().getConnection().createStatement();
		ResultSet resultSet = statement.executeQuery("select count(*) from "+getName());
		int record_num = 0;
		if (resultSet.next()){
		
			record_num=resultSet.getInt(1);
		}
		resultSet.close();
		statement.close();
		
		
		//formiranje dela upita za SELECT sql nad tabelom
		String columnParams=null;
		for (int i=0;i<getAttributes().size();i++){
			if (columnParams==null){
				columnParams=getAttributes().get(i).getName();
			}else{
				columnParams+=","+getAttributes().get(i).getName();
			}
			
		}
		
		statement =Application.getInstance().geDbConnection().getConnection().createStatement();
		resultSet = statement.executeQuery(query);
		
		
		data = new String[(int) record_num][];

		//u objektu ResultSet-a rs nalaze se svi podaci iz date tabele
		//iz result set-a se podaci čitaju i prebacuju u matricu data[][]
		int i=0;
		while (resultSet.next()){
			data[i]=new String [getAttributes().size()];
			for (int j=0;j<getAttributes().size();j++){
				data[i][j]=resultSet.getString(j+1);
			}
			i++;
		}
		resultSet.close();
		statement.close();
		System.out.println("broj slogova:"+(getName()));
		System.out.println("broj slogova:"+(i++));
		} catch (SQLException e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(Application.getInstance().getInfoFrame(), e.toString());
		}
		
		return true;
		// TODO Auto-generated method stub
		
	}
	
	public boolean getFilteredForParent(Entity parent, String[] queryData){
		String filterString = "select count(*) from " + getName() + " where ";
		int i = 0;
		int found = 0;
		for(Attribute parentAttribute : parent.getAttributes()){
			for(Attribute childAttribute: getAttributes()){
				if(parentAttribute.getName().equals(childAttribute.getName())){
					if(found==0)filterString += parentAttribute + " = ?";
					else {
						filterString += " and " + parentAttribute + " = ?";
					}
					found++;
				}
			}
			i++;
		}
		System.out.println();
		System.out.println(filterString);
		//broj slogova u tabeli :
		try{
		PreparedStatement statement =Application.getInstance().geDbConnection().getConnection().prepareStatement(filterString);
		i = 0;
		found = 1;
		for(Attribute parentAttribute : parent.getAttributes()){
			for(Attribute childAttribute: getAttributes()){
				if(parentAttribute.getName().equals(childAttribute.getName())){
					statement.setObject(found, queryData[i]);
					found++;
				}
			}
			i++;
		}
		ResultSet resultSet;
		resultSet = statement.executeQuery();
		int record_num = 0;
		if (resultSet.next()){
		
			record_num=resultSet.getInt(1);
		}
		resultSet.close();
		statement.close();
		System.out.println("record num = " + record_num );
		
		
		filterString = filterString.replace("count(*)", "*");
		statement =Application.getInstance().geDbConnection().getConnection().prepareStatement(filterString);
		i = 0;
		found = 1;
		for(Attribute parentAttribute : parent.getAttributes()){
			for(Attribute childAttribute: getAttributes()){
				if(parentAttribute.getName().equals(childAttribute.getName())){
					statement.setObject(found, queryData[i]);
					found++;
				}
			}
			i++;
		}
		resultSet = statement.executeQuery();
		
		data = new String[(int) record_num][];

		i = 0;
		while (resultSet.next()){
			data[i]=new String [getAttributes().size()];
			for (int j=0;j<getAttributes().size();j++){
				data[i][j]=resultSet.getString(j+1);
			}
			i++;
		}
		resultSet.close();
		statement.close();
		System.out.println("broj slogova:"+(getName()));
		System.out.println("broj slogova:"+(i++));
		} catch (SQLException e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(Application.getInstance().getInfoFrame(), e.toString());
		}
		
		return true;
		// TODO Auto-generated method stub
		
	}
	
	public boolean search(ArrayList<String> dataString,String query){
		
		boolean status = true;
		try {
			
			PreparedStatement preparedStatement = Application.getInstance().geDbConnection().getConnection().prepareStatement(query);
			for(int i = 0;i<dataString.size();i++){
				System.out.println(dataString.get(i).toString() + " " + i);
				preparedStatement.setObject(i+1, dataString.get(i));
			}
			System.out.println(preparedStatement + " ee");
			ResultSet resultSet = preparedStatement.executeQuery();
			int record_num = 0;
			if (resultSet.next()){
			
				record_num=resultSet.getInt(1);
			}
			resultSet.close();
			preparedStatement.close();
			
			query = query.replace("count(*)", "*");
			preparedStatement = Application.getInstance().geDbConnection().getConnection().prepareStatement(query);
			for(int i = 0;i<dataString.size();i++){
				preparedStatement.setObject(i+1, dataString.get(i));
			}
			resultSet = preparedStatement.executeQuery();
			data = new String[(int) record_num][];

			//u objektu ResultSet-a rs nalaze se svi podaci iz date tabele
			//iz result set-a se podaci čitaju i prebacuju u matricu data[][]
			int i=0;
			while (resultSet.next()){
				data[i]=new String [getAttributes().size()];
				for (int j=0;j<getAttributes().size();j++){
					data[i][j]=resultSet.getString(j+1);
				}
				i++;
			}
			resultSet.close();
			preparedStatement.close();
			System.out.println("broj slogova:"+(getName()));
			System.out.println("broj slogova:"+(i++));
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			status = false;
			JOptionPane.showMessageDialog(Application.getInstance().getInfoFrame(), e.toString());
		}
		
		return status;
	}

	@Override
	public String[][] getData() {
		// TODO Auto-generated method stub
		return data;
	}

	@Override
	public void setBlockFactor(int factor) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public long getBlockFactor() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long getNumOfFetch() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setNumOfFetch(long numOfFetch) {
		// TODO Auto-generated method stub
		
	}
	
	

	
	
}

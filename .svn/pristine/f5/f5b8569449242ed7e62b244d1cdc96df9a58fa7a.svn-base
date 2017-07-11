package model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.swing.JOptionPane;

import com.fasterxml.jackson.annotation.JsonProperty;

import model.tree.InfoTree;

public class ISekFile extends AbstractFile {

	@JsonProperty("overZone")
	private String mOverZonePath;
	@JsonProperty("tree")
	private String mTreePath;

	private InfoTree mTree;
	private boolean isCreated = false;
	private boolean mIsFirst = true;

	public ISekFile() {
		super();
		// System.out.println("ISEK");
	}

	/* Metoda koja pravi kopiju objekta, ne novu referencu,
	 * setuje atribute i putanju do fajla na disku.
	 * Relacije nisu potrebne jer pravimo objekat koji je child?
	 */
	public ISekFile copy() {

		ISekFile copy = new ISekFile();

		copy.getAttributes().addAll(getAttributes());
		// ne dodajem mu relacije jer je child, sta ce mu?
		copy.setPath(getPath());
		copy.setName(getName());
		copy.setIsChild(true);

		return copy;
	}

	@Override
	public void add() {
		// TODO Auto-generated method stub

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

	public String getOverZonePath() {
		return mOverZonePath;
	}

	public void setOverZonePath(String mOverZonePath) {
		this.mOverZonePath = mOverZonePath;
	}

	public String getTreePath() {
		return mTreePath;
	}

	public void setTreePath(String mTreePath) {
		this.mTreePath = mTreePath;
	}

	public boolean openTree() {

		ObjectInputStream ins = null;

		try {
			ins = new ObjectInputStream(new FileInputStream(mTreePath));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}

		try {
			mTree = (InfoTree) ins.readObject();
		} catch (ClassNotFoundException | IOException e) {
			// e.printStackTrace();
			return false;
		}
		return true;
	}

	public InfoTree getInfViewTree() {
		return mTree;
	}

	// public int getRecordLength() {
	// return mRecordLength;
	// }
	//
	// public void setRecordLength(int mRecordLength) {
	// this.mRecordLength = mRecordLength;
	// }
}

package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

import app.Application;

public class SerFile extends AbstractFile {

	public SerFile() {
		super();
		// System.out.println(getAttributes().size());
	}

	@Override
	public void add() {
			String line = Application.getInstance().getQuery();
			
		try {
			System.out.println(mPath);
			RandomAccessFile afile = new RandomAccessFile(mPath, "rw");

			afile.seek(afile.length());
			afile.writeBytes(line);
			afile.setLength(afile.length());
			afile.close();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("SERIJSKA");

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


	// public int getRecordLength() {
	// return mRecordLength;
	// }
	//
	// public void setRecordLength(int mRecordLength) {
	// this.mRecordLength = mRecordLength;
	// }

}

package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY)
@JsonSubTypes({
    @JsonSubTypes.Type(value = SerFile.class, name = "SerFile"),
    @JsonSubTypes.Type(value = SekFile.class, name = "SekFile"),
    @JsonSubTypes.Type(value = ISekFile.class, name = "ISekFile")})

public abstract class AbstractFile extends Entity{

	@JsonProperty("path")
	protected String mPath;
	
	protected String[][] mData;
	
	protected long mBlockFactor = 20;

	protected int mRecordSize = 0;

	protected int mBufferSize = 0;

	protected int mBlockNum = 0;

	protected long mRecordNum = 0;

	protected long mFilePointer = 0;

	protected long mFileSize = 0;
	
	protected long mNumOfFetch = 0;
	
	public AbstractFile() {
		super();
	}
	
	@Override
	public boolean fetch(){
		
		mNumOfFetch++;
		
		calculateRecordSize();
		
		//System.out.println("FETCH METODA FILEPOINTER JE NA "+mFilePointer);
		
		RandomAccessFile rafFile = calculateFileSize();
		
		//System.out.println("Napravio fajl i record size je "+mRecordSize);
		
		mRecordNum = (long) Math.ceil((mFileSize * 1.0000) / (mRecordSize * 1.0000));
		mBlockNum = (int) (mRecordNum / mBlockFactor) + 1;

		if (mFilePointer / mRecordSize + mBlockFactor > mRecordNum) {
			mBufferSize = (int) (mRecordNum - mFilePointer / mRecordSize) * mRecordSize;
		} else {
			mBufferSize = (int) (mRecordSize * mBlockFactor);
		}

		byte[] buffer = new byte[mBufferSize];
		mData = new String[(int) (mBufferSize / mRecordSize)][];

		try {
			rafFile.seek(mFilePointer);
			rafFile.read(buffer);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		String stringContent = new String(buffer);
		//System.out.println("IDEMO\n"+stringContent);

		for (int i = 0; i < mBufferSize / mRecordSize; i++) {
			String line = stringContent.substring(i * mRecordSize, i * mRecordSize + mRecordSize);
			mData[i] = new String[getAttributes().size()];
			int k = 0;
			for (int j = 0; j < getAttributes().size(); j++) {
				String field = line.substring(k, k + getAttributes().get(j).getLength());
				mData[i][j] = field;
				k += getAttributes().get(j).getLength();
			}
		}

		try {
		
			mFilePointer = rafFile.getFilePointer();
		
		
			
			
			
			rafFile.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		
		
		return true;
		
	}

	public String getPath() {
		return mPath;
	}

	public void setPath(String mPath) {
		this.mPath = mPath;
	}
	
	/* Funkcija koja racuna record size jer je u konstruktorima
	 * klasa fajlova mAttributes size uvek 0, sto je posledica
	 * nacina na koji Jackoson pravi objekte na osnovvu meta sheme.
	 * Poziva se trenutno samo u fetch() metodi implementiranoj
	 * u ovoj klasi, nasledjuju je sve klase fajlova
	 */
	
	public void calculateRecordSize(){
		if (mRecordSize == 0){
			//System.out.println("mRecordSize je 0, sad cu da ga izracunam");
			
			for (Attribute attr : getAttributes()) {
				//System.out.println("Attr name je "+attr.getName()+" a length je "+attr.getLength());
				mRecordSize+=attr.getLength();
			}
			mRecordSize = mRecordSize + 2;
		}
		//System.out.println("Vracam "+mRecordSize);
	}
	
	public void setData(String[][] mData) {
		this.mData = mData;
	}

	@Override
	public String[][] getData() {
		return mData;
	}
	
	@Override
	public void setBlockFactor(int factor){
		mBlockFactor = factor;
	}
	
	@Override
	public long getBlockFactor(){
		return mBlockFactor;
	}

	public long getFilePointer() {
		return mFilePointer;
	}

	public void setFilePointer(long mFilePointer) {
		this.mFilePointer = mFilePointer;
	}
	
	@Override
	public long getNumOfFetch(){
		return mNumOfFetch;
	}
	
	@Override
	public void setNumOfFetch(long numOfFetch){
		mNumOfFetch = numOfFetch;
	}
	
	/*Metoda koja racuna velicinu fajla i setuje
	 * je u mFileSize i pritom vraca napravljeni
	 * fajl */

	public RandomAccessFile calculateFileSize(){
		
		RandomAccessFile rafFile = null;
		try {
			rafFile = new RandomAccessFile(mPath, "r");
			mFileSize = rafFile.length();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return rafFile;
	}

	public int getRecordSize() {
		return mRecordSize;
	}
	
	
	
}

package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.RandomAccessFile;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.SynchronousQueue;

import javax.swing.JOptionPane;

import app.Application;

import java.util.Collections;
import java.util.Comparator;

import model.tree.InfoKeyElement;
import model.tree.InfoTree;
import model.tree.InfoNode;
import model.tree.InfoNodeElement;

public class SekFile extends AbstractFile {

	private int pkLen;
	private long mFileSearchPointer = 0;

	public SekFile() {
		super();
		// System.out.println("SEK");
	}
	
	/* Metoda koja pravi kopiju objekta, ne novu referencu,
	 * setuje atribute i putanju do fajla na disku.
	 * Relacije nisu potrebne jer pravimo objekat koji je child?
	 */
	public SekFile copy() {
		
		SekFile copy = new SekFile();
		
		copy.getAttributes().addAll(getAttributes());
		//ne dodajem mu relacije jer je child, sta ce mu?
		copy.setPath(getPath());
		copy.setName(getName());
		copy.setIsChild(true);

		return copy;
	}

	@Override
	public void add() {

		writeInExternalFile(".help");

	}

	@Override
	public void delete() {
		writeInExternalFile(".help");

	}

	@Override
	public void modify() {
		writeInExternalFile(".help");

	}

	public void search(String query, boolean fromCurrent, boolean stopOnFrist, boolean saveExternal) {

		calculateRecordSize();

		String[] mSearchData = new String[getAttributes().size()];

		// rezultat koji ce biti prikazan
		ArrayList<String[]> dataForFetch = new ArrayList<>();

		// Od query-a napravi niz stringova
		int k = 0;
		for (int j = 0; j < getAttributes().size(); j++) {
			String field = query.substring(k, k + getAttributes().get(j).getLength());
			mSearchData[j] = field;
			k += getAttributes().get(j).getLength();
		}

		boolean serachPrimary = searchPrimary(mSearchData);
		String mSearchDataPKString = createPrimary(mSearchData);

		System.out.println("Pretražuješ pk :" + serachPrimary);
		// PRVI USLOV - UKOLIKO NIJE IZABRA, PRETRAZI DADOTEKU OD POCETKA
		if (!fromCurrent)
			mFileSearchPointer = 0;

		RandomAccessFile rafFile = null;
		try {

			// fajl za privremeno cuvanje rekorda
			RandomAccessFile afileSearch = new RandomAccessFile(mPath + ".search", "rw");
			afileSearch.setLength(0);

			rafFile = new RandomAccessFile(mPath, "r");
			mFileSize = rafFile.length();

			while (mFileSearchPointer < rafFile.length()) {

				byte[] buffer = new byte[mRecordSize];
				String[] mAttributes = null;

				rafFile.seek(mFileSearchPointer);
				rafFile.read(buffer);

				String line = new String(buffer);
				mFileSearchPointer = rafFile.getFilePointer();

				// napravi od trenutnog niz stringova
				String mCurrentData[] = new String[getAttributes().size()];
				k = 0;
				for (int j = 0; j < getAttributes().size(); j++) {
					String field = line.substring(k, k + getAttributes().get(j).getLength());
					mCurrentData[j] = field;
					k += getAttributes().get(j).getLength();
				}

				// Pretraga po Primary Key-u
				if (serachPrimary) {

					// String currentPKString = createPrimary(mCurrentData);

					// int cmp = mSearchDataPKString.compareTo(currentPKString);

					int cmp = cmp(query, line);

					// System.out.println(cmp + "\n" + mSearchDataPKString +
					// "\n" + currentPKString);
					if (cmp == 0) {
						if (saveExternal) {

							afileSearch.seek(afileSearch.length());
							afileSearch.writeBytes(line);
							afileSearch.setLength(afileSearch.length());

						} else
							dataForFetch.add(mCurrentData);

						if (stopOnFrist)
							break;
					}

					if (cmp < 0)
						break;

				}

				// Ukoliko pretraga nije po Primary Key-u
				// vidi da li se podaci podudaraju
				else if (isItInSearchRange(mCurrentData, mSearchData)) {
					if (saveExternal) {

						afileSearch.seek(afileSearch.length());
						afileSearch.writeBytes(line);
						afileSearch.setLength(afileSearch.length());

					} else
						dataForFetch.add(mCurrentData);

					if (stopOnFrist)
						break;

				}

			}
			afileSearch.close();
			rafFile.close();

			// iscitaj iz externog fajla
			if (saveExternal) {

				readFromSearchFile();

			}

			else {

				int n = dataForFetch.size();
				int m = getAttributes().size();

				mData = new String[n][m];

				for (int i = 0; i < n; i++)
					for (int j = 0; j < m; j++) {
						// System.out.println(dataForFetch.get(i)[j]);
						mData[i][j] = dataForFetch.get(i)[j];

					}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private int cmpPK(String[] mSearchData, String[] mCurrentData) {

		for (int i = 0; i < getAttributes().size(); i++) {

			// ukoliko nije PK idi dalje
			if (!getAttributes().get(i).isPK())
				continue;

			// ako su int proveri ovako
			if (isNumber(mSearchData[i]) && isNumber(mCurrentData[i])) {
				int brSerach = Integer.parseInt(mSearchData[i].trim());
				int brCurrent = Integer.parseInt(mCurrentData[i].trim());

				if (brSerach > brCurrent)
					return 1;
				if (brSerach == brCurrent)
					continue;

				return -1;

			}

			else {

				if (mSearchData[i].trim().compareTo(mCurrentData[i].trim()) == 0)
					continue;

				else
					return mSearchData[i].trim().compareTo(mCurrentData[i].trim());

			}

		}

		return 0;
	}

	boolean isNumber(String a) {

		
		if(a.trim().length() == 0)
			return false;
		
		
		for (int i = 0; i < a.trim().length(); i++)
			if (a.charAt(i) < '0' || a.charAt(i) > '9')
				return false;

		
		return true;

	}

	private void readFromSearchFile() {

		RandomAccessFile rafFile = null;
		try {

			long mFileSearchPointer = 0;
			rafFile = new RandomAccessFile(mPath + ".search", "r");
			mFileSize = rafFile.length();
			mData = new String[(int) (rafFile.length() / mRecordSize)][];
			byte[] buffer = new byte[mBufferSize];

			int i = 0;
			while (mFileSearchPointer < rafFile.length()) {

				rafFile.seek(mFileSearchPointer);
				rafFile.read(buffer);

				String line = new String(buffer);

				mData[i] = new String[getAttributes().size()];
				int k = 0;
				for (int j = 0; j < getAttributes().size(); j++) {
					String field = line.substring(k, k + getAttributes().get(j).getLength());
					System.out.println(field);
					mData[i][j] = field;
					k += getAttributes().get(j).getLength();
				}

				i++;
				mFileSearchPointer = rafFile.getFilePointer();

			}

			rafFile.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void writeInExternalFile(String extension) {

		String line = Application.getInstance().getQuery();
		//line+='\r';

		try {
			//System.out.println(mPath);
			RandomAccessFile afile = new RandomAccessFile(mPath + extension, "rw");

			afile.seek(afile.length());
			afile.writeBytes(line);
			afile.setLength(afile.length());
			afile.close();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private boolean isItInSearchRange(String[] mCurrentData, String[] mSearchData) {

		for (int g = 0; g < getAttributes().size(); g++) {

			if (!mSearchData[g].trim().isEmpty()) {

				if (mCurrentData[g].trim().toLowerCase().contains(mSearchData[g].trim().toLowerCase()))
					return true;

			}

			/*
			 * if (mSearchData[g].indexOf(' ') == -1) { if
			 * (mSearchData[g].charAt(0) != ' ' &&
			 * mCurrentData[g].toLowerCase().contains(mSearchData[g].toLowerCase
			 * ())) return true; }
			 * 
			 * else {
			 * 
			 * if (mSearchData[g].charAt(0) != ' ' &&
			 * mCurrentData[g].toLowerCase()
			 * .contains(mSearchData[g].substring(0, mSearchData[g].indexOf('
			 * ')).toLowerCase())) return true;
			 * 
			 * }
			 */

		}

		return false;

	}

	// Napravi string od primary Key-eva
	private String createPrimary(String[] data) {

		int n = getAttributes().size();

		String rezult = "";
		for (int i = 0; i < n; i++) {

			if (getAttributes().get(i).isPK() && !data[i].trim().isEmpty())
				rezult += data[i];

		}

		return rezult;

	}

	// Proverava da li je korisnik uneo search iskljucivo po primray Key-u
	private boolean searchPrimary(String[] data) {

		int n = getAttributes().size();

		for (int i = 0; i < n; i++) {
			if (getAttributes().get(i).isPK() && !(data[i].trim().isEmpty()))
				return true;

		}

		return false;

	}

	@Override
	public void search() {
		// TODO Auto-generated method stub

	}

	public void makeIndFile() throws IOException {
		makeTree();
		makeOverZoneFile();
	}

	private void makeOverZoneFile() {
		String path = getPath();
		path = path.replaceAll(".stxt", ".over");
		path = path.replaceAll("Sekvencijalne datoteke", "Indeks - sekvencijalne datoteke");
		File overFile = new File(path);
		if (!overFile.exists()) {
			try {
				overFile.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private void makeTree() throws IOException {
		// Mozda proveriti sa if da li je mFileSize ==0 i mRecordSize == 0?
		calculateFileSize();
		calculateRecordSize();

		mFilePointer = 0;
		List<InfoNode> listNodes = new ArrayList<>();

		InfoTree tree = null;
		long tFilePointer = 0;

		while (mFilePointer < mFileSize) {
			tFilePointer = mFilePointer;
			fetch();
			List<InfoKeyElement> listKeyElements = new ArrayList<>();

			List<InfoNodeElement> listNodeElements = new ArrayList<>();
			for (int i = 0; i < getAttributes().size(); i++) {
				if (getAttributes().get(i).isPK()) {
					InfoKeyElement keyElement = new InfoKeyElement(getAttributes().get(i).getDataType(), mData[0][i]);
					listKeyElements.add(keyElement);
				}

			}
			InfoNodeElement nodeElement = new InfoNodeElement((int) (tFilePointer / mRecordSize), listKeyElements);

			listNodeElements.add(nodeElement);
			InfoNode node = new InfoNode(listNodeElements);
			tFilePointer = mFilePointer;
			fetch();
			listKeyElements = new ArrayList<>();

			for (int i = 0; i < getAttributes().size(); i++) {
				if (getAttributes().get(i).isPK()) {
					InfoKeyElement keyElement = new InfoKeyElement(getAttributes().get(i).getDataType(), mData[0][i]);
					listKeyElements.add(keyElement);
				}

			}

			nodeElement = new InfoNodeElement((int) (tFilePointer / mRecordSize), listKeyElements);
			listNodeElements.add(nodeElement);
			node = new InfoNode(listNodeElements);
			listNodes.add(node);
		}

		while (listNodes.size() > 1) {
			Queue<InfoNode> queue = new LinkedList<>();
			if (listNodes.size() % 2 != 0) {
				InfoNode emptyNode = new InfoNode();
				listNodes.add(emptyNode);
			}

			for (int i = 0; i < listNodes.size(); i++) {
				queue.add(listNodes.get(i));
			}
			listNodes.clear();

			while (!queue.isEmpty()) {
				InfoNode newNode = new InfoNode();
				newNode.addChild(queue.poll());
				newNode.addChild(queue.poll());

				List<InfoNodeElement> listNodeElements = new ArrayList<>();
				InfoNodeElement firstElement = new InfoNodeElement(0,
						((InfoNode) newNode.getChildAt(0)).getData().get(0).getKeyValue());
				InfoNodeElement secondElement = null;

				if (((InfoNode) newNode.getChildAt(1)).getData().size() == 0) {
					// List<InfoKeyElement> keys = new ArrayList<>();
					// secondElement = new InfoNodeElement(1, keys);

				} else {
					secondElement = new InfoNodeElement(1,
							((InfoNode) newNode.getChildAt(1)).getData().get(0).getKeyValue());

				}
				listNodeElements.add(firstElement);
				if (secondElement != null)
					listNodeElements.add(secondElement);
				newNode.setData(listNodeElements);

				listNodes.add(newNode);
			}

		}

		InfoNode root = listNodes.get(0);
		tree = new InfoTree();
		tree.setRootElement(root);

		mFilePointer = 0;
		ObjectOutputStream os;
		String path = getPath();
		path = path.replaceAll(".stxt", ".tree");
		path = path.replaceAll("Sekvencijalne datoteke", "Indeks - sekvencijalne datoteke");
		try {
			os = new ObjectOutputStream(new FileOutputStream(path));
			os.writeObject(tree);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		//System.out.println("napravioo" + path);

	}

	public void mergeData() {

		ArrayList<String> records = new ArrayList<>();

		calculateRecordSize();

		// UCITAVANJE IZ FAJLA
		RandomAccessFile rafFile = null;
		try {

			long mFileSearchPointer = 0;
			rafFile = new RandomAccessFile(mPath + ".help", "rw");

			
			int i = 0;
			while (mFileSearchPointer < rafFile.length()) {
				//aA1SMRT
				//aA1SMRT
				byte[] buffer = new byte[mRecordSize + 1];
				byte[] buffer2 = new byte[mRecordSize];

				rafFile.seek(mFileSearchPointer);
				rafFile.read(buffer);
				String line = new String(buffer);

				rafFile.seek(mFileSearchPointer + mRecordSize + 1);

				String bla = "";

				if (line.charAt(0) == 'E') {
					rafFile.read(buffer2);
					bla = new String(buffer2);
				}

				line = line + bla;
				// posebno smo ucitali edit, posebno sve ostale. Na prvom mestu
				// se nalazi simbol

				records.add(line);

				mFileSearchPointer = rafFile.getFilePointer();

			}

			//System.out.println("obrisan pomocni fajl");
			rafFile.setLength(0);
			rafFile.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		// SORTIRANJE PO PK
		ArrayList<String> recordsSorted = new ArrayList<>();

		for (String record : records) {

			int i = 0;

			while (i < recordsSorted.size() && cmp(record.substring(1), recordsSorted.get(i).substring(1)) >= 0)
				i++;

			recordsSorted.add(i, record);


		}
		

		records = recordsSorted;
		//System.out.println("Pre sorta");
		//for(int i = 0; i < records.size(); i++)
			//System.out.println(records.get(i));

		//System.out.println("SORTIRALI");

		
		try {

			// sada ponovo imamo \r
			//mRecordSize++;

			long mOldFilePointer = 0;
			RandomAccessFile rafOldFile = new RandomAccessFile(mPath, "rw");

			long mNewFilePointer = 0;
			RandomAccessFile rafNewFile = new RandomAccessFile(mPath + ".NEW", "rw");

			long mErrorFilePointer = 0;
			RandomAccessFile rafErrorFile = new RandomAccessFile(mPath + ".error", "rw");

			rafErrorFile.setLength(0);
			rafNewFile.setLength(0);

			byte[] buffer = new byte[mRecordSize];
			
			//System.out.println("Record je " + mRecordSize);

			int i = 0;

			String line = "";

			int j = 0;
			int cmp;
			while (mOldFilePointer <= rafOldFile.length() || i < records.size()) {
				
				//Thread.sleep(20);
				
				//System.out.println("Broj ulazaka: " + j++);
				//System.out.println(mOldFilePointer + " .. " + rafOldFile.length() + " .. " + i);
				
				if (mOldFilePointer == 0) {
					rafOldFile.seek(mOldFilePointer);
					rafOldFile.read(buffer);
					line = new String(buffer);
					mOldFilePointer = rafOldFile.getFilePointer();
					
					//System.out.println(mOldFilePointer+" Line: " + line);
				}
				
				
				if (i >= records.size())
					cmp = -1;

				else if (mOldFilePointer > rafOldFile.length())
					cmp = 1;
				else
					cmp = cmp(line, records.get(i).substring(1));
				
				//System.out.println("cmp" + cmp);
				
				if(mOldFilePointer == rafOldFile.length())
					mOldFilePointer += 5;
				
				if(cmp == 0)
					System.out.println("DEO"+line + "\n" + records.get(i));
				
				//Ukoliko smo iscitali poslednji i upisali ga

				/*
				if (mOldFilePointer == 0) {

					rafOldFile.seek(mOldFilePointer);
					rafOldFile.read(buffer);
					line = new String(buffer);
					mOldFilePointer = rafOldFile.getFilePointer();
					

					if(!line.isEmpty() && i < records.size())
						cmp = compare(line, records.get(i));
					
					else if(i >= records.size())
						cmp = -1;
					else
						cmp = 1;

				}
				
				
				//else 
	*/			
				
				//System.out.println(cmp);
				
				

				
				// System.out.println(cmp+"\n" + line + "\n" +
				// records.get(i)+"\n\n");
				// Ukoliko je fajl manji od pomocnog

				if (cmp < 0) {

					// zapisi stare i pomeri za jedan
					rafNewFile.seek(rafNewFile.length());
					rafNewFile.writeBytes(line);
					rafNewFile.setLength(rafNewFile.length());

					rafOldFile.seek(mOldFilePointer);
					rafOldFile.read(buffer);
					line = new String(buffer);
					mOldFilePointer = rafOldFile.getFilePointer();

				}

				// ukoliko su jednaki
				else if (cmp == 0) {

					System.out.println("0");
					if (records.get(i).charAt(0) == 'E') {
						// upisi novi, oba pomeri

						rafNewFile.seek(rafNewFile.length());
						// iseci tacno njega
						rafNewFile.writeBytes(records.get(i).substring(mRecordSize + 1));
						rafNewFile.setLength(rafNewFile.length());

						i++;

						rafOldFile.seek(mOldFilePointer);
						rafOldFile.read(buffer);
						line = new String(buffer);
						mOldFilePointer = rafOldFile.getFilePointer();

					}

					else if (records.get(i).charAt(0) == 'A') {

						
						
						// upisi u greske, uvecaj i
						rafErrorFile.seek(rafErrorFile.length());
						rafErrorFile.writeBytes(records.get(i));
						rafErrorFile.setLength(rafErrorFile.length());

						i++;

					}

					else if (records.get(i).charAt(0) == 'D') {

						// oba pomeri za jedan
						i++;

						rafOldFile.seek(mOldFilePointer);
						rafOldFile.read(buffer);
						line = new String(buffer);
						mOldFilePointer = rafOldFile.getFilePointer();

					}

				}

				
				else {
					//System.out.println(records.get(i));
					if (records.get(i).charAt(0) == 'A') {
						System.out.println("A");
						
						//ukoliko se dodaju 2 identicna
						if(i > 0 && records.get(i - 1).charAt(i) == 'A' && 
								cmp(records.get(i).substring(1), records.get(i - 1).substring(1)) == 0){
							
							// upisi u greske, uvecaj i
							rafErrorFile.seek(rafErrorFile.length());
							rafErrorFile.writeBytes(records.get(i));
							rafErrorFile.setLength(rafErrorFile.length());

							
							
							
						}
						
						else{
							
							rafNewFile.seek(rafNewFile.length());
							rafNewFile.writeBytes(records.get(i).substring(1));
							rafNewFile.setLength(rafNewFile.length());

							
							
						}
						
						
						// dodaj, uvecaj i

						
						i++;

					} else if (records.get(i).charAt(0) == 'D') {
						System.out.println("D");
						// upisi u gresku, uvecaj i
						rafErrorFile.seek(rafErrorFile.length());
						rafErrorFile.writeBytes(records.get(i));
						rafErrorFile.setLength(rafErrorFile.length());

						i++;

					}

					else if (records.get(i).charAt(0) == 'E') {
					
						//System.out.println("E");
						// upisi u greske, uvecaj i
						
						//System.out.println("Greska:\n" + records.get(i) + line);
						rafErrorFile.seek(rafErrorFile.length());
						rafErrorFile.writeBytes(records.get(i));
						rafErrorFile.setLength(rafErrorFile.length());

						i++;

					}
					
					
				//	System.out.println();

				}

			}

	
			rafOldFile.close();
			rafNewFile.close();
			rafErrorFile.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		
		
		//System.out.println("Dosli smo da brisemo");
		
		//obrisi stari fajl
		File file = new File(mPath);
		if(file.delete() == false){
			
			JOptionPane.showMessageDialog(Application.getInstance().getInfoFrame(), "Zatvorite sve fajlove u kojima je otvorena originalna dadoteka pre spajanja!");
			
		} else {
			JOptionPane.showMessageDialog(Application.getInstance().getInfoFrame(), "Spajanje uspesno zavrseno!");

		}
	

		//promeni naziv novom u naziv starog
		file = new File(mPath);
		File file2 = new File(mPath +".NEW");
		file2.renameTo(file);
		//System.out.println(mPath);

		
		
		
		
	}
	
	private int cmp(String a, String b) {
		//System.out.print("CMP " + a + b);
		
		int n = getAttributes().size();
		int k = 0;
		for(int i = 0; i < n; i++){
			
			//System.out.println("i :" + i);
			 
				String mSearchData = a.substring(k, k + getAttributes().get(i).getLength());
				String mCurrentData = b.substring(k, k + getAttributes().get(i).getLength());
				
				k += getAttributes().get(i).getLength();
			
			
			//ukoliko nije PK idi dalje
			if(!getAttributes().get(i).isPK())
				continue;
			
			//System.out.println(getAttributes().get(i).getDataType());
			//ako su int proveri ovako
			//if(getAttributes().get(i).getDataType().equals("TYPE_NUMERIC"))
			if(isNumber(mSearchData) && isNumber(mCurrentData))
			{
				//System.out.println(mSearchData);
				//System.out.println(mSearchData.trim());
				int brSerach = Integer.parseInt(mSearchData.trim());
				int brCurrent = Integer.parseInt(mCurrentData.trim());
				
				if(brSerach > brCurrent) 
					return 1;
				
				if(brSerach == brCurrent)
					continue;
				
				return -1;
				
				
			}
			
			else{
				
				if(mSearchData.toUpperCase().compareTo(mCurrentData.toUpperCase()) == 0)
					continue;
				
				else
					return mSearchData.toUpperCase().compareTo(mCurrentData.toUpperCase());
					
				
				
			}
			
			
			
		}
		
		
		
		return 0;
		
		

	}

	public void filterForTable(Entity mEntity, String[] query) {
		String[] commonQuery = new String[getAttributes().size()];
		Boolean[] commonAttributes = new Boolean[getAttributes().size()];

		for (int i = 0; i < getAttributes().size(); i++)
			commonAttributes[i] = false;

		for (int i = 0; i < getAttributes().size(); i++) {
			commonAttributes[i] = false;
			for (int j = 0; j < mEntity.getAttributes().size(); j++)
				if (mEntity.getAttributes().get(j).getName().equals(getAttributes().get(i).getName())) {
					commonAttributes[i] = true;
					commonQuery[i] = query[j];
				}

		}

		int n = getAttributes().size();

//		for (int i = 0; i < n; i++)
//			System.out.print(commonAttributes[i]);

		ArrayList<String[]> records = new ArrayList<>();

		RandomAccessFile rafFile = null;
		try {

			long mFileSearchPointer = 0;
			rafFile = new RandomAccessFile(mPath, "r");

			while (mFileSearchPointer < rafFile.length()) {

				byte[] buffer = new byte[mRecordSize];

				rafFile.seek(mFileSearchPointer);
				rafFile.read(buffer);
				String line = new String(buffer);

				String mCurrentData[] = new String[n];
				int k = 0;
				for (int j = 0; j < n; j++) {
					String field = line.substring(k, k + getAttributes().get(j).getLength());
					mCurrentData[j] = field;
					k += getAttributes().get(j).getLength();
				}
				boolean bla = true;

				// dakle ukoliko negde dodje do greske postavi ga na false;
				for (int i = 0; i < n; i++)
					if (commonAttributes[i] && (!commonQuery[i].equals(mCurrentData[i]))) {

						// System.out.println("Ubio je \n " + commonQuery[i]
						// +"\n" + mCurrentData[i] );
						bla = false;

					}

				if (bla)
					records.add(mCurrentData);

				mFileSearchPointer = rafFile.getFilePointer();

			}

			rafFile.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		int m = records.size();
		mData = new String[m][n];
		//System.out.println(m + "Kraj fajla");

		for (int i = 0; i < m; i++) {
			System.out.println("");
			for (int j = 0; j < n; j++) {
				// System.out.println(dataForFetch.get(i)[j]);
				mData[i][j] = records.get(i)[j];
				//System.out.print(mData[i][j]);
			}
		}

	}

	public void ResetSearchPointer() {

		mFileSearchPointer = 0;

	}

}

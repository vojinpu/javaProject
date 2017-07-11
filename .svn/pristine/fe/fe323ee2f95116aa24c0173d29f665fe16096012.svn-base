package model.tree;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
public class InfoNodeElement implements Serializable{

	private int blockAddress;
	
	private List<InfoKeyElement> keyValue;
    
    
    
    public InfoNodeElement(int blockAddress,List<InfoKeyElement> keyValue) {
		super();
		this.blockAddress = blockAddress;
		this.keyValue = keyValue;
	}

	public List<InfoKeyElement> getKeyValue() {
		return keyValue;
	}
	public void setKeyValue(List<InfoKeyElement> keyValue) {
		this.keyValue = keyValue;
	}
	public int getBlockAddress() {
		return blockAddress;
	}
	public void setBlockAddress(int blockAddress) {
		this.blockAddress = blockAddress;
	}
	
	public String toString(){
		String value="AE: "+blockAddress+" Key: ";
		for (int i=0;i<keyValue.size();i++){
			value+=keyValue.get(i).getValue()+"|";
		}
		return value;

	}
	
	public InfoNodeElement clone(){
		List<InfoKeyElement> keyValueCopy=new ArrayList<InfoKeyElement>();
		keyValueCopy.addAll(keyValue);
		return new InfoNodeElement(blockAddress,keyValueCopy);
	}




	
}

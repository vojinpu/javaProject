package model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY)
@JsonSubTypes({
    @JsonSubTypes.Type(value = Field.class, name = "Field"),
    @JsonSubTypes.Type(value = Column.class, name = "Column")})

public class Attribute extends InformationResource{
	
	@JsonProperty("dataType")
	private String mDataType;
	@JsonProperty("length")
	private int mLength;
	@JsonProperty("isPK")
	private boolean mIsPK;
	@JsonProperty("isNotNULL")
	private boolean isNotNULL;
 
	public Attribute(String name, String dataType, int length, boolean isPK) {
		setName(name);
		mDataType = dataType;
		mLength = length;
		mIsPK = isPK;
	}
	
	public Attribute() {
		
	}
	
	public String getDataType() {
		return mDataType;
	}

	public void setDataType(String dataType) {
		mDataType = dataType;
	}

	public int getLength() {
		return mLength;
	}

	public void setLength(int mLength) {
		this.mLength = mLength;
	}

	public boolean isPK() {
		return mIsPK;
	}

	public void setIsPK(boolean mIsPK) {
		this.mIsPK = mIsPK;
	}

	public boolean isNotNULL() {
		return isNotNULL;
	}

	public void setNotNULL(boolean isNotNULL) {
		this.isNotNULL = isNotNULL;
	}
	
	
	
}

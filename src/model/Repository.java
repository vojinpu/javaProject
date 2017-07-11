package model;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Repository extends InformationResource {

	@JsonProperty("collection")
	private ArrayList<CollectionResource> mCollection;
	
	@JsonProperty("username")
	 private String mUserName;
	 
	 @JsonProperty("password")
	 private String mPassword;
	 
	 @JsonProperty("server")
	 private String mServer;
	
	public Repository() {
		super();
		mCollection = new ArrayList<>();
	}

	public String getmUserName() {
		return mUserName;
	}

	public void setmUserName(String mUserName) {
		this.mUserName = mUserName;
	}

	public String getmPassword() {
		return mPassword;
	}

	public void setmPassword(String mPassword) {
		this.mPassword = mPassword;
	}

	public String getmServer() {
		return mServer;
	}

	public void setmServer(String mServer) {
		this.mServer = mServer;
	}

	public ArrayList<CollectionResource> getCollection() {
		
		return mCollection;

	}

	public void setCollection(ArrayList<CollectionResource> collection) {
		this.mCollection = collection;
	}
	
	
	@Override
	public String toString() {
	    return getName();
	}

}

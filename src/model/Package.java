package model;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Package extends CollectionResource {
	
	@JsonProperty("collection")
	private ArrayList<CollectionResource> mCollection; // Kolekcija paketa i entiteta

	public Package() {
		super();
		mCollection = new ArrayList<>();
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

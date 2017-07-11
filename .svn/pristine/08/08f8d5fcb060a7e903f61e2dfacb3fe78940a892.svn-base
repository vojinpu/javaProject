package model;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY)
@JsonSubTypes({ @JsonSubTypes.Type(value = AbstractFile.class, name = "AbstractFile"),
		@JsonSubTypes.Type(value = Table.class, name = "Table") })

public abstract class Entity extends CollectionResource {

	@JsonProperty("attributes")
	private ArrayList<Attribute> mAttributes;

	@JsonProperty("relations")
	protected ArrayList<Relation> mRelations;
	
	@JsonIgnore
	private boolean mIsChild;

	private ArrayList<Entity> childrenRelations;

	public Entity() {
		super();
		mAttributes = new ArrayList<>();
		childrenRelations = new ArrayList<>();
		// mRelations = new ArrayList<>();
	}

	public abstract void add();

	public abstract void delete();

	public abstract void modify();

	public abstract void search();

	public abstract boolean fetch();

	public abstract String[][] getData();

	public abstract void setBlockFactor(int factor);

	public abstract long getBlockFactor();

	public abstract long getNumOfFetch();

	public abstract void setNumOfFetch(long numOfFetch);

	public ArrayList<Attribute> getAttributes() {
		return mAttributes;
	}

	public void setAttributes(ArrayList<Attribute> attributes) {
		mAttributes = attributes;
	}

	public ArrayList<Relation> getRelations() {
		return mRelations;
	}

	public void setRelations(ArrayList<Relation> mRelations) {
		this.mRelations = mRelations;
	}

	public void addRelations(Relation relation){
		
		if(mRelations == null){
			mRelations = new ArrayList<>();
			
		}
		
		mRelations.add(relation);
		
	}

	public void removeRelation(Relation rel) {
		
		mRelations.remove(rel);
		
	}

	public boolean isChild() {
		return mIsChild;
	}

	public void setIsChild(boolean mIsChild) {
		this.mIsChild = mIsChild;
	}
	
	
}

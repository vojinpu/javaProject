package model;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

//@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE, isGetterVisibility = JsonAutoDetect.Visibility.NONE, getterVisibility = JsonAutoDetect.Visibility.NONE)
public class Relation extends InformationResource {

	@JsonProperty("parent")
	private String mParentName;
	@JsonProperty("parentIDs")
	private ArrayList<String> mParentIDsNames;
	@JsonProperty("child")
	private String mChildName;
	@JsonProperty("childIDs")
	private ArrayList<String> mChildIDsNames;

	@JsonIgnore
	private Entity mParent;
	@JsonIgnore
	private Entity mChild;
	@JsonIgnore
	private ArrayList<Attribute> mParentAttrbutes;
	@JsonIgnore
	private ArrayList<Attribute> mChildAttrbutes;

	public Relation() {

		// Objekti
		mParentAttrbutes = new ArrayList<>();
		mChildAttrbutes = new ArrayList<>();

		// Imena
		mParentIDsNames = new ArrayList<>();
		mChildIDsNames = new ArrayList<>();

	}

	public String getParentName() {
		return mParentName;
	}

	public void setParentName(String mParentName) {
		this.mParentName = mParentName;
	}

	public ArrayList<String> getParentIDsNames() {
		return mParentIDsNames;
	}

	public void setParentIDsNames(ArrayList<String> mParentIDsNames) {
		this.mParentIDsNames = mParentIDsNames;
	}

	public String getChildName() {
		return mChildName;
	}

	public void setChildName(String mChildName) {
		this.mChildName = mChildName;
	}

	public ArrayList<String> getChildIDsNames() {
		return mChildIDsNames;
	}

	public void setChildIDsNames(ArrayList<String> mChildIDsNames) {
		this.mChildIDsNames = mChildIDsNames;
	}

	@JsonIgnore
	public Entity getParent() {
		return mParent;
	}

	@JsonIgnore
	public void setParent(Entity mParent) {
		this.mParent = mParent;
	}

	@JsonIgnore
	public Entity getChild() {
		return mChild;
	}

	@JsonIgnore
	public void setChild(Entity mChiled) {
		this.mChild = mChiled;
	}

	@JsonIgnore
	public ArrayList<Attribute> getParentAttrbutes() {
		return mParentAttrbutes;
	}

	@JsonIgnore
	public void setParentAttrbutes(ArrayList<Attribute> mParentAttrbutes) {
		this.mParentAttrbutes = mParentAttrbutes;
	}

	@JsonIgnore
	public ArrayList<Attribute> getChildAttrbutes() {
		return mChildAttrbutes;
	}

	@JsonIgnore
	public void setChildAttrbutes(ArrayList<Attribute> mChildAttrbutes) {
		this.mChildAttrbutes = mChildAttrbutes;
	}

	public void addParentAttribute(Attribute attribute) {

		mParentAttrbutes.add(attribute);
	}

	public void addChildAttribute(Attribute attribute) {

		mChildAttrbutes.add(attribute);
	}

}

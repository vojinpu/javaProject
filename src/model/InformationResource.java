package model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY)
@JsonSubTypes({
    @JsonSubTypes.Type(value = Repository.class, name = "Repository"),
    @JsonSubTypes.Type(value = DBConnection.class, name = "Repository"),
    @JsonSubTypes.Type(value = CollectionResource.class, name = "CollectionResource"),
    @JsonSubTypes.Type(value = Attribute.class, name = "Attribute"),
    @JsonSubTypes.Type(value = Relation.class, name = "Relation")})

public abstract class InformationResource {

	@JsonProperty("name")
	private String mName;
	
	//Ovo moze biti problematicno zbog Jacksona, 
	public InformationResource(String name){
		mName = name;
	}
	
	public InformationResource(){

	}
	
	public String getName() {
		return mName;
	}

	public void setName(String mName) {
		this.mName = mName;
	}
	
	
	@Override
	public String toString() {
	    return getName();
	}
	
}

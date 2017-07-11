package model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY)
@JsonSubTypes({
    @JsonSubTypes.Type(value = Package.class, name = "Package"),
    @JsonSubTypes.Type(value = Entity.class, name = "AbstractEntity")})	//Izmenjeno iz Entity u AbstractEntity

public abstract class CollectionResource extends InformationResource {

	public CollectionResource (){
		super();
	}
	
}

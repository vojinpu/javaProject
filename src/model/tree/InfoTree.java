package model.tree;

import java.io.Serializable;

@SuppressWarnings("serial")
public class InfoTree implements Serializable{
	 
    private InfoNode rootElement;
     
    public InfoTree() {
        super();
    }
 
    public InfoNode getRootElement() {
        return this.rootElement;
    }
 
    public void setRootElement(InfoNode rootElement) {
        this.rootElement = rootElement;
    }
     


}

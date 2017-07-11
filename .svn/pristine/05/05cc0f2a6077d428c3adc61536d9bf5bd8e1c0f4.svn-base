package model.tree;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.tree.TreeNode;

@SuppressWarnings("serial")
public class InfoNode implements TreeNode, Serializable{
	 
    public List<InfoNodeElement>  data;
    public List<InfoNode> children;
 
    
    public InfoNode() {
    	
    	
        
        data=new ArrayList<InfoNodeElement>();
        children=new ArrayList<InfoNode>();
    }
 
 

    public InfoNode(List<InfoNodeElement>  data) {
        this();
        setData(data);
    } 
    
    
    public void addChild(InfoNode child) {
        if (children == null) {
            children = new ArrayList<InfoNode>();
        }
        children.add(child);
    }
    
    
	public Enumeration children() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean getAllowsChildren() {
		// TODO Auto-generated method stub
		return true;
	}

	public TreeNode getChildAt(int childIndex) {
		// TODO Auto-generated method stub
		 return this.children.get(childIndex);
	}

	public int getChildCount() {
		// TODO Auto-generated method stub
        if (children == null) {
            return 0;
        }
        return children.size();
	}

	public int getIndex(TreeNode node) {
		// TODO Auto-generated method stub
		return children.indexOf(node);
	}

	public TreeNode getParent() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean isLeaf() {
		// TODO Auto-generated method stub
		return false;
	}
	
    public List<InfoNodeElement> getData() {
        return this.data;
    }
 
    public void setData(List<InfoNodeElement> data) {
        this.data = data;
    }
    
    public List<InfoNode> getChildren() {
        if (this.children == null) {
            return new ArrayList<InfoNode>();
        }
        
        return this.children;
    }
 

    public void setChildren(List<InfoNode> children) {
        this.children = children;
    }
    
    
	public InfoNode clone(){
	    List<InfoNodeElement>  dataCopy=new ArrayList<InfoNodeElement>();
	    for (int i=0;i<data.size();i++){
	    	InfoNodeElement nodeElement=data.get(i).clone();
	    	dataCopy.add(nodeElement);
	    }
	    List<InfoNode> childrenCopy=new ArrayList<InfoNode>();		
	    for (int i=0;i<children.size();i++){
	    	InfoNode node=children.get(i).clone();
	    	childrenCopy.add(node);
	    }	
	    
	    InfoNode nodeCopy=new InfoNode();
	    nodeCopy.setChildren(childrenCopy);
	    nodeCopy.setData(dataCopy);
	    return nodeCopy;
	}
}

package app.controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.tree.DefaultMutableTreeNode;

import org.mozilla.javascript.ast.GeneratorExpressionLoop;

import model.CollectionResource;
import model.Entity;
import model.ISekFile;
import model.InformationResource;
import model.Package;
import model.Relation;
import model.Repository;
import model.SekFile;
import model.Table;

public class TreeFilling {

	private Repository mRepository;
	
	public TreeFilling(){
		
	}
	
	public DefaultMutableTreeNode fillTree(Repository repository){
		
		mRepository = repository;
		DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode(mRepository);
		
		if (repository == null){
			System.out.println("repo je null");
		}
		
		
		fillPackages(rootNode);
		
		return rootNode;
		
	}




	private void fillPackages(DefaultMutableTreeNode root) {
		
		
		Object object = root.getUserObject();
		
		/* Ovo sam izmenio iz InformationResource u CollectionResource koji extenduju entitet i paket,
		 *  da li moze da napravi problem negde?
		 */
		ArrayList<CollectionResource> children = new ArrayList<>();

		if(object instanceof Repository)
			children = ((Repository) object).getCollection();
		

		if(object instanceof Package)
			children = ((Package) object).getCollection();
		
		

		ArrayList<Package> chPackage = new ArrayList<>();

		ArrayList<Entity> chEntity = new ArrayList<>();
		
		
		//Paralelno punjenje lista
		for(InformationResource inf : children){

			if(inf instanceof Package)
				chPackage.add((Package) inf);
			
			//Sad je glavna nadklasa Entity!!
			if(inf instanceof Entity)
				chEntity.add((Entity) inf);
			
		}
		
		
		
		//Sortiranje lista po imenu
		Collections.sort(chEntity, new Comparator<Entity>() {

			@Override
			public int compare(Entity o1, Entity o2) {
				// TODO Auto-generated method stub
				return o1.getName().compareTo(o2.getName());
				
			}
		});
		
		
		Collections.sort(chPackage, new Comparator<Package>() {

			@Override
			public int compare(Package o1, Package o2) {
				// TODO Auto-generated method stub
				return o1.getName().compareTo(o2.getName());
				
			}
		});
		
		
		
		
		//Prvo drvo popunjava sa paketima, a zatim sa Entitetima, kako bi bili sortirani
		for(Package pack : chPackage){
			
			DefaultMutableTreeNode childNode = new DefaultMutableTreeNode(pack);
			root.add(childNode);
			
			fillPackages(childNode);
			
		}
		
		for(Entity entity: chEntity){
			
			DefaultMutableTreeNode childNode = new DefaultMutableTreeNode(entity);
			root.add(childNode);
			
			
			if((entity instanceof SekFile || entity instanceof ISekFile || entity instanceof Table)&& entity.getRelations() != null)
				for(Relation rel : entity.getRelations()){
					
					Entity obj = (Entity)childNode.getUserObject();
					
					if(rel.getParent().getName().equals(obj.getName())){
						
						DefaultMutableTreeNode relationNode = new DefaultMutableTreeNode(rel.getChild());
						childNode.add(relationNode);
						
						
						
					}
						
					
				}
			
		}
		
		
		
		
		
		
		
	}
	

	
}

package app.controllers.app;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.AbstractAction;

import com.fasterxml.jackson.databind.ObjectMapper;

import app.Application;
import model.Attribute;
import model.CollectionResource;
import model.Entity;
import model.ISekFile;
import model.InformationResource;
import model.Package;
import model.Relation;
import model.Repository;
import model.SekFile;
import model.Table;

public class ParseController extends AbstractAction {

	private Repository repo;

	@Override
	public void actionPerformed(ActionEvent e) {

		ObjectMapper mapper = new ObjectMapper();
		File file = new File(Application.getInstance().getContext().getMetaPath());
		repo = new Repository();

		try {

			repo = mapper.readValue(file, Repository.class);

			// debugShow(repo);
		} catch (IOException e1) {

			System.out.println("LOS JSON DRAGANEEEEE111!");
			// e1.printStackTrace();
		}

		markChilds(repo.getCollection());

		// test(repo.getCollection());
		
		
		// // Inser RelationsinsertRelationsISEK(repo.getCollection());
		insertRelationsISEK(repo.getCollection());

		insertRelationsSek(repo.getCollection());
		
		insertRelationsDB(repo.getCollection());
		
		
//		for(CollectionResource c : ((Package)repo.getCollection().get(0)).getCollection()){
//			if(c.getName().equals("SPORTSKO_DRUSTVO")){
//			
//				
//				
//				for(Relation re : ((Entity)c).getRelations()){
//					
//					System.out.println(re.getParentName() + " -> " + re.getChildName());
//					
//				}
//				
//				
//			}
//			
//		}

		
	
		
		Application.getInstance().setRepository(repo);
	}

	// private void test(ArrayList<CollectionResource> collection) {
	//
	// for (CollectionResource resource : collection) {
	//
	// if ((resource instanceof Package)) {
	// test(((Package) resource).getCollection());
	// continue;
	// }
	//
	// Entity entity = (Entity) resource;
	//
	// if (!entity.isChild()) {
	// continue;
	// }
	//
	// for (Relation relation : entity.getRelations()) {
	//
	// Entity parent = findParent(relation.getParentName());
	//
	// relation.setParent(parent);
	//
	// if (entity instanceof SekFile) {
	// SekFile sekFile = (SekFile) entity;
	// relation.setChiled(sekFile.copy());
	// } else if (entity instanceof ISekFile) {
	// ISekFile iSekFile = (ISekFile) entity;
	// relation.setChiled(iSekFile.copy());
	// }
	//
	// for (Attribute attr : parent.getAttributes()) {
	// if (relation.getParentIDsNames().contains(attr.getName())) {
	// relation.getParentAttrbutes().add(attr);
	// }
	// }
	//
	// for (Attribute attr : entity.getAttributes()) {
	// if (relation.getChildIDsNames().contains(attr.getName())) {
	// relation.getChildAttrbutes().add(attr);
	// }
	// }
	//
	// parent.addRelations(relation);
	//
	// }
	// entity.setIsChild(false);
	// }
	//
	// }

	private void markChilds(ArrayList<CollectionResource> collection) {

		for (CollectionResource collectionResource : collection) {
			if (collectionResource instanceof Package) {
				markChilds(((Package) collectionResource).getCollection());
			} else {
				// collectionResoirce je entitet
				Entity entity = (Entity) collectionResource;

				if (entity.getRelations() != null) {
					entity.setIsChild(true);
				} else {
					entity.setIsChild(false);
				}
			}
		}

	}
	
	private void insertRelationsDB(ArrayList<CollectionResource> collection) {
		
		for (CollectionResource resource : collection) {

			if (resource instanceof Package)
				insertRelationsDB(((Package) resource).getCollection());

			if (resource instanceof Table && ((Table) resource).isChild()) {

				for (Relation rel : ((Entity) resource).getRelations()) {

					// ukoliko relacija nije setovana
					if (rel.getParent() == null) {

						// Dobili smo parenta relacije
						Entity ent = getParentDB(rel.getParentName(), repo.getCollection());

						// Relaciji smo postavili
						rel.setChild(((Table)resource).copy());
						rel.setParent(ent);

						// ***dodela atributa relacijama ***

						for (Attribute attribute : ((Entity) resource).getAttributes()) {
							for (String str : rel.getChildIDsNames()) {
								if (attribute.getName().equals(str)) {
									rel.addChildAttribute(attribute);
								//	System.out.println(attribute.getName() + "   "+attribute.isNotNULL());
								}
							}
						}

						for (Attribute attribute : ent.getAttributes()) {
							for (String str : rel.getParentIDsNames()) {
								if (attribute.getName().contains(str)) {
									rel.addParentAttribute(attribute);
								}
							}
						}
						
						// Njemu smo dodali relaciju
						ent.addRelations(rel);
						
					}

				}

				((Entity)resource).setIsChild(false);
				//((Entity)resource).getRelations().clear();

			}
			
		}

	}

	private void insertRelationsSek(ArrayList<CollectionResource> collection) {

		for (CollectionResource resource : collection) {

			if (resource instanceof Package)
				insertRelationsSek(((Package) resource).getCollection());

			if (resource instanceof SekFile && ((SekFile) resource).isChild()) {

				for (Relation rel : ((Entity) resource).getRelations()) {

					// ukoliko relacija nije setovana
					if (rel.getParent() == null) {

						// Dobili smo parenta relacije
						Entity ent = getParentSek(rel.getParentName(), repo.getCollection());

						// Relaciji smo postavili
						rel.setChild(((SekFile)resource).copy());
						rel.setParent(ent);

						// ***dodela atributa relacijama ***

						for (Attribute attribute : ((Entity) resource).getAttributes()) {
							for (String str : rel.getChildIDsNames()) {
								if (attribute.getName().equals(str)) {
									rel.addChildAttribute(attribute);
								}
							}
						}

						for (Attribute attribute : ent.getAttributes()) {
							for (String str : rel.getParentIDsNames()) {
								if (attribute.getName().contains(str)) {
									rel.addParentAttribute(attribute);
								}
							}
						}
						
						// Njemu smo dodali relaciju
						ent.addRelations(rel);
						
					}

				}

				((Entity)resource).setIsChild(false);
				((Entity)resource).getRelations().clear();

			}
			
		}

	}

	private Entity getParent(String parentName, ArrayList<CollectionResource> collection, Entity entity) {

		for (CollectionResource col : collection) {

			if (col instanceof Package) {

				Entity proba = getParentSek(parentName, ((Package) col).getCollection());
				if (proba != null && proba.getName().equals(parentName))
					return proba;
			}
			if (col.getClass().equals(entity.getClass()) && col.getName().equals(parentName)) {
				return ((Entity) col);
			}

		}

		return null;
	}

	private Entity getParentDB(String parentName, ArrayList<CollectionResource> collection) {

		for (CollectionResource col : collection) {

			if (col instanceof Package) {

				Entity proba = getParentDB(parentName, ((Package) col).getCollection());
				if (proba != null && proba.getName().equals(parentName))
					return proba;
			}
			if (col instanceof Table && col.getName().equals(parentName)) {
				return ((Entity) col);
			}

		}

		return null;
	}
	
	
	private Entity getParentSek(String parentName, ArrayList<CollectionResource> collection) {

		for (CollectionResource col : collection) {

			if (col instanceof Package) {

				Entity proba = getParentSek(parentName, ((Package) col).getCollection());
				if (proba != null && proba.getName().equals(parentName))
					return proba;
			}
			if (col instanceof SekFile && col.getName().equals(parentName)) {
				return ((Entity) col);
			}

		}

		return null;
	}

	private void insertRelationsISEK(ArrayList<CollectionResource> collection) {

		for (CollectionResource resource : collection) {

			if (resource instanceof Package)
				insertRelationsISEK(((Package) resource).getCollection());

			if (resource instanceof ISekFile && ((ISekFile) resource).isChild()) {

				for (Relation rel : ((Entity) resource).getRelations()) {

					// ukoliko relacija nije setovana
					if (rel.getParent() == null) {

						// Dobili smo parenta relacije
						Entity ent = getParentISEK(rel.getParentName(), repo.getCollection());

						// Relaciji smo postavili
						rel.setChild(((ISekFile)resource).copy());
						rel.setParent(ent);

						// ***dodela atributa relacijama ***

						for (Attribute attribute : ((Entity) resource).getAttributes()) {
							for (String str : rel.getChildIDsNames()) {
								if (attribute.getName().equals(str)) {
									rel.addChildAttribute(attribute);
								}
							}
						}

						for (Attribute attribute : ent.getAttributes()) {
							for (String str : rel.getParentIDsNames()) {
								if (attribute.getName().contains(str)) {
									rel.addParentAttribute(attribute);
								}
							}
						}
						
						// Njemu smo dodali relaciju
						ent.addRelations(rel);
					}

				}

				((Entity)resource).setIsChild(false);

			}
		}

	}

	private Entity getParentISEK(String parentName, ArrayList<CollectionResource> collection) {

		for (CollectionResource col : collection) {

			if (col instanceof Package) {

				Entity proba = getParentISEK(parentName, ((Package) col).getCollection());
				if (proba != null && proba.getName().equals(parentName))
					return proba;
			}
			if (col instanceof ISekFile && col.getName().equals(parentName)) {
				return ((Entity) col);
			}

		}

		return null;
	}

	private void debugShow(Repository repo) {

		System.out.println("<--- DEBUG --->");

		System.out.println("Ime repozitorijuma: " + repo.getName());

		ArrayList<CollectionResource> repoCollection = repo.getCollection();

		System.out.println("Broj elemenata u repou " + repoCollection.size());

		for (CollectionResource collectionResource : repoCollection) {

			System.out.println("\nPAKET: " + collectionResource.getName() + "\n");

			ArrayList<CollectionResource> entities = ((Package) collectionResource).getCollection();

			for (CollectionResource collectionResource2 : entities) {

				System.out.println(
						"ENTITET " + collectionResource2.getName() + " KLASE " + collectionResource2.getClass());

				ArrayList<Relation> relations = ((Entity) collectionResource2).getRelations();

				if (relations == null) {
					System.out.println("Relations je null");
					continue;
				} else {
					System.out.println("Entitet sadrzi " + relations.size() + " relacija"
							+ ((relations.size() > 0) ? " i to su :" : ""));
				}

				for (Relation relation : relations) {
					System.out.println(relation.getName());
					System.out.println("Parent je " + relation.getParentName() + " i njegovi id-evi su:");
					for (String id : relation.getParentIDsNames()) {
						System.out.println(id);
					}
					System.out.println("Child je " + relation.getChildName() + " i njegovi id-evi su:");
					for (String id : relation.getChildIDsNames()) {
						System.out.println(id);
					}
				}
			}
		}
		System.out.println("<--- END DEBUG --->");
	}
}

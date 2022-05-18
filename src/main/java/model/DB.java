package model;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.entities.Book;
import model.entities.Etudiant;
import model.entities.PackageType;
import model.entities.Universite;

public class DB {
	// --------------------------------------------- Entity Manager
	private EntityManagerFactory emf;
	public EntityManager em;

	// --------------------------------------------- Dependency Inversion pattern
	private DB() {
		// MySql or h2 -> get configuration from persistence.xml
		emf = Persistence.createEntityManagerFactory("h2");
		em = emf.createEntityManager();
	}

	// --------------------------------------------- singleton pattern
	private static final Object LOCK = new Object();
	private static DB db;

	public static DB getInstanceDB() {
		synchronized (LOCK) {
			if (db == null) {
				db = new DB();
				System.out.println("********************* : getInstanceDB : singleton pattern");
			}

		}
		return db;
	}
	
	public static void init() {
		if(PackageType.getOne(1l) == null) {
			Book.addNew("book 01", "domaine 01");
			Book.addNew("book 02", "domaine 02");
			Book.addNew("book 03", "domaine 03");
			Book.addNew("book 04", "domaine 04");
			Book.addNew("book 05", "domaine 05");

			PackageType.addNew("premium");
			PackageType.addNew("standard");
			PackageType.addNew("illimite");
	
			Universite.addNew("universite alger 1",1L);
			Universite.addNew("universite alger 2",2L);
			Universite.addNew("universite alger 3",2L);
			Universite.addNew("universite USTHB",1L);
			
			Etudiant.addNew("etudiant01", "password01", 1L);
			Etudiant.addNew("etudiant02", "password02", 2L);
			Etudiant.addNew("etudiant03", "password03", 3L);
			Etudiant.addNew("etudiant04", "password04", 4L);
			
			Etudiant e1 = Etudiant.getOneById(1l);
			e1.takeBookFromLibrary(Book.getOne(1l));
			e1.takeBookFromLibrary(Book.getOne(2l));
			System.out.println("not exist");
		}
	}
}

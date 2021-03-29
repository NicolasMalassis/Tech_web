package Application;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity // On déclare a la base de donnée que c'est une entité.
@Table(name = "ECOLES") // On précise le nom de la table...
public class Ecoles {
	// On génère la clé primaire de manière automatique.
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	
	// Les variables propre a l'entité école:
	private long id;
	
	private String nomEcole;
	private int inscrits;
	private int classes;
	private int integres;
	
	// Classe equals utile dans la requête delete de FiliereRessource, pour utiliser le .remove(Object) d'une liste ligne 166.
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ecoles other = (Ecoles) obj;
		if (classes != other.classes)
			return false;
		if (id != other.id)
			return false;
		if (inscrits != other.inscrits)
			return false;
		if (integres != other.integres)
			return false;
		if (nomEcole == null) {
			if (other.nomEcole != null)
				return false;
		} else if (!nomEcole.equals(other.nomEcole))
			return false;
		return true;
	}
	// Constructeur par défaut:
	public Ecoles() {
		super();
		// TODO Auto-generated constructor stub
	}
	// Constructeur par recopie:
	public Ecoles(String nomEcole, int inscrits, int classes, int integres) {
		super();
		this.nomEcole = nomEcole;
		this.inscrits = inscrits;
		this.classes = classes;
		this.integres = integres;
	}
	// Getters et Setters:
	public synchronized String getNomEcole() {
		return nomEcole;
	}
	public synchronized void setNomEcole(String nomEcole) {
		this.nomEcole = nomEcole;
	}
	public synchronized int getInscrits() {
		return inscrits;
	}
	public synchronized void setInscrits(int inscrits) {
		this.inscrits = inscrits;
	}
	public synchronized int getClasses() {
		return classes;
	}
	public synchronized void setClasses(int classes) {
		this.classes = classes;
	}
	public synchronized int getIntegres() {
		return integres;
	}
	public synchronized void setIntegres(int integres) {
		this.integres = integres;
	}
	public synchronized long getId() {
		return id;
	}
	public synchronized void setId(long id) {
		this.id = id;
	}
	
}

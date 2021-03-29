package Application;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="FILIERES")
public class Filieres {
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	
	private long id;
	
	private String nomFiliere;
	//Génère tout les types de cascade, Charge la relation par défaut, Supprime les concours si ils ne sont plus ratachés a une filière. 
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
	private List<Concours> concours;
	
	public Filieres() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Filieres(String nomFiliere, List<Concours> concours) {
		super();
		this.nomFiliere = nomFiliere;
		this.concours = concours;
	}
	
	public List<Concours> getConcours() {
		return concours;
	}		
	public void setEcoles(List<Concours> concours) {
		this.concours = concours;
	}
	
	public synchronized String getNomFiliere() {
		return nomFiliere;
	}
	public synchronized void setNomFiliere(String nomFiliere) {
		this.nomFiliere = nomFiliere;
	}
	public synchronized long getId() {
		return id;
	}
	public synchronized void setId(long id) {
		this.id = id;
	}
		
}

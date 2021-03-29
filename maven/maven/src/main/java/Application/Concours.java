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
@Table(name="CONCOURS")
public class Concours {
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	
	private long id;
	
	private String nomConcour;
	//Génère tout les types de cascade, Charge la relation par défaut, Supprime les ecoles si elles ne sont plus ratachées a un concours. 
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
	private List<Ecoles> ecoles;

	public Concours() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Concours(String nomConcour, List<Ecoles> ecoles) {
		super();
		this.nomConcour = nomConcour;
		this.ecoles = ecoles;
	}
	
	public List<Ecoles> getEcoles() {
		return ecoles;
	}		
	public void setEcoles(List<Ecoles> ecoles) {
		this.ecoles = ecoles;
	}
	
	public synchronized String getNomConcour() {
		return nomConcour;
	}
	public synchronized void setNomConcour(String nomConcour) {
		this.nomConcour = nomConcour;
	}
	public synchronized long getId() {
		return id;
	}
	public synchronized void setId(long id) {
		this.id = id;
	}
	
}

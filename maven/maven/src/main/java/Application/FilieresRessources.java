package Application;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PATCH;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.springframework.beans.factory.annotation.Autowired;


@Path("Filieres") // Chemin de l'url
public class FilieresRessources {
	
	 // On créer les différents CRUD des objets Filieres, Concours et Ecoles
	 @Autowired
	 private FilieresRepository fr;
	 @Autowired
	 private ConcoursRepository cr;
	 @Autowired
	 private EcolesRepository er;
	 
	 // Créer un objet Filieres
	 @POST
	 @Consumes(MediaType.APPLICATION_JSON)
	 @Produces(MediaType.APPLICATION_JSON)
	 public Filieres createFiliere(Filieres f) {
		return fr.save(f);
	 }
	 
	 // Chercher l'ensemble des objets Filieres
	 @GET
	 @Produces(MediaType.APPLICATION_JSON)
	 public List<Filieres> getAllFilieres() {
		List<Filieres> fil = new ArrayList<>();
		fr.findAll().forEach(fil::add);
		return fil;
	 }
	 
	 // Chercher l'ensemble des objets Concours appartenants a une filière passée en paramètre
	 @GET
	 @Path("/getAllConcours/{nomFiliere}")
	 @Produces(MediaType.APPLICATION_JSON)
	 public List<Concours> getAllConcours(@PathParam("nomFiliere") String nomFiliere) {
		List<Filieres> fil = new ArrayList<>(); // On créer une liste qui regroupe toutes les filières
		fr.findAll().forEach(fil::add); // On ajoute les différentes filières
		for(Filieres f : fil) {  // Tri 
			if((f.getNomFiliere()).equals(nomFiliere)) { // Si la filière a le même nom que celui passé en paramètre
				return f.getConcours(); //On retourne la liste de ses concours
			}
		}
		return null; // Sinon null
	}
	
	// Chercher les écoles d'un concours particulier appartenant a une filière particulière
	@GET
	@Path("/getAllEcoles/{nomFiliere}/{nomConcour}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Ecoles> getAllEcoles(@PathParam("nomFiliere") String nomFiliere, @PathParam("nomConcour") String nomConcour) {
		List<Filieres> fil = new ArrayList<>();
		fr.findAll().forEach(fil::add);
		for(Filieres f : fil) {
			if((f.getNomFiliere()).equals(nomFiliere)) { // Tri sur les filières
				for(Concours c : f.getConcours()) {
					if(c.getNomConcour().equals(nomConcour)) { // Tri sur les concours
						return c.getEcoles(); // On retourne la liste de ses écoles
					}
				}
			}
		}
		return null; // Sinon null
	}
	
	// Chercher une école particulière avec ses paramètres
	@GET
	@Path("/findEcole/{nomFiliere}/{nomConcour}/{nomEcole}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findEcole(@PathParam("nomFiliere") String nomFiliere, @PathParam("nomConcour") String nomConcour, @PathParam("nomEcole") String nomEcole) {
		List<Filieres> fil = new ArrayList<>();
		fr.findAll().forEach(fil::add);
		for(Filieres f : fil) {
			if((f.getNomFiliere()).equals(nomFiliere)) { // Tri sur les filières 
				for(Concours c : f.getConcours()) {
					if(c.getNomConcour().equals(nomConcour)) { // Tri sur les concours
						for(Ecoles e : c.getEcoles()) {
							if((e.getNomEcole()).equals(nomEcole)) { // Tri sur les écoles
								return Response.ok(e).build(); // On retourne l'école
							}
						}
					}
				}
			}
		}
		return Response.status(Response.Status.NO_CONTENT).build(); // Sinon rien
	}
	
	// Chercher la liste des écoles d'un concours particulier
	@GET
	@Path("/findConcours/{nomFiliere}/{nomConcour}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findConcours(@PathParam("nomFiliere") String nomFiliere, @PathParam("nomConcour") String nomConcour) {
		List<Filieres> fil = new ArrayList<>();
		fr.findAll().forEach(fil::add);
		for(Filieres f : fil) {
			if((f.getNomFiliere()).equals(nomFiliere)) { // Tri sur les filières
				for(Concours c : f.getConcours()) {
					if(c.getNomConcour().equals(nomConcour)) { // Tri sur les concours
						return Response.ok(c.getEcoles()).build(); // On retourne sa liste d'écoles
					}
				}	
			}
		}
		return Response.status(Response.Status.NO_CONTENT).build(); // Sinon rien
	}
	
	// Créer un concour sans écoles
	@POST
	@Path("/createConcours/{nomFiliere}/{nomConcour}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createConcours(@PathParam("nomFiliere") String nomFiliere, @PathParam("nomConcour") String nomConcour) {
		List<Filieres> fil = new ArrayList<>();
		fr.findAll().forEach(fil::add);
		for(Filieres f : fil) {
			if((f.getNomFiliere()).equals(nomFiliere)) { // On cherche la filière dans laquelle on veux ajouter le concours
				// On créer un concour avec une liste d'écoles initialement nulle et on l'ajoute a la liste des concours de la filière
				f.getConcours().add(new Concours(nomConcour, null)); 
				fr.save(f); // On sauvegarde la filière
				return Response.ok(f).build(); // On retourne la filière
			}
		}
		return Response.status(Response.Status.NOT_IMPLEMENTED).build(); // Sinon on lui indique qu'on a pas réussi
	}
	
	// Créer une école avec tous ses paramètres dans un concours particulier
	@POST
	@Path("/createEcoles/{nomFiliere}/{nomConcour}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createEcole(@PathParam("nomFiliere") String nomFiliere, @PathParam("nomConcour") String nomConcour, Ecoles e) {
		List<Filieres> fil = new ArrayList<>();
		fr.findAll().forEach(fil::add);
		for(Filieres f : fil) {
			if((f.getNomFiliere()).equals(nomFiliere)) { // Tri sur les filières
				for(Concours c : f.getConcours()) {
					if(c.getNomConcour().equals(nomConcour)) { // Tri sur les concours
						c.getEcoles().add(e); // On ajout l'école passée en paramètre
						fr.save(f); // On sauvegarde la filière
						return Response.ok(f).build(); // On retourne la totalité de la filière
					}
				}
			}
		}
		return Response.status(Response.Status.NOT_IMPLEMENTED).build(); // Sinon on lui indique qu'on a pas réussi
	}
	
	// Supprimer une école particulière
	@DELETE
	@Path("/deleteEcoles/{idFilieres}/{idConcours}/{idEcole}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteEcoles(@PathParam ("idFilieres") long idFilieres, @PathParam ("idConcours") long idConcours, @PathParam("idEcole") long idEcole) {
		Optional<Ecoles> ecole = er.findById(idEcole); // On cherche l'école en question par id		
		if(ecole.isPresent()) {	// Si l'école existe
			Filieres f = fr.findById(idFilieres).get();	// On récupère l'objet Filieres qui contient cette école par id		
			for(Concours c : f.getConcours()) { // On trouve le concours qui contient l'école dans la liste de cette filière
				if(c.getId() == idConcours) { // On le trouve avec son id
					c.getEcoles().remove(ecole.get()); // On retire l'école de ce concours 
				}
			}
			fr.save(f); // On sauvegarde
			return Response.ok(f).build(); // On retourne la filière
		} else { // Sinon on renvoie un statut personnalisé 
			return Response.status(Response.Status.NOT_FOUND).build();
		}
	}
	
	// Mettre à jour les paramètres d'une école
	@PATCH
	@Path("/updateEcole/{idEcole}/{stat}/{modification}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateEcole(@PathParam("idEcole") long idEcole, @PathParam("stat") String stat, @PathParam("modification") int modification) {
		Optional<Ecoles> optional = er.findById(idEcole); // On cherche l'école à modifier par id

		if (optional.isPresent()) { // Si elle est présente
			Ecoles e = optional.get(); // On la récupère dans une variable
			
			// On va ensuite venir chercher quelle statistique a été passée en paramètre, pour la modifier ensuite
			if(stat.equals("inscrits")) {
				e.setInscrits(modification); // On la modifie avec le paramètre "modification" passé en paramètre
				er.save(e); // On sauvegarde l'école
				return Response.ok(e).build(); // On renvoie l'école modifiée
			}
			if(stat.equals("classes")) {
				e.setClasses(modification);
				er.save(e);
				return Response.ok(e).build();
			} 
			if(stat.equals("integres")) {
				e.setIntegres(modification);
				er.save(e);
				return Response.ok(e).build();
			}
		}
		return Response.status(Response.Status.NOT_FOUND).build(); // Sinon statut not found
	}
	
}
	


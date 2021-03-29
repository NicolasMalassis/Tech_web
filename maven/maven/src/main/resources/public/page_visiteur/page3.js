
	// La redirection des différents onglets
	
	$(".bouton_rouge").click(function(){ window.location.href = "https://www.insa-hautsdefrance.fr/"; });
	$("li:eq(0)").click(function(){ window.location.href = "../page_accueil/index.html"; });
	$("li:eq(2)").click(function(){ window.location.href = "visiteur.html"; });
	$("li:eq(3)").click(function(){ window.location.href = "../page_contact/contact.html"; });
	
	// Partie session, accès a administrateur
	
	let nom = sessionStorage.getItem('nom');
	let mdp = sessionStorage.getItem('mdp');
	
	$("li:eq(1)").click(function(){
		if(nom === "admin" && mdp === "1234"){
			window.location.href = "../page_admin/admin.html";
		} else {
			window.location.href = "../page_visiteur/visiteur.html";
		}
	});
	
	
	$.get("http://localhost:8080/API/Filieres",function(response){
		response.forEach(r => appendToSelects(r));
   	});
   	
   	
    // Requête de recherche d'une école particulière
    
    $('#my-button').click(function(){
    	let filiere = $("#filiere option:selected").text();
    	let concours = $("#concours option:selected").text();
		let ecole = $('#my-input').val();
		
    	$.ajax({
    	    type: "GET",
   		    url: "http://localhost:8080/API/Filieres/findEcole/"+filiere+"/"+concours+"/"+ecole, 		    
            success: function(data){
           		appendToListStats(data);
            }
        });
        
        $('#my-input1').val('');
		return false;
    });
     
    // Recherche de la liste des écoles d'un concours particulier
    
    $('#my-button2').click(function(){
    	let filiere = $("#filiere1 option:selected").text();
		let concours = $('#my-input1').val();
		
    	$.ajax({
    	    type: "GET",
   		    url: "http://localhost:8080/API/Filieres/findConcours/"+filiere+"/"+concours, 		    
            success: function(data){
           		appendToListEcoles(data);
            }
        });
        
        $('#my-input1').val('');
		return false;
    });
    
    
    // Recherche de la liste des concours d'une filière pour les ajouter au select
    
   	function choixRechercheEcole() {
		document.getElementById("concours").length = 1;
        let select = $("#filiere option:selected").text();
 		$.get("http://localhost:8080/API/Filieres/getAllConcours/"+select,function(response){ 
    		response.forEach(c => appendToConcours(c)); 
    	});
    }
	
	
	// Ajout des filières, concours aux différents selects...
	
	function appendToConcours(Concours) {
       $('#concours').append('<option value="'+Concours.id+'">'+Concours.nomConcour+'</option>')
	}
   	
   	function appendToSelects(Filieres) {
    	$('#filiere').append('<option value="'+Filieres.id+'">'+Filieres.nomFiliere+'</option>')
        $('#filiere1').append('<option value="'+Filieres.id+'">'+Filieres.nomFiliere+'</option>')
    }
    
    
   // Affichage de la liste des écoles recherchées avec leurs paramètres
   
   function appendToListEcoles(Ecoles) {
   		$("#resultat").find('li').remove()
   		if(Ecoles == null){
   			$("#resultat").append(`<li>Le concours que vous chercher n'existe pas...</li>`);
  		} else {
   			for(let valeur of Ecoles){
       	 		$("#resultat").append(`<li id="${valeur.id}"> Ecole: ${valeur.nomEcole} </li>`);
    		}
    	}
   } 
   
   // Affichage de la liste des écoles du concours recherché
   
   function appendToListStats(Ecole) {
   		$("#resultat").find('li').remove()
   		if(Ecole == null){
   			$("#resultat").append(`<li>L'école que vous chercher n'existe pas...</li>`);
  		} else {
  			$("#resultat").append(`<li id="${Ecole.id}"> Ecole: ${Ecole.nomEcole} - Inscrits: ${Ecole.inscrits} - Classés: ${Ecole.classes} - Intégrés: ${Ecole.integres}</li>`);
    	}
   } 
    
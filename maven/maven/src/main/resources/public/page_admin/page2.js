	
	// La redirection des différents onglets
	
	$(".bouton_rouge").click(function(){ window.location.href = "https://www.insa-hautsdefrance.fr/"; });
	$("li:eq(0)").click(function(){ window.location.href = "../page_accueil/index.html"; });
	$("li:eq(1)").click(function(){ window.location.href = "admin.html"; });
	$("li:eq(2)").click(function(){ window.location.href = "../page_visiteur/visiteur.html"; });
	$("li:eq(3)").click(function(){ window.location.href = "../page_contact/contact.html"; });
	
	// Ajouter une filière
	$('#my-button1').click(function(){
    	let filiere = $('#my-input1').val();
		
		if(!filiere || /^\s*$/.test(filiere)){
			return false;
		} else {
		
    	$.ajax({
    	    type: "POST",
   		    url: "http://localhost:8080/API/Filieres",
   		    data: JSON.stringify({ "nomFiliere": filiere}),
   		    contentType: "application/json; charset=utf-8",
   		    dataType: "json",
   		    
            success: function(data){
           		appendToSelects(data);  
            }
        });
        
        $('#my-input1').val('');
		return false;
		
		}
    });
    
    // Créer un concours  
	$('#my-button2').click(function(){

		let select = $("#filiere1 option:selected").text();
		let concours = $('#my-input2').val();
				
    	$.ajax({
    		type: "POST",
    		url: "http://localhost:8080/API/Filieres/createConcours/"+select+"/"+concours,
    		success: function(data){
    			appendToConcours1(concours);
    		}
 		});

    	$('#my-input2').val('');
    	return false;
   	});
	
	// Créer une école
    $('#my-button3').click(function(){
    	let filiere = $("#filiere2 option:selected").text();
    	let concours = $("#concours1 option:selected").text();
    	
    	let ecole = $('#my-input3').val();
        let nbr_inscrit = $('#my-input4').val();
        let nbr_pers_classe = $('#my-input5').val();
        let nbr_pers_integr = $('#my-input6').val();
        
        	$.ajax({
            	type: "POST",
            	url: "http://localhost:8080/API/Filieres/createEcoles/"+filiere+"/"+concours,
            	data: JSON.stringify({"nomEcole":ecole, "inscrits":nbr_inscrit, "classes":nbr_pers_classe, "integres":nbr_pers_integr}),
            	contentType: "application/json; charset=utf-8",
            	dataType: "json",
            	
            	success: function(data){
            	}
            });

            $('#my-input3').val('');
            $('#my-input4').val('');
            $('#my-input5').val('');
            $('#my-input6').val('');
            return false;
	});
	
	// Supprimer une école
	$('#my-button4').click(function(){
		let idFiliere = $("#filiere3 option:selected").val();
		let idConcours = $("#concours2 option:selected").val();
  		let idEcole = $("#ecole option:selected").val();     

        $.ajax({
            type: "DELETE",
            url: "http://localhost:8080/API/Filieres/deleteEcoles/"+idFiliere+"/"+idConcours+"/"+idEcole,
            success: function(){
            			let index = document.getElementById("ecole").selectedIndex;
            			document.getElementById("ecole").remove(index);
            		}
        });
     
        return false;
	});
	
	// Modifier une école
  	$('#my-button5').click(function(){
  		let idEcole = $("#ecole2 option:selected").val();
    	let statistique = $("#stat option:selected").text();
    	let modification = $('#my-input7').val();       

        $.ajax({
            type: "PATCH",
            url: "http://localhost:8080/API/Filieres/updateEcole/"+idEcole+"/"+statistique+"/"+modification
        });

        $('#my-input7').val('');       
        return false;
	});
	
	// On récupère toutes les filières de notre BDD et on les envoies a appendToSelects
 	$.get("http://localhost:8080/API/Filieres",function(response){
		response.forEach(r => appendToSelects(r));
   	});
   	
   	// Ajoute au select concours1 la liste des concours selon la filière précisée
	function choixAjout() {
		document.getElementById("concours1").length = 1;
        let select2 = $("#filiere2 option:selected").text();
 		$.get("http://localhost:8080/API/Filieres/getAllConcours/"+select2,function(response){ 
    		response.forEach(c => appendToConcours1(c)); 
    	});
    }
    
    // Même chose mais pour le select de la partie Suppression d'une école
    function choixSuppression() {
		document.getElementById("concours2").length = 1;
        let select3 = $("#filiere3 option:selected").text();
 		$.get("http://localhost:8080/API/Filieres/getAllConcours/"+select3,function(response){ 
    		response.forEach(c => appendToConcours2(c)); 
    	});
    }
    
    // Même chose mais pour le select de la partie Modification d'une école
    function choixModification() {
		document.getElementById("concours3").length = 1;
        let select4 = $("#filiere4 option:selected").text();
 		$.get("http://localhost:8080/API/Filieres/getAllConcours/"+select4,function(response){ 
    		response.forEach(c => appendToConcours3(c)); 
    	});
    }
	
	// Ajoute les écoles au select ecole en fonction de la filière et du concours précisé
	function choixSuppressionEcoles() {
		document.getElementById("ecole").length = 1;
        let filiere = $("#filiere3 option:selected").text();
        let concours = $("#concours2 option:selected").text();
 		$.get("http://localhost:8080/API/Filieres/getAllEcoles/"+filiere+"/"+concours,function(response){ 
    		response.forEach(c => appendToEcole(c)); 
    	});
    }
    
    // Même chose pour le select de la partie Modification d'une école
    function choixModificationEcoles() {
		document.getElementById("ecole2").length = 1;
        let filiere = $("#filiere4 option:selected").text();
        let concours = $("#concours3 option:selected").text();
 		$.get("http://localhost:8080/API/Filieres/getAllEcoles/"+filiere+"/"+concours,function(response){ 
    		response.forEach(c => appendToEcole2(c)); 
    	});
    }
    
 	// Ajoute a tous les selects les filières de notre BDD
	function appendToSelects(Filieres) {
    	$('#filiere1').append('<option value="'+Filieres.id+'">'+Filieres.nomFiliere+'</option>')
        $('#filiere2').append('<option value="'+Filieres.id+'">'+Filieres.nomFiliere+'</option>')
        $('#filiere3').append('<option value="'+Filieres.id+'">'+Filieres.nomFiliere+'</option>')
        $('#filiere4').append('<option value="'+Filieres.id+'">'+Filieres.nomFiliere+'</option>')
    }
    
    // Même chose pour les écoles, concours...
    
    function appendToConcours1(Concours) {
       $('#concours1').append('<option value="'+Concours.id+'">'+Concours.nomConcour+'</option>')
	}
	
	function appendToConcours2(Concours) {
       $('#concours2').append('<option value="'+Concours.id+'">'+Concours.nomConcour+'</option>')
	}
	
	function appendToConcours3(Concours) {
       $('#concours3').append('<option value="'+Concours.id+'">'+Concours.nomConcour+'</option>')
	}

    function appendToEcole(Ecoles) {
        $('#ecole').append('<option value="'+Ecoles.id+'">'+Ecoles.nomEcole+'</option>')
	};	
	
	function appendToEcole2(Ecoles) {     
	    $('#ecole2').append('<option value="'+Ecoles.id+'">'+Ecoles.nomEcole+'</option>')
	};		
	


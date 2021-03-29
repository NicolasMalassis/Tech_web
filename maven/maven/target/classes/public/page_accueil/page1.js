	
	// La redirection des différents onglets
	
	$(".bouton_rouge").click(function(){ window.location.href = "https://www.insa-hautsdefrance.fr/"; });
	$("li:eq(0)").click(function(){ window.location.href = "../page_accueil/index.html"; });	
	$("li:eq(2)").click(function(){ window.location.href = "../page_visiteur/visiteur.html"; });
	$("li:eq(3)").click(function(){ window.location.href = "../page_contact/contact.html"; });
	
	// Pour récupérer les éléments de connexion administrateur	
	
	$("#connexion").click(function(){
		let nom = $('#nom').val();
		let mdp = $('#mdp').val();
		sessionStorage.setItem('nom', nom);
		sessionStorage.setItem('mdp', mdp); 
		$('#nom').val('');
		$('#mdp').val('');
	});
	
	// Si jamais on clique sur l'onglet admin, on vérifie les nom et mdp, si ils sont bon on peut y accéder par cette page sinon retour à la page courante
	
	$("li:eq(1)").click(function(){	
		if(sessionStorage.getItem('nom') === "admin" && sessionStorage.getItem('mdp') === "1234"){
			window.location.href = "../page_admin/admin.html";
		} else {
			window.location.href = "../page_accueil/index.html";
		}		
	});


	
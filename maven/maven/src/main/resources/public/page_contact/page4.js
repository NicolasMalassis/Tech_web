	
	// La redirection des différents onglets
	
	$(".bouton_rouge").click(function(){ window.location.href = "https://www.insa-hautsdefrance.fr/"; });
	$("li:eq(0)").click(function(){ window.location.href = "../page_accueil/index.html"; });
	$("li:eq(2)").click(function(){ window.location.href = "../page_visiteur/visiteur.html"; });
	$("li:eq(3)").click(function(){ window.location.href = "contact.html"; });
	
	// Partie session, accès a administrateur
	
	let nom = sessionStorage.getItem('nom');
	let mdp = sessionStorage.getItem('mdp');
	
	$("li:eq(1)").click(function(){
		if(nom === "admin" && mdp === "1234"){
			window.location.href = "../page_admin/admin.html";
		} else {
			window.location.href = "../page_contact/contact.html";
		}
	});
	

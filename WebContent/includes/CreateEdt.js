//importation des fichiers js 
var imported = document.createElement('script');
imported.src = '/Schedulator/includes/addBloc.js';
document.head.appendChild(imported);

var listDeBloc = [];
var listeHoraire = []
//var unBloc = {duree: ""; hDeb= ""; hFin = ""; jour = ""} + plusieur fois le meme bloc => choix

$(document).ready(function(){    
    $("#add").click(function(){
        $("#add").replaceWith(createAddParam());
   });
});

/*
 * Fonction declanche apres le clique du bouton 'add'
 * elle permet de rentrer le nom et choisir le type de bloc
 * 
 */

function createAddParam(){
	//div principal
	
	if(document.getElementById("val") != null){
		document.getElementById('val').remove();
	}
	
	var divAddParam = document.createElement('div');
	divAddParam.id = 'addParam';
	
	//div (label + input)
	var divLabInput = document.createElement('div');
	divLabInput.className = 'form-group';
	//label
	var label = document.createElement('label');
	label.htmlFor = 'addParamName';
	label.innerHTML = "Nom du bloc : ";
	$(label).appendTo($(divLabInput));
	//input
	var input = document.createElement('input');
	input.type = 'text';
	input.id = "addParamName";
	input.className = "form-control";
	$(input).appendTo($(divLabInput));
	//ajout du div (l + i) au div principal 
	$(divLabInput).appendTo($(divAddParam));

	//div select
	var divSelect = document.createElement('div');
	divSelect.className	 = 'form-group';
	//select
	var selectText = ["Selectionner un type de bloc", "Fixe", "A choix", "Libre"]; //text
	var selectValue = ["", "fixe", "choix", "libre"];	//value
	var selectList = document.createElement("select"); //le select
	selectList.id = "addParamCombo";
	selectList.className ="form-control";
	selectList.onchange = function(){changeCombo();};
	//selectList.onchange();
	divSelect.appendChild(selectList);

	for (var i = 0; i < selectText.length; i++) {
	    var option = document.createElement("option");
	    option.value = selectValue[i];
	    option.text = selectText[i];
	    selectList.appendChild(option);
	}
	$(divSelect).appendTo($(divAddParam)); 
	var br = document.createElement("br");
	$(br).appendTo($(divSelect));
	
	return divAddParam;
}


function refreshListBloc(){

	var divListBlocAffichage = document.createElement('div');
	for (i = 0; i < listDeBloc.length ; i++){
		var x = document.createElement("li");
    	x.className = "list-group-item";
    	x.innerHTML = listDeBloc[i];
    	$(x).appendTo($(divListBlocAffichage));
    }
    $("#divListBloc").replaceWith(divListBlocAffichage);
    divListBlocAffichage.id = "divListBloc";
}

function refreshListHoraire(){

	var divListBlocAffichage = document.createElement('div');
	for (i = 0; i < listeHoraire.length ; i++){
    	var x = document.createElement("li");
    	x.className = "list-group-item";
    	x.innerHTML = listeHoraire[i];
    	$(x).appendTo($(divListBlocAffichage));
    }
    $("#divListHoraire").replaceWith(divListBlocAffichage);
    divListBlocAffichage.id = "divListHoraire";
}

//creer le formulaire d'ajout d'un bloc


//changement du combobox
function changeCombo(){
	listeHoraire = [];
	//supprimer le div de cration de bloc
	if(document.getElementById("createBloc") != null){
		document.getElementById('createBloc').remove();
	}
	//supprime le bouton "valider le bloc"
	if(document.getElementById("addParamBtn") != null){
		document.getElementById('addParamBtn').remove();
	}
	//supprime el bouton ajouter un creneaux
	if(document.getElementById("addCreneauxBtn") != null){
		document.getElementById('addCreneauxBtn').remove();
	}
	
	//on recuper le choix du combo box
    var x = document.getElementById("addParamCombo").selectedIndex;
    var val = document.getElementsByTagName("option")[x].value;
    
    switch (val) { //en fonction du choix du combo box :
	case "fixe":
		$("#addParamCombo").after(blocFixe("fixe"));
        //creation du bouton de validation du bloc "valider le bloc"
    	var addParamBtn = document.createElement('input');
    	addParamBtn.id = "addParamBtn";
    	addParamBtn.type = "button";
    	addParamBtn.value = "Valider le bloc";
    	addParamBtn.onclick = function(){submitBloc();};
    	$(addParamBtn).appendTo($(addParam));
		break;
		
	case "choix":
		$("#addParamCombo").after(blocFixe("fixechoix"));
        //button validation du crenaux
    	
		break;
	case "libre":
		$("#addParamCombo").after(blocFixe("libre"));

		break;

	default:
		
		break;
	}
}

//clic pour valider la saisie du bloc
function submitBloc(){
	var err = [];
	
	if(document.getElementById("addParamName").value == ""){
		err[0] = [1];
		document.getElementById("addParamName").style.border = "1px solid red";
		alert("Le nom du bloc ne peut pas rester vide");
	}else{
		document.getElementById("addParamName").style.border = "1px solid green";
	}
	
	if(document.getElementById("duree").value == ""){
		err[1] = [1];
		alert("La durée du bloc ne peut pas rester vide");
		document.getElementById("duree").style.border = "1px solid red";
	}else{
		document.getElementById("duree").style.border = "1px solid green";
	}
	if(document.getElementById("heureDebutH") != null){
			
		if(document.getElementById("heureDebutH").value == "" || document.getElementById("heureDebutMin").value == ""){
			err[2] = [1];
			document.getElementById("heureDebutH").style.border = "1px solid red";
			document.getElementById("heureDebutMin").style.border = "1px solid red";
			alert("L'heure de debut ne peut pas rester vide");
		}else{
			document.getElementById("heureDebutH").style.border = "1px solid green";
			document.getElementById("heureDebutMin").style.border = "1px solid green";		
		}
		if(document.getElementById("heureFinH").value == "" || document.getElementById("heureFinMin").value == ""){
			err[3] = [1];
			document.getElementById("heureFinH").style.border = "1px solid red";
			document.getElementById("heureFinMin").style.border = "1px solid red";
			alert("L'heure de fin ne peut pas rester vide");
		}else{
			document.getElementById("heureFinH").style.border = "1px solid green";
			document.getElementById("heureFinMin").style.border = "1px solid green";		}
		
		var e = document.getElementById("blocFixeCombo");
	    var jour = e.options[e.selectedIndex].value;
		if(jour == ""){
			err[4] = [1];
			document.getElementById("blocFixeCombo").style.border = "1px solid red";
			alert("Veuillez selectionner un jour");
		}else{
			document.getElementById("blocFixeCombo").style.border = "1px solid green";

		}
	}
	if(err.length > 0 ){
		return;
	}
	
    
			
	addCreneaux("validation");
	var blocNom = document.getElementById("addParamName").value;
	var blocDuree = document.getElementById("duree").value;
	var z = listDeBloc.length;
	listDeBloc[z] = [blocNom, blocDuree];
	listDeBloc[z] = listDeBloc[z].concat(listeHoraire);
	listeHoraire = [];

	refreshListBloc();

	var addParamBtn = document.createElement('input');
	addParamBtn.id = "add";
	addParamBtn.type = "button";
	addParamBtn.value = "+";
	addParamBtn.onclick = function(){$("#add").replaceWith(createAddParam());};
    $("#addParam").replaceWith(addParamBtn);

    var valider = document.createElement('input');
    valider.id = "val";
    valider.type = "button";
    valider.value = "Valider ";
    valider.onclick = function(){
    	validationRedirect();
	};
	
    $("#add").after($(valider));
}

function validationRedirect(){
	var stringBlocs = "";
	var stringTmp = "";
	for (var i = 0 ; i < listDeBloc.length ; i++){
		for (var j = 0 ; j < listDeBloc[i].length ; j ++){
			stringTmp += listDeBloc[i][j];
			stringTmp += "-";
		}
		stringBlocs += stringTmp;
		stringTmp = "";
		stringBlocs += ";";
	}
	
	var f = document.createElement("form");
	f.setAttribute('method',"post");
	f.setAttribute('action',"CreateEdt");
	f.style.display = "none";

	var i = document.createElement("input"); //input element, text
	i.setAttribute('type',"text");
	i.setAttribute('name',"stringBlocs");
	i.setAttribute('value',stringBlocs);
	i.style.display = "none";
	f.appendChild(i);
	document.body.append(f);
	f.submit();

}


//addCreneaux ("fixe") => pour ajouter hdeb hfin et jour a la liste 
//addcreneaux("choix") => pour ajouter plusieur crenaux
//"validation" => ne remplis pas al list d'ho
function addCreneaux(type){
	
	if(document.getElementById("heureDebutH") != null && type == "validation"){
		alert("pas verifier ! ");
		var heureDebutInputH = document.getElementById("heureDebutH").value;
		var heureDebutInputMin = document.getElementById("heureDebutMin").value;

		var heureFinInputH = document.getElementById("heureFinH").value;
		var heureFinInputMin  = document.getElementById("heureFinMin").value;

		var e = document.getElementById("blocFixeCombo");
	    var jour = e.options[e.selectedIndex].value;
	    
		//+ selecteed index
	    var strDeb = heureDebutInputH+"H"+heureDebutInputMin;
	    var strFin = heureFinInputH+"H"+heureFinInputMin;

	    listeHoraire.push(strDeb, strFin, jour);
		    
		refreshListHoraire();
	}
	

	if (type == "choix"){
		var err = [];
		if(document.getElementById("heureDebutH").value == "" || document.getElementById("heureDebutMin").value == ""){
			err[2] = [1];
			document.getElementById("heureDebutH").style.border = "1px solid red";
			document.getElementById("heureDebutMin").style.border = "1px solid red";
			alert("L'heure de debut ne peut pas rester vide");
		}else{
			document.getElementById("heureDebutH").style.border = "1px solid green";
			document.getElementById("heureDebutMin").style.border = "1px solid green";		
		}
		if(document.getElementById("heureFinH").value == "" || document.getElementById("heureFinMin").value == ""){
			err[3] = [1];
			document.getElementById("heureFinH").style.border = "1px solid red";
			document.getElementById("heureFinMin").style.border = "1px solid red";
			alert("L'heure de fin ne peut pas rester vide");
		}else{
			document.getElementById("heureFinH").style.border = "1px solid green";
			document.getElementById("heureFinMin").style.border = "1px solid green";		}
		
		var e = document.getElementById("blocFixeCombo");
	    var jour = e.options[e.selectedIndex].value;
		if(jour == ""){
			err[4] = [1];
			document.getElementById("blocFixeCombo").style.border = "1px solid red";
			alert("Veuillez selectionner un jour");
		}else{
			document.getElementById("blocFixeCombo").style.border = "1px solid green";

		}
		
		if(err.length > 0 ){
			return;
		}

		
		var heureDebutInputH = document.getElementById("heureDebutH").value;
		var heureDebutInputMin = document.getElementById("heureDebutMin").value;

		var heureFinInputH = document.getElementById("heureFinH").value;
		var heureFinInputMin  = document.getElementById("heureFinMin").value;
	//+ selecteed index
	    var strDeb = heureDebutInputH+"H"+heureDebutInputMin;
	    var strFin = heureFinInputH+"H"+heureFinInputMin;

	    listeHoraire.push(strDeb, strFin, jour);
		    
		refreshListHoraire();
		
		document.getElementById('divLabInputHeureDebut').remove();
		document.getElementById('divLabInputHeureFin').remove();
		document.getElementById('divSelectblocFixe').remove();
		document.getElementById('addCreneauxBtn').remove();
		var br = document.createElement("br");
		br.id ="brCreneaux"
		$("#divListHoraire").after($(br));
		var subCreneauxBtn = document.createElement('input');
		subCreneauxBtn.id = "subCreneauxBtn";
		subCreneauxBtn.type = "button";
		subCreneauxBtn.value = "Ajouter un creneau";
		subCreneauxBtn.onclick = function(){
			this.remove();
			document.getElementById('addParamBtn').remove();
			$("#brCreneaux").after(blocFixe("choix"));
		};
		$("#brCreneaux").after($(subCreneauxBtn));
		
		var addParamBtn = document.createElement('input');
		addParamBtn.id = "addParamBtn";
		addParamBtn.type = "button";
		addParamBtn.value = "Valider le bloc";
		addParamBtn.onclick = function(){submitBloc();};
		$("#brCreneaux").after($(addParamBtn));
			
	}
	

	



	


}
























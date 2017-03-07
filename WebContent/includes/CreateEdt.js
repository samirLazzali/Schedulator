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
	//manque le placeholder !
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
	
	if(document.getElementById("createBloc") != null){
		document.getElementById('createBloc').remove();
	}
	if(document.getElementById("addParamBtn") != null){
		document.getElementById('addParamBtn').remove();
	}
	if(document.getElementById("addCreneauxBtn") != null){
		document.getElementById('addCreneauxBtn').remove();
	}
	
    var x = document.getElementById("addParamCombo").selectedIndex;
    var val = document.getElementsByTagName("option")[x].value;
    
    switch (val) {
	case "fixe":
		
		$("#addParamCombo").after(blocFixe("fixe"));
        //button validation du bloc
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
		//alert("librette");
		$("#addParamCombo").after(blocFixe("libre"));

		break;

	default:
		
		break;
	}
}

//clic pour valider la saisie du bloc
function submitBloc(){
	addCreneaux();

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
		stringBlocs += ";";
	}
	
	var f = document.createElement("form");
	f.setAttribute('method',"post");
	f.setAttribute('action',"CreateEdt");

	var i = document.createElement("input"); //input element, text
	i.setAttribute('type',"text");
	i.setAttribute('name',"stringBlocs");
	i.setAttribute('value',stringBlocs);
	f.appendChild(i);
	f.submit();

}


//addCreneaux ("fixe") => pour ajouter hdeb hfin et jour a la liste 
//addcreneaux("choix") => pour ajouter plusieur crenaux
function addCreneaux(type){
	//incremente une liste d'horaire
	// et permet d'en ajouter une autre
	if (document.getElementById("heureDebut") != null){
		var heureDebutInput = document.getElementById("heureDebut").value;
		var heureFinInput = document.getElementById("heureFin").value;
		var e = document.getElementById("blocFixeCombo");
	    var jour = e.options[e.selectedIndex].value;
	    
		//+ selecteed index
		listeHoraire.push(heureDebutInput, heureFinInput, jour);
		refreshListHoraire();
	}

	if (type == "choix"){
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
			
	};
	

	



	


}
























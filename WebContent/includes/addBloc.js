
/*
 * Code a afficher en fonction du type de bloc choisit 
 * si type == fixe => duree + hdeb + hfin + jour
 * si type == choix => hdeb + hfin + jour + btn
 * si type == fixechoix => duree + hdeb + hfin + jour + btn
 * 		      libre
 */
//pour le bloc fixe : 
function blocFixe(type){
		
	var divBlocFixe = document.createElement('div');
	divBlocFixe.id = 'createBloc';
	/*
	 * <div class="form-group">
                <div class='input-group date' id='datetimepicker3'>
                    <input type='text' class="form-control" />
                    <span class="input-group-addon">
                        <span class="glyphicon glyphicon-time"></span>
                    </span>
                </div>
            </div>
	 */
	if (type == "fixe" || type == "fixechoix" || type == "libre"){	

		
		//div label + input durée 
		var divLabInputDuree = document.createElement('div');
		divLabInputDuree.className = 'form-group';
		divLabInputDuree.id = "divLabInputDuree";

		var label = document.createElement('label');
		label.htmlFor = 'duree';
				label.innerHTML = "Durée : ";
		$(label).appendTo($(divLabInputDuree));
		//input
		var input = document.createElement('input');
		input.type = 'number';
		input.id = "duree";
		input.className = "form-control";

		$(input).appendTo($(divLabInputDuree));
		//manque le placeholder !
		//ajout du div (l + i) au div principal 
		$(divLabInputDuree).appendTo($(divBlocFixe));
		
		var divListHoraire = document.createElement('div');
		divListHoraire.id = 'divListHoraire';
		$(divListHoraire).appendTo($(divBlocFixe));

	}

	//div label + input HeureDebut 
	var divLabInputHeureDebut = document.createElement('div');
	divLabInputHeureDebut.className = 'form-group';
	divLabInputHeureDebut.id = "divLabInputHeureDebut";
	//label
	var label = document.createElement('label');
	label.htmlFor = 'heureDebut';
	if(type == "libre"){
		label.innerHTML = "Debut de la plage horaire : ";

	}else {
		label.innerHTML = "Heure de debut : ";
	}
	$(label).appendTo($(divLabInputHeureDebut));
	//input
	var divNum1 = document.createElement('div');
	divNum1.className = "row";
	
	var input = document.createElement('input');
	input.type = 'number';
	input.id = "heureDebutH";
	input.min= '0';
	input.max="23";
	input.step ="1";

	$(input).appendTo($(divNum1));
	divNum1.append("H");
	var input = document.createElement('input');
	input.type = 'number';
	input.id = "heureDebutMin";
	input.min= '0';
	input.max="45";
	input.step ="15";
	$(input).appendTo($(divNum1));

	$(divNum1).appendTo($(divLabInputHeureDebut));
	//manque le placeholder !
	//ajout du div (l + i) au div principal 
	$(divLabInputHeureDebut).appendTo($(divBlocFixe));

	//div label + input HeureFin 
	var divLabInputHeureFin = document.createElement('div');
	divLabInputHeureFin.className = 'form-group';
	divLabInputHeureFin.id = "divLabInputHeureFin";

	var label = document.createElement('label');
	label.htmlFor = 'heureFin';
	if(type == "libre"){
		label.innerHTML = "Fin de la plage horaire : ";

	}else {
		label.innerHTML = "Heure de fin : ";
	}
	$(label).appendTo($(divLabInputHeureFin));
	//input
	
	var divNum1 = document.createElement('div');
	divNum1.className = "row";
	
	var input = document.createElement('input');
	input.type = 'number';
	input.id = "heureFinH";
	input.min= '0';
	input.max="23";
	input.step ="1";
	$(input).appendTo($(divNum1));
	divNum1.append("H");
	var input = document.createElement('input');
	input.type = 'number';
	input.id = "heureFinMin";
	input.min= '0';
	input.max="45";
	input.step ="15";
	$(input).appendTo($(divNum1));

	$(divNum1).appendTo($(divLabInputHeureFin));
	
	$(divLabInputHeureFin).appendTo($(divBlocFixe));

	var divSelect = document.createElement('div');
	divSelect.className	 = 'form-group';
	divSelect.id = "divSelectblocFixe"
	//select
	var selectText = ["Selectionner un jour", "Lundi", "Mardi", "Mercredi" ,"Jeudi" ,"Vendredi" ,"Samedi" ,"Dimanche"]; //text
	var selectValue = ["", "lundi", "mardi", "mercredi" ,"jeudi" ,"vendredi" ,"samedi" ,"dimanche"];	//value
	var selectList = document.createElement("select"); //le select
	selectList.id = "blocFixeCombo";
	selectList.className ="form-control";
	selectList.onchange = function(){};
	divSelect.appendChild(selectList);

	//selectList.onchange();

	for (var i = 0; i < selectText.length; i++) {
	    var option = document.createElement("option");
	    option.value = selectValue[i];
	    option.text = selectText[i];
	    selectList.appendChild(option);
	}
	
	$(divSelect).appendTo($(divBlocFixe)); 
	if (type == "choix" || type == "fixechoix" || type == "libre"){

		var addCreneauxBtn = document.createElement('input');
    	addCreneauxBtn.id = "addCreneauxBtn";
    	addCreneauxBtn.type = "button";
    	addCreneauxBtn.value = "Valider le créneaux";
    	addCreneauxBtn.onclick = function(){addCreneaux("choix");};
    	$(addCreneauxBtn).appendTo($(divBlocFixe));
	}

	
	//ajout du div (l + i) au div principal 

	return divBlocFixe;
}




































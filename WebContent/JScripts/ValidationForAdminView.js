function CheckRegularExpression(inputtxt, regxp)
{
	if(inputtxt.match(regxp)){
		return true;
	}
	else{
		return false;
	}
}

function CheckRimuovi(qualeform){
	console.log("funzione chiamata");
	
	if (qualeform == "prodotto"){ //vede se il form è di prodotto
		console.log("ha inserito prodotto");
		
		if (CheckRegularExpression(document.getElementById("codice_prodotto").value, /^[0-9]{13}$/) == true) //vede se c'è match nell'espressione regolare 
		{ 
			
			if (confirm('Sicuro di voler rimuovere quel prodotto dal magazzino?')) //se clicca su Ok...
			{
				
				document.getElementById('moduloProdotto').submit();  //...invia il form
				
				return true;
			}
		}
		
		else {
			
				document.forms["moduloProdotto"]["nome_prodotto"].focus();
				
		    	document.getElementById("erroreCodiceProdotto").innerHTML = "<i> ! Prego, voglia dare un indentificativo al prodotto da aggiungere <br> L'identificativo deve essere numerico e di 13 cifre max </i>";
		    	
		    	return false;
			}
	}
	
	else if (qualeform == "cliente"){
		console.log("ha inserito cliente");
		
			if (CheckRegularExpression(document.getElementById("email").value, /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,7})+$/) == true) 
			{ 
				if (confirm('Sicuro di voler rimuovere quel cliente dal magazzino?')) 
				{
					
					document.getElementById('moduloProdotto').submit(); 
					
					return true;
				}
			}
			
			else {
					
				document.forms["moduloCliente"]["email"].focus();
				document.getElementById("erroreEmail").innerHTML = "<i> ! Prego, voglia inserire l'email dell'account</i>";
				
			 	return false;
			}
	}
	
	else return false;	
}

var CheckFormProdotto= function()
{
	//Il "blocco di dichiarazioni" segue lo schema
	//x <-- tutto il form
	//variabile <-- regex 
	//variabile <-- modulo del dom con valore
	
	
	let x = document.getElementById("moduloProdotto");
	var codiceprodotto_expr = /^[0-9]{13}$/;
	let codice_prodotto = document.forms["moduloProdotto"]["codice_prodotto"].value;
	document.getElementById("erroreCodiceProdotto").innerHTML = "";
	document.getElementById("errorePercentualeSconto").innerHTML = "";
	let nome_prodotto = document.forms["moduloProdotto"]["nome_prodotto"].value;
	document.getElementById("erroreNomeProdotto").innerHTML = "";
	var nome_brand_anycat_expr = /^[a-zA-Z-\s]{1,50}+$/;
	let prezzo_netto = document.forms["moduloProdotto"]["prezzo_netto"].value;
	document.getElementById("errorePrezzoNetto").innerHTML = "";
	var prezzonetto_expr = /^[0-9]*\.[0-9]{2}$/; 
	let percentuale_di_sconto = document.forms["moduloProdotto"]["percentuale_di_sconto"].value;
	document.getElementById("erroreDescrizioneBreve").innerHTML = "";
	var percentuale_expr = /^[0-9]+$/;
	let descrizione_breve = document.forms["moduloProdotto"]["descrizione_breve"].value;
	document.getElementById("erroreDescrizioneDettagliata").innerHTML = "";
	var descrizione_expr = /^[a-zA-Z0-9-\s]{1,500}+$/;
	let descrizione_dettagliata = document.forms["moduloProdotto"]["descrizione_dettagliata"].value;
	document.getElementById("erroreBrand").innerHTML = "";
	let quantitadisponibile = document.forms["moduloProdotto"]["quantitadisponibile"].value;
	var quantitadisponibile_expr = /^[0-9]+$/;
	document.getElementById("erroreQuantitaDisponibile").innerHTML = "";
	let brand = document.forms["moduloProdotto"]["brand"].value;
	let microcat = document.forms["moduloProdotto"]["microcat"].value; 
	document.getElementById("erroreMacrocat").innerHTML = "";
	let macrocat = document.forms["moduloProdotto"]["macrocat"].value; 
	document.getElementById("erroreMicrocat").innerHTML = "";
	let Linkimmagine = document.forms["moduloProdotto"]["Linkimmagine"].value;
	document.getElementById("erroreLinkImmagine").innerHTML = "";
	
	//gli if verificano se non ci sono match
	if (codice_prodotto == "" || CheckRegularExpression(codice_prodotto, codiceprodotto_expr) == false){
		document.forms["moduloProdotto"]["codice_prodotto"].focus();
		
	    document.getElementById("erroreCodiceProdotto").innerHTML = "<i> ! Prego, voglia dare un indentificativo al prodotto da aggiungere <br> L'identificativo deve essere numerico e di 13 cifre max </i>";
	    return false;
	}
																			//nome_brand perchè può essere max 50 caratt. ed è di tipo alfanumerico
	else if (nome_prodotto == "" || CheckRegularExpression(nome_prodotto, nome_brand_anycat_expr) == false){
		
		document.forms["moduloProdotto"]["nome_prodotto"].focus();
	    document.getElementById("erroreNomeProdotto").innerHTML = "<i> ! Prego, voglia dare un nome al prodotto da aggiungere</i>";
	 	return false;
	}
	
	else if (prezzo_netto == "" || CheckRegularExpression(prezzo_netto, prezzonetto_expr) == false){
		
		document.forms["moduloProdotto"]["prezzo_netto"].focus();
		 document.getElementById("errorePrezzoNetto").innerHTML = "<i> ! Prego, voglia dare un prezzo al prodotto da aggiungere, specificando la parte decimale (almeno due cifre dopo la vigola)</i>";
	 	return false;
	}
	
	else if (percentuale_di_sconto == "" || CheckRegularExpression(percentuale_di_sconto, percentuale_expr) == false){
		document.forms["moduloProdotto"]["percentuale_di_sconto"].focus();
		 document.getElementById("errorePercentualeSconto").innerHTML = "<i> ! Prego, voglia dare almeno 0 come sconto</i>";
	 	return false;
	}
	
	else if (quantitadisponibile == "" || CheckRegularExpression(quantitadisponibile, descrizione_expr) == false){
		document.forms["moduloProdotto"]["quantitadisponibile"].focus();
		 document.getElementById("erroreQuantitaDisponibile").innerHTML = "<i> ! Prego, voglia inserire almeno 0 come quantità disponibile</i>";
	 	return false;
	}
	
	else if (descrizione_breve == "" || CheckRegularExpression(descrizione_breve, descrizione_expr) == false){
		document.forms["moduloProdotto"]["descrizione_breve"].focus();
		 document.getElementById("erroreDescrizioneBreve").innerHTML = "<i> ! Prego, voglia dare una breve descrizione del prodotto da aggiungere</i>";
	 	return false;
	}
	

	else if (descrizione_dettagliata == "" || CheckRegularExpression(descrizione_dettagliata, descrizione_expr) == false){
		document.forms["moduloProdotto"]["descrizione_dettagliata"].focus();
		 document.getElementById("erroreDescrizioneDettagliata").innerHTML = "<i> ! Prego, voglia dare una descrizione dettagliata al prodotto da aggiungere</i>";
	 	return false;
	}
		
	else if (brand == "" || CheckRegularExpression(brand, nome_brand_anycat_expr) == false){
		document.forms["moduloProdotto"]["brand"].focus();
		 document.getElementById("erroreBrand").innerHTML = "<i> ! Prego, inserisca un brand valido</i>";
	 	return false;
	}
																//nome_brand perchè entrambe sono di max 50 caratt. e di tipo alfanumerico
	else if (microcat == "" || CheckRegularExpression(microcat, nome_brand_anycat_expr) == false){
		document.forms["moduloProdotto"]["microcat"].focus();
		 document.getElementById("erroreMicrocat").innerHTML = "<i> ! Prego, voglia associare il prodotto ad una microcategoria valido</i>";
	 	return false;
	}
	
	else if (macrocat == "" || CheckRegularExpression(macrocat, nome_brand_anycat_expr) == false){
		document.forms["moduloProdotto"]["macrocat"].focus();
		 document.getElementById("erroreMacrocat").innerHTML = "<i> ! Prego, voglia associare il prodotto ad una macrocategoria</i>";
	 	return false;
	}
	
	else if (Linkimmagine == ""){
		document.forms["moduloProdotto"]["Linkimmagine"].focus();
		 document.getElementById("erroreLinkImmagine").innerHTML = "<i> ! Prego, voglia associare al prodotto una foto</i>";
	 	return false;
	}
	
	else {
		
		if (confirm('Sicuro di voler aggiungere quel prodotto nel magazzino?') == true){
			x.submit();
			return true;
		}	
		 else return false;
	 }
}


function CheckFormCliente()
{
	//Il "blocco di dichiarazioni" segue lo schema
	//x <-- tutto il form
	//variabile <-- modulo del dom con valore
	//variabile <-- regex 
	
	
	
	let x = document.getElementById("moduloCliente");
	let email = document.forms["moduloCliente"]["email"].value;
	document.getElementById("erroreEmail").innerHTML = "";
	var email_expr = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,7}){1,256}+$/;
	let password = document.forms["moduloCliente"]["password"].value;
	document.getElementById("errorePass").innerHTML = "";
	var password_expr = /^[a-zA-Z0-9^!@#$%^&?*()]{6,256}$/;
	let nome_utente = document.forms["moduloCliente"]["nome_utente"].value;
	document.getElementById("erroreNomeUtente").innerHTML = "";
	let cognome_utente = document.forms["moduloCliente"]["cognome_utente"].value;
	document.getElementById("erroreCognomeUtente").innerHTML = "";
	var cognome_nome_expr = /^[A-Za-z-\s]{1,20}+$/;
	let pf = document.forms["moduloCliente"]["punti_fedelta"].value;
	document.getElementById("errorePf").innerHTML = "";
	var pf_exp = /^[0-9]+$/;
	
	
	//valori del campo e regex vengono confrontati se non ci sono match
	if (email == "" || CheckRegularExpression(email, email_expr) == false){
		document.forms["moduloCliente"]["email"].focus();
		 document.getElementById("erroreEmail").innerHTML = "<i> ! Prego, voglia inserire l'email dell'account</i>";
	 	return false;
	}
	
	else if (password == "" || CheckRegularExpression(password, password_expr) == false){
		document.forms["moduloCliente"]["password"].focus();
		 document.getElementById("errorePass").innerHTML = "<i> ! Prego, voglia inserire la password dell'utente (almeno 6 caratteri senza caratteri accentati)</i>";
	 	return false;
	}
	
	else if (nome_utente == "" || CheckRegularExpression(nome_utente, cognome_nome_expr) == false){
		document.forms["moduloCliente"]["nome_utente"].focus();
		 document.getElementById("erroreNomeUtente").innerHTML = "<i> ! Prego, voglia inserire il nome dell'utente</i>";
	 	return false;
	}
	
	else if (cognome_utente == "" || CheckRegularExpression(cognome_utente, cognome_nome_expr) == false){
		document.forms["moduloCliente"]["cognome_utente"].focus();
		 document.getElementById("erroreCognomeUtente").innerHTML = "<i> ! Prego, voglia inserire il cognome dell'utente</i>";
	 	return false;
	}
	
	else if (pf == "" || CheckRegularExpression(pf, pf_exp) == false){
		document.forms["moduloCliente"]["pf"].focus();
		 document.getElementById("errorePf").innerHTML = "<i> ! Prego, voglia inserire almeno 0 punti fedeltà</i>";
	 	return false;
	}
	
	else {
		if(confirm('Sicuro di voler aggiungere questo cliente?')){
		 x.submit();
		 return true;
		}
		else return false;
	 }
}

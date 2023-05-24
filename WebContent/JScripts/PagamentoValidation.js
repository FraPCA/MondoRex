
function CheckRegularExpression(inputtxt, regxp)
{
	if(inputtxt.match(regxp)){
		return true;
	}
	else{
		return false;
	}
}


function validatePayForm(){
	 let myForm = document.getElementById("form2");
	
	 let num_carta = document.forms["form2"]["numcarta"].value;
	 var num_carta_exp =  /^[3-6]+[0-9]{15}$/;
	 document.getElementById("numcartaError").innerHTML = "" ;
	 let cvc =  document.forms["form2"]["CVCCVV"].value;
	 var cvc_exp =  /^\d{3}$/;
	 document.getElementById("cvccvvError").innerHTML = "";
	 let scadenza_mese = document.forms["form2"]["mese"].value;
	 var scadenza_mese_exp = /^([1-9]|0[1-9]|1[012])$/;
	 document.getElementById("meseError").innerHTML = "";
	 let scadenza_anno = document.forms["form2"]["anno"].value;
	 var scadenza_anno_exp = /^\d{2}$/;
	 document.getElementById("annoError").innerHTML = "";
	 let codicefiscale = document.forms["form2"]["cf"].value;
	 var codice_fiscale_exp = /^[A-Z]{6}\d{2}[A-Z]\d{2}[A-Z]\d{3}[A-Z]$/;
	 document.getElementById("cfError").innerHTML = "";
	 let phone = document.forms["form2"]["phone"].value;
	 var phone_exp = /^\d{10}$/;
	 document.getElementById("ntelError").innerHTML = "";
	 let regione = document.forms["form2"]["regione"].value;
	 var regione_exp = /^[a-zA-Z-\s]+$/;
	 document.getElementById("regioneError").innerHTML = "";
	 let provincia = document.forms["form2"]["provincia"].value;
	 var provincia_exp = /^[a-zA-Z]+$/;
	 document.getElementById("provinciaError").innerHTML = "";
	 let via = document.forms["form2"]["via"].value;
	 var via_exp = /^[a-zA-Z-\s-\d]+$/; // \s per il carattere vuoto
	 document.getElementById("viaError").innerHTML = "";
	 let CAP = document.forms["form2"]["CAP"].value;
	 var CAP_exp = /^\d{5}$/;
	 document.getElementById("capError").innerHTML = "";
	 let nazione = document.forms["form2"]["nazione"].value;
	 var nazione_exp =  /^[a-zA-Z-\s]+$/;
	 document.getElementById("nazioneError").innerHTML = "";
	 let pf = document.forms["form2"]["pf"].value;
	 var pf_exp = /^\d{1,}$/;
	 document.getElementById("pfError").innerHTML = "";
	 
	 
	 if (num_carta == "" || num_carta == null || CheckRegularExpression(num_carta, num_carta_exp) == false){
		 document.forms["form2"]["numcarta"].focus();
	 		document.getElementById("numcartaError").innerHTML = "<i> ! Prego, inserisca un numero di carta valido</i>" ;
	 	return false;
	 }
	 
	 else if  (cvc == "" || cvc == null || CheckRegularExpression(cvc, cvc_exp) == false){
		 document.forms["form2"]["CVCCVV"].focus();
		 document.getElementById("cvccvvError").innerHTML = "<i> ! Prego, inserisca il CVC-CVV della carta scelta (deve essere valido)</i>";
	 	return false;

	 } 
	 else if  (scadenza_mese == "" || CheckRegularExpression(scadenza_mese, scadenza_mese_exp) == false || scadenza_mese == null){
		 document.forms["form2"]["mese"].focus();
		 document.getElementById("meseError").innerHTML = "<i> ! Prego, inserisca il mese presente sulla carta </i>";
		 return false;
	 }
	 else if  (scadenza_anno == "" || scadenza_anno == null || CheckRegularExpression(scadenza_anno, scadenza_anno_exp) == false){ 
		 document.forms["form2"]["anno"].focus();
		 document.getElementById("annoError").innerHTML = "<i> ! Prego, inserisca l'anno presente sulla carta </i>";
			 return false;
	 }
	 else if  (codicefiscale == "" || codicefiscale == null || CheckRegularExpression(codicefiscale, codice_fiscale_exp) == false){
		 document.forms["form2"]["cf"].focus();
		 document.getElementById("cfError").innerHTML = "<i> ! Prego, è necessario un codice fiscale valido per la fattura!</i>";
			 return false;
	 }
	 
	 else if  (phone == "" || phone == null || CheckRegularExpression(phone, phone_exp) == false){
		 document.forms["form2"]["phone"].focus();
		 document.getElementById("ntelError").innerHTML = "<i> ! Questo campo è obbligatorio e deve essere valido </i>";
			 return false;
	 }
	 
	 else if  (regione == "" || regione == null || CheckRegularExpression(regione, regione_exp) == false){
		 document.forms["form2"]["regione"].focus();
		 document.getElementById("regioneError").innerHTML = "<i> ! Questo campo è obbligatorio e deve essere valido </i>";
			 return false;
	 }
	 
	 else if  (provincia == "" || provincia == null || CheckRegularExpression(provincia, provincia_exp) == false){
		 document.forms["form2"]["provincia"].focus();
		 document.getElementById("provinciaError").innerHTML = "<i> ! Questo campo è obbligatorio e deve essere valido  </i>";
			 return false;
	 }
	 
	 else if  (via == "" || via == null || CheckRegularExpression(via, via_exp) == false ){
		 document.forms["form2"]["via"].focus();
		 document.getElementById("viaError").innerHTML = "<i> ! Questo campo è obbligatorio e deve essere valido  </i>";
			 return false;
	 }
	 
	 else if  (CAP == "" || CAP == null || CheckRegularExpression(CAP, CAP_exp) == false){
		 document.forms["form2"]["CAP"].focus();
		 document.getElementById("capError").innerHTML = "<i> ! Questo campo è obbligatorio e deve essere valido </i>";
			 return false;
	 }
	 
	 else if  (nazione == "" || nazione == null || CheckRegularExpression(nazione, nazione_exp) == false){
		 document.forms["form2"]["nazione"].focus();
		 document.getElementById("nazioneError").innerHTML = "<i> ! Questo campo è obbligatorio e deve essere valido </i>";
			 return false;
	 }
	 else if  (pf == "" || pf == null || CheckRegularExpression(pf, pf_exp) == false){
		 document.forms["form2"]["pf"].focus();
		 document.getElementById("pfError").innerHTML = "<i> ! Prego, inserisca almeno 0 pt. fedeltà</i>";
	 	return false;

	 } 
	 
	 else {
		myForm.submit();
		return true;
	 }
}


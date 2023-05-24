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
	 let myForm = document.getElementById("formpagamento");
	
	 let num_carta = document.forms["formpagamento"]["numcarta"].value;
	 var num_carta_exp =  /^[3-6]+[0-9]{15}$/;
	 document.getElementById("numcartaError").innerHTML = "" ;
	 let cvc =  document.forms["formpagamento"]["CVCCVV"].value;
	 var cvc_exp =  /^\d{3}$/;
	 document.getElementById("cvccvvError").innerHTML = "";
	 let scadenza_mese = document.forms["formpagamento"]["mese"].value;
	 var scadenza_mese_exp = /^\d[0-12]$/;
	 document.getElementById("meseError").innerHTML = "";
	 let scadenza_anno = document.forms["formpagamento"]["anno"].value;
	 var scadenza_anno_exp = /^\d{2}$/;
	 document.getElementById("annoError").innerHTML = "";
	 let codicefiscale = document.forms["formpagamento"]["cf"].value;
	 var codice_fiscale_exp = /^[A-Z]{6}\d{2}[A-Z]\d{2}[A-Z]\d{3}[A-Z]$/;
	 document.getElementById("cfError").innerHTML = "";
	 
	 if (num_carta == "" || num_carta == null || CheckRegularExpression(num_carta, num_carta_exp) == false){
		 document.forms["formpagamento"]["numcarta"].focus();
	 		document.getElementById("numcartaError").innerHTML = "<i> ! Prego, inserisca un numero di carta valido</i>" ;
	 	return false;
	 }
	 
	 else if  (cvc == "" || cvc == null || CheckRegularExpression(cvc, cvc_exp) == false){
		 document.forms["formpagamento"]["CVCCVV"].focus();
		 document.getElementById("cvccvvError").innerHTML = "<i> ! Prego, inserisca il CVC-CVV della carta scelta (deve essere valido)</i>";
	 	return false;

	 } 
	 else if  (scadenza_mese == "" || CheckRegularExpression(scadenza_mese, scadenza_mese_exp) == false || scadenza_mese == null){
		 document.forms["formpagamento"]["mese"].focus();
		 document.getElementById("meseError").innerHTML = "<i> ! Prego, inserisca il mese presente sulla carta </i>";
		 return false;
	 }
	 else if  (scadenza_anno == "" || scadenza_anno == null || CheckRegularExpression(scadenza_anno, scadenza_anno_exp) == false){ 
		 document.forms["formpagamento"]["anno"].focus();
		 document.getElementById("annoError").innerHTML = "<i> ! Prego, inserisca l'anno presente sulla carta </i>";
			 return false;
	 }
	 else if  (codicefiscale == "" || codicefiscale == null || CheckRegularExpression(codicefiscale, codice_fiscale_exp) == false){
		 document.forms["formpagamento"]["cf"].focus();
		 document.getElementById("cfError").innerHTML = "<i> ! Prego, è necessario un codice fiscale valido per la fattura!</i>";
			 return false;
	 }
	 else {
		myForm.submit();
		 return true;
	 }
}
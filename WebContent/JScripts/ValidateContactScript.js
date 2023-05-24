function CheckRegularExpression(inputtxt, regxp)
{
	if(inputtxt.match(regxp)){
		return true;
	}
	else{
		return false;
	}
}


function validateContactForm(){
	 let myForm = document.getElementById("formcontatto");
	 let phone = document.forms["formcontatto"]["phone"].value;
	 var phone_exp = /^\d{10}$/;
	 document.getElementById("ntelError").innerHTML = "";
	 let regione = document.forms["formcontatto"]["regione"].value;
	 var regione_exp = /^[a-zA-Z]{1,255}+$/;
	 document.getElementById("regioneError").innerHTML = "";
	 let provincia = document.forms["formcontatto"]["provincia"].value;
	 var provincia_exp = /^[a-zA-Z]{1,255}+$/;
	 document.getElementById("provinciaError").innerHTML = "";
	 let via = document.forms["formcontatto"]["via"].value;
	 var via_exp = /^[a-zA-Z-\s-\d]{1,255}+$/;
	 document.getElementById("viaError").innerHTML = "";
	 let CAP = document.forms["formcontatto"]["CAP"].value;
	 var CAP_exp = /^\d{5}$/;
	 document.getElementById("capError").innerHTML = "";
	 let nazione = document.forms["formcontatto"]["nazione"].value;
	 var nazione_exp =  /^[a-zA-Z-\s]{1,255}+$/;
	 document.getElementById("nazioneError").innerHTML = "";
	 
	 if  (phone == "" || phone == null || CheckRegularExpression(phone, phone_exp) == false){
		 document.forms["formcontatto"]["phone"].focus();
		 document.getElementById("ntelError").innerHTML = "<i> ! Questo campo è obbligatorio e forse è troppo lungo </i>";
			 return false;
	 }
	 
	 else if  (regione == "" || regione == null || CheckRegularExpression(regione, regione_exp) == false){
		 document.forms["formcontatto"]["regione"].focus();
		 document.getElementById("regioneError").innerHTML = "<i> ! Questo campo è obbligatorio e forse è troppo lungo </i>";
			 return false;
	 }
	 
	 else if  (provincia == "" || provincia == null || CheckRegularExpression(provincia, provincia_exp) == false){
		 document.forms["formcontatto"]["provincia"].focus();
		 document.getElementById("provinciaError").innerHTML = "<i> ! Questo campo è obbligatorio e forse è troppo lungo  </i>";
			 return false;
	 }
	 
	 else if  (via == "" || via == null || CheckRegularExpression(via, via_exp) == false ){
		 document.forms["formcontatto"]["via"].focus();
		 document.getElementById("viaError").innerHTML = "<i> ! Questo campo è obbligatorio e forse è troppo lungo  </i>";
			 return false;
	 }
	 
	 else if  (CAP == "" || CAP == null || CheckRegularExpression(CAP, CAP_exp) == false){
		 document.forms["formcontatto"]["CAP"].focus();
		 document.getElementById("capError").innerHTML = "<i> ! Questo campo è obbligatorio e forse è troppo lungo </i>";
			 return false;
	 }
	 
	 else if  (nazione == "" || nazione == null || CheckRegularExpression(nazione, nazione_exp) == false){
		 document.forms["formcontatto"]["nazione"].focus();
		 document.getElementById("nazioneError").innerHTML = "<i> ! Questo campo è obbligatorio e forse è troppo lungo </i>";
			 return false;
	 }
	 else {
			myForm.submit();
			return true;
		 }
}
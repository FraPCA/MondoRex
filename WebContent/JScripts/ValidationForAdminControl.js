	function ValidateDate(){
		 let date_form = document.getElementById("FormDate");
	
		 let data_to = document.forms["cercaData"]["FromDate"].value;
		 let data_from = document.forms["cercaData"]["ToDate"].value;
		
		 
		 if (data_to == "" || data_to == null){
		 	alert("Manca una data!");
		 	return false;
		 }
		 else if  (data_from == "" || data_from == null ){
		 	alert("Manca una data!");
		 	return false;
		 } 
	}
	
	function ValidateNome(){
		let cerca_form = document.getElementById("cercaFrom");
		let nome_in_cerca = document.forms["cercaForm"]["caratteristicaCliente"].value;
		
		 if  (nome_in_cerca == "" || nome_in_cerca == null ){
			 	alert("Inserisca almeno un valore");
			 	return false;
			 } 
		 
		 else {
			 cerca_form.submit();
			 return true;
		 }
	}
	

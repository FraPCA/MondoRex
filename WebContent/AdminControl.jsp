
<!-- è la pagina a cui accede l'amministratore -->

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<title>Admin Page</title>

<style><%@include file ="stileAdminControl.css"%>
</style>
<style>
table, th, td {
  border: 1px solid black;
}
</style>
<script> <!-- Serve per sapere se l'operazione è andata bene o no -->
function operationResult(){
	let status = <%= session.getAttribute("errorOperation") %>  //(sfrutta java per leggere dati della sessione)
	
	if (status == '0'){
		window.alert("Operazione terminata con successo");
		console.log("[OK]: status " +status);
	}
	
	else if (status == '1'){
		window.alert("Errore nell'operazione");
		console.log("[*NOT OK*]: status "+status);
	}
	
	else if (status == null){
		console.log("[OK]: status is "+status);
	}
}
</script>
</head>
<% session.removeAttribute("errorOperation"); //dopo il check dell'operazione l'attributo è rimosso%> 
<% 	
	//evita l'accesso scrivendo il link nel browser
	//bisogna avere l'attributo di sessione
	if (session.getAttribute("adminredirect") == null) {
	response.sendError(403);
}
%>

<body onload="{return operationResult();}">
<script src="./jquery-3.6.0.js"></script>
<script src="JScripts/ValidationForAdminControl.js" type="text/javascript">
</script>
<script> 
var xmlhttprequest = new XMLHttpRequest();

function requestSend(){
	
	xmlhttprequest.open("post", "/ConsegnaFinaleTSW/productControl?mostraOrdini=" + document.getElementById("ordiniButton").value, true);
	xmlhttprequest.onreadystatechange = printOrdini;
	xmlhttprequest.send(null);
}

function printOrdini(){
	if (xmlhttprequest.readyState == 4 && xmlhttprequest.status == 200){ //status è lo stato della risposta (POST in questo caso)
		console.log(xmlhttprequest.readyState); //debug					 //POST: versioneHTTP,200	, OK 
		var obj = JSON.parse((xmlhttprequest.responseText));			 //                   status, statusText
		console.log(obj);//debug
		document.getElementById("tables").innerHTML = "<table><tr><th>Email</th>" +
													  "<th>timestamp_ordine</th>" +
													  "<th>totale</th>" +
													  "<th>fornitore</th>" +
													  "<th>pf usati</th></tr>";
		var j = 0;
		
		console.log("sono prima del while");//debug
		while (j < obj.ordini.email.length){ 					 //il nome della colonna è il nome dell'array
			console.log("ho superato la condizione nel while");	 //i valori dell'array sono le occorrenzze nel db
			
		$("#tables tbody").append("<tr>"+
									  "<td>"+ obj.ordini.email[j] +"</td>" +
									  "<td>"+ obj.ordini.timestamp[j] +"</td>" +
									  "<td>"+ obj.ordini.totale[j] +"</td>" +
									  "<td>"+ obj.ordini.fornitore[j] +"</td>" +
									  "<td>"+ obj.ordini.pf[j] +"</td></tr>" +
									  "</tr>");

		j++;
		}
		document.getElementById("tables").innerHTML += "</table>"; //chiude la tabella
	}
}

</script>
<div id=titolo>
<h1>Benvenuto amministratore!</h1>
</div> 
<br><br>

<div>
<div id=selezione>
<form action="productControl" method="get"><!-- Form per vedere i prodotti -->
<label for="categoria">Categoria</label><br>
<select name="categoria" id="categoria">
<option value="*">Qualunque</option>	
<option value="Igiene">Igiene</option>
<option value="Abbigliamento">Abbigliamento</option>
<option value="Giocattoli">Giocattoli</option>
<option value="Cucce">Cucce</option>
<option value="Parafarmacia">Parafarmacia</option>
<option value="Cibosecco">Cibosecco</option>
<option value="Snack">Snack</option>
<option value="Toelettatura">Toelettatura</option>
<option value="Guinzagli">Guinzagli</option>
<option value="Ciotole">Ciotole</option>
<option value="Antiparassitari">Antiparassitari</option>
<option value="Ciboumido">Ciboumido</option>	
</select> 
<button type="submit" name="clienti" value="Clienti">Clienti</button> <!-- Questo bottone restituisce la tabella dei clienti -->
<br><br>

<label for=macategoria>Macro-Categoria</label><br> <!-- La select per scegliere la macrocategoria -->
<select name="macro-categoria" id="macategoria">
<option value="*">Qualunque</option>
<option value="Igieneecura">Igiene e cura</option>
<option value="Accessori">Accessori</option>
<option value="Cibo">Cibo</option>
</select>
<button type="submit" >Invia richiesta!</button><br><br> <!-- inviare la richiesta -->
</form>
<form action="productControl" method="post">
<input type="submit" name="assistenza" value="Assistenza"> <!-- richiedere assistenza -->
</form>
</div>
<div id="cerca">
<form id="cercaForm" method="get" action="productControl"> <!-- form di ricerca dei clienti -->
<input type="text" name="caratteristicaCliente"> 
<input type="submit" value="Cerca" onclick="return ValidateNome()"> 
</form>
<button type="button" id="ordiniButton" value="showordini" onclick="requestSend()">Mostrare tutti gli ordini</button>
</div>
<div id="data">
<form id="cercaData" method="get" action="productControl">
<input type="date" name="FromDate"> 
 <input type="date" name="ToDate">
<input type="submit" value="Cerca per data" onclick="return ValidateDate()">
</form>
</div>
</div>
<div id=logout>
<form action="Logout" method="get">
<input class = "Logout" type="submit" value="Logout">
</form>
</div>
<div id="tables">
</div>
</body>
</html>


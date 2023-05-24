<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Registrati</title>
<script src="./jquery-3.6.0.js"></script>
<link rel="stylesheet" href="stileRegister.css">
</head>
<body>
<script>
function CheckRegularExpression(inputtxt, regxp)
{
	if(inputtxt.match(regxp)){
		return true;
	}
	else{
		return false;
	}
}

function checkTheForm(){

	//Resetto i parametri di errore
	document.getElementById("nameError").innerHTML = "";
	document.getElementById("usernameError").innerHTML = "";
	document.getElementById("surnameError").innerHTML = "";
	document.getElementById("passwordError").innerHTML = "";
	document.getElementById("verifypasswordError").innerHTML = "";
	
	let x = document.getElementById("form");
	let name = document.forms["form"]["name"].value;
	var name_expr = /^[a-zA-Z]+$/;
	let surname = document.forms["form"]["surname"].value;
	var password_expr = /^[a-zA-Z0-9^!@#$%^&*()]{6,}$/;
	let username = document.forms["form"]["username"].value;
	var email_expr = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,7})+$/;
	let password = document.forms["form"]["password"].value;
	let verifypassword = document.forms["form"]["verifypassword"].value;
		
	console.log(username);
	if (name == "" || CheckRegularExpression(name, name_expr) == false) {
		 document.forms["form"]["name"].focus();
		 document.getElementById("nameError").innerHTML = "<i> ! Prego, inserisca il nome</i>";
	 	return false;
	}
	
	
	else if (surname == "" || CheckRegularExpression(surname, name_expr) == false) {
		document.forms["form"]["surname"].focus();
		document.getElementById("surnameError").innerHTML = "<i> ! Prego, inserisca il cognome</i>";
	 	return false;
	}
	
	else if (username == "" || CheckRegularExpression(username, email_expr) == false) {
		document.forms["form"]["username"].focus();
		 document.getElementById("usernameError").innerHTML = "<i> ! Prego, inserisca una email valida</i>";
	 	return false;
	}
	
	else if (password == "" || CheckRegularExpression(password, password_expr) == false) {
		document.forms["form"]["password"].focus();
		 document.getElementById("passwordError").innerHTML = "<i> ! Prego, manca una password o le due inserite non coincidono. La lunghezza minima per una password è di 6 caratteri.</i>";
	 	return false;
	}
	
	else if (password != verifypassword) {
		document.forms["form"]["verifypassword"].focus();
		 document.getElementById("verifypasswordError").innerHTML = "<i> ! Prego, manca una password o le due inserite non coincidono. La lunghezza minima per una password è di 6 caratteri.</i>";
	 	return false;
	}
	
	
	 else {
		 x.submit();
		 return true;
	 }
}
</script>

<h1>Registrazione</h1>
<%
	if(request.getSession().getAttribute("RegisterError") !=  null)
	{
		out.print("<p id='error'> " + request.getSession().getAttribute("RegisterError") + "</p><br>");
	}
%>
<p id='text1'>Inserisci i dettagli richiesti per registrarti sul sito. </p>
<div id="formBox">
<form action="Register" method="post" id="form">
<label for="name">Il tuo nome</label> <br>
<input id="name" type="text" name="name" placeholder="nome">
<p id="nameError" style="color: red"></p>
<br>
<label for="surname">Il tuo cognome</label> <br>
<input id="surname" type="text" name="surname" placeholder="cognome">
<p id="surnameError" style="color: red"></p>
<br>
<label for="username">Il tuo indirizzo di posta elettronica</label> <br>
<input id="username" type="text" name="username" placeholder="email">
<p id="usernameError" style="color: red"></p>
<br>
<label for="password">La password che desideri utilizzare</label> <br>
<input id="password" type="password" name="password" placeholder="password">
<p id="passwordError" style="color: red"></p>
<br>
<label for="verifypassword">Inserisci di nuovo la password per confermarla</label> <br>
<input id="verifypassword" type="password" name="verifypassword" placeholder="">
<p id="verifypasswordError" style="color: red"></p>
<br>
<button class="bottone" type="button" value="Register" name="Registrami sul sito" onclick="checkTheForm()">Registrami sul sito</button><!-- diventa button così posso lanciare la validazione -->
</form>
</div>
</body>
</html>
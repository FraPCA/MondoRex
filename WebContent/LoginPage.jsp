<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<style><%@include file ="stileLogin.css"%></style>
<title>Accedi</title>
<script src="JScripts/ValidateLogIn.js">
</script>
</head>
<body>
<h1>Login</h1>
<form action="Login" method="post" id="form"> <!-- Manda form alla servlet login. L'id permettere a JS di pescarlo dal documento -->
<fieldset>
<label for="username">Username</label> <br>
<input id="username" type="text" name="username" placeholder="enter username">
<br>
<label for="password">Password</label> <br>
<input id="password" type="password" name="password" placeholder="enter password">
<%
// Check user credentials

/* Controlla se si ha effettuato l'accesso senza successo, stampa messaggio di errore */
if(session.getAttribute("failedLogin") != null)
{
	Boolean failedLogin = (Boolean) session.getAttribute("failedLogin");
	if(failedLogin.booleanValue() == true)
	{	
		out.println("<p class = 'errore'><b>Sorry, you are not registered.</b></p>");
	}
}
%>
<p id="QuestaNonEunaEmail" style="color: red"></p> <!-- è il paragrafo d'errore mostrato se la regexp dell'email non è valida -->
<button class="bottone" type="button" value="Login" onclick="CheckTheEmail()">Login</button> <!-- fa il check del regexp -->
<input class="bottone" type="reset" value="Reset"/>
</fieldset>
</form>
</body>
</html>
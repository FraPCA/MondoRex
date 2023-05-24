<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import = "model.Bean.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<script src="./jquery-3.6.0.js"></script>
<script src="JScripts/PagamentoValidation.js">
</script>
<jsp:useBean id="utente" class="model.Bean.UserBean" scope="session"/>
<link rel="stylesheet" href="stileInfoPagamentoEcontatto.css"> 
</head>
<body>

<%@ include file="menu.html" %>

<div class="formscontainer">

<form id="form2" name="form2" action="GestioneOrdine" method ="post">
<div class="metodiPagamento">
<div class="numerodicartaAndCVCrow">
<span class="numcartaSpan"><label for="numcarta" id="numcartaLabel">Numero di carta</label></span>

</div>
<div class="input-rows">
<input type="tel" id="numcarta" name="numcarta" >
<p id="numcartaError" style="color: red"></p>
<span class="cvcSpan"><label for="CVCCVV" >CVC-CCV</label></span><br>
<input type="tel" id="CVCCVV" name="CVCCVV" >
<p id="cvccvvError" style="color: red"></p>
</div><br>
<label for="Mese">Scadenza<br></label>
<input type="tel" id="mese" name="mese" max="2" >
<p id="meseError" style="color: red"></p>
 / 
<input type="tel" id="anno" name="anno" max="2" >
<p id="annoError" style="color: red"></p>
<br><br>
<label for="pf" >Punti Fedelta da usare</label><br>
<input type="tel" id="pf" name="pf" >
<p id="pfError" style="color: red"></p>
<p>Punti fedeltà disponibili: 
<%
if(request.getSession().getAttribute("utentesessione") == null)
{
	response.sendRedirect("LoginPage.jsp");
	return;
}
UserBean ub = (UserBean) request.getSession().getAttribute("utentesessione");
out.print(" " + ub.getN_punti_fedelta() + "</p>");

%>
<br><br>
<label for="cf" >Codice Fiscale</label><br>
<input type="text" id="cf" name="cf" >
<p id="cfError" style="color: red"></p>
<br><br><br><br><br><br><br>
</div>
<div class="contatto">
<label for="phone" >Numero di telefono:</label><br>
<input type="tel" id="phone" name="n_telefono">
<p id="ntelError" style="color: red"></p>
<br><br>
<label for="regione" >Regione:</label><br>
<input type="text" id="regione" name="regione">
<p id="regioneError" style="color: red"></p>
<br><br>
<label for="provincia">Provincia:</label><br>
<input type="text" id="provincia" name="provincia">
<p id="provinciaError" style="color: red"></p>
<br><br>
<label for="provincia">Via:</label><br>
<input type="text" id="via" name="via">
<p id="viaError" style="color: red"></p>
<br><br>
<label for="CAP">CAP:</label><br>
<input type="tel" id="CAP" name="CAP" max="5">
<p id="capError" style="color: red"></p>
<br><br>
<label for="nazione" >Nazione:</label><br>
<input type="text" id="nazione" name="nazione">
<p id="nazioneError" style="color: red"></p>
<br><br>
<button class="submitButton" type="button" name="Invia" onclick="validatePayForm()">Invia!</button>
</div>
</form>
</div>


<%@include file="footer.html"%>
</body>
</html>
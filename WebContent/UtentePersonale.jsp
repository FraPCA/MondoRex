<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*" import = "model.Bean.*" import = "model.*"%>
<!DOCTYPE html>
<html lang="en">
<%
UserBean ub = (UserBean) request.getSession().getAttribute("utentesessione");
%>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="stileCarrello.css"> <!-- dovrà poter stampare il carrello -->
    <link rel="stylesheet" href="stileUtentePersonale.css">
    <%
    //Controllo che l'utente abbia effettuato l'accesso, altrimenti lo rimando alla pagina di login.
	if(request.getSession().getAttribute("utentesessione") == null)
	{
		response.sendRedirect("/ConsegnaFinaleTSW/LoginPage.jsp");
		return; //Stoppa l'esecuzione, altrimenti prova a vedere dopo lo stesso e va in crash.
	}	
%>
    <title>Pagina personale di <%out.print(ub.getNome() + " " + ub.getCognome());%></title>
     <jsp:useBean id="BeanUtente" class="model.Bean.UserBean" scope="session"/>
      <jsp:useBean id="BeanContatto" class="model.Bean.ContattoBean" scope="session"/>
      <jsp:useBean id="BeanMetodo" class="model.Bean.MetodoPagamentoBean" scope="session"/>
	<jsp:useBean id="BeanProdottoOrdine" class="model.Bean.RedundantProductBean" scope="session"/>
	<script src="./jquery-3.6.0.js"></script>
</head>
<body>
<h2 id="benvenuto">Benvenuto alla tua pagina personale, <%out.print(ub.getNome() + " " + ub.getCognome()); %> ! <br> Cosa vuoi fare?</h2>

<div id="divBottoni">
<form action="AreaPersonale" method = get>
<button class="bottoneUtente" type="submit" name='ModificaContatto' value='ModificaContatto'>Modifica I tuoi contatti</button> 
<button class="bottoneUtente" type="submit" name='ModificaMetodi' value='ModificaMetodi'>Modifica I tuoi metodi di pagamento</button>
<button class="bottoneUtente" type="submit" name='VisualizzaOrdini' value='VisualizzaOrdini'>Visualizza i tuoi ordini</button>
</form>
</div>

<%
if(request.getSession().getAttribute("html") != null)
{
	out.print(request.getSession().getAttribute("html")); //html è il messaggio che viene stampato
	request.getSession().setAttribute("html", null);
}
%>
<br><br>
<div id="divBottoneHome">
<a href="/ConsegnaFinaleTSW/index.jsp">
	<button id="bottoneHome">Home</button>
</a>  
</div>
<br>
<script type='text/javascript' src='./JScripts/ValidateContactScript.js' ></script>
<script type='text/javascript' src='./JScripts/ValidatePayScript.js'> </script>

</body>
</html>
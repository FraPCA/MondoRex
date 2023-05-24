<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*" import = "model.Bean.*" import = "model.*"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="stileCheckout.css">
    <title>Checkout</title>
</head>
<body>
<%@ include file="menu.html"%>
<h2>Ordine effettuato</h2>
<%
System.out.println("Sono Checkout :" +session.getAttribute("whereYouComeFrom"));
if (request.getSession().getAttribute("utentesessione") == null || session.getAttribute("whereYouComeFrom") == null){
	response.sendError(403);
	return;
}

System.out.println("Debug: pf in sessione = " + ((UserBean) request.getSession().getAttribute("utentesessione")).getN_punti_fedelta());

if(request.getSession().getAttribute("carrello") != null && session.getAttribute("whereYouComeFrom").equals("InfoPagamentoEcontatto"))
{
	String str = "<div id='testo'><p>Il totale pagato è stato di <b>" + request.getSession().getAttribute("nuovototale") + " </b> per <b>" + ((Carrello) request.getSession().getAttribute("carrello")).getListaprodottiordine().size() + " </b> articoli.</p><p>A seguito dell'ordine, sono stati accreditati <b>" + request.getSession().getAttribute("pfaccreditati") + "</b> Punti Fedeltà. </p><p>Il tuo bilancio corrente è di <b>" + ((UserBean) request.getSession().getAttribute("utentesessione")).getN_punti_fedelta() + " </b> Punti Fedeltà. </p></div><br><h1>Grazie per l'acquisto!</h1></p>";
	out.print(str); // Salvo i dati nel modo più semplice
	session.removeAttribute("carrello");
	session.removeAttribute("nuovototale");
	session.removeAttribute("pfaccreditati");
	request.getSession().setAttribute("str", str); //se aggiorno la pagina i dati dell'ordine non vengono passati da GestioneOrdine
												   //quindi salvo la stringa (come attributo di sessione)
	session.removeAttribute("whereYouComeFrom");
}
else
{
	out.print(request.getSession().getAttribute("str"));
}
out.print("<img id=logo src='/ConsegnaFinaleTSW/img/checkout_logo.png'");
%>
<%@ include file="footer.html"%>
</body>
</html>
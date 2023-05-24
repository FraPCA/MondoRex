<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*" import = "model.Bean.*" import = "model.*"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="stileCarrello.css">
    <title>Carrello</title>
    <jsp:useBean id="BeanCarrello" class="model.Bean.ProductBean" scope="session"/>
	<jsp:useBean id="BeanProdottoOrdine" class="model.Bean.RedundantProductBean" scope="session"/>
</head>
<body>
<%@ include file="menu.html" %>	<!-- import del menu -->
<h2>CARRELLO CORRENTE</h2>
<%
String messaggiovuoto= "";//diventa "tutti gli elementi" se il carrello è pieno, il carrello è vuoto altrimenti
if(request.getSession().getAttribute("carrello") != null)
{
	System.out.println("Stampo il carrello");
	Carrello carrellocurr = (Carrello) request.getSession().getAttribute("carrello");
	System.out.println(carrellocurr);
	if(!(carrellocurr.getListaprodottiordine().isEmpty()))
	{
		if (request.getSession().getAttribute("errorInQuantity") != null){
			System.out.println("Scrivo l'errore");
		out.print("<p class='assenteInMagazzino' style='color: red' >"+ " L'oggetto "+request.getSession().getAttribute("errorInQuantity") +" non è disponibile nella quantità richiesta. La quantità nell'ordine è stata impostata a quella massima disponibile. Riforniremo presto.</p>");	
		}
		Collection<RedundantProductBean> pbL = carrellocurr.getListaprodottiordine();
		Iterator<RedundantProductBean> it = pbL.iterator();
		while (it.hasNext())
		{
			System.out.println("Leggo il prossimo bean");
			RedundantProductBean pb = it.next();
			System.out.println(pb);
			
			out.print("<div class='boxInfo'>");
			if (pb.getLink_immagine().startsWith("img/")) { //se inizia con img vuol dire che sono nella cartella ConsegnaFinale
				out.print("<img class='prodotto' src='/ConsegnaFinaleTSW/" + pb.getLink_immagine() + "'>");
			}

			else if (pb.getLink_immagine().startsWith("http")){ //altrimenti l'admin le ha inserite con un url :)
				out.print("<img class='prodotto' src='" + pb.getLink_immagine() + "'>");
				
			}
			
			out.print("<br>");
			out.print("<h4>" + pb.getNome() + "</h4>");
			out.print("<h4>Prezzo: " + pb.getPrezzo_totale() + "</h4>"); 
			out.print("<form action='Cart' method='post'>");
			out.print("<input type='hidden' name='itemIDCambioQuantita' value='" + pb.getCodice_prodotto() + "'>");;
			out.print("<label for='itemnumber'>Quantità: </label><input class='inputNum' type='number' name='itemnumber' placeholder='");
			out.print(pb.getQuantita() + "'>");
			out.print("<input class='bottone' type='submit' name='AzioneCarrello' value='CambiaNumero'>");
			out.print("</form>");
			out.print("</div>");	//fine div boxInfo
			out.print("<hr><br>");
		}
		
		out.print("<p id='tot'>Totale: " + carrellocurr.getTotaleCarrello() + "</p>" + "<br>");
	}
	else //se il carrello esiste ma è vuoto
	{
		messaggiovuoto = "<div id='carrelloVuoto'><h3>Il tuo carrello è vuoto.</h3><br><p>Incomincia ad aggiungere dei prodotti al tuo carrello cliccando sul bottone 'Aggiungi al carrello', nel catalogo oppure nella pagina del prodotto a cui sei interessato.</p></div><br>";
	}	
}
else //se il carrello non esiste
{
	messaggiovuoto = "<div id='carrelloVuoto'><h3>Il tuo carrello è vuoto.</h3><br><p>Incomincia ad aggiungere dei prodotti al tuo carrello cliccando sul bottone 'Aggiungi al carrello', nel catalogo oppure nella pagina del prodotto a cui sei interessato.</p></div><br>";

}
out.print("<div id='bottomBox'>");
out.print(messaggiovuoto);
if(messaggiovuoto.isEmpty() == true)
{
	out.print("<a href='InfoPagamentoEcontatto.jsp'><button class='bottone2'>Procedi con l'ordine</button></a><br><br>");
}
out.print("<a href='index.jsp'><button class='bottone2'>Ritorna alla pagina iniziale</button></a><br><br>");
out.print("</div>");
%>
	
<%@ include file="footer.html" %>	<!-- import del footer -->
</body>
</html>
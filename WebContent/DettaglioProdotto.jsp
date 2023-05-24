<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*" import = "model.Bean.ProductBean" import="model.Bean.ReviewBean" %>
<!DOCTYPE html>
<html>
<jsp:useBean id="prodottodamostrare" class="model.Bean.ProductBean" scope="session"/>
<jsp:useBean id="review" class="model.Bean.ReviewBean" scope="session"/>

<%
if ((request.getAttribute("prodottodamostrare") == null || request.getAttribute("reviewlist") == null)){
	
	response.sendError(400);
	return;
}
ProductBean pb = (ProductBean)request.getAttribute("prodottodamostrare");
Collection<ReviewBean> rlist = (Collection<ReviewBean>)request.getAttribute("reviewlist");
%>
<head>
<title><%out.print(pb.getNome());%> su MondoRex</title>
<style>
<%@include file ="stileDettaglioProdotto.css"%>
<%@ include file="stileMenu.css" %>	
<%@ include file="stileFooter.css" %>
</style>
<script src="https://code.jquery.com/jquery-3.1.1.js"></script>	<!-- link jQuery -->
</head>
<body>
<%@ include file="menu.html" %>	<!-- import del menu -->
<%
System.out.println("<img src='" + pb.getLink_immagine() + "'>"); //debug
out.print("<div id='boxProdotto'>");
out.print("<img id='prodotto' src='/ConsegnaFinaleTSW/" + pb.getLink_immagine() + "'>");
out.print("<div id='infoProdotto'>");
out.print("<span id='nome'>" + pb.getNome() + "</span> <br><br>");
for(int c = 1; c <= (int) request.getAttribute("score"); c++) //stampa le stelle piene
{
	out.print("<img class='stella' src='/ConsegnaFinaleTSW/img/Stella.png'>");
}
for(int c = 4; c >= (int) request.getAttribute("score") ; c--)//stampa le stelle vuote
{
	out.print("<img class='stella' src='/ConsegnaFinaleTSW/img/Stella2.png'>");
}
out.print("<br><br><span id='prezzo'>Prezzo totale: " + pb.getPrezzo_totale() + "</span>" + "<br><br><span id='sconto'>Percentuale sconto: " + pb.getPercentuale_sconto() + " %" +  "</span><br><br>" + pb.getDescrizione_dettagliata() + "<br><br>Brand: " + "<span id='brand'>" + pb.getBrand() + "<span>" + "<br>");
out.print("<form action='../Cart' method='post'>"); //per accedere a Cart bisogna tornare indietro di due directories
out.print("<input type='hidden' name='itemIDCarrello' value='" + pb.getCodice_prodotto() + "'>");
out.print("<input class='bottone' type='submit' name='AzioneCarrello' value='AggiungiACarrello'/>");
out.print("</form>");
out.print("</div>");	//fine infoProdotto
out.print("</div>");	//fine boxProdotto
out.print("<h3>Recensioni prodotto</h3><br><br>");
Iterator<ReviewBean> it = rlist.iterator();
while(it.hasNext())
{
	ReviewBean currentreview = it.next();
	System.out.println("DettaglioProdotto : " +currentreview); //debug
	out.print("<div class='boxRecensione'>");
	out.print("<span class='tipo'>Email utente: </span>" + currentreview.getNome_utente() + "<br><span class='tipo'>Orario pubblicazione: </span>" + currentreview.getTimestamp_rec() + "<br>");
	out.print("<span class='tipo'>Valutazione: </span>");
	for(int c = 1; c <= currentreview.getValutazione(); c++)//stelle piene
	{
		out.print("<img class='stellaR' src='/ConsegnaFinaleTSW/img/Stella.png'>");
	}
	for(int c = 4; c >= currentreview.getValutazione(); c--)//stelle vuote
	{
		out.print("<img class='stellaR' src='/ConsegnaFinaleTSW/img/Stella2.png'>");
	}
	out.print("<br><span class='tipo'>Descrizione: </span>" + currentreview.getDescrizione());
	out.print("</div><br><br>");
}
%>
<%@include file="footer.html"%>

<script>
	$('#prodotto').mouseover(function(){
		console.log("Sto sopra");
	   $(this).css("cursor","pointer");
	   //$(this).animate({width: "50%"}, 'slow');	facendo così esce brutto
	   $(this).css({"transform": "scale(1.3)", "transition": "transform .2s"});	//zoom in
	});
	
	$('#prodotto').mouseout(function(){
		console.log("Vado via");
		   //$(this).animate({width: "30%"}, 'slow');
		$(this).css("transform","scale(1.0)");	//zoom out
		});
</script>

</body>
</html>
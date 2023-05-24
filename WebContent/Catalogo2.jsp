<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import = "model.Bean.*" import = "model.DAO.ProductDAO_DS" 
    import = "java.util.Collection" import = "java.util.Iterator" import = "java.util.LinkedList"%>

<!DOCTYPE html>
<html>
<head>
	<title>Catalogo</title>
	<link rel="stylesheet" href="stileCatalogo.css">
	<script src="https://code.jquery.com/jquery-3.1.1.js"></script>	<!-- link jQuery -->
</head>
<body>
	<%@ include file="menu.html" %>	<!-- import del menu -->
	
	<h1 id="titolo">Catalogo</h1>
	<br>
	<div id="Catalog">
	<% 
	//Controlla se è impostato l'attributo dalla servlet di carrello
	if (session.getAttribute("errorInQuantity") != null){
		out.print("<p class=\"assenteInMagazzino\" style=\"color: red\" >"+ " L'oggetto "+session.getAttribute("errorInQuantity") +" è terminato</p>");	
	}%>
		<%
			Collection<ProductBean> pbL = new LinkedList<>();
			ProductDAO_DS pdao = new ProductDAO_DS();
			System.out.println(request.getQueryString()); //debug
			
				try {
					pbL = pdao.doRetrieveAll(null);
					System.out.println(pbL);
					Iterator<ProductBean> it = pbL.iterator();
					while (it.hasNext()) {
						System.out.println("Leggo il prossimo bean");
						ProductBean pb = it.next();
						System.out.println(pb);//debug
						String formURL = response.encodeURL("PaginaProdotto"); // PaginaProdotto + info di sessione 
						System.out.println("ID:" + pb.getCodice_prodotto());
						out.print("<div id='boxProdotto'>");			//replaceAll sostituisce gli spazi
						out.print("<form action='" + formURL + "/" + pb.getNome().replaceAll("\\s", "") +"'" + "method='post'>"); //Crea l'url dinamico che poi viene mappato nel web.xml alla servlet DettaglioProdotto.
						out.print("<h4>" + pb.getNome() + "</h4>");
						out.print("<h4>Prezzo: " + pb.getPrezzo_totale() + "</h4>");
						out.print("<h4> Descrizione: " + pb.getDescrizione_breve() + "</h4>");
						out.print("<input type='hidden' name='itemID' value='" + pb.getCodice_prodotto() + "'>");
						out.print("<input class='immagineProdotto' type='image' src='/ConsegnaFinaleTSW/" + pb.getLink_immagine() + " '" + " name='submit'></form><br><br>");
						out.print("<form action='Cart' method='post'>");
						out.print("<input type='hidden' name='itemIDCarrello' value='" + pb.getCodice_prodotto() + "'>");
						out.print("<input class='bottone' type='submit' name='AzioneCarrello' value='AggiungiACarrello'/>");
						out.print("</form>");
						out.print("</div>");	//fine div che contiene il prodotto
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					response.sendError(501, "Errore if n.1 in doPost (Catalogo)");
				}
			%>
			<br>
			</div>
		<br>
	<br>
	<%@ include file="footer.html" %>	<!-- import del footer -->
	
	<script>
		$('.immagineProdotto').mouseover(function(){
			console.log("Sto sopra");
		   $(this).css("cursor","pointer");
		   $(this).css({"transform": "scale(1.2)", "transition": "transform .2s"});	//zoom in
		});
		
		$('.immagineProdotto').mouseout(function(){
			console.log("Vado via");
			$(this).css("transform","scale(1.0)");	//zoom out
			});
	</script>
	
</body>
</html>
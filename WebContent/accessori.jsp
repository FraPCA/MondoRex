<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import = "java.util.LinkedList" import = "model.Bean.*" import = "model.DAO.ProductDAO_DS" 
    import = "java.util.Collection" import = "java.util.Iterator" import = "java.util.LinkedList"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="stileCatalogo.css">
<link rel="stylesheet" href="stileCategorie.css">
<script src="./jquery-3.6.0.js"></script>
<title>Accessori</title>
</head>
<body>
	<%@ include file="menu.html" %>	<!-- import del footer -->    
	
	<h1>Ecco gli accessori preferiti dai nostri amici a quattro zampe</h1>

	<% 
		Collection<ProductBean> pbL = new LinkedList<>();
		ProductDAO_DS pdao = new ProductDAO_DS();
		try {
			pbL = pdao.doRetrieveByCategory("accessori");
			System.out.println(pbL);
			Iterator<ProductBean> it = pbL.iterator();
			while (it.hasNext()) {
				System.out.println("Leggo il prossimo bean");
				ProductBean pb = it.next();
				System.out.println(pb);
				String formURL = response.encodeURL("PaginaProdotto");
				System.out.println("ID:" + pb.getCodice_prodotto());
				out.print("<div id='boxProdotto'>");
				out.print("<form action='" + formURL + "/" + pb.getNome().replaceAll("\\s", "") +"'" + "method='post'>"); //Crea l'url dinamico che poi viene mappato nel web.xml alla servlet DettaglioProdotto.
				out.print("<div class='infoProdotto'>");
				out.print("<h4>" + pb.getNome() + "</h4>");
				out.print("<h4>Prezzo: " + pb.getPrezzo_totale() + "</h4>");
				out.print("<h4> Descrizione: " + pb.getDescrizione_breve() + "</h4>");
				out.print("</div>");
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
</html>
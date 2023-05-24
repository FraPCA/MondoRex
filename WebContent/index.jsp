<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import = "java.util.LinkedList" import = "model.Bean.*" import = "model.DAO.ProductDAO_DS" 
    import = "java.util.Collection" import = "java.util.Iterator" import = "java.util.LinkedList"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
   	<link rel="stylesheet" href="stileIndex.css">
   	<script src="./jquery-3.6.0.js"></script>
    <title>MondoRex</title>
</head>
<body>
		<%@ include file="menu.html" %>	<!-- import del menu -->
        <h1>Chi siamo</h1>
        <p>
            Siamo un gruppo di studenti di Informatica, appartenenti all'Università  degli studi di Salerno. Questa
            è la nostra applicazione web fatta per il corso "Tecnologie Software per il Web". Il sito è rivolto
            a chi ama e possiede un cane/i. Su Mondo Rex potete trovare svariati articoli, dal cibo sano e nutriente,
            ad articoli utili per l'igiene e la cura del vostro cane, fino agli accessori più utili e trendy.
        </p>

		<div class="divBanner">
		 	<img id="banner" src = "img/indexba.png">
		</div>

        <h2>I nostri brand</h2>
        
        <div class="divBrand">
		 	<img id="brand" src = "img/brands.png">
		</div>
        
        <h2>I nostri prodotti</h2>
        
        <div id="miniCatalogo">
        <%
        Collection<ProductBean> pbL = new LinkedList<>();
		ProductDAO_DS pdao = new ProductDAO_DS();
		try {
			pbL = pdao.doRetrieveRandom(4);
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
        
        </div>
       

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
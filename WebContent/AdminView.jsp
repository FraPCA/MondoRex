<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import = "java.util.LinkedList" import = "model.Bean.ProductBean" import = "model.DAO.ProductDAO_DS" 
    import = "model.Bean.UserBean" import = "model.DAO.UserDAO_DM"
    import = "java.util.Collection" import = "java.util.Iterator" import = "java.util.LinkedList" import = "model.Bean.OrdineBean" import = "model.DAO.OrdineDAO_DS" %>

<!DOCTYPE html>
<html>
<head>
<style>
table, th, td {
  border: 1px solid black;
}
</style>
<script src="./jquery-3.6.0.js"></script>
<script src="JScripts/ValidationForAdminView.js">
</script>
<title>Admin Page</title>
</head>

<body>
<div id="adminControl">
<%@include file="AdminControl.jsp" %>
</div>
<br>
<br>
<div id=popUpPage>
<%
System.out.println("I'm AdminView: " +request.getAttribute("whereYouComeFrom"));

if (request.getAttribute("whereYouComeFrom") != null && request.getAttribute("whereYouComeFrom").equals("AdminControl")){ //se non si passa prima per AdminControl, rimanda ad essa 
																																
	 
	System.out.println(request.getQueryString());
	
	//**Nel codice che segue, ogni if è un possibile parametro ricevuto
	// in base a un certo parametro certi dati verranno pescati da db e stampati
	
 if (request.getParameter("clienti") != null && request.getParameter("clienti").equals("Clienti")) {
	 	Collection<UserBean> ubC = new LinkedList<>();
	 	UserDAO_DM udao = new UserDAO_DM();
		out.print("<table> ");
		try {
			System.out.println("Letto il parametro *");
			ubC = udao.doRetrieveAll(null);
			request.setAttribute("clienti", true);
			
			out.print("<table>");
			out.print("<tr>\r\n" + 
			"<th>email</th>\r\n" + 
			"<th>nome</th>\r\n" + 
			"<th>password</th>\r\n" + 
			"<th>cognome</th>\r\n" + 
			"<th>timestamp registrazione</th>\r\n" + 
			"<th>punti fedeltà</th>\r\n" + 
			"</tr>"); //stampo le intestazioni della tabella
			Iterator<UserBean> it = ubC.iterator();
			while (it.hasNext()) { //stampo il contenuto della tabella
				System.out.println("Leggo il prossimo bean");
				UserBean ub = it.next();
				System.out.println("pb");
				out.print("<tr>");
				out.print("<td>" +
				ub.getEmail() +
				"</td>");
				out.print("<td>" +
				ub.getNome() +
				"</td>");
				out.print("<td>" +
				ub.getPassword() +
				"</td>");
				out.print("<td>" +
				ub.getCognome() +
				"</td>");
				out.print("<td>" +
				ub.getTimestamp_registrazione() +
				"</td>");
				out.print("<td>" +
				ub.getN_punti_fedelta() +
				"</td>");
				out.print("</tr>");
			}	
		out.print("</table>");
	} catch (Exception e) {
		// TODO Auto-generated catch block
	}	
}
 
	else if (request.getParameter("FromDate") != null && request.getParameter("ToDate") != null) {
	 	Collection<OrdineBean> obC = new LinkedList<>();
	 	OrdineDAO_DS odao = new OrdineDAO_DS();
		out.print("<table> ");
		obC = odao.doRetrieveAll();
		try {
			System.out.println("Letto il parametro *");
			obC = odao.doRetrieveAllBetween(request.getParameter("FromDate"), request.getParameter("ToDate"));
			
			out.print("<table>");
			out.print("<tr>\r\n" + 
			"<th>email</th>\r\n" + 
			"<th>timestamp</th>\r\n" + 
			"<th>prezzo totale</th>\r\n" + 
			"<th>fornitore</th>\r\n" +  
			"<th>punti fedeltà usati</th>\r\n" + 
			"</tr>"); //stampo le intestazioni della tabella
			Iterator<OrdineBean> it = obC.iterator();
			while (it.hasNext()) {//stampo il contenuto della tabella
				System.out.println("Leggo il prossimo bean");
				OrdineBean ob = it.next();
				System.out.println("pb");
				out.print("<tr>");
				out.print("<td>" +
				ob.getEmail_utente_ordine() +
				"</td>");
				out.print("<td>" +
				ob.getTimestamp_ordine() +
				"</td>");
				out.print("<td>" +
				ob.getFornitore() +
				"</td>");
				out.print("<td>" +
				ob.getPrezzo_totale() +
				"</td>");
				out.print("<td>" +
				ob.getUso_PF() +
				"</td>");
				out.print("</tr>");
			}	
		out.print("</table>");
	} catch (Exception e) {
		// TODO Auto-generated catch block	
		}
	}
 
 else if (request.getParameter("caratteristicaCliente") != null) {
		UserDAO_DM udao = new UserDAO_DM();
		String stringa_da_cercare = request.getParameter("caratteristicaCliente");
		String where_condition = "email like \""+stringa_da_cercare+"\" or nome like \""+stringa_da_cercare+"\" or N_punti_fedelta like \""+stringa_da_cercare+"\" or cognome like \""+stringa_da_cercare+"\" or password like \""+stringa_da_cercare+"\" ";
		Collection<UserBean> ubC = new LinkedList<>();
		out.print("<table> ");
		try {
			ubC = udao.cercaStringaInColonne(where_condition);
			out.print("<table>");
			out.print("<tr>\r\n" + 
			"<th>email</th>\r\n" + 
			"<th>nome</th>\r\n" + 
			"<th>password</th>\r\n" + 
			"<th>cognome</th>\r\n" + 
			"<th>timestamp registrazione</th>\r\n" + 
			"<th>punti fedeltà</th>\r\n" + 
			"</tr>"); //stampo le intestazioni della tabella
			Iterator<UserBean> it = ubC.iterator();
			while (it.hasNext()) {//stampo il contenuto della tabella
				System.out.println("Leggo il prossimo bean");
				UserBean ub = it.next();
				System.out.println("pb");
				out.print("<tr>");
				out.print("<td>" +
				ub.getEmail() +
				"</td>");
				out.print("<td>" +
				ub.getNome() +
				"</td>");
				out.print("<td>" +
				ub.getPassword() +
				"</td>");
				out.print("<td>" +
				ub.getCognome() +
				"</td>");
				out.print("<td>" +
				ub.getTimestamp_registrazione() +
				"</td>");
				out.print("<td>" +
				ub.getN_punti_fedelta() +
				"</td>");
				out.print("</tr>");
			}	
		out.print("</table>");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 }
else
{	
	Collection<ProductBean> pbL = new LinkedList<>(); //è usato per stampare i prodotti
	ProductDAO_DS pdao = new ProductDAO_DS();
	
	if (request.getParameter("categoria").equals("*") && request.getParameter("macro-categoria").equals("*")) {
		try {
			System.out.println("Letto il parametro *");
			pbL = pdao.doRetrieveAll(null);
			request.setAttribute("prodotto", true);
			System.out.println(pbL);
			out.print("<table>");
			out.print("<tr>\r\n" + 
			"<th>codice_prodotto</th>\r\n" + 
			"<th>nome</th>\r\n" + 
			"<th>prezzo totale</th>\r\n" + 
			"<th>prezzo netto</th>\r\n" + 
			"<th>percentuale sconto</th>\r\n" + 
			"<th>descrizione breve</th>\r\n" + 
			"<th>IVA</th>\r\n" + 
			"<th>descrizione dettagliata</th>\r\n" + 
			"<th>quantità disponibile</th>\r\n" + 
			"<th>brand</th>\r\n" + 
			"<th>microcategoria</th>\r\n" + 
			"<th>macrocategoria</th>\r\n" + 
			"<th>linkImmagine</th>\r\n" + 
			"</tr>"); //stampo le intestazioni della tabella
			Iterator<ProductBean> it = pbL.iterator();
			while (it.hasNext()) {
				System.out.println("Leggo il prossimo bean");
				ProductBean pb = it.next();
				System.out.println("pb");
				out.print("<tr>");
				out.print("<td>" +
				pb.getCodice_prodotto() +
				"</td>");
				out.print("<td>" +
				pb.getNome() +
				"</td>");
				out.print("<td>" +
				pb.getPrezzo_totale() +
				"</td>");
				out.print("<td>" +
				pb.getPrezzo_netto() +
				"</td>");
				out.print("<td>" +
				pb.getPercentuale_sconto() +
				"</td>");
				out.print("<td>" +
				pb.getDescrizione_breve() +
				"</td>");
				out.print("<td>" + pb.getIVA() +
				"</td>");	
				out.print("<td>" +
				pb.getDescrizione_dettagliata() +
				"</td>");			
				out.print("<td>" +
				pb.getQuantita_disponibile() +
				"</td>");
				out.print("<td>" +
				pb.getBrand() +
				"</td>");
				out.print("<td>" +
				pb.getMicrocategoria() +
				"</td>");	
				out.print("<td>" +
				pb.getMacrocategoria() +
				"</td>");		
				out.print("<td>" + "<img src=\"" +
				pb.getLink_immagine() + "\"" +
				"</td>");	
				out.print("</tr>");
				}
			out.print("</table>");
		} catch (Exception e) {
		// TODO Auto-generated catch block
		}
	}

	if(request.getParameter("categoria").equals("*") && !(request.getParameter("macro-categoria").equals("*")))
	{
		try {
			System.out.println("Letto il parametro *");
			pbL = pdao.doRetrieveAll(null, " macrocategoria = '" + request.getParameter("macro-categoria") + "'");
			request.setAttribute("prodotto", true);
			System.out.println(pbL);
			out.print("<table>");
			out.print("<tr>\r\n" + 
			"<th>codice_prodotto</th>\r\n" + 
			"<th>nome</th>\r\n" + 
			"<th>prezzo totale</th>\r\n" + 
			"<th>prezzo netto</th>\r\n" + 
			"<th>percentuale sconto</th>\r\n" + 
			"<th>descrizione breve</th>\r\n" + 
			"<th>IVA</th>\r\n" + 
			"<th>descrizione dettagliata</th>\r\n" + 
			"<th>quantità disponibile</th>\r\n" + 
			"<th>brand</th>\r\n" + 
			"<th>microcategoria</th>\r\n" + 
			"<th>macrocategoria</th>\r\n" + 
			"<th>linkImmagine</th>\r\n" + 
			"</tr>"); //stampo le intestazioni della tabella		
			Iterator<ProductBean> it = pbL.iterator();
			while (it.hasNext()) {
				System.out.println("Leggo il prossimo bean");
				ProductBean pb = it.next();
				System.out.println("pb");
				out.print("<tr>");
				out.print("<td>" +
				pb.getCodice_prodotto() +
				"</td>");
				out.print("<td>" +
				pb.getNome() +
				"</td>");
				out.print("<td>" +
				pb.getPrezzo_totale() +
				"</td>");
				out.print("<td>" +
				pb.getPrezzo_netto() +
				"</td>");
				out.print("<td>" +
				pb.getPercentuale_sconto() +
				"</td>");
				out.print("<td>" +
				pb.getDescrizione_breve() +
				"</td>");
				out.print("<td>" + pb.getIVA() +
				"</td>");
				out.print("<td>" +
				pb.getDescrizione_dettagliata() +
				"</td>");
				out.print("<td>" +
				pb.getQuantita_disponibile() +
				"</td>");
				out.print("<td>" +
				pb.getBrand() +
				"</td>");			
				out.print("<td>" +
				pb.getMicrocategoria() +
				"</td>");
				out.print("<td>" +
				pb.getMacrocategoria() +
				"</td>");
				out.print("<td>" + "<img src=\"" +
				pb.getLink_immagine() + "\"" +
				"</td>");			
				out.print("</tr>");
				}
			out.print("</table>");
			} catch (Exception e) {
		// TODO Auto-generated catch block
		}	
	}

	if(request.getParameter("macro-categoria").equals("*") && (!(request.getParameter("categoria").equals("*"))))
	{
		out.print("<table>");
		try {
			System.out.println("Letto il parametro *");
			pbL = pdao.doRetrieveAll(null, " microcategoria = '" + request.getParameter("categoria") + "'");
			request.setAttribute("prodotto", true);
			System.out.println(pbL);
			out.print("<table>");
			out.print("<tr>\r\n" + 
			"<th>codice_prodotto</th>\r\n" + 
			"<th>nome</th>\r\n" + 
			"<th>prezzo totale</th>\r\n" + 
			"<th>prezzo netto</th>\r\n" + 
			"<th>percentuale sconto</th>\r\n" + 
			"<th>descrizione breve</th>\r\n" + 
			"<th>IVA</th>\r\n" + 
			"<th>descrizione dettagliata</th>\r\n" + 
			"<th>quantità disponibile</th>\r\n" + 
			"<th>brand</th>\r\n" + 
			"<th>microcategoria</th>\r\n" + 
			"<th>macrocategoria</th>\r\n" + 
			"<th>linkImmagine</th>\r\n" + 
			"</tr>"); //stampo le intestazioni della tabella		
			Iterator<ProductBean> it = pbL.iterator();
			while (it.hasNext()) {
				System.out.println("Leggo il prossimo bean");
				ProductBean pb = it.next();
				System.out.println("pb");
				out.print("<tr>");
				out.print("<td>" +
				pb.getCodice_prodotto() +
				"</td>");
				out.print("<td>" +
				pb.getNome() +
				"</td>");
				out.print("<td>" +
				pb.getPrezzo_totale() +
				"</td>");
				out.print("<td>" +
				pb.getPrezzo_netto() +
				"</td>");
				out.print("<td>" +
				pb.getPercentuale_sconto() +
				"</td>");
				out.print("<td>" +
				pb.getDescrizione_breve() +
				"</td>");
				out.print("<td>" + pb.getIVA() +
				"</td>");
				out.print("<td>" +
				pb.getDescrizione_dettagliata() +
				"</td>");
				out.print("<td>" +
				pb.getQuantita_disponibile() +
				"</td>");
				out.print("<td>" +
				pb.getBrand() +
				"</td>");
				out.print("<td>" +
				pb.getMicrocategoria() +
				"</td>");
				out.print("<td>" +
				pb.getMacrocategoria() +
				"</td>");
				out.print("<td>" + "<img src=\"" +
				pb.getLink_immagine() + "\"" +
				"</td>");
				out.print("</tr>");
				}
			out.print("</table>");
			} catch (Exception e) {
		// TODO Auto-generated catch block
		}
	out.print("</table>");
	}
}
}
else {
	//Questo form serve a farmi ritornare ad AdminControl se ci sono errori (guardare onload)
	//invia da solo la richiesta se ci sono errori, la servlet la accetta e reindirizza ad AdminControl
	out.print("<form action=\"AdminControl.jsp\" id=\"sendMeToAdminControl\" type=\"hidden\" onload=\"return { document.getElementById(\"sendMeToAdminControl\").submit();  }\"></form>");
}
%>
</div>
<br><br>
<div>
<h2>Modulo di alterazione prodotto</h2>
<form action="productControl" id="moduloProdotto" name="moduloProdotto" method="post" > <!-- enctype="multipart-form/data"--> 

<label for="codice_prodotto">Codice prodotto:</label><br>
<input type="text" id="codice_prodotto" name="codice_prodotto" required><br>
<p id="erroreCodiceProdotto" style="color: red"></p>
<label for="nome_prodotto">Nome:</label><br>
<input type="text" id="nome_prodotto" name="nome_prodotto"><br>
<p id="erroreNomeProdotto" style="color: red"></p>
<label for="prezzo_netto">Prezzo_netto:</label><br>
<input type="number" step="0.01" name="prezzo_netto" id="prezzo_netto"><br>
<p id="errorePrezzoNetto" style="color: red"></p>
<label for="percentuale_di_sconto">Percentuale sconto:</label><br>
<input type="number" name="percentuale_di_sconto" id="percentuale_di_sconto"><br>
<p id="errorePercentualeSconto" style="color: red"></p>
<label for="descrizione_breve">Descrizione breve:</label><br>
<textarea id="descrizione_breve" name="descrizione_breve" rows="4" cols="50">
</textarea>
<p id="erroreDescrizioneBreve" style="color: red"></p>
<label for="iva">IVA:</label>
<input type="number" name="iva"><br>
<p id="erroreIva" style="color: red"></p>
<label for="descrizione_dettagliata">Descrizione dettagliata:</label><br>
<textarea id="descrizione_dettagliata" name="descrizione_dettagliata" rows="4" cols="50">
</textarea>
<p id="erroreDescrizioneDettagliata" style="color: red"></p>
<br>
<label for="quantitadisponibile">Quantità disponibile:</label>
<input type="number" name="quantitadisponibile" id="quantitadisponibile">
<p id="erroreQuantitaDisponibile" style="color: red"></p><br>
<label for="brand">Brand:</label><br>
<input type="text" id="brand" name="brand">
<p id="erroreBrand" style="color: red"></p><br>
<label for="microcat">Microcategoria:</label><br>
<input type="text" id="microcat" name="microcat">
<p id="erroreMicrocat" style="color: red"></p><br>
<label for="macrocat">Macrocategoria:</label>

<input type="text" id="macrocat" name="macrocat">
<p id="erroreMacrocat" style="color: red"></p><br><br>
<label for="Linkimmagine">Nome dell'immagine + .png:</label>
<p id="erroreLinkImmagine" style="color: red"></p><br><br>
<input type="text" id="Linkimmagine" name="Linkimmagine"><br>	<!-- linkimmagine ma in realtà contiene il nome dell'immagine-->
<button type="submit"  name="OperazioneProdotto" value="Aggiungi" onclick="return CheckFormProdotto()">Aggiungi</button> <!-- Vede le regexpression -->
<button type="submit" name="OperazioneProdotto" value="Rimuovi" onclick="return CheckRimuovi('prodotto')">Rimuovi</button><!-- Vede solo la regexpression per codice_prodotto -->
<button type="submit" name="OperazioneProdotto" value="Modifica" onclick="return CheckFormProdotto()">Modifica</button> <!-- Vede le regexpression -->
</form>
<h2>Modulo di alterazione cliente</h2>
<form action="productControl" method="post" id="moduloCliente">

<label for="email">Email:</label><br>
<input type="text" id="email" name="email" required><br>
<p id="erroreEmail" style="color: red"></p><br>
<label for="nome_utente">Nome:</label><br>
<input type="text" id="nome_utente" name="nome_utente"><br>
<p id="erroreNomeUtente" style="color: red"></p><br>
<label for="password">Password:</label><br>
<input type="text" id="password" name="password"><br>
<p id="errorePass" style="color: red"></p><br>
<label for="cognome_utente">Cognome:</label><br>
<input type="text" id="cognome_utente" name="cognome_utente"><br>
<p id="erroreCognomeUtente" style="color: red"></p><br>
<label for="punti_fedelta">Punti fedeltà:</label>
<input type="number" name="punti_fedelta" id="pf"><br>
<p id="errorePf" style="color: red"></p><br>

<button type="submit" name="OperazioneClienti" value="Aggiungi" onclick="return CheckFormCliente()">Aggiungi</button>
<button type="submit" name="OperazioneClienti" value="Rimuovi" onclick="return CheckRimuovi('cliente')">Rimuovi</button>
<button type="submit" name="OperazioneClienti" value="Modifica" onclick="return CheckFormCliente()">Modifica</button>
</form>
</div>
<!-- include file operation form -->
</body>
</html>


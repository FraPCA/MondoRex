package control;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import model.Bean.*;
import model.DAO.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AreaPersonale
 */
@WebServlet("/AreaPersonale")
public class AreaPersonale extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	
	 public String getFornitoreCarta(String numCarta) throws Exception
	    {
	    	System.out.println("Debug: char= " + numCarta.charAt(0));
	    	switch(numCarta.charAt(0))
	    	{
	    	case '3':
	    		return "American Express";
	    	case '4':
	    		return "Visa";
	    	case '5':
	    		return "MasterCard";
	    	case '6':
	    		return "Discovery";
	    	default:
	    		throw new Exception("Numero carta di credito invalido");
	    	}
	    }
	
    public AreaPersonale() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("Arrivato alla servlet!");
		request.getSession().setAttribute("html", ""); 
		StringBuffer html = new StringBuffer();//è l'html di output passato a UtentePersonale
		//String buffer può fare l'append di altre stringhe 
		
		if(request.getParameter("DettagliOrdine") != null)
		{
			System.out.println("Stampo i dettagli dell'ordine");
			Collection<RedundantProductBean> prodottiordine = new ArrayList<>();
			RedundantProductDAO_DS rpdao = new RedundantProductDAO_DS();
			ProductDAO_DS pdao = new ProductDAO_DS();
			try {
				System.out.println("Provo ad ottenere i prodotti");
				prodottiordine = rpdao.doRetrieveAllConnectedToOrder(((UserBean)request.getSession().getAttribute("utentesessione")).getEmail(), Timestamp.valueOf(request.getParameter("timestampordine")));
				System.out.println("Ottenuti i prodotti");
				System.out.println(prodottiordine);
				if(prodottiordine.size() != 0) //C'è almeno un prodotto nell'ordine
				{
					Iterator<RedundantProductBean> it = prodottiordine.iterator();
					while (it.hasNext())
					{
						System.out.println("Leggo il prossimo bean");
						RedundantProductBean pb = it.next();
						System.out.println(pb);
						
						html.append("<div class='boxInfo'>");
					
						html.append("<img class='prodotto' src='/ConsegnaFinaleTSW/" + pb.getLink_immagine() + "'>"); //Aggiungerlo al salvataggio nel db
					
						html.append("<br>");
						html.append("<h4>" + pb.getNome() + "</h4>");
						html.append("<h4>Prezzo: " + (pb.getPrezzo_totale() * pb.getQuantita()) + "</h4>"); 
						html.append("</div>");	//fine div boxInfo
						html.append("<hr><br>");
					}
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(request.getParameter("VisualizzaOrdini") != null)
		{
			Collection<OrdineBean> ordiniutente = new ArrayList<>();
			OrdineDAO_DS odao = new OrdineDAO_DS();
			try {
				ordiniutente = odao.doRetrieveAllClient(((UserBean)request.getSession().getAttribute("utentesessione")).getEmail());
				System.out.println("Stampo gli ordini dell'utente " + ordiniutente);
				html.append("<div id='containerOrdini'><h3> I tuoi ordini salvati <br>");
				if(ordiniutente.size() != 0)
				{
					for(OrdineBean ordine: ordiniutente)
					{					  //fa il toString ma con i tag HTML
						html.append(ordine.toPersonalHTML());
						// "html" verrà passato a UtentePersonale.jsp, il seguente è un modo per passare il timestamp
						//												all'if con DettagliOrdine (113)	
						html.append("<form action='AreaPersonale' method='get'>"
						+ "<input type='hidden' name='timestampordine' value='" + ordine.getTimestamp_ordine()	
						+ "'>"
						+ "<div class='containerBottone'><button class='bottoneK' type='submit' name='DettagliOrdine' >Visualizza l'ordine in dettaglio</button></div>"
						+ "</form><br><br>");
						html.append("<form action='GenFattura' method='post'>"
						+ "<input type='hidden' name='utentecorrente' value='" + request.getSession().getAttribute("utentesessione") + "'>"
						+ "<input type='hidden' name='emailutenteordinefattura' value='" + ordine.getEmail_utente_ordine() + "'>"
						+ "<input type='hidden' name='timestampordine' value='" + ordine.getTimestamp_ordine()	
						+ "'>"
						+ "<div class='containerBottone'><button class='bottoneK' type='submit' name='StampaFattura' >Stampa la fattura</button></div>"
						+ "</form><br></div>"
								);
					}
									}
				else //l'utente non ha effettuato alcun ordine
				{
					html.append("<p> Non abbiamo nessun tuo ordine salvato. Puoi effettuare un ordine dalla pagina del carrello, dopo avere aggiunto almeno un prodotto ad esso.");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//le seguenti sono subordinate alla richiesta "ModificaContatto"
		if(request.getParameter("RimuoviContatto") != null)
		{
			System.out.println("Cancello contatto!");
			String N_telefono = request.getParameter("N_telefono"); //il parametro è in un campo nascosto
			System.out.println("N_telefono: " + N_telefono);
			ContattoDAO_DS cdao = new ContattoDAO_DS();
			try {
				cdao.doDelete(((UserBean) request.getSession().getAttribute("utentesessione")).getEmail(), N_telefono);
				System.out.println("Contatto cancellato!");
				html.append("Il contatto è stato cancellato!");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		//salva il nuovo contatto (154, 167)
		if(request.getParameter("InviaContatto") != null) //InviaContatto è in un campo del form, visibile 
		{
			ContattoDAO_DS cdao = new ContattoDAO_DS();
			ContattoBean cb = new ContattoBean(((UserBean) request.getSession().getAttribute("utentesessione")).getEmail(), request.getParameter("n_telefono"), request.getParameter("regione"), request.getParameter("provincia"), request.getParameter("via"), request.getParameter("CAP"), request.getParameter("nazione"));
			try {
				cdao.doSave(cb);
				System.out.println("Contatto salvato!");
				html.append("<p> Il tuo nuovo indirizzo di contatto è stato salvato con successo!");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(request.getParameter("ModificaContatto") != null)
		{
			System.out.println("Arrivato a modificacontatto");
			Collection<ContattoBean> contattiutente = new ArrayList<>();
			ContattoDAO_DS cdao = new ContattoDAO_DS();
			try {
				contattiutente = cdao.doRetrieveAllForUtente(((UserBean)request.getSession().getAttribute("utentesessione")).getEmail());
				System.out.println("Prima dello script");
				html.append("<h3> I tuoi contatti salvati <br><br>");
				if(contattiutente.size() != 0) //se l'utente ha almeno un metodo di contatto collegato
				{
					for(ContattoBean contatto: contattiutente)
					{
						html.append(contatto.toPersonalHTML() + "<br><br>");
						html.append("<form action='AreaPersonale' method='get'>"
						+ "<input type='hidden' name='N_telefono' value='" + contatto.getN_telefono()
						+ "'>" 
						+ "<button class='bottoneK' type='submit' name='RimuoviContatto' >Rimuovi questo contatto</button>"
						+ "</form>");
					}
				}
				else
				{
					html.append("<p> Non abbiamo nessun tuo contatto salvato. Puoi aggiungere un nuovo contatto compilando i campi qui sotto.");
				}
				html.append("<p>Riempire questi campi per inserire nuove informazioni di contatto</p><br>");
				html.append("<form id='formcontatto' name='formcontatto' action='AreaPersonale' method='get'>"); 
				html.append("<label for='cf' >Codice Fiscale</label><br>" + 
						"<input type='text' id='cf' name='cf'>" + 
						"<p id='cfError' style='color: red'></p>" + 
						"<br><br><br><br><br><br><br>" +  
						"<div class='contatto'>" + 
						"<label for='phone' >Numero di telefono:</label><br>" + 
						"<input type='tel' id='phone' name='n_telefono'>" + 
						"<p id='ntelError' style='color: red'></p>" + 
						"<br><br>" + 
						"<label for='regione' >Regione:</label><br>" + 
						"<input type='text' id='regione' name='regione'>" + 
						"<p id='regioneError' style='color: red'></p>" + 
						"<br><br>" + 
						"<label for='provincia'>Provincia:</label><br>" + 
						"<input type='text' id='provincia' name='provincia'>" + 
						"<p id='provinciaError' style='color: red'></p>" + 
						"<br><br>" + 
						"<label for='provincia'>Via:</label><br>" + 
						"<input type='text' id='via' name='via'>" + 
						"<p id='viaError' style='color: red'></p>" + 
						"<br><br>" + 
						"<label for='CAP'>CAP:</label><br>" + 
						"<input type='tel' id='CAP' name='CAP' max='5'>" + 
						"<p id='capError' style='color: red'></p>" + 
						"<br><br>" + 
						"<label for='nazione' >Nazione:</label><br>" + 
						"<input type='text' id='nazione' name='nazione'>" + 
						"<p id='nazioneError' style='color: red'></p>" + 
						"<br><br>" + 
						"<button class='submitButton' type='submit'  onclick='return validateContactForm()' name='InviaContatto'>Invia!</button>" + 
						"</div>" + 
						"</form>" + 
						"</div><br>" );
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(request.getParameter("RimuoviMetodo") != null) //stesso funzionamento di RimuoviContatto
		{
			System.out.println("Cancello metodo!");
			Date scadenza = Date.valueOf(request.getParameter("mpscadenza"));
			Integer CVCCVV = Integer.parseInt(request.getParameter("mpcvccvv"));
			System.out.println("Scadenza: " + scadenza);
			System.out.println("CVCVVV: " + CVCCVV);
			MetodoPagamentoDAO_DS mpdao = new MetodoPagamentoDAO_DS();
			try {
				mpdao.doDelete(((UserBean) request.getSession().getAttribute("utentesessione")).getEmail(), CVCCVV, scadenza);
				System.out.println("Metodo di pagamento cancellato!");
				html.append("Il metodo di pagamento è stato cancellato!");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(request.getParameter("InviaMetodo") != null) //salva il metodo
		{
			MetodoPagamentoDAO_DS mpdao = new MetodoPagamentoDAO_DS();
			try {
				MetodoPagamentoBean mpb = new MetodoPagamentoBean(request.getParameter("numcarta"), Integer.parseInt(request.getParameter("CVCCVV")), getFornitoreCarta(request.getParameter("numcarta")), "Prepagata", Date.valueOf("20" + request.getParameter("anno") + "-" + request.getParameter("mese") + "-1"), ((UserBean) request.getSession().getAttribute("utentesessione")).getEmail());
				mpdao.doSave(mpb);
				System.out.println("Metodo salvato!");
				html.append("<p> Il tuo nuovo metodo di pagamento è stato salvato con successo!");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(request.getParameter("ModificaMetodi") != null) //identico funzionamento di ModificaContatto
		{
			Collection<MetodoPagamentoBean> metodiutente = new ArrayList<>();
			MetodoPagamentoDAO_DS mpdao = new MetodoPagamentoDAO_DS();
			try {
				metodiutente = mpdao.doRetrieveAllForUtente(((UserBean)request.getSession().getAttribute("utentesessione")).getEmail());
				html.append("<h3> I tuoi metodi di pagamento salvati <br><br>");
				if(metodiutente.size() != 0)
				{
					for(MetodoPagamentoBean metodo: metodiutente)
					{
						html.append(metodo.toPersonalHTML() + "<br><br>");
						html.append("<form action='AreaPersonale' method='get'>"
						+ "<input type='hidden' name='mpscadenza' value='" + metodo.getScadenza() 
						+ "'>" + "<input type='hidden' name='mpcvccvv' value='" + metodo.getCVCCVV()
						+ "'>" 
						+ "<button class='bottoneK' type='submit' name='RimuoviMetodo' >Rimuovi questo metodo di pagamento</button>"
						+ "</form>");
					}
				}
				else
				{
					html.append("<p> Non abbiamo nessun tuo metodo di pagamento salvato. Puoi aggiungere un nuovo metodo di pagamento compilando i campi qui sotto.");
				}
				html.append("<p>Riempire questi campi per inserire un nuovo metodo di pagamento</p><br>");
				html.append("<form id='formpagamento' name='formpagamento' action='AreaPersonale' method='get'>");
				html.append("<div class='metodiPagamento'>" + 
						"<div class='numerodicartaAndCVCrow'>" + 
						"<span class='numcartaSpan'><label for='numcarta' id='numcartaLabel'>Numero di carta</label></span>" + 
						"</div>" + 
						"<div class='input-rows'>" + 
						"<input type='tel' id='numcarta' name='numcarta' >" + 
						"<p id='numcartaError' style='color: red'></p>" + 
						"<span class='cvcSpan'><label for='CVCCVV' >CVC-CCV</label></span><br>" + 
						"<input type='tel' id='CVCCVV' name='CVCCVV' >" + 
						"<p id='cvccvvError' style='color: red'></p>" + 
						"</div><br>" + 
						"<label for='Mese'>Scadenza<br></label>" + 
						"<input type='tel' id='mese' name='mese' max='2' >" + 
						"<p id='meseError' style='color: red'></p>" + 
						" / " + 
						"<input type='tel' id='anno' name='anno' max='2' >" + 
						"<p id='annoError' style='color: red'></p>"
						+ "<label for='cf' >Codice Fiscale</label><br>"
						+ "<input type='text' id='cf' name='cf'><p id='cfError' style='color: red'>"
						+ "</p>"
						+ "<button class='submitButton' type='submit' onclick='return validatePayForm()' name='InviaMetodo'>Invia!</button>" 
						+ "<br></div>"
						+ "</form>"
						);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		request.getSession().setAttribute("html", html.toString());
		response.sendRedirect("/ConsegnaFinaleTSW/UtentePersonale.jsp");
}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

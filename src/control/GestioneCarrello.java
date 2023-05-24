package control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.DAO.*;
import model.Carrello;
import model.Bean.*;

/**
 * Servlet implementation class Carrello
 */
@WebServlet("/GestioneCarrello")
public class GestioneCarrello extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GestioneCarrello() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		//Crea il carrello se non gi√† esistente come attributo di sessione
		
		System.out.println("Servlet prova a reperire il carrello");
		Carrello carrellocurr = (Carrello)request.getSession().getAttribute("carrello");
		if(carrellocurr == null) {
			System.out.println("Servlet non ha trovato il carrello");
			carrellocurr = new Carrello();
			request.getSession().setAttribute("carrello", carrellocurr);
			System.out.println("nuovo carrello creato");
		}
		System.out.println("Servlet ha trovato il carrello");
		ProductBean pb = new ProductBean();
		ProductDAO_DS pdao = new ProductDAO_DS();
		String azione = request.getParameter("AzioneCarrello");//questo Ë il parametro passato da aggiungi al carrello
															   //o da "cambia numero" (cambia la quantit‡ del prodotto)
		System.out.println("Letta l'azione " + azione);
		try {
			
			   //Controllo che l'utente abbia effettuato l'accesso, altrimenti lo rimando alla pagina di login.
			if(request.getSession().getAttribute("utentesessione") == null)
			{
				response.sendRedirect("/ConsegnaFinaleTSW/LoginPage.jsp");
			}	
			else
			{
				if (azione != null) { //Evita di accedere al carrello se vi si accede copiando il link
				
					if (azione.equals("AggiungiACarrello")) {	//Per distinguere dalla visualizzazione dei prodotti nel carrello
						System.out.println("Letto AggiungiACarrello");
						//itemIDCarrello Ë il campo del form contentente  l'id del prodotto
						if(request.getParameter("itemIDCarrello") != null) 
						{
							System.out.println("Provo ad aggiungere prodotto con codice " + request.getParameter("itemIDCarrello"));
							System.out.println("Letto codice del prodotto da aggiungere");
							pb = pdao.doRetrieveByKey(request.getParameter("itemIDCarrello"));
							if (pb.getQuantita_disponibile() > 0) {
								carrellocurr.aggiungiProdotto(request.getParameter("itemIDCarrello"));
								if(request.getSession().getAttribute("errorInQuantity") != null)
								{
									request.getSession().removeAttribute("errorInQuantity");
									//rimuovo errorInQuantity se impostato precedentemente
									//o la quantit‡ ritorna disponibile
								}
							}
						
							else 
							{
								request.getSession().setAttribute("errorInQuantity", pb.getNome() );
							}
							
						
						
							System.out.println("Aggiunto il prodotto alla lista di prodotti dell'ordine");
						}
					}
					else
					{
						if(azione.equals("CambiaNumero"))
						{
							System.out.println("Letto cambio di quantita ");											//"" vuol dire che il campo Ë inalterato
							if((request.getParameter("itemIDCambioQuantita") != null) && (request.getParameter("itemnumber") != ""))
							{
								System.out.println("Provo ad impostare la quantit√† del prodotto " + request.getParameter("itemIDCambioQuantita") + " a " + request.getParameter("itemnumber"));
								Integer itemnumber = 0;
								//quantit‡ massima disponibile (103)
								Integer itemnumbermax = pdao.doRetrieveByKey(request.getParameter("itemIDCambioQuantita")).getQuantita_disponibile();
								
								if (itemnumbermax < Integer.valueOf(request.getParameter("itemnumber"))) {
									System.out.println("Debug: Scrivo errore nella sessione");
									request.getSession().setAttribute("errorInQuantity", request.getParameter("itemIDCambioQuantita"));
									itemnumber =  itemnumbermax;
								}
								else
								{
									itemnumber = Integer.parseInt(request.getParameter("itemnumber"));
									request.getSession().removeAttribute("errorInQuantity");
								}
								carrellocurr.impostanumeroordinati(request.getParameter("itemIDCambioQuantita"), itemnumber);
							
							
								System.out.println("Aggiornata la quantit√† del prodotto");
							}
						
						}
					}
				}
				request.getSession().removeAttribute("itemIDCarrello");
				request.getSession().removeAttribute("AzioneCarrello");
				System.out.println("Eliminati attributi.");
				//Per evitare che ricaricando la pagina lo rifaccia (124, 125)
			request.getSession().setAttribute("carrello", carrellocurr);
			request.setAttribute("carrello", carrellocurr);
			System.out.println("Aggiornato il carrello");
			System.out.println("Stampo il carrello corrente");
			System.out.println(carrellocurr);
		
			String redirectURL = "";
			//controlliamo se la pagina Ë stata caricata da PaginaProdotto o da un'altra (135, 144) 
			if(request.getHeader("referer").contains("PaginaProdotto"))
			{
				redirectURL = "/ConsegnaFinaleTSW/Catalogo2.jsp";
			}
			else
			{
				redirectURL = request.getHeader("referer").replace("http://localhost:8080", "");//Ottengo l'url della pagina a cui ritornare, devo togliere l'inizio perch√® altrimenti tomcat si lamenta che non incomincia con /
			}
			response.sendRedirect(redirectURL); //Usato al posto di requestdispatcher.forward, altrimenti stack overflow.
		
		}
		} catch (SQLException e) {
			System.out.println("Error:" + e.getMessage());
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

package control;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Enumeration;

import model.Bean.*;
import model.*;
import model.DAO.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

/**
 * Servlet implementation class GestioneOrdine
 */
@WebServlet("/GestioneOrdine")
public class GestioneOrdine extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GestioneOrdine() {
        super();
        // TODO Auto-generated constructor stub
    }

    public void connectRedundantProductToOrder(RedundantProductBean rpb, OrdineBean ob, String email_utente) throws Exception
    {
    	/*Inizializzazione DS
    	 * 
    	 */
    	DataSource ds;
		System.out.println("Entrato nel try dell'inizializzazione");
		Context initCtx = new InitialContext();
		System.out.println("Creato InitialContext");
		Context envCtx = (Context) initCtx.lookup("java:comp/env");
		System.out.println("Creato envCTX");
		ds = (DataSource) envCtx.lookup("jdbc/MondoRex");
		System.out.println("Creata la ds");
		ds.toString();
    	
    	Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		connection = ds.getConnection();
		String SQL = "INSERT INTO contenimento_ordine (email_utente_contenimento, timestamp_ordine_contenimento, codice_prodotto_ridondante) VALUES (?,?,?);";
		preparedStatement = connection.prepareStatement(SQL);
		preparedStatement.setString(1, email_utente);
		preparedStatement.setTimestamp(2, ob.getTimestamp_ordine());
		preparedStatement.setString(3, rpb.getCodice_prodotto());
		preparedStatement.execute();
		System.out.println("Debug: creato il collegamento.");
		preparedStatement.close();
		connection.close();
    }
    
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
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Enumeration<String> enumeration = request.getParameterNames();
		while(enumeration.hasMoreElements())
		{
			System.out.println(" VALORE: " + request.getParameter(enumeration.nextElement()));
		}
		/*CREAZIONE ORDINE
	 	SALVA DATI UTENTE SE NON GIA PRESENTI NEL DB, SALVA I PRODUCTBEAN RIDONDANTI NEL DB, CREA ORDINEBEAN E LO SALVA, CREA RELAZIONE NEL DB PER OGNI PRODOTTORIDONDANTE E LO COLLEGA, SVUOTA IL CARRELLO. */
		HttpSession session = request.getSession();
		System.out.println("Ottenuta la sessione");
		ContattoBean cb = new ContattoBean(((UserBean) session.getAttribute("utentesessione")).getEmail(), request.getParameter("n_telefono"), request.getParameter("regione"), request.getParameter("provincia"), request.getParameter("via"), request.getParameter("CAP"), request.getParameter("nazione"));
		System.out.println("Stampo il ContattoBean" + cb);
		try {
																																				//in base al n. di carta recupera il fornitore
			MetodoPagamentoBean mpb = new MetodoPagamentoBean(request.getParameter("numcarta"), Integer.parseInt(request.getParameter("CVCCVV")), getFornitoreCarta(request.getParameter("numcarta")), "Prepagata", Date.valueOf("20" + request.getParameter("anno") + "-" + request.getParameter("mese") + "-1"), ((UserBean) session.getAttribute("utentesessione")).getEmail());
			System.out.println("Stampo il MetodoPagamentoBean" + mpb); //Tipo e Fornitore sono predefiniti in quanto dovrebbero essere ottenuti automaticamente dall'API di gestione del pagamento, siccome noi non ce l'abbiamo, presupponiamo che non servano.
			System.out.println("Controllo se i bean sono presenti gi√† nel db, altrimenti li salvo");
			ContattoDAO_DS cdao = new ContattoDAO_DS();
			MetodoPagamentoDAO_DS mpdao = new MetodoPagamentoDAO_DS();
			Collection<ContattoBean> contatti = cdao.doRetrieveAllForUtente(((UserBean) session.getAttribute("utentesessione")).getEmail());
			boolean insert = false;
			if(contatti.size() == 0)
			{
				insert = true;
			}
			else
			{
				for(ContattoBean contatto: contatti)
				{
					insert= (cb.available(contatto)); //Vediamo se possiamo inserire il contatto o se Ë un duplicato 
					System.out.println("Debug: valore insert= " + insert);
					if(insert == false)
					{
						break;//evito di scorrere il resto dell'array
					}
				}
			}
			if((insert))
			{
				cdao.doSave(cb);
			}
			
			//Ë identico a (115, 136)
			Collection<MetodoPagamentoBean> metodi = mpdao.doRetrieveAllForUtente(((UserBean) session.getAttribute("utentesessione")).getEmail());
			insert = false;
			if(metodi.size() == 0)
			{
				insert = true;
			}
			else
			{
				for(MetodoPagamentoBean metodo: metodi)
				{
					insert = (mpb.available(metodo));
					System.out.println("Debug: valore insert= " + insert);
					if(insert == false)
					{
						break;
					}
				}
			}
			if((insert))
			{
				mpdao.doSave(mpb);
			}
			System.out.println("Effettuato gestione indirizzo e pagamento");
			Carrello carrellocurr = (Carrello) session.getAttribute("carrello");
			System.out.println("Stampo il carrello: " + carrellocurr);
			RedundantProductDAO_DS rpdao = new RedundantProductDAO_DS();
			Collection<RedundantProductBean> prodotti = carrellocurr.getListaprodottiordine();
			
			for(RedundantProductBean prodotto : prodotti)
			{
				insert = true;
				//Scorro tutti i prodotti nell'ordine e per ciascuno controllo che non esista gi‡ nel db (171, 179)
				for(RedundantProductBean prodottodb : rpdao.doRetrieveAll(null, null)) 
				{
					System.out.println("Stampo prodotto: " + prodotto + " Stamp prodottodb" + prodottodb + "Stampo uguaglianza tra i due " + prodotto.equalsDB(prodottodb));
					if(prodotto.equalsDB(prodottodb))
					{
						insert = false;
						break;
					}
				}
				if(insert)
				{
					rpdao.doSave(prodotto);
				}
			}
			System.out.println("Salvati i prodotti dell'ordine");
			/*Applicazione sconto punti fedelt√† all'ordine, come nelle regole specificate nella pagina html*/
			Double nuovototale = 0.00;
			Integer numeropf = 0;
			if((carrellocurr.getTotaleCarrello() >= 10.00) && (Integer.parseInt(request.getParameter("pf")) >= 100) && (((UserBean) session.getAttribute("utentesessione")).getN_punti_fedelta() >= Integer.parseInt(request.getParameter("pf"))))
			{
				System.out.println("Debug: Quest'ordine qualifica per l'uso di punti fedelta");
				numeropf = Integer.parseInt(request.getParameter("pf"));
				nuovototale = (carrellocurr.getTotaleCarrello() - (0.01 * numeropf)); //sottrae al totale i punti fedelt‡
				if(nuovototale < 0)	//Non sottrarre oltre gratis. 
				{
					nuovototale = 0.00;
					numeropf = numeropf + (int) (carrellocurr.getTotaleCarrello() * 100);	//Usa solo tanti punti fedelt√† quanto bastano per rendere gratis.
					System.out.println("Debug: numero punti fedelt√† eccessivo, uso invece " + numeropf + "pf");
				}
			}
			else //non applichiamo punti fedelt‡
			{
				nuovototale = carrellocurr.getTotaleCarrello();
			}
			System.out.println("Debug: numeropf= " + numeropf);
			System.out.println("Debug: nuovototale = " + nuovototale);
			OrdineBean ob = new OrdineBean(((UserBean) session.getAttribute("utentesessione")).getEmail(), (Timestamp.valueOf(LocalDateTime.now())) , nuovototale, mpb.getFornitore(), numeropf, cb.getN_telefono());
			System.out.println("Creato il bean ordine: " + ob);
			OrdineDAO_DS odao = new OrdineDAO_DS();
			odao.doSave(ob);
			System.out.println("Salvato l'ordine nel DB");
			System.out.println("Collego i prodotti ridondanti all'ordine nel DB");
			for(RedundantProductBean prodotto: prodotti)//Popoliamo "contenimento_ordine" con i prodotti del carrello.
			{
				connectRedundantProductToOrder(prodotto, ob, ((UserBean) session.getAttribute("utentesessione")).getEmail());
			}
			System.out.println("Effettuato il collegamento dei prodotti nel carrello con l'ordine.");
						
			/*Codice per accreditare i punti fedelt√† generati dall'ordine
			 * 
			 */
			int pfaccreditati = (int) Math.round(nuovototale * 10);
			System.out.println("Debug pfaccreditati: " + pfaccreditati + "");
			UserBean currentUser = (UserBean) session.getAttribute("utentesessione");
			currentUser.setN_punti_fedelta(currentUser.getN_punti_fedelta() + pfaccreditati - numeropf);
			session.removeAttribute("utentesessione");
			session.setAttribute("utentesessione", currentUser);
			System.out.println("Debug: pf in sessione = " + ((UserBean) request.getSession().getAttribute("utentesessione")).getN_punti_fedelta());
			UserDAO_DS udao = new UserDAO_DS();
			udao.doUpdate(currentUser, " N_punti_fedelta", currentUser.getN_punti_fedelta().toString());
			System.out.println("Effettuato accredito");
			System.out.println("Operazioni completate: rimando al checkout.");
			session.setAttribute("nuovototale", nuovototale);
			session.setAttribute("pfaccreditati", pfaccreditati);
			
			//bisogna essere passato obbligatoriamente da InfoPagamentoEcontatto per accedere al checkout
			request.getSession().setAttribute("whereYouComeFrom", (String) "InfoPagamentoEcontatto"); 
			/*la sendRedirect crea una nuova richiesta resettando gli attributi normali
			  per questo "whereYouComeFrom" ora Ë att. di sessione */
			
			System.out.println("Sono GestioneOrdine: " + request.getSession().getAttribute("whereYouComeFrom"));
			response.sendRedirect("Checkout.jsp");
		} catch(Exception e){
			e.printStackTrace();
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

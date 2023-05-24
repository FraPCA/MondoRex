package control;

import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.LinkedList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Bean.OrdineBean;
import model.Bean.ProductBean;
import model.Bean.UserBean;
import model.DAO.OrdineDAO_DS;
import model.DAO.ProductDAO_DS;
import model.DAO.UserDAO_DM;


/**
 * Servlet implementation class productControl
 */
@WebServlet("/productControl")
public class productControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	
	
    public productControl() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public static boolean nand(boolean a, boolean b) {
    	return a ? !b : true;
    }
    
    
	public String jsonFromOrdineBeanList(LinkedList<OrdineBean> ob) {
		
		/*
		 * Il JSON è come le tabelle del DB ma sottoforma di array
		 * Il nome della colonna è il nome del campo e i valori della colonna sono contenuti in un array
		 */
		String JSON_emails = "{\"ordini\": { \"email\" : ["; //<-- l'elemento precedente è chiuso da successivo di sotto
		String JSON_timestamps = "],	\"timestamp\" : ["; 
		String JSON_totale = "],	\"totale\" : ["; //array di totali (ordine) etc..
		String JSON_fornitore = "],	\"fornitore\" : ["; 
		String JSON_pf =  "],	\"pf\" : [";
		String JSON_close = "] }}";
		
		
		Iterator<OrdineBean> it = ob.iterator();
		int i = 0;
		
		//popolo l'ennesimo elemento di ogni array ogni occorrenza del db 
		while(it.hasNext()) {
			
			OrdineBean temp = it.next();		
			//il primo elemento non ha bisogno della virgola di separazione...
			if (i == 0) {
				
				JSON_emails = JSON_emails + "\""+ temp.getEmail_utente_ordine() + "\"";
				JSON_timestamps = JSON_timestamps + "\""+ temp.getTimestamp_ordine().toString() + "\"";
				JSON_totale = JSON_totale + "\""+ temp.getPrezzo_totale().toString() + "\"";
				JSON_fornitore = JSON_fornitore + "\""+ temp.getFornitore() + "\"";
				JSON_pf = JSON_pf + "\""+ temp.getUso_PF().toString() + "\"";
			}
			//... gli altri sì
			else {
				
				JSON_emails = JSON_emails + ", " + "\""+ temp.getEmail_utente_ordine()+ "\"";
				JSON_timestamps = JSON_timestamps + ", " + "\""+ temp.getTimestamp_ordine().toString()+ "\"";
				JSON_totale = JSON_totale + ", " + "\""+ temp.getPrezzo_totale().toString()+ "\"";
				JSON_fornitore = JSON_fornitore + ", " + "\""+ temp.getFornitore()+ "\"";
				JSON_pf = JSON_pf + ", " + "\""+ temp.getUso_PF().toString()+ "\"";
			}
		i++;	
		}
		
		//concateno tutti i pezzi del JSON con il primo e lo restituisco
		return JSON_emails = JSON_emails + JSON_timestamps + JSON_totale + JSON_fornitore + JSON_pf + JSON_close;
	} 




	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	
	//LA GET E' PER VEDERE LE TABELLE
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		request.setAttribute("whereYouComeFrom", (String) "AdminControl");
		RequestDispatcher rd = request.getServletContext().getRequestDispatcher("/AdminView.jsp");
		rd.include(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	
	//IL POST E' USATO PER MODIFICARE I DATI
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		
		boolean result = false; //serve per sapere il risultato delle operazioni
		boolean result1= false; //per chi usa due funzioni (solitamente l'update che usa doSave poi doDelete)
		
		
		//** Gli if più esterni sono i possibili valori assunti dai campi di AdminView
		if (request.getParameter("email") != null){
			
			System.out.println("OperazioneClienti");
			if (request.getParameter("OperazioneClienti").equals("Aggiungi")) {
				System.out.println("Ricevuto Aggiungi");
				UserDAO_DM udao = new UserDAO_DM();
				UserBean ub = new UserBean();
				ub.setCognome(request.getParameter("cognome_utente"));
				ub.setEmail(request.getParameter("email"));
				ub.setN_punti_fedelta(Integer.valueOf(request.getParameter("punti_fedelta")));
				ub.setPassword(request.getParameter("password"));
				ub.setNome(request.getParameter("nome_utente"));
				
				try {
					result = udao.doSave(ub); //restituisce lo stato dell'operazione (ok o no) (1 o 0)
					if (result == true) {
						System.out.println("prductControll talking : result is " +result );
						request.getSession(true).setAttribute("errorOperation", (int) 0);
						System.out.println("prductControll talking : session errop is " +request.getAttribute("errorOperation") );
						
						response.sendRedirect("./AdminControl.jsp");
					}
					if (result == false) {
						System.out.println("prductControll talking : result is " +result );
						request.getSession().setAttribute("errorOperation", 1); 
						System.out.println("prductControll talking : session errop is " +request.getAttribute("errorOperation") );
						
						response.sendRedirect("./AdminControl.jsp");	
					}
					
				}catch (SQLException e) {
					System.out.println("SQLError:" + e.getMessage()); 
					}
			}
			
			else if (request.getParameter("OperazioneClienti").equals("Modifica")) {
				UserDAO_DM udao = new UserDAO_DM();
				UserBean ub = new UserBean();
				ub.setCognome(request.getParameter("cognome_utente"));
				ub.setEmail(request.getParameter("email"));
				ub.setN_punti_fedelta(Integer.valueOf(request.getParameter("punti_fedelta")));
				ub.setPassword(request.getParameter("password"));
				ub.setNome(request.getParameter("nome_utente"));
				
				try {
					
					result = udao.doDelete(request.getParameter("email"));
					result1 = udao.doSave(ub);
					
					
					if (nand(result, result1)) { /* se una delle due o entrambe sono sbagliate (0) allora entra nell'if*/
						request.getSession().setAttribute("errorOperation", (int) 1);
						response.sendRedirect("./AdminControl.jsp");
					}
					else { //quando sono entrambe vere
						request.getSession().setAttribute("errorOperation",(int) 0);
						response.sendRedirect("./AdminControl.jsp");	
					}	
					
					
					} catch (SQLException e) {
						System.out.println("SQLError:" + e.getMessage()); 
						}
			
			}
			
			
			
			else if (request.getParameter("OperazioneClienti").equals("Rimuovi")) {
				UserDAO_DM udao = new UserDAO_DM();
				
				try {
					result = udao.doDelete(request.getParameter("email"));
					
					if (result == true) {
						System.out.println("prductControll talking : result is " +result );
						request.getSession(true).setAttribute("errorOperation", (int) 0);
						System.out.println("prductControll talking : session errop is " +request.getAttribute("errorOperation") );
						
						response.sendRedirect("./AdminControl.jsp");
					}
					if (result == false) {
						System.out.println("prductControll talking : result is " +result );
						request.getSession().setAttribute("errorOperation", 1);
						System.out.println("prductControll talking : session errop is " +request.getAttribute("errorOperation") );
						
						response.sendRedirect("./AdminControl.jsp");	
					}
					
				} catch (SQLException e) {
					System.out.println("SQLError:" + e.getMessage()); 
					}
				
			}
			
			
		}
		
		
		
		
		if (request.getParameter("assistenza") != null) {
			//response.sendRedirect("https://accounts.google.com/signin/v2/identifier?continue=https%3A%2F%2Fmail.google.com%2Fmail%2F&service=mail&sacu=1&rip=1&flowName=GlifWebSignIn&flowEntry=ServiceLogin");
			response.sendRedirect("https://mail.google.com/mail/?view=cm&fs=1&to=adminassistenzamondorex@gmail.com&su=Richiesta assistenza");
		}
		
		
		
		
		
		if(request.getParameter("OperazioneProdotto") != null)
		{
				
			if (request.getParameter("OperazioneProdotto").equals("Aggiungi")) {
				ProductDAO_DS pdao = new ProductDAO_DS();
				ProductBean pb = new ProductBean();
				
				pb.setCodice_prodotto(request.getParameter("codice_prodotto"));
				pb.setNome(request.getParameter("nome_prodotto"));
				pb.setPrezzo_netto(Double.valueOf(request.getParameter("prezzo_netto")));
				pb.setPercentuale_sconto(Integer.valueOf(request.getParameter("percentuale_di_sconto")));
				pb.setDescrizione_breve(request.getParameter("descrizione_breve"));
				pb.setIVA(Integer.valueOf(request.getParameter("iva")));
				pb.setDescrizione_dettagliata(request.getParameter("descrizione_dettagliata"));
				pb.setQuantita_disponibile(Integer.valueOf(request.getParameter("quantitadisponibile")));
				pb.setBrand(request.getParameter("brand"));
				pb.setMicrocategoria(request.getParameter("microcat"));
				pb.setMacrocategoria(request.getParameter("macrocat"));
				pb.setLink_immagine(request.getParameter("Linkimmagine")); 
				
				Double prezzo_totale = Double.valueOf(request.getParameter("prezzo_netto"));
				pb.setPrezzo_totale(prezzo_totale);
				
				try {
					result = pdao.doSave(pb);
					if (result == true) {
						System.out.println("prductControll talking : result is " +result );
						request.getSession(true).setAttribute("errorOperation", (int) 0);
						System.out.println("prductControll talking : session errop is " +request.getAttribute("errorOperation") );
						
						response.sendRedirect("./AdminControl.jsp");
					}
					if (result == false) {
						System.out.println("prductControll talking : result is " +result );
						request.getSession().setAttribute("errorOperation", 1);
						System.out.println("prductControll talking : session errop is " +request.getAttribute("errorOperation") );
						
						response.sendRedirect("./AdminControl.jsp");	
					}
					
				} catch (SQLException e) {
					System.out.println("SQLError:" + e.getMessage()); 
					}
			}
			
			
			
			
			
			
			
			else if (request.getParameter("OperazioneProdotto").equals("Rimuovi")) {
				ProductDAO_DS pdao = new ProductDAO_DS();
				try {
					result = pdao.doDelete(request.getParameter("codice_prodotto"));
					if (result == true) {
						System.out.println("prductControll talking : result is " +result );
						request.getSession(true).setAttribute("errorOperation", (int) 0);
						System.out.println("prductControll talking : session errop is " +request.getAttribute("errorOperation") );
						
						response.sendRedirect("./AdminControl.jsp");
					}
					if (result == false) {
						System.out.println("prductControll talking : result is " +result );
						request.getSession().setAttribute("errorOperation", 1);
						System.out.println("prductControll talking : session errop is " +request.getAttribute("errorOperation") );
						
						response.sendRedirect("./AdminControl.jsp");	
					}
					
				} catch (SQLException e) {
					System.out.println("SQLError:" + e.getMessage()); 
					}
			}	
			
			
			
			
			
			
			
			else if (request.getParameter("OperazioneProdotto").equals("Modifica")) {
				ProductDAO_DS pdao = new ProductDAO_DS();
				ProductBean pb = new ProductBean();
				
				pb.setCodice_prodotto(request.getParameter("codice_prodotto"));
				pb.setNome(request.getParameter("nome_prodotto"));
				pb.setPrezzo_netto(Double.valueOf(request.getParameter("prezzo_netto")));
				System.out.println("Arrivato prima della percentuale sconto");
				pb.setPercentuale_sconto(Integer.valueOf(request.getParameter("percentuale_di_sconto")));
				pb.setDescrizione_breve(request.getParameter("descrizione_breve"));
				System.out.println("Arrivato prima dell'iva");
				pb.setIVA(Integer.valueOf(request.getParameter("iva")));
				pb.setDescrizione_dettagliata(request.getParameter("descrizione_dettagliata"));
				System.out.println("Arrivato prima della quantita disponibile");
				pb.setQuantita_disponibile(Integer.valueOf(request.getParameter("quantitadisponibile")));
				pb.setBrand(request.getParameter("brand"));
				pb.setMicrocategoria(request.getParameter("microcat"));
				pb.setMacrocategoria(request.getParameter("macrocat"));
				pb.setLink_immagine(request.getParameter("Linkimmagine"));
				try {
					result = pdao.doDelete(request.getParameter("codice_prodotto"));
					result1 = pdao.doSave(pb); 
					if (nand(result, result1)) {  /* se una delle due o entrambe sono sbagliate (0) allora entra nell'if*/
						request.getSession().setAttribute("errorOperation", (int) 1);
						response.sendRedirect("./AdminControl.jsp");
					}
					else{ //tutte e due ok
						request.getSession().setAttribute("errorOperation",(int) 0);
						response.sendRedirect("./AdminControl.jsp");	
					}	
					System.out.println("productControl, this is result: "+result + " and this is result1: "+result1);
					
					
					} catch (Exception e) {
						e.printStackTrace();
						System.out.println("SQLError:" + e.getMessage()); 
						}
				}		
			}
		
		
		
		
		
		
		
		else if (request.getParameter("mostraOrdini") != null){
			OrdineDAO_DS ords = new OrdineDAO_DS();
			LinkedList<OrdineBean> obL = new LinkedList<>();
			try {
				obL = (LinkedList<OrdineBean>) ords.doRetrieveAll();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				// TODO Auto-generated catch block
				request.getSession().setAttribute("errorOperation", 1);
				e.printStackTrace();
				}
		finally {
		}	
			String JSON = this.jsonFromOrdineBeanList(obL);
			System.out.println("DEBUG: STAMPO JSON DA MANDARE\n" + JSON);
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(JSON);
			request.getSession().setAttribute("errorOperation",  0);
		}		
	
	}	
}

package control;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Locale;

import model.*;
import model.Bean.*;
import model.DAO.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.pdfbox.pdmodel.PDDocument; //nuove librerie (si trovano nella cartella lib di tomcat)
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.fdf.FDFPage;
import org.apache.pdfbox.pdmodel.font.PDType1Font;


/**
 * Servlet implementation class GenFattura
 */
@WebServlet("/GenFattura")
public class GenFattura extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GenFattura() {
        super();
        // TODO Auto-generated constructor stub
    }

    public static double arrotonda(double num, int num_posti) {
  
        BigDecimal bd = BigDecimal.valueOf(num);
        bd = bd.setScale(num_posti, RoundingMode.CEILING); 
        return bd.doubleValue();
    }
    
    public Double getPrezzoNetto(Collection<RedundantProductBean> rpb)
    {
    	double pn = 0.0;
    	for(RedundantProductBean prodottoridondante : rpb)
    	{
    		pn+= (prodottoridondante.getPrezzo_netto() * prodottoridondante.getQuantita());
    	}
    	return pn;
    }
    
    public Double getPrezzoScontato(Collection<RedundantProductBean> rpb)
    {
    	double psc = 0.0;
    	for(RedundantProductBean prodottoridondante : rpb)
    	{
    		psc+= prodottoridondante.getQuantita() * (prodottoridondante.getPrezzo_netto() - ((prodottoridondante.getPrezzo_netto() / 100)) * prodottoridondante.getPercentuale_sconto());
    	}
    	return psc;
    }
    
    public void csprintln(PDPageContentStream cs, String string) throws IOException
    {
    	cs.showText(string);
    	cs.newLine();
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {
		// TODO Auto-generated method stub 
		  response.setHeader("Content-disposition", "attachment; filename=Fattura.pdf");
	      String servletPath = getServletContext().getRealPath("/"); //definiamo il path della servlet
	      System.out.println("Servlet path: " + servletPath);
	      File file = new File(servletPath + "/TemplateFattura12.pdf");
	      PDDocument fattura = PDDocument.load(file);
	      System.out.println("Aperto il documento");
	      
	      //PARTE DI OTTENIMENTO DATI
	      
	      OrdineDAO_DS odao = new OrdineDAO_DS();
	      RedundantProductDAO_DS rpdao = new RedundantProductDAO_DS(); 
	      
	      UserBean ub = (UserBean) request.getSession().getAttribute("utentesessione");
	      OrdineBean ob;
	      ContattoDAO_DS cdao = new ContattoDAO_DS();
	      ContenimentoOrdineBean cb = new ContenimentoOrdineBean();
	      ContenimentoOrdineDAO_DS cbdao = new ContenimentoOrdineDAO_DS();
		try {
			ob = odao.doRetrieveByKey(request.getParameter("emailutenteordinefattura"), Timestamp.valueOf(request.getParameter("timestampordine")));
			System.out.println(ob);
			System.out.println("TIMESTAMP PASSATO: " + ob.getTimestamp_ordine());
			while(cb.getProgessivo() == null) 
			{
				System.out.println(cb.getProgessivo());
				cb = cbdao.doRetrieveByKey(ob.getEmail_utente_ordine(), ob.getTimestamp_ordine().toString());
			}
			ContattoBean cob = cdao.doRetrieveByKey(ob.getEmail_utente_ordine(), ob.getTel_utente());
	      //PARTE DI MODIFICA PDF
	      
	      PDPage page = fattura.getPage(0); //carico la prima pagina del documento TemplateFattura12
	      File file2 = new File(servletPath + "/TemplateFattura2.pdf"); //(seconda pagina della fattura)
	      PDDocument fattura2 = PDDocument.load(file2);
	      System.out.println("Aperto il documento 2");
	      //E' un writer (stream) per il pdf (119)											//aggiungi e non sostituire, non comprimere
	      PDPageContentStream contentStream = new PDPageContentStream(fattura, page, PDPageContentStream.AppendMode.APPEND, false);
	      
	      //INTESTAZIONE DI FATTURA
	      
	      contentStream.beginText(); //inizio a scrivere
	      contentStream.setFont(PDType1Font.TIMES_ROMAN, 9);
	      contentStream.setLeading(11.0f);	//lo spazio tra una nuova riga e l'altra (interlinea)
	      contentStream.newLineAtOffset(42, 780); //prende le coordinate (x,y) del documento in cui iniziare a scrivere
	      
	      csprintln(contentStream, ub.getNome() + " " + ub.getCognome());//NOME DESTINATARIO
	      csprintln(contentStream, cob.getVia());//VIA E NUMERO CIVICO
	      
	                                            //substring per stampare solo le prime due lettere della provincia
	      csprintln(contentStream, cob.getCAP() + " (" + cob.getProvincia().substring(0, 2).toUpperCase() + ")");//CAP CITTA (PROVINCIA)
	      csprintln(contentStream, cob.getN_telefono());//TELEFONO
	      contentStream.endText();
	      
	      //INDIRIZZO DI SPEDIZIONE
	      
	      contentStream.beginText();
	      contentStream.newLineAtOffset(305, 780);
	      csprintln(contentStream, ub.getNome() + " " + ub.getCognome());//NOME DESTINATARIO
	      csprintln(contentStream, cob.getVia());//VIA E NUMERO CIVICO
	      csprintln(contentStream, cob.getCAP() + " (" + cob.getProvincia().substring(0, 2).toUpperCase() + ")");//CAP CITTA (PROVINCIA)
	      csprintln(contentStream, cob.getN_telefono());//TELEFONO
	      contentStream.endText();
	     
	      //NUMERO DOCUMENTO 
	      contentStream.beginText();
	      contentStream.newLineAtOffset(42, 625);
	  
	      csprintln(contentStream, cb.getProgessivo().toString());
	      contentStream.endText();
	      
	    //DATA DOCUMENTO 
	      Timestamp timestamp_ordine = ob.getTimestamp_ordine();
	      DateTimeFormatter df_it2 = DateTimeFormatter.ofPattern("MMMM").withLocale(new Locale("it"));
	      String dataordineformattata = timestamp_ordine.toLocalDateTime().getDayOfMonth() + " " + df_it2.format(timestamp_ordine.toLocalDateTime()) + " " + timestamp_ordine.toLocalDateTime().getYear();
	      contentStream.beginText();
	      contentStream.newLineAtOffset(172, 625);
	      csprintln(contentStream, dataordineformattata);
	      contentStream.endText();
	      
	    //CODICE CLIENTE 
	      contentStream.beginText();
	      contentStream.newLineAtOffset(252, 625);
	      Integer ccli = ob.getEmail_utente_ordine().hashCode();
	      if(ccli < 0) //può uscire un hashCode negativo (brutto)
	      {
	    	  ccli = ccli * -1;
	      }
	      csprintln(contentStream, ccli.toString());
	      contentStream.endText();
	      
	      //PARTITA IVA
	      contentStream.beginText();
	      contentStream.newLineAtOffset(334, 625);
	      csprintln(contentStream, "74684320893");
	      contentStream.endText();
	      
	      
	      //TABELLA PRODOTTI DA GENERARE
	      contentStream.setStrokingColor(Color.black); //colore dei bordi della tabella
	      
	      float RectX = 36; //(x del foglio da cui iniziare a scrivere)
	      float RectY = 526;//(y del foglio da cui iniziare a scrivere) 
	      float RectWidth = 50;// ampiezza di ogni riga
	      float RectHeight = 20;// altezza di ogni riga
	      
	      
		LinkedList<RedundantProductBean> prodotti = new LinkedList<RedundantProductBean>(rpdao.doRetrieveAllConnectedToOrder(ob.getEmail_utente_ordine(), ob.getTimestamp_ordine()));
	
	      int numrows = prodotti.size(); //il numero di righe è il numero di prodotti
	      
	      int currpage = 0; /*conta la pagina corrente se il numero di prodotti sfora le dimensioni della pagina, si passa
	      					ad una nuova */
	      contentStream.setFont(PDType1Font.TIMES_ROMAN, 7);
	    for(int j = 0; j < numrows; j++) //righe
	    {
	      for(int c = 0; c < 8; c++) //colonne, 7 sono il totale dei campi della tabella 
	      {
	    	 switch(c)	/* la larghezza tra una colonna e l'altra può variare,
	    	  			   con lo switch controlliamo a quale distanza scrivere */
	    	 
	    	 {
	    	 case 1:
	    		 RectWidth = 262;
	    		 break;
	    	 case 2:
	    		 RectWidth = 35;
	    		 break;
	    	 case 4:
	    		 RectWidth = 52;
	    		 break;
	    	 case 5:
	    		 RectWidth = 26;
	    		 break;
	    	 case 6:
	    		 RectWidth = 65;
	    		 break;
	    	 case 7:
	    		 RectWidth = 28;
	    		 break;
	    	 }
	    	  contentStream.addRect(RectX, RectY, RectWidth, RectHeight); //aggiungo il rettangolo senza i bordi 
	    	  contentStream.stroke(); //colora i bordi
	    	  contentStream.beginText(); 
	    	  contentStream.newLineAtOffset(RectX+2, RectY + 3); //mi sposto per scrivere nel rettangolo
	    	  contentStream.showText(prodotti.get(j).callEveryGetMethod(c)); //restituisce la Stringa per quell'occorrenza
	    	  contentStream.endText();
		      RectX = RectX + RectWidth; //Per poter scrivere nella colonna successiva
	      }
	      
	      //siamo nella riga successiva
	      RectX = 36; //resettiamo la x della tabella e la larghezza del rettangolo
	      RectWidth = 50; 
	      if((RectY - RectHeight) >= 20)	//HO SPAZIO NELLA PAGINA PER UN'ALTRA RIGA
	      {
	    	  RectY = RectY - RectHeight;  
	      }
	      else
	      {
	    	  //CAMBIA PAGINA
	    	  System.out.println("Finito spazio su questa pagina");
	    	  PDPage newpage = new PDPage(); //creo una nuova pagina
	    	  fattura.addPage(newpage);
	    	  System.out.println("Aggiunta pagina");
	    	  currpage+= 1;
	    	  contentStream.close();
	    	  
	    	  //riapro il contentStream sulla nuova pagina (251)
	    	  contentStream = new PDPageContentStream(fattura, fattura.getPage(currpage), PDPageContentStream.AppendMode.APPEND, false);
	    	  contentStream.setStrokingColor(Color.black);
	    	  RectY = 700; //resetto la y sulla nuova pagina
	      }
	    }
	     
	      contentStream.close();
	      
	      
	      //Aggiungo seconda parte (TemplateFattura2)
	     
	      
	      PDPage paginafinale = fattura2.getPage(0);
	      fattura.addPage(paginafinale);
	      //AGGIUNGI TESTO PARTE FINALE
	      							/* facendo currpage + 1 raggiungiamo la pagina su cui scrivere 
	      							 * anche se abbiamo scritto più di una pagina  
	      							 */
	      contentStream = new PDPageContentStream(fattura, fattura.getPage(currpage + 1), PDPageContentStream.AppendMode.APPEND, false);
	      contentStream.setFont(PDType1Font.TIMES_ROMAN, 9);
	      
	      //SPESE DI CONTRASSEGNO
	      contentStream.beginText();
	      contentStream.newLineAtOffset(45, 750);
	      csprintln(contentStream, "€ 0.00"); //sono sempre zero
	      contentStream.endText();
	      
	    //SPESE DI TRASPORTO
	      contentStream.beginText();
	      contentStream.newLineAtOffset(190, 750);
	      csprintln(contentStream, "€ 0.00");
	      contentStream.endText();
	      
	    //TOTALE MERCE
	      contentStream.beginText();
	      contentStream.newLineAtOffset(300, 750);
	      Double tmerce = getPrezzoScontato(rpdao.doRetrieveAllConnectedToOrder(ub.getEmail(), ob.getTimestamp_ordine()));
	      csprintln(contentStream, String.format("%.2f", tmerce)); //tmerce è il prezzo scontato
	      contentStream.endText();
	      
	    //TOTALE SCONTO
	      contentStream.beginText();
	      contentStream.newLineAtOffset(395, 750);
	      Double prezzoN = getPrezzoNetto(rpdao.doRetrieveAllConnectedToOrder(ub.getEmail(), ob.getTimestamp_ordine()));
	      Double prezzoS = getPrezzoScontato(rpdao.doRetrieveAllConnectedToOrder(ub.getEmail(), ob.getTimestamp_ordine()));
	      csprintln(contentStream, String.format("%.2f", ((prezzoN - prezzoS)))); //ricavo e scrivo lo sconto nella tabella
	      contentStream.endText();
	      
	    //TOTALE IMPONIBILE
	      contentStream.beginText();
	      contentStream.newLineAtOffset(45, 705);
	      csprintln(contentStream, String.format("%.2f", tmerce));
	      contentStream.endText(); 
	      
	    //TOTALE IMPOSTA
	      contentStream.beginText();
	      contentStream.newLineAtOffset(215, 705);
	      
	      //ricavo l'iva con map dalla lista di prodotti ridondanti (308)
	      Integer curriva = (rpdao.doRetrieveAllConnectedToOrder(ub.getEmail(), ob.getTimestamp_ordine()).stream().map(rp -> rp.getIVA()).findFirst()).get();
	      Double timposta = arrotonda((tmerce / 100 ) * curriva, 2);
	      System.out.println("VALORE TIMPOSTA: " + timposta);
	      csprintln(contentStream, String.format("%.2f", timposta));
	      contentStream.endText(); 
	      
	    //TOTALE FATTURA
	      contentStream.beginText();
	      contentStream.newLineAtOffset(385, 705);
	      csprintln(contentStream, ob.getPrezzo_totale().toString());
	      contentStream.endText(); 
	      
	      
	      contentStream.close();
	      
	      //MANDA DOCUMENTO IN OUTPUT
	      
	      fattura.save(response.getOutputStream()); //salva il documeto in una fonte di output in questo caso response
	      fattura.close();
	      fattura2.close();
	      System.out.println("Chiuso il documento");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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

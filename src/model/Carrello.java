package model;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.*;

import model.Bean.ProductBean;
import model.Bean.RedundantProductBean;
import model.DAO.*;
	
public class Carrello{
	private List<RedundantProductBean> listaprodottiordine;

	public Carrello()
	{
		this.listaprodottiordine = new ArrayList<>();
	}

	
	@Override
	public String toString() {
		return "Carrello [listaprodottiordine=" + listaprodottiordine + "]";
	}

	
	public List<RedundantProductBean> getListaprodottiordine() {
		return listaprodottiordine;
	}


	public void setListaprodottiordine(List<RedundantProductBean> listaprodottiordine) {
		this.listaprodottiordine = listaprodottiordine;
	}
	
	public Double getTotaleCarrello() 
	{
		Double totale = 0.0;
		for(RedundantProductBean prodotto: listaprodottiordine)
		{
			System.out.println("DEBUG: " + prodotto + "costototale: " + prodotto.getcostototale());
			totale+=prodotto.getcostototale();
		}
		System.out.println("TOTALE CALCOLATO PRIMA DI FORMATTARE: " + totale);
		DecimalFormat df = new DecimalFormat("#.00");
		try {
			//totale con formato "brutto" --> Stringa --> decimale con formato buono (47)
			return (df.parse(df.format(totale)).doubleValue()); 
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0.0; //Nel caso in cui esca dal try
	}
	 /*Looks through cart to see if it already contains
	   *  an order entry corresponding to item ID. If it does,
	   *  increments the number ordered. If not, looks up
	   *  Item in catalog and adds an order entry for it.
	  */
	  public synchronized void aggiungiProdotto(String codiceProdotto) throws SQLException {
	    RedundantProductBean prodottoordinecurr;
	    ProductDAO_DS pdao = new ProductDAO_DS();
	    ProductBean pb;
		pb = pdao.doRetrieveByKey(codiceProdotto);
		for(int i=0; i<this.listaprodottiordine.size(); i++) {
			prodottoordinecurr= listaprodottiordine.get(i);
			if(prodottoordinecurr.equals(pb.makeRedundant())) //Prodotto esiste ed è uguale a quello attuale
			{
				prodottoordinecurr.incrementaquantita();
				return;
			}
			else
			{
				if(prodottoordinecurr.getCodice_prodotto().equals(codiceProdotto)) //Prodotto esiste ma è diverso da quello attuale, necessario rimuoverlo e riaggiungerlo dopo.
				{																   //ad esempio cambia il prezzo.
					listaprodottiordine.remove(prodottoordinecurr);
			    }
			}
		}
		listaprodottiordine.add(pb.makeRedundant());
	}

	  /* Looks through cart to find order entry corresponding
	   *  to item ID listed. If the designated number
	   *  is positive, sets it. If designated number is 0
	   *  (or, negative due to a user input error), deletes
	   *  item from cart.
	   */
	  
	  public synchronized void impostanumeroordinati(String codiceProdotto,
	                                         int numeroordinati) throws SQLException {
		RedundantProductBean prodottoordinecurr;
		ProductDAO_DS pdao = new ProductDAO_DS();
		ProductBean pb = pdao.doRetrieveByKey(codiceProdotto);
	    for(int i=0; i<listaprodottiordine.size(); i++) {
	      prodottoordinecurr = listaprodottiordine.get(i);
	      if(prodottoordinecurr.equals(pb.makeRedundant())) //Prodotto esiste ed è uguale a quello attuale
		    {
	    	  if(numeroordinati <= 0)
	    	  {
	    		  listaprodottiordine.remove(i);
	    	  }
	    	  else
	    	  {
	    		  prodottoordinecurr.setQuantita(numeroordinati);
	    	  }
	    	  return;
		    }
		    else
		    {
		    	if(prodottoordinecurr.getCodice_prodotto().equals(codiceProdotto)) //Prodotto esiste ma è diverso da quello attuale, necessario rimuoverlo e riaggiungerlo dopo.
		    	{																   //ad esempio � cambiato il prezzo.
		    		listaprodottiordine.remove(prodottoordinecurr);
		    	}
		    }
		    
		}
	    if(numeroordinati > 0)
	    {
	    	RedundantProductBean nuovoprodotto = pb.makeRedundant();
	    	nuovoprodotto.setQuantita(numeroordinati);
	    	listaprodottiordine.add(nuovoprodotto);
	    }
	  }
}
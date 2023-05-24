package model.Bean;

import java.io.Serializable;
 

public class ProductBean implements Serializable{
	
	private String codice_prodotto;
	private String nome;
	private Double prezzo_totale;
	private Double prezzo_netto;
	private Integer percentuale_sconto;
	private Integer IVA;
	private String descrizione_breve;
	private String descrizione_dettagliata;
	private Integer quantita_disponibile;
	private String link_immagine;
	private String brand;
	private String microcategoria;
	private String macrocategoria;
		
	public RedundantProductBean makeRedundant() {
		RedundantProductBean rp = new RedundantProductBean();
		rp.setCodice_prodotto(this.codice_prodotto);
		rp.setNome(this.nome);
		rp.setPrezzo_totale(this.prezzo_totale);
		rp.setPrezzo_netto(this.prezzo_netto);
		rp.setPercentuale_sconto(this.percentuale_sconto);
		rp.setIVA(this.IVA);
		rp.setMicrocategoria(this.microcategoria);
		rp.setMacrocategoria(this.macrocategoria);
		rp.setLink_immagine(this.link_immagine);
		rp.setQuantita(1);
		return rp;
	}
	
	public String getMacrocategoria() {
		return macrocategoria;
	}



	public void setMacrocategoria(String macrocategoria) {
		this.macrocategoria = macrocategoria;
	}




	@Override
	public String toString() {
		return "ProductBean [codice_prodotto=" + codice_prodotto + ", nome=" + nome + ", prezzo_totale=" + prezzo_totale
				+ ", prezzo_netto=" + prezzo_netto + ", percentuale_sconto=" + percentuale_sconto + ", IVA=" + IVA
				+ ", descrizione_breve=" + descrizione_breve + ", descrizione_dettagliata=" + descrizione_dettagliata
				+ ", quantita_disponibile=" + quantita_disponibile + ", link_immagine=" + link_immagine + ", brand="
				+ brand + ", microcategoria=" + microcategoria + ", macrocategoria=" + macrocategoria + "]";
	}



	public Integer getPercentuale_sconto() {
		return percentuale_sconto;
	}



	public void setPercentuale_sconto(Integer percentuale_sconto) {
		this.percentuale_sconto = percentuale_sconto;
	}



	public String getCodice_prodotto() {
		return codice_prodotto;
	}
	public void setCodice_prodotto(String codice_prodotto) {
		this.codice_prodotto = codice_prodotto;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}

	public Double getPrezzo_totale() {
		return prezzo_totale;
	}
	public void setPrezzo_totale(Double prezzo_totale) {
		this.prezzo_totale = prezzo_totale;
	}
	public Double getPrezzo_netto() {
		return prezzo_netto;
	}
	public void setPrezzo_netto(Double prezzo_netto) {
		this.prezzo_netto = prezzo_netto;
	}
	public Integer getIVA() {
		return IVA;
	}
	public void setIVA(Integer iVA) {
		IVA = iVA;
	}
	public String getDescrizione_breve() {
		return descrizione_breve;
	}
	public void setDescrizione_breve(String descrizione_breve) {
		this.descrizione_breve = descrizione_breve;
	}
	public String getDescrizione_dettagliata() {
		return descrizione_dettagliata;
	}
	public void setDescrizione_dettagliata(String descrizione_dettagliata) {
		this.descrizione_dettagliata = descrizione_dettagliata;
	}
	public Integer getQuantita_disponibile() {
		return quantita_disponibile;
	}
	public void setQuantita_disponibile(Integer quantita_disponibile) {
		this.quantita_disponibile = quantita_disponibile;
	}
	public String getLink_immagine() {
		return link_immagine;
	}
	public void setLink_immagine(String link_immagine) {
		this.link_immagine = link_immagine;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getMicrocategoria() {
		return microcategoria;
	}
	public void setMicrocategoria(String microcategoria) {
		this.microcategoria = microcategoria;
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
		
}

package model.Bean;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class ReviewBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	String nome_utente;
	Timestamp timestamp_rec;
	Integer valutazione;
	String descrizione;
	
	public ReviewBean() {
		super();
		this.nome_utente = "constructorGenerated";
		this.timestamp_rec = Timestamp.valueOf(LocalDateTime.now());
		this.valutazione = 0;
		this.descrizione = 	"constructorGenerated";	
	}

	public String getNome_utente() {
		return nome_utente;
	}

	public void setNome_utente(String nome_utente) {
		this.nome_utente = nome_utente;
	}

	public Timestamp getTimestamp_rec() {
		return timestamp_rec;
	}

	public void setTimestamp_rec(Timestamp timestamp_rec) {
		this.timestamp_rec = timestamp_rec;
	}

	public Integer getValutazione() {
		return valutazione;
	}

	public void setValutazione(Integer valutazione) {
		this.valutazione = valutazione;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	@Override
	public String toString() {
		return "ReviewBean [nome_utente=" + nome_utente + ", timestamp_rec=" + timestamp_rec + ", valutazione="
				+ valutazione + ", descrizione=" + descrizione + "]";
	}
	
}

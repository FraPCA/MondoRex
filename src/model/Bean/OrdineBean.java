package model.Bean;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class OrdineBean {
	String email_utente_ordine;
	Timestamp timestamp_ordine;
	Double prezzo_totale;
	String fornitore;
	Integer uso_PF;
	String tel_utente;
	
	public OrdineBean() {
		super();
	}
	public String getEmail_utente_ordine() {
		return email_utente_ordine;
	}
	public void setEmail_utente_ordine(String email_utente_ordine) {
		this.email_utente_ordine = email_utente_ordine;
	}
	public Timestamp getTimestamp_ordine() {
		return timestamp_ordine;
	}
	public void setTimestamp_ordine(Timestamp timestamp_ordine) {
		this.timestamp_ordine = timestamp_ordine;
	}
	public Double getPrezzo_totale() {
		return prezzo_totale;
	}
	public void setPrezzo_totale(Double prezzo_totale) {
		this.prezzo_totale = prezzo_totale;
	}
	public String getFornitore() {
		return fornitore;
	}
	public void setFornitore(String fornitore) {
		this.fornitore = fornitore;
	}
	public Integer getUso_PF() {
		return uso_PF;
	}
	public void setUso_PF(Integer uso_PF) {
		this.uso_PF = uso_PF;
	}
	
	public String getTel_utente() {
		return tel_utente;
	}
	public void setTel_utente(String tel_utente) {
		this.tel_utente = tel_utente;
	}
	public OrdineBean(String email_utente_ordine, Timestamp timestamp_ordine, Double prezzo_totale, String fornitore,
			Integer uso_PF, String tel_utente) {
		super();
		this.email_utente_ordine = email_utente_ordine;
		this.timestamp_ordine = timestamp_ordine;
		this.prezzo_totale = prezzo_totale;
		this.fornitore = fornitore;
		this.uso_PF = uso_PF;
		this.tel_utente = tel_utente;
	}

	
	@Override
	public String toString() {
		return "OrdineBean [email_utente_ordine=" + email_utente_ordine + ", timestamp_ordine=" + timestamp_ordine
				+ ", prezzo_totale=" + prezzo_totale + ", fornitore=" + fornitore + ", uso_PF=" + uso_PF
				+ ", tel_utente=" + tel_utente + "]";
	}
	public String toPersonalHTML() {
		DateTimeFormatter df_it2 = DateTimeFormatter.ofPattern("MMMM").withLocale(new Locale("it"));
		String dataordineformattata = timestamp_ordine.toLocalDateTime().getDayOfMonth() + " " + df_it2.format(timestamp_ordine.toLocalDateTime()) + " " + timestamp_ordine.toLocalDateTime().getYear();
		return "<h2> Ordine effettuato il " + dataordineformattata + "<br> Totale: " + prezzo_totale + "<br>" + "Fornitore del metodo di pagamento utilizzato: " + fornitore + "<br>" + "Punti fedeltà  usati: " + uso_PF + "</h2>";
	}
}

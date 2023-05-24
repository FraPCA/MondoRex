package model.Bean;


public class ContattoBean{
	String email_utente_contatto;
	String N_telefono;
	String regione;
	String provincia;
	String via;
	String CAP;
	String Nazione;
	
	public ContattoBean()
	{
		super();
	}
	
	public ContattoBean(String email_utente_contatto, String n_telefono, String regione, String provincia, String via,
			String cAP, String nazione) {
		super();
		this.email_utente_contatto = email_utente_contatto;
		N_telefono = n_telefono;
		this.regione = regione;
		this.provincia = provincia;
		this.via = via;
		CAP = cAP;
		Nazione = nazione;
	}
	public String getEmail_utente_contatto() {
		return email_utente_contatto;
	}
	public void setEmail_utente_contatto(String email_utente_contatto) {
		this.email_utente_contatto = email_utente_contatto;
	}
	public String getN_telefono() {
		return N_telefono;
	}
	public void setN_telefono(String n_telefono) {
		N_telefono = n_telefono;
	}
	public String getRegione() {
		return regione;
	}
	public void setRegione(String regione) {
		this.regione = regione;
	}
	public String getProvincia() {
		return provincia;
	}
	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}
	public String getVia() {
		return via;
	}
	public void setVia(String via) {
		this.via = via;
	}
	public String getCAP() {
		return CAP;
	}
	public void setCAP(String cAP) {
		CAP = cAP;
	}
	public String getNazione() {
		return Nazione;
	}
	public void setNazione(String nazione) {
		Nazione = nazione;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((CAP == null) ? 0 : CAP.hashCode());
		result = prime * result + ((N_telefono == null) ? 0 : N_telefono.hashCode());
		result = prime * result + ((Nazione == null) ? 0 : Nazione.hashCode());
		result = prime * result + ((email_utente_contatto == null) ? 0 : email_utente_contatto.hashCode());
		result = prime * result + ((provincia == null) ? 0 : provincia.hashCode());
		result = prime * result + ((regione == null) ? 0 : regione.hashCode());
		result = prime * result + ((via == null) ? 0 : via.hashCode());
		return result;
	}
	
	public boolean available(ContattoBean cb)
	{	  //confronta di campi di questa istanza con quelli di cb	
		if((this.getEmail_utente_contatto().equals(cb.getEmail_utente_contatto())) && (this.getN_telefono().equals(cb.getN_telefono())))
		{
			return false;
		}
		else return true;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ContattoBean other = (ContattoBean) obj;
		if (CAP == null) {
			if (other.CAP != null)
				return false;
		} else if (!CAP.equals(other.CAP))
			return false;
		if (N_telefono == null) {
			if (other.N_telefono != null)
				return false;
		} else if (!N_telefono.equals(other.N_telefono))
			return false;
		if (Nazione == null) {
			if (other.Nazione != null)
				return false;
		} else if (!Nazione.equals(other.Nazione))
			return false;
		if (email_utente_contatto == null) {
			if (other.email_utente_contatto != null)
				return false;
		} else if (!email_utente_contatto.equals(other.email_utente_contatto))
			return false;
		if (provincia == null) {
			if (other.provincia != null)
				return false;
		} else if (!provincia.equals(other.provincia))
			return false;
		if (regione == null) {
			if (other.regione != null)
				return false;
		} else if (!regione.equals(other.regione))
			return false;
		if (via == null) {
			if (other.via != null)
				return false;
		} else if (!via.equals(other.via))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "ContattoBean [email_utente_contatto=" + email_utente_contatto + ", N_telefono=" + N_telefono
				+ ", regione=" + regione + ", provincia=" + provincia + ", via=" + via + ", CAP=" + CAP + ", Nazione="
				+ Nazione + "]";
	}
	
	public String toPersonalHTML() {
		return "Informazioni di contatto per " + this.email_utente_contatto + "<br>Numero di telefono: " + this.N_telefono + "<br>" + "Nazione: " + this.Nazione + "<br>Informazioni di spedizione<br> " + this.regione + "," + this.via + " " + this.CAP + " (" + this.getProvincia().substring(0, 2).toUpperCase() + ")";     
	}
}
package model.Bean;

import java.sql.Date;

public class MetodoPagamentoBean{
	String numero; //numero di carta
	Integer CVCCVV;
	String fornitore;
	String tipo;
	Date scadenza;
	String email_utente_pagamento;
	
	
	
	public MetodoPagamentoBean(String numero, Integer cVCCVV, String fornitore, String tipo, Date scadenza,
			String email_utente_pagamento) {
		super();
		this.numero = numero;
		CVCCVV = cVCCVV;
		this.fornitore = fornitore;
		this.tipo = tipo;
		this.scadenza = scadenza;
		this.email_utente_pagamento = email_utente_pagamento;
	}

	public Date getScadenza() {
		return scadenza;
	}

	public void setScadenza(Date scadenza) {
		this.scadenza = scadenza;
	}

	public boolean available(MetodoPagamentoBean mpb)
	{
		if((this.getEmail_utente_pagamento().equals(mpb.getEmail_utente_pagamento())) && (this.getScadenza().equals(mpb.getScadenza())) && (this.getCVCCVV().equals(mpb.getCVCCVV())))
		{
			return false;
		}
		else return true;
	}
	
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((CVCCVV == null) ? 0 : CVCCVV.hashCode());
		result = prime * result + ((email_utente_pagamento == null) ? 0 : email_utente_pagamento.hashCode());
		result = prime * result + ((fornitore == null) ? 0 : fornitore.hashCode());
		result = prime * result + ((numero == null) ? 0 : numero.hashCode());
		result = prime * result + ((scadenza == null) ? 0 : scadenza.hashCode());
		result = prime * result + ((tipo == null) ? 0 : tipo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MetodoPagamentoBean other = (MetodoPagamentoBean) obj;
		if (CVCCVV == null) {
			if (other.CVCCVV != null)
				return false;
		} else if (!CVCCVV.equals(other.CVCCVV))
			return false;
		if (email_utente_pagamento == null) {
			if (other.email_utente_pagamento != null)
				return false;
		} else if (!email_utente_pagamento.equals(other.email_utente_pagamento))
			return false;
		if (fornitore == null) {
			if (other.fornitore != null)
				return false;
		} else if (!fornitore.equals(other.fornitore))
			return false;
		if (numero == null) {
			if (other.numero != null)
				return false;
		} else if (!numero.equals(other.numero))
			return false;
		if (scadenza == null) {
			if (other.scadenza != null)
				return false;
		} else if (!scadenza.equals(other.scadenza))
			return false;
		if (tipo == null) {
			if (other.tipo != null)
				return false;
		} else if (!tipo.equals(other.tipo))
			return false;
		return true;
	}

	public String getEmail_utente_pagamento() {
		return email_utente_pagamento;
	}

	public void setEmail_utente_pagamento(String email_utente_pagamento) {
		this.email_utente_pagamento = email_utente_pagamento;
	}

	public MetodoPagamentoBean()
	{
		super();
	}
	
	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public Integer getCVCCVV() {
		return CVCCVV;
	}

	public void setCVCCVV(Integer cVCCVV) {
		CVCCVV = cVCCVV;
	}

	public String getFornitore() {
		return fornitore;
	}

	public void setFornitore(String fornitore) {
		this.fornitore = fornitore;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	@Override
	public String toString() {
		return "MetodoPagamentoBean [numero=" + numero + ", CVCCVV=" + CVCCVV + ", fornitore=" + fornitore + ", tipo="
				+ tipo + ", scadenza=" + scadenza + ", codice_utente=" + email_utente_pagamento + "]";
	}
	
	public String toPersonalHTML()
	{
		return this.fornitore + " ************" + this.numero.substring(12) + " <br>" +  this.tipo;
	}
	
}
package model.Bean;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class ContenimentoOrdineBean {
	String email;
	Timestamp timestamp_contenimento;
	List<String> codice_prodotto_ridondante;
	List<RedundantProductBean> prodotti_ridondanti;
	Integer progessivo;
	
	public ContenimentoOrdineBean()
	{
		this.codice_prodotto_ridondante = new ArrayList<>();
		this.prodotti_ridondanti = new ArrayList<>();
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Timestamp getTimestamp_contenimento() {
		return timestamp_contenimento;
	}
	public void setTimestamp_contenimento(Timestamp timestamp_contenimento) {
		this.timestamp_contenimento = timestamp_contenimento;
	}
	public List<String> getCodice_prodotto_ridondante() {
		return codice_prodotto_ridondante;
	}
	public void setCodice_prodotto_ridondante(String string) {
		this.codice_prodotto_ridondante.add(string);
	}
	public List<RedundantProductBean> getProdotti_ridondanti() {
		return prodotti_ridondanti;
	}
	public void setProdotti_ridondanti(RedundantProductBean prodotti_ridondanti) {
		this.prodotti_ridondanti.add(prodotti_ridondanti);
	}
	public Integer getProgessivo() {
		return progessivo;
	}
	public void setProgessivo(Integer progessivo) {
		this.progessivo = progessivo;
	}
	
	
	@Override
	public String toString() {
		return "ContenimentoOrdineBean [email=" + email + ", timestamp_contenimento=" + timestamp_contenimento
				+ ", codice_prodotto_ridondante=" + codice_prodotto_ridondante + ", prodotti_ridondanti="
				+ prodotti_ridondanti + ", progessivo=" + progessivo + "]";
	}
	
	
	
}

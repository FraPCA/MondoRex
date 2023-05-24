package model.Bean;

import java.io.Serializable;

public class RedundantProductBean implements Serializable{
	
	private String codice_prodotto;
	private String nome;
	private Double prezzo_totale;
	private Double prezzo_netto;
	private Integer percentuale_sconto;
	private Integer IVA;
	private Integer quantita;
	private String microcategoria;
	private String macrocategoria;
	private String link_immagine;
	
	
	public String getLink_immagine() {
	return link_immagine;
	}


	public void setLink_immagine(String link_immagine) {
	this.link_immagine = link_immagine;
	}


		public RedundantProductBean() {
		super();
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


	public Integer getPercentuale_sconto() {
		return percentuale_sconto;
	}


	public void setPercentuale_sconto(Integer percentuale_sconto) {
		this.percentuale_sconto = percentuale_sconto;
	}


	public Integer getIVA() {
		return IVA;
	}


	public void setIVA(Integer iVA) {
		IVA = iVA;
	}


	public Integer getQuantita() {
		return quantita;
	}


	public void setQuantita(Integer quantita) {
		this.quantita = quantita;
	}


	public String getMicrocategoria() {
		return microcategoria;
	}


	public void setMicrocategoria(String microcategoria) {
		this.microcategoria = microcategoria;
	}


	public String getMacrocategoria() {
		return macrocategoria;
	}


	public void setMacrocategoria(String macrocategoria) {
		this.macrocategoria = macrocategoria;
	}


		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */


		public void incrementaquantita() {
			this.setQuantita(this.quantita + 1);
		}


		public void rimuoviprodotto() {
			this.setQuantita(0);
		}


		
		public double getcostototale() {
		
			return (this.quantita * this.getPrezzo_totale());
		}


		@Override
		public String toString() {
			return "RedundantProductBean [codice_prodotto=" + codice_prodotto + ", nome=" + nome + ", prezzo_totale="
					+ prezzo_totale + ", prezzo_netto=" + prezzo_netto + ", percentuale_sconto=" + percentuale_sconto
					+ ", IVA=" + IVA + ", quantita=" + quantita + ", microcategoria=" + microcategoria
					+ ", macrocategoria=" + macrocategoria + ", link_immagine=" + link_immagine + "]";
		}

		
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((IVA == null) ? 0 : IVA.hashCode());
			result = prime * result + ((codice_prodotto == null) ? 0 : codice_prodotto.hashCode());
			result = prime * result + ((link_immagine == null) ? 0 : link_immagine.hashCode());
			result = prime * result + ((macrocategoria == null) ? 0 : macrocategoria.hashCode());
			result = prime * result + ((microcategoria == null) ? 0 : microcategoria.hashCode());
			result = prime * result + ((nome == null) ? 0 : nome.hashCode());
			result = prime * result + ((percentuale_sconto == null) ? 0 : percentuale_sconto.hashCode());
			result = prime * result + ((prezzo_netto == null) ? 0 : prezzo_netto.hashCode());
			result = prime * result + ((prezzo_totale == null) ? 0 : prezzo_totale.hashCode());
			result = prime * result + ((quantita == null) ? 0 : quantita.hashCode());
			return result;
		}


		public boolean equalsDB(Object obj) { //è una equals che controlla solo alcuni campi
			if (this == obj)				  //ad esempio non controlla link_immagine
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			RedundantProductBean other = (RedundantProductBean) obj;
			if (IVA == null) {
				if (other.IVA != null)
					return false;
			} else if (!IVA.equals(other.IVA))
				return false;
			if (codice_prodotto == null) {
				if (other.codice_prodotto != null)
					return false;
			} else if (!codice_prodotto.equals(other.codice_prodotto))
				return false;
			if (macrocategoria == null) {
				if (other.macrocategoria != null)
					return false;
			} else if (!macrocategoria.equals(other.macrocategoria))
				return false;
			if (microcategoria == null) {
				if (other.microcategoria != null)
					return false;
			} else if (!microcategoria.equals(other.microcategoria))
				return false;
			if (nome == null) {
				if (other.nome != null)
					return false;
			} else if (!nome.equals(other.nome))
				return false;
			if (percentuale_sconto == null) {
				if (other.percentuale_sconto != null)
					return false;
			} else if (!percentuale_sconto.equals(other.percentuale_sconto))
				return false;
			if (prezzo_netto == null) {
				if (other.prezzo_netto != null)
					return false;
			} else if (!prezzo_netto.equals(other.prezzo_netto))
				return false;
			if (prezzo_totale == null) {
				if (other.prezzo_totale != null)
					return false;
			} else if (!prezzo_totale.equals(other.prezzo_totale))
				return false;
			if (quantita == null) {
				if (other.quantita != null)
					return false;
			} else if (!quantita.equals(other.quantita))
				return false;
			return true;
		}
		
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			RedundantProductBean other = (RedundantProductBean) obj;
			if (IVA == null) {
				if (other.IVA != null)
					return false;
			} else if (!IVA.equals(other.IVA))
				return false;
			if (codice_prodotto == null) {
				if (other.codice_prodotto != null)
					return false;
			} else if (!codice_prodotto.equals(other.codice_prodotto))
				return false;
			if (link_immagine == null) {
				if (other.link_immagine != null)
					return false;
			} else if (!link_immagine.equals(other.link_immagine))
				return false;
			if (macrocategoria == null) {
				if (other.macrocategoria != null)
					return false;
			} else if (!macrocategoria.equals(other.macrocategoria))
				return false;
			if (microcategoria == null) {
				if (other.microcategoria != null)
					return false;
			} else if (!microcategoria.equals(other.microcategoria))
				return false;
			if (nome == null) {
				if (other.nome != null)
					return false;
			} else if (!nome.equals(other.nome))
				return false;
			if (percentuale_sconto == null) {
				if (other.percentuale_sconto != null)
					return false;
			} else if (!percentuale_sconto.equals(other.percentuale_sconto))
				return false;
			if (prezzo_netto == null) {
				if (other.prezzo_netto != null)
					return false;
			} else if (!prezzo_netto.equals(other.prezzo_netto))
				return false;
			if (prezzo_totale == null) {
				if (other.prezzo_totale != null)
					return false;
			} else if (!prezzo_totale.equals(other.prezzo_totale))
				return false;
			if (quantita == null) {
				if (other.quantita != null)
					return false;
			} else if (!quantita.equals(other.quantita))
				return false;
			return true;
		}
		
		public String callEveryGetMethod(int i) { //i è la colonna della fattura
			String result = new String();
			
			switch (i) {
			case 0:
				result = this.getCodice_prodotto().toString();
				break;
			case 1:
				result = this.getNome().toString();
				break;
			case 2: 
				result = this.getQuantita().toString();
				break;
			case 3 : 
				result = "pz";
				break;
			case 4 :
				result = "€ " + this.getPrezzo_netto().toString();
				break;
			case 5:
				result = this.getPercentuale_sconto().toString();
				break;
			case 6:
				result = "€ " + String.format("%.2f", (this.getQuantita() * (this.getPrezzo_netto() - ((this.getPrezzo_netto() / 100)) * this.getPercentuale_sconto())));
				break;
			case 7:
				result = this.getIVA().toString();
				break;
			}
			
			return result;
		}
		
}
		

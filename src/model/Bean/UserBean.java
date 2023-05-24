package model.Bean;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class UserBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	String email;
	String nome;
	String password;
	String cognome;
	Timestamp timestamp_registrazione;
	Integer N_punti_fedelta;
	public UserBean() {
		super();
		this.email = "constructorGenerated";
		this.nome = "constructorGenerated";
		this.cognome = "constructorGenerated";
		this.timestamp_registrazione = Timestamp.valueOf(LocalDateTime.now());
		this.N_punti_fedelta = 0;		
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public Timestamp getTimestamp_registrazione() {
		return timestamp_registrazione;
	}

	public void setTimestamp_registrazione(Timestamp timestamp_registrazione) {
		this.timestamp_registrazione = timestamp_registrazione;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((N_punti_fedelta == null) ? 0 : N_punti_fedelta.hashCode());
		result = prime * result + ((cognome == null) ? 0 : cognome.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((timestamp_registrazione == null) ? 0 : timestamp_registrazione.hashCode());
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
		UserBean other = (UserBean) obj;
		if (N_punti_fedelta == null) {
			if (other.N_punti_fedelta != null)
				return false;
		} else if (!N_punti_fedelta.equals(other.N_punti_fedelta))
			return false;
		if (cognome == null) {
			if (other.cognome != null)
				return false;
		} else if (!cognome.equals(other.cognome))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (timestamp_registrazione == null) {
			if (other.timestamp_registrazione != null)
				return false;
		} else if (!timestamp_registrazione.equals(other.timestamp_registrazione))
			return false;
		return true;
	}

	public Integer getN_punti_fedelta() {
		return N_punti_fedelta;
	}

	public void setN_punti_fedelta(Integer n_punti_fedelta) {
		this.N_punti_fedelta = n_punti_fedelta;
	}

	@Override
	public String toString() {
		return "UserBean [email=" + email + ", nome=" + nome + ", password=" + password + ", cognome=" + cognome
				+ ", timestamp_registrazione=" + timestamp_registrazione + ", N_punti_fedelta=" + N_punti_fedelta + "]";
	}
}

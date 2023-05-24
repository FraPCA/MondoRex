package model.Bean;

import java.sql.SQLException;
import java.util.Collection;

public interface ContattoModel {
	public void doSave(ContattoBean Contatto) throws SQLException;

	public boolean doDelete(String email_utente_contatto, String N_telefono) throws SQLException;

	public ContattoBean doRetrieveByKey(String email_utente_contatto, String N_telefono) throws SQLException;
	
	public Collection<ContattoBean> doRetrieveAll() throws SQLException;
	public Collection<ContattoBean> doRetrieveAllForUtente(String email_utente_contatto) throws SQLException;
	
}
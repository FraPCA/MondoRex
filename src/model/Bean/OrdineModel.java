package model.Bean;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Collection;

public interface OrdineModel {
	public void doSave(OrdineBean Ordine) throws SQLException;

	public boolean doDelete(String email_utente_ordine, Timestamp timestamp_ordine) throws SQLException;

	public OrdineBean doRetrieveByKey(String email_utente_ordine, Timestamp timestamp_ordine) throws SQLException;
	
	public Collection<OrdineBean> doRetrieveAll() throws SQLException;
	
	public Collection<OrdineBean> doRetrieveAllClient(String email_utente_ordine) throws SQLException;
	
	public Collection<OrdineBean> doRetrieveAllClientBetween(String startinterval, String endinterval, String email_utente_ordine) throws SQLException;
	
	public Collection<OrdineBean> doRetrieveAllBetween(String startinterval, String endinterval) throws SQLException;
	
}
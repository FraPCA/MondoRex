package model.Bean;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Collection;

public interface RedundantProductModel {
	
	public void doSave(RedundantProductBean p) throws SQLException;
	
	public boolean doDelete(String codice_prodotto) throws SQLException;
	
	public RedundantProductBean doRetrieveByKey(String codice_prodotto) throws SQLException; 
	
	public Collection<RedundantProductBean> doRetrieveAll(String order, String where) throws SQLException;
	
	public Collection<RedundantProductBean> doRetrieveAllConnectedToOrder(String email, Timestamp timestamp_ordine) throws SQLException;

}

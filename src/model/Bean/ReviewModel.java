package model.Bean;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Collection;

public interface ReviewModel {
	public void doSave(ReviewBean review) throws SQLException;

	public boolean doDelete(String nome_utente, Timestamp timestamp_rec) throws SQLException;

	public ReviewBean doRetrieveByKey(String nome_utente, Timestamp timestamp_rec) throws SQLException;
	
	public Collection<ReviewBean> doRetrieveAll(String order, String where) throws SQLException;
	public Collection<ReviewBean> doRetrieveAll(String order) throws SQLException;
	public Collection<ReviewBean> doRetrieveOnProduct(String order, String codice_prodotto) throws SQLException;
	public int getAverageForProduct(String codice_prodotto) throws SQLException;
	
}
package model.Bean;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Collection;

public interface ContenimentoOrdineModel {
	
	public void doSave(ContenimentoOrdineBean t) throws SQLException;

	public boolean doDelete(String t, Timestamp v) throws SQLException;

	public ContenimentoOrdineBean doRetrieveByKey(String t, String v) throws SQLException;

	ContenimentoOrdineBean doRetrieveByKey(String t, Timestamp v) throws SQLException;
	
}

package model.Bean;

import java.sql.SQLException;
import java.util.Collection;

public interface ProductModel {
	
	public boolean doSave(ProductBean p) throws SQLException; //true se è tutto okay, false altrimenti
	
	public boolean doDelete(String codice_prodotto) throws SQLException; //stessa cosa di doSave
	
	public ProductBean doRetrieveByKey(String codice_prodotto) throws SQLException; 
	
	public Collection<ProductBean> doRetrieveAll(String order) throws SQLException;

	Collection<ProductBean> doRetrieveAll(String order, String group) throws SQLException;
	
	public Collection<ProductBean> doRetrieveByCategory(String macroCategoria) throws SQLException;
	
}

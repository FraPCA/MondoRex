package model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import model.Bean.RedundantProductBean;
import model.Bean.RedundantProductModel;

public class RedundantProductDAO_DS implements RedundantProductModel{

	
	private static DataSource ds;
	
	static {
		try {
			System.out.println("Entrato nel try dell'inizializzazione");
			Context initCtx = new InitialContext();
			System.out.println("Creato InitialContext");
			Context envCtx = (Context) initCtx.lookup("java:comp/env");
			System.out.println("Creato envCTX");

			ds = (DataSource) envCtx.lookup("jdbc/MondoRex");
			System.out.println("Creata la ds");
			ds.toString();

		} catch (NamingException e) {
			System.out.println("Error:" + e.getMessage());
		}
	}
	
	
	@Override
	public void doSave(RedundantProductBean r) throws SQLException {
		
		Connection connection = null;
		PreparedStatement preparedStatementCarrello = null; //� la query che riempie prodotto_ridondante
		
		connection = ds.getConnection();
		
		String insertSQLcarrello = "Insert into prodotto_ridondante (codice_prodotto, nome, quantita, percentuale_di_sconto, IVA, microcategoria, macrocategoria, prezzo_netto, link_immagine) "
				+ 											"values (		?		,  ?  , 	?	, 		?			   ,  ? ,		? 		,		?		, 	?	, ?	  )";
		
		preparedStatementCarrello = connection.prepareStatement(insertSQLcarrello);
			preparedStatementCarrello.setString(1, r.getCodice_prodotto());
			preparedStatementCarrello.setString(2, r.getNome());
			preparedStatementCarrello.setInt(3, r.getQuantita());
			preparedStatementCarrello.setInt(4, r.getPercentuale_sconto());
			preparedStatementCarrello.setInt(5, r.getIVA());
			preparedStatementCarrello.setString(6, r.getMicrocategoria());
			preparedStatementCarrello.setString(7, r.getMacrocategoria());
			preparedStatementCarrello.setDouble(8, r.getPrezzo_netto());
			preparedStatementCarrello.setString(9, r.getLink_immagine());
			
			preparedStatementCarrello.executeUpdate();
	}

	@Override
	public boolean doDelete(String codice_prodotto) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		int result = 0;

		String deleteSQL = "DELETE FROM prodotto_ridondante WHERE codice_prodotto = ?";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(deleteSQL);
			preparedStatement.setString(1, codice_prodotto);

			result = preparedStatement.executeUpdate();

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				if (connection != null)
					connection.close();
			}
		}
		return (result != 0);
		
	}

	@Override
	public RedundantProductBean doRetrieveByKey(String codice_prodotto) throws SQLException {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		RedundantProductBean bean = new RedundantProductBean();

		String selectSQL = "SELECT * FROM prodotto_ridondante " + " WHERE codice_prodotto = ?";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, codice_prodotto);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				
				bean.setCodice_prodotto(rs.getString("codice_prodotto"));
				bean.setNome(rs.getString("nome"));
				bean.setPrezzo_totale(rs.getDouble("prezzo_totale"));
				bean.setPrezzo_netto(rs.getDouble("prezzo_netto"));
				bean.setPercentuale_sconto(rs.getInt("percentuale_di_sconto"));
				bean.setIVA(rs.getInt("IVA"));	
				bean.setQuantita(rs.getInt("quantita"));
				bean.setMicrocategoria(rs.getString("microcategoria"));
				bean.setMacrocategoria(rs.getString("macrocategoria"));
				bean.setLink_immagine(rs.getString("link_immagine"));
			}

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				if (connection != null)
					connection.close();
			}
		}
		return bean;
	}

	@Override
	public Collection<RedundantProductBean> doRetrieveAll(String order, String where) throws SQLException {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Collection<RedundantProductBean> products = new LinkedList<RedundantProductBean>();

		String selectSQL = "SELECT * FROM prodotto_ridondante ";

		if (order != null && !order.equals("")) {
			selectSQL += " ORDER BY " + order;
		}
		
		else if (where != null && !where.equals("")) {
			selectSQL += " WHERE " + where + ";";
		}

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				RedundantProductBean bean = new RedundantProductBean();

				bean.setCodice_prodotto(rs.getString("codice_prodotto"));
				bean.setNome(rs.getString("nome"));
				bean.setPrezzo_totale(rs.getDouble("prezzo_totale"));
				bean.setPrezzo_netto(rs.getDouble("prezzo_netto"));
				bean.setPercentuale_sconto(rs.getInt("percentuale_di_sconto"));
				bean.setIVA(rs.getInt("IVA"));	
				bean.setQuantita(rs.getInt("quantita"));
				bean.setMicrocategoria(rs.getString("microcategoria"));
				bean.setMacrocategoria(rs.getString("macrocategoria"));
				bean.setLink_immagine(rs.getString("link_immagine"));
				products.add(bean);
			}

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				if (connection != null)
					connection.close();
			}
		}
		return products;
	}

	@Override
	public Collection<RedundantProductBean> doRetrieveAllConnectedToOrder(String email, Timestamp timestamp_ordine)
			throws SQLException {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Collection<RedundantProductBean> products = new ArrayList<RedundantProductBean>();

		String selectSQL = "SELECT codice_prodotto, nome, quantita, prodotto_ridondante.prezzo_totale, prezzo_netto, percentuale_di_sconto, IVA, microcategoria, macrocategoria, link_immagine from (contenimento_ordine JOIN prodotto_ridondante ON codice_prodotto_ridondante = codice_prodotto) WHERE (email_utente_contenimento = ? AND timestamp_ordine_contenimento = ? );";
		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, email);
			preparedStatement.setTimestamp(2, timestamp_ordine);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				RedundantProductBean bean = new RedundantProductBean();

				bean.setCodice_prodotto(rs.getString("codice_prodotto"));
				bean.setNome(rs.getString("nome"));
				bean.setPrezzo_totale(rs.getDouble("prezzo_totale"));
				bean.setPrezzo_netto(rs.getDouble("prezzo_netto"));
				bean.setPercentuale_sconto(rs.getInt("percentuale_di_sconto"));
				bean.setIVA(rs.getInt("IVA"));	
				bean.setQuantita(rs.getInt("quantita"));
				bean.setMicrocategoria(rs.getString("microcategoria"));
				bean.setMacrocategoria(rs.getString("macrocategoria"));
				bean.setLink_immagine(rs.getString("link_immagine"));
				products.add(bean);
			}

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				if (connection != null)
					connection.close();
			}
		}
		return products;
		}

}

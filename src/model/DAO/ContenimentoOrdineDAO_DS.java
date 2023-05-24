package model.DAO;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Collection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import model.Bean.ContenimentoOrdineBean;
import model.Bean.ContenimentoOrdineModel;
import model.Bean.RedundantProductBean;
import model.Bean.UserBean;
import model.DAO.RedundantProductDAO_DS;

public class ContenimentoOrdineDAO_DS implements ContenimentoOrdineModel{

private static DataSource ds;
	
	static {
		try {
			Context initCtx = new InitialContext();
			Context envCtx = (Context) initCtx.lookup("java:comp/env");

			ds = (DataSource) envCtx.lookup("jdbc/MondoRex");

		} catch (NamingException e) {
			System.out.println("Error:" + e.getMessage());
		}
	}
	
	public ContenimentoOrdineDAO_DS() {
		;
	}

	
	private static final String TABLE_NAME = "contenimento_ordine";
	
	@Override
	public void doSave(ContenimentoOrdineBean t) throws SQLException {
		// TODO Auto-generated method stub

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String insertSQL = "INSERT INTO " + ContenimentoOrdineDAO_DS.TABLE_NAME
				+ " VALUES (?, ?, ?)";

		try {
			connection = ds.getConnection();
			
			for (int i = 0; i < t.getProdotti_ridondanti().size(); i++) {
				
			
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setString(1, t.getEmail());
			preparedStatement.setTimestamp(2, t.getTimestamp_contenimento());
			preparedStatement.setString(3, String.valueOf(t.getProdotti_ridondanti().get(i)));

			preparedStatement.executeUpdate();

			connection.commit();
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

	}

	@Override
	public boolean doDelete(String t, Timestamp v) throws SQLException {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		int result = 0;

		String deleteSQL = "DELETE FROM " + ContenimentoOrdineDAO_DS.TABLE_NAME + " WHERE email_utente_contenimento = ? and timestamp_ordine_contenimento = ?";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(deleteSQL);
			preparedStatement.setString(1, t);
			preparedStatement.setTimestamp(2, v);

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
	public ContenimentoOrdineBean doRetrieveByKey(String t, String v) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		System.out.println("Ottenuto timestamp " + v);
		
		ContenimentoOrdineBean bean = new ContenimentoOrdineBean();

		String selectSQL = "SELECT * FROM contenimento_ordine "/* + ContenimentoOrdineDAO_DM.TABLE_NAME */+ " WHERE (email_utente_contenimento = ? AND timestamp_ordine_contenimento = ?);";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, t);
			preparedStatement.setString(2, v);

			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();
			
			RedundantProductBean rpb = new RedundantProductBean();
			RedundantProductDAO_DS rpbdao = new RedundantProductDAO_DS();
			while (rs.next()) {
				if(rs.isFirst())
				{
					bean.setEmail(rs.getString("email_utente_contenimento"));
					bean.setTimestamp_contenimento(rs.getTimestamp("timestamp_ordine_contenimento"));
					bean.setProgessivo(rs.getInt("progressivo_ordine"));
				}
				rpb = new RedundantProductBean();
				rpbdao = new RedundantProductDAO_DS();
				rpb = rpbdao.doRetrieveByKey(rs.getString("codice_prodotto_ridondante"));
				bean.setCodice_prodotto_ridondante(rs.getString("codice_prodotto_ridondante"));
				bean.setProdotti_ridondanti(rpb);
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
		System.out.println("DEBUG: " + bean);
		return bean;

	}
	
	@Override
	public ContenimentoOrdineBean doRetrieveByKey(String t, Timestamp v) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		System.out.println("Ottenuto timestamp " + v);
		
		ContenimentoOrdineBean bean = new ContenimentoOrdineBean();

		String selectSQL = "SELECT * FROM contenimento_ordine "/* + ContenimentoOrdineDAO_DM.TABLE_NAME */+ " WHERE (email_utente_contenimento = ? AND timestamp_ordine_contenimento = ?);";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, t);
			preparedStatement.setTimestamp(2, v);

			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();
			
			RedundantProductBean rpb = new RedundantProductBean();
			RedundantProductDAO_DS rpbdao = new RedundantProductDAO_DS();
			while (rs.next()) {
				if(rs.isFirst())
				{
					bean.setEmail(rs.getString("email_utente_contenimento"));
					bean.setTimestamp_contenimento(rs.getTimestamp("timestamp_ordine_contenimento"));
					bean.setProgessivo(rs.getInt("progressivo_ordine"));
				}
				rpb = new RedundantProductBean();
				rpbdao = new RedundantProductDAO_DS();
				rpb = rpbdao.doRetrieveByKey(rs.getString("codice_prodotto_ridondante"));
				bean.setCodice_prodotto_ridondante(rs.getString("codice_prodotto_ridondante"));
				bean.setProdotti_ridondanti(rpb);
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
		System.out.println("DEBUG: " + bean);
		return bean;

	}

	public ContenimentoOrdineBean popolaListaDiProdotti(ContenimentoOrdineBean cb) {
		ContenimentoOrdineDAO_DS cdm = new ContenimentoOrdineDAO_DS();
		try {
			cdm.doRetrieveByKey(cb.getEmail(), cb.getTimestamp_contenimento().toString());
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return cb;
		
		
	}



}
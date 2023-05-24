package model.DAO;

import model.Bean.MetodoPagamentoBean;
import model.Bean.MetodoPagamentoModel;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class MetodoPagamentoDAO_DS implements MetodoPagamentoModel{


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

	private static final String TABLE_NAME = "metodo_pagamento";

	public MetodoPagamentoDAO_DS() {
		;
	}

	

	@Override
	public Collection<MetodoPagamentoBean> doRetrieveAll() throws SQLException{
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Collection<MetodoPagamentoBean> metodi = new LinkedList<>();

		String selectSQL = "SELECT * FROM " + MetodoPagamentoDAO_DS.TABLE_NAME;

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				MetodoPagamentoBean bean = new MetodoPagamentoBean();

				bean.setEmail_utente_pagamento(rs.getString("email_utente_pagamento"));
				bean.setNumero(rs.getString("numero"));
				bean.setCVCCVV(rs.getInt("CVCCVV"));
				bean.setTipo(rs.getString("tipo"));
				bean.setFornitore(rs.getString("fornitore"));
				bean.setScadenza(rs.getDate("scadenza"));
				metodi.add(bean);
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
		return metodi;
	}

	@Override
	public void doSave(MetodoPagamentoBean metodopagamento) throws SQLException {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String insertSQL = "INSERT INTO " + MetodoPagamentoDAO_DS.TABLE_NAME
				+ " VALUES (?, ?, ?, ?, ?, ?)";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setString(1, metodopagamento.getEmail_utente_pagamento());
			preparedStatement.setString(2, metodopagamento.getNumero());
			preparedStatement.setInt(3, metodopagamento.getCVCCVV());
			preparedStatement.setString(4, metodopagamento.getTipo());
			preparedStatement.setString(5, metodopagamento.getFornitore());
			preparedStatement.setDate(6, metodopagamento.getScadenza());
			preparedStatement.executeUpdate();
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
	public boolean doDelete(String email_utente_pagamento, Integer CVCCVV, Date scadenza) throws SQLException {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		int result = 0;

		String deleteSQL = "DELETE FROM " + MetodoPagamentoDAO_DS.TABLE_NAME + " WHERE ((email_utente_pagamento = ?) AND (CVCCVV = ?) AND (scadenza = ?));";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(deleteSQL);
			preparedStatement.setString(1, email_utente_pagamento);
			preparedStatement.setInt(2, CVCCVV);
			preparedStatement.setDate(3, scadenza);
		
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
	public MetodoPagamentoBean doRetrieveByKey(String email_utente_pagamento, Integer CVCCVV, Date scadenza)
			throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		MetodoPagamentoBean bean = new MetodoPagamentoBean();

		String selectSQL = "SELECT * FROM " + MetodoPagamentoDAO_DS.TABLE_NAME + " WHERE ((email_utente_pagamento = ?) AND (CVCCVV = ?) AND (scadenza = ?));";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, email_utente_pagamento);
			preparedStatement.setInt(2, CVCCVV);
			preparedStatement.setDate(3, scadenza);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				
				bean.setEmail_utente_pagamento(rs.getString("email_utente_pagamento"));
				bean.setNumero(rs.getString("numero"));
				bean.setCVCCVV(rs.getInt("CVCCVV"));
				bean.setTipo(rs.getString("tipo"));
				bean.setFornitore(rs.getString("fornitore"));
				bean.setScadenza(rs.getDate("scadenza"));
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
	public Collection<MetodoPagamentoBean> doRetrieveAllForUtente(String email_utente_pagamento) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Collection<MetodoPagamentoBean> metodi = new LinkedList<>();

		String selectSQL = "SELECT * FROM " + MetodoPagamentoDAO_DS.TABLE_NAME + " WHERE email_utente_pagamento = ? ;";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, email_utente_pagamento);
			
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				MetodoPagamentoBean bean = new MetodoPagamentoBean();

				bean.setEmail_utente_pagamento(rs.getString("email_utente_pagamento"));
				bean.setNumero(rs.getString("numero"));
				bean.setCVCCVV(rs.getInt("CVCCVV"));
				bean.setTipo(rs.getString("tipo"));
				bean.setFornitore(rs.getString("fornitore"));
				bean.setScadenza(rs.getDate("scadenza"));
				metodi.add(bean);
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
		return metodi;
	}

}

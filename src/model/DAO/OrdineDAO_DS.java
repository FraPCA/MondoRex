package model.DAO;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Collection;
import model.Bean.OrdineBean;
import model.Bean.OrdineModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class OrdineDAO_DS implements OrdineModel {
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

	private static final String TABLE_NAME = "ordine";

	public OrdineDAO_DS() {
		;
	}

	@Override
	public void doSave(OrdineBean Ordine) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String insertSQL = "INSERT INTO " + OrdineDAO_DS.TABLE_NAME
				+ " VALUES (?, ?, ?, ?, ?, ?)";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setString(1, Ordine.getEmail_utente_ordine());
			preparedStatement.setTimestamp(2, Ordine.getTimestamp_ordine());
			preparedStatement.setDouble(3, Ordine.getPrezzo_totale());
			preparedStatement.setString(4, Ordine.getFornitore());
			preparedStatement.setInt(5, Ordine.getUso_PF());
			preparedStatement.setString(6, Ordine.getTel_utente());
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
	public boolean doDelete(String email_utente_ordine, Timestamp timestamp_ordine) throws SQLException {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		int result = 0;

		String deleteSQL = "DELETE FROM " + OrdineDAO_DS.TABLE_NAME + " WHERE ((email_utente_ordine = ?) AND (timestamp_ordine = ?));";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(deleteSQL);
			preparedStatement.setString(1, email_utente_ordine);
			preparedStatement.setTimestamp(2, timestamp_ordine);
		
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
	public OrdineBean doRetrieveByKey(String email_utente_ordine, Timestamp timestamp_ordine) throws SQLException {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		OrdineBean bean = new OrdineBean();

		String selectSQL = "SELECT * FROM " + OrdineDAO_DS.TABLE_NAME + " WHERE ((email_utente_ordine = ?) AND (timestamp_ordine = ?));";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, email_utente_ordine);
			preparedStatement.setTimestamp(2, timestamp_ordine);


			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				bean.setEmail_utente_ordine(rs.getString("email_utente_ordine"));
				bean.setTimestamp_ordine(rs.getTimestamp("timestamp_ordine"));
				bean.setPrezzo_totale(rs.getDouble("prezzo_totale"));
				bean.setFornitore(rs.getString("fornitore"));
				bean.setUso_PF(rs.getInt("uso_PF"));
				bean.setTel_utente(rs.getString("tel_utente"));
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
	public Collection<OrdineBean> doRetrieveAll() throws SQLException {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Collection<OrdineBean> ordini = new LinkedList<OrdineBean>();

		String selectSQL = "SELECT * FROM " + OrdineDAO_DS.TABLE_NAME;

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				OrdineBean bean = new OrdineBean();
				bean.setEmail_utente_ordine(rs.getString("email_utente_ordine"));
				bean.setTimestamp_ordine(rs.getTimestamp("timestamp_ordine"));
				bean.setPrezzo_totale(rs.getDouble("prezzo_totale"));
				bean.setFornitore(rs.getString("fornitore"));
				bean.setUso_PF(rs.getInt("uso_PF"));
				bean.setTel_utente(rs.getString("tel_utente"));
				ordini.add(bean);
			}

		}catch (SQLException e) {
			System.out.println("SQLError:" + e.getMessage());
		}
		finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				if (connection != null)
					connection.close();
			}
		} 
		return ordini;
	}

	@Override
	public Collection<OrdineBean> doRetrieveAllClientBetween(String startinterval, String endinterval, String email_utente_ordine) throws SQLException {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Collection<OrdineBean> ordini = new LinkedList<OrdineBean>();

		String selectSQL = "SELECT * FROM " + OrdineDAO_DS.TABLE_NAME + " WHERE email_utente_ordine = ? AND (CAST(timestamp_ordine AS DATE) BETWEEN CAST( ? AS DATE) AND CAST(? AS DATE));"; 
		
		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, email_utente_ordine);
			preparedStatement.setString(2, startinterval);
			preparedStatement.setString(3, endinterval);
			
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				OrdineBean bean = new OrdineBean();
				bean.setEmail_utente_ordine(rs.getString("email_utente_ordine"));
				bean.setTimestamp_ordine(rs.getTimestamp("timestamp_ordine"));
				bean.setPrezzo_totale(rs.getDouble("prezzo_totale"));
				bean.setFornitore(rs.getString("fornitore"));
				bean.setUso_PF(rs.getInt("uso_PF"));
				bean.setTel_utente(rs.getString("tel_utente"));
				ordini.add(bean);
			}

		}catch (SQLException e) {
			System.out.println("SQLError:" + e.getMessage());
		}
		finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				if (connection != null)
					connection.close();
			}
		} 
		return ordini;
	}

	public Collection<OrdineBean> doRetrieveAllBetween(String startinterval, String endinterval) throws SQLException {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Collection<OrdineBean> ordini = new LinkedList<OrdineBean>();

		String selectSQL = "SELECT * FROM " + OrdineDAO_DS.TABLE_NAME + " WHERE (CAST(timestamp_ordine AS DATE) BETWEEN CAST(? AS DATE) AND CAST(? AS DATE));"; 
		
		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, startinterval);
			preparedStatement.setString(2, endinterval);
			
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				OrdineBean bean = new OrdineBean();
				bean.setEmail_utente_ordine(rs.getString("email_utente_ordine"));
				bean.setTimestamp_ordine(rs.getTimestamp("timestamp_ordine"));
				bean.setPrezzo_totale(rs.getDouble("prezzo_totale"));
				bean.setFornitore(rs.getString("fornitore"));
				bean.setUso_PF(rs.getInt("uso_PF"));
				bean.setTel_utente(rs.getString("tel_utente"));
				ordini.add(bean);
			}

		}catch (SQLException e) {
			System.out.println("SQLError:" + e.getMessage());
		}
		finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				if (connection != null)
					connection.close();
			}
		} 
		return ordini;
	}

	
	
	@Override
	public Collection<OrdineBean> doRetrieveAllClient(String email_utente_ordine) throws SQLException {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Collection<OrdineBean> ordini = new LinkedList<OrdineBean>();

		String selectSQL = "SELECT * FROM " + OrdineDAO_DS.TABLE_NAME + " WHERE email_utente_ordine = ? ;"; 
		
		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, email_utente_ordine);
			
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				OrdineBean bean = new OrdineBean();
				bean.setEmail_utente_ordine(rs.getString("email_utente_ordine"));
				bean.setTimestamp_ordine(rs.getTimestamp("timestamp_ordine"));
				bean.setPrezzo_totale(rs.getDouble("prezzo_totale"));
				bean.setFornitore(rs.getString("fornitore"));
				bean.setUso_PF(rs.getInt("uso_PF"));
				bean.setTel_utente(rs.getString("tel_utente"));
				ordini.add(bean);
			}

		}catch (SQLException e) {
			System.out.println("SQLError:" + e.getMessage());
		}
		finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				if (connection != null)
					connection.close();
			}
		} 
		return ordini;
	}

	
}

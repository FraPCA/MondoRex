package model.DAO;

import model.Bean.ContattoBean;
import model.Bean.ContattoModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ContattoDAO_DS implements ContattoModel{


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

	private static final String TABLE_NAME = "contatto";

	public ContattoDAO_DS() {
		;
	}

	

	@Override
	public Collection<ContattoBean> doRetrieveAll() throws SQLException{
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Collection<ContattoBean> contatti = new LinkedList<>();

		String selectSQL = "SELECT * FROM " + ContattoDAO_DS.TABLE_NAME;

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				ContattoBean bean = new ContattoBean();

				bean.setEmail_utente_contatto(rs.getString("email_utente_contatto"));
				bean.setN_telefono(rs.getString("N_telefono"));
				bean.setRegione(rs.getString("regione"));
				bean.setProvincia(rs.getString("provincia"));
				bean.setVia(rs.getString("via"));
				bean.setCAP(rs.getString("CAP"));
				bean.setNazione(rs.getString("Nazione"));
				contatti.add(bean);
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
		return contatti;
	}

	@Override
	public void doSave(ContattoBean contatto) throws SQLException {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String insertSQL = "INSERT INTO " + ContattoDAO_DS.TABLE_NAME
				+ " VALUES (?, ?, ?, ?, ?, ?, ?)";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setString(1, contatto.getEmail_utente_contatto());
			preparedStatement.setString(2, contatto.getN_telefono());
			preparedStatement.setString(3, contatto.getRegione());
			preparedStatement.setString(4, contatto.getProvincia());
			preparedStatement.setString(5, contatto.getVia());
			preparedStatement.setString(6, contatto.getCAP());
			preparedStatement.setString(7, contatto.getNazione());
			
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
	public boolean doDelete(String email_utente_contatto, String N_telefono) throws SQLException {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		int result = 0;

		String deleteSQL = "DELETE FROM " + ContattoDAO_DS.TABLE_NAME + " WHERE ((email_utente_contatto = ?) AND (N_telefono = ?));";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(deleteSQL);
			preparedStatement.setString(1, email_utente_contatto);
			preparedStatement.setString(2, N_telefono);
		
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
	public ContattoBean doRetrieveByKey(String email_utente_contatto, String N_telefono)
			throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		ContattoBean bean = new ContattoBean();

		String selectSQL = "SELECT * FROM " + ContattoDAO_DS.TABLE_NAME + " WHERE ((email_utente_contatto = ?) AND (N_telefono = ?));";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, email_utente_contatto);
			preparedStatement.setString(2, N_telefono);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				bean.setEmail_utente_contatto(rs.getString("email_utente_contatto"));
				bean.setN_telefono(rs.getString("N_telefono"));
				bean.setRegione(rs.getString("regione"));
				bean.setProvincia(rs.getString("provincia"));
				bean.setVia(rs.getString("via"));
				bean.setCAP(rs.getString("CAP"));
				bean.setNazione(rs.getString("Nazione"));
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
	public Collection<ContattoBean> doRetrieveAllForUtente(String email_utente_contatto) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Collection<ContattoBean> contatti = new LinkedList<>();

		String selectSQL = "SELECT * FROM " + ContattoDAO_DS.TABLE_NAME + " WHERE email_utente_contatto = ?";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, email_utente_contatto);
			
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				ContattoBean bean = new ContattoBean();

				bean.setEmail_utente_contatto(rs.getString("email_utente_contatto"));
				bean.setN_telefono(rs.getString("N_telefono"));
				bean.setRegione(rs.getString("regione"));
				bean.setProvincia(rs.getString("provincia"));
				bean.setVia(rs.getString("via"));
				bean.setCAP(rs.getString("CAP"));
				bean.setNazione(rs.getString("Nazione"));
				contatti.add(bean);
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
		return contatti;
	}

}

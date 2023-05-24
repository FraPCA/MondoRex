package model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.LinkedList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import model.Bean.ReviewBean;
import model.Bean.ReviewModel;

public class ReviewDAO_DS implements ReviewModel{
	
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

	private static final String TABLE_NAME = "recensione";

	@Override
	public synchronized void doSave(ReviewBean p) throws SQLException {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		

		String insertSQL = "INSERT INTO " + ReviewDAO_DS.TABLE_NAME
				+ "(nome_utente, timestamp_rec, valutazione, descrizione) VALUES (?, ?, ?, ?)";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setString(1, p.getNome_utente());
			preparedStatement.setTimestamp(2, p.getTimestamp_rec());
			preparedStatement.setInt(3, p.getValutazione());
			
			preparedStatement.setString(4, p.getDescrizione());
			preparedStatement.executeUpdate();
		}
		catch (SQLException e) {
			System.out.println("SQLError:" + e.getMessage());
		}
		finally {
			try {
				if (preparedStatement != null)
					System.out.println("Ho fatto la query");
					preparedStatement.close();
			} finally {
				if (connection != null)
					connection.close();
			}
		}
	}
	@Override
	public synchronized boolean doDelete(String nome_utente, Timestamp timestamp_rec) throws SQLException {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		int result = 0;

		String deleteSQL = "DELETE FROM " + ReviewDAO_DS.TABLE_NAME + " WHERE (nome_utente = ? AND timestamp_rec = ?)";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(deleteSQL);
			preparedStatement.setString(1, nome_utente);
			preparedStatement.setTimestamp(2, timestamp_rec);

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
	public synchronized ReviewBean doRetrieveByKey(String nome_utente, Timestamp timestamp_rec) throws SQLException {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		ReviewBean bean = new ReviewBean();

		String selectSQL = "SELECT * FROM " + ReviewDAO_DS.TABLE_NAME + " WHERE (nome_utente = ? AND timestamp_rec = ?)";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, nome_utente);
			preparedStatement.setTimestamp(2, timestamp_rec);


			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				
				bean.setNome_utente(rs.getString("nome_utente"));
				bean.setTimestamp_rec(rs.getTimestamp("timestamp_rec"));
				bean.setValutazione(rs.getInt("valutazione"));
				bean.setDescrizione(rs.getString("descrizione"));
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
	public synchronized Collection<ReviewBean> doRetrieveAll(String order, String where) throws SQLException {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Collection<ReviewBean> reviews = new LinkedList<ReviewBean>();

		String selectSQL = "SELECT * FROM " + ReviewDAO_DS.TABLE_NAME;

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
				ReviewBean bean = new ReviewBean();

				bean.setNome_utente(rs.getString("nome_utente"));
				bean.setTimestamp_rec(rs.getTimestamp("timestamp_rec"));
				bean.setValutazione(rs.getInt("valutazione"));
				bean.setDescrizione(rs.getString("descrizione"));
				reviews.add(bean);
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
		return reviews;
	}

	@Override
	public Collection<ReviewBean> doRetrieveAll(String order) throws SQLException {
		// TODO Auto-generated method stub
		System.out.println("Entrato nel doRetrieveAll");
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Collection<ReviewBean> reviews = new LinkedList<ReviewBean>();

		String selectSQL = "SELECT * FROM " + ReviewDAO_DS.TABLE_NAME;

		if (order != null && !order.equals("")) {
			selectSQL += " ORDER BY " + order;
		}

		System.out.println("Prima del try");
		try {
			ds.toString();
			System.out.println("Provo ad ottenere connection al ds");
			connection = ds.getConnection();
			System.out.println("Ottenuta connessione dal ds");
			preparedStatement = connection.prepareStatement(selectSQL);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				ReviewBean bean = new ReviewBean();

				bean.setNome_utente(rs.getString("nome_utente"));
				bean.setTimestamp_rec(rs.getTimestamp("timestamp_rec"));
				bean.setValutazione(rs.getInt("valutazione"));
				bean.setDescrizione(rs.getString("descrizione"));
				reviews.add(bean);
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
		return reviews;
	}
	@Override
	public Collection<ReviewBean> doRetrieveOnProduct(String order, String codice_prodotto) throws SQLException {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Collection<ReviewBean> reviews = new LinkedList<ReviewBean>();

		String selectSQL = "SELECT * FROM ((recensione JOIN collegamento ON recensione.timestamp_rec = collegamento.timestamp_rec) JOIN prodotto ON collegamento.codice_prodotto = prodotto.codice_prodotto) WHERE prodotto.codice_prodotto = ? ;";
		
		if (order != null && !order.equals("")) {
			selectSQL += " ORDER BY " + order;
		}
		try {
		connection = ds.getConnection();
		preparedStatement = connection.prepareStatement(selectSQL);
		preparedStatement.setString(1, codice_prodotto);
		ResultSet rs = preparedStatement.executeQuery();

		while (rs.next()) {
			ReviewBean bean = new ReviewBean();

			bean.setNome_utente(rs.getString(1));
			bean.setTimestamp_rec(rs.getTimestamp(2));
			bean.setValutazione(rs.getInt(3));
			bean.setDescrizione(rs.getString(4));
			System.out.println(bean);
			reviews.add(bean);
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
		return reviews;
	}
	@Override
	public int getAverageForProduct(String codice_prodotto) throws SQLException {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String selectSQL = "SELECT FLOOR(AVG(valutazione)) from ((recensione JOIN collegamento ON recensione.timestamp_rec = collegamento.timestamp_rec) JOIN prodotto ON collegamento.codice_prodotto = prodotto.codice_prodotto) WHERE prodotto.codice_prodotto = ?;";
		
		try {
		connection = ds.getConnection();
		preparedStatement = connection.prepareStatement(selectSQL);
		preparedStatement.setString(1, codice_prodotto);
		ResultSet rs = preparedStatement.executeQuery();
		
		rs.next();
		Integer averagereviewscore = rs.getInt(1);
		return averagereviewscore;
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
		return 0;
	}
} 

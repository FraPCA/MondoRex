package model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import model.Bean.ProductBean;
import model.Bean.ProductModel;

public class ProductDAO_DS implements ProductModel{
	
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

	private static final String TABLE_NAME = "prodotto";
	
	@Override
	public synchronized boolean doSave(ProductBean p) throws SQLException {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		int result = 0;
		
		String insertSQL = "INSERT INTO " + ProductDAO_DS.TABLE_NAME
				+ "(codice_prodotto, nome, prezzo_netto, percentuale_di_sconto, IVA, descrizione_breve, descrizione_dettagliata ,quantita_disponibile, link_immagine, brand, microcategoria, macrocategoria) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			System.out.println("PREPARO LO STATEMENT");
			preparedStatement.setString(1, p.getCodice_prodotto());
			System.out.println("HO IL CODICE PRODOTTO" + p.getCodice_prodotto());
			preparedStatement.setString(2, p.getNome());
			System.out.println("HO IL NOME" + p.getNome());

			
			preparedStatement.setInt(5, p.getIVA());
			System.out.println("HO L'IVA" + p.getIVA());
			
			preparedStatement.setInt(4, p.getPercentuale_sconto());
			System.out.println("HO LO SCONTO" + p.getPercentuale_sconto() +"%");
			
			preparedStatement.setDouble(3, p.getPrezzo_netto());
			System.out.println("HO IL PREZZO NETTO" + p.getPrezzo_netto());
		
			
			preparedStatement.setString(6, p.getDescrizione_breve());
			System.out.println("HO LA DESCRIZIONE BREVE" + p.getDescrizione_breve());
			
			preparedStatement.setString(7, p.getDescrizione_dettagliata());
			System.out.println("HO LA DESCRIZIONE DETTAGLIATA" + p.getDescrizione_dettagliata());
			
			preparedStatement.setInt(8,p.getQuantita_disponibile());
			System.out.println("HO LA QUANTITA' DISPONIBILE" + p.getQuantita_disponibile());
			
			preparedStatement.setString(9, p.getLink_immagine());
			System.out.println("HO IL LINK IMMAGINE" + p.getLink_immagine());
			
			preparedStatement.setString(10, p.getBrand());
			System.out.println("HO IL BRAND" + p.getBrand());
			
			
			preparedStatement.setString(11, p.getMicrocategoria());
			System.out.println("HO LA MICROCATEGORIA" + p.getMicrocategoria());
			
			preparedStatement.setString(12, p.getMacrocategoria());
			System.out.println("HO LA MACROCATEGORIA" + p.getMacrocategoria());
			
			result = preparedStatement.executeUpdate();
		}
		catch (SQLException e) {
			System.out.println("I'm doSave ahah --> SQLError:" + e.getMessage() + "result " + (result != 0)); 
			return (result != 0);
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
		return (result != 0);
	}

	@Override
	public synchronized boolean doDelete(String codice_prodotto) throws SQLException {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		int result = 0;

		String deleteSQL = "DELETE FROM " + ProductDAO_DS.TABLE_NAME + " WHERE codice_prodotto = ?";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(deleteSQL);
			preparedStatement.setString(1, codice_prodotto);

			result = preparedStatement.executeUpdate();

		}catch(SQLException e) {
			e.printStackTrace();
			return (result != 0);
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
		return (result != 0); 
		
	}

	@Override
	public synchronized ProductBean doRetrieveByKey(String codice_prodotto) throws SQLException {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		ProductBean bean = new ProductBean();

		String selectSQL = "SELECT * FROM " + ProductDAO_DS.TABLE_NAME + " WHERE codice_prodotto = ?";

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
				bean.setDescrizione_breve(rs.getString("descrizione_breve"));
				bean.setDescrizione_dettagliata(rs.getString("descrizione_dettagliata"));
				bean.setQuantita_disponibile(rs.getInt("quantita_disponibile"));
				bean.setLink_immagine(rs.getString("link_immagine"));				
				bean.setBrand(rs.getString("brand"));
				bean.setMicrocategoria(rs.getString("microcategoria"));
				bean.setMacrocategoria(rs.getString("macrocategoria"));
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
	public synchronized Collection<ProductBean> doRetrieveAll(String order, String where) throws SQLException {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Collection<ProductBean> products = new LinkedList<ProductBean>();

		String selectSQL = "SELECT * FROM " + ProductDAO_DS.TABLE_NAME;

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
				ProductBean bean = new ProductBean();

				bean.setCodice_prodotto(rs.getString("codice_prodotto"));
				bean.setNome(rs.getString("nome"));
				bean.setPrezzo_totale(rs.getDouble("prezzo_totale"));
				bean.setPrezzo_netto(rs.getDouble("prezzo_netto"));
				bean.setPercentuale_sconto(rs.getInt("percentuale_di_sconto"));
				bean.setIVA(rs.getInt("IVA"));
				bean.setDescrizione_breve(rs.getString("descrizione_breve"));
				bean.setDescrizione_dettagliata(rs.getString("descrizione_dettagliata"));
				bean.setQuantita_disponibile(rs.getInt("quantita_disponibile"));
				bean.setLink_immagine(rs.getString("link_immagine"));				
				bean.setBrand(rs.getString("brand"));
				bean.setMicrocategoria(rs.getString("microcategoria"));
				bean.setMacrocategoria(rs.getString("macrocategoria"));
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
	public Collection<ProductBean> doRetrieveAll(String order) throws SQLException {
		// TODO Auto-generated method stub
		System.out.println("Entrato nel doRetrieveAll");
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Collection<ProductBean> products = new LinkedList<ProductBean>();

		String selectSQL = "SELECT * FROM " + ProductDAO_DS.TABLE_NAME;

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
				ProductBean bean = new ProductBean();

				bean.setCodice_prodotto(rs.getString("codice_prodotto"));
				bean.setNome(rs.getString("nome"));
				bean.setPrezzo_totale(rs.getDouble("prezzo_totale"));
				bean.setPrezzo_netto(rs.getDouble("prezzo_netto"));
				bean.setPercentuale_sconto(rs.getInt("percentuale_di_sconto"));
				bean.setIVA(rs.getInt("IVA"));
				bean.setDescrizione_breve(rs.getString("descrizione_breve"));
				bean.setDescrizione_dettagliata(rs.getString("descrizione_dettagliata"));
				bean.setQuantita_disponibile(rs.getInt("quantita_disponibile"));
				bean.setLink_immagine(rs.getString("link_immagine"));				
				bean.setBrand(rs.getString("brand"));
				bean.setMicrocategoria(rs.getString("microcategoria"));
				bean.setMacrocategoria(rs.getString("macrocategoria"));
				products.add(bean);
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
		return products;
	} 
	
	public Collection<ProductBean> doRetrieveRandom(int number) throws SQLException {
		// TODO Auto-generated method stub
		System.out.println("Entrato nel doRetrieveRandom");
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Collection<ProductBean> products = new LinkedList<ProductBean>();
																 	
		String selectSQL = "SELECT * FROM prodotto ORDER BY RAND() LIMIT ?;";

		System.out.println("Prima del try");
		try {
			ds.toString();
			System.out.println("Provo ad ottenere connection al ds");
			connection = ds.getConnection();
			System.out.println("Ottenuta connessione dal ds");
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, number); //number: numero esatto di entry da restituire
			
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				ProductBean bean = new ProductBean();

				bean.setCodice_prodotto(rs.getString("codice_prodotto"));
				bean.setNome(rs.getString("nome"));
				bean.setPrezzo_totale(rs.getDouble("prezzo_totale"));
				bean.setPrezzo_netto(rs.getDouble("prezzo_netto"));
				bean.setPercentuale_sconto(rs.getInt("percentuale_di_sconto"));
				bean.setIVA(rs.getInt("IVA"));
				bean.setDescrizione_breve(rs.getString("descrizione_breve"));
				bean.setDescrizione_dettagliata(rs.getString("descrizione_dettagliata"));
				bean.setQuantita_disponibile(rs.getInt("quantita_disponibile"));
				bean.setLink_immagine(rs.getString("link_immagine"));				
				bean.setBrand(rs.getString("brand"));
				bean.setMicrocategoria(rs.getString("microcategoria"));
				bean.setMacrocategoria(rs.getString("macrocategoria"));
				products.add(bean);
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
		return products;
	}
	
	public Collection<ProductBean> doRetrieveByCategory(String macroCategoria) throws SQLException {
		// TODO Auto-generated method stub
			System.out.println("Entrato nel doRetrieveByCategory");
			Connection connection = null;
			PreparedStatement preparedStatement = null;

			Collection<ProductBean> products = new LinkedList<ProductBean>();

			String selectSQL = "SELECT * FROM prodotto WHERE macrocategoria = ? ";

			System.out.println("Prima del try");
			try {
				ds.toString();
				System.out.println("Provo ad ottenere connection al ds");
				connection = ds.getConnection();
				System.out.println("Ottenuta connessione dal ds");
				preparedStatement = connection.prepareStatement(selectSQL);
				preparedStatement.setString(1, macroCategoria);
				
				ResultSet rs = preparedStatement.executeQuery();

				while (rs.next()) {
					ProductBean bean = new ProductBean();

					bean.setCodice_prodotto(rs.getString("codice_prodotto"));
					bean.setNome(rs.getString("nome"));
					bean.setPrezzo_totale(rs.getDouble("prezzo_totale"));
					bean.setPrezzo_netto(rs.getDouble("prezzo_netto"));
					bean.setPercentuale_sconto(rs.getInt("percentuale_di_sconto"));
					bean.setIVA(rs.getInt("IVA"));
					bean.setDescrizione_breve(rs.getString("descrizione_breve"));
					bean.setDescrizione_dettagliata(rs.getString("descrizione_dettagliata"));
					bean.setQuantita_disponibile(rs.getInt("quantita_disponibile"));
					bean.setLink_immagine(rs.getString("link_immagine"));				
					bean.setBrand(rs.getString("brand"));
					bean.setMicrocategoria(rs.getString("microcategoria"));
					bean.setMacrocategoria(rs.getString("macrocategoria"));
					products.add(bean);
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
			return products;
		}
	
	public ArrayList<String> doRetrieveBrand() throws SQLException {
		// TODO Auto-generated method stub
			System.out.println("Entrato nel doRetrieveBrand");
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			ArrayList<String> brands = new ArrayList<String>();


			String selectSQL = "SELECT DISTINCT brand FROM prodotto";

			System.out.println("Prima del try");
			try {
				ds.toString();
				System.out.println("Provo ad ottenere connection al ds");
				connection = ds.getConnection();
				System.out.println("Ottenuta connessione dal ds");
				preparedStatement = connection.prepareStatement(selectSQL);
				
				ResultSet rs = preparedStatement.executeQuery();
				
				System.out.println("Ho trovato i seguenti brand: ");
				
				while (rs.next()) {
					System.out.println(rs.getString("brand"));
					brands.add(rs.getString("brand"));
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
			return brands;
		}


} 

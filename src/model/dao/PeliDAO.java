package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Peli;


public class PeliDAO extends DataAccessObject {
	public ArrayList<Peli> findAll() {	
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<Peli> pelit = new ArrayList<Peli>();
		Peli peli = null; 
		
		try {
			// Luodaan yhteys
			conn = getConnection();
			// Luodaan komento: haetaan kaikki rivit pelit-taulusta
			String sqlSelect = "SELECT id, nimi, kuvaus, kehittaja, julkaisija, julkaisuvuosi, arvostelu, hinta FROM pelit;";
			// Valmistellaan komento:
			stmt = conn.prepareStatement(sqlSelect);
			// Lähetetään komento:
			rs = stmt.executeQuery();
			while (rs.next()) {
				peli = readPelit(rs);
				// lisätään peli listaan
				pelit.add(peli);
			}
		}
		catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
		return pelit;
	}
	private Peli readPelit(ResultSet rs) {	
		try {
			// Haetaan yhden pelin tiedot kyselyn tulostaulun (ResultSet-tyyppinen rs-olion) aktiiviselta tietoriviltä
			int id = rs.getInt("id");
			String nimi = rs.getString("nimi");
			String kuvaus = rs.getString("kuvaus");
			String kehittaja = rs.getString("kehittaja");
			String julkaisija = rs.getString("julkaisija");
			int julkaisuvuosi = rs.getInt("julkaisuvuosi");
			double arvostelu = rs.getDouble("arvostelu");
			double hinta = rs.getDouble("hinta");
		
			//  Luodaan ja palautetaan uusi peli
			return new Peli(id, nimi, kuvaus, kehittaja, julkaisija, julkaisuvuosi, arvostelu, hinta);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	public void addPeli(Peli peli) throws SQLException {
		Connection connection = null;
		PreparedStatement stmtInsert = null;
	

		try {
			// Luodaan yhteys
			connection = getConnection();
			//Luodaan uusi peli tietokantaan:
			String sqlInsert = "INSERT INTO pelit(id, nimi, kuvaus, kehittaja, julkaisija, julkaisuvuosi, arvostelu, hinta) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
			stmtInsert = connection.prepareStatement(sqlInsert);
			stmtInsert.setInt(1, peli.getId());
			stmtInsert.setString(2, peli.getNimi());
			stmtInsert.setString(3, peli.getKuvaus());
			stmtInsert.setString(4, peli.getKehittaja());
			stmtInsert.setString(5, peli.getJulkaisija());
			stmtInsert.setInt(6, peli.getJulkaisuvuosi());
			stmtInsert.setDouble(7, peli.getArvostelu());
			stmtInsert.setDouble(8, peli.getHinta());
			stmtInsert.executeUpdate();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			close(stmtInsert, connection); // Suljetaan statement ja yhteys
		}
	}
	public void removePeli(int peliId) throws SQLException {
		Connection connection = null;
		PreparedStatement stmtDelete = null;

		try {
			// Luodaan yhteys
			connection = getConnection();
			//Poistetaan peli tietokantasta:
			String sqlInsert = "DELETE FROM pelit WHERE id = ?";
			stmtDelete = connection.prepareStatement(sqlInsert);
			stmtDelete.setInt(1, peliId);
			stmtDelete.executeUpdate();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			close(stmtDelete, connection); // Suljetaan statement ja yhteys
		}
	}
	
	public void updatePeli(Peli peli) throws SQLException {
		Connection connection = null;
		PreparedStatement stmtUpdate = null;
		try {
			// muodostetaan yhteys
			connection = getConnection();
			String sqlInsert = "UPDATE pelit SET nimi=?, kuvaus=?, kehittaja=?, julkaisija=?, julkaisuvuosi=?, hinta=? WHERE id=?";
			stmtUpdate = connection.prepareStatement(sqlInsert);
			// Päivitetään pelin tiedot saaduilla arvoilla
			stmtUpdate.setString(1, peli.getNimi());
			stmtUpdate.setString(2, peli.getKuvaus());
			stmtUpdate.setString(3, peli.getKehittaja());
			stmtUpdate.setString(4, peli.getJulkaisija());
			stmtUpdate.setInt(5, peli.getJulkaisuvuosi());
			stmtUpdate.setDouble(6, peli.getHinta());
			stmtUpdate.setInt(7, peli.getId());
			stmtUpdate.executeUpdate();
		}
		catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			close(stmtUpdate, connection); // Suljetaan statement ja yhteys
		}
		
	}
	public void ArvostelePeli(Peli peli) {
		Connection connection = null;
		PreparedStatement stmtUpdate = null;
		try {
			// muodostetaan yhteys
			connection = getConnection();
			String sqlInsert = "UPDATE pelit SET arvostelu=? WHERE id=?";
			stmtUpdate = connection.prepareStatement(sqlInsert);
			// Päivitetään pelin arvostelut
			stmtUpdate.setDouble(1, peli.getArvostelu());
			stmtUpdate.setInt(2, peli.getId());
	
			stmtUpdate.executeUpdate();
		}
		catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			close(stmtUpdate, connection); // Suljetaan statement ja yhteys
		}
	}
	public Peli FindById(int peliId) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Peli peli = null; 
		try {
			// Luodaan yhteys
			conn = getConnection();
			// Luodaan komento: haetaan kaikki rivit pelittaulusta
			String sqlSelect = "SELECT * FROM pelit WHERE id = ?";
			// Valmistellaan komento:
			stmt = conn.prepareStatement(sqlSelect);
			stmt.setInt(1, peliId);
			// Lähetetään komento:
			rs = stmt.executeQuery();
			// Käydään tulostaulun rivit läpi ja luetaan read()-metodilla:
			if (rs.next()) {
				peli = readPelit(rs);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			close(rs, stmt, conn); // Suljetaan
		}
	
		return peli;
	}
}

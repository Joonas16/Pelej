package test;

import java.sql.SQLException;

import model.Peli;
import model.dao.PeliDAO;

public class PeliTEST {
	public static void main(String[] args) throws SQLException {
		PeliDAO peli = new PeliDAO();
		Peli spyro = new Peli(0, "Spyro", "Seikkailu", "EA", "EA", 2004, 5.4, 12);
		peli.addPeli(spyro);
	}
}

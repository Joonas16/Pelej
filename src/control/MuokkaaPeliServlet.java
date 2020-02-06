package control;

import java.io.IOException;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Peli;
import model.dao.PeliDAO;

@WebServlet("/muokkaa_peli")
public class MuokkaaPeliServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	// Luodaan staattinen peli-olio ja peliId
	static Peli peli = new Peli(0, null, null, null, null, 0, 0, 0);
	static int peliId;
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String peliIdStr = request.getParameter("peliid");
		peliId = Integer.parseInt(peliIdStr);
	
		PeliDAO pelidao = new PeliDAO();
		// peli-olio saa arvot FindById-metodilla
		Peli peli = pelidao.FindById(peliId);
		
		
		request.setAttribute("pelit", peli);
		String jsp = "/view/muokkauslomake.jsp";
		getServletContext().getRequestDispatcher(jsp).forward(request, response);

		peli.setInt(peliId);
		
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			String nimi = request.getParameter("nimi"); 
			String kuvaus = request.getParameter("kuvaus"); 
			String kehittaja = request.getParameter("kehittaja"); 
			String julkaisija = request.getParameter("julkaisija"); 
			String julkaisuvuosiStr = request.getParameter("julkaisuvuosi");
			String hintaStr = request.getParameter("hinta"); 
			
			String virheteksti = null;
			int julkaisuvuosi = 0;
			double hinta = 0.00;
			
			try { // Yritetään muuntaa käyttäjän syötteet integer muotoon
				julkaisuvuosi = Integer.parseInt(julkaisuvuosiStr); // julkaisuvuosi
				hinta = Double.parseDouble(hintaStr); // hinta
				
			} catch (NumberFormatException e) {
				virheteksti = "Syöttämäsi tiedot olivat virheelliset";
			}
			// Päivitetään peli saaduilla arvoilla. Id ja arvostelut ovat samat.
			peli = new Peli(peliId, nimi, kuvaus, kehittaja, julkaisija, julkaisuvuosi, peli.getArvostelu(), hinta);

			PeliDAO pelidao = new PeliDAO();
			// Lisätään pelin tiedot tietokantaan
			try {
				pelidao.updatePeli(peli);
			} catch (Exception e) {
				virheteksti = "Tapahtui tietokantavirhe.";
			}


			if (virheteksti != null) {
				String koodattuTeksti = URLEncoder.encode(virheteksti, "UTF-8");
				// uudelleenohjataan selain pelilista-sivulle virhetekstin kera
				response.sendRedirect("listaa-pelit?viesti=" + koodattuTeksti);
			} else
				response.sendRedirect("listaa-pelit");
		
		
		
	}


}

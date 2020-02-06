package control;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Peli;
import model.dao.PeliDAO;


@WebServlet("/lisaa-peli")
public class LisaaUusiPeliServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// ohjataan pelilomake sivulle
		String jsp = "/view/pelilomake.jsp";
		getServletContext().getRequestDispatcher(jsp).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Otetaan käyttäjän syötteet talteen
		String nimi = request.getParameter("nimi");
		String kuvaus = request.getParameter("kuvaus");
		String kehittaja = request.getParameter("kehittaja");
		String julkaisija = request.getParameter("julkaisija");
		String julkaisuvuosiStr = request.getParameter("julkaisuvuosi");
		String hintaStr = request.getParameter("hinta");
		
		String virheteksti = null;
		int julkaisuvuosi = 0;
		double hinta = 0.00;
		
		try { // Yrittää muuntaa käyttäjän syötteet integer ja double muotoon
			julkaisuvuosi = Integer.parseInt(julkaisuvuosiStr);
			hinta = Double.parseDouble(hintaStr);
			
		} catch (NumberFormatException e) {
			virheteksti = "Syöttämäsi tiedot olivat virheelliset";
		}
		// Jos nimi, kuvaus, kehittaja tai julkaisija kentät ovat tyhjiä, annetaan niille arvot "ei tietoja"
		if(nimi.length() == 0) {
			nimi = "ei tietoja";
		}
		if(kuvaus.length() == 0) {
			kuvaus = "ei tietoja";
		}
		if(kehittaja.length() == 0) {
			kehittaja = "ei tietoja";
		}
		if(julkaisija.length() == 0) {
			julkaisija = "ei tietoja";
		}

		// Luodaan uusi peli edellisillä parametreilla, arvostelu on luonti vaiheessa 0
		Peli peli = new Peli(0, nimi, kuvaus, kehittaja, julkaisija, julkaisuvuosi, 0, hinta);

		PeliDAO pelidao = new PeliDAO();
		
		// Lisätään pelin tiedot tietokantaan
		try {
			pelidao.addPeli(peli);
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

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


@WebServlet("/arvostele_peli")
public class ArvostelePeliServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	// Staattinen peli ja peli.id, jolla arvot saadaan pysymään
    static Peli peli = new Peli(0, null, null, null, null, 0, 0, 0);
    static int peliId;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// Haetaan id-arvo selaimelta
		String peliIdStr = request.getParameter("peliid");
		peliId = Integer.parseInt(peliIdStr);
	
		PeliDAO pelidao = new PeliDAO();
		// Peli saa arvot FindById-metodilla
		Peli peli = pelidao.FindById(peliId);
		
		// lähetetään tiedot selaimelle
		request.setAttribute("pelit", peli);
		String jsp = "/view/arvostelulomake.jsp";
		getServletContext().getRequestDispatcher(jsp).forward(request, response);
		// Asetetaan peli-oliolle staattinen id
		peli.setInt(peliId);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
			// Haetaan käyttäjän arvostelu syöte
			String arvosteluStr = request.getParameter("arvostelu");
			// Haetaan staattiselta oliolta aikaisempi arvo
			double vanhaArvostelu = peli.getArvostelu();
			double uusiArvostelu;
			
			String virheteksti = null;
			double arvostelu = 0.00;
			
			try { // Yritetään muuntaa käyttäjän syötteet double muotoon
				arvostelu = Double.parseDouble(arvosteluStr);
				
			} catch (NumberFormatException e) {
				virheteksti = "Syöttämäsi tiedot olivat virheelliset";
			}
			// Jos käyttäjän arvostelu on yli 5, arvoksi annetaan 5.
			if(arvostelu > 5) {
				arvostelu = 5;
			}
			if(vanhaArvostelu == 0) {
				uusiArvostelu = arvostelu;
			} else {
				// Uusi arvostelu on uuden syötteen ja vanhan arvostelu-arvon keskiarvo
				uusiArvostelu = (vanhaArvostelu + arvostelu) / 2;
			}
			
			// Annetaan staattiselle pelille uudet arvot, jossa peliId on staattinen, joka annettiin doGet metodissa.
			peli = new Peli(peliId, peli.getNimi(), peli.getKuvaus(), peli.getKehittaja(), peli.getJulkaisija(),
					peli.getJulkaisuvuosi(), uusiArvostelu, peli.getHinta());

			PeliDAO pelidao = new PeliDAO();
			// Lisätään pelin arvostelu tietokantaan
			try {
				pelidao.ArvostelePeli(peli);
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

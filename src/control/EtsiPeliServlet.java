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


@WebServlet("/etsi_peli")
public class EtsiPeliServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	static String virheteksti = null;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// Haetaan lomakkeella syötetyn pelin tiedot request-oliolta
		String peliIdStr = request.getParameter("peliid");
		int peliId = 0;
		try {
			peliId = Integer.parseInt(peliIdStr);
		} catch (NumberFormatException e) {
			
		}
		

		PeliDAO pelidao = new PeliDAO();
		Peli peli = pelidao.FindById(peliId);
		
		// Lähetetään tiedot selaimelle
		request.setAttribute("pelit", peli);
		String jsp = "/view/found_id.jsp";
		getServletContext().getRequestDispatcher(jsp).forward(request, response);
		}
		
		

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String idStr = request.getParameter("peliid");
		int id = Integer.parseInt(idStr);
		String arvosteluStr = request.getParameter("peliid");
		double arvostelu = Double.parseDouble(idStr);
		String nimi = request.getParameter("nimi"); 
		String kuvaus = request.getParameter("kuvaus"); 
		String kehittaja = request.getParameter("kehittaja"); 
		String julkaisija = request.getParameter("julkaisija"); 
		String julkaisuvuosiStr = request.getParameter("julkaisuvuosi");
		String hintaStr = request.getParameter("hinta");
		
	}

}

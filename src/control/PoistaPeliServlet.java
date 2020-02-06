package control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.PeliDAO;


@WebServlet("/poista_peli")
public class PoistaPeliServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		try {
			// Haetaan lomakkeella syötetyn pelin tiedot request-oliolta
			String idStr = request.getParameter("peliid");
			int peliId = Integer.parseInt(idStr);

			PeliDAO pelidao = new PeliDAO();
			// Poistetaan peli tietokannasta
			pelidao.removePeli(peliId);
		} catch (SQLException e) {
			System.out
					.println("Sovelluksessa tapahtui virhe " + e.getMessage());
		}

		// uudelleenohjataan selain pelilista-sivulle
		response.sendRedirect("listaa-pelit");
	}

	
}

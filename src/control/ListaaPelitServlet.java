package control;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Peli;
import model.dao.PeliDAO;

@WebServlet("/listaa-pelit")
public class ListaaPelitServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ListaaPelitServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		PeliDAO pelidao = new PeliDAO();
		// Luodaan lista, johon pelidaolla haetaan kaikki peli-oliot
		ArrayList<Peli> lista = pelidao.findAll();
		// Lähetetään tiedot selaimelle
		request.setAttribute("pelit", lista);
		String jsp = "/view/pelilista.jsp";
		getServletContext().getRequestDispatcher(jsp).forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

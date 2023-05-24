package control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Bean.ProductBean;
import model.Bean.ReviewBean;
import model.DAO.ProductDAO_DS;
import model.DAO.ReviewDAO_DS;

/**
 * Servlet implementation class DettaglioProdotto
 */
@WebServlet("/DettaglioProdotto")
public class DettaglioProdotto extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DettaglioProdotto() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ProductDAO_DS pdao = new ProductDAO_DS();
		ReviewDAO_DS rdao = new ReviewDAO_DS();
		Collection<ReviewBean> rlist = new LinkedList<ReviewBean>();
		ProductBean pb = new ProductBean();
		try {
			pb = pdao.doRetrieveByKey(request.getParameter("itemID"));
			request.setAttribute("prodottodamostrare", pb);
			Integer averagereviewscore = rdao.getAverageForProduct(pb.getCodice_prodotto());//media dei voti del prodotto
			request.setAttribute("score", averagereviewscore);
			rlist = rdao.doRetrieveOnProduct(null, pb.getCodice_prodotto());
			
			//------- inizio debug
			System.out.println("Testo rlist");   
			Iterator<ReviewBean> it = rlist.iterator();
			while(it.hasNext())
			{
				ReviewBean currentreview = it.next();
				System.out.println("Stampo il bean corrente\n " + currentreview);
			}
			//------- fine debug
			
			request.setAttribute("reviewlist", rlist);
			//passa gli attributi alla pagina DettaglioProdotto.jsp
			RequestDispatcher rd = request.getServletContext().getRequestDispatcher("/DettaglioProdotto.jsp");
			rd.forward(request, response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

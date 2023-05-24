package control;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Bean.UserBean;
import model.DAO.UserDAO_DS;

/**
 * Servlet implementation class Register
 */
@WebServlet("/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Register() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		//Se l'utente ha riprovato a registrarsi, parti da zero.
		
		if(request.getSession().getAttribute("RegisterError") != null)
		{
			request.getSession().removeAttribute("RegisterError");
		}
		
		String redirectURL = "/RegisterSuccess.html";
		//Ottenimento dei parametri
		
		String username = request.getParameter("username");
		String name = request.getParameter("name");
		String surname = request.getParameter("surname");
		String password = request.getParameter("password");
		String verifypassword = request.getParameter("verifypassword");
		System.out.println("Debug: Parametri ricevuti: " + name + " " + username + " " + surname + " " + password + " " + verifypassword);
		
		//Controllo serverside
		System.out.println("Controllo password");
		if(password.equals(verifypassword))
		{
			System.out.println("Sono uguali, procedo");
			//Crea il bean corrispondente
			
			UserBean ub = new UserBean();
			ub.setNome(name);
			ub.setCognome(surname);
			ub.setEmail(username);
			ub.setPassword(password);
			/* Punti fedeltà e timestamp registrazione sono automaticamente gestiti dal costruttore di UserBean, 
			ora salvo il bean */
			
			System.out.println("Provo a creare il dao");
			UserDAO_DS udao = new UserDAO_DS();
			try {
				System.out.println("Entrato nel try");
				if(udao.doRetrieveByKey(username).getEmail().equals("constructorGenerated"))//vede se la email � quella predefinita
				{
					System.out.println("Non esiste, Provo a salvare");
					udao.doSave(ub);
					System.out.println("Salvato");
				}
				else
				{
					redirectURL = "/Register.jsp";
					request.getSession().setAttribute("RegisterError", "L'email che ha inserito corrisponde già ad un utente registrato sul sito. Per favore, inserisca un'altra email, oppure effettui l'accesso con questa email.");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else
		{
			redirectURL = "/Register.jsp";
			request.getSession().setAttribute("RegisterError", "Si è verificato un errore nella gestione della registrazione. Per riprovare, reinserisca i dati nei campi appositi.");
		}
		response.sendRedirect(request.getContextPath() + redirectURL);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

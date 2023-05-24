package control;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Bean.UserBean;
import model.DAO.UserDAO_DM;

@WebServlet("/Login")
public class Login extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String redirectedpage = null; //questa è la stringa che indica l'url della pagina a cui si effettuerà il redirect
		try {
			UserDAO_DM ud = new UserDAO_DM();
			UserBean ub = new UserBean();
			ub = ud.doRetrieveByKey(username);
			if(request.getSession().getAttribute("adminredirect") != null)//se precedentemente ci si è loggati come amministratore poi come utente... 
			{
				//...rimuove l'attributo adminredirect che reindirizza alla pagina di amministratore
				request.getSession().removeAttribute("adminredirect");
			}
			checkLogin(username, password, ub);
			request.getSession().setAttribute("adminRoles", Boolean.valueOf(true)); //è un attributo impostato quando si fa il login
			request.getSession().setAttribute("failedLogin", false);
			
			//impostiamo l'userbean come attributo di sessione per evitare di passarlo tra le pagine ogni volta
			if(request.getSession().getAttribute("utentesessione") == null)
			{
				request.getSession().setAttribute("utentesessione", ub);
			}
			if(checkAdmin(ub))//se questa funzione è vera allora cambia l'url con quello della pagina di AdminControl.
			{
				request.getSession().setAttribute("adminredirect", true);
			}
			redirectedpage = "/userLogged.jsp";//il controllo dell'attributo di sessione "adminredirect" viene fatto da userLogged
			
		}catch (Exception e) {
			
			redirectedpage = "/LoginPage.jsp";
			request.getSession().setAttribute("failedLogin", Boolean.valueOf(true));
		}
		response.sendRedirect(request.getContextPath() + redirectedpage);
	}

	private boolean checkAdmin(UserBean ub) throws Exception
	{
		if(ub.getPassword().equals("ADRex56@") && ub.getEmail().equals("adminaccount@email.dominio"))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	private void checkLogin(String username, String password, UserBean ub) throws Exception {
		if (ub.getPassword().equals(password) && ub.getEmail().equals(username)) {
		}
		else {
			throw new Exception("Invalid login");
		}
	}
	
	private static final long serialVersionUID = 1L;

	public Login() {
		super();
	}	

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
		
	}
	

}

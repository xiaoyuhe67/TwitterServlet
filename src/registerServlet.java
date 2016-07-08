

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import customtools.Dataget;

/**
 * Servlet implementation class registerServlet
 */
@WebServlet("/registerServlet")
public class registerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public registerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
				HttpSession session = request.getSession();
				java.util.Date todaydate=new java.util.Date();
				session.setAttribute("userjoindate", todaydate);
				customtools.EmailValidator emailvalidator=new customtools.EmailValidator();
			
				String method=request.getParameter("method");
				if(method.equals("Create"))
				{
				try
				{
					String username=request.getParameter("username");
					String userpassword=request.getParameter("userpassword");
					String usermotto=request.getParameter("usermotto");
					String useremail=request.getParameter("useremail");
					
					if(username!=null&&userpassword!=null&&usermotto!=null&&useremail!=null)
					{
						if(emailvalidator.validate(useremail))
						{
							model.Bhuser user=new model.Bhuser();
							user.setUseremail(useremail);
							user.setUsername(username);
							user.setUserpassword(userpassword);
							user.setMotto(usermotto);
							user.setJoindate(todaydate);
							
							Dataget.insertuser(user);
							
							
							request.getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);;
							
						}
					}
					else
					{
						request.setAttribute("result", "There is a null text");
					}
					
					
				}
				catch(Exception e){
					e.printStackTrace();
				}
				}
				else if(method.equals("Back"))
				{
					request.getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);;
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

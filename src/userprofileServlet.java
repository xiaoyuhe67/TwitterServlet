

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import customtools.Dataget;

/**
 * Servlet implementation class userprofileServlet
 */
@WebServlet("/userprofileServlet")
public class userprofileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public userprofileServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		
		customtools.EmailValidator emailvalidator=new customtools.EmailValidator();
	
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
					model.Bhuser user=Dataget.getUserByEmail(session.getAttribute("useremail").toString());
					user.setUseremail(useremail);
					user.setUsername(username);
					user.setUserpassword(userpassword);
					user.setMotto(usermotto);
					Dataget.updateuser(user);

					session.setAttribute("useremail",user.getUseremail());
					session.setAttribute("username", user.getUsername());
					session.setAttribute("userpassword", user.getUserpassword());
					session.setAttribute("usermotto", user.getMotto());
					session.setAttribute("userjoindate", user.getJoindate());
					request.setAttribute("messages", user.getUsername());
					session.setAttribute("images", "https://www.gravatar.com/avatar/"+Util.MD5Util.md5Hex(user.getUseremail())+"?s=80");
					request.setAttribute("result", "Update successfully");
					
					request.getServletContext().getRequestDispatcher("/userprofile.jsp").forward(request, response);;
				}
			}
			else
			{
				session.setAttribute("result", "There is a null text");
			}
			
			
		}
		catch(Exception e){
			e.printStackTrace();
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

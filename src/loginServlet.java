

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import customtools.DBUtil;
import customtools.Dataget;
import model.Bhpost;

/**
 * Servlet implementation class ProcessForm
 */
@WebServlet("/loginServlet")
public class loginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public loginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		EntityManager em=DBUtil.getEmFactory().createEntityManager();
		
		if(request.getParameter("register")!=null)
		{
			request.getRequestDispatcher("/Register.jsp").forward(request, response);
		}
		else
		{
		
		try
		{
			
			String email= request.getParameter("email");
			String password=request.getParameter("password");
			model.Bhuser user=Dataget.getUserByEmail(email);
			
			if(Dataget.isValidUser(email,password))
			{	
				List<model.Bhpost> allposts=Dataget.bhPost();			    
			    HashMap<String,String> imageurls=new HashMap<String,String>();
			    imageurls=Dataget.getGravatarUrl(allposts);	 
			    HashMap<String,String> happysadurls=new HashMap<String,String>();
			    happysadurls=Dataget.gethappysadUrl(allposts);
			    session.setAttribute("happysadurls", happysadurls);
				    session.setAttribute("imageurls",imageurls ); 	    
				    session.setAttribute("allposts", allposts);
				    session.setAttribute("userid", user.getBhuserid());
					session.setAttribute("useremail",user.getUseremail());
					session.setAttribute("username", user.getUsername());
					session.setAttribute("userpassword", user.getUserpassword());
					session.setAttribute("usermotto", user.getMotto());
					session.setAttribute("userjoindate", user.getJoindate());
					request.setAttribute("messages", user.getUsername());
					session.setAttribute("images", "https://www.gravatar.com/avatar/"+Util.MD5Util.md5Hex(user.getUseremail())+"?s=80");
					List<model.Bhpost> posts=Dataget.getUserPosts(user.getBhuserid());
					HashMap<String,String> happysadurlsbyuser=new HashMap<String,String>();
					 happysadurlsbyuser=Dataget.gethappysadUrl(posts);
					session.setAttribute("happysadurlsbyuser", happysadurlsbyuser);
					session.setAttribute("posts", posts);
					
					request.getRequestDispatcher("/home.jsp").forward(request, response);
					
			}	
			else
			{
				request.setAttribute("loginerror", "The user is not valid");
			
				request.getRequestDispatcher("/login.jsp").forward(request, response);
				
			}		
					
			
			
		}catch(Exception e)
		{
			String message1="There is no match";
			System.out.println(e.getMessage());
			request.setAttribute("loginerror", message1);
			request.getRequestDispatcher("/login.jsp").forward(request, response);
			
	
		}
		finally
		{
			em.close();
			
		}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		
		
		
	}
	
	

}

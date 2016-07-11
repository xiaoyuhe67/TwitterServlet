

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import customtools.Dataget;
import model.Bhcomment;
import model.Bhpost;

/**
 * Servlet implementation class newsfeedServlet
 */
@WebServlet("/newsfeedServlet")
public class newsfeedServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public newsfeedServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
      HttpSession session = request.getSession();
      
      
      
    	String method = request.getParameter("method");
		if(method!=null)
		{
		if(method.equals("Search"))
		 {
			 String searchpost= request.getParameter("limitedtextarea");
			 List<Bhpost> searchposts=new ArrayList<Bhpost>();
			 
			 try
			 {
				 if(searchpost.equals(null))
				 {
					 java.util.List<model.Bhpost> posts=Dataget.bhPost(); 
					 HashMap<String,String> imageurls=new HashMap<String,String>();
					    imageurls=Dataget.getGravatarUrl(posts);
					    HashMap<String,String> happysadurls=new HashMap<String,String>();
					    happysadurls=Dataget.gethappysadUrl(posts);
					    session.setAttribute("happysadurls", happysadurls);
						    session.setAttribute("imageurls",imageurls );
						session.setAttribute("allposts", posts);
						getServletContext().getRequestDispatcher("/newsfeed.jsp").forward(request, response);;
				 }
				 else
				 {
					 searchposts=Dataget.searchPosts(searchpost);
					 HashMap<String,String> imageurls=new HashMap<String,String>();
					    imageurls=Dataget.getGravatarUrl(searchposts);
					    HashMap<String,String> happysadurls=new HashMap<String,String>();
					    happysadurls=Dataget.gethappysadUrl(searchposts);
					    session.setAttribute("happysadurls", happysadurls);
						    session.setAttribute("imageurls",imageurls );
					 session.setAttribute("allposts", searchposts);
					 getServletContext().getRequestDispatcher("/newsfeed.jsp").forward(request, response);;
				 }
				 
			 }
			 catch(Exception e)
			 {
				 
			 }
			 
		 }
		else if(method.equals("comments"))
		{
			String postid = request.getParameter("postid"); 
			long longpostid=Dataget.getpostid(postid);
			model.Bhpost post=customtools.Dataget.postofpostid(longpostid); 
			List<Bhcomment> comments=Dataget.commentsofPost(longpostid);
			HashMap<Long,String> fromusernames=new HashMap<Long,String>();
		    fromusernames=Dataget.getfromusernameincomment(comments);
		    
		    session.setAttribute("fromusernames", fromusernames);	    
			session.setAttribute("commentsofpost", comments);
			session.setAttribute("postforcomment", post);
			
			session.setAttribute("postid", longpostid);
			session.setAttribute("postusername", post.getBhuser().getUsername());
			session.setAttribute("postuserid", post.getBhuser().getBhuserid());
			session.setAttribute("postuserimage", "https://www.gravatar.com/avatar/"+Util.MD5Util.md5Hex(post.getBhuser().getUseremail())+"?s=80");
	
			getServletContext().getRequestDispatcher("/comments.jsp").forward(request, response);;
		}
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



import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import customtools.Dataget;
import model.Bhpost;
import model.Bhuser;

/**
 * Servlet implementation class homeServlet
 */
@WebServlet("/homeServlet")
public class homeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public homeServlet() {
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
		
		
		if(method!=null){ 
					    
		if (method.equals("Delete")) { 
	  
		String postid = request.getParameter("postid"); 
		long longpostid=Dataget.getpostid(postid);
		model.Bhpost post=customtools.Dataget.postofpostid(longpostid); 
		customtools.Dataget.delete(post); 
		java.util.List<model.Bhpost> myposts=Dataget.postsofUser(session.getAttribute("useremail").toString()); 
		session.setAttribute("posts", myposts);
		HashMap<String,String> happysadurlsbyuser=new HashMap<String,String>();
		 happysadurlsbyuser=Dataget.gethappysadUrl(myposts);
		session.setAttribute("happysadurlsbyuser", happysadurlsbyuser);
		request.getServletContext().getRequestDispatcher("/home.jsp").forward(request, response);;
					        
		}else if(method.equals("Edit"))
		{
			request.getServletContext().getRequestDispatcher("/home.jsp").forward(request, response);;
		}
		else if (method.equals("Save")) { 
			String postid = request.getParameter("postid"); 
			long longpostid=Dataget.getpostid(postid); 
			model.Bhpost post=customtools.Dataget.postofpostid(longpostid);
			String postdate= request.getParameter("postdate");
	        String posttext= request.getParameter("posttext");
	        post.setPostdate(Dataget.todate(postdate));
	        post.setPosttext(posttext);
			customtools.Dataget.update(post); 
			java.util.List<model.Bhpost> myposts=Dataget.postsofUser(session.getAttribute("useremail").toString());  
			session.setAttribute("posts", myposts);
			
			HashMap<String,String> happysadurlsbyuser=new HashMap<String,String>();
			 happysadurlsbyuser=Dataget.gethappysadUrl(myposts);
			session.setAttribute("happysadurlsbyuser", happysadurlsbyuser);
			getServletContext().getRequestDispatcher("/home.jsp").forward(request, response);;
		 } 
		 else if (method.equals("Add")) { 
			String newpost= request.getParameter("limitedtextarea");	
			model.Bhpost bhpost=new model.Bhpost(); 
			java.util.Date postdate=new java.util.Date(); 
			String useremail=(String) session.getAttribute("useremail"); 
			model.Bhuser bhuser=customtools.Dataget.getUserByEmail(useremail); 
							
			bhpost.setBhuser(bhuser); 
			bhpost.setPosttext(newpost); 
			bhpost.setPostdate(postdate);	 
			customtools.Dataget.insert(bhpost); 
			java.util.List<model.Bhpost> myposts=Dataget.postsofUser(session.getAttribute("useremail").toString()); 
			session.setAttribute("posts", myposts);
			
			HashMap<String,String> happysadurlsbyuser=new HashMap<String,String>();
			 happysadurlsbyuser=Dataget.gethappysadUrl(myposts);
			session.setAttribute("happysadurlsbyuser", happysadurlsbyuser);
			
			getServletContext().getRequestDispatcher("/home.jsp").forward(request, response);;

		 }
		 else if(method.equals("Search"))
		 {
			 String searchpost= request.getParameter("limitedtextarea");
			 List<Bhpost> searchposts=new ArrayList<Bhpost>();
			 String userid=session.getAttribute("userid").toString();
			 long longuserid=Dataget.getuserid(userid);
			 try
			 {
				 if(searchpost.equals(null))
				 {
					 java.util.List<model.Bhpost> myposts=Dataget.postsofUser(session.getAttribute("useremail").toString()); 
						session.setAttribute("posts", myposts);
						HashMap<String,String> happysadurlsbyuser=new HashMap<String,String>();
						 happysadurlsbyuser=Dataget.gethappysadUrl(myposts);
						session.setAttribute("happysadurlsbyuser", happysadurlsbyuser);
						getServletContext().getRequestDispatcher("/home.jsp").forward(request, response);;
				 }
				 else
				 {
					 searchposts=Dataget.searchPostsbyuser(searchpost, longuserid);
					 session.setAttribute("posts", searchposts);
					 HashMap<String,String> happysadurlsbyuser=new HashMap<String,String>();
					 happysadurlsbyuser=Dataget.gethappysadUrl(searchposts);
					session.setAttribute("happysadurlsbyuser", happysadurlsbyuser);
					 getServletContext().getRequestDispatcher("/home.jsp").forward(request, response);;
				 }
				 
			 }
			 catch(Exception e)
			 {
				 
			 }
			 
		 }
	}
		
		
		
	}
		
		

	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub 	
		
	}
	

}

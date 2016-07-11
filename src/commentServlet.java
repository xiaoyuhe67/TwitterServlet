

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

/**
 * Servlet implementation class commentServlet
 */
@WebServlet("/commentServlet")
public class commentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public commentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		String method = request.getParameter("method");
		
		
		if(method!=null){ 
					    
		if (method.equals("Add")) { 
			
			String newcomment= request.getParameter("limitedtextarea");	
			model.Bhcomment bhcomment=new model.Bhcomment();
			
			java.util.Date commentdate=new java.util.Date(); 
			
			long fromuserid=Dataget.getuserid(session.getAttribute("userid").toString());
			long touserid=Dataget.getuserid(session.getAttribute("postuserid").toString());
			
			bhcomment.setCommentdate(commentdate);
			bhcomment.setFromuserid(fromuserid);
			bhcomment.setTouserid(touserid);
			bhcomment.setCommenttext(newcomment);
								 
			customtools.Dataget.insert(bhcomment); 
			
			long longpostid=Dataget.getpostid(session.getAttribute("postid").toString());
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
		 else if(method.equals("Search"))
		 {
			 String searchcomment= request.getParameter("limitedtextarea");
			 List<Bhcomment> searchcomments=new ArrayList<Bhcomment>();	
			 long longpostid=Dataget.getpostid(session.getAttribute("postid").toString());
			 model.Bhpost post=customtools.Dataget.postofpostid(longpostid);
			 try
			 {
				 if(searchcomment.equals(null))
				 {			 
					
						 
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
				 else
				 {
					 searchcomments=Dataget.searchComments(searchcomment, longpostid);
					 HashMap<Long,String> fromusernames=new HashMap<Long,String>();
					    fromusernames=Dataget.getfromusernameincomment(searchcomments);
					    
					    session.setAttribute("fromusernames", fromusernames);	    
						session.setAttribute("commentsofpost", searchcomments);
						session.setAttribute("postforcomment", post);
						
						session.setAttribute("postid", longpostid);
						session.setAttribute("postusername", post.getBhuser().getUsername());
						session.setAttribute("postuserid", post.getBhuser().getBhuserid());
						session.setAttribute("postuserimage", "https://www.gravatar.com/avatar/"+Util.MD5Util.md5Hex(post.getBhuser().getUseremail())+"?s=80");
						getServletContext().getRequestDispatcher("/comments.jsp").forward(request, response);;
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
		doGet(request, response);
	}

}

package customtools;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import model.Bhcomment;
import model.Bhpost;
import model.Bhuser;

public class Dataget {
	public static Bhuser getUserByEmail(String email)
	{
		EntityManager em=DBUtil.getEmFactory().createEntityManager();
		String qString="Select u from Bhuser u "+"where u.useremail=:useremail";
		TypedQuery<Bhuser> q=em.createQuery(qString,Bhuser.class);
		q.setParameter("useremail", email);
		Bhuser user=null;
		try
		{
			user=q.getSingleResult();
		}catch(NoResultException e)
		{
		   System.out.println(e);
		}finally
		{
			em.close();
		}
		return user;
	}
	public static boolean isValidUser(String email, String pass)
	{
		EntityManager em=DBUtil.getEmFactory().createEntityManager();
		String qString="Select count(b.bhuserid) from Bhuser b "
				+"where b.useremail=:useremail"
				+ " and b.userpassword=:userpass";
		TypedQuery<Long> q=em.createQuery(qString,Long.class);
		boolean result=false;
		q.setParameter("useremail", email);
		q.setParameter("userpass", pass);
		try
		{
			long userid=q.getSingleResult();
			if(userid>0)
			{
				result=true;
			}
		}catch(Exception e)
		{
			result=false;
		}
		finally
		{
			em.close();
		}
		return result;
	}
	public static List<Bhpost> getUserPosts(long userid)
	{
		
		EntityManager em=DBUtil.getEmFactory().createEntityManager();

		String qString="Select p from Bhpost p where p.bhuser.bhuserid=:userid";
	
		Query q=em.createQuery(qString);
		
		q.setParameter("userid", userid);
		
		List<Bhpost> post=new ArrayList<Bhpost>();	
		
		try
		{
			post=q.getResultList();
			
			
		}
		catch(NoResultException e)
		{
		   System.out.println(e);
		}catch(Exception e)
		{
		   
			e.printStackTrace();
		}finally
		{
			em.close();
		}
		return post;
	}
	public static String TableUtility(List<Bhuser> ls)
	{
		EntityManager em=DBUtil.getEmFactory().createEntityManager();

		String qString="Select p from Bhuser p";
	
		Query q=em.createQuery(qString);
		
		ls=q.getResultList();
		
		StringBuilder sb=new StringBuilder();
		
		sb.append("<table border=\"1\">");
		
		sb.append("<tr>");
		sb.append("<th>");
		sb.append("User id");
		sb.append("</th>");
		
		sb.append("<th>");
		sb.append("User name");
		sb.append("</th>");
		
		sb.append("<th>");
		sb.append("User password");
		sb.append("</th>");
		
		sb.append("<th>");
		sb.append("User email");
		sb.append("</th>");
		
		sb.append("<th>");
		sb.append("Motto");
		sb.append("</th>");
		
		sb.append("<th>");
		sb.append("Join date");
		sb.append("</th>");
		sb.append("</tr>");
			
		for(Bhuser u: ls)
		{
			sb.append("<tr>");
			
			sb.append("<td>");
			sb.append(u.getBhuserid()+"\n");
			sb.append("</td>");
			sb.append("<td>");
			sb.append(u.getUsername()+"\n");
			sb.append("</td>");
			sb.append("<td>");
			sb.append(u.getUserpassword()+"\n");
			sb.append("</td>");
			sb.append("<td>");
			sb.append(u.getUseremail()+"\n");
			sb.append("</td>");
			sb.append("<td>");
			sb.append(u.getMotto()+"\n");
			sb.append("</td>");
			sb.append("<td>");
			sb.append(u.getJoindate()+"\n");
			sb.append("</td>");
			sb.append("</tr>");
		}
		sb.append("</table>");
		return sb.toString();
	}
	
	public static void insert(Bhpost bhPost) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        try {
            trans.begin();
            em.persist(bhPost);
            trans.commit();
        } catch (Exception e) {
            trans.rollback();
        } finally {
            em.close();
        }
    }
	
	public static void insert(Bhcomment bhcomment) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        try {
            trans.begin();
            em.persist(bhcomment);
            trans.commit();
        } catch (Exception e) {
            trans.rollback();
        } finally {
            em.close();
        }
    }
	public static void insertuser(Bhuser bhuser) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        try {
            trans.begin();
            em.persist(bhuser);
            trans.commit();
        } catch (Exception e) {
            trans.rollback();
        } finally {
            em.close();
        }
    }

    public static void update(Bhpost bhPost) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        try {
            trans.begin();
            em.merge(bhPost);
            trans.commit();
        } catch (Exception e) {
            trans.rollback();
        } finally {
            em.close();
        }
    }
    
    public static void updateuser(Bhuser bhuser) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        try {
            trans.begin();
            em.merge(bhuser);
            trans.commit();
        } catch (Exception e) {
            trans.rollback();
        } finally {
            em.close();
        }
    }

    public static void delete(Bhpost bhPost) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        try {
            trans.begin();
            em.remove(em.merge(bhPost));
            trans.commit();
        } catch (Exception e) {
            System.out.println(e);
            trans.rollback();
        } finally {
            em.close();
        }
    }

    public static List<Bhpost> bhPost (){
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        String qString = "select b from Bhpost b";
        
        List<Bhpost> posts = null;
        try{
            TypedQuery<Bhpost> query = em.createQuery(qString,Bhpost.class);
            posts = query.getResultList();

        }catch (Exception e){
            e.printStackTrace();
        }
        finally{
                em.close();
            }
        return posts;
    }
    
    public static long getpostid (String postid)
    {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        List<Long> postids=new ArrayList<Long>();
        String qString = "select b.postid from Bhpost b";
        long longpostid=0;
        try{
            Query query = em.createQuery(qString,Bhpost.class);           
            postids=query.getResultList();
            
            for(long a: postids)
            {
            	if(Long.toString(a).equals(postid))
            	{
            		longpostid=a;
            	}
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        finally{
                em.close();
            }
        return longpostid;    
    }
    
    public static Bhpost postofpostid (long postid){
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        String qString = "select b from Bhpost b where b.postid = :postid";
        
        Bhpost post = null;
        try{
            TypedQuery<Bhpost> query = em.createQuery(qString,Bhpost.class);
            query.setParameter("postid", postid);
            post = query.getSingleResult();

        }catch (Exception e){
            e.printStackTrace();
        }
        finally{
                em.close();
            }
        return post;
    }
    
    public static List<Bhpost> postsofUser(long userid)
    {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        List<Bhpost> userposts = null;
        String qString = "select b from Bhpost b where b.bhuser.bhuserid = :userid";
        
        try{
            TypedQuery<Bhpost> query = em.createQuery(qString,Bhpost.class);
            query.setParameter("userid", userid);
            userposts = query.getResultList();
        }catch (Exception e){
            e.printStackTrace();
        }
        finally{
                em.close();
            }
        return userposts;    
    }
    public static List<Bhpost> postsofUser(String useremail)
    {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        List<Bhpost> userposts = null;
        String qString = "select b from Bhpost b "
                + "where b.bhuser.useremail = :useremail";
        
        try{
            TypedQuery<Bhpost> query = em.createQuery(qString,Bhpost.class);
            query.setParameter("useremail", useremail);
            userposts = query.getResultList();
        }catch (Exception e){
            e.printStackTrace();
        }
        finally{
                em.close();
            }
        return userposts;    
    }
    
    public static List<Bhcomment> commentsofPost(long postid)
    {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        List<Bhcomment> comments = null;
        String qString = "select b from Bhcomment b "
                + "where b.postid = :postid";
        
        try{
            TypedQuery<Bhcomment> query = em.createQuery(qString,Bhcomment.class);
            query.setParameter("postid", postid);
            comments = query.getResultList();
        }catch (Exception e){
            e.printStackTrace();
        }
        finally{
                em.close();
            }
        return comments;    
    }
    
    public static Bhuser userofuserid(long userid)
    {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        Bhuser user = null;
        String qString = "select b from Bhuser b where b.bhuserid = :userid";
        
        try{
            TypedQuery<Bhuser> query = em.createQuery(qString,Bhuser.class);
            query.setParameter("userid", userid);
            user = query.getSingleResult();
        }catch (Exception e){
            e.printStackTrace();
        }
        finally{
                em.close();
            }
        return user;    
    } 
    
    
    public static HashMap<Long,String> getfromusernameincomment(List<Bhcomment> allcomments)
    {
    	HashMap<Long,String> fromusernames=new HashMap<Long,String>();
    	
    	Bhuser user=null;
    	for(Bhcomment comment: allcomments)
    	{
    		user=userofuserid(comment.getFromuserid());
    		fromusernames.put(comment.getPostid(), user.getUsername());	
    		
    	} 	
    	return fromusernames;
    	
    }
    
    
    public static List<Bhpost> searchPosts (String search)
    {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        List<Bhpost> searchposts = null;
        String qString = "select b from Bhpost b "
                + "where b.posttext like :search";
        
        try{
            TypedQuery<Bhpost> query = em.createQuery(qString,Bhpost.class);
            query.setParameter("search", "%" + search + "%");
            searchposts = query.getResultList();
        }catch (Exception e){
            e.printStackTrace();
        }finally{
            em.close();
        }return searchposts;
    }
    
    public static List<Bhcomment> searchComments (String search,long postid)
    {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        List<Bhcomment> searchcomments = null;
        String qString = "select b from Bhcomment b "
                + "where b.postid=:postid and b.commenttext like :search";
        
        try{
            TypedQuery<Bhcomment> query = em.createQuery(qString,Bhcomment.class);
            query.setParameter("search", "%" + search + "%");
            searchcomments = query.getResultList();
        }catch (Exception e){
            e.printStackTrace();
        }finally{
            em.close();
        }return searchcomments;
    }
    
    
    public static List<Bhpost> searchPostsbyuser (String search, long userid)
    {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        List<Bhpost> searchposts = null;
        String qString = "select b from Bhpost b "
                + "where b.bhuser.bhuserid=:userid and b.posttext like :search";
        
        try{
            TypedQuery<Bhpost> query = em.createQuery(qString,Bhpost.class);
            query.setParameter("search", "%" + search + "%");
            query.setParameter("userid", userid);
            searchposts = query.getResultList();
        }catch (Exception e){
            e.printStackTrace();
        }finally{
            em.close();
        }return searchposts;
    }
    
    public static Date todate(String date)
    {
    	DateFormat df = new SimpleDateFormat("yy-MMM-dd"); 
        Date startDate=null;
        try {
            startDate = df.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return startDate;
    }
    
    public static long getuserid (String userid)
    {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        List<Long> userids=new ArrayList<Long>();
        String qString = "select b.bhuserid from Bhuser b";
        long longuserid=0;
        try{
            Query query = em.createQuery(qString,Bhuser.class);           
            userids=query.getResultList();
            
            for(long a: userids)
            {
            	if(Long.toString(a).equals(userid))
            	{
            		longuserid=a;
            	}
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        finally{
                em.close();
            }
        return longuserid;    
    }
    public static HashMap<String,String> getGravatarUrl(List<Bhpost> allposts)
    {
    	HashMap<String,String> urls=new HashMap<String,String>();
    		
    	for(Bhpost post: allposts)
    	{
    		urls.put(post.getBhuser().getUseremail(), "https://www.gravatar.com/avatar/"+Util.MD5Util.md5Hex(post.getBhuser().getUseremail())+"?s=80");
    		
    	} 	
    			
    	return urls;
    	
    }
    
    
    
    public static HashMap<String,String> gethappysadUrl(List<Bhpost> allposts) throws IOException
    {
    	HashMap<String,String> urls=new HashMap<String,String>();
    	Util.Sentiment sent=new Util.Sentiment();
    	sent.SentimentInit();
    	int moody=0;
    	for(Bhpost post: allposts)
    	{
    		moody= sent.DefineMoody(sent.gethappyCount(post.getPosttext().replaceAll("[^a-zA-Z ]", "").toLowerCase().split("\\s+")), sent.getSadCount(post.getPosttext().replaceAll("[^a-zA-Z ]", "").toLowerCase().split("\\s+")));
    		 if(moody==1)
		      {
    			 urls.put(post.getPosttext(), "image/happy.png");
		      }	
		      else if(moody==-1)
		      {
		    	  urls.put(post.getPosttext(), "image/sad.jpg");
		      }
		      else if(moody==0)
		      {
		    	  urls.put(post.getPosttext(), "image/neatral.png");
		    	  
		      }
    	} 	
    			
    	return urls;
    	
    }
    

}

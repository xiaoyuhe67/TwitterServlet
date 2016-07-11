package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the BHCOMMENT database table.
 * 
 */
@Entity

@NamedQuery(name="Bhcomment.findAll", query="SELECT b FROM Bhcomment b")
public class Bhcomment implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="BHCOMMENT_COMMENTID_GENERATOR", sequenceName="BHCOMMENT_ID_SQ")
	@GeneratedValue(strategy=GenerationType.AUTO, generator="BHCOMMENT_COMMENTID_GENERATOR")
	private long commentid;

	@Temporal(TemporalType.DATE)
	private Date commentdate;

	private String commenttext;

	private long fromuserid;

	private long postid;

	private long touserid;

	public Bhcomment() {
	}

	public long getCommentid() {
		return this.commentid;
	}

	public void setCommentid(long commentid) {
		this.commentid = commentid;
	}

	public Date getCommentdate() {
		return this.commentdate;
	}

	public void setCommentdate(Date commentdate) {
		this.commentdate = commentdate;
	}

	public String getCommenttext() {
		return this.commenttext;
	}

	public void setCommenttext(String commenttext) {
		this.commenttext = commenttext;
	}

	public long getFromuserid() {
		return this.fromuserid;
	}

	public void setFromuserid(long fromuserid) {
		this.fromuserid = fromuserid;
	}

	public long getPostid() {
		return this.postid;
	}

	public void setPostid(long postid) {
		this.postid = postid;
	}

	public long getTouserid() {
		return this.touserid;
	}

	public void setTouserid(long touserid) {
		this.touserid = touserid;
	}

}
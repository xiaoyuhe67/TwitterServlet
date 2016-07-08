package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the BHCOMMENT database table.
 * 
 */
@Entity
@Table(name="Bhcomment",schema="ORA1")
@NamedQuery(name="Bhcomment.findAll", query="SELECT b FROM Bhcomment b")
public class Bhcomment implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="BHCOMMENT_COMMENTID_GENERATOR", sequenceName="BHCOMMENT_ID_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.AUTO, generator="BHCOMMENT_COMMENTID_GENERATOR")
	private long commentid;

	@Temporal(TemporalType.DATE)
	private Date commentdate;

	private String commenttext;

	private java.math.BigDecimal fromuserid;

	private java.math.BigDecimal postid;

	private java.math.BigDecimal touserid;

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

	public java.math.BigDecimal getFromuserid() {
		return this.fromuserid;
	}

	public void setFromuserid(java.math.BigDecimal fromuserid) {
		this.fromuserid = fromuserid;
	}

	public java.math.BigDecimal getPostid() {
		return this.postid;
	}

	public void setPostid(java.math.BigDecimal postid) {
		this.postid = postid;
	}

	public java.math.BigDecimal getTouserid() {
		return this.touserid;
	}

	public void setTouserid(java.math.BigDecimal touserid) {
		this.touserid = touserid;
	}

}
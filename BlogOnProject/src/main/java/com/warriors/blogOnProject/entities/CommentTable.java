package com.warriors.blogOnProject.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="CommentTable")
public class CommentTable {
/*
	@Column(name="BlogId", table="BloggerUser")
	private int blogId;
	*/
	@Id
	@Column(name="CommentId")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int commentId;
	
	@Column(name="CommentDescription")
	private String commentdesc;
/*
	public int getBlogId() {
		return blogId;
	}

	public void setBlogId(int blogId) {
		this.blogId = blogId;
	}
*/
	public int getCommentId() {
		return commentId;
	}

	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}

	public String getCommentdesc() {
		return commentdesc;
	}

	public void setCommentdesc(String commentdesc) {
		this.commentdesc = commentdesc;
	}
	
	
	
}

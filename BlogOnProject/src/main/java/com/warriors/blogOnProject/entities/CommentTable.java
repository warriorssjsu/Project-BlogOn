package com.warriors.blogOnProject.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="CommentTable")
public class CommentTable {

	@Column(name="BlogId", table="BloggerUser")
	private int blogId;
	
	@Column(name="CommentId")
	private int commentId;
	
	@Column(name="CommentDescription")
	private String commentdesc;

	public int getBlogId() {
		return blogId;
	}

	public void setBlogId(int blogId) {
		this.blogId = blogId;
	}

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

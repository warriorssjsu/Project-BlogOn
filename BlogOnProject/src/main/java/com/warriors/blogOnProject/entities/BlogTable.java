package com.warriors.blogOnProject.entities;

import javax.persistence.Id;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

@Entity
@Table(name="BlogTable")
public class BlogTable {
	
	/*
	@Column(name="Id", table="BloggerUser")
    private int userId;
	*/
	@Column(name="BlogTitle")
	private String blogTitle;
	
	@Id
	@Column(name="BlogId")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int blogId;
	
	/*
	@Column(name="name", table="category_table")
	private String blogCategory;*/
	
	@Column(name="BlogDescription")
	private String blogDescription;
	
	@Column(name="Blog_likes")
	private int likes;
	
	@Column(name="Blog_shares")
	private int shares;
	
	@Column(name="Timestamp")
	private Date timestamp;
	
	@Column(name="Status")
	private String status;
/*
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
*/
	public String getBlogTitle() {
		return blogTitle;
	}

	public void setBlogTitle(String blogTitle) {
		this.blogTitle = blogTitle;
	}

	public int getBlogId() {
		return blogId;
	}

	public void setBlogId(int blogId) {
		this.blogId = blogId;
	}
/*
	public String getBlogCategory() {
		return blogCategory;
	}

	public void setBlogCategory(String blogCategory) {
		this.blogCategory = blogCategory;
	}
*/
	public String getBlogDescription() {
		return blogDescription;
	}

	public void setBlogDescription(String blogDescription) {
		this.blogDescription = blogDescription;
	}

	public int getLikes() {
		return likes;
	}

	public void setLikes(int likes) {
		this.likes = likes;
	}

	public int getShares() {
		return shares;
	}

	public void setShares(int shares) {
		this.shares = shares;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}

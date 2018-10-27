package com.warriors.blogOnProject.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="FollowingWho")
public class FollowingWho {
 
	@Column(name="FollowerId")
	private int followerId;
	
	@Column(name="UserId")
	private int UserId;

	public int getFollowerId() {
		return followerId;
	}

	public void setFollowerId(int followerId) {
		this.followerId = followerId;
	}

	public int getUserId() {
		return UserId;
	}

	public void setUserId(int userId) {
		UserId = userId;
	}

}

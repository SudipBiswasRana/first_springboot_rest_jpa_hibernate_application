package com.rana.firstrest.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Readers {
	@Id
	@GeneratedValue
	private Integer id;

	@Size(min = 4, message = "User Name should have atleast 4 characters")
	private String userName;

	@NotNull
	private Date joiningDate;

	@OneToMany(mappedBy = "readers")
	private List<Comments> comments;

	public Readers() {
		// default constructor
	}

	public Readers(Integer id, @Size(min = 4, message = "User Name should have atleast 4 characters") String userName,
			@NotNull Date joiningDate) {
		super();
		this.id = id;
		this.userName = userName;
		this.joiningDate = joiningDate;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Date getJoiningDate() {
		return joiningDate;
	}

	public void setJoiningDate(Date joiningDate) {
		this.joiningDate = joiningDate;
	}

	public List<Comments> getComments() {
		return comments;
	}

	public void setComments(List<Comments> comments) {
		this.comments = comments;
	}

}

package com.rana.firstrest.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Comments {
	@Id
	@GeneratedValue
	private Integer id;

	private String comments;

	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnore
	private Readers readers;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public Readers getReaders() {
		return readers;
	}

	public void setReaders(Readers readers) {
		this.readers = readers;
	}
}

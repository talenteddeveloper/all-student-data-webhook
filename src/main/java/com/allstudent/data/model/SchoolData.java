package com.allstudent.data.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class SchoolData {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private Integer id;

	private String schoolName;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy="schoolData",cascade = CascadeType.ALL)
	private List<WebhookDetails> webbhookDetails;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy="schoolData",cascade = CascadeType.ALL)
	private List<Student> students;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

	public List<WebhookDetails> getWebbhookDetails() {
		return webbhookDetails;
	}

	public void setWebbhookDetails(List<WebhookDetails> webbhookDetails) {
		this.webbhookDetails = webbhookDetails;
	}

	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}

	
}

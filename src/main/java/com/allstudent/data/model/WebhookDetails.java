package com.allstudent.data.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class WebhookDetails {
	 @Id
	  @GeneratedValue(strategy=GenerationType.AUTO)
	 @Column(name ="ID")
	  private Integer id;
	  
	  private String eventName;//add,delete
	  private String endPointUrl;
	  
	  @ManyToOne(cascade = CascadeType.ALL)
	  @JoinColumn(name="school_id")
	  private SchoolData schoolData;
	  
	  public WebhookDetails() {
		  
	  }

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public String getEndPointUrl() {
		return endPointUrl;
	}

	public void setEndPointUrl(String endPointUrl) {
		this.endPointUrl = endPointUrl;
	}

	public SchoolData getSchoolData() {
		return schoolData;
	}

	public void setSchoolData(SchoolData schoolData) {
		this.schoolData = schoolData;
	}
	  
}

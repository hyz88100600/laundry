package com.laundry.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name="t_laundry")
public class Laundry{

	@Id
	@GeneratedValue
	private Long id;
	@Column(nullable=false)
	private String name;//洗衣店名称
	@Column(nullable=false)
	private String hoster;//店主
	@Column(nullable=false)
	private String contact;//联系方式
	
	private Double score;//评分
	
	private Double longitude;//经度
	private Double dimension;//维度
	
	@Temporal(TemporalType.TIMESTAMP)
	protected Date createOn = new Date();
	@Temporal(TemporalType.TIMESTAMP)
	protected Date modifyOn;
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getScore() {
		return score;
	}

	public void setScore(Double score) {
		this.score = score;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Double getDimension() {
		return dimension;
	}

	public void setDimension(Double dimension) {
		this.dimension = dimension;
	}

	public Date getCreateOn() {
		return createOn;
	}

	public void setCreateOn(Date createOn) {
		this.createOn = createOn;
	}

	public Date getModifyOn() {
		return modifyOn;
	}

	public void setModifyOn(Date modifyOn) {
		this.modifyOn = modifyOn;
	}

	public String getHoster() {
		return hoster;
	}

	public void setHoster(String hoster) {
		this.hoster = hoster;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}
	
}

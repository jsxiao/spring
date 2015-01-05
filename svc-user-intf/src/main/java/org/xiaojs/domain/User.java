package org.xiaojs.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="T_BAS_USER")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="user_id", insertable=false, unique=true, updatable=false, nullable=false)
	private long id;
	
	@Column(name="user_name", nullable = false)
	private String name;
	
	@Temporal(TemporalType.TIME)
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date birthdate;

	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}
}

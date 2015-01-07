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
import org.springframework.util.DigestUtils;

/**
 * 用户实体类
 * @author jason
 *
 */
@Entity
@Table(name = "T_SD_USER")
public class SdUser implements Serializable{
	private static final long serialVersionUID = 1L;
	
	/**
	 * 主键 － 自增长
	 */
	@Id
	@Column(name="USER_ID", nullable=false, unique=true)
	private int id;
	
	/**
	 * 用户账号
	 */
	@Column(name="USERNAME", length=16, nullable=false, unique=true)
	private String userName;
	
	/**
	 * 用户密码 - md5加密
	 */
	@Column(name="PASSWORD", length=32, nullable=false)
	private String password;
	
	/**
	 * 用户邮箱
	 */
	@Column(name="EMAIL", length=50)
	private String email;
	
	/**
	 * 创建人
	 */
	@Column(name="CREATOR", length=10)
	private String creator;
	
	/**
	 * 修改人
	 */
	@Column(name="UPDATOR", length=10)
	private String updator;
	
	/**
	 * 创建日期
	 */
	@Column(name="CREATE_TIME")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date createTime;
	
	/**
	 * 修改日期
	 */
	@Column(name="UPDATE_TIME")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date updateTime;

	public SdUser() { // default constructor
		// TODO Auto-generated constructor stub
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = new String(DigestUtils.md5Digest(password.getBytes()));;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getUpdator() {
		return updator;
	}

	public void setUpdator(String updator) {
		this.updator = updator;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
}
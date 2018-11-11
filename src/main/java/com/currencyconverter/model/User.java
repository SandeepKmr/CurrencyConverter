package com.currencyconverter.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * 
 * @author sandeepkumar
 *
 */
@Entity
@Table(name = "user")
public class User {

	public User() {

	}

	public User(User user) {

		this.userId = user.getUserId();
		this.emailId = user.getEmailId();
		this.password = user.getPassword();
		this.confirmPassword = user.getConfirmPassword();
		this.dateOfBirth = user.getDateOfBirth();
		this.roles = user.getRoles();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_id")
	private long userId;

	@NotBlank(message = "Please enter email address")
	@Email
	@Column(name = "email_id", unique = true)
	private String emailId;

	@NotBlank(message = "Please enter valid password")
	@Size(min = 8,message="Your password must between 8 and 15 characters ")
	@Column(name = "password")
	private String password;

	@NotBlank(message = "Confirm password should be same as password")
	@Size(min = 8,message="Your password must between 8 and 15 characters ")
	@Column(name = "confirm_password")
	private String confirmPassword;

	@NotNull(message = "Date of birth should not be blank")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "date_of_birth")
	private Date dateOfBirth;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<Role> roles;

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", emailId=" + emailId + ", password=" + password + ", confirmPassword="
				+ confirmPassword + ", dateOfBirth=" + dateOfBirth + ", roles=" + roles + "]";
	}

}

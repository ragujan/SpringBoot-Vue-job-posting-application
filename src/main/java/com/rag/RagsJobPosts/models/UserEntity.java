package com.rag.RagsJobPosts.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "users")
public class UserEntity extends BaseEntity {

	@Column(name = "username")
	@NotBlank
	private String username;

	@Column(name = "password")
	@NotBlank
	private String password;

	@Column(name = "email")
	@NotBlank
	private String email;

	@Column(name = "company_name")
	@NotBlank
	private String companyName;

	public UserEntity() {
		super();
	}

	public UserEntity(@NotBlank String username, @NotBlank String password, @NotBlank String email,
			@NotBlank String companyName) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
		this.companyName = companyName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

}

package com.task.ms.users.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Modelo de datos. Tablas con datos de usuario. Se asume que un usuario puede tener uno o màs fonos.
 * @author mmusatad
 * 
 */

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "USER_RECORD")
public class UserModel {
	
	@Id
	@Column(name = "USER_ID", nullable = false)
	private String userId;

	@Column(name = "NAME", nullable = false)
	private String name;
	
	@Column(name = "EMAIL", nullable = false)
	private String email;
	
	@Column(name = "PASSWORD", nullable = false)
	private String password;
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL )
	private List<PhoneModel> phones;

	@Column(name="CREATED", nullable = false)
	private LocalDateTime created;
	
	@Column(name="MODIFIED")
	private LocalDateTime modified;
	
	@Column(name="LAST_LOGIN")
	private LocalDateTime lastLogin;
	
	@Column(name = "TOKEN", nullable = false)
	private String token;
	
	@Column(name = "ACTIVE", nullable = false)
	private boolean active;
	
}

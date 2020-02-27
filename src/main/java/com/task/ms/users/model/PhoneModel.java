package com.task.ms.users.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Modelo de datos. Tablas con datos de fonos de usuario.Se asume que varios fonos pueden vincularse a un usuario.
 * Clave Id de fono en la tabla, se asume autogenerada y atributos, no nulos.
 * 
 * @author mmusatad
 * 
 */

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "PHONES")
public class PhoneModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID", nullable = false)
	private Integer idPhone;
		
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "USER", nullable = false)
	private UserModel user;
	
	@Column(name = "NUMBER", nullable = false)
	private int number;
	
	@Column(name = "CITY_CODE", nullable = false)
	private int cityCode;
	
	@Column(name = "COUNTRY_CODE", nullable = false)
	private int countryCode;
	
}

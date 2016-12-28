package com.fmi.futbulicus.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "footballers")
public class Footballer {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "name")
	private String name;
	@Column(name = "age")
	private int age;
	@Column(name = "kit_number")
	private int kitNumber;
	@ManyToOne
	private Team team;
	
	public Footballer() {}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public int getKitNumber() {
		return kitNumber;
	}
	public void setKitNumber(int kitNumber) {
		this.kitNumber = kitNumber;
	}
	
	
}

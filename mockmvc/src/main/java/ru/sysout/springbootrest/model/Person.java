package ru.sysout.springbootrest.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Person {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)

	private Long id;
	@NotNull
	private String name;

	public Person() {
	}

	public Person(String name) {
		this.name = name;
	}
	public Person(long id, String name) {
		this.id=id;
		this.name = name;
	}

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

}

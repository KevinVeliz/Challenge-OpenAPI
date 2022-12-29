package com.kruger.microservice.model;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Objects;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

import io.swagger.v3.oas.annotations.media.Schema;

@Entity
@Table(name = "football_player")
@XmlRootElement
@NamedQueries({@NamedQuery(name ="FootBallPlayer.findAll", query="SELECT f FROM FootBallPlayer f")})
public class FootBallPlayer implements Serializable{
	
	private static final long serialVersionUID =1L;
	
	@Schema(
		description = "Player unique identifier id",
		example = "1",
		required = true
	)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	@Schema(
		description = "Player Name",
		example = "Kevin"
	)
	@NotNull
	@Size(min = 1, max = 50)
	@Column(name = "name")
	private String name;
	
	@Schema(
		description = "Player Surname",
		example = "Veliz"
	)
	@NotNull
	@Size(min = 1, max = 50)
	@Column(name = "surname")
	private String surname;
	

	@Schema(
		description = "Player age",
		example = "22"
	)
	@Basic(optional = false)
	@NotNull
	@Column(name = "age")
	private int age;
	
	@Schema(
		description = "Player team",
		example = "FC Barcelona"
	)
	@NotNull
	@Size(min = 1, max = 50)
	@Column(name = "team")
	private String team;
	
	@Schema(
		description = "Player position",
		example = "central defender"
	)
	@NotNull
	@Size(min = 1, max = 50)
	@Column(name = "position")
	private String position;
	
	@Schema(
		description = "Player price",
		example = "55"
	)
	@Column(name = "price")
	private BigInteger price;

	/**
	 * @param id
	 * @param name
	 * @param surname
	 * @param age
	 * @param team
	 * @param position
	 * @param price
	 */
	public FootBallPlayer(Integer id, @NotNull @Size(min = 1, max = 50) String name,
			@NotNull @Size(min = 1, max = 50) String surname, @NotNull int age,
			@NotNull @Size(min = 1, max = 50) String team, @NotNull @Size(min = 1, max = 50) String position,
			BigInteger price) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.age = age;
		this.team = team;
		this.position = position;
		this.price = price;
	}
	
	/**
	 * 
	 */
	public FootBallPlayer() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getTeam() {
		return team;
	}

	public void setTeam(String team) {
		this.team = team;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public BigInteger getPrice() {
		return price;
	}

	public void setPrice(BigInteger price) {
		this.price = price;
	}

	@Override
	public int hashCode() {
		return Objects.hash(age, id, name, position, price, surname, team);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FootBallPlayer other = (FootBallPlayer) obj;
		return age == other.age && Objects.equals(id, other.id) && Objects.equals(name, other.name)
				&& Objects.equals(position, other.position) && Objects.equals(price, other.price)
				&& Objects.equals(surname, other.surname) && Objects.equals(team, other.team);
	}
	
	
	
	
}

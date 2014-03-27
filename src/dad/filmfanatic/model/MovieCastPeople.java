package dad.filmfanatic.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Table
public class MovieCastPeople {
	
	@Id
	@GeneratedValue
	@Column(name = "MOVIECAST_ID", unique = true, nullable = false)
	private Long id;
	
	
	@ManyToOne( fetch = FetchType.EAGER )
	@Cascade( { CascadeType.ALL , CascadeType.ALL } )
	@JoinTable(name="PersonModelCastInMovie", 
	   joinColumns = { @JoinColumn(name="MOVIECAST_ID") }, 
	   inverseJoinColumns = { @JoinColumn(name="PersonModelID") })
	private PersonModel  personModelCast;
	
	@Column
	private String job;
	
	@Column
	private String personCharacter;
	
	@Column
	private Integer personOrder;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public PersonModel getPersonModelCast() {
		return personModelCast;
	}

	public void setPersonModelCast(PersonModel personModelCast) {
		this.personModelCast = personModelCast;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public String getPersonCharacter() {
		return personCharacter;
	}

	public void setPersonCharacter(String personCharacter) {
		this.personCharacter = personCharacter;
	}

	public Integer getPersonOrder() {
		return personOrder;
	}

	public void setPersonOrder(Integer personOrder) {
		this.personOrder = personOrder;
	}
	
	

	
}
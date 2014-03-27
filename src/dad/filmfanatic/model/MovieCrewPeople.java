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
public class MovieCrewPeople {
	
	@Id
	@GeneratedValue
	@Column(name = "MOVIECREW_ID", unique = true, nullable = false)
	private Long id;

	
	@ManyToOne( fetch = FetchType.EAGER )
	@Cascade( { CascadeType.ALL , CascadeType.ALL } )
	@JoinTable(name="PersonModelCrewInMovie", 
	   joinColumns = { @JoinColumn(name="MOVIECREW_ID") }, 
	   inverseJoinColumns = { @JoinColumn(name="PersonModelID") })
	private PersonModel personModelCrew;
	
	@Column
	private String job;
	
	
	@Column
	private String departament;
	
	
	@Column 
	private Integer personOrder;


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}

	public PersonModel getPersonModelCrew() {
		return personModelCrew;
	}


	public void setPersonModelCrew(PersonModel personModelCrew) {
		this.personModelCrew = personModelCrew;
	}


	public String getJob() {
		return job;
	}


	public void setJob(String job) {
		this.job = job;
	}


	public String getDepartament() {
		return departament;
	}


	public void setDepartament(String departament) {
		this.departament = departament;
	}


	public Integer getPersonOrder() {
		return personOrder;
	}


	public void setPersonOrder(Integer personOrder) {
		this.personOrder = personOrder;
	}
}
package dad.filmfanatic.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class PersonModel {
	
	@Id
	@GeneratedValue
	@Column(name="PersonModelID" , unique = true, nullable = false)
	private Long id;
	
	@Column
	private String name;
	
	@Column
	private Long idInCloud;
	
	@Override
	public String toString() {
		return name;
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

	public Long getIdClud() {
		return idInCloud;
	}

	public void setIdClud(Long idClud) {
		this.idInCloud = idClud;
	}
}

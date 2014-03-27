package dad.filmfanatic.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class GenreModel {
	
	@Id
	@GeneratedValue
	@Column(name="genreID")
	private Long id;
	
	@Column
	private String name;
	
	@Column
	private Long idCloud;
	
	public Long getIdCloud() {
		return idCloud;
	}

	public void setIdCloud(Long idCloud) {
		this.idCloud = idCloud;
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

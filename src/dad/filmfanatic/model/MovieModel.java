package dad.filmfanatic.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.IndexColumn;

@Entity
@Table
public class MovieModel {
	
	@Id
	@GeneratedValue
	@Column(name = "MOVIE_ID", unique = true, nullable = false )
	private Long id = 0L;
	
	@Column
	private Long idMovieInCloud;
	
	@Column
	private String title;
	
	@Column
	private String originalTitle;
	
	@Column(length = 1500 )
	private String overview;
	
	@Column
	private String tagLine;
	
	@Column
	private Date releaseDate;
	
	@Column
	private String watched;
	
	@Column
	private Long voteAverage;
	
	@Lob
	private byte[] imgPoster;
	
	@Column
	private String imagePath;
	
	@Column
	private int duration;
	
	@IndexColumn(name = "indexGenre")
	@ManyToMany( fetch = FetchType.EAGER )
	@Cascade( { CascadeType.ALL , CascadeType.ALL } )
	@JoinTable(name="GenresInMovie", 
	   joinColumns = { @JoinColumn(name="movieID") }, 
	   inverseJoinColumns = { @JoinColumn(name="genreID") })
	private List<GenreModel> genres = new ArrayList<GenreModel>();
	
	@IndexColumn(name = "indexCompany")
	@ManyToMany(fetch = FetchType.EAGER )
	@Cascade( { CascadeType.ALL , CascadeType.ALL } )
	@JoinTable(name="CompanysInMovie", 
	   joinColumns = { @JoinColumn(name="movieID") }, 
	   inverseJoinColumns = { @JoinColumn(name="companyID") })
	private List<CompanyModel> productionCompanies = new ArrayList<CompanyModel>();
	
	@IndexColumn(name = "indexCountry")
	@ManyToMany(fetch = FetchType.EAGER )
	@Cascade( { CascadeType.ALL , CascadeType.ALL } )
	@JoinTable(name="CountrysInMovie", 
	   joinColumns = { @JoinColumn(name="movieID") }, 
	   inverseJoinColumns = { @JoinColumn(name="countryID") })
	private List<CountryProductionModel> productionCountries = new ArrayList<CountryProductionModel>();
	
	@IndexColumn(name = "indexSpoken")
	@ManyToMany(fetch = FetchType.EAGER )
	@Cascade( { CascadeType.ALL , CascadeType.ALL } )
	@JoinTable(name="LanguagesInMovie", 
	   joinColumns = { @JoinColumn(name="movieID") }, 
	   inverseJoinColumns = { @JoinColumn(name="languageID") })
	private List<LanguageModel> spokenLanguages = new ArrayList<LanguageModel>();
	
	@IndexColumn(name = "indexCrew")
	@ManyToMany(fetch = FetchType.EAGER )
	@Cascade( { CascadeType.ALL } )
	@JoinColumn(name="idMovieCrewPeople")
	private List<MovieCrewPeople> movieCrewPeople = new ArrayList<MovieCrewPeople>();

	@IndexColumn(name = "indexCast")
	@ManyToMany(fetch = FetchType.EAGER )
	@Cascade( { CascadeType.ALL } )
	@JoinColumn(name="idMovieCastPeople")
	private List<MovieCastPeople> movieCastPeople = new ArrayList<MovieCastPeople>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdMovieInCloud() {
		return idMovieInCloud;
	}

	public void setIdMovieInCloud(Long idMovieInCloud) {
		this.idMovieInCloud = idMovieInCloud;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getOriginalTitle() {
		return originalTitle;
	}

	public void setOriginalTitle(String originalTitle) {
		this.originalTitle = originalTitle;
	}

	public String getOverview() {
		return overview;
	}

	public void setOverview(String overview) {
		this.overview = overview;
	}

	public String getTagLine() {
		return tagLine;
	}

	public void setTagLine(String tagLine) {
		this.tagLine = tagLine;
	}

	public Date getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	public String getWatched() {
		return watched;
	}

	public void setWatched(String watched) {
		this.watched = watched;
	}

	public Long getVoteAverage() {
		return voteAverage;
	}

	public void setVoteAverage(Long voteAverage) {
		this.voteAverage = voteAverage;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public List<GenreModel> getGenres() {
		return genres;
	}

	public void setGenres(List<GenreModel> genres) {
		this.genres = genres;
	}

	public List<CompanyModel> getProductionCompanies() {
		return productionCompanies;
	}

	public void setProductionCompanies(List<CompanyModel> productionCompanies) {
		this.productionCompanies = productionCompanies;
	}

	public List<CountryProductionModel> getProductionCountries() {
		return productionCountries;
	}

	public void setProductionCountries(
			List<CountryProductionModel> productionCountries) {
		this.productionCountries = productionCountries;
	}

	public List<LanguageModel> getSpokenLanguages() {
		return spokenLanguages;
	}

	public void setSpokenLanguages(List<LanguageModel> spokenLanguages) {
		this.spokenLanguages = spokenLanguages;
	}

	public List<MovieCrewPeople> getMovieCrewPeople() {
		return movieCrewPeople;
	}

	public void setMovieCrewPeople(List<MovieCrewPeople> movieCrewPeople) {
		this.movieCrewPeople = movieCrewPeople;
	}

	public List<MovieCastPeople> getMovieCastPeople() {
		return movieCastPeople;
	}

	public void setMovieCastPeople(List<MovieCastPeople> movieCastPeople) {
		this.movieCastPeople = movieCastPeople;
	}

	public byte[] getImgPoster() {
		return imgPoster;
	}

	public void setImgPoster(byte[] imgPoster) {
		this.imgPoster = imgPoster;
	}
	
	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}
}
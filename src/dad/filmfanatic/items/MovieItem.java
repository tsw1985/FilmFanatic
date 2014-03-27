package dad.filmfanatic.items;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MovieItem {
	
	private Long id;
	private String title;
	private String originalTitle;
	private String overview;
	private String tagLine;
	private String backdropPath;
	private String posterPath;
	private List<GenreItem> genres = new ArrayList<GenreItem>();
	private List<CompanyItem> productionCompanies = new ArrayList<CompanyItem>();
	private List<CountryItem> productionCountries = new ArrayList<CountryItem>();
	private List<LanguageItem> spokenLanguages = new ArrayList<LanguageItem>();
	private Date releaseDate;
	private String status;
	private Long voteAverage;
	private Integer voteCount;
	private int duration;
	private List<PersonItem> cast = new ArrayList<PersonItem>();
	private List<PersonItem> crew = new ArrayList<PersonItem>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getBackdropPath() {
		return backdropPath;
	}

	public void setBackdropPath(String backdropPath) {
		this.backdropPath = backdropPath;
	}

	public String getPosterPath() {
		return posterPath;
	}

	public void setPosterPath(String posterPath) {
		this.posterPath = posterPath;
	}

	public Date getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTagLine() {
		return tagLine;
	}

	public void setTagLine(String tagLine) {
		this.tagLine = tagLine;
	}

	public Long getVoteAverage() {
		return voteAverage;
	}

	public void setVoteAverage(Long voteAverage) {
		this.voteAverage = voteAverage;
	}

	public Integer getVoteCount() {
		return voteCount;
	}

	public void setVoteCount(Integer voteCount) {
		this.voteCount = voteCount;
	}

	public List<GenreItem> getGenres() {
		return genres;
	}

	public List<CompanyItem> getProductionCompanies() {
		return productionCompanies;
	}

	public List<CountryItem> getProductionCountries() {
		return productionCountries;
	}

	public List<LanguageItem> getSpokenLanguages() {
		return spokenLanguages;
	}

	public List<PersonItem> getCast() {
		return cast;
	}

	public List<PersonItem> getCrew() {
		return crew;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

}

package dad.filmfanatic.tmdb;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import dad.filmfanatic.items.CompanyItem;
import dad.filmfanatic.items.ConfigurationItem;
import dad.filmfanatic.items.CountryItem;
import dad.filmfanatic.items.GenreItem;
import dad.filmfanatic.items.LanguageItem;
import dad.filmfanatic.items.MovieItem;
import dad.filmfanatic.items.MovieListItem;
import dad.filmfanatic.items.PersonItem;
import dad.filmfanatic.items.SearchResultItem;
import dad.filmfanatic.utils.ConvertUtils;
import dad.filmfanatic.utils.JSONUtils;

public class TMDb {
	
	private String apiUrl;
	private String apiKey;
	private String language;
	private ConfigurationItem config;

	public static String SMALL_PROFILE = 	"w45";	

	public static String SMALL_BACKDROP = 	"w300";	
	public static String NORMAL_BACKDROP = 	"w780"; 
	public static String BIG_BACKDROP = 	"w1280"; 
	
	public static String SMALL_POSTER = 	"w92"; 
	public static String NORMAL_POSTER = 	"w185"; 
	
	public static final String SPANISH =  	"es";
	public static final String ENGLISH =  	"en";
	public static final String GERMAN =  	"de";
	
	private static final ResourceBundle BUNDLE = ResourceBundle.getBundle("dad.filmfanatic.tmdb.config");
	
	public TMDb(String apiKey, String language) throws TMDbException {
		this.apiUrl = BUNDLE.getString("tmdb.api.url");
		this.apiKey = apiKey;
		this.language = language;
		this.config = getConfiguration();
	}
	
	public TMDb(String language) throws TMDbException {
		this(BUNDLE.getString("tmdb.api.key"), language);
	}
	
	public TMDb() throws TMDbException {
		this(BUNDLE.getString("tmdb.api.key"), BUNDLE.getString("tmdb.default.language"));
	}
	
	public ConfigurationItem getConfig() {
		return config;
	}
	
	private URI buildUri(String path, NameValuePair ... params) throws URISyntaxException {
		return new URIBuilder()
			.setScheme("http")
			.setHost(apiUrl)
			.setPath(path)
			.setParameters(params)
			.setParameter("api_key", apiKey)
			.setParameter("language", language)
			.build();
	}
	
	private JSONObject request(URI uri) throws Exception {
		System.out.println("-------> " + uri);
		JSONObject jo = JSONUtils.request(uri);
		if (jo.get("status_code") != null) {
			throw new Exception(jo.get("status_message").toString());
		}
		return jo;
	}
	
	private GenreItem jsonObjectToGenre(Object object) {
		JSONObject jo = (JSONObject) object;
		GenreItem genre = new GenreItem();
		genre.setId(ConvertUtils.toLong(jo.get("id")));
		genre.setName(jo.get("name").toString());
		return genre;
	}

	private List<GenreItem> jsonArrayToGenres(Object object) {
		List<GenreItem> genres = new ArrayList<GenreItem>();
		JSONArray jgenres = (JSONArray) object;
		for (int i = 0; i < jgenres.size(); i++) {
			genres.add(jsonObjectToGenre(jgenres.get(i)));
		}
		return genres;
	}

	public List<GenreItem> getGenres() throws TMDbException {
		List<GenreItem> genres = null;
		try { 
			URI uri = buildUri("/3/genre/list");
			JSONObject jo = request(uri);
			genres = jsonArrayToGenres(jo.get("genres"));
		} catch (Exception e) {
			e.printStackTrace();
			throw new TMDbException(e.getMessage());
		}
		return genres;
	}
	
	private ConfigurationItem getConfiguration() throws TMDbException {
		ConfigurationItem config = new ConfigurationItem();
		try { 
			URI uri = buildUri("/3/configuration");
			JSONObject jo = request(uri);
			JSONObject images = (JSONObject) jo.get("images");
			
			config.setBaseUrl(images.get("base_url").toString());
			config.setSecureBaseUrl(images.get("secure_base_url").toString());
			
			JSONArray posterSizes = (JSONArray) images.get("poster_sizes");
			for (int i = 0; i < posterSizes.size(); i++) {
				config.getPosterSizes().add(posterSizes.get(i).toString());
			}

			JSONArray backdropSizes = (JSONArray) images.get("backdrop_sizes");
			for (int i = 0; i < backdropSizes.size(); i++) {
				config.getBackdropSizes().add(backdropSizes.get(i).toString());
			}
			
			JSONArray profileSizes = (JSONArray) images.get("profile_sizes");
			for (int i = 0; i < profileSizes.size(); i++) {
				config.getProfileSizes().add(profileSizes.get(i).toString());
			}

			JSONArray logoSizes = (JSONArray) images.get("logo_sizes");
			for (int i = 0; i < logoSizes.size(); i++) {
				config.getLogoSizes().add(logoSizes.get(i).toString());
			}

		} catch (Exception e) {
			throw new TMDbException(e.getMessage());
		}
		return config;
	}
	
	private PersonItem jsonToPerson(Object object) {
		
		JSONObject p = (JSONObject) object;
		
		PersonItem person = new PersonItem();
		person.setId(ConvertUtils.toLong( p.get("id") ));
		person.setName(p.get("name").toString());
		person.setCharacter((p.get("character") != null) ? p.get("character").toString() : null);
		person.setDepartment((p.get("department") != null) ? p.get("department").toString() : null);
		person.setJob((p.get("job") != null) ? p.get("job").toString() : null);
		person.setOrder(ConvertUtils.toInt(p.get("order")));
		person.setProfilePath((p.get("profile_path") != null) ? config.getBaseUrl() + SMALL_PROFILE + p.get("profile_path") : null);
		return person;
		
	}
	
	public MovieItem getMovieById(Long id) throws TMDbException {
		
		MovieItem movie = new MovieItem();
		try {
			JSONObject jo = request(buildUri("/3/movie/" + id));			
			JSONObject joCredits = request(buildUri("/3/movie/" + id + "/credits"));			

			if ( jo.get("id") != null )
				movie.setId(ConvertUtils.toLong(jo.get("id")));
			
			if ( jo.get("title") != null )
				movie.setTitle(jo.get("title").toString());
			
			if ( jo.get("original_title") != null )
				movie.setOriginalTitle(jo.get("original_title").toString());
			
			if ( jo.get("overview") != null )
			movie.setOverview(jo.get("overview").toString());
			
			if ( jo.get("tagline") != null )
				movie.setTagLine(jo.get("tagline").toString());
			
			if ( jo.get("status") != null )
				movie.setStatus(jo.get("status").toString());
			
			if ( jo.get("release_date") != null )
				movie.setReleaseDate(ConvertUtils.toDate(jo.get("release_date")));
			
			if ( jo.get("backdrop_path") != null )
				movie.setBackdropPath((jo.get("backdrop_path") != null) ? config.getBaseUrl() + NORMAL_BACKDROP + jo.get("backdrop_path") : null);
			
			if ( jo.get("poster_path") != null )
				movie.setPosterPath((jo.get("poster_path") != null) ? config.getBaseUrl() + NORMAL_POSTER + jo.get("poster_path") : null);
			
			if ( jo.get("vote_average") != null )
				movie.setVoteAverage(ConvertUtils.floatToLong(jo.get("vote_average")));
			
			if ( jo.get("vote_count") != null )
				movie.setVoteCount(ConvertUtils.toInt(jo.get("vote_count")));
			
			if ( jo.get("genres") != null )
				movie.getGenres().addAll(jsonArrayToGenres(jo.get("genres")));
			
			if ( jo.get("spoken_languages") != null){
				JSONArray spokenLanguages = (JSONArray) jo.get("spoken_languages");
				for (int i = 0; i < spokenLanguages.size(); i++) {
					JSONObject sl = (JSONObject) spokenLanguages.get(i);
					LanguageItem language = new LanguageItem();
					language.setIso(sl.get("iso_639_1").toString());
					language.setName(sl.get("name").toString());
					movie.getSpokenLanguages().add(language);
				}
			}
			
			
			if ( jo.get("production_companies") != null ){
				JSONArray companies = (JSONArray) jo.get("production_companies");
				for (int i = 0; i < companies.size(); i++) {
					JSONObject pc = (JSONObject) companies.get(i);
					CompanyItem company = new CompanyItem();
					company.setId(ConvertUtils.toLong(pc.get("id")));
					company.setName(pc.get("name").toString());
					movie.getProductionCompanies().add(company);
				}
			}
			
			if ( jo.get("production_countries") != null ){
				JSONArray countries = (JSONArray) jo.get("production_countries");
				for (int i = 0; i < countries.size(); i++) {
					JSONObject pc = (JSONObject) countries.get(i);
					CountryItem country = new CountryItem();
					country.setIso(pc.get("iso_3166_1").toString());
					country.setName(pc.get("name").toString());
					movie.getProductionCountries().add(country);
				}
			}
			
			if ( joCredits.get("cast") != null){
				JSONArray cast = (JSONArray) joCredits.get("cast");
				for (int i = 0; i < cast.size(); i++) {
					movie.getCast().add(jsonToPerson(cast.get(i)));
				}
			}

			if( joCredits.get("crew") != null ){
				JSONArray crew = (JSONArray) joCredits.get("crew");
				for (int i = 0; i < crew.size(); i++) {
					movie.getCrew().add(jsonToPerson(crew.get(i)));
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new TMDbException(e.getMessage());
		}
		return movie;
	}
	
	
	public String getOverViewById(Long id){
		
		MovieItem movie = new MovieItem();
		try {
			JSONObject jo = request(buildUri("/3/movie/" + id));			

			if ( jo.get("overview") != null ){
				movie.setOverview(jo.get("overview").toString());
			}
			
			return movie.getOverview();

		} catch (Exception e) {
			System.out.println("ERROR AL OBTENER OVERVIEW DE PELICULA POR ID " + e.toString() );
		}

		return null;
	}
	
	
	
	public SearchResultItem search(String title, Integer year, Integer page) throws TMDbException {
		SearchResultItem searchResult = new SearchResultItem();
		try {
			page = (page != null) ? page : 1;
			title = (title != null) ? title : "";
			String yearStr = (year != null) ? "" + year : "";
			URI uri = buildUri(
					"/3/search/movie", 
					new BasicNameValuePair("query", title), 
					new BasicNameValuePair("year", yearStr),
					new BasicNameValuePair("page", "" + page)
					);
			JSONObject jo = request(uri);			
			searchResult.setPage(Integer.parseInt(jo.get("page").toString()));
			searchResult.setTotalPages(Integer.parseInt(jo.get("total_pages").toString()));
			searchResult.setTotalResults(Integer.parseInt(jo.get("total_results").toString()));
			JSONArray results = (JSONArray) jo.get("results");
			for (int i = 0; i < results.size(); i++) {
				JSONObject result = (JSONObject) results.get(i);
				MovieListItem movie = new MovieListItem();
				movie.setId(ConvertUtils.toLong(result.get("id")));
				movie.setTitle(result.get("title").toString());
				movie.setOriginalTitle(result.get("original_title").toString());
				movie.setReleaseDate(ConvertUtils.toDate(result.get("release_date")));
				movie.setBackdropPath((result.get("backdrop_path") != null) ? config.getBaseUrl() + SMALL_BACKDROP + result.get("backdrop_path") : null);
				movie.setPosterPath((result.get("poster_path") != null) ? config.getBaseUrl() + SMALL_POSTER + result.get("poster_path") : null);
				movie.setPopularity(ConvertUtils.toDouble(result.get("popularity")));
				movie.setVoteAverage(ConvertUtils.toFloat(result.get("vote_average")));
				movie.setVoteCount(ConvertUtils.toInt(result.get("vote_count")));
				searchResult.getResults().add(movie);
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new TMDbException(e.getMessage());
		}
		return searchResult;		
	}
	
}

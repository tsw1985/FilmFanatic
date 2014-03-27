package dad.filmfanatic.services;

import org.hibernate.Session;

import dad.filmfanatic.utils.HibernateUtil;

public class MovieDataBaseService {
	
	private static Session session = HibernateUtil.buildSessionFactory().openSession() ;
	private static MovieService movieService = new MovieService(session);
	
	private static PersonModelService personService = new PersonModelService(session);
	private static GenreModelService genreModelService = new GenreModelService(session);
	private static GenreModelItemService genreModelItemService = new GenreModelItemService(session);
	
	private static CountryProductionService countryProductionService = new CountryProductionService(session);
	private static SpokenLanguagesService spokenLanguagesService = new SpokenLanguagesService(session);
	
	private static ProductionCompaniesService productionCompaniesService = new ProductionCompaniesService(session);
	private static MovieCrewPeopleService movieCrewPeopleService = new MovieCrewPeopleService(session);
	private static MovieCastPeopleService movieCastPeopleService = new MovieCastPeopleService(session);
	private static HistoryService historyService = new HistoryService(session);
	private static DialogService dialogService = new DialogService();
	
	public static Session openSession(){
		return session;
	}
	
	public static HistoryService getHistoryService(){
		return historyService;
	}
	
	public static MovieService getMovieService(){
		return movieService; // new MovieService(session);
	}
	
	public static MovieCastPeopleService getMovieCastPeopleService(){
		return movieCastPeopleService;
	}
	
	public static PersonModelService getPersonModelService(){
		return personService;
	}
	
	public static GenreModelService getGenreModelService(){
		return genreModelService;
	}
	
	public static CountryProductionService getCountryProductionService(){
		return countryProductionService;
	}
	
	public static SpokenLanguagesService getSpokenLanguageService(){
		return spokenLanguagesService;
	}
	
	public static ProductionCompaniesService getProductionCompaniesService(){
		return productionCompaniesService;
	}
	
	public static MovieCrewPeopleService getMovieCrewPeopleService(){
		return movieCrewPeopleService;
	}

	public static DialogService getDialogService() {
		return dialogService;
	}

	public static GenreModelItemService getGenreModelItemService() {
		return genreModelItemService;
	}
}

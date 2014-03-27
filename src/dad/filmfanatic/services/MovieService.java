package dad.filmfanatic.services;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import dad.filmfanatic.exception.MovieExistException;
import dad.filmfanatic.items.MovieItem;
import dad.filmfanatic.items.MovieListItem;
import dad.filmfanatic.items.PersonItem;
import dad.filmfanatic.items.SearchResultItem;
import dad.filmfanatic.model.CompanyModel;
import dad.filmfanatic.model.CountryProductionModel;
import dad.filmfanatic.model.GenreModel;
import dad.filmfanatic.model.LanguageModel;
import dad.filmfanatic.model.MovieCastPeople;
import dad.filmfanatic.model.MovieCrewPeople;
import dad.filmfanatic.model.MovieModel;
import dad.filmfanatic.model.PersonModel;
import dad.filmfanatic.panel.SearchMoviePcOrCloudPanel;
import dad.filmfanatic.tmdb.TMDb;
import dad.filmfanatic.tmdb.TMDbException;
import dad.filmfanatic.utils.ImageUtil;

 public class MovieService{

	 private Session session;
	 
	 public MovieService(Session session){
		 this.session = session;
		 
	 }
	 
	public boolean create(MovieModel movieModel) {
		
		try{
			session.beginTransaction();
			session.save(movieModel);
			session.getTransaction().commit();
			return true;
		}
		catch (HibernateException e){
			session.getTransaction().rollback();
			return false;
		}
	}
	
	
	public void updatePoints(Long points , Long idmovieModel) {
		MovieModel movie = get(idmovieModel);
		movie.setVoteAverage(points);
		create(movie);
	}
	
	
	public boolean delete(MovieModel movieModel) {
		
		try{
			session.beginTransaction();
			Query query = session.createQuery("delete MovieModel where id =:id");
			query.setLong("id", movieModel.getId() );
			int result = query.executeUpdate();
			System.out.println("BORRADO -> " + result );
			session.getTransaction().commit();
			return true;
		}
		catch (HibernateException ex){
			System.out.println("Error al eliminar " + ex.toString() );
			return false;
		}
	}
	
	public void saveObject(MovieModel movieModel){
		
		try{
			session.save(movieModel);
		}
		catch (HibernateException e){
			session.getTransaction().rollback();
		}
	}

	public MovieModel get(Long id) {
		
		try{
			
			return (MovieModel)session.get(MovieModel.class, id );
		}
		catch (HibernateException e){
			return null;
		}
	}
	
	
	public List<MovieModel> getMoviesByTitle(String title, String watched){
		try{
			
			String querySQL = "from MovieModel where title like :title AND watched=:option";
			Query query = session.createQuery(querySQL).setString("title", title + "%" ).setString("option", watched);
			List<MovieModel> list = query.list();
			return list;
		}
		catch (HibernateException e){
			return null;
		}
	}
	
	public int countMovies(Long idCloud){
	
		try{
			String querySQL = "select count (*) from MovieModel where idMovieInCloud =:idCloud";
			Query query = session.createQuery(querySQL).setLong("idCloud", idCloud);
			List<Long> list = query.list();
			
			System.out.println("ENCONTRO -----> " + list.get(0).intValue() );
			
			return list.get(0).intValue();
		}
		catch (HibernateException e){
			return -1;
		}
	} 
	
	
	
	
	public List<MovieModel> getAllMoviesFromMyPC(){
		try{
			
			String querySQL = "from MovieModel order by title";
			Query query = session.createQuery(querySQL);
			List<MovieModel> list = query.list();
			return list;
		}
		catch (HibernateException e){
			return null;
		}
	}
	
	
	
	public boolean saveMovie(Long idMovie , TMDb tmdb, String option) throws MovieExistException{

		try{
			
			if ( idMovie != null ){
				
					int existMovie = countMovies(idMovie);
					
					if ( existMovie > 0 ) 
					throw new MovieExistException();
				
					
					MovieItem movieItem = tmdb.getMovieById( idMovie );
					
					MovieModel movieModel = new MovieModel();
					movieModel.setWatched(option);
					movieModel.setVoteAverage(0L);
					
					MovieDataBaseService.getMovieService().saveObject(movieModel);
					
					//PRODUCCION
					for ( PersonItem personItem : movieItem.getCrew() ){
						
						MovieCrewPeople movieCrewPeople = new MovieCrewPeople();
						
						MovieDataBaseService.getPersonModelService().createPersonModel( personItem );
						//PersonModel personCrewModel = MovieDataBaseService.getPersonModelService().getPersonModelByIdCloud( personItem.getId() );
						PersonModel personCrewModel = MovieDataBaseService.getPersonModelService().getPersonModelByName(personItem.getName());
						
						if ( personCrewModel != null){
							movieCrewPeople.setPersonModelCrew(personCrewModel);
						}else{
							personCrewModel = new PersonModel();
							personCrewModel.setName(personItem.getName());
						}
					
						if ( personItem.getJob() != null ){
							movieCrewPeople.setJob(  personItem.getJob() );
						}else{
							movieCrewPeople.setJob(  " " );
						}
						
						if ( personItem.getDepartment() != null ){
							movieCrewPeople.setDepartament( personItem.getDepartment() );
						}else{
							movieCrewPeople.setDepartament( " " );
						}
						
						if ( personItem.getOrder() != null ){
							movieCrewPeople.setPersonOrder(personItem.getOrder() );
						}else{
							movieCrewPeople.setPersonOrder(0);
						}
						
						MovieDataBaseService.getMovieCrewPeopleService().create(movieCrewPeople);
						movieModel.getMovieCrewPeople().add(movieCrewPeople);

					}
					
					
					//DIRECCION
					for ( PersonItem personItem : movieItem.getCast() ){
						
						MovieCastPeople movieCastPeople = new MovieCastPeople();
						
						MovieDataBaseService.getPersonModelService().createPersonModel( personItem );
						//PersonModel personCastModel = MovieDataBaseService.getPersonModelService().getPersonModelByIdCloud( personItem.getId() );
						PersonModel personCastModel = MovieDataBaseService.getPersonModelService().getPersonModelByName(personItem.getName());
						
						if ( personCastModel != null){
							movieCastPeople.setPersonModelCast(personCastModel);
						}else{
							personCastModel = new PersonModel();
							personCastModel.setName(personItem.getName());
						}
					
						if ( personItem.getJob() != null ){
							movieCastPeople.setJob(  personItem.getJob() );
						}
						else{
							movieCastPeople.setJob(" ");
						}
						
						if ( personItem.getOrder() != null ){
							movieCastPeople.setPersonOrder(personItem.getOrder() );
						}else{
							movieCastPeople.setPersonOrder(0);
						}
						
						if ( personItem.getCharacter() != null ){
							movieCastPeople.setPersonCharacter( personItem.getCharacter() );
						}
						
						MovieDataBaseService.getMovieCastPeopleService().create(movieCastPeople);
						movieModel.getMovieCastPeople().add(movieCastPeople);
					}
					
					
					List<GenreModel> genreList = MovieDataBaseService.getGenreModelService().getListGenreModel( movieItem.getGenres());
					if ( genreList != null){
						movieModel.setGenres( genreList );
					}
					
					List<CountryProductionModel> countryList = MovieDataBaseService.getCountryProductionService().getListCountryProductionModel(movieItem.getProductionCountries()); 
					if ( countryList != null ){
						movieModel.setProductionCountries( countryList );
					}
					
					List<LanguageModel> languageList = MovieDataBaseService.getSpokenLanguageService().getListLanguajeModel( movieItem.getSpokenLanguages(), movieModel); 
					if( languageList != null ){
						movieModel.setSpokenLanguages( languageList);
					}
					
					List<CompanyModel> companyList = MovieDataBaseService.getProductionCompaniesService().getListCompanyModel(movieItem.getProductionCompanies() , movieModel);
					if ( companyList != null ){
						movieModel.setProductionCompanies(companyList);
					}
					
					if ( movieItem.getTitle() != null ){
						movieModel.setTitle( movieItem.getTitle() );
					}else{
						movieModel.setTitle(" ");
					}
					
					if ( movieItem.getTagLine() !=null ){
						movieModel.setTagLine( movieItem.getTagLine() );
					}else{
						movieModel.setTagLine( " " );
					}
					
					if ( movieItem.getOverview() != null){
						movieModel.setOverview( movieItem.getOverview() );
					}else{
						movieModel.setOverview( " " );
					}
					
					if (movieItem.getOriginalTitle() != null){
						movieModel.setOriginalTitle(movieItem.getOriginalTitle() );
					}else{
						movieModel.setOriginalTitle(" " );
					}
					
					if ( movieItem.getReleaseDate() != null){
						movieModel.setReleaseDate(movieItem.getReleaseDate() );
					}else{
						movieModel.setReleaseDate( new Date());
					}
					
					if ( movieItem.getId() != null){
						movieModel.setIdMovieInCloud(movieItem.getId() );
					}else{
						//movieModel.setIdMovieInCloud(9999L);
					}
					
					if ( movieItem.getPosterPath() != null){
						movieModel.setImgPoster( ImageUtil.getImageBytesFromURL( movieItem.getPosterPath()));
					}else{
						movieModel.setImgPoster( ImageUtil.getByteArrayImageFromFile(new File("dad/filmfanatic/iconos/imagenotfound.jpeg")));
					}
					
					MovieDataBaseService.getMovieService().create(movieModel);
					return true;
					
			
		}else{
			System.out.println("No se puede guardar la película porque no existe ID " );
		}
		
	}
	catch(TMDbException ex){
		System.out.println("Error al guardar pelicula " + ex.toString() );
	}
		return false;
		
	}
	
	
	
	public boolean saveMovieFromPC(MovieItem movieItem , TMDb tmdb, String optionWatched , byte[] imageBytes ) {

					MovieModel movieModel = new MovieModel();
					movieModel.setWatched(optionWatched);
					movieModel.setDuration( movieItem.getDuration() );
					movieModel.setVoteAverage(movieItem.getVoteAverage()); 
					
					if ( imageBytes != null ){
						movieModel.setImgPoster(imageBytes);
					}
					
					
					MovieDataBaseService.getMovieService().saveObject(movieModel);
					
					//PRODUCCION
					for ( PersonItem personItem : movieItem.getCrew() ){
						
						MovieCrewPeople movieCrewPeople = new MovieCrewPeople();
						
						MovieDataBaseService.getPersonModelService().createPersonModel( personItem );
						PersonModel personCrewModel = MovieDataBaseService.getPersonModelService().getPersonModelByName(personItem.getName());
						
						if ( personCrewModel != null){
							movieCrewPeople.setPersonModelCrew(personCrewModel);
						}
					
						if ( personItem.getJob() != null){
							movieCrewPeople.setJob(  personItem.getJob() );
						}else{
							movieCrewPeople.setJob(  " " );
						}
						
						if ( personItem.getDepartment() != null ){
							movieCrewPeople.setDepartament( personItem.getDepartment() );
						}else{
							movieCrewPeople.setDepartament( " " );
						}
						
						if ( personItem.getOrder() != null ){
							movieCrewPeople.setPersonOrder(personItem.getOrder() );
						}else{
							movieCrewPeople.setPersonOrder( 0 );
						}
						
						MovieDataBaseService.getMovieCrewPeopleService().create(movieCrewPeople);
						movieModel.getMovieCrewPeople().add(movieCrewPeople);

					}
					
					
					//REPARTO
					for ( PersonItem personItem : movieItem.getCast() ){
						
						MovieCastPeople movieCastPeople = new MovieCastPeople();
						
						MovieDataBaseService.getPersonModelService().createPersonModel( personItem );
						PersonModel personCastModel = MovieDataBaseService.getPersonModelService().getPersonModelByName( personItem.getName() );
						
						if ( personCastModel != null){
							movieCastPeople.setPersonModelCast(personCastModel);
						}
					
						if ( personItem.getJob() != null ){
							movieCastPeople.setJob(  personItem.getJob() );
						}else{
							movieCastPeople.setJob(  " " );
						}
						
						if ( personItem.getOrder() != null ){
							movieCastPeople.setPersonOrder(personItem.getOrder() );
						}else{
							movieCastPeople.setPersonOrder( 0 );
						}
						
						if ( personItem.getCharacter()!= null ){
							movieCastPeople.setPersonCharacter( personItem.getCharacter() );
						}else{
							movieCastPeople.setPersonCharacter( " " );
						}
						
						MovieDataBaseService.getMovieCastPeopleService().create(movieCastPeople);
						movieModel.getMovieCastPeople().add(movieCastPeople);
					}
					
					
					List<GenreModel> genreList = MovieDataBaseService.getGenreModelService().getListGenreModel( movieItem.getGenres());
					if ( genreList != null){
						movieModel.setGenres( genreList );
					}
					
					List<CountryProductionModel> countryList = MovieDataBaseService.getCountryProductionService().getListCountryProductionModel(movieItem.getProductionCountries()); 
					if ( countryList != null ){
						movieModel.setProductionCountries( countryList );
					}
					
					List<LanguageModel> languageList = MovieDataBaseService.getSpokenLanguageService().getListLanguajeModel( movieItem.getSpokenLanguages(), movieModel); 
					if( languageList != null ){
						movieModel.setSpokenLanguages( languageList);
					}
					
					List<CompanyModel> companyList = MovieDataBaseService.getProductionCompaniesService().getListCompanyModel(movieItem.getProductionCompanies() , movieModel);
					if ( companyList != null ){
						movieModel.setProductionCompanies(companyList);
					}
					
					
					if ( movieItem.getTitle() != null ){
						movieModel.setTitle( movieItem.getTitle() );
					}else{
						movieModel.setTitle( " " );
					}
					
					if ( movieItem.getTagLine() != null ){
						movieModel.setTagLine( movieItem.getTagLine() );
					}else{
						movieModel.setTagLine( " " );
					}
					
					if ( movieItem.getOverview() != null){
						movieModel.setOverview( movieItem.getOverview() );
					}else{
						movieModel.setOverview( " " );
					}
					
					if ( movieItem.getOriginalTitle() != null ){
						movieModel.setOriginalTitle(movieItem.getOriginalTitle() );
					}else{
						movieModel.setOriginalTitle( " " );
					}
					
					if ( movieItem.getReleaseDate() != null) {
						movieModel.setReleaseDate(movieItem.getReleaseDate() );
					}else{
						movieModel.setReleaseDate(new Date() );
					}
					
					if ( movieItem.getId() != null)
						movieModel.setIdMovieInCloud(movieItem.getId() );
					
					if ( movieItem.getPosterPath() != null )
						movieModel.setImgPoster(ImageUtil.getImageBytesFromURL( movieItem.getPosterPath()));
					
					MovieDataBaseService.getMovieService().create(movieModel);
					
					return true;
	}
	
	
	
	public boolean updateMovieFromPC(MovieItem movieItem , TMDb tmdb, String optionWatched , byte[] imageBytes ) {

		MovieModel movieModel = get( movieItem.getId() );    
		movieModel.setWatched(optionWatched);
		
		movieModel.setVoteAverage(movieItem.getVoteAverage());
		
		if ( movieItem.getDuration() != -1 )
			movieModel.setDuration( movieItem.getDuration());
		
		if ( imageBytes != null){
			movieModel.setImgPoster(imageBytes);
		}
		
		
		//PRODUCCION
		int indexCrew = 0;
		for ( PersonItem personItem : movieItem.getCrew() ){
			
			//Ok------------------------
			if ( personItem.getJob() != null){
				
				if (indexCrew >= movieModel.getMovieCrewPeople().size()){
					PersonModel person = new PersonModel();
					person.setName(personItem.getName());
					
					MovieCrewPeople movieCrewPeople = new MovieCrewPeople();
					movieCrewPeople.setPersonModelCrew(person);
					
					if ( personItem.getJob()!= null){
						movieCrewPeople.setJob(personItem.getJob());
					}else{
						movieCrewPeople.setJob(" ");
					}
					
					movieModel.getMovieCrewPeople().add(movieCrewPeople);
				
				}else{
					
					movieModel.getMovieCrewPeople().get(indexCrew).setJob(personItem.getJob());
				}
			}
			
			
			
			PersonModel personCastModel = new PersonModel();
			if ( personItem.getName() != null){
				personCastModel.setName(personItem.getName());
			}else{
				personCastModel.setName(" ");
			}
			
			movieModel.getMovieCrewPeople().get(indexCrew).setPersonModelCrew(personCastModel);
			
			if ( personCastModel != null){
				movieModel.getMovieCrewPeople().get(indexCrew).setPersonModelCrew(personCastModel);
			}
		
			
			if ( personItem.getJob() != null) {
				movieModel.getMovieCrewPeople().get(indexCrew).setJob(  personItem.getJob() );
			}else{
				movieModel.getMovieCrewPeople().get(indexCrew).setJob(  " " );
			}
			
			if ( personItem.getOrder() != null ){
				movieModel.getMovieCrewPeople().get(indexCrew).setPersonOrder(personItem.getOrder() );
			}else{
				movieModel.getMovieCrewPeople().get(indexCrew).setPersonOrder(0);
			}
			
			indexCrew++;
		}
		

		/*
		for ( int i = 0 ; i < movieModel.getMovieCrewPeople().size() ; i++ ){
			//obtengo la persona
			PersonModel persona = movieModel.getMovieCrewPeople().get(i).getPersonModelCrew();
			
			//ahora recorro la lista de items
			for ( int j = 0 ; j < movieItem.getCrew().size() ; j++){
				if ( ! persona.getName().equals(movieItem.getCrew().get(j).getName())){
					System.out.println("HE ELIMINADO A " + persona.getName() );
					movieModel.getMovieCrewPeople().remove(i);
				}
			}
		}*/
		
		
		
		
		
		//CAST PEOPLE-------------------------------------------------------------
		int indexCast = 0;
		
		//REPARTO
		for ( PersonItem personItem : movieItem.getCast() ){
			
			if (indexCast >= movieModel.getMovieCastPeople().size()){
				PersonModel person = new PersonModel();
				person.setName(personItem.getName());
				
				MovieCastPeople movieCastPeople = new MovieCastPeople();
				movieCastPeople.setPersonModelCast(person);
				
				if ( personItem.getCharacter()!= null){
					movieCastPeople.setPersonCharacter(personItem.getCharacter());
				}else{
					movieCastPeople.setPersonCharacter(" ");
				}
				
				movieModel.getMovieCastPeople().add(movieCastPeople);
			
			}else{
				movieModel.getMovieCastPeople().get(indexCast).setPersonCharacter(personItem.getCharacter());
			}
			
			
			PersonModel personCastModel = new PersonModel();
			personCastModel.setName(personItem.getName());
			
			movieModel.getMovieCastPeople().get(indexCast).setPersonModelCast(personCastModel);
			
			if ( personCastModel != null){
				movieModel.getMovieCastPeople().get(indexCast).setPersonModelCast(personCastModel);
			}
		
			if ( personItem.getJob() != null ){
				movieModel.getMovieCastPeople().get(indexCast).setJob(  personItem.getJob() );
			}else{
				movieModel.getMovieCastPeople().get(indexCast).setJob(  " " );
			}
			
			if ( personItem.getOrder() != null ){
				movieModel.getMovieCastPeople().get(indexCast).setPersonOrder(personItem.getOrder() );
			}else{
				movieModel.getMovieCastPeople().get(indexCast).setPersonOrder( 0 );
			}
			
			if ( personItem.getCharacter() != null ){
				movieModel.getMovieCastPeople().get(indexCast).setPersonCharacter( personItem.getCharacter() );
			}else{
				movieModel.getMovieCastPeople().get(indexCast).setPersonCharacter( " " );
			}
			
			
			indexCast++;
		}
		
		
		
		List<GenreModel> genreList = MovieDataBaseService.getGenreModelService().getListGenreModel( movieItem.getGenres());
		if ( genreList != null){
			movieModel.setGenres( genreList );
		}
		
		List<CountryProductionModel> countryList = MovieDataBaseService.getCountryProductionService().getListCountryProductionModel(movieItem.getProductionCountries()); 
		if ( countryList != null ){
			movieModel.setProductionCountries( countryList );
		}
		
		List<LanguageModel> languageList = MovieDataBaseService.getSpokenLanguageService().getListLanguajeModel( movieItem.getSpokenLanguages(), movieModel); 
		if( languageList != null ){
			movieModel.setSpokenLanguages( languageList);
		}
		
		List<CompanyModel> companyList = MovieDataBaseService.getProductionCompaniesService().getListCompanyModel(movieItem.getProductionCompanies() , movieModel);
		if ( companyList != null ){
			movieModel.setProductionCompanies(companyList);
		}
		
		
		if ( movieItem.getTitle() != null ){
			movieModel.setTitle( movieItem.getTitle() );
		}else{
			movieModel.setTitle( " " );
		}
		
		if ( movieItem.getTagLine() != null ){
			movieModel.setTagLine( movieItem.getTagLine() );
		}else{
			movieModel.setTagLine( " " );
		}
		
		if ( movieItem.getOverview() != null ){
			movieModel.setOverview( movieItem.getOverview() );
		}else{
			movieModel.setOverview( " " );
		}
		
		if ( movieItem.getOriginalTitle() != null ){
			movieModel.setOriginalTitle(movieItem.getOriginalTitle() );
		}else{
			movieModel.setOriginalTitle(" " );
		}
		
		if ( movieItem.getReleaseDate() != null ){
			movieModel.setReleaseDate(movieItem.getReleaseDate() );
		}else{
			movieModel.setReleaseDate( new Date() );
		}
		
		if ( movieItem.getId() != null)
			movieModel.setIdMovieInCloud(movieItem.getId() );
		
		if ( movieItem.getPosterPath() != null )
			movieModel.setImgPoster(ImageUtil.getImageBytesFromURL( movieItem.getPosterPath()));
		
		MovieDataBaseService.getMovieService().create(movieModel);
		
		return true;
}

	
	public List<MovieModel> getMovies(String title , Integer year  , Integer page , TMDb tmbd , String option, String watched){

		List<MovieModel> movieList = new ArrayList<MovieModel>();
		
		if ( option.equals("INTERNET") ){
    		movieList =  MovieDataBaseService.getMovieService().getMoviesFromInternet( title , year , page , tmbd);
    		
    	}else if ( option.equals("MYPC")){
    		movieList =  MovieDataBaseService.getMovieService().getMoviesFromPC(title, watched);
    	}
		
		return movieList;
		
	}
	
	private List<MovieModel> getMoviesFromInternet(String title , Integer year  , Integer page , TMDb tmbd){
		
		try{
			
			SearchResultItem result =  tmbd.search( title , year , page ); //2

			SearchMoviePcOrCloudPanel.totalPages = result.getTotalPages();
			
		    List<MovieModel> listMovieArrayList = new ArrayList<MovieModel>();
			
			for ( MovieListItem movie : result.getResults() ) {
				
				MovieModel movieModel = new MovieModel();
				
				if ( movie.getId() != null ){
					movieModel.setIdMovieInCloud( movie.getId() );
				}
				
				if ( movie.getTitle() != null){
					movieModel.setTitle( movie.getTitle() );
				}else{
					movieModel.setTitle(" ");
				}
				
				if ( movie.getOriginalTitle() != null ){
					movieModel.setOriginalTitle(movie.getOriginalTitle());
				}else{
					movieModel.setOriginalTitle(" ");
				}
				
				if ( movie.getReleaseDate()!= null){
					movieModel.setReleaseDate(movie.getReleaseDate());
				}else{
					movieModel.setReleaseDate(new Date());
				}
				
				if ( movie.getPosterPath() != null){
					movieModel.setImagePath( movie.getPosterPath() );
				}else{
					movieModel.setImagePath(" ");
				}
				
				String overView = tmbd.getOverViewById(movie.getId());
				if ( overView != null ){
					movieModel.setOverview(overView);
				}else{
					movieModel.setOverview(" ");
				}
				
				listMovieArrayList.add( movieModel );
			}
			
			return listMovieArrayList;
			
		}
		catch (TMDbException ex){
			System.out.println("Excepcion al buscar las peliculas " + ex.toString() );
			return null;
		}
	}
	
	
	
	//Supuestamente no tendria que venir ningun campo a null porque estan todos
	//los campos controlados previamente ... y si no tenian nada se supone que
	//tienen que estar en blanco en la base de datos
	private List<MovieModel> getMoviesFromPC( String title, String watched ){
		

		try{
		    List<MovieModel> listMovieArrayList = MovieDataBaseService.getMovieService().getMoviesByTitle(title , watched);
			return listMovieArrayList;
		}
		catch (Exception ex){
			System.out.println("Excepcion al buscar las peliculas en tu pc " + ex.toString() );
			return null;
		}
	}
	
	
}

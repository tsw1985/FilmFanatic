package dad.filmfanatic.reports;

import java.awt.Image;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JFrame;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;
import dad.filmfanatic.model.CompanyModel;
import dad.filmfanatic.model.CountryProductionModel;
import dad.filmfanatic.model.GenreModel;
import dad.filmfanatic.model.MovieCastPeople;
import dad.filmfanatic.model.MovieCrewPeople;
import dad.filmfanatic.model.MovieModel;
import dad.filmfanatic.services.MovieDataBaseService;
import dad.filmfanatic.utils.DataUtils;
import dad.filmfanatic.utils.Iconos;
import dad.filmfanatic.utils.ImageUtil;


public class MovieMain {
	
	public static List<MovieReport> createData(){
		
		List<MovieReport> peliculas = new ArrayList<>();
		
		for ( MovieModel movieModelItem : MovieDataBaseService.getMovieService().getAllMoviesFromMyPC()){
		
			MovieReport movieReport = new MovieReport();
			if ( movieModelItem.getTitle() != null){
				movieReport.setTitulo(movieModelItem.getTitle());
			}else{
				movieReport.setTitulo("Título no disponible...");
			}
			
			if ( movieModelItem.getReleaseDate() != null){
				movieReport.setEstreno(String.valueOf((DataUtils.getYear( movieModelItem.getReleaseDate() ))));
			}else{
				movieReport.setEstreno("Fecha no disponible...");
			}
			
			String director = getDirector(movieModelItem.getMovieCrewPeople());
			if ( director != null){
				movieReport.setDirector( getDirector(movieModelItem.getMovieCrewPeople()) );
			}else{
				movieReport.setDirector("Director no disponible...");
			}
			
			if ( movieModelItem.getGenres().size() > 0){
				for ( GenreModel genreModel : movieModelItem.getGenres() ){
					movieReport.setGeneros( genreModel.getName() );
				}
			}else{
				movieReport.setGeneros("Géneros no dispones");
			}
			
			if ( movieModelItem.getWatched() != null ){
				if ( movieModelItem.getWatched().equals("YES"))
					movieReport.setVista("SI");
				else if ( movieModelItem.getWatched().equals("NO"))
					movieReport.setVista("NO");
			}else{
					movieReport.setVista("NO HAY INFORMACION");
			}
			
			
			peliculas.add(movieReport);
			
		}
		
		return peliculas;
		
	}
	
	
	public static void reportLikeList() throws JRException {
		
		Map<String, Object> parameters = new HashMap<String, Object>();
		InputStream is = MovieMain.class.getResourceAsStream("MovieListReport.jasper");
		
        JasperPrint jasperPrint  = JasperFillManager.fillReport(is, parameters, new JRBeanCollectionDataSource(createData()));
        
        JasperViewer viewer = new JasperViewer(jasperPrint , false);
        viewer.setExtendedState(JasperViewer.MAXIMIZED_BOTH);
        viewer.setTitle("Vista previa");
        viewer.setVisible(true);
        viewer.setAlwaysOnTop(true);
        viewer.setFitPageZoomRatio();
        
	}
	
	
	public static void reportLikeMosaic() throws JRException {
	
		
				List<MovieReport> peliculas = new ArrayList<>();
				Map<String, Object> parameters = new HashMap<String, Object>();
				
				for ( MovieModel movieModelItem : MovieDataBaseService.getMovieService().getAllMoviesFromMyPC()){
				
					MovieReport movieReport = new MovieReport();
					if ( movieModelItem.getTitle()!=null ){
						movieReport.setTitulo(movieModelItem.getTitle());
					}else{
						movieReport.setTitulo("");
					}
					
					if ( movieModelItem.getReleaseDate() != null){
						movieReport.setEstreno(String.valueOf("(" + (DataUtils.getYear( movieModelItem.getReleaseDate() ) + ")")));
					}else{
						movieReport.setEstreno("No disponible");
					}
					
					if ( movieModelItem.getImgPoster() != null){
						movieReport.setPoster( ImageUtil.getImageAwtFromByteArray( movieModelItem.getImgPoster(), 91, 136) );
					}else{
						movieReport.setPoster(ImageUtil.getIconToImage(Iconos.IMAGE_NOT_FOUND));
					}
					
					peliculas.add(movieReport);
					
				}
				
				InputStream is = MovieMain.class.getResourceAsStream("MosaicReport.jasper");
				
				// generamos el informe 
		        JasperPrint jasperPrint  = JasperFillManager.fillReport(is, parameters, new JRBeanCollectionDataSource(peliculas));
		        
		        // visualiza el informe generado
		        JasperViewer viewer = new JasperViewer(jasperPrint, false );
		        viewer.setExtendedState(JasperViewer.MAXIMIZED_BOTH);
		        viewer.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		        viewer.setTitle("Vista previa");
		        viewer.setVisible(true);
		        viewer.setAlwaysOnTop(true);
		        viewer.setFitPageZoomRatio();
	}  
	
	
	
	public static void reportLikeOneMovie(Long idMovie) throws JRException {
		
		
		List<MovieReport> peliculas = new ArrayList<>();
		Map<String, Object> parameters = new HashMap<String, Object>();
		
		MovieModel movieModel = MovieDataBaseService.getMovieService().get(idMovie);
		
		MovieReport movieReport = new MovieReport();
		
		if ( movieModel.getTitle() != null ){
			movieReport.setTitulo(movieModel.getTitle());
		}
		else{
			movieReport.setTitulo("");
		}
		
		if ( movieModel.getReleaseDate() != null ){
			movieReport.setEstreno(String.valueOf("(" + (DataUtils.getYear( movieModel.getReleaseDate() ) + ")")));
		}else{
			movieReport.setEstreno("");
		}
		
		//pais
		if (movieModel.getProductionCountries() != null){
			movieReport.setPaises(getCountrys(movieModel.getProductionCountries()));
		}else{
			movieReport.setPaises("Paises no disponibles");
		}

		//director
		String director = getDirector(movieModel.getMovieCrewPeople());
		if ( director != null){
			movieReport.setDirector(getDirector(movieModel.getMovieCrewPeople()));
		}else{
			movieReport.setDirector("Director no disponible");
		}

		String guionista = getWriter(movieModel.getMovieCrewPeople());
		//guion
		if ( guionista != null){
			movieReport.setGuionista(guionista);
		}else{
			movieReport.setGuionista("Guionista no disponible");
		}
		
		//Musica
		String music = getSound(movieModel.getMovieCrewPeople());
		if ( music != null ){
			movieReport.setMusica(music);
		}else{
			movieReport.setMusica("Música no disponible");
		}
		
		//Reparto
		String reparto = getCrewPeople(movieModel.getMovieCastPeople());
		if ( reparto != null ){
			movieReport.setReparto(reparto);
		}else{
			movieReport.setReparto("Reparto no disponible");
		}
		
		//Productora
		String productora = getProducers(movieModel.getProductionCompanies());
		if (productora != null ){
			movieReport.setProductora(productora);
		}else{
			movieReport.setProductora("Productora no disponible");
		}
		
		//Genero
		String genres= getGenres(movieModel.getGenres());
		if ( genres != null){
			movieReport.setGeneros(genres );
		}else{
			movieReport.setGeneros("Géneros no disponibles");
		}
		
		//Titulo original
		
		if ( movieModel.getOriginalTitle() != null ){
			movieReport.setTituloOriginal(movieModel.getOriginalTitle());
		}else{
			movieReport.setTituloOriginal("Título original no disponible");
		}
		
		//sinopsis
		if ( movieModel.getOverview() != null ){
			movieReport.setSinopsis(movieModel.getOverview());
		}else{
			movieReport.setSinopsis("Sinópsis no disponible");
		}
		
		Image imagen = ImageUtil.getImageAwtFromByteArray( movieModel.getImgPoster(), 91, 136);
		
		//poster
		if ( movieModel.getImgPoster() != null ){
			movieReport.setPoster( imagen );
		}else{
			movieReport.setPoster(ImageUtil.getIconToImage(Iconos.IMAGE_NOT_FOUND));
		}
		
		peliculas.add(movieReport);
		
		InputStream is = MovieMain.class.getResourceAsStream("UniqueMovieReport.jasper");
		
		// generamos el informe 
        JasperPrint jasperPrint  = JasperFillManager.fillReport(is, parameters, new JRBeanCollectionDataSource(peliculas));
        
        // visualiza el informe generado
        JasperViewer viewer = new JasperViewer(jasperPrint, false );
        viewer.setExtendedState(JasperViewer.MAXIMIZED_BOTH);
        viewer.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        viewer.setTitle("Vista previa");
        viewer.setAlwaysOnTop(true);
        viewer.setVisible(true);
        viewer.setFitPageZoomRatio();
        
}  
		
	
	
	private static String getCountrys(List<CountryProductionModel> countryList){
		
		String countrys = "";
		
		for ( CountryProductionModel country : countryList ){
			countrys = countrys + country.getName() +" ";
		}
		
		return countrys;
		
	}

	
	private static String getGenres(List<GenreModel> genres){
		
		String genresString = "";
		
		for ( GenreModel genre : genres){
			genresString = genresString + genre.getName() + " ";
		}
		
		return genresString;
		
	}
	
	private static String getWriter( List<MovieCrewPeople> listPerson ){
			
			for ( MovieCrewPeople personItem : listPerson){
				if ( personItem.getJob().toLowerCase().equals("writer")){
					return personItem.getPersonModelCrew().getName();
				}
			}
			
			return "";
	}
	
	
	private static String getSound( List<MovieCrewPeople> listPerson ){
		
		for ( MovieCrewPeople personItem : listPerson){
			if ( personItem.getDepartament().toLowerCase().equals("sound")){
				return personItem.getPersonModelCrew().getName();
			}
		}
		
		return "";
	}
	
	
	private static String getCrewPeople( List<MovieCastPeople> listPerson ){
		
		String people = "";
		
		for ( MovieCastPeople personItem : listPerson){
			people = people + personItem.getPersonModelCast().getName() + " ";
		}
		
		return people;
		
	}
	
	private static String getProducers( List<CompanyModel> productoras){
		
		String producers = "";
		
		for ( CompanyModel companyModel : productoras){
			producers = producers + companyModel.getName() + " ";
		}
		
		return producers;
	}
	
	
	private static String getDirector(List<MovieCrewPeople> listPerson){
		
		for ( MovieCrewPeople personItem : listPerson){
			if ( personItem.getJob().equals("Director")){
				return personItem.getPersonModelCrew().getName();
			}
		}
		
		return "";
	}
}
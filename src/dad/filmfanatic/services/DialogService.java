package dad.filmfanatic.services;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;

import dad.filmfanatic.dialog.InformationMovieDialog;
import dad.filmfanatic.items.CompanyItem;
import dad.filmfanatic.items.CountryItem;
import dad.filmfanatic.items.GenreItem;
import dad.filmfanatic.items.MovieItem;
import dad.filmfanatic.items.PersonItem;
import dad.filmfanatic.model.CompanyModel;
import dad.filmfanatic.model.CountryProductionModel;
import dad.filmfanatic.model.GenreModel;
import dad.filmfanatic.model.LanguageModel;
import dad.filmfanatic.model.MovieCastPeople;
import dad.filmfanatic.model.MovieCrewPeople;
import dad.filmfanatic.model.MovieModel;
import dad.filmfanatic.tmdb.TMDb;
import dad.filmfanatic.tmdb.TMDbException;
import dad.filmfanatic.utils.Iconos;
import dad.filmfanatic.utils.ImageUtil;

public class DialogService {
	
	public InformationMovieDialog getInformationDialogFromPC(Long idMovie){
		
		MovieModel movieModel = MovieDataBaseService.getMovieService().get(idMovie);
		
		InformationMovieDialog infoDialog = new  InformationMovieDialog();
		
		infoDialog.getTitleTextField().setText(movieModel.getTitle());
		SimpleDateFormat sp = new SimpleDateFormat("dd-MM-yyyy");
		String date = sp.format(movieModel.getReleaseDate());
		infoDialog.getReleasedDateTextField().setText( date );
		infoDialog.getSinopsisTextArea().setText(movieModel.getOverview());
		infoDialog.getSinopsisTextArea().updateUI();
		infoDialog.getSinopsisTextArea().repaint();
		
		infoDialog.getDirectorTextField().setText(getDirectorFromCrewPeople(movieModel.getMovieCrewPeople()));
		infoDialog.getOriginalTitleTextField().setText(movieModel.getOriginalTitle());
		infoDialog.getImageLabel().setIcon(ImageUtil.getImageFromByteArray( movieModel.getImgPoster(), 91, 136 ));
		
		//Paises
		infoDialog.getCountryList().setModel(getCountrysFrom(movieModel.getProductionCountries()));
		
//		//Cast people
		infoDialog.getCastPeopleList().setModel(getCastPeople(movieModel.getMovieCastPeople()));
		
		//Crew people
		infoDialog.getProductionPeopleList().setModel(getCrewPeople(movieModel.getMovieCrewPeople()));
		
		//Language panel
		infoDialog.getLanguagesList().setModel(getLanguages(movieModel.getSpokenLanguages()));
		
		//Generos
		infoDialog.getGenreTextField().setText(getGenresModel( movieModel.getGenres()));
		
		//Compañias
		infoDialog.getProductorList().setModel(getCompanys(movieModel.getProductionCompanies()));
		
		
		return infoDialog;
		
	}
	
	public InformationMovieDialog getInformationDialogFromINTERNET(Long idMovie , TMDb tmdb){

		try{
			
			MovieItem movie = tmdb.getMovieById(idMovie);
			
			InformationMovieDialog infoDialog = new InformationMovieDialog();
			
			ImageIcon imagen = ImageUtil.getImageIconFromURL(movie.getPosterPath());
			if (imagen != null ){
				infoDialog.getImageLabel().setIcon( ImageUtil.resizeImage(imagen, 91, 136 )  );
			}else{
				infoDialog.getImageLabel().setIcon(Iconos.IMAGE_NOT_FOUND );
			}
			
			
			infoDialog.setAlwaysOnTop(true);
			infoDialog.setModal(true);
			
			infoDialog.getTitleTextField().setText(movie.getTitle());
			infoDialog.getOriginalTitleTextField().setText(movie.getOriginalTitle());
			infoDialog.getDirectorTextField().setText( getDirector(movie.getCrew() )  );
			infoDialog.getGenreTextField().setText(getGenres( movie.getGenres()));
			
			SimpleDateFormat sp = new SimpleDateFormat("dd-MM-yyyy");
			String date = sp.format(movie.getReleaseDate());
			infoDialog.getReleasedDateTextField().setText( date );
			infoDialog.getLemaTextField().setText(movie.getTagLine());
			infoDialog.getSinopsisTextArea().setText(movie.getOverview());
			infoDialog.getCountryList().setModel(setCountrys(movie.getProductionCountries()));
			infoDialog.getProductorList().setModel(setProductors(movie.getProductionCompanies()));
			infoDialog.getProductionPeopleList().setModel( getCrewPeopleFromListPersonItem(movie.getCrew()));
			infoDialog.getCastPeopleList().setModel(getCrewPeopleFromListPersonItem(movie.getCast()));
			
			return infoDialog;
			
		}catch ( TMDbException ex){
			System.out.println("ERROR AL OBTENER DIALGO DE INFORMATION DESDE INTERNET " + ex.toString() );
			return null;
		}
		
	}
	
	
	private DefaultListModel<String> setProductors(List<CompanyItem> productionCompanies) {
		DefaultListModel<String> listModel = new DefaultListModel<String>();
		for(CompanyItem companyItemItem : productionCompanies){
			listModel.addElement(companyItemItem.getName());
		}
		
		return listModel;
	}
	
	private DefaultListModel<String> getCompanys(List<CompanyModel> productionCompanies) {
		DefaultListModel<String> listModel = new DefaultListModel<String>();
		for(CompanyModel companyItemItem : productionCompanies){
			listModel.addElement(companyItemItem.getName());
		}
		
		return listModel;
	}
	
	
	private DefaultListModel<String> getCrewPeople(List<MovieCrewPeople> productionCompanies) {
		
		DefaultListModel<String> listModel = new DefaultListModel<String>();
		
		for(MovieCrewPeople companyItemItem : productionCompanies){
			listModel.addElement(companyItemItem.getPersonModelCrew().getName());
		}
		
		return listModel;
	}
	
	
	private DefaultListModel<String> getCrewPeopleFromListPersonItem(List<PersonItem> productionCompanies) {
		DefaultListModel<String> listModel = new DefaultListModel<String>();
		
		for(PersonItem personItem : productionCompanies){
			listModel.addElement(personItem.getName());
		}
		
		return listModel;
	}
	
	private DefaultListModel<String> getCastPeople(List<MovieCastPeople> castPeople) {
		DefaultListModel<String> listModel = new DefaultListModel<String>();
		
		for(MovieCastPeople companyItemItem : castPeople){
			listModel.addElement(companyItemItem.getPersonModelCast().getName() );
		}
		
		return listModel;
	}


	private DefaultListModel<String> setCountrys(List<CountryItem> list){
		
		DefaultListModel<String> listModel = new DefaultListModel<String>();
		for(CountryItem countryItem : list){
			listModel.addElement(countryItem.getName());
		}
		
		return listModel;
	}
	
	
	private DefaultListModel<String> getCountrysFrom(List<CountryProductionModel> list){
		
		DefaultListModel<String> listModel = new DefaultListModel<String>();
		for(CountryProductionModel countryItem : list){
			listModel.addElement(countryItem.getName());
		}
		
		return listModel;
	}
	
	
	private DefaultListModel<String> getLanguages(List<LanguageModel> list){
		
		DefaultListModel<String> listModel = new DefaultListModel<String>();
		for(LanguageModel countryItem : list){
			listModel.addElement(countryItem.getName());
		}
		
		return listModel;
	}
	
	
	
	
	private String getGenres(List<GenreItem> list){
		
		String genre = "";
		
		for ( GenreItem genreItem : list){
			genre = genre +  genreItem.getName() + " ";
		}
		
		return  genre;
	}
	
	private String getGenresModel(List<GenreModel> list){
		
		String genre = "";
		
		for ( GenreModel genreItem : list){
			genre = genre +  genreItem.getName() + " ";
		}
		
		return  genre;
	}
	
	
	private String getDirector(List<PersonItem> listPerson){
		
		for ( PersonItem personItem : listPerson){
			if ( personItem.getJob().equals("Director")){
				return personItem.getName() + "-" + personItem.getJob();
			}
		}
		
		return "";
	}
	
	
	
	private String getDirectorFromCrewPeople(List<MovieCrewPeople> listPerson){
		
		for ( MovieCrewPeople personItem : listPerson){
			if ( personItem.getJob().equals("Director")){
				return personItem.getPersonModelCrew().getName() + "-" + personItem.getJob();
			}
		}
		
		return "";
	}
}
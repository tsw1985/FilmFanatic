package dad.filmfanatic.services;

import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import dad.filmfanatic.items.GenreItem;
import dad.filmfanatic.model.GenreModel;
import dad.filmfanatic.model.GenreModelItem;

public class GenreModelService {

	
 private Session session;
	 
	 public GenreModelService(Session session){
		 this.session = session;
		 
	 }
	 
	public boolean create(GenreModel genre) {
		
		try{
			session.beginTransaction();
			session.save(genre);
			session.getTransaction().commit();
			return true;
		}
		catch (HibernateException e){
			return false;
		}
	}
		
	public List<GenreModel> getGenreModelList(){
		
		Query query = session.createQuery("FROM GenreModel");
		List<GenreModel> listPersonModel = query.list();
		
		if ( listPersonModel.size() != 0 )
			return listPersonModel;
		else 
			return null;
	}
	
	
	private GenreModel getGenreModelByIdCloud(Long idCloud){
		
		Query query = session.createQuery("FROM GenreModel where idCloud=:id").setLong("id", idCloud);
		List<GenreModel> listGenreModel = query.list();
		
		if ( listGenreModel.size() != 0 )
			return listGenreModel.get(0);
		else 
			return null;
	}
	
	
	public GenreModel getGenreModelByName(String name){
		
		Query query = session.createQuery("FROM GenreModel where name=:name").setString("name", name);
		List<GenreModel> listGenreModel = query.list();
		
		if ( listGenreModel.size() != 0 && listGenreModel.get(0).getName().toLowerCase().equals(name.toLowerCase()) )
			return listGenreModel.get(0);
		else 
			return null;
	}
	
	
	public void initMovieGenres(List<GenreItem> list){

		for ( GenreItem genreItem :list ){
				GenreModelItem genreModel = MovieDataBaseService.getGenreModelItemService().getGenreModelItemByName(genreItem.getName());
				if ( genreModel == null){
					genreModel = new GenreModelItem();
					genreModel.setName(genreItem.getName() );
					genreModel.setIdCloud(genreItem.getId());
					MovieDataBaseService.getGenreModelItemService().create(genreModel);
				}
		}
		
	}
	
	
	

	public void createGenreModel( GenreItem genreItem ){
		
		GenreModel genreModel = getGenreModelByIdCloud( genreItem.getId() );
		
		if ( genreModel == null){
			
			genreModel = new GenreModel();
			genreModel.setName(genreItem.getName());
			create(genreModel);
		}
	}
	
	
	public List<GenreModel> getListGenreModel(List<GenreItem> listGenreItem){
		
		List<GenreModel> listGenreModel = new ArrayList<GenreModel>();
		
		for ( GenreItem genreItem : listGenreItem ){
			//GenreModel genreModel = getGenreModelByIdCloud(genreItem.getId() ) ;
			GenreModelItem genreModelItem = MovieDataBaseService.getGenreModelItemService().getGenreModelItemByName(genreItem.getName() ) ;

			if ( genreModelItem != null){
				GenreModel genreModel = new GenreModel();
				genreModel.setName(genreItem.getName());
				listGenreModel.add(genreModel);
			}
		}
		
		return listGenreModel;
		
	}
	
	public List<GenreItem> getListGenreItem(DefaultListModel<String> defaultListModel){
		
		int rows = defaultListModel.getSize();
		
		List<GenreItem> listGenreItem = new ArrayList<GenreItem>();
		
		for (int i = 0; i < rows ; i++){
				GenreItem genreItem = new GenreItem();
				genreItem.setName( defaultListModel.getElementAt(i));
				listGenreItem.add(genreItem);
		}
		
		return listGenreItem;
	}
	
	
}
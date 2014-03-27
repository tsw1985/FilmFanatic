package dad.filmfanatic.services;

import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import dad.filmfanatic.items.GenreItem;
import dad.filmfanatic.model.GenreModelItem;

public class GenreModelItemService {

	
 private Session session;
	 
	 public GenreModelItemService(Session session){
		 this.session = session;
		 
	 }
	 
	public boolean create(GenreModelItem genre) {
		
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
	
	
	public List<GenreModelItem> getGenreModelItemList(){
		
		Query query = session.createQuery("FROM GenreModelItem");
		List<GenreModelItem> listPersonModel = query.list();
		
		if ( listPersonModel.size() != 0 )
			return listPersonModel;
		else 
			return null;
	}
	
	
	private GenreModelItem getGenreModelItemByIdCloud(Long idCloud){
		
		Query query = session.createQuery("FROM GenreModelItem where idCloud=:id").setLong("id", idCloud);
		List<GenreModelItem> listGenreModelItem = query.list();
		
		if ( listGenreModelItem.size() != 0 )
			return listGenreModelItem.get(0);
		else 
			return null;
	}
	
	
	public GenreModelItem getGenreModelItemByName(String name){
		
		Query query = session.createQuery("FROM GenreModelItem where name=:name").setString("name", name);
		List<GenreModelItem> listGenreModelItem = query.list();
		
		if ( listGenreModelItem.size() != 0 && listGenreModelItem.get(0).getName().toLowerCase().equals(name.toLowerCase()) )
			return listGenreModelItem.get(0);
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
				}
				
				MovieDataBaseService.getGenreModelItemService().create(genreModel);
		}
		
	}
	
	
	

	public void createGenreModelItem( GenreItem genreItem ){
		
		GenreModelItem genreModel = getGenreModelItemByIdCloud( genreItem.getId() );
		
		if ( genreModel == null){
			
			genreModel = new GenreModelItem();
			genreModel.setName(genreItem.getName());
			create(genreModel);
		}
	}
	
	
	public List<GenreModelItem> getListGenreModelItem(List<GenreItem> listGenreItem){
		
		List<GenreModelItem> listGenreModelItem = new ArrayList<GenreModelItem>();
		
		for ( GenreItem genreItem : listGenreItem ){
			//GenreModelItem genreModel = getGenreModelItemByIdCloud(genreItem.getId() ) ;
			GenreModelItem genreModel = getGenreModelItemByName(genreItem.getName() ) ;
			
			if (genreModel == null){
				genreModel = new GenreModelItem();
				genreModel.setName(genreItem.getName());
			}
			
			listGenreModelItem.add(genreModel);
		}
		
		return listGenreModelItem;
		
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

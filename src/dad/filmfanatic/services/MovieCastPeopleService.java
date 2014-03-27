package dad.filmfanatic.services;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import org.hibernate.Session;

import dad.filmfanatic.items.PersonItem;
import dad.filmfanatic.model.MovieCastPeople;
import dad.filmfanatic.utils.ListTableModel;

public class MovieCastPeopleService {
	
	private Session session;
	
	public MovieCastPeopleService(Session session2){
		this.session = session2;
	}
	
	
	public MovieCastPeople get(Long id){
		return (MovieCastPeople)session.get(MovieCastPeople.class, id);
	}
	
	
	public void create (MovieCastPeople movieCastPeople){
		try{
			session.beginTransaction();
			session.save(movieCastPeople);
			session.getTransaction().commit();
		}catch(Exception ex){
			session.getTransaction().rollback();
		}
	}
	
	
	public List<PersonItem> getListPersonItemCrew(DefaultTableModel defaultTableModel){
		
		int rows = defaultTableModel.getRowCount();
		int columns = defaultTableModel.getColumnCount();
		List<PersonItem> listPersonItem = new ArrayList<PersonItem>();
		
		for (int i = 0; i < rows ; i++){
			for(int j= 0 ; j < columns ; j++){
				PersonItem personItem = new PersonItem();
				personItem.setName( defaultTableModel.getValueAt(i, j).toString() );
				personItem.setJob( defaultTableModel.getValueAt(i, j).toString() );
				listPersonItem.add(personItem);
			}
		}
		
		return listPersonItem;
	}
	
	
	public List<PersonItem> getListPersonItemCast(ListTableModel<PersonItem> defaultTableModel){
		
		List<PersonItem> listPersonItem = new ArrayList<PersonItem>();
		
		for (int i = 0; i < defaultTableModel.getValues().size() ; i++){
				PersonItem personItem = new PersonItem();
				personItem.setName( defaultTableModel.getValueAt(i, 0).toString() );
				personItem.setCharacter( defaultTableModel.getValueAt(i, 1 ).toString() );
				listPersonItem.add(personItem);
		}
		
		return listPersonItem;
	}
	
	
	
	/*
	public List<PersonItem> getListPersonItemCast(DefaultTableModel defaultTableModel){
		
		int rows = defaultTableModel.getRowCount();
		int columns = defaultTableModel.getColumnCount();
		List<PersonItem> listPersonItem = new ArrayList<PersonItem>();
		
		for (int i = 0; i < rows ; i++){
			for(int j= 0 ; j < columns ; j++){
				PersonItem personItem = new PersonItem();
				personItem.setName( defaultTableModel.getValueAt(i, j).toString() );
				personItem.setCharacter( defaultTableModel.getValueAt(i, j ).toString() );
				listPersonItem.add(personItem);
			}
		}
		
		return listPersonItem;
	}*/
	
	
	
}

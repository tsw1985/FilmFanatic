package dad.filmfanatic.services;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import dad.filmfanatic.items.PersonItem;
import dad.filmfanatic.model.MovieModel;
import dad.filmfanatic.model.PersonModel;

public class PersonModelService {

	
 private Session session;
	 
	 public PersonModelService(Session session){
		 this.session = session;
		 
	 }
	 
	private boolean create(PersonModel personModel) {
		
		try{
			session.beginTransaction();
			session.save(personModel);
			session.getTransaction().commit();
			return true;
		}
		catch (HibernateException e){
			session.getTransaction().rollback();
			return false;
		}
	}
	
	
	public void createPersonModel(PersonItem personItem ){
		
		PersonModel personModel = getPersonModelByName( personItem.getName() );

		if ( personModel == null ){
			
			personModel = new PersonModel();
			personModel.setName( personItem.getName());
			personModel.setIdClud( personItem.getId() );
			create( personModel );
		}
	
	}
	
	public void update (PersonItem personItem){
		//TODO
	}
	
	
	
	public PersonModel getPersonModelByIdCloud( Long id ){
		
		Query query = session.createQuery("FROM PersonModel WHERE idInCloud=:id").setLong("id", id);
		
		List<PersonModel> listPersonModel = query.list();
		
		if ( listPersonModel.size() != 0 )
			return listPersonModel.get(0);
		else 
			return null;
	}
	
	
	public PersonModel getPersonModelByName( String name ){
		
		String querySQL = "from PersonModel where name=:name";
		Query query = session.createQuery(querySQL).setString("name",name );
		
		List<PersonModel> listPersonModel = query.list();
		
		if ( listPersonModel.size() != 0 && listPersonModel.get(0).getName().toLowerCase().equals(name.toLowerCase()))
			return listPersonModel.get(0);
		else 
			return null;
	}
	
	public List<PersonModel> getAllPersonModelList(){
		
		String querySQL = "from PersonModel";
		Query query = session.createQuery(querySQL);
		
		List<PersonModel> listPersonModel = query.list();
		
		if ( listPersonModel.size() != 0 )
			return listPersonModel;
		else 
			return null;
	}
	
	
	public List<PersonModel> getListPersonModelForCast(List<PersonItem> listPersonItem , MovieModel movieModel){
		
		List<PersonModel> listPersonModel = new ArrayList<PersonModel>();
		
		for(PersonItem personItem : listPersonItem){
			
			PersonModel personModel = new PersonModel();
			personModel.setName( personItem.getName() );

			listPersonModel.add(personModel);
		}

		return listPersonModel;
		
	}
	
	
}

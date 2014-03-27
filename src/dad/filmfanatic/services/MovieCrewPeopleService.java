package dad.filmfanatic.services;

import org.hibernate.Session;

import dad.filmfanatic.model.MovieCrewPeople;

public class MovieCrewPeopleService {
	
	private Session session;
	
	public MovieCrewPeopleService(Session session2){
		this.session = session2;
	}
	
	
	public MovieCrewPeople get(Long id){
		return (MovieCrewPeople)session.get(MovieCrewPeople.class, id);
	}
	
	
	public void create (MovieCrewPeople movieCrewPeople){
		try{
		session.beginTransaction();
		session.save(movieCrewPeople);
		session.getTransaction().commit();
		}
		catch (Exception e){
			session.getTransaction().rollback();
		}
	}
}

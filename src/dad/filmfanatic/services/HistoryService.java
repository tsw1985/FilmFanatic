package dad.filmfanatic.services;

import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import dad.filmfanatic.model.HistoryModel;

 public class HistoryService{

	 private Session session;
	 
	 public HistoryService(Session session){
		 this.session = session;
		 
	 }
	 
	public boolean create(HistoryModel historyModel) {
		
		try{
			
			HistoryModel historyModelSaved = getHistoryTextByExactText(historyModel.getText());
			
			if ( historyModelSaved == null){
				session.beginTransaction();
				session.save(historyModel);
				session.getTransaction().commit();
				return true;
			}else{
				return false;
			}
			
		}
		catch (HibernateException e){
			return false;
		}
		
	}
	
	
	public boolean delete(HistoryModel historyModel) {
		
		try{
			session.beginTransaction();
			session.delete(historyModel);
			session.getTransaction().commit();
			return true;
		}
		catch (HibernateException e){
			return false;
		}
	}
	

	public HistoryModel get(Long id) {
		
		try{
			
			return (HistoryModel)session.get(HistoryModel.class, id );
		}
		catch (HibernateException e){
			return null;
		}
	}
	
	
	
	public List<HistoryModel> getAllHistory(){

		try{
			
			String querySQL = "from HistoryModel order by text";
			Query query = session.createQuery(querySQL);
			List<HistoryModel> list = query.list();
			return list;
		}
		catch (HibernateException e){
			return null;
		}
	}

	
	private HistoryModel getHistoryTextByExactText(String text){

		try{
			String querySQL = "from HistoryModel where text =:text";
			Query query = session.createQuery(querySQL).setString("text",text);
			List<HistoryModel> list = query.list();
			if ( list.size() > 0)
				return list.get(0);
			else
				return null;
		}
		catch (HibernateException e){
			return null;
		}
	}
	
	
	
	public List<HistoryModel> getHistoryTextByText(String text){

		try{
			
			String querySQL = "from HistoryModel where text like :text";
			Query query = session.createQuery(querySQL).setString("text", "%" + text + "%" );
			List<HistoryModel> list = query.list();
			return list;
		}
		catch (HibernateException e){
			return null;
		}
	}
	
}

package dad.filmfanatic.services;

import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import dad.filmfanatic.items.LanguageItem;
import dad.filmfanatic.model.LanguageModel;
import dad.filmfanatic.model.MovieModel;

public class SpokenLanguagesService {

	
 private Session session;
	 
	 public SpokenLanguagesService(Session session){
		 this.session = session;
		 
	 }
	 
	public boolean create(LanguageModel languageModel) {
		
		try{
			session.beginTransaction();
			session.save(languageModel);
			session.getTransaction().commit();
			return true;
		}
		catch (HibernateException e){
			return false;
		}
	}
	
	
	public List<LanguageModel> getListLanguajeModel( List<LanguageItem> listLanguageItem , MovieModel movieModel){
		
		List<LanguageModel> listLanguageModel = new ArrayList<LanguageModel>();
		
		for(LanguageItem personItem : listLanguageItem){
			
			LanguageModel languageModel = new LanguageModel();
			languageModel.setName( personItem.getName() );
			listLanguageModel.add(languageModel);
		}

		return listLanguageModel;
		
	}
	
	public List<LanguageItem> getListSpokenLanguagesItem(DefaultListModel<String> defaultListModel){
		
		int rows = defaultListModel.getSize();
		
		List<LanguageItem> listLanguageItem = new ArrayList<LanguageItem>();
		
		for (int i = 0; i < rows ; i++){
			LanguageItem languageItem = new LanguageItem();
			languageItem.setName( defaultListModel.getElementAt(i));
			listLanguageItem.add(languageItem);
		}
		
		return listLanguageItem;
	}
}

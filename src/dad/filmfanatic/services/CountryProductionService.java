package dad.filmfanatic.services;

import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;

import org.hibernate.Query;
import org.hibernate.Session;

import dad.filmfanatic.items.CountryItem;
import dad.filmfanatic.model.CountryProductionModel;

public class CountryProductionService {
	
	private Session session;
	
	public CountryProductionService(Session session2){
		this.session = session2;
	}
	
	
	public CountryProductionModel get(Long id){
		return (CountryProductionModel)session.get(CountryProductionModel.class, id);
	}
	
	
	private void create (CountryProductionModel country){
		
		session.beginTransaction();
		session.save(country);
		session.getTransaction().commit();
	}
	
	private void update (CountryProductionModel country){
	}
	
	
	
	
	private CountryProductionModel getCountryByISO(String iso){
		
		Query query = session.createQuery("FROM CountryProductionModel WHERE iso=:iso").setString("iso" , iso);
		
		List<CountryProductionModel> listCountryProductionModel = query.list();
		
		if ( listCountryProductionModel.size() != 0 )
			return listCountryProductionModel.get(0);
		else 
			return null;
	}
	
	
	
	public List<CountryProductionModel> getAllCountryProductionModel(){
		
		Query query = session.createQuery("FROM CountryProductionModel");
		
		List<CountryProductionModel> listCountryProductionModel = query.list();
		
		if ( listCountryProductionModel.size() != 0 )
			return listCountryProductionModel;
		else 
			return null;
	}
	
	
	
	public List<CountryProductionModel> getListCountryProductionModel(List<CountryItem> listCountry ){
		
		List<CountryProductionModel> listCountryProductionModel = new ArrayList<CountryProductionModel>();

		for ( CountryItem countryItem : listCountry ){
			
			CountryProductionModel countryProductionModel = getCountryByISO( countryItem.getIso() ); 
			
			if ( countryProductionModel == null){
				countryProductionModel = new CountryProductionModel();
				countryProductionModel.setIso( countryItem.getIso() );
				countryProductionModel.setName( countryItem.getName());
				create( countryProductionModel);
			}
			
			listCountryProductionModel.add(countryProductionModel);
		}
		
		return listCountryProductionModel; 
	}
	
	
	public List<CountryItem> getListCountryItem(DefaultListModel<String> defaultListModel){
		
		int rows = defaultListModel.getSize();
		List<CountryItem> listCountryItem = new ArrayList<CountryItem>();
		
		for (int i = 0; i < rows ; i++){
			CountryItem countryItem = new CountryItem();
			countryItem.setName( defaultListModel.getElementAt(i));
			listCountryItem.add(countryItem);
		}
		
		return listCountryItem;
	}
}
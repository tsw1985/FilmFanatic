package dad.filmfanatic.services;

import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;

import org.hibernate.Session;

import dad.filmfanatic.items.CompanyItem;
import dad.filmfanatic.model.CompanyModel;
import dad.filmfanatic.model.MovieModel;

public class ProductionCompaniesService {
	
	private Session session;
	
	public ProductionCompaniesService(Session session2){
		this.session = session2;
	}
	
	
	public CompanyModel get(Long id){
		return (CompanyModel)session.get(CompanyModel.class, id);
	}
	
	
	public void create (CompanyModel companyModel){
		
		session.beginTransaction();
		session.save(companyModel);
		session.getTransaction().commit();
	}
	

	public void update (CompanyModel companyModel){
		//TODO
	}
	
	
	
	public List<CompanyModel> getListCompanyModel(List<CompanyItem> listCompanyItem , MovieModel movieModel){
		
		List<CompanyModel> listCompanyModel = new ArrayList<CompanyModel>();

		for ( CompanyItem countryItem : listCompanyItem ){
			CompanyModel companyModel = new CompanyModel();
			companyModel.setName(countryItem.getName());
			listCompanyModel.add(companyModel);
		}
		
		return listCompanyModel; 
	}
	
	
	
	public List<CompanyItem> getListCompanyItem(DefaultListModel<String> defaultListModel){
		
		int rows = defaultListModel.getSize();
		List<CompanyItem> listCompanyItem = new ArrayList<CompanyItem>();
		
		for (int i = 0; i < rows ; i++){
			CompanyItem companyItem = new CompanyItem();
			companyItem.setName( defaultListModel.getElementAt(i));
			listCompanyItem.add(companyItem);
		}
		
		return listCompanyItem;
	}
}
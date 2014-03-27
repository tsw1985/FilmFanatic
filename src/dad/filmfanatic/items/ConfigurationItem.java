package dad.filmfanatic.items;

import java.util.ArrayList;
import java.util.List;

public class ConfigurationItem {
	private String baseUrl;
	private String secureBaseUrl;
	private List<String> posterSizes;
	private List<String> backdropSizes;
	private List<String> profileSizes;
	private List<String> logoSizes;

	public ConfigurationItem() {
		posterSizes = new ArrayList<String>();
		backdropSizes = new ArrayList<String>();
		profileSizes = new ArrayList<String>();
		logoSizes = new ArrayList<String>();
	}

	public String getBaseUrl() {
		return baseUrl;
	}

	public void setBaseUrl(String baseUrl) {
		this.baseUrl = baseUrl;
	}

	public String getSecureBaseUrl() {
		return secureBaseUrl;
	}

	public void setSecureBaseUrl(String secureBaseUrl) {
		this.secureBaseUrl = secureBaseUrl;
	}

	public List<String> getPosterSizes() {
		return posterSizes;
	}

	public List<String> getBackdropSizes() {
		return backdropSizes;
	}

	public List<String> getProfileSizes() {
		return profileSizes;
	}

	public List<String> getLogoSizes() {
		return logoSizes;
	}

}

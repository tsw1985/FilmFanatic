package dad.filmfanatic.items;

import java.util.ArrayList;
import java.util.List;

public class SearchResultItem {
	private Integer page;
	private Integer totalPages;
	private Integer totalResults;
	private List<MovieListItem> results = new ArrayList<MovieListItem>();

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(Integer totalPages) {
		this.totalPages = totalPages;
	}

	public Integer getTotalResults() {
		return totalResults;
	}

	public void setTotalResults(Integer totalResults) {
		this.totalResults = totalResults;
	}

	public List<MovieListItem> getResults() {
		return results;
	}

}

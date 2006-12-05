package de.berlios.quotations;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;

import de.berlios.quotations.db.Quotation;


public class QuotationFilter extends ViewerFilter {

	private Map<String, String> filters = new HashMap<String, String>();

	public boolean select(Viewer viewer, Object parentElement, Object element) {
		boolean filtrOk = true;
		String search = "";
		if (filters.containsKey("author")) {
			 search = ((Quotation) element).getAuthor();

			filtrOk = filtrOk && search != null
					&& (search.toUpperCase().indexOf(((String) filters.get("author")).toUpperCase()) >= 0);
		}
		
		if (filters.containsKey("chapter")) {
			search = ((Quotation) element).getChapter();

			filtrOk = filtrOk && search != null
					&& (search.toUpperCase().indexOf(((String) filters.get("chapter")).toUpperCase()) >= 0);
		}
		
		if (filters.containsKey("city")) {
			search = ((Quotation) element).getChapter();

			filtrOk = filtrOk && search != null
					&& (search.toUpperCase().indexOf(((String) filters.get("city")).toUpperCase()) >= 0);
		}
		
		if (filters.containsKey("keyword")) {
			search = ((Quotation) element).getKeyword();

			filtrOk = filtrOk && search != null
					&& (search.toUpperCase().indexOf(((String) filters.get("keyword")).toUpperCase()) >= 0);
		}
		
		if (filters.containsKey("print")) {
			search = ((Quotation) element).getPrint();

			filtrOk = filtrOk && search != null
					&& (search.toUpperCase().indexOf(((String) filters.get("print")).toUpperCase()) >= 0);
		}
		
		if (filters.containsKey("quotation")) {
			search = ((Quotation) element).getQuotation();

			filtrOk = filtrOk && search != null
					&& (search.toUpperCase().indexOf(((String) filters.get("quotation")).toUpperCase()) >= 0);
		}
		
		if (filters.containsKey("title")) {
			search = ((Quotation) element).getTitle();

			filtrOk = filtrOk && search != null
					&& (search.toUpperCase().indexOf(((String) filters.get("title")).toUpperCase()) >= 0);
		}
		
		if (filters.containsKey("year")) {
			Integer year = ((Quotation) element).getYear(); 
			search = year == null?null:year.toString();

			filtrOk = filtrOk && search != null
					&& (search.equals((String) filters.get("year")));
		}
		
		return filtrOk;
	}

	public void addLikeFilter(String columnName, String value) {
		filters.put(columnName, value);
	}
	
	public void removeLikeFilter(String columnName) {
		if (filters.containsKey(columnName))
			filters.remove(columnName);
	}
	
	public String getLikeFilter(String columnName) {
		if (filters.containsKey(columnName))
			return filters.get(columnName);
		else
			return "";
	}
}

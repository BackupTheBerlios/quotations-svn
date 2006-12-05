package de.berlios.quotations;

import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.swt.graphics.Image;

import de.berlios.quotations.db.Quotation;


public class QuotationLabelProvider implements ITableLabelProvider {

	public Image getColumnImage(Object element, int columnIndex) {
		return null;
	}

	public String getColumnText(Object element, int columnIndex) {
		String wynik = null;
		switch (columnIndex) {
		case 0:
			wynik = getColumnText(element, "author");
			break;
		case 1:
			wynik = getColumnText(element, "title");
			break;
		case 2:
			wynik = getColumnText(element, "print");
			break;
		case 3:
			wynik = getColumnText(element, "year");
			break;
		case 4:
			wynik = getColumnText(element, "city");
			break;
		case 5:
			wynik = getColumnText(element, "chapter");
			break;
		case 6:
			wynik = getColumnText(element, "page");
			break;
		case 7:
			wynik = getColumnText(element, "keyword");
			break;
		
		}
		return wynik;
	}

	public void addListener(ILabelProviderListener listener) {

	}

	public void dispose() {

	}

	public boolean isLabelProperty(Object element, String property) {
		return false;
	}

	public void removeListener(ILabelProviderListener listener) {

	}

	public String getColumnText(Object element, String columnName) {
		String wynik = "";

		if (columnName.toUpperCase().equals("ID"))
			wynik = ((Quotation) element).getId().toString();
		else if (columnName.toUpperCase().equals("AUTHOR"))
			wynik = ((Quotation) element).getAuthor();
		else if (columnName.toUpperCase().equals("TITLE"))
			wynik = ((Quotation) element).getTitle();
		else if (columnName.toUpperCase().equals("PRINT"))
			wynik = ((Quotation) element).getPrint();
		else if (columnName.toUpperCase().equals("YEAR")) {
			Integer year = ((Quotation) element).getYear();
			wynik = (year == null) ? null : year.toString();
		} else if (columnName.toUpperCase().equals("CITY"))
			wynik = ((Quotation) element).getCity();
		else if (columnName.toUpperCase().equals("CHAPTER"))
			wynik = ((Quotation) element).getChapter();
		else if (columnName.toUpperCase().equals("PAGE")) {
			Integer page = ((Quotation) element).getPage();
			wynik = (page == null) ? null : page.toString();
		} else if (columnName.toUpperCase().equals("KEYWORD"))
			wynik = ((Quotation) element).getKeyword();
		else if (columnName.toUpperCase().equals("QUOTATION"))
			wynik = ((Quotation) element).getQuotation();
		return wynik;
	}
}

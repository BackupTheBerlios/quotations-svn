package de.berlios.quotations;

import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import org.eclipse.swt.widgets.Text;

import de.berlios.quotations.db.Quotation;


public class QuotationObserver implements Observer {

	Object obj;

	public QuotationObserver(Object obj) {
		this.obj = obj;
	}

	public void update(Observable o, Object arg) {
		Quotation quotation = (Quotation) ((QuotationObservable) o).getData();
		String tmp = quotation.getPrint();
		((Text) ((Map) obj).get("print")).setText(tmp == null ? "" : tmp);

		tmp = quotation.getYear() == null ? "" : quotation.getYear().toString();
		((Text) ((Map) obj).get("year")).setText(tmp == null ? "" : tmp);

		tmp = quotation.getCity();
		((Text) ((Map) obj).get("city")).setText(tmp == null ? "" : tmp);

		tmp = quotation.getChapter();
		((Text) ((Map) obj).get("chapter")).setText(tmp == null ? "" : tmp);

		tmp = quotation.getPage() == null ? "" : quotation.getPage().toString();
		((Text) ((Map) obj).get("page")).setText(tmp == null ? "" : tmp);

		tmp = quotation.getKeyword();
		((Text) ((Map) obj).get("keywords")).setText(tmp == null ? "" : tmp);

		tmp = quotation.getQuotation();
		((Text) ((Map) obj).get("quotation")).setText(tmp == null ? "" : tmp);

	}
}
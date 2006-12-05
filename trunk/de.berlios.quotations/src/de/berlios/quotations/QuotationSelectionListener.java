package de.berlios.quotations;

import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Table;

public class QuotationSelectionListener implements Listener {
	private QuotationObservable observable;

	public QuotationSelectionListener(QuotationObservable observable) {
		this.observable = observable;
	}

	/**
	 * Po zaznaczeniu rekordu należy poinformować o tym wszystkich obserwatorów
	 */
	public void handleEvent(Event arg0) {
		Object selectedObject = ((Table) arg0.widget).getSelection()[0]
				.getData();
		if (observable != null)
			observable.set(selectedObject);

	}

}

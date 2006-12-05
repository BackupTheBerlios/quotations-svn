package de.berlios.quotations.actions;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;

import de.berlios.quotations.QuotationControl;
import de.berlios.quotations.QuotationFilter;
import de.berlios.quotations.QuotationFilterWindow;
import de.berlios.quotations.util.Messages;


public class SearchAction extends Action {

	private QuotationFilter quotationFilter;

	private QuotationControl myControl;

	public SearchAction(QuotationControl myControl,
			QuotationFilter quotationFilter) {
		super(Messages.getString("SearchAction.search")); //$NON-NLS-1$
		this.myControl = myControl;
		setToolTipText(Messages.getString("SearchAction.search_tooltip")); //$NON-NLS-1$
		setAccelerator(SWT.F5);
		this.quotationFilter = quotationFilter;
	}

	public void run() {
		QuotationFilterWindow editWindow = new QuotationFilterWindow(myControl
				.getShell(), quotationFilter);
		if (editWindow.open() == Window.OK) {
			myControl.refreshTable(true);
		}
		super.run();
	}

}

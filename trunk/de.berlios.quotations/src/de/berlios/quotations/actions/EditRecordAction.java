package de.berlios.quotations.actions;

import java.util.Observable;
import java.util.Observer;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;

import de.berlios.quotations.QuotationControl;
import de.berlios.quotations.QuotationEditWindow;
import de.berlios.quotations.QuotationObservable;
import de.berlios.quotations.db.Quotation;
import de.berlios.quotations.db.QuotationDB;
import de.berlios.quotations.util.Messages;


public class EditRecordAction extends Action implements Observer {

	private Quotation quotation = null;

	private QuotationDB quotationDB;

	private QuotationControl myControl;

	public EditRecordAction(QuotationControl myControl, QuotationDB quotationDB) {
		super(Messages.getString("EditRecordAction.Edit_record")); //$NON-NLS-1$
		this.myControl = myControl;
		setToolTipText(Messages.getString("EditRecordAction.Edit_tooltip")); //$NON-NLS-1$
		setAccelerator(SWT.F2);
		this.quotationDB = quotationDB;
	}

	public void run() {
		if (quotation != null) {
			QuotationEditWindow editWindow = new QuotationEditWindow(myControl
					.getShell(), quotation, quotationDB, Messages.getString("EditRecordAction.Edit_window_title")); //$NON-NLS-1$
			if (editWindow.open() == Window.OK) {
				myControl.refreshTable(true);
			}
		}
		super.run();
	}

	public void update(Observable o, Object arg) {
		quotation = (Quotation) ((QuotationObservable) o).getData();
		quotation = quotationDB.getById(quotation.getId());
	}

}

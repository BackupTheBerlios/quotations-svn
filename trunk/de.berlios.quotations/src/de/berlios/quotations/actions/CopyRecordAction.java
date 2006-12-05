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


public class CopyRecordAction extends Action implements Observer {

	private Quotation quotation = null;

	private QuotationDB quotationDB;

	private QuotationControl myControl;

	public CopyRecordAction(QuotationControl myControl, QuotationDB quotationDB) {
		super(Messages.getString("CopyRecordAction.Caption")); //$NON-NLS-1$
		this.myControl = myControl;
		setToolTipText(Messages.getString("CopyRecordAction.Tooltip")); //$NON-NLS-1$
		setAccelerator(SWT.F3);
		this.quotationDB = quotationDB;
	}

	public void run() {
		if (quotation.getId() != null) {
			QuotationEditWindow editWindow = new QuotationEditWindow(myControl
					.getShell(), quotation, quotationDB,
					Messages.getString("CopyRecordAction.WindowTitle")); //$NON-NLS-1$
			if (editWindow.open() == Window.OK) {
				myControl.refreshTable(true);
			}
		}
		super.run();
	}

	public void update(Observable o, Object arg) {
		quotation = (Quotation) ((QuotationObservable) o).getData();
	}

}

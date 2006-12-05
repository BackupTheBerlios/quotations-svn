package de.berlios.quotations.actions;

import java.util.Observable;
import java.util.Observer;

import org.eclipse.jface.action.Action;
import org.eclipse.swt.SWT;

import de.berlios.quotations.QuotationControl;
import de.berlios.quotations.QuotationObservable;
import de.berlios.quotations.db.Quotation;
import de.berlios.quotations.db.QuotationDB;
import de.berlios.quotations.util.MessageBoxes;
import de.berlios.quotations.util.Messages;


public class DeleteRecordAction extends Action implements Observer {

	private Quotation quotation = null;

	private QuotationDB quotationDB;

	private QuotationControl myControl;

	public DeleteRecordAction(QuotationControl myControl, QuotationDB quotationDB) {
		super(Messages.getString("DeleteRecordAction.Caption")); //$NON-NLS-1$
		this.myControl = myControl;
		setToolTipText(Messages.getString("DeleteRecordAction.Tooltip")); //$NON-NLS-1$
		setAccelerator(SWT.CTRL | SWT.DEL);
		this.quotationDB = quotationDB;
	}

	public void run() {
		if (quotation.getId() != null) {
			
			if (MessageBoxes.question(Messages.getString("DeleteRecordAction.Question")) == SWT.YES) { //$NON-NLS-1$
				quotationDB.deleteById(quotation.getId());
				myControl.refreshTable(false);
			}
		}
		super.run();
	}

	public void update(Observable o, Object arg) {
		quotation = (Quotation) ((QuotationObservable) o).getData();
	}

}

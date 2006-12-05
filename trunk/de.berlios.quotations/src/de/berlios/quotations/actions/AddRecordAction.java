package de.berlios.quotations.actions;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;

import de.berlios.quotations.QuotationControl;
import de.berlios.quotations.QuotationEditWindow;
import de.berlios.quotations.db.QuotationDB;
import de.berlios.quotations.util.Messages;


public class AddRecordAction extends Action {

	private QuotationDB quotationDB;

	private QuotationControl myControl;

	public AddRecordAction(QuotationControl myControl, QuotationDB quotationDB) {
		super(Messages.getString("AddRecordAction.Caption")); //$NON-NLS-1$
		this.myControl = myControl;
		setToolTipText(Messages.getString("AddRecordAction.Tooltip")); //$NON-NLS-1$
		setAccelerator(SWT.CTRL | SWT.INSERT);
		this.quotationDB = quotationDB;
	}

	public void run() {
		QuotationEditWindow editWindow = new QuotationEditWindow(myControl
				.getShell(), null, quotationDB, Messages.getString("AddRecordAction.WindowTitle")); //$NON-NLS-1$
		if (editWindow.open() == Window.OK) {
			myControl.refreshTable(false);
		}
		super.run();
	}

}

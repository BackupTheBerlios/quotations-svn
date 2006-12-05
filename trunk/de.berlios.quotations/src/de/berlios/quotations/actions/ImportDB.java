package de.berlios.quotations.actions;

import java.io.IOException;

import org.eclipse.jface.action.Action;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;

import de.berlios.quotations.QuotationControl;
import de.berlios.quotations.db.QuotationsImporter;
import de.berlios.quotations.util.MessageBoxes;
import de.berlios.quotations.util.Messages;


public class ImportDB extends Action {

	private QuotationControl myControl;

	public ImportDB(QuotationControl myControl) {
		super(Messages.getString("ImportDB.Caption")); //$NON-NLS-1$
		setToolTipText(Messages.getString("ImportDB.Tooltip")); //$NON-NLS-1$
		this.myControl = myControl;
	}

	public void run() {
		Shell shell = Display.getCurrent().getActiveShell();

		// pytam użytkownika, czy jest świadom skasowania wszystkich
		// dotychczasowych danych
		int ret = MessageBoxes.question(Messages.getString("ImportDB.Question"),SWT.YES | SWT.NO //$NON-NLS-1$
				| SWT.ICON_WARNING);
		if (ret == SWT.NO)
			return;

		// wybór pliku z bazą
		FileDialog fileDialog = new FileDialog(shell, SWT.OPEN);
		fileDialog.setText(Messages.getString("ImportDB.Select_import_file")); //$NON-NLS-1$
		fileDialog.setFilterExtensions(new String[] { "*.db", "*" }); //$NON-NLS-1$ //$NON-NLS-2$
		fileDialog.setFilterNames(new String[] { Messages.getString("ImportDB.Databases"), //$NON-NLS-1$
				Messages.getString("ImportDB.All_files") }); //$NON-NLS-1$
		String file = fileDialog.open();
		if (!file.equals("")) { //$NON-NLS-1$
			try {
				QuotationsImporter.importFromSqlite(file);
			} catch (IOException e) {
				MessageBoxes.error(e.getLocalizedMessage());
				return;
			}
		}

		MessageBoxes.info(Messages.getString("ImportDB.Successful")); //$NON-NLS-1$
		
		myControl.refreshTable(false);

		super.run();
	}

}

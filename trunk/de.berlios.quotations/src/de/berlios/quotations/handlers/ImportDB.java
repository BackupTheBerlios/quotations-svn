package de.berlios.quotations.handlers;

import java.io.IOException;

import org.eclipse.core.commands.AbstractHandlerWithState;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.State;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;

import de.berlios.quotations.application.Activator;
import de.berlios.quotations.db.QuotationsImporter;
import de.berlios.quotations.util.MessageBoxes;
import de.berlios.quotations.util.Messages;

public class ImportDB extends AbstractHandlerWithState {
    public void handleStateChange(State state, Object oldValue) {
        // TODO Automatycznie generowany szkielet metody

    }

    public Object execute(ExecutionEvent event) throws ExecutionException {
        Shell shell = Display.getCurrent().getActiveShell();

        // pytam użytkownika, czy jest świadom skasowania wszystkich
        // dotychczasowych danych
        int ret = MessageBoxes.question(
                Messages.getString("ImportDB.Question"), SWT.YES | SWT.NO //$NON-NLS-1$
                        | SWT.ICON_WARNING);
        if (ret == SWT.NO)
            return null;

        // wybór pliku z bazą
        FileDialog fileDialog = new FileDialog(shell, SWT.OPEN);
        fileDialog.setText(Messages.getString("ImportDB.Select_import_file")); //$NON-NLS-1$
        fileDialog.setFilterExtensions(new String[]{"*.db", "*"}); //$NON-NLS-1$ //$NON-NLS-2$
        fileDialog.setFilterNames(new String[]{
                Messages.getString("ImportDB.Databases"), //$NON-NLS-1$
                Messages.getString("ImportDB.All_files")}); //$NON-NLS-1$
        String file = fileDialog.open();
        if (!file.equals("")) { //$NON-NLS-1$
            try {
                QuotationsImporter.importFromSqlite(file);
            } catch (IOException e) {
                MessageBoxes.error(e.getLocalizedMessage());
                return null;
            }
        }

        MessageBoxes.info(Messages.getString("ImportDB.Successful")); //$NON-NLS-1$

        Activator.refresh();
        return null;
    }

}

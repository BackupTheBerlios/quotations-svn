package de.berlios.quotations.handlers;

import org.eclipse.core.commands.AbstractHandlerWithState;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.State;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.ui.PlatformUI;

import de.berlios.quotations.application.Activator;
import de.berlios.quotations.db.Quotation;
import de.berlios.quotations.util.MessageBoxes;
import de.berlios.quotations.util.Messages;

public class DeleteRecord extends AbstractHandlerWithState {
    public void handleStateChange(State state, Object oldValue) {
        // TODO Automatycznie generowany szkielet metody

    }

    public Object execute(ExecutionEvent event) throws ExecutionException {
        StructuredSelection mainViewSelection = (StructuredSelection) PlatformUI
                .getWorkbench().getActiveWorkbenchWindow()
                .getSelectionService().getSelection(
                        "de.berlios.quotations.ui.MainView");
        if (!mainViewSelection.isEmpty()) {
            Quotation quotation = (Quotation) mainViewSelection
                    .getFirstElement();
            if (quotation.getId() != null) {
                if (MessageBoxes.question(Messages
                        .getString("DeleteRecordAction.Question")) == SWT.YES) { //$NON-NLS-1$
                    Activator.getDB().deleteById(quotation.getId());
                    Activator.refresh();
                }
            }
        }
        return null;
    }

}

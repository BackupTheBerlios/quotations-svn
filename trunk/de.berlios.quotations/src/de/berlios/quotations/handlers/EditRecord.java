package de.berlios.quotations.handlers;

import org.eclipse.core.commands.AbstractHandlerWithState;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.State;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.window.Window;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.handlers.HandlerUtil;

import de.berlios.quotations.application.Activator;
import de.berlios.quotations.db.Quotation;
import de.berlios.quotations.ui.QuotationEditWindow;
import de.berlios.quotations.util.Messages;

public class EditRecord extends AbstractHandlerWithState {
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
            if (quotation != null) {
                QuotationEditWindow editWindow = new QuotationEditWindow(
                        HandlerUtil.getActiveShell(event),
                        quotation,
                        Activator.getDB(),
                        Messages
                                .getString("EditRecordAction.Edit_window_title"), false); //$NON-NLS-1$
                if (editWindow.open() == Window.OK) {
                    Activator.refresh();
                }
            }
        }
        return null;
    }

}

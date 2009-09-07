package de.berlios.quotations.handlers;

import org.eclipse.core.commands.AbstractHandlerWithState;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.State;
import org.eclipse.jface.window.Window;
import org.eclipse.ui.handlers.HandlerUtil;

import de.berlios.quotations.application.Activator;
import de.berlios.quotations.ui.QuotationEditWindow;
import de.berlios.quotations.util.Messages;

public class AddRecord extends AbstractHandlerWithState {
    public void handleStateChange(State state, Object oldValue) {
        // TODO Automatycznie generowany szkielet metody

    }

    public Object execute(ExecutionEvent event) throws ExecutionException {
        QuotationEditWindow editWindow = new QuotationEditWindow(HandlerUtil
                .getActiveShell(event), null, Activator.getDB(), Messages
                .getString("AddRecordAction.WindowTitle")); //$NON-NLS-1$
        if (editWindow.open() == Window.OK) {
            Activator.refresh();
        }
        return null;
    }

}

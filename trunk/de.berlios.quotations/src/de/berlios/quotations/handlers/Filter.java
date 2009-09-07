package de.berlios.quotations.handlers;

import org.eclipse.core.commands.AbstractHandlerWithState;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.State;
import org.eclipse.jface.window.Window;
import org.eclipse.ui.handlers.HandlerUtil;

import de.berlios.quotations.application.Activator;
import de.berlios.quotations.ui.QuotationFilterWindow;

public class Filter extends AbstractHandlerWithState {
    public void handleStateChange(State state, Object oldValue) {
        // TODO Automatycznie generowany szkielet metody

    }

    public Object execute(ExecutionEvent event) throws ExecutionException {
        QuotationFilterWindow editWindow = new QuotationFilterWindow(
                HandlerUtil.getActiveWorkbenchWindow(event).getShell(),
                Activator.getFilter());
        if (editWindow.open() == Window.OK) {
            Activator.refresh();
        }
        return null;
    }


}

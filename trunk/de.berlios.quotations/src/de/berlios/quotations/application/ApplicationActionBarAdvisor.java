/*
 * biz.safo.rcp.com.application.ApplicationActionBarAdvisor
 *
 * $Id$
 *
 * Utworzony dnia 2007-01-02 przez THaratynowicz
 *
 * Copyright Safo sp. z o. o. 2007
 */

package de.berlios.quotations.application;

import org.eclipse.jface.action.ICoolBarManager;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.actions.ActionFactory.IWorkbenchAction;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;

/**
 * Konfiguracja menu i paska narzędziowego aplikacji.
 * <p>
 * <b>Umożliwia:</b>
 * <ol>
 * <li>Konstrukcję menu aplikacji.</li>
 * <li>Konstrukcję paska narzędziowego aplikacji.</li>
 * <li>Deklarację i wnoszenie do menu i paska nzrzędziowego akcji globalnych.</li>
 * </ol>
 * </p>
 * 
 * @author THaratynowicz
 * @version $Id: ApplicationActionBarAdvisor.java 3545 2007-07-11 09:43:43 +0000
 *          (Śr, 11 lip 2007) pbernat $
 */
public class ApplicationActionBarAdvisor extends ActionBarAdvisor {


    /**
     * Akcja pokazania pomocy.
     */
    private IWorkbenchAction helpAction;

    /**
     * Wyszukiwarka w pomocy.
     */
    private IWorkbenchAction searchHelpAction;

    /**
     * Dynamiczna pomoc.
     */
    private IWorkbenchAction dynamicHelpAction;

    // /**
    // * Akcja pokazania dialogu z perspektywami i otwierania wybrenej w nowym
    // * oknie.
    // */
    // private IWorkbenchAction openPerspectiveDialogAction;

    /**
     * Akcja maksymalizacji widoku lub edytora.
     */
    private IWorkbenchAction maximizeAction;

    /**
     * Akcja minimalizacji widoku lub edytora.
     */
    private IWorkbenchAction minimizeAction;

    /**
     * Akcja aktywowania edytora.
     */
    private IWorkbenchAction activateEditorAction;

    /**
     * Akcja: następny edytor.
     */
    private IWorkbenchAction nextEditorAction;

    /**
     * Akcja: poprzedni edytor.
     */
    private IWorkbenchAction previousEditorAction;

    /**
     * Akcja: następny widok.
     */
    private IWorkbenchAction nextViewAction;

    /**
     * Akcja: poprzedni vidok.
     */
    private IWorkbenchAction previousViewAction;

    /**
     * The constructor.
     * 
     * @param configurer
     *            Object that implements IActionBarConfigurer
     * 
     */
    public ApplicationActionBarAdvisor(final IActionBarConfigurer configurer) {
        super(configurer);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void fillCoolBar(ICoolBarManager coolBar) {

    }

    /**
     * {@inheritDoc}
     */
    protected void fillMenuBar(IMenuManager menuBar) {
    }

    /**
     * {@inheritDoc}
     */
    protected void makeActions(final IWorkbenchWindow window) {


        ActionFactory.ABOUT.create(window);
        helpAction = ActionFactory.HELP_CONTENTS.create(window);
        register(helpAction);
        searchHelpAction = ActionFactory.HELP_SEARCH.create(window);
        register(searchHelpAction);
        dynamicHelpAction = ActionFactory.DYNAMIC_HELP.create(window);
        register(dynamicHelpAction);

        maximizeAction = ActionFactory.MAXIMIZE.create(window);
        register(maximizeAction);
        minimizeAction = ActionFactory.MINIMIZE.create(window);
        register(minimizeAction);

        activateEditorAction = ActionFactory.ACTIVATE_EDITOR.create(window);
        register(activateEditorAction);
        nextEditorAction = ActionFactory.NEXT_EDITOR.create(window);
        register(nextEditorAction);
        previousEditorAction = ActionFactory.PREVIOUS_EDITOR.create(window);
        register(previousEditorAction);
        nextViewAction = ActionFactory.NEXT_PART.create(window);
        register(nextViewAction);
        previousViewAction = ActionFactory.PREVIOUS_PART.create(window);
        register(previousViewAction);

        ActionFactory.QUIT.create(window);


    }
}

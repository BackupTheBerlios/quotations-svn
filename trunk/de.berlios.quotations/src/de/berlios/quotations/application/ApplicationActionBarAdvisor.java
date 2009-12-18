package de.berlios.quotations.application;

import org.eclipse.jface.action.ICoolBarManager;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;

/**
 * Konfiguracja menu i paska narzędziowego aplikacji.
 * 
 */
public class ApplicationActionBarAdvisor extends ActionBarAdvisor {

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
	}

}

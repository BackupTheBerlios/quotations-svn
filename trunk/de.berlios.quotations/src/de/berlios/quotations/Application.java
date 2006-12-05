package de.berlios.quotations;

import java.sql.SQLException;

import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;

import de.berlios.quotations.db.HibernateUtil;
import de.berlios.quotations.util.Messages;


public class Application extends ApplicationWindow {

	public Application() throws ClassNotFoundException, SQLException {
		super(null);
	}

	protected Control createContents(Composite parent) {
		getShell().setText(Messages.getString("Application.Quotations_title")); //$NON-NLS-1$
		try {
			(new QuotationControl(parent)).createContents();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return parent;
	}

	public static void main(String[] args) throws ClassNotFoundException,
			SQLException {
		Application wwin = new Application();
		wwin.setBlockOnOpen(true);
		wwin.open();
		Display.getCurrent().dispose();
		HibernateUtil.shutdown();
	}

}

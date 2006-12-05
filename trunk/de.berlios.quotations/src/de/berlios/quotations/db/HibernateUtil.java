package de.berlios.quotations.db;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import de.berlios.quotations.util.Debug;


public class HibernateUtil {

	private static final SessionFactory sessionFactory;

	private static String dbDirecotry = System.getProperty("user.home")
			+ System.getProperty("file.separator") + ".quotations"
			+ System.getProperty("file.separator") + "db";

	static {
		try {
			// create db folfer
			File file = new File(dbDirecotry);
			if (!file.exists())
				// zakładam katalog
				file.mkdir();

			// Create the SessionFactory from hibernate.cfg.xml
			String show_sql = "false";
			if (Debug.isEnabled("hibernate"))
				show_sql = "true";
			
			Configuration cfg = new Configuration().configure().setProperty(
					"hibernate.connection.url",
					"jdbc:hsqldb:file:" + dbDirecotry
							+ System.getProperty("file.separator")
							+ "cytaty.db").setProperty("hibernate.show_sql", show_sql);
			
			sessionFactory = cfg.buildSessionFactory();

		} catch (Throwable ex) {
			// Make sure you log the exception, as it might be swallowed
			System.err.println("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	/**
	 * db writes out to files and performs clean shuts down otherwise there will
	 * be an unclean shutdown when program ends
	 */
	public static void shutdown() {

		if (sessionFactory.getCurrentSession().isOpen()) {

			Statement st;
			Transaction tr = getSessionFactory().getCurrentSession()
					.getTransaction();
			if (!tr.isActive()) {
				tr.begin();
			}
			Connection connection = sessionFactory.getCurrentSession()
					.connection();
			try {
				st = connection.createStatement();
				st.execute("SHUTDOWN");
				connection.close(); // if there are no other open connection

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}

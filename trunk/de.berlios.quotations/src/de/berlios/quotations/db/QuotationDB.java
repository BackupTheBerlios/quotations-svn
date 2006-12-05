package de.berlios.quotations.db;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class QuotationDB {

	public static final int DB_TABLES_VERSION = 1;

	public QuotationDB() throws ClassNotFoundException, SQLException {

		Class.forName("org.hsqldb.jdbcDriver");
		create_tables();
	}

	private void create_tables() throws SQLException {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		DatabaseMetaData dbMData = session.connection().getMetaData();
		ResultSet rs = dbMData.getTables(null, null, "QUOTATIONS", null);

		if (!rs.next()) {
			Connection conn = HibernateUtil.getSessionFactory()
					.getCurrentSession().connection();
			String sql = "create table quotations (id INTEGER PRIMARY KEY, quotation varchar not null, chapter varchar, page integer, author varchar, title varchar, print varchar, year integer, city varchar, keyword varchar);";
			try {
				PreparedStatement st = conn.prepareStatement(sql);
				st.execute();
			} catch (SQLException e) {
				System.out.println(e.getLocalizedMessage());
			}
		}

		rs = dbMData.getTables(null, null, "CONFIG", null);

		if (!rs.next()) {
			Connection conn = session.connection();
			String sql = "create table config (id INTEGER PRIMARY KEY, key varchar, value varchar);";
			try {
				PreparedStatement st = conn.prepareStatement(sql);
				st.execute();
			} catch (SQLException e) {
				System.out.println(e.getLocalizedMessage());
			}
			sql = "create unique index config_key_i on config (key);";
			try {
				PreparedStatement st = conn.prepareStatement(sql);
				st.execute();
			} catch (SQLException e) {
				System.out.println(e.getLocalizedMessage());
			}

			Config config = new Config();
			config.setKey("tables_version");
			config.setValue("1");
			session.save(config);
		}

		transaction.commit();
		HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();
	}

	public Object[] getRows() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.getTransaction();
		if (!transaction.isActive())
			transaction.begin();

		List result = session.createQuery("from Quotation").list();
		Object[] objects = result.toArray();
		return objects;
	}

	public void save(Quotation quotation) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.getTransaction();
		if (transaction.isActive()) {
			session.save(quotation);
			transaction.commit();
			HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();
		}

	}
	
	public void deleteById(Integer id) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tr = session.getTransaction();
		if (!tr.isActive())
			HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();
		
		session.delete(session.load(Quotation.class, id));
		tr.commit();
		HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();
	}

	public Quotation getById(Integer id) {
		if (id == null)
			return null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tr = session.getTransaction();
		if (!tr.isActive())
			HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();
		Quotation q = (Quotation) session.load(Quotation.class, id);
		return q;
	}

}

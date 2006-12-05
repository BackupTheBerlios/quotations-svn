package de.berlios.quotations.db;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;
import org.hibernate.Session;
import org.hibernate.Transaction;

import de.berlios.quotations.util.MessageBoxes;
import de.berlios.quotations.util.Messages;


public class QuotationsImporter {

	/**
	 * Import data from exists sqlite database
	 * 
	 * @param dbFilePath
	 *            path to file that contains sqlite database
	 * @throws IOException
	 * 
	 */
	public static void importFromSqlite(String dbFilePath) throws IOException {
		String sqlitePath = "sqlite"; //$NON-NLS-1$
		Shell shell = null;
		if (Display.getCurrent() != null) {
			shell = Display.getCurrent().getActiveShell();
		}
		boolean isOk = false;
		do {
			try {
				Runtime.getRuntime().exec(sqlitePath + " -version"); //$NON-NLS-1$
				isOk = true;
			} catch (IOException e) {
				if (shell == null) {
					throw e;
				} else {
					if (MessageBoxes.question(Messages.getString("QuotationsImporter.NoSqlite"), SWT.YES | SWT.NO | SWT.ICON_ERROR) == SWT.YES) { //$NON-NLS-1$
						FileDialog fileDialog = new FileDialog(shell, SWT.OPEN);
						fileDialog.setText(Messages.getString("QuotationsImporter.SelectSqlite")); //$NON-NLS-1$
						sqlitePath = fileDialog.open();
					} else
						return;
				}
			}
		} while (!isOk);
		File file = new File(dbFilePath);
		if (!file.exists())
			throw new FileNotFoundException();

		// create temporary table

		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		Connection conn = session.connection();
		// check if table exists
		try {
			DatabaseMetaData dbMData = session.connection().getMetaData();
			ResultSet rs = dbMData.getTables(null, null, "IMP", null); //$NON-NLS-1$

			if (rs.next()) {
				String sql = "drop table imp;"; //$NON-NLS-1$
				PreparedStatement st = conn.prepareStatement(sql);
				st.execute();
			}
		} catch (SQLException e) {
			// TODO sensowna obsługa błędu
			System.out.println(e.getLocalizedMessage());
			return;
		}
		String sql = "create table imp (id INTEGER PRIMARY KEY, quotation varchar not null, chapter varchar, page varchar, author varchar, title varchar, print varchar, year integer, city varchar, keyword varchar);"; //$NON-NLS-1$
		try {
			PreparedStatement st = conn.prepareStatement(sql);
			st.execute();
		} catch (SQLException e) {
			// TODO sensowna obsługa błędu
			System.out.println(e.getLocalizedMessage());
			return;
		}

		String line;
		Process p = Runtime.getRuntime().exec(
				sqlitePath + " " + dbFilePath + " .dump"); //$NON-NLS-1$ //$NON-NLS-2$
		BufferedReader input = new BufferedReader(new InputStreamReader(p
				.getInputStream()));
		while ((line = input.readLine()) != null) {
			if (line.startsWith("INSERT")) { //$NON-NLS-1$
				while (line.indexOf(",'',") > -1) //$NON-NLS-1$
					line = line.replace(",'',", ",null,"); //$NON-NLS-1$ //$NON-NLS-2$

				line = line
						.replace("INSERT INTO quotations", "INSERT INTO imp"); //$NON-NLS-1$ //$NON-NLS-2$
				try {
					String sqlLine = new String(line.getBytes(), "UTF-8"); //$NON-NLS-1$
					PreparedStatement st = conn.prepareStatement(sqlLine);
					st.execute();
				} catch (SQLException e) {
					// TODO sensowna obsługa błędu
					System.out.println(e.getLocalizedMessage());
				}
			}

		}
		input.close();
		String errorMsg = ""; //$NON-NLS-1$
		BufferedReader error = new BufferedReader(new InputStreamReader(p
				.getErrorStream()));
		while ((line = error.readLine()) != null) {
			errorMsg += " " + line; //$NON-NLS-1$
		}
		error.close();
		if (!errorMsg.equals("")){ //$NON-NLS-1$
			try {
				PreparedStatement st = conn.prepareStatement("drop table imp"); //$NON-NLS-1$
				st.execute();
			} catch (SQLException e) {
				//olewamy błąd
			}
			throw new IOException(errorMsg);
		}
		// tu mamy już dane w tabeli pomocniczej
		try {
			PreparedStatement st = conn
					.prepareStatement("update imp set page = substr(page,1,POSITION('-' " //$NON-NLS-1$
							+ " in page) - 1) where page is not null and POSITION('-' " //$NON-NLS-1$
							+ " in page) > 0 ;"); //$NON-NLS-1$
			st.execute();
		} catch (SQLException e) {
			// TODO sensowna obsługa błędu
			System.out.println(e.getLocalizedMessage());
		}

		try {
			PreparedStatement st = conn
					.prepareStatement("alter table imp alter column page integer;"); //$NON-NLS-1$
			st.execute();

			st = conn.prepareStatement("delete from quotations;"); //$NON-NLS-1$
			st.execute();
			
			st = conn
					.prepareStatement("insert into quotations select * from imp;"); //$NON-NLS-1$
			st.execute();
		} catch (SQLException e) {
			// TODO sensowna obsługa błędu
			System.out.println(e.getLocalizedMessage());
		}

		// drop tabeli pomocniczej
		try {
			PreparedStatement st = conn.prepareStatement("drop table imp;"); //$NON-NLS-1$
			st.execute();
		} catch (SQLException e) {
			// TODO sensowna obsługa błędu
			System.out.println(e.getLocalizedMessage());
		}

		transaction.commit();

	}
}

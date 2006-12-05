package de.berlios.quotations;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;

import de.berlios.quotations.actions.AddRecordAction;
import de.berlios.quotations.actions.CopyRecordAction;
import de.berlios.quotations.actions.DeleteRecordAction;
import de.berlios.quotations.actions.EditRecordAction;
import de.berlios.quotations.actions.ImportDB;
import de.berlios.quotations.actions.SearchAction;
import de.berlios.quotations.db.Quotation;
import de.berlios.quotations.db.QuotationDB;
import de.berlios.quotations.util.Messages;


/**
 * Kontrolka rysująca i obsługująca cytaty
 * 
 * @author pkontek
 * 
 */
public class QuotationControl {

	private QuotationDB db;

	private TableViewer tableViewer;

	private AddRecordAction addRecordAction;

	private EditRecordAction editRecordAction;

	private CopyRecordAction copyRecordAction;

	private DeleteRecordAction deleteRecordAction;
	private SearchAction searchAction;

	private QuotationObservable observable;

	private Composite parent;

	private Map<String, Text> fieldsMap = new HashMap<String, Text>();

	private QuotationFilter quotationFilter = new QuotationFilter();

	public QuotationControl(Composite parent) throws ClassNotFoundException,
			SQLException {
		this.parent = parent;
		db = new QuotationDB();
		parent.setSize(800, 600);
	}

	protected Control createContents() {

		getShell().setText(Messages.getString("QuotationControl.Quotations")); //$NON-NLS-1$
		Composite comp = new Composite(parent, SWT.NONE);
		comp.setLayout(new FillLayout(SWT.VERTICAL));

		// tabelka
		tableViewer = new TableViewer(comp, SWT.SINGLE | SWT.FULL_SELECTION
				| SWT.BORDER);
		tableViewer.addFilter(quotationFilter);
		Table table = tableViewer.getTable();
		TableColumn tcAuthor = new TableColumn(table, SWT.NONE);
		tcAuthor.setText(Messages.getString("QuotationControl.Author")); //$NON-NLS-1$
		tcAuthor.setWidth(220);
		TableColumn tcTitle = new TableColumn(table, SWT.NONE);
		tcTitle.setText(Messages.getString("QuotationControl.Title")); //$NON-NLS-1$
		tcTitle.setWidth(550);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);

		// pola pod tabelką
		Composite fieldsComposite = new Composite(comp, SWT.NONE);
		fieldsComposite.setLayout(new GridLayout(4, false));

		GridData fieldsLayoutData = new GridData(SWT.FILL, SWT.BEGINNING, true,
				false);

		Label printLabel = new Label(fieldsComposite, SWT.NONE);
		printLabel.setText(Messages.getString("QuotationControl.Print")); //$NON-NLS-1$
		Text printText = new Text(fieldsComposite, SWT.BORDER | SWT.READ_ONLY);
		printText.setLayoutData(fieldsLayoutData);
		fieldsMap.put("print", printText); //$NON-NLS-1$

		Label yearLabel = new Label(fieldsComposite, SWT.NONE);
		yearLabel.setText(Messages.getString("QuotationControl.Year")); //$NON-NLS-1$
		Text yearText = new Text(fieldsComposite, SWT.BORDER | SWT.READ_ONLY);
		yearText.setLayoutData(fieldsLayoutData);
		fieldsMap.put("year", yearText); //$NON-NLS-1$

		Label cityLabel = new Label(fieldsComposite, SWT.NONE);
		cityLabel.setText(Messages.getString("QuotationControl.City")); //$NON-NLS-1$
		Text cityText = new Text(fieldsComposite, SWT.BORDER | SWT.READ_ONLY);
		cityText.setLayoutData(fieldsLayoutData);
		fieldsMap.put("city", cityText); //$NON-NLS-1$

		Label chapterLabel = new Label(fieldsComposite, SWT.NONE);
		chapterLabel.setText(Messages.getString("QuotationControl.Chapter")); //$NON-NLS-1$
		Text chapterText = new Text(fieldsComposite, SWT.BORDER | SWT.READ_ONLY);
		chapterText.setLayoutData(fieldsLayoutData);
		fieldsMap.put("chapter", chapterText); //$NON-NLS-1$

		Label pageLabel = new Label(fieldsComposite, SWT.NONE);
		pageLabel.setText(Messages.getString("QuotationControl.Page")); //$NON-NLS-1$
		Text pageText = new Text(fieldsComposite, SWT.BORDER | SWT.READ_ONLY);
		pageText.setLayoutData(fieldsLayoutData);
		fieldsMap.put("page", pageText); //$NON-NLS-1$

		Label keywordsLabel = new Label(fieldsComposite, SWT.NONE);
		keywordsLabel.setText(Messages.getString("QuotationControl.Keywords")); //$NON-NLS-1$
		Text keywordsText = new Text(fieldsComposite, SWT.BORDER | SWT.READ_ONLY);
		keywordsText.setLayoutData(fieldsLayoutData);
		fieldsMap.put("keywords", keywordsText); //$NON-NLS-1$

		Label quotationLabel = new Label(fieldsComposite, SWT.NONE);
		quotationLabel.setText(Messages.getString("QuotationControl.Quotation")); //$NON-NLS-1$
		Text quotationText = new Text(fieldsComposite, SWT.MULTI | SWT.WRAP
				| SWT.V_SCROLL | SWT.BORDER | SWT.READ_ONLY);
		GridData quotationLayoutData = new GridData(SWT.FILL, SWT.FILL, true,
				true);
		quotationLayoutData.horizontalSpan = 3;
		quotationText.setLayoutData(quotationLayoutData);
		fieldsMap.put("quotation", quotationText); //$NON-NLS-1$

		// stworzenie akcji
		addRecordAction = new AddRecordAction(this, db);
		editRecordAction = new EditRecordAction(this, db);
		copyRecordAction = new CopyRecordAction(this, db);
		deleteRecordAction = new DeleteRecordAction(this, db);
		searchAction = new SearchAction(this, quotationFilter);

		// dodanie obserwatorów
		observable = new QuotationObservable();
		table.addListener(SWT.Selection, new QuotationSelectionListener(
				observable));
		observable.addObserver(new QuotationObserver(fieldsMap));

		observable.addObserver(editRecordAction);
		observable.addObserver(copyRecordAction);
		observable.addObserver(deleteRecordAction);

		createMenu();

		// napełnienie tabeli
		tableViewer.setLabelProvider(new QuotationLabelProvider());
		tableViewer.setContentProvider(new QuotationContentProvider());
		refreshTable(false);

		return parent;
	}

	public void refreshTable(boolean reselectCurrentRecord) {
		try {
			Table table = tableViewer.getTable();
			TableItem[] selection = table.getSelection();
			tableViewer.setInput(db.getRows());
			observable.set(new Quotation());
			if (reselectCurrentRecord) {
				table.setSelection(selection);
				if (selection.length > 0)
					observable.set(selection[0].getData());
			} else if (table.getItemCount() > 0) {
				table.setSelection(0);
				observable.set(table.getItem(0).getData());
			}
		} catch (Exception e) {
			// TODO some message about this error
			e.printStackTrace();
		}
	}

	private void createMenu() {
		IAction importDBAction = new ImportDB(this);

		Menu menuBar = getShell().getMenuBar();
		if (menuBar == null) {
			menuBar = new Menu(getShell(), SWT.BAR);
			getShell().setMenuBar(menuBar);
		}
		MenuManager edycja = new MenuManager(Messages.getString("QuotationControl.Menu_Edit")); //$NON-NLS-1$
		edycja.add(addRecordAction);
		edycja.add(editRecordAction);
		edycja.add(copyRecordAction);
		edycja.add(deleteRecordAction);
		edycja.fill(menuBar, -1);
		MenuManager search = new MenuManager(Messages.getString("QuotationControl.Menu_Search")); //$NON-NLS-1$
		search.add(searchAction);
		search.fill(menuBar, -1);
		MenuManager inne = new MenuManager(Messages.getString("QuotationControl.Menu_Others")); //$NON-NLS-1$
		inne.add(importDBAction);
		inne.fill(menuBar, -1);

	}

	public Shell getShell() {
		return parent.getShell();
	}


}

package de.berlios.quotations.ui;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import de.berlios.quotations.db.Quotation;
import de.berlios.quotations.db.QuotationDB;
import de.berlios.quotations.util.Messages;


public class QuotationEditWindow extends Dialog {

	private Quotation quotation;

	private QuotationDB quotationDB;

	private Text authorField;

	private Text titleField;

	private Text printField;

	private Text yearField;

	private Text cityField;

	private Text chapterField;

	private Text pageField;

	private Text keywordField;

	private Text quotationField;

	private String title;

	public QuotationEditWindow(Shell parentShell, Quotation quotation,
			QuotationDB quotationDB, String title) {
		super(parentShell);
		this.quotation = quotation;
		this.quotationDB = quotationDB;
		this.title = title;
	}

	protected Control createContents(Composite parent) {
		getShell().setText(title == null ? "" : title); //$NON-NLS-1$
		Composite topComposite = new Composite(parent, SWT.NONE);
		topComposite.setLayout(new GridLayout(4, false));
		topComposite
				.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

		// author
		Label authorLabel = new Label(topComposite, SWT.NONE);
		authorLabel.setText(Messages.getString("QuotationEditWindow.author")); //$NON-NLS-1$
		authorField = new Text(topComposite, SWT.BORDER);
		authorField.setLayoutData(new GridData(SWT.FILL, SWT.BEGINNING, true,
				false));
		// title
		Label titleLabel = new Label(topComposite, SWT.NONE);
		titleLabel.setText(Messages.getString("QuotationEditWindow.title")); //$NON-NLS-1$
		titleField = new Text(topComposite, SWT.BORDER);
		titleField.setLayoutData(new GridData(SWT.FILL, SWT.BEGINNING, true,
				false));
		// print
		Label printLabel = new Label(topComposite, SWT.NONE);
		printLabel.setText(Messages.getString("QuotationEditWindow.print")); //$NON-NLS-1$
		printField = new Text(topComposite, SWT.BORDER);
		printField.setLayoutData(new GridData(SWT.FILL, SWT.BEGINNING, true,
				false));
		// year
		Label yearLabel = new Label(topComposite, SWT.NONE);
		yearLabel.setText(Messages.getString("QuotationEditWindow.year")); //$NON-NLS-1$
		yearField = new Text(topComposite, SWT.BORDER);
		yearField.setLayoutData(new GridData(SWT.FILL, SWT.BEGINNING, true,
				false));
		// city
		Label cityLabel = new Label(topComposite, SWT.NONE);
		cityLabel.setText(Messages.getString("QuotationEditWindow.city")); //$NON-NLS-1$
		cityField = new Text(topComposite, SWT.BORDER);
		cityField.setLayoutData(new GridData(SWT.FILL, SWT.BEGINNING, true,
				false));
		// chapter
		Label chapterLabel = new Label(topComposite, SWT.NONE);
		chapterLabel.setText(Messages.getString("QuotationEditWindow.chapter")); //$NON-NLS-1$
		chapterField = new Text(topComposite, SWT.BORDER);
		chapterField.setLayoutData(new GridData(SWT.FILL, SWT.BEGINNING, true,
				false));
		// page
		Label pageLabel = new Label(topComposite, SWT.NONE);
		pageLabel.setText(Messages.getString("QuotationEditWindow.page")); //$NON-NLS-1$
		pageField = new Text(topComposite, SWT.BORDER);
		pageField.setLayoutData(new GridData(SWT.FILL, SWT.BEGINNING, true,
				false));
		// keyword
		Label keywordLabel = new Label(topComposite, SWT.NONE);
		keywordLabel.setText(Messages.getString("QuotationEditWindow.keyword")); //$NON-NLS-1$
		keywordField = new Text(topComposite, SWT.BORDER);
		keywordField.setLayoutData(new GridData(SWT.FILL, SWT.BEGINNING, true,
				false));
		// quotation
		Label quotationLabel = new Label(topComposite, SWT.NONE);
		quotationLabel.setText(Messages.getString("QuotationEditWindow.quotation")); //$NON-NLS-1$
		quotationField = new Text(topComposite, SWT.MULTI | SWT.WRAP
				| SWT.V_SCROLL | SWT.BORDER);
		GridData quotationGridData = new GridData(SWT.FILL, SWT.FILL, true,
				true);
		quotationGridData.widthHint = 500;
		quotationGridData.heightHint = 300;
		quotationGridData.horizontalSpan = 3;
		quotationField.setLayoutData(quotationGridData);
		if (quotation != null) {
			authorField.setText(quotation.getAuthor() == null ? "" : quotation //$NON-NLS-1$
					.getAuthor());
			titleField.setText(quotation.getTitle() == null ? "" : quotation //$NON-NLS-1$
					.getTitle());
			printField.setText(quotation.getPrint() == null ? "" : quotation //$NON-NLS-1$
					.getPrint());
			yearField.setText(quotation.getYear() == null ? "" : quotation //$NON-NLS-1$
					.getYear().toString());
			cityField.setText(quotation.getCity() == null ? "" : quotation //$NON-NLS-1$
					.getCity());
			chapterField.setText(quotation.getChapter() == null ? "" //$NON-NLS-1$
					: quotation.getChapter());
			pageField.setText(quotation.getPage() == null ? "" : quotation //$NON-NLS-1$
					.getPage().toString());
			keywordField.setText(quotation.getKeyword() == null ? "" //$NON-NLS-1$
					: quotation.getKeyword());
			quotationField.setText(quotation.getQuotation() == null ? "" //$NON-NLS-1$
					: quotation.getQuotation());
		}

		return super.createContents(parent);
	}

	protected void okPressed() {
		if (quotation == null) {
			quotation = new Quotation();
		}
		quotation.setAuthor(authorField.getText());
		quotation.setChapter(chapterField.getText());
		quotation.setCity(cityField.getText());
		quotation.setKeyword(keywordField.getText());
		if (pageField.getText().equals("")) //$NON-NLS-1$
			quotation.setPage(null);
		else
			quotation.setPage(new Integer(pageField.getText()));
		quotation.setPrint(printField.getText());
		quotation.setQuotation(quotationField.getText());
		quotation.setTitle(titleField.getText());
		if (yearField.getText().equals("")) //$NON-NLS-1$
			quotation.setYear(null);
		else
			quotation.setYear(new Integer(yearField.getText()));
		quotationDB.save(quotation);
		super.okPressed();
	}

}

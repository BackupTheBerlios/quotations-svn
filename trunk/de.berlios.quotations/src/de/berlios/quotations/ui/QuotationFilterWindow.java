package de.berlios.quotations.ui;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import de.berlios.quotations.util.Messages;


public class QuotationFilterWindow extends Dialog {

	private Text authorField;

	private Text titleField;

	private Text printField;

	private Text yearField;

	private Text cityField;

	private Text chapterField;

	private Text pageField;

	private Text keywordField;

	private Text quotationField;
	
	private QuotationFilter quotationFilter;
	
	public static final int CLEAR_ID = IDialogConstants.NO_TO_ALL_ID + 1;

	public QuotationFilterWindow(Shell parentShell, QuotationFilter quotationFilter) {
		super(parentShell);
		this.quotationFilter = quotationFilter; 
	}

	protected Control createContents(Composite parent) {
		getShell().setText(Messages.getString("QuotationFilterWindow.Search_quotation")); //$NON-NLS-1$
		Composite topComposite = new Composite(parent, SWT.NONE);
		topComposite.setLayout(new GridLayout(4, false));
		topComposite
				.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

		// author
		Label authorLabel = new Label(topComposite, SWT.NONE);
		authorLabel.setText(Messages.getString("QuotationFilterWindow.author")); //$NON-NLS-1$
		authorField = new Text(topComposite, SWT.BORDER);
		authorField.setLayoutData(new GridData(SWT.FILL, SWT.BEGINNING, true,
				false));
		authorField.setText(quotationFilter.getLikeFilter("author")); //$NON-NLS-1$
		
		// title
		Label titleLabel = new Label(topComposite, SWT.NONE);
		titleLabel.setText(Messages.getString("QuotationFilterWindow.title")); //$NON-NLS-1$
		titleField = new Text(topComposite, SWT.BORDER);
		titleField.setLayoutData(new GridData(SWT.FILL, SWT.BEGINNING, true,
				false));
		titleField.setText(quotationFilter.getLikeFilter("title")); //$NON-NLS-1$
		
		// print
		Label printLabel = new Label(topComposite, SWT.NONE);
		printLabel.setText(Messages.getString("QuotationFilterWindow.print")); //$NON-NLS-1$
		printField = new Text(topComposite, SWT.BORDER);
		printField.setLayoutData(new GridData(SWT.FILL, SWT.BEGINNING, true,
				false));
		printField.setText(quotationFilter.getLikeFilter("print")); //$NON-NLS-1$
		
		// year
		Label yearLabel = new Label(topComposite, SWT.NONE);
		yearLabel.setText(Messages.getString("QuotationFilterWindow.year")); //$NON-NLS-1$
		yearField = new Text(topComposite, SWT.BORDER);
		yearField.setLayoutData(new GridData(SWT.FILL, SWT.BEGINNING, true,
				false));
		yearField.setText(quotationFilter.getLikeFilter("year")); //$NON-NLS-1$
		
		// city
		Label cityLabel = new Label(topComposite, SWT.NONE);
		cityLabel.setText(Messages.getString("QuotationFilterWindow.city")); //$NON-NLS-1$
		cityField = new Text(topComposite, SWT.BORDER);
		cityField.setLayoutData(new GridData(SWT.FILL, SWT.BEGINNING, true,
				false));
		cityField.setText(quotationFilter.getLikeFilter("city")); //$NON-NLS-1$
		
		// chapter
		Label chapterLabel = new Label(topComposite, SWT.NONE);
		chapterLabel.setText(Messages.getString("QuotationFilterWindow.chapter")); //$NON-NLS-1$
		chapterField = new Text(topComposite, SWT.BORDER);
		chapterField.setLayoutData(new GridData(SWT.FILL, SWT.BEGINNING, true,
				false));
		chapterField.setText(quotationFilter.getLikeFilter("chapter")); //$NON-NLS-1$
		
		// page
		Label pageLabel = new Label(topComposite, SWT.NONE);
		pageLabel.setText(Messages.getString("QuotationFilterWindow.page")); //$NON-NLS-1$
		pageField = new Text(topComposite, SWT.BORDER);
		pageField.setLayoutData(new GridData(SWT.FILL, SWT.BEGINNING, true,
				false));
		// keyword
		Label keywordLabel = new Label(topComposite, SWT.NONE);
		keywordLabel.setText(Messages.getString("QuotationFilterWindow.keyword")); //$NON-NLS-1$
		keywordField = new Text(topComposite, SWT.BORDER);
		keywordField.setLayoutData(new GridData(SWT.FILL, SWT.BEGINNING, true,
				false));
		keywordField.setText(quotationFilter.getLikeFilter("keyword")); //$NON-NLS-1$
		// quotation
		Label quotationLabel = new Label(topComposite, SWT.NONE);
		quotationLabel.setText(Messages.getString("QuotationFilterWindow.quotation")); //$NON-NLS-1$
		quotationField = new Text(topComposite, SWT.BORDER);
		GridData quotationGridData = new GridData(SWT.FILL, SWT.FILL, true,
				true);
		quotationGridData.horizontalSpan = 3;
		quotationField.setLayoutData(quotationGridData);
		quotationField.setText(quotationFilter.getLikeFilter("quotation")); //$NON-NLS-1$
		
		
		return super.createContents(parent);
	}

	protected void okPressed() {
		
		if (authorField.getText() == null || authorField.getText().equals("")) //$NON-NLS-1$
			quotationFilter.removeLikeFilter("author"); //$NON-NLS-1$
		else
			quotationFilter.addLikeFilter("author",authorField.getText()); //$NON-NLS-1$
		if (titleField.getText() == null || titleField.getText().equals("")) //$NON-NLS-1$
			quotationFilter.removeLikeFilter("title"); //$NON-NLS-1$
		else
			quotationFilter.addLikeFilter("title",titleField.getText()); //$NON-NLS-1$
		
		if (printField.getText() == null || printField.getText().equals("")) //$NON-NLS-1$
			quotationFilter.removeLikeFilter("print"); //$NON-NLS-1$
		else
			quotationFilter.addLikeFilter("print",printField.getText()); //$NON-NLS-1$
		
		if (yearField.getText() == null || yearField.getText().equals("")) //$NON-NLS-1$
			quotationFilter.removeLikeFilter("year"); //$NON-NLS-1$
		else
			quotationFilter.addLikeFilter("year",yearField.getText()); //$NON-NLS-1$
		
		if (cityField.getText() == null || cityField.getText().equals("")) //$NON-NLS-1$
			quotationFilter.removeLikeFilter("city"); //$NON-NLS-1$
		else
			quotationFilter.addLikeFilter("city",cityField.getText()); //$NON-NLS-1$
		
		if (chapterField.getText() == null || chapterField.getText().equals("")) //$NON-NLS-1$
			quotationFilter.removeLikeFilter("chapter"); //$NON-NLS-1$
		else
			quotationFilter.addLikeFilter("chapter",chapterField.getText()); //$NON-NLS-1$
		
		if (keywordField.getText() == null || keywordField.getText().equals("")) //$NON-NLS-1$
			quotationFilter.removeLikeFilter("keyword"); //$NON-NLS-1$
		else
			quotationFilter.addLikeFilter("keyword",keywordField.getText()); //$NON-NLS-1$
		
		if (quotationField.getText() == null || quotationField.getText().equals("")) //$NON-NLS-1$
			quotationFilter.removeLikeFilter("quotation"); //$NON-NLS-1$
		else
			quotationFilter.addLikeFilter("quotation",quotationField.getText()); //$NON-NLS-1$
		
		super.okPressed();
	}

	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		super.createButtonsForButtonBar(parent);
		createButton(parent, CLEAR_ID, Messages.getString("QuotationFilterWindow.Clear_button"), false); //$NON-NLS-1$
		
	}

	@Override
	protected void buttonPressed(int buttonId) {
		if (buttonId == CLEAR_ID) {
			authorField.setText(""); //$NON-NLS-1$
			titleField.setText(""); //$NON-NLS-1$
			printField.setText(""); //$NON-NLS-1$
			yearField.setText(""); //$NON-NLS-1$
			cityField.setText(""); //$NON-NLS-1$
			chapterField.setText(""); //$NON-NLS-1$
			keywordField.setText(""); //$NON-NLS-1$
			quotationField.setText(""); //$NON-NLS-1$
		}
		super.buttonPressed(buttonId);
	}

}

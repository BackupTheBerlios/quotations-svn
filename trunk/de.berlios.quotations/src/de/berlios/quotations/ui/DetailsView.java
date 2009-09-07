package de.berlios.quotations.ui;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ViewPart;

import de.berlios.quotations.db.Quotation;
import de.berlios.quotations.util.Messages;

public class DetailsView extends ViewPart {
    public static final String ID = "de.berlios.quotations.ui.DetailsView";

    private Map<String, Text> fieldsMap = new HashMap<String, Text>();

    /**
     * This is a callback that will allow us to create the viewer and initialize
     * it.
     */
    public void createPartControl(Composite parent) {

        Composite fieldsComposite = new Composite(parent, SWT.NONE);
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
        Text keywordsText = new Text(fieldsComposite, SWT.BORDER
                | SWT.READ_ONLY);
        keywordsText.setLayoutData(fieldsLayoutData);
        fieldsMap.put("keywords", keywordsText); //$NON-NLS-1$

        Label quotationLabel = new Label(fieldsComposite, SWT.NONE);
        quotationLabel
                .setText(Messages.getString("QuotationControl.Quotation")); //$NON-NLS-1$
        Text quotationText = new Text(fieldsComposite, SWT.MULTI | SWT.WRAP
                | SWT.V_SCROLL | SWT.BORDER | SWT.READ_ONLY);
        GridData quotationLayoutData = new GridData(SWT.FILL, SWT.FILL, true,
                true);
        quotationLayoutData.horizontalSpan = 3;
        quotationText.setLayoutData(quotationLayoutData);
        fieldsMap.put("quotation", quotationText); //$NON-NLS-1$
        PlatformUI.getWorkbench().getActiveWorkbenchWindow()
                .getSelectionService().addPostSelectionListener(
                        new ISelectionListener() {

                            public void selectionChanged(IWorkbenchPart part,
                                    ISelection selection) {
                                Object firstElement = ((IStructuredSelection) selection)
                                        .getFirstElement();
                                Quotation quotation = (Quotation) firstElement;
                                String tmp = quotation.getPrint();
                                ((Text) fieldsMap.get("print"))
                                        .setText(tmp == null ? "" : tmp);

                                tmp = quotation.getYear() == null ? ""
                                        : quotation.getYear().toString();
                                ((Text) fieldsMap.get("year"))
                                        .setText(tmp == null ? "" : tmp);

                                tmp = quotation.getCity();
                                ((Text) fieldsMap.get("city"))
                                        .setText(tmp == null ? "" : tmp);

                                tmp = quotation.getChapter();
                                ((Text) fieldsMap.get("chapter"))
                                        .setText(tmp == null ? "" : tmp);

                                tmp = quotation.getPage() == null ? ""
                                        : quotation.getPage().toString();
                                ((Text) fieldsMap.get("page"))
                                        .setText(tmp == null ? "" : tmp);

                                tmp = quotation.getKeyword();
                                ((Text) fieldsMap.get("keywords"))
                                        .setText(tmp == null ? "" : tmp);

                                tmp = quotation.getQuotation();
                                ((Text) fieldsMap.get("quotation"))
                                        .setText(tmp == null ? "" : tmp);
                            }
                        });
    }

    /**
     * Passing the focus request to the viewer's control.
     */
    public void setFocus() {

    }

}
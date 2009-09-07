package de.berlios.quotations.ui;

import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.ui.part.ViewPart;

import de.berlios.quotations.application.Activator;
import de.berlios.quotations.util.Messages;

public class MainView extends ViewPart {
    public static final String ID = "de.berlios.quotations.ui.MainView";

    private TableViewer viewer;

    /**
     * This is a callback that will allow us to create the viewer and initialize
     * it.
     */
    public void createPartControl(Composite parent) {

        viewer = new TableViewer(parent, SWT.SINGLE | SWT.FULL_SELECTION
                | SWT.BORDER);
        viewer.addFilter(Activator.getFilter());
        Table table = viewer.getTable();
        TableColumn tcAuthor = new TableColumn(table, SWT.NONE);
        tcAuthor.setText(Messages.getString("QuotationControl.Author")); //$NON-NLS-1$
        tcAuthor.setWidth(220);
        TableColumn tcTitle = new TableColumn(table, SWT.NONE);
        tcTitle.setText(Messages.getString("QuotationControl.Title")); //$NON-NLS-1$
        tcTitle.setWidth(550);
        table.setHeaderVisible(true);
        table.setLinesVisible(true);

        viewer.setLabelProvider(new QuotationLabelProvider());
        viewer.setContentProvider(new QuotationContentProvider());
        getSite().setSelectionProvider(viewer);

        viewer.setInput(Activator.getDB().getRows());
    }

    /**
     * Passing the focus request to the viewer's control.
     */
    public void setFocus() {
        viewer.getControl().setFocus();
    }

    public void refresh() {
        viewer.setInput(Activator.getDB().getRows());
    }

}
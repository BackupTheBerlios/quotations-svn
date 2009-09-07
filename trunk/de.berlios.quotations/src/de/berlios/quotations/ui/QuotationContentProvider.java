package de.berlios.quotations.ui;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;


public class QuotationContentProvider implements IStructuredContentProvider{

	public Object[] getElements(Object inputElement) {
		return (Object[])inputElement;
	}

	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		// TODO Auto-generated method stub
		
	}

}

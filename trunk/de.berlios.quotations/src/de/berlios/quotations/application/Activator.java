package de.berlios.quotations.application;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

import de.berlios.quotations.db.QuotationDB;
import de.berlios.quotations.ui.MainView;
import de.berlios.quotations.ui.QuotationFilter;

/**
 * {@inheritDoc}
 * 
 * @author THaratynowicz
 * 
 */
public class Activator extends AbstractUIPlugin {

    /**
     * The plug-in ID.
     */
    public static final String PLUGIN_ID = "de.berlios.quotations.application"; //$NON-NLS-1$

    /**
     * The shared instance.
     */
    private static Activator plugin;

    /**
     * Quotation database.
     */
    private static QuotationDB quotationDB;

    private static QuotationFilter quotationFilter;

    /**
     * Returns the shared instance.
     * 
     * @return the shared instance
     */
    public static Activator getDefault() {
        return plugin;
    }

    /**
     * Returns an image descriptor for the image file at the given plug-in
     * relative path.
     * 
     * @param path
     *            the path
     * @return the image descriptor
     */
    public static ImageDescriptor getImageDescriptor(String path) {
        return imageDescriptorFromPlugin(PLUGIN_ID, path);
    }

    /**
     * The constructor.
     */
    public Activator() {
        plugin = this;
    }

    /**
     * {@inheritDoc}
     */
    public final void start(BundleContext context) throws Exception {
        super.start(context);
    }

    /**
     * {@inheritDoc}
     */
    public final void stop(BundleContext context) throws Exception {
        plugin = null;
        super.stop(context);
    }

    public static QuotationDB getDB() {
        if (quotationDB == null) {
            try {
                quotationDB = new QuotationDB();
            } catch (Exception e) {
                // TODO Automatycznie generowany blok catch
                e.printStackTrace();
            }
        }
        return quotationDB;
    }

    public static QuotationFilter getFilter() {
        if (quotationFilter == null) {
            quotationFilter = new QuotationFilter();
        }
        return quotationFilter;
    }

    public static void refresh() {
        ((MainView) PlatformUI.getWorkbench().getActiveWorkbenchWindow()
                .getActivePage().findView(MainView.ID)).refresh();
    }
}

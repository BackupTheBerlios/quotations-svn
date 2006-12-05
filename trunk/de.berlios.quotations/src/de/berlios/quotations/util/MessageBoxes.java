package de.berlios.quotations.util;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;

public class MessageBoxes {

	private static Shell prevShell;

	public static void error(String message) {
		Shell shell = Display.getCurrent().getActiveShell();
		shell = shell == null ? prevShell : shell;
		prevShell = shell;
		MessageBox mb = new MessageBox(shell, SWT.ICON_ERROR);
		mb.setText(Messages.getString("MessageBoxes.Error_title")); //$NON-NLS-1$
		mb.setMessage(message);
		mb.open();

	}

	public static void info(String message) {
		Shell shell = Display.getCurrent().getActiveShell();
		shell = shell == null ? prevShell : shell;
		prevShell = shell;
		MessageBox mb = new MessageBox(shell, SWT.ICON_INFORMATION);
		mb.setText(Messages.getString("MessageBoxes.Information_title")); //$NON-NLS-1$
		mb.setMessage(message);
		mb.open();

	}

	public static int question(String message) {
		return question(message, SWT.YES | SWT.NO | SWT.ICON_QUESTION);
	}

	public static int question(String message, int style) {
		Shell shell = Display.getCurrent().getActiveShell();
		shell = shell == null ? prevShell : shell;
		prevShell = shell;
		MessageBox mb = new MessageBox(shell, style);
		mb.setText(Messages.getString("MessageBoxes.Question_title")); //$NON-NLS-1$
		mb.setMessage(message);
		return mb.open();

	}
}

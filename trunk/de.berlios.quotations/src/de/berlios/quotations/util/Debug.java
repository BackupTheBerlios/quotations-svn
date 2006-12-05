package de.berlios.quotations.util;

public class Debug {
	/**
	 * Kategori� mo�na w��czy� poprzez dodanie do polecenia uruchomieniowego
	 * opcji -Ddebug.kategoria
	 * 
	 * @param category
	 *            kategoria
	 * @return czy debugowanie danej kategorii jest w��czone
	 */
	public static boolean isEnabled(String category) {
		return System.getProperty("debug." + category) != null;
	}

	public static void println(String category, String msg) {
		if (isEnabled(category))
			System.out.println("(" + category + "): " + msg);
	}

	public static void println(String category, Object obj) {
		println(category, obj.toString());
	}
}

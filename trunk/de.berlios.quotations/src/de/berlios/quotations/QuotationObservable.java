package de.berlios.quotations;

import java.util.Observable;

/**
 * @author pkontek
 *
 */
public class QuotationObservable extends Observable {

	private Object data;
	
	/**
	 * informuje obserwatorów o zmianie obiektu obserwowanego
	 * @param data - obiekt wybrany w obiekcie obserwowanym
	 */
	public void set(Object data){
		this.data = data;
		setChanged();
		notifyObservers();
	}
	
	/**
	 * @return obiekt wybrany w obiekcie obserwowanym
	 */
	public Object getData(){
		return data;
	}
	
}

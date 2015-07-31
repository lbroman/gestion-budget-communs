/**
 * 
 */
package com.terrier.finances.gestion.ui.listener.budget.mensuel.creation;

import com.terrier.finances.gestion.ui.components.budget.mensuel.components.CreerDepenseForm;
import com.terrier.finances.gestion.ui.controler.common.AbstractComponentListener;
import com.terrier.finances.gestion.ui.sessions.UISessionManager;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Window;

/**
 * @author vzwingma
 *
 */
public class ActionCreerDepenseClickListener extends AbstractComponentListener implements ClickListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1823872638217135776L;

	
	
	/* (non-Javadoc)
	 * @see com.vaadin.ui.Button.ClickListener#buttonClick(com.vaadin.ui.Button.ClickEvent)
	 */
	@Override
	public void buttonClick(ClickEvent event) {
		Window sub = new Window("Créer une nouvelle dépense");
		sub.setWidth("600px");
		sub.setHeight("350px");
		sub.setContent(new CreerDepenseForm());
		sub.setModal(true);
		UISessionManager.getSession().setPopupModale(sub);
	}
}


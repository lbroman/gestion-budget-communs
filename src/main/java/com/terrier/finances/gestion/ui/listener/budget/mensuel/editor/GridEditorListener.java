package com.terrier.finances.gestion.ui.listener.budget.mensuel.editor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.terrier.finances.gestion.model.business.budget.LigneDepense;
import com.terrier.finances.gestion.ui.controler.budget.mensuel.liste.operations.GridOperationsController;
import com.vaadin.ui.Notification;
import com.vaadin.ui.components.grid.EditorCancelEvent;
import com.vaadin.ui.components.grid.EditorCancelListener;
import com.vaadin.ui.components.grid.EditorOpenEvent;
import com.vaadin.ui.components.grid.EditorOpenListener;
import com.vaadin.ui.components.grid.EditorSaveEvent;
import com.vaadin.ui.components.grid.EditorSaveListener;

/**
 * Listener de l'éditor de la grille des opérations
 * @author vzwingma
 *
 */
public class GridEditorListener implements EditorCancelListener<LigneDepense>, EditorSaveListener<LigneDepense>, EditorOpenListener<LigneDepense> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4092876167681783200L;

	/**
	 * Logger
	 */ 
	private static final Logger LOGGER = LoggerFactory.getLogger(GridEditorListener.class);


	private GridOperationsController controler;

	public GridEditorListener(GridOperationsController controller) {
		this.controler = controller;
	}


	@Override
	public void onEditorCancel(EditorCancelEvent<LigneDepense> event) {
		this.controler.updateViewGridOnEditableMode(false);
	}

	/* (non-Javadoc)
	 * @see com.vaadin.ui.components.grid.EditorOpenListener#onEditorOpen(com.vaadin.ui.components.grid.EditorOpenEvent)
	 */
	@Override
	public void onEditorOpen(EditorOpenEvent<LigneDepense> event) {
		this.controler.updateViewGridOnEditableMode(true);
	}

	/* (non-Javadoc)
	 * @see com.vaadin.ui.components.grid.EditorSaveListener#onEditorSave(com.vaadin.ui.components.grid.EditorSaveEvent)
	 */
	@Override
	public void onEditorSave(EditorSaveEvent<LigneDepense> event) {

		boolean validateForm = controler.validateEditableForm();
		if(validateForm){
			// Recalcul du budget
			this.controler.getServiceDepense().calculBudgetEtSauvegarde(this.controler.getBudgetMensuelCourant());
			// MAJ des tableaux
			this.controler.getBudgetControleur().miseAJourVueDonnees();
			Notification.show("Les dépenses ont bien été mises à jour", Notification.Type.TRAY_NOTIFICATION);
			this.controler.updateViewGridOnEditableMode(false);
		}
		else{
			Notification.show("Les données sont incorrectes pas de mise à jour", Notification.Type.TRAY_NOTIFICATION);
			LOGGER.warn("Les données sont incorrectes pas de mise à jour");
		}
	}
}


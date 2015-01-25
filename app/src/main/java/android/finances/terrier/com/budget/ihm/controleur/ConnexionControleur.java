package android.finances.terrier.com.budget.ihm.controleur;

import android.finances.terrier.com.budget.R;
import android.finances.terrier.com.budget.abstrait.AbstractActivityControleur;
import android.finances.terrier.com.budget.ihm.vue.ConnexionActivity;
import android.finances.terrier.com.budget.utils.IHMViewUtils;
import android.finances.terrier.com.budget.utils.Logger;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

/**
 * Controleur de budget
 * Created by vzwingma on 26/12/2014.
 */
public class ConnexionControleur extends AbstractActivityControleur<ConnexionActivity> {


    // Logger
    private final Logger LOG = new Logger(ConnexionControleur.class);


    /**
     * Démarrage du controleur
     */
    @Override
    public void startControleur() {
        new AuthenticationHTTPAsyncTask(this).execute();
    }


    /**
     * Affichage du résultat de l'auth
     *
     * @param resultat résultat
     * @param message  message à affiché
     */
    public void getResultatAuth(String message, boolean resultat) {
        ((TextView) getActivity().findViewById(R.id.textViewResultat)).setText(message);
        (getActivity().findViewById(R.id.textViewResultat)).setVisibility(View.VISIBLE);
        ((TextView) getActivity().findViewById(R.id.textViewResultat)).setTextColor(!resultat ? IHMViewUtils.COLOR_VALEUR_NEGATIF : IHMViewUtils.COLOR_VALEUR_POSITIF);
        (getActivity().findViewById(R.id.progressBar)).setVisibility(View.INVISIBLE);
    }


    /**
     * Arrét du controleur
     */
    @Override
    public void stopControleur() {

    }

    /**
     * Menu Item select
     */
    @Override
    public boolean onMenuItemSelected(int featureId, MenuItem item) {

        switch (item.getItemId()) {
            default:
                LOG.info("onMenuItemSelected : " + item.getItemId());
                return super.onMenuItemSelected(featureId, item);
        }
    }
}
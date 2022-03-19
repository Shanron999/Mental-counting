package com.example.projetcalculemental.Service;

import android.view.View;
import android.widget.EditText;

import com.example.projetcalculemental.Models.Exception.NoNumberException;
import com.example.projetcalculemental.Models.Exception.NovaluesException;

public class VerifService {

    public static String verifResultat(double resultat, EditText saisie)throws NoNumberException,NovaluesException{
        try {
            String saisieToString= saisie.getText().toString();
            if(saisieToString==null) {
                throw new NovaluesException("il n'y a pas de valeur saisie");

            }
            Double valeurSaisie =(double) Double.parseDouble(saisieToString);

            if(resultat==valeurSaisie)
            {
                return "true";

            }
            else {

                return "false";

            }
        }catch (NumberFormatException ex){
            throw new NoNumberException("Il y a une erreur lors de la saisie " +ex);
        }

    }
}

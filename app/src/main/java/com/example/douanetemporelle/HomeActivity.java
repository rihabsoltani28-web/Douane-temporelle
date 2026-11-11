
package com.example.douanetemporelle;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {

    // declarit les variables
    RadioGroup hDejaVoyage, hTypeVoyage;
    Button btnContinuer, btnNouveauVoyageur;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // recuperite les composants XML
        hDejaVoyage = findViewById(R.id.rs_dejavoyage);
        hTypeVoyage = findViewById(R.id.rs_typevoyage);
        btnContinuer = findViewById(R.id.rs_btn_continuer);
        btnNouveauVoyageur = findViewById(R.id.rs_btn_nouv_voy);

        // meloul désactivite le groupe des types de voyage
        hTypeVoyage.setEnabled(false);
        activerRadioButtons(false);

        // wl bouton continuer desactivitha zeda
        btnContinuer.setEnabled(false);

        // wl bouton nouveau voyageur desactivitha zeda
        btnNouveauVoyageur.setEnabled(false);

        // khdhit choix ya Oui / Non
        hDejaVoyage.setOnCheckedChangeListener((group, checkedId) -> {

            if (checkedId == R.id.rs_oui) {

                // Activet les types de voyage lena
                hTypeVoyage.setEnabled(true);
                activerRadioButtons(true);

                // Activer Continuer juste ba3d makhtar choix
                btnContinuer.setEnabled(
                        hTypeVoyage.getCheckedRadioButtonId() != -1);

                btnNouveauVoyageur.setEnabled(false);

            } else if (checkedId == R.id.rs_non) {

                // Désactivit les types de voyage
                hTypeVoyage.clearCheck();
                hTypeVoyage.setEnabled(false);
                activerRadioButtons(false);

                btnContinuer.setEnabled(false);
                btnNouveauVoyageur.setEnabled(true);
            }
        });

        // khdhi choix du type de voyage
        hTypeVoyage.setOnCheckedChangeListener((group, checkedId) -> {

            if (checkedId != -1) {
                btnContinuer.setEnabled(true);
            } else {
                btnContinuer.setEnabled(false);
            }
        });

        // lena khdemt 3la bouton continuer
        btnContinuer.setOnClickListener(v -> {

            int typeChoisi = hTypeVoyage.getCheckedRadioButtonId();

            if (typeChoisi == -1) {
                Toast.makeText(this,
                        "Veuillez choisir un type de voyage",
                        Toast.LENGTH_SHORT).show();
                return;
            }
            //bch yhel page personalisée selon nawa3 voyage
            Intent intent = new Intent(
                    HomeActivity.this, WelcomeVoyageActivity.class);

            if (typeChoisi == R.id.rs_tourisme) {
                intent.putExtra("TYPE_VOYAGE", "Tourisme");

            } else if (typeChoisi == R.id.rs_modification) {
                intent.putExtra("TYPE_VOYAGE", "Modification du passé");

            } else if (typeChoisi == R.id.rs_fuite) {
                intent.putExtra("TYPE_VOYAGE", "Fuite");
            }

            startActivity(intent);
        });

        // lena khdemt 3la bouton nouveau voyageur :bch yhel page mainactivity mte3na
        btnNouveauVoyageur.setOnClickListener(v -> {

            Intent intent = new Intent(
                    HomeActivity.this, MainActivity.class);

            startActivity(intent);
        });
    }

    // activer wala désactiver les boutons radio du groupe
    private void activerRadioButtons(boolean etat) {

        for (int i = 0; i < hTypeVoyage.getChildCount(); i++) {

            hTypeVoyage.getChildAt(i).setEnabled(etat);
        }
    }
}


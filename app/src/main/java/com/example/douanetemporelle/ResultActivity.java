package com.example.douanetemporelle;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ResultActivity extends AppCompatActivity {

    // Déclaration des variables
    TextView tvVerdict, tvMessage;
    Button btnNouveauVoyageur;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        // Récupérer les composants mn le fichier XML
        tvVerdict = findViewById(R.id.rs_tv_verdict);
        tvMessage = findViewById(R.id.rs_tv_message);
        btnNouveauVoyageur = findViewById(R.id.rs_btn_nouveau_voyageur);

        // Récupérer les données envoyées mn VerificationActivity
        Intent recu = getIntent();

        String m = CleUtils.MATRICULE;

        boolean approuve = recu.getBooleanExtra(
                CleUtils.construireCle(m, CleUtils.VERDICT), false);

        String nom = recu.getStringExtra(
                CleUtils.construireCle(m, CleUtils.NOM));

        // Vérifier le verdict et afficher le résultat
        if (approuve) {

            // wa9t eli cas approuvé l fond yabda vert
            tvVerdict.setText("VERDICT APPROUVÉ");
            tvMessage.setText("Bon voyage " + nom + " !");

            findViewById(R.id.rs_layout_resultat)
                    .setBackgroundColor(Color.GREEN);

        } else {

            // wa9t eli cas refusé l fond yabda rouge
            tvVerdict.setText("VERDICT REFUSÉ");
            tvMessage.setText(
                    nom + " est en état d'arrestation temporelle.");

            findViewById(R.id.rs_layout_resultat)
                    .setBackgroundColor(Color.RED);
        }

        // Bouton bch na3mel voyageur jdid
        btnNouveauVoyageur.setOnClickListener(v -> {

            Intent intent = new Intent(
                    ResultActivity.this, MainActivity.class);

            // nsaker les activite lo9dom
            intent.setFlags(
                    Intent.FLAG_ACTIVITY_NEW_TASK |
                            Intent.FLAG_ACTIVITY_CLEAR_TASK);

            startActivity(intent);
        });
    }
}

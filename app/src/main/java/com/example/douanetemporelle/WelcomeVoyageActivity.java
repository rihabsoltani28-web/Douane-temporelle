package com.example.douanetemporelle;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class WelcomeVoyageActivity extends AppCompatActivity {

    ImageView imgVoyage;
    TextView titVoyage, messVoyage;
    Button btnNouveauVoyageur;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_voyage);

        // recuperina les composants XML
        imgVoyage = findViewById(R.id.rs_imgvoy);
        titVoyage = findViewById(R.id.rs_titvoy);
        messVoyage = findViewById(R.id.rs_messvoy);
        btnNouveauVoyageur = findViewById(R.id.rs_btn_nouveau_voyageur);

        // recuperina type de voyage choisi
        String typeVoyage = getIntent().getStringExtra("TYPE_VOYAGE");

        // affichina l'image selon le type de voyage
        if ("Tourisme".equals(typeVoyage)) {

            imgVoyage.setImageResource(R.drawable.tourisme);
            titVoyage.setText("Voyage touristique");

        } else if ("Modification du passé".equals(typeVoyage)) {

            imgVoyage.setImageResource(R.drawable.modificationdepasse);
            titVoyage.setText("Modification du passé");

        } else if ("Fuite".equals(typeVoyage)) {

            imgVoyage.setImageResource(R.drawable.fuite);
            titVoyage.setText("Voyage de fuite");
        }

        // ktebt message ll utilisateur yabda personalisé
        messVoyage.setText("Saha lik, nchallah ykoun voyage 3ejbek !");

        // bouton nouveau voyageur
        btnNouveauVoyageur.setOnClickListener(v -> {

            Intent intent = new Intent(
                    WelcomeVoyageActivity.this,
                    MainActivity.class
            );

            startActivity(intent);
        });
    }
}

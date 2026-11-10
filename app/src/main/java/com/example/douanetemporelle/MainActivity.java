
package com.example.douanetemporelle;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Button;
import android.widget.Toast;
import android.content.Intent;
import android.widget.RadioButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Calendar;


public class MainActivity extends AppCompatActivity {

    //houni 3malt déclaration des variables mte3i
    EditText nom;
    EditText anneeNaiss;
    EditText anneeDest;
    RadioGroup radioGroupMotif;
    Button btnInspect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        WindowCompat.enableEdgeToEdge(getWindow());
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(
                findViewById(R.id.main), (v, insets) -> {

                    Insets systemBars = insets.getInsets(
                            WindowInsetsCompat.Type.systemBars());

                    v.setPadding(systemBars.left, systemBars.top,
                            systemBars.right, systemBars.bottom);

                    return insets;
                });

        // récupérite les composants eli fi l'interface
        nom = findViewById(R.id.rs_nom);
        anneeNaiss = findViewById(R.id.rs_anneeN);
        anneeDest = findViewById(R.id.rs_anneeD);
        radioGroupMotif = findViewById(R.id.rs_radio_group_motif);
        btnInspect = findViewById(R.id.rs_btn_insp);

        // chnowa bch ysir wa9t clic 3la bouton
        btnInspect.setOnClickListener(v -> {

            // nahit les espace mn les valeurs eli dakhalhom utilisateur w
            String nomVoyageur = nom.getText().toString().trim();
            String naissance = anneeNaiss.getText().toString().trim();
            String destination = anneeDest.getText().toString().trim();

            // Contrôle du nom
            if (nomVoyageur.isEmpty()) {
                nom.setError("Veuillez saisir votre nom");
                nom.requestFocus();
                return;
            }

            // Contrôle de l'année de naissance
            if (naissance.isEmpty()) {
                anneeNaiss.setError("Veuillez saisir votre année de naissance");
                anneeNaiss.requestFocus();
                return;
            }

            // Contrôle de l'année de destination
            if (destination.isEmpty()) {
                anneeDest.setError("Veuillez saisir l'année de destination");
                anneeDest.requestFocus();
                return;
            }

            int anneeN;
            int anneeD;

            // n vérifiw l format numérique
            try {
                anneeN = Integer.parseInt(naissance);
                anneeD = Integer.parseInt(destination);
            } catch (NumberFormatException e) {
                Toast.makeText(this,
                        "Les années doivent être des nombres",
                        Toast.LENGTH_SHORT).show();
                return;
            }

            // lena ntal3ou Année actuelle
            int anneeActuelle = Calendar.getInstance()
                    .get(Calendar.YEAR);

            if (anneeN > anneeActuelle) {
                anneeNaiss.setError("L'année de naissance ne peut pas être dans le futur");
                anneeNaiss.requestFocus();
                return;
            }

            // n vérifiw l motif
            int motifSelectionne =
                    radioGroupMotif.getCheckedRadioButtonId();

            if (motifSelectionne == -1) {
                Toast.makeText(this,
                        "Veuillez choisir un motif de voyage",
                        Toast.LENGTH_SHORT).show();
                return;
            }

            // khdhit l motif l mokhtara
            RadioButton rbChoisi = findViewById(motifSelectionne);
            String motif = rbChoisi.getText().toString();

            //bch n lancer l'act 2
            Intent intent = new Intent(MainActivity.this, VerificationActivity.class);
            String m = CleUtils.MATRICULE;
            intent.putExtra(CleUtils.construireCle(m, CleUtils.NOM), nomVoyageur);
            intent.putExtra(CleUtils.construireCle(m, CleUtils.NAISSANCE), anneeN);
            intent.putExtra(CleUtils.construireCle(m, CleUtils.DESTINATION), anneeD);
            intent.putExtra(CleUtils.construireCle(m, CleUtils.MOTIF), motif);
            startActivity(intent);

        });
    }
}

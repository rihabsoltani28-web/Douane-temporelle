package com.example.douanetemporelle;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class VerificationActivity extends AppCompatActivity {

    // declaration des variables
    TextView tvNom, tvAge, tvNaissance, tvDestination, tvMotif, tvAlerte;
    Button btnApprouver, btnRefuser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification);

        // rbatna les attribut mn xml
        tvNom = findViewById(R.id.rs_nom_resume);
        tvAge = findViewById(R.id.rs_age_temporel);
        tvNaissance = findViewById(R.id.rs_naissance_resume);
        tvDestination = findViewById(R.id.rs_destination_resume);
        tvMotif = findViewById(R.id.rs_motif_resume);
        tvAlerte = findViewById(R.id.rs_alerte_paradoxe);
        btnApprouver = findViewById(R.id.rs_btn_approuver);
        btnRefuser = findViewById(R.id.rs_btn_refuser);

        // khdina les donnéées mn activité 1
        Intent recu = getIntent();
        String m = CleUtils.MATRICULE;
        String nom = recu.getStringExtra(CleUtils.construireCle(m, CleUtils.NOM));
        int naissance = recu.getIntExtra(CleUtils.construireCle(m, CleUtils.NAISSANCE), 0);
        int destination = recu.getIntExtra(CleUtils.construireCle(m, CleUtils.DESTINATION), 0);
        String motif = recu.getStringExtra(CleUtils.construireCle(m, CleUtils.MOTIF));

        // hsebna fatret l wa9t w 3maltelha 9ima motel9a khater tnajem tji negative
        int difference = Math.abs(destination - naissance);

        // hatit les données fl passeport(interface)
        tvNom.setText("Nom : " + nom);
        tvAge.setText("Âge temporel : " + difference + " ans");
        tvNaissance.setText("Naissance : " + naissance);
        tvDestination.setText("Destination : " + destination);
        tvMotif.setText("Motif : " + motif);

        // lena hatit ken l motif feha modification du passé w ken age temporel premier desactiver bouton approuver
        if ("Modification du passé".equals(motif) && estPremier(difference)) {
            btnApprouver.setEnabled(false);
            tvAlerte.setText("Paradoxe détecté : approbation impossible");
        }

        // ki nenzel ala bouton approuver ye9bel w fonction tab3eth true w esm l 3abed
        btnApprouver.setOnClickListener(v -> envoyerVerdict(true, nom));

        // ki nenzel ala bouton refuser yorfedh w yab3eth ghalet(refuser) w l esm zeda
        btnRefuser.setOnClickListener(v -> envoyerVerdict(false, nom));
    }

    //  l fonction eli bch tab3ethli true/false wl esm zeda w theli l activité 3
    private void envoyerVerdict(boolean verdict, String nom) {
        Intent intent = new Intent(VerificationActivity.this, ResultActivity.class);
        String m = CleUtils.MATRICULE;
        intent.putExtra(CleUtils.construireCle(m, CleUtils.VERDICT), verdict);
        intent.putExtra(CleUtils.construireCle(m, CleUtils.NOM), nom);
        startActivity(intent);
    }

    // fct bch tchoufli la3dad premier ou non
    private boolean estPremier(int n) {
        if (n < 2) return false;
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) return false;
        }
        return true;
    }
}
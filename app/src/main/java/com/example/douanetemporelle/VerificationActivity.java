
package com.example.douanetemporelle;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class VerificationActivity extends AppCompatActivity {

    // declaration des variables
    TextView verNom, verAge, verNaiss, verDest, verMotif, verAlerte;
    Button btnApprouver, btnRefuser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification);

        // rbatna les attribut mn xml
        verNom = findViewById(R.id.rs_nom);
        verAge = findViewById(R.id.rs_ageTemp);
        verNaiss = findViewById(R.id.rs_naiss);
        verDest = findViewById(R.id.rs_dest);
        verMotif = findViewById(R.id.rs_motif);
        verAlerte = findViewById(R.id.rs_alerte_paradoxe);
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
        verNom.setText("Nom : " + nom);
        verAge.setText("Âge temporel : " + difference + " ans");
        verNaiss.setText("Naissance : " + naissance);
        verDest.setText("Destination : " + destination);
        verMotif.setText("Motif : " + motif);

        // lena hatit ken l motif feha modification du passé w ken age temporel premier desactiver bouton approuver
        if ("Modification du passé".equals(motif) && estPremier(difference)) {
            btnApprouver.setEnabled(false);
            verAlerte.setText("Paradoxe détecté : approbation impossible");
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

package com.example.douanetemporelle;

public class CleUtils {

    //hatit les 4 dernier chiffre de carte CIN khater f carte etudiant mafamech num
    public static final String MATRICULE = "2916";

    // أسماء المفاتيح بدون الرقم
    public static final String NOM = "NOM_VOYAGEUR";
    public static final String NAISSANCE = "ANNEE_NAISSANCE";
    public static final String DESTINATION = "ANNEE_DESTINATION";
    public static final String MOTIF = "MOTIF_VOYAGE";
    public static final String VERDICT = "VERDICT";

    // nasna3 l cle mte3i b matricule w nom mta3 cle
    public static String construireCle(String matricule, String nomCle) {
        return matricule + "_" + nomCle;
    }
}

package MatchEngineCLI;

import java.util.*;
public class GenerateurTailleNomsOriginaleIndex implements GenerateurCandidats {
    private  Map<Integer, List<Nom>> map ;
    private static final int DIFFERENCE_MAX = 2;

    public GenerateurTailleNomsOriginaleIndex(Map<Integer, List<Nom>> map) {this.map=map;}; // Étape 1 : Créer une Map pour classer les noms de list2 par taille

    @Override

    public  List<CoupleDeNoms> generer(List<Nom> listeNoms1, List<Nom> listeNoms2) {
        // Parcourir list2 pour remplir la Map
        for (Nom nom : listeNoms2) {
            int tailleNom = nom.nomNonTraite().length();
            // Vérifier si la clé existe, sinon initialiser une nouvelle liste
            this.map.putIfAbsent(tailleNom, new ArrayList<>());
            this.map.get(tailleNom).add(nom);
        }
        // Étape 2 : Générer les couples de noms
        List<CoupleDeNoms> couples = new ArrayList<>();
        // Parcourir chaque nom de list1
        for (Nom nom1 : listeNoms1) {
            int tailleNom1 = nom1.nomTraite().size();
            // Définir l'intervalle de tailles [taille-1, taille+1]
            for (int taille = Math.max(1, tailleNom1 - DIFFERENCE_MAX); taille <= tailleNom1 + DIFFERENCE_MAX; taille++) {
                // Vérifier si la taille existe dans la Map
                if (this.map.containsKey(taille)) {
                    // Créer un couple avec chaque nom de la liste correspondante
                    for (Nom nom2 : this.map.get(taille)) {
                        couples.add(new CoupleDeNoms(nom1, nom2));
                    }
                }
            }
        }

        return couples;
    }
}
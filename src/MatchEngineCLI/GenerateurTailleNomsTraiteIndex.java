package MatchEngineCLI;

import java.util.*;

public class GenerateurTailleNomsTraiteIndex implements GenerateurCandidats {
    private Map<Integer, List<Nom>> map;
    private static final int DIFFERENCE_MAX = 2; // Constante pour la marge de l'intervalle

    public GenerateurTailleNomsTraiteIndex(Map<Integer, List<Nom>> map) {
        this.map = map;
    }

    @Override
    public List<CoupleDeNoms> generer(List<Nom> listeNoms1, List<Nom> listeNoms2) {
        // Étape 1 : Parcourir listeNoms2 pour remplir la Map en utilisant nomTraite().size()
        for (Nom nom : listeNoms2) {
            int tailleNom = nom.nomTraite().size(); // Utiliser size() de nomTraite()
            this.map.putIfAbsent(tailleNom, new ArrayList<>());
            this.map.get(tailleNom).add(nom);
        }

        // Étape 2 : Générer les couples de noms
        List<CoupleDeNoms> couples = new ArrayList<>();
        for (Nom nom1 : listeNoms1) {
            int tailleNom1 = nom1.nomTraite().size(); // Utiliser size() de nomTraite()
            // Définir l'intervalle [tailleNom1 - DIFFERENCE_MAX, tailleNom1 + DIFFERENCE_MAX]
            for (int taille = Math.max(1, tailleNom1 - DIFFERENCE_MAX); taille <= tailleNom1 + DIFFERENCE_MAX; taille++) {
                if (this.map.containsKey(taille)) {
                    for (Nom nom2 : this.map.get(taille)) {
                        couples.add(new CoupleDeNoms(nom1, nom2));
                    }
                }
            }
        }
        return couples;
    }
}
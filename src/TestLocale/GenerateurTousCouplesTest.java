package TestLocale;

import MatchEngineCLI.*;



import java.util.List;
import java.util.ArrayList;

public class GenerateurTousCouplesTest{

    public static void main(String[] args) {
        // Création des listes de noms
        List<Nom> liste1 = new ArrayList<>();
        liste1.add(new Nom("1", "Alice"));
        liste1.add(new Nom("2", "Bob"));

        List<Nom> liste2 = new ArrayList<>();
        liste2.add(new Nom("3", "Xavier"));
        liste2.add(new Nom("4", "Yasmine"));

        // Instanciation du générateur
        GenerateurCandidats generateur = new GenerateurTousCouples();

        // Génération des couples
        List<CoupleDeNoms> couples = generateur.generer(liste1, liste2);

        // Affichage des couples générés
        for (CoupleDeNoms couple : couples) {
            System.out.println(couple.nom1().getNomNonTraite() + " " + couple.nom2().getNomNonTraite());
        }
    }
}
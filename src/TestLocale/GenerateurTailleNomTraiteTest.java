package TestLocale;

import MatchEngineCLI.*;

import java.util.List;
import java.util.ArrayList;

public class GenerateurTailleNomTraiteTest {

    public static void main(String[] args) {

        List<Nom> liste1 = new ArrayList<>();

        liste1.add(new Nom("1", "Alice"));

        liste1.add(new Nom("2", "Bob"));

        List<Nom> liste2 = new ArrayList<>();
        liste2.add(new Nom("3", "Xavier"));

        liste2.add(new Nom("4", "Yas"));


        GenerateurCandidats generateur = new GenerateurTailleNomsTraite();


        List<CoupleDeNoms> couples = generateur.generer(liste1, liste2);


        if (couples.isEmpty()) {
            System.out.println("Aucun couple généré (les tailles des listes de noms traités sont trop différentes).");
        } else {
            for (CoupleDeNoms couple : couples) {
                System.out.println(couple.nom1().getNomNonTraite() + " " + couple.nom2().getNomNonTraite());
            }
        }
    }
}
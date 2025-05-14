package TestLocale;

import MatchEngineCLI.*;

import java.util.List;
import java.util.ArrayList;

public class GenerateurTailleNomOriginaleTest {

    public static void main(String[] args) {
        List<Nom> liste1 = new ArrayList<>();
        liste1.add(new Nom("1", "Alice", List.of("A", "Al", "Ali", "Alice")));
        liste1.add(new Nom("2", "Bob", List.of("B", "Bo", "Bob")));

        List<Nom> liste2 = new ArrayList<>();
        liste2.add(new Nom("3", "Xavier", List.of("X", "Xa", "Xav", "Xavier")));
        liste2.add(new Nom("4", "Yas", List.of("Y", "Ya", "Yas", "Yasmine")));
        liste2.add(new Nom("5", "Yasmine", List.of("Y", "Ya", "Yas", "Yasmine")));

        GenerateurCandidats generateur = new GenerateurTailleNomsOriginale();

        List<CoupleDeNoms> couples = generateur.generer(liste1, liste2);

        for (CoupleDeNoms couple : couples) {
            System.out.println(couple.nom1().getNomNonTraite() + " " + couple.nom2().getNomNonTraite());
        }
    }
}
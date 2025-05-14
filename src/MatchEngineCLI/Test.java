package MatchEngineCLI;

import java.util.Arrays;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        // Création de l'instance du comparateur
        ComparateurNomsOriginalJaroWinkler comparateur1 = new ComparateurNomsOriginalJaroWinkler();
        ComparateurNomsTraiteJaroWinkler comparateur2 = new ComparateurNomsTraiteJaroWinkler();
        ComparateurNomsOriginalLevenshtein comparateur3 = new ComparateurNomsOriginalLevenshtein();
        ComparateurNomsTraiteLevenshtein comparateur = new ComparateurNomsTraiteLevenshtein();
        ComparateurNomsOriginalExacte comparateur5 = new ComparateurNomsOriginalExacte();
        ComparateurNomsOriginalExacte comparateur6 = new ComparateurNomsOriginalExacte();
        // Création de l'instance du prétraiteur
        PretraiteurNormalisation pretraiteur1 = new PretraiteurNormalisation();
        PretraiteurDecomposeur pretraiteur = new PretraiteurDecomposeur();
        PretraiteurMetaphone pretraiteur3 = new PretraiteurMetaphone();
        PretraiteurSoundex pretraiteur4 = new PretraiteurSoundex();

        // Liste de cas de test pour comparateur
        List<Nom[]> tests = Arrays.asList(
                // Cas 1 : Noms identiques
                new Nom[]{new Nom("1", "Jean", pretraiteur.pretraiter("Jean")), new Nom("2", "Jean", pretraiteur.pretraiter("Jean"))},
                // Cas 2 : Noms complètement différents
                new Nom[]{new Nom("3", "Jean", pretraiteur.pretraiter("Jean")), new Nom("4", "Marie", pretraiteur.pretraiter("Marie"))},
                // Cas 3 : Noms similaires avec une petite différence
                new Nom[]{new Nom("5", "Jean", pretraiteur.pretraiter("Jean")), new Nom("6", "Jeanne", pretraiteur.pretraiter("Jeanne"))},
                // Cas 4 : Chaînes vides
                new Nom[]{new Nom("7", "", pretraiteur.pretraiter("")), new Nom("8", "", pretraiteur.pretraiter(""))},
                // Cas 5 : Une chaîne vide et une non vide
                new Nom[]{new Nom("9", "", pretraiteur.pretraiter("")), new Nom("10", "Jean", pretraiteur.pretraiter("Jean"))},
                // Cas 6 : Noms avec différence de casse
                new Nom[]{new Nom("11", "jean", pretraiteur.pretraiter("jean")), new Nom("12", "Jean", pretraiteur.pretraiter("Jean"))},
                // Cas 7 : Noms longs avec petites différences
                new Nom[]{new Nom("13", "Alexandre", pretraiteur.pretraiter("Alexandre")), new Nom("14", "Alexander", pretraiteur.pretraiter("Alexander"))},
                // Cas 8 : Noms avec espaces
                new Nom[]{new Nom("15", "Jean Paul", pretraiteur.pretraiter("Jean Paul")), new Nom("16", "Jean-Paul", pretraiteur.pretraiter("Jean-Paul"))},
                // Cas 9 : Noms avec caractères spéciaux
                new Nom[]{new Nom("17", "José", pretraiteur.pretraiter("José")), new Nom("18", "Jose", pretraiteur.pretraiter("Jose"))},
                // Cas 10 : Noms de longueurs très différentes
                new Nom[]{new Nom("19", "Jean", pretraiteur.pretraiter("Jean")), new Nom("20", "Jean-Baptiste", pretraiteur.pretraiter("Jean-Baptiste"))},
                // Cas 11 : Noms avec répétition de caractères
                new Nom[]{new Nom("21", "Hanna", pretraiteur.pretraiter("Hanna")), new Nom("22", "Hannah", pretraiteur.pretraiter("Hannah"))},
                // Cas 12 : Noms avec caractères unicode complexes
                new Nom[]{new Nom("23", "Bjørn", pretraiteur.pretraiter("Bjørn")), new Nom("24", "Bjorn", pretraiteur.pretraiter("Bjorn"))},
                // Cas 13 : Noms très longs
                new Nom[]{new Nom("25", "JeanChristopheDupont", pretraiteur.pretraiter("JeanChristopheDupont")),
                        new Nom("26", "JeanChristopherDupont", pretraiteur.pretraiter("JeanChristopherDupont"))},
                // Cas 14 : Noms avec chiffres
                new Nom[]{new Nom("27", "John2", pretraiteur.pretraiter("John2")), new Nom("28", "John3", pretraiteur.pretraiter("John3"))},
                // Cas 15 : Noms avec ponctuation
                new Nom[]{new Nom("29", "O'Neil", pretraiteur.pretraiter("O'Neil")), new Nom("30", "ONeil", pretraiteur.pretraiter("ONeil"))},
                // Cas 16 : Noms avec espaces multiples
                new Nom[]{new Nom("31", "Jean   Paul", pretraiteur.pretraiter("Jean   Paul")), new Nom("32", "Jean Paul", pretraiteur.pretraiter("Jean Paul"))},
                // Cas 17 : Noms avec lettres inversées
                new Nom[]{new Nom("33", "listen", pretraiteur.pretraiter("listen")), new Nom("34", "silent", pretraiteur.pretraiter("silent"))},
                // Cas 18 : Noms avec caractères répétés
                new Nom[]{new Nom("35", "aaaa", pretraiteur.pretraiter("aaaa")), new Nom("36", "aaab", pretraiteur.pretraiter("aaab"))},
                // Cas 19 : Noms avec espaces en début/fin
                new Nom[]{new Nom("37", " Jean ", pretraiteur.pretraiter(" Jean ")), new Nom("38", "Jean", pretraiteur.pretraiter("Jean"))},
                // Cas 20 : Noms très courts vs très longs
                new Nom[]{new Nom("39", "Li", pretraiteur.pretraiter("Li")), new Nom("40", "LilianneMarieDupont", pretraiteur.pretraiter("LilianneMarieDupont"))},
                // Cas 21 : Noms avec caractères non latins
                new Nom[]{new Nom("41", "Анна", pretraiteur.pretraiter("Анна")), new Nom("42", "Anna", pretraiteur.pretraiter("Anna"))},
                // Cas 22 : Noms avec tirets multiples
                new Nom[]{new Nom("43", "Jean-Paul-Marie", pretraiteur.pretraiter("Jean-Paul-Marie")),
                        new Nom("44", "Jean-Paul", pretraiteur.pretraiter("Jean-Paul"))},
                // Cas 23 : Noms avec caractères mélangés (lettres, chiffres, symboles)
                new Nom[]{new Nom("45", "User#123", pretraiteur.pretraiter("User#123")), new Nom("46", "User#124", pretraiteur.pretraiter("User#124"))}
        );

        // Liste de chaînes de test pour prétraiteur
        List<String> liste = Arrays.asList(
                "",                             // Chaîne vide
                "hello",                        // Chaîne simple, lettres uniquement
                "Hello",                        // Chaîne avec majuscules
                "José",                         // Chaîne avec accents
                "Héllô",                        // Chaîne avec plusieurs accents
                "John123 Smith",                      // Chaîne avec chiffres
                "Jean#Paul",                    // Chaîne avec symboles
                "Jean Paul",                    // Chaîne avec espaces
                "Jean   Paul",                  // Chaîne avec espaces multiples
                " Jean ",                       // Chaîne avec espaces en début/fin
                "O'Neil",                       // Chaîne avec ponctuation
                "Анна",                         // Chaîne avec caractères Unicode non latins
                "Jean-Christophe_Dupont123!",   // Chaîne longue et complexe
                "aaaa",                         // Chaîne avec caractères répétés
                "Bjørn123#Ä",                   // Chaîne avec caractères mixtes
                "Jean-Paul-Marie",              // Chaîne avec tirets multiples
                "café",                         // Chaîne avec caractères spéciaux Unicode
                "12345",                        // Chaîne avec chiffres uniquement
                "@#$%^",                        // Chaîne avec symboles uniquement
                "HÉLÈNE",                       // Chaîne avec accents et majuscules
                "Jean\nPaul",                   // Chaîne avec caractères de contrôle
                "JeanChristopheDupontLongNameWithAccentsÉèê123", // Chaîne très longue
                "こんにちは",                     // Chaîne avec caractères asiatiques
                "مرحبا",                        // Chaîne avec caractères arabes
                "JeanمرحباHello"                // Chaîne avec mélange de scripts
        );

        // Exécution des tests du prétraiteur
        System.out.println("Tests du Pretraiteur :");
        System.out.println("---------------------------------------------");
        for (int i = 0; i < tests.size(); i++) {
            String input = liste.get(i);
            List<String> result = pretraiteur.pretraiter(input);
            System.out.printf("Test %2d: Input: '%s' -> Output: %s%n",
                    i + 1, input, result);
        }

        // Exécution des tests du comparateur
        System.out.println("Tests du Comparateur :");
        System.out.println("---------------------------------------------");
        for (int i = 0; i < tests.size(); i++) {
            Nom nom1 = tests.get(i)[0];
            Nom nom2 = tests.get(i)[1];
            double score = comparateur.comparer(nom1, nom2);
            System.out.printf("Test %2d: '%s' vs '%s' -> Score: %.4f%n",
                    i + 1, nom1.nomNonTraite(), nom2.nomNonTraite(), score);
        }
    }
}
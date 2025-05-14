package MatchEngineCLI;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    static MoteurDeMatching moteur = new MoteurDeMatching();
    static Recuperateur recuperateur;
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.println("Enter l'emplacement de fichier source :");
        String emplacement = "C:\\Users\\mariem\\Downloads\\peps_names_100.csv";

        recuperateur = new RecuperateurCSV(emplacement);

        // SET DEFAULT CONFIG FOR ENGINE
        List<Pretraiteur> pretraiteurs = new ArrayList<>();
        pretraiteurs.add(new PretraiteurNormalisation());
        pretraiteurs.add(new PretraiteurDecomposeur());

        moteur.setComparateur(new ComparateurNomsOriginalExacte());
        moteur.setSelectionneur(new SelectionneurNMeilleursSort(10));
        moteur.setPretraiteurs(pretraiteurs);
        moteur.setGenerateurCandidats(new GenerateurTailleNomsOriginaleIndex());
        moteur.setSeuil(0.6);
        moteur.setNbMax(10);

        while (true) {
            afficherMenuPrincipal();
            String choix = scanner.nextLine();

            switch (choix) {
                case "1" -> effectuerRecherche();
                case "2" -> comparerDeuxListes();
                case "3" -> effectuerDedupliquerListe();
                case "4" -> configurerParametres();
                case "5" -> {
                    System.out.println("Fin du programme.");
                    scanner.close();
                    return;
                }
                default -> System.out.println("Choix invalide.");
            }


        }

    }

    static void afficherMenuPrincipal() {
        System.out.println("\n===== MENU PRINCIPAL =====\n");
        System.out.println("1. Effectuer une recherche\n");
        System.out.println("2. Comparer deux listes\n");
        System.out.println("3. Dédupliquer une liste\n");
        System.out.println("4. Configurer les paramètres\n");
        System.out.println("5. Quitter\n");
        System.out.print("Votre choix : ");
    }

    static void afficherMenuConfiguration() {
        // TODO
        System.out.println("\n===== MENU CONFIGURATION =====");
        System.out.println("1. Choisir les prétraitements\r\n");
        System.out.println("2. Choisir une mesure de comparaison\r\n");
        System.out.println("3. Choisir un generateur de candidats\r\n");
        System.out.println("4. Choisir un selectionneur\r\n");
        System.out.println("5. Quitter\n");
        System.out.print("\nVotre choix : ");
    }


    static void configurerParametres() {

        while (true) {
            afficherMenuConfiguration();
            String choix = scanner.nextLine();
            switch (choix) {
                case "1" -> choixPretraiteurs();
                case "2" -> choixComparateur();
                case "3" -> choixGenerateurDeCandidats();
                case "4" -> choixSelectionneur();
                case "5" -> {

                    return;
                }
                default -> System.out.println("\nChoix invalide.");
            }
        }


    }

    static void afficherMenuGenerateurDeCandidats() {

        System.out.println("\n===== MENU GENERATEUR DE CANDIDATS =====");
        System.out.println("1. Choisir tous combinisants possibles\r\n");
        System.out.println("3. Choisir generateur par prefix \r\n");
        System.out.println("4. Choisir generateur par taille de noms original \r\n");
        System.out.println("5. Choisir generateur par taille de noms traités \r\n");
        System.out.println("6. Choisir generateur par taille de noms original index \r\n");
        System.out.println("7. Choisir generateur par taille de noms traités index \r\n");
        System.out.println("8. Quitter\n");
        System.out.print("Votre choix : ");
    }

    static void choixGenerateurDeCandidats() {

        while (true) {
            afficherMenuGenerateurDeCandidats();
            String choix = scanner.nextLine();
            switch (choix) {
                case "1" -> generateurTous();
                case "2" -> generateurAvecPrefix();
                case "3" -> generateurParTailleOriginale();
                case "4" -> generateurParTailleTraite();
                case "5" -> generateurParTailleOriginaleIndex();
                case "6" -> generateurParTailleTraiteIndex();
                default -> System.out.println("\nChoix invalide.");
            }
        }


    }

    static void generateurAvecPrefix() {
        moteur.setGenerateurCandidats(new GenerateurPrefixeCommun());
    }

    static void generateurTous() {
        moteur.setGenerateurCandidats(new GenerateurTousCouples());

    }

    static void generateurParTailleOriginale() {

        moteur.setGenerateurCandidats(new GenerateurTailleNomsOriginale());
    }

    static void generateurParTailleTraite() {

        moteur.setGenerateurCandidats(new GenerateurTailleNomsTraite());
    }


    static void generateurParTailleOriginaleIndex() {

        moteur.setGenerateurCandidats(new GenerateurTailleNomsOriginaleIndex());
    }

    static void generateurParTailleTraiteIndex() {
        moteur.setGenerateurCandidats(new GenerateurTailleNomsTraiteIndex());
    }

    static void afficherMenuComparateur() {
        // TODO
        System.out.println("\n===== MENU COMPARATEUR =====\n");
        System.out.println("1. Choisir  Comparateur Noms Original Exacte\r\n");
        System.out.println("2. Choisir Comparateur Noms Original JaroWinkler\r\n");
        System.out.println("3. choisir Comparateur Noms Original Levenshtein\n");
        System.out.println("4. choisir Comparateur Noms Traite Exacte \n");
        System.out.println("5. choisir Comparateur Noms Traite JaroWinkler \n");
        System.out.println("6. choisir Comparateur Noms Traite Levenshtein \n");
        System.out.println("7. Quitter");
        System.out.print("Votre choix : ");
    }

    static void choixComparateur() {


        while (true) {
            afficherMenuComparateur();
            String choix = scanner.nextLine();
            switch (choix) {
                case "1" -> choixComparateurNomsOriginalExacte();
                case "2" -> choixComparateurNomsOriginalJaroWinkler();
                case "3" -> choixComparateurNomsOriginalLevenshtein();
                case "4" -> choixComparateurNomsTraiteExacte();
                case "5" -> choixComparateurNomsTraiteJaroWinkler();
                case "6" -> choixComparateurNomsTraiteLevenshtein();
                case "7" -> {

                    return;
                }
                default -> System.out.println("Choix invalide.");
            }
        }


    }

    static void choixComparateurNomsOriginalExacte() {
        moteur.setComparateur(new ComparateurNomsOriginalExacte());

    }

    static void choixComparateurNomsOriginalJaroWinkler() {
        moteur.setComparateur(new ComparateurNomsOriginalJaroWinkler());

    }

    static void choixComparateurNomsOriginalLevenshtein() {

        moteur.setComparateur(new ComparateurNomsOriginalLevenshtein());

    }

    static void choixComparateurNomsTraiteExacte() {
        moteur.setComparateur(new ComparateurNomsTraiteExacte());

    }

    static void choixComparateurNomsTraiteJaroWinkler() {
        moteur.setComparateur(new ComparateurNomsTraiteJaroWinkler());

    }

    static void choixComparateurNomsTraiteLevenshtein() {
        moteur.setComparateur(new ComparateurNomsTraiteLevenshtein());

    }


    static void afficheMenuPretraiteur() {
        System.out.println("\n===== MENU PRETRAITEUR =====\n");
        System.out.println("1. prétraiteur normalisation\n");
        System.out.println("2. prétraiteur décomposeur\n");
        System.out.println("3. prétraiteur soundex \n");
        System.out.println("4. Pretraiteur metaphone\n");
        System.out.println("5. Quitter\n");
        System.out.print("Votre choix : ");

    }

    static void choixPretraiteurs() {
        while (true) {
            afficheMenuPretraiteur();
            String choix = scanner.nextLine();

            switch (choix) {
                case "1" -> PretraiteurNormalisation();
                case "2" -> PretraiteurDecomposeur();
                case "3" -> PretraiteurSoundex();
                case "4" -> PretraiteurMetaphone();
                case "5" -> {
                    return;
                }
                default -> System.out.println("Choix invalide.");
            }
        }
    }

    static void PretraiteurNormalisation() {
        for (Pretraiteur p : moteur.getPretraiteur()) {
            if (p instanceof PretraiteurNormalisation) {
                System.out.println("ce pretraiteur exist deja!!");
                return;
            }
        }

        moteur.getPretraiteur().add(new PretraiteurNormalisation());

    }

    static void PretraiteurDecomposeur() {
        for (Pretraiteur p : moteur.getPretraiteur()) {
            if (p instanceof PretraiteurDecomposeur) {
                System.out.println("ce pretraiteur exist deja!!");
                return;
            }
        }

        moteur.getPretraiteur().add(new PretraiteurDecomposeur());

    }

    static void PretraiteurSoundex() {
        for (Pretraiteur p : moteur.getPretraiteur()) {
            if (p instanceof PretraiteurSoundex) {
                System.out.println("ce pretraiteur exist deja!!");
                return;
            }
        }
        moteur.getPretraiteur().add(new PretraiteurSoundex());

    }

    static void PretraiteurMetaphone() {
        for (Pretraiteur p : moteur.getPretraiteur()) {
            if (p instanceof PretraiteurMetaphone) {
                System.out.println("ce pretraiteur exist deja!!");
                return;
            }
        }
        moteur.getPretraiteur().add(new PretraiteurMetaphone());

    }


    static void afficheMenuSelectionneur() {
        System.out.println("\n===== MENU SELECTIONNEUR =====\n");
        System.out.println("1.Choisir selectionneur de tous les resultats\n");
        System.out.println("2.Choisir selectionneur aves seuil\n");
        System.out.println("3.Choisir selectionneur des N meilleurs max\n");
        System.out.println("4.Choisir selectionneur des N meilleurs sort\n");
        System.out.println("5. Quitter\n");
        System.out.print("Votre choix : ");

    }


    static void choixSelectionneur() {
        while (true) {
            afficheMenuSelectionneur();
            String choix = scanner.nextLine();

            switch (choix) {
                case "1" -> SelectionneurSansLesResultats();
                case "2" -> SelectionneurParSeuil();
                case "3" -> SelectionneurNMeilleursmax();
                case "4" -> SelectionneurNMeilleurssort();
                case "5" -> {
                    return;
                }
                default -> System.out.println("Choix invalide.");
            }
        }
    }

    static void SelectionneurSansLesResultats() {
        moteur.setSelectionneur(new SelectionneurSansResultat());
    }

    static void SelectionneurParSeuil() {

        System.out.println("donner le seuil :");
        String saisie = scanner.nextLine().trim().replace(',', '.');

        try {
            double seuil = Double.parseDouble(saisie);
            System.out.println("Seuil accepté : " + seuil);
            moteur.setSelectionneur(new SelectionneurParSeuil(seuil));
        } catch (NumberFormatException e) {
            System.out.println("Erreur : valeur non valide !");
        }

    }

    static void SelectionneurNMeilleursmax() {
        System.out.println("Entrez le nombre de resultats a selectionner : ");
        int n = scanner.nextInt();
        scanner.nextLine();
        moteur.setSelectionneur(new SelectionneurNMeilleursMax(n));

    }

    static void SelectionneurNMeilleurssort() {
        System.out.println("Entrez le nombre de resultats a selectionner : ");
        int n = scanner.nextInt();
        scanner.nextLine();
        moteur.setSelectionneur(new SelectionneurNMeilleursSort(n));

    }

    static void effectuerRecherche() {
        System.out.print("Entrez le nom à rechercher : ");
        String nomOriginal = scanner.nextLine();

        Nom nom = new Nom("-1", nomOriginal);
        List<Nom> listeOriginale = recuperateur.recuperer();

        System.out.println(listeOriginale.size());
        long start = System.currentTimeMillis();


        if (listeOriginale == null || listeOriginale.isEmpty()) {
            System.out.println("Erreur : la liste des candidats est vide ou n'a pas pu être chargée.");
            return;
        }


        List<NomAvecScore> resultats = moteur.rechercher(nom, listeOriginale);
        long end = System.currentTimeMillis();

        System.out.println("\nRésultats pour : " + nom.getNomNonTraite());
        for (NomAvecScore ns : resultats) {
            System.out.printf("Nom: %s (Score: %.2f)%n",
                    ns.nom(),
                    ns.score());
        }

        System.out.println("Execution time: " + (end - start) + " ms");


    }

    static void comparerDeuxListes() {
        // TODO

        List<Nom> list1 = new ArrayList<Nom>();
        list1 = recuperateur.recuperer();
        if (list1 == null || list1.isEmpty()) {
            System.out.println("Erreur : la liste 1  des candidats est vide ou n’a pas pu être chargée.");
            return;
        }


        List<Nom> list2 = new ArrayList<Nom>();
        list2 = recuperateur.recuperer();
        if (list2 == null || list2.isEmpty()) {
            System.out.println("Erreur : la liste 2 des candidats est vide ou n’a pas pu être chargée.");
            return;
        }
        long start = System.currentTimeMillis();

        List<CoupleAvecScore> resultat = new ArrayList<>();
        resultat = moteur.comparerListes(list1, list2);


        resultat = moteur.getSelectionneur().selectionner(resultat);
        long end = System.currentTimeMillis();
        if (resultat == null || resultat.isEmpty()) {
            System.out.println("Aucun résultat trouvé.");
            return;
        }
        for (CoupleAvecScore couple : resultat) {
            System.out.println(couple);


        }
        System.out.println("Execution time: " + (end - start) + " ms");
    }

    static void effectuerDedupliquerListe() {
        // TODO

        List<Nom> listeOriginale = recuperateur.recuperer();

        if (listeOriginale == null || listeOriginale.isEmpty()) {
            System.out.println("Erreur : la liste des candidats est vide ou n'a pas pu être chargée.");
            return;
        }
        long start = System.currentTimeMillis();
        moteur.dedupliquerListe(listeOriginale);
        // List<CoupleNomsScore> resultat = new ArrayList<>(moteur.DedupliquerList(listeOriginale));
        long end = System.currentTimeMillis();
			   /* if (resultat == null || resultat.isEmpty()) {
		            System.out.println("Aucun résultat trouvé.");
		            return;
		        }
			    resultat = moteur.getSelectionneur().selectionner(resultat);

				 for(CoupleNomsScore couple : resultat) {
					 System.out.println(couple);
				 }
				 */
        System.out.println("Execution time: " + (end - start) + " ms");
    }


}



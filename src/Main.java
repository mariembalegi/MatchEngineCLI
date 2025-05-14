package MatchEngineCLI;

import java.io.*;
import java.util.*;
public class Main {
    static MoteurDeMatching moteur = new MoteurDeMatching();

    static Recuperateur recuperateur = new RecuperateurCSV();
    static  Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {



        // TODO Auto-generated method stub

        //D:/Desktop/listesNoms/peps_names_658k.csv

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

        while(true) {
            afficherMenuGenerateurDeCandidats();
            String choix = scanner.nextLine();
            switch (choix) {
                case "1" -> generateurTous();
                case "2" -> generateurAvecPrefix();
                case "3" -> generateurParTailleOriginale ();
                case "4" -> generateurParTailleTraite ();
                case "5" -> generateurParTailleOriginaleIndex();
                case "6" -> generateurParTailleTraiteIndex();
                case "5" -> {

                    return;
                }
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


        while(true) {
            afficherMenuComparateur();
            String choix = scanner.nextLine();
            switch (choix) {
                case "1" -> choixComparateurNomsOriginalExacte();
                case "2" -> choixComparateurNomsOriginalJaroWinkler();
                case "3" -> choixComparateurNomsOriginalLevenshtein() ;
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
        moteur.setComparateur(new  ComparateurNomsOriginalExacte());

    }
    static void choixComparateurNomsOriginalJaroWinkler() {
        moteur.setComparateur(new ComparateurNomsOriginalJaroWinkler());

    }
    static void choixComparateurNomsOriginalLevenshtein()() {
        
        moteur.setComparateur(new ComparateurNomsOriginalLevenshtein());

    }
    static void  choixComparateurNomsTraiteExacte() {
        moteur.setComparateur(new ComparateurNomsTraiteExacte ());

    }
    static void  choixComparateurNomsTraiteJaroWinkler() {
        moteur.setComparateur(new ComparateurNomsTraiteJaroWinkler());

    }
    static void choixComparateurNomsTraiteLevenshtein() {
        moteur.setComparateur(new ComparateurNomsTraiteLevenshtein);

    }





    static void afficheMenuPretraiteur() {
        System.out.println("\n===== MENU PRETRAITEUR =====\n");
        System.out.println("Le pretraiteur par defaut est le pretraiteur en minuscule\n");
        System.out.println("1.suprimer le pretraiteur en minuscule\n");
        System.out.println("2. Encodage phonétique\n");
        System.out.println("3. Pretraire suprime les accents \n");
        System.out.println("4. Pretraiteur suprime les caractaires speciciaux\n");
        System.out.println("5. Pretraiteur de tri les noms\n");
        System.out.println("6. Quitter\n");
        System.out.print("Votre choix : ");

    }
    static void choixPretraiteurs() {
        while (true) {
            afficheMenuPretraiteur();
            String choix = scanner.nextLine();

            switch (choix) {
                case "1" -> suprimerpretraiterMinuscule();
                case "2" -> pretraiterPhonetique();
                case "3" -> pretraiterAccents();
                case "4" -> pretraiteurSansCaracteresSpeciaux();
                case "5" -> pretraiteurDeTRiNom();
                case "6" ->{
                    return;
                }
                default -> System.out.println("Choix invalide.");
            }
        }
    }

    static void pretraiteurDeTRiNom() {
        for (Pretraiteur p : moteur.getPretraiteur()) {
            if (p instanceof  PretraiteurDeTRiNom) {
                System.out.println("ce pretraiteur exist deja!!");
                return;
            }
        }

        moteur.getPretraiteur().add(new PretraiteurDeTRiNom());

    }

    static void pretraiterPhonetique(){
        for (Pretraiteur p : moteur.getPretraiteur()) {
            if (p instanceof  PretraiteurPhonetique) {
                System.out.println("ce pretraiteur exist deja!!");
                return;
            }
        }

        moteur.getPretraiteur().add(new PretraiteurPhonetique());

    }
    static void pretraiterAccents() {
        for (Pretraiteur p : moteur.getPretraiteur()) {
            if (p instanceof  PretraiteurSansAccents)  {
                System.out.println("ce pretraiteur exist deja!!");
                return;
            }
        }
        moteur.getPretraiteur().add(new PretraiteurSansAccents());

    }
    static void suprimerpretraiterMinuscule() {
        Iterator<Pretraiteur> it = moteur.getPretraiteur().iterator();
        while (it.hasNext()) {
            Pretraiteur p = it.next();
            if (p instanceof PretraiteurMinuscule) {
                it.remove();
            }
        }
    }
    static void pretraiteurSansCaracteresSpeciaux() {
        for (Pretraiteur p : moteur.getPretraiteur()) {
            if (p instanceof  PretraiteurSansCaracteresSpeciaux) {
                System.out.println("ce pretraiteur exist deja!!");
                return;

            }
        }
        moteur.getPretraiteur().add(new PretraiteurSansCaracteresSpeciaux());
    }


    static void afficheMenuSelectionneur() {
        System.out.println("\n===== MENU SELECTIONNEUR =====\n");
        System.out.println("1.Choisir selectionneur de tous les resultats\n");
        System.out.println("2.Choisir selectionneur aves seuil\n");
        System.out.println("3.Choisir selectionneur des N premiers\n");
        System.out.println("4. Quitter\n");
        System.out.print("Votre choix : ");

    }


    static void choixSelectionneur() {
        while (true) {
            afficheMenuSelectionneur();
            String choix = scanner.nextLine();

            switch (choix) {
                case "1" -> SelectionneurDeTousLesResultats();
                case "2" -> SelectionneurAvecSeuil();
                case "3" -> SelectionneurNPremiers();
                case "4" -> {
                    return;
                }
                default -> System.out.println("Choix invalide.");
            }
        }
    }

    static void SelectionneurDeTousLesResultats() {
        moteur.setSelectionneur(new SelectionneurDeTousLesResultats());

    }
    static void SelectionneurAvecSeuil() {

        System.out.println("donner le seuil :");
        String saisie = scanner.nextLine().trim().replace(',', '.');

        try {
            double seuil = Double.parseDouble(saisie);
            System.out.println("Seuil accepté : " + seuil);
            moteur.setSelectionneur(new SelectionneurAvecSeuil(seuil));
        } catch (NumberFormatException e) {
            System.out.println("Erreur : valeur non valide !");
        }

    }
    static void SelectionneurNPremiers() {
        System.out.println("Entrez le nombre de resultats a selectionner : ");
        int n = scanner.nextInt();
        scanner.nextLine();
        moteur.setSelectionneur(new SelectionneurNPremiers(n));

    }
    static void effectuerRecherche()  {
        System.out.print("Entrez le nom à rechercher : ");
        String nomOriginal = scanner.nextLine();

        EntiteNom nom = new EntiteNom(nomOriginal,"-1");
        List<EntiteNom> listeOriginale = recuperateur.recuperer();
        long start = System.currentTimeMillis();


        if (listeOriginale == null || listeOriginale.isEmpty()) {
            System.out.println("Erreur : la liste des candidats est vide ou n'a pas pu être chargée.");
            return;
        }



        List<CoupleNomsScore> resultats = moteur.rechercher(nom, listeOriginale);
        long end = System.currentTimeMillis();

        System.out.println("\nRésultats pour : " + nom.getNomcomplet());
        for (CoupleNomsScore ns : resultats) {



            System.out.printf("Nom: %s (Score: %.2f)%n",
                    ns.getNom2(),
                    ns.getScore());
        }

        System.out.println("Execution time: " + (end - start) + " ms");



    }

    static void comparerDeuxListes() {
        // TODO

        List<EntiteNom> list1 =new ArrayList<EntiteNom>();
        list1 = recuperateur.recuperer();
        if (list1 == null || list1.isEmpty()) {
            System.out.println("Erreur : la liste 1  des candidats est vide ou n’a pas pu être chargée.");
            return;
        }


        List<EntiteNom> list2 =new ArrayList<EntiteNom>();
        list2 = recuperateur.recuperer();
        if (list2 == null || list2.isEmpty()) {
            System.out.println("Erreur : la liste 2 des candidats est vide ou n’a pas pu être chargée.");
            return;
        }
        long start = System.currentTimeMillis();

        List<CoupleNomsScore> resultat = new ArrayList<>();
        resultat = moteur.ComparerListes(list1, list2);



        resultat = moteur.getSelectionneur().selectionner(resultat);
        long end = System.currentTimeMillis();
        if (resultat == null || resultat.isEmpty()) {
            System.out.println("Aucun résultat trouvé.");
            return;
        }
        for(CoupleNomsScore couple : resultat) {
            System.out.println(couple);


        }
        System.out.println("Execution time: " + (end - start) + " ms");
    }

    static void effectuerDedupliquerListe() {
        // TODO

        List<EntiteNom> listeOriginale = recuperateur.recuperer();

        if (listeOriginale == null || listeOriginale.isEmpty()) {
            System.out.println("Erreur : la liste des candidats est vide ou n'a pas pu être chargée.");
            return;
        }
        long start = System.currentTimeMillis();
        moteur.DedupliquerList(listeOriginale);
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



package MatchEngineCLI;

import java.util.ArrayList;
import java.util.List;

public class MoteurDeMatching {

    private List<Pretraiteur> pretraiteurs;
    private Recuperateur recuperateur;
    private Comparateur comparateur;
    private Selectionneur selectionneur;
    private GenerateurCandidats generateur;

    public MoteurDeMatching(
            List<Pretraiteur> pretraiteurs,
            Recuperateur recuperateur,
            Comparateur comparateur,
            Selectionneur selectionneur,
            GenerateurCandidats generateur
    ) {
        this.pretraiteurs = pretraiteurs;
        this.recuperateur = recuperateur;
        this.comparateur = comparateur;
        this.selectionneur = selectionneur;
        this.generateur = generateur;
    }

    public List<List<Object>> rechercher(String nomARechercher) {
        
        Nom nomRecherche = new Nom(nomARechercher, null);
        for (Pretraiteur pretraiteur : pretraiteurs) {
            nomRecherche = new Nom(nomRecherche.getNomNonTraite(), pretraiteur.pretraiter(nomRecherche.getNomNonTraite()));
        }
        List<Nom> nomsRecuperes = new ArrayList<>();
        List<Object> ids = new ArrayList<>();
        List<List<Object>> lignes = recuperateur.recuperer();

        for (List<Object> ligne : lignes) {
            String nomBrut = ligne.get(0).toString();
            Object id = ligne.get(1);

            Nom nom = new Nom(nomBrut, null);
            for (Pretraiteur pretraiteur : pretraiteurs) {
                nom = new Nom(nom.getNomNonTraite(), pretraiteur.pretraiter(nom.getNomNonTraite()));
            }

            nomsRecuperes.add(nom);
            ids.add(id);
        }

        List<Nom> candidats = generateur.generer(nomsRecuperes, nomRecherche);

        List<List<Object>> resultatsAvecScore = new ArrayList<>();
        for (int i = 0; i < candidats.size(); i++) {
            Nom candidat = candidats.get(i);
            Object id = ids.get(i);
            double score = comparateur.comparer(nomRecherche, candidat);

            List<Object> resultat = new ArrayList<>();
            resultat.add(nomRecherche);
            resultat.add(candidat);
            resultat.add(id);
            resultat.add(score);

            resultatsAvecScore.add(resultat);
        }

        return selectionneur.selectionner(resultatsAvecScore);
    }
}


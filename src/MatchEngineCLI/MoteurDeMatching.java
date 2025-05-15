package MatchEngineCLI;
import java.util.ArrayList;
import java.util.List;

public class MoteurDeMatching {

    private List<Pretraiteur> pretraiteurs;
    private Comparateur comparateur;
    private Selectionneur selectionneur;
    private GenerateurCandidats generateur;
    private static double seuil = 0.6;
    private static int nbMax = 10;

    public List<NomAvecScore> rechercher(Nom nomRecherche, List<Nom> listeNoms) {
        //Pretraitement Liste
        for (Nom nom : listeNoms) {
            List<String> nomsATraiter = nom.getNomTraite();
            for (Pretraiteur pretraiteur : pretraiteurs) {
                List<String> nomsApresPretraitement = new ArrayList<>();
                for (String nomStr : nomsATraiter) {
                    nomsApresPretraitement.addAll(pretraiteur.pretraiter(nomStr));
                }
                nomsATraiter = nomsApresPretraitement;
            }
            nom.setNomTraite(nomsATraiter);
        }
        //Pretraitement nomRecherche
        List<String> nomAtraiter = nomRecherche.getNomTraite();
        for (Pretraiteur pretraiteur : pretraiteurs) {
            List<String> nomsAPresPretraitement = new ArrayList<>();
            for (String nomStr : nomAtraiter) {
                nomsAPresPretraitement.addAll(pretraiteur.pretraiter(nomStr));
            }
            nomAtraiter = nomsAPresPretraitement;
        }

        nomRecherche.setNomTraite(nomAtraiter);


        //generer les candidats
        List<CoupleDeNoms> candidats = generateur.generer(List.of(nomRecherche), listeNoms);


        // comparaison
        List<CoupleAvecScore> resultatsAvecScore = new ArrayList<>();
        for (CoupleDeNoms couple : candidats) {
            double score = comparateur.comparer(couple.nom1(), couple.nom2());
            resultatsAvecScore.add(new CoupleAvecScore(couple.nom1(), couple.nom2(), score));
        }

        //Selectionner
        List<CoupleAvecScore> listeApresSelectionneur = selectionneur.selectionner(resultatsAvecScore);
        //retour liste(nom,score)
        List<NomAvecScore> res = new ArrayList<>();
        for (CoupleAvecScore c : listeApresSelectionneur) {
            res.add(new NomAvecScore(c.nom2(), c.score()));
        }
        return res;
    }

    public List<CoupleAvecScore> comparerListes(List<Nom> liste1, List<Nom> liste2) {
        List<CoupleAvecScore> correspondances = new ArrayList<>();
        for (Nom nom : liste1) {
            List<NomAvecScore> similaires = rechercher(nom, liste2);
            for (NomAvecScore sim : similaires) {
                correspondances.add(new CoupleAvecScore(nom, sim.nom(), sim.score()));
            }
        }
        return correspondances;
    }

    public List<CoupleAvecScore> dedupliquerListe(List<Nom> liste) {
        return comparerListes(liste, liste);
    }


    public void setGenerateurCandidats(GenerateurCandidats generateurCandidats) {
        this.generateur = generateurCandidats;
    }

    public Selectionneur getSelectionneur() {
        return selectionneur;
    }

    public void setSelectionneur(Selectionneur selectionneur) {
        this.selectionneur = selectionneur;
    }

    public void setComparateur(Comparateur comparateurNoms) {
        this.comparateur = comparateurNoms;
    }

    public double getSeuil() {
        return seuil;
    }

    public int getNbMax() {
        return nbMax;
    }

    public void setSeuil(double seuil) {
        this.seuil = seuil;
    }

    public void setNbMax(int nbMax) {
        this.nbMax = nbMax;
    }

    public List<Pretraiteur> getPretraiteur() {
        return this.pretraiteurs;
    }

    public void setPretraiteur(List<Pretraiteur> pretraiteurs) {
        this.pretraiteurs = pretraiteurs;
    }
}

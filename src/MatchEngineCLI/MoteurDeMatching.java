import java.util.ArrayList;
import java.util.List;

public class MoteurDeMatching {

    private List<Pretraiteur> pretraiteurs;
    private Recuperateur recuperateur;
    private Comparateur comparateur;
    private Selectionneur selectionneur;
    private Generateur generateur;

    public MoteurDeMatching(
            List<Pretraiteur> pretraiteurs,
            Recuperateur recuperateur,
            Comparateur comparateur,
            Selectionneur selectionneur,
            Generateur generateur
    ) {
        this.pretraiteurs = pretraiteurs;
        this.recuperateur = recuperateur;
        this.comparateur = comparateur;
        this.selectionneur = selectionneur;
        this.generateur = generateur;
    }

    public List<List<Object>> rechercher(String nomARechercher) {

        
        Nom nomRecherche = new Nom(nomARechercher);
        for (Pretraiteur pretraiteur : pretraiteurs) {
            nomRecherche.setNomTraite(pretraiteur.pretraiter(nomRecherche.getNomNonTraite()));
        }

        List<List<Object>> donneesPretraitees = new ArrayList<>();
        List<List<Object>> lignesRecuperees = recuperateur.recuperer();

        for (List<Object> ligne : lignesRecuperees) {
            String nomBrutRecupere = ligne.get(0).toString();
            Object id = ligne.get(1);

            Nom nomRecupere = new Nom(nomBrutRecupere);

            
            for (Pretraiteur pretraiteur : pretraiteurs) {
                nomRecupere.setNomTraite(pretraiteur.pretraiter(nomRecupere.getNomNonTraite()));
            }

            donneesPretraitees.add(List.of(nomRecherche, nomRecupere, identifiant));
        }

        List<List<Object>> combinaisons = generateur.generer(donneesPretraitees);
        List<List<Object>> resultatsAvecScore = new ArrayList<>();

        for (List<Object> combinaison : combinaisons) {
            Nom nomRecherche1 = (Nom) combinaison.get(0);
            Nom nomRecupere = (Nom) combinaison.get(1);
            Object id = combinaison.get(2);

            List<List<Object>> scores = comparateur.comparer(nomRecherche1, nomRecupere);
            for (List<Object> scoreLigne : scores) {
                Double score = (Double) scoreLigne.get(0);

                List<Object> ligneAvecScore = new ArrayList<>();
                ligneAvecScore.add(nomRecherche1);
                ligneAvecScore.add(nomRecupere);
                ligneAvecScore.add(id);
                ligneAvecScore.add(score);

                resultatsAvecScore.add(ligneAvecScore);
            }
        }

        return selectionneur.selectionner(resultatsAvecScore);
    }
}

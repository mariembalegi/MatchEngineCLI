import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class GenerateurCouplesIndex implements GenerateurCandidats {

    private final int intervalle;
    private final Map<Integer, List<Nom>> indexParTaille;

    public GenerateurParTailleNomAvecIndex(int intervalle) {
        this.intervalle = intervalle;
        this.indexParTaille = new HashMap<>();
    }

    // Méthode pour construire l'index à partir de liste2
    public void construireMap(List<Nom> liste2) {
        indexParTaille.clear();
        for (Nom n : liste2) {
            int taille = n.getNomComplet().length();
            List<Nom> liste = indexParTaille.get(taille);
            if (liste == null) {
                liste = new ArrayList<>();
                indexParTaille.put(taille, liste);
            }
            liste.add(n);
        }
    }

    @Override
    public List<Couple> generer(List<Nom> liste1, List<Nom> liste2) {
        
        if (indexParTaille.isEmpty()) {
            construireMap(liste2);
        }

        List<Couple> couples = new ArrayList<>();
        for (Nom n1 : liste1) {
            int taille1 = n1.getNomComplet().length();

            for (int t = taille1 - intervalle; t <= taille1 + intervalle; t++) {
                List<Nom> correspondants = indexParTaille.get(t);
                if (correspondants != null) {
                    for (Nom n2 : correspondants) {
                        couples.add(new Couple(n1, n2));
                    }
                }
            }
        }

        return couples;
    }
}
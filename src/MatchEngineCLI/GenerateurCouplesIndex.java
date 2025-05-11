import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GenerateurCouplesIndex implements GenerateurDeCandidats {

    @Override
    public List<CoupleDeNoms> generer(List<Nom> liste1, List<Nom> liste2) {
        Map<Integer, List<Nom>> tailleANoms = new HashMap<>();
        for (Nom nom : liste2) {
            int taille = nom.toString().length();
            if (!tailleANoms.containsKey(taille)) {
            tailleANoms.put(taille, new ArrayList<>());
        }
        tailleANoms.get(taille).add(nom);
        }

        List<CoupleDeNoms> couples = new ArrayList<>();

        liste2.forEach(nom -> tailleANoms.computeIfAbsent(taille, k -> new ArrayList<>()).add(nom));

        return couples;
    }
}

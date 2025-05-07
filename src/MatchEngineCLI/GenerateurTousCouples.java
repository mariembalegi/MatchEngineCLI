package MatchEngineCLI;

import java.util.List;
import java.util.ArrayList;

public class GenerateurTousCouples implements GenerateurCandidats {

    @Override
    public List<List<Nom>> generer(List<Nom> listeNoms, Nom nomRecherche) {
        List<List<Nom>> result = new ArrayList<>();
        for (Nom nom : listeNoms) {
            result.add(List.of(nomRecherche, nom));
        }
        return result;
    }
}

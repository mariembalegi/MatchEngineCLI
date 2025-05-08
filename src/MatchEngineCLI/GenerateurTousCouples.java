package MatchEngineCLI;

import java.util.List;
import java.util.ArrayList;

public class GenerateurTousCouples implements GenerateurCandidats {

    @Override
    public List<List<Nom>> generer(List<Nom> listeNoms1, List<Nom> listeNoms2) {
        List<List<Nom>> result = new ArrayList<>();
        for (Nom nom1 : listeNoms1) {
            for (Nom nom2 : listeNoms2) {
                result.add(List.of(nom1, nom2));
            }
        }
        return result;
    }
}

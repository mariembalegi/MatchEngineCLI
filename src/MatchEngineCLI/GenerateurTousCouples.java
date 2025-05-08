package MatchEngineCLI;

import java.util.List;
import java.util.ArrayList;

public class GenerateurTousCouples implements GenerateurCandidats {

    @Override
    public List<CoupleDeNoms> generer(List<Nom> listeNoms1, List<Nom> listeNoms2) {
        List<CoupleDeNoms> result = new ArrayList<>();
        for (Nom nom1 : listeNoms1) {
            for (Nom nom2 : listeNoms2) {
                result.add(new CoupleDeNoms(nom1, nom2));
            }
        }
        return result;
    }
}

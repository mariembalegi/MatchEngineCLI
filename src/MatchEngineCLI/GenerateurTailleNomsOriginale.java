package MatchEngineCLI;

import java.util.ArrayList;
import java.util.List;

public class GenerateurTailleNomsOriginale implements GenerateurCandidats {
    private static final int DIFFERENCE_MAX = 2;
    @Override
    public List<CoupleDeNoms> generer(List<Nom> liste1, List<Nom> liste2) {
        List<CoupleDeNoms> couples = new ArrayList<>();
        for (Nom nom1 : liste1) {
            String s1 = nom1.nomNonTraite();
            for (Nom nom2 : liste2) {
                String s2 = nom2.nomNonTraite();
                int diff = Math.abs(s1.length() - s2.length());
                if (diff <= DIFFERENCE_MAX) {
                    couples.add(new CoupleDeNoms(nom1, nom2));
                }
            }
        }
        return couples;
    }

}
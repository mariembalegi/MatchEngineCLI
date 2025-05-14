package MatchEngineCLI;

import java.util.List;
import java.util.ArrayList;

public class GenerateurTailleNomsTraite implements GenerateurCandidats {
    private static final int DIFFERENCE_MAX = 2;

    public List<CoupleDeNoms> generer(List<Nom> liste1, List<Nom> liste2) {
        List<CoupleDeNoms> couples = new ArrayList<>();
        for (Nom nom1 : liste1) {
            int taille1 = nom1.nomTraite().size();

            for (Nom nom2 : liste2) {
                int taille2 = nom2.nomTraite().size();
                int diff = Math.abs(taille1 - taille2);

                if (diff <= DIFFERENCE_MAX) {
                    couples.add(new CoupleDeNoms(nom1, nom2));
                }
            }
        }
        return couples;
    }
}

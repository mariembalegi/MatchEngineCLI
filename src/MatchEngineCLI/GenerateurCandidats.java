package MatchEngineCLI;

import java.util.List;

public interface GenerateurCandidats {

    List<CoupleDeNoms> generer(List<Nom> listeNoms1, List<Nom> listeNoms2);
}




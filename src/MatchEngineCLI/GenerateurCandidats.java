package MatchEngineCLI;

import java.util.List;

public interface GenerateurCandidats {

    List<List<Nom>> generer(List<Nom> listeNoms1, List<Nom> listeNoms2);
}




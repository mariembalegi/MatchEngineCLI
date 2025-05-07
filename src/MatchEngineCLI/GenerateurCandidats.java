package MatchEngineCLI;

import java.util.List;

public interface GenerateurCandidats {

    List<Nom> generer(List<Nom> listeNoms, Nom nomRecherche);
}




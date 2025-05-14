package MatchEngineCLI;


public class ComparateurNomsOriginalExacte implements Comparateur {
    @Override
    public double comparer(Nom nom1, Nom nom2) {
        return nom1.getNomNonTraite().equals(nom2.getNomNonTraite()) ? 1 : 0;
    }
}

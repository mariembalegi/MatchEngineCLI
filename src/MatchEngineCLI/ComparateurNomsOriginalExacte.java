package MatchEngineCLI;


public class ComparateurNomsOriginalExacte implements Comparateur {
    @Override
    public double comparer(Nom nom1, Nom nom2) {
        return nom1.nomNonTraite().equals(nom2.nomNonTraite()) ? 1 : 0;
    }
}

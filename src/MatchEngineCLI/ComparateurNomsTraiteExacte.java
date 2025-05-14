package MatchEngineCLI;

public class ComparateurNomsTraiteExacte implements Comparateur {
        @Override
        public double comparer(Nom nom1, Nom nom2) {
            return Math.abs(nom1.nomTraite().size() - nom2.nomTraite().size()) >= 2 ? 0 : 1;
        }
}

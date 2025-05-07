package MatchEngineCLI;


public class ComparateurEgalite implements Comparateur {
    @Override
    public double comparer(Nom nom1, Nom nom2) {
        boolean sontEgaux = nom1.equals(nom2);
        if (sontEgaux) {
            return 1 ;
        } else {
            return 0 ;
        }
    }
}

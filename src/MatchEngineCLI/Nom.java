package MatchEngineCLI;

import java.util.ArrayList;
import java.util.List;

public class Nom {
    private List<String> nomTraite;
    private String nomNonTraite;

    public Nom(String nomNonTraite, List<Pretraiteur> pretraiteurs) {
        this.nomNonTraite = nomNonTraite;
        this.nomTraite = new ArrayList<>(List.of(nomNonTraite));
        for (Pretraiteur pretraiteur : pretraiteurs) {
            List<String> atraite = nomTraite;
            this.nomTraite.clear();
            for (String s : atraite) {
                this.nomTraite.addAll(pretraiteur.pretraiter(s));
            }
        }
    }

    public List<String> getNomTraite() {
        return nomTraite;
    }

    public String getNomNonTraite() {
        return nomNonTraite;
    }
}
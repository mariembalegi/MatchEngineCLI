package MatchEngineCLI;

import java.util.List;

public class Nom {
    private List<String> nomTraite;
    private String nomNonTraite;

    public Nom(String nomNonTraite, List<String> nomTraite) {
        this.nomNonTraite = nomNonTraite;
        this.nomTraite=nomTraite;
        }


    public List<String> getNomTraite() {return nomTraite;}

    public String getNomNonTraite() {
        return nomNonTraite;
    }
}
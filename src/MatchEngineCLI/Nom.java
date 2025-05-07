package MatchEngineCLI;

import java.util.List;

public class Nom {
    private String id;
    private String nomNonTraite;
    private List<String> nomTraite;


    public Nom(String id, String nomNonTraite, List<String> nomTraite) {
        this.id = id;
        this.nomNonTraite = nomNonTraite;
        this.nomTraite=nomTraite;
        }

    public String getId() {
        return id;
    }

    public String getNomNonTraite() {
        return nomNonTraite;
    }

    public List<String> getNomTraite() {return nomTraite;}


}
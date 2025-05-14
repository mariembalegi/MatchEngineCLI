package MatchEngineCLI;


import java.util.List;

public class Nom {
    private String id;
    private String nomNonTraite;
    private List<String> nomTraite;

    // Constructeur
    public Nom(String id, String nomNonTraite, List<String> nomTraite) {
        this.id = id;
        this.nomNonTraite = nomNonTraite;
        this.nomTraite = nomTraite;
    }

    // Getters
    public String getId() {
        return id;
    }

    public String getNomNonTraite() {
        return nomNonTraite;
    }

    public List<String> getNomTraite() {
        return nomTraite;
    }

    // Setters
    public void setId(String id) {
        this.id = id;
    }

    public void setNomNonTraite(String nomNonTraite) {
        this.nomNonTraite = nomNonTraite;
    }

    public void setNomTraite(List<String> nomTraite) {
        this.nomTraite = nomTraite;
    }
}
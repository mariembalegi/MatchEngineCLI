package MatchEngineCLI;

import java.util.ArrayList;
import java.util.List;

public class Configuration {
    // Prétraitement par défaut
    private List<String> pretraiteurs = List.of("PretraiteurDecomposer","PretraiteurNormalisation","PretraiteurSoundex","PretraiteurMetaphone");
    // Indexation par défaut
    private String index = "Map";
    // Mesure de comparaison par défaut
    private String mesureComparison = "Levenshtein";
    // Paramètre de résultat par défaut
    private double seuil = 0.6;
    private int nbMax = 10;

    // Getters et setters
    public List<String> getPretraiteurs() { return this.pretraiteurs; }
    public void setPretraiteurs(List<String> pretraiteurs) { this.pretraiteurs = pretraiteurs; }

    public String getIndex() { return this.index; }
    public void setIndex(String index) { this.index = index; }

    public String getMesureComparison() { return this.mesureComparison; }
    public void setMesureComparison(String mesureComparison) { this.mesureComparison = mesureComparison; }

    public double getSeuil() { return this.seuil; }
    public void setSeuil(double seuil) { this.seuil = seuil; }

    public int getNbMax() { return this.nbMax; }
    public void setNbMax(int nbMax) { this.nbMax = nbMax; }
}

package MatchEngineCLI;

import java.util.ArrayList;
import java.util.List;

public class PretraiteurDecomposeur implements Pretraiteur {
    @Override
    public List<String> pretraiter(String nom) {
        List<String> resultat = new ArrayList<>();
        String[] parties = nom.split("\\s+");
        for (String partie : parties) {
             resultat.add(partie);
        }
        return resultat;
    }
}
package MatchEngineCLI;

import java.util.ArrayList;
import java.util.List;

public class PretraiteurDecomposeur implements Pretraiteur {
    @Override
    public List<String> pretraiter(String nom) {
        List<String> result = new ArrayList<>();
        String[] parties = nom.split("\\s+");
        for (String partie : parties) {
            if (!partie.isEmpty()) {
                result.add(partie);
            }
        }
        return result;
    }
}
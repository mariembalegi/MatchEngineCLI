package MatchEngineCLI;

import java.util.ArrayList;
import java.util.List;

public class Decomposeur implements Pretraiteur {
    @Override
    public List<Nom> pretraiter(String nom) {
        List<Nom> result = new ArrayList<>();
        String[] parties = nom.split(" ");
        for (int i = 0; i < parties.length; i++) {
            Nom nouveauNom = new Nom(String.valueOf(i+1), parties[i], List.of());
            result.add(nouveauNom);
        }
        return result;
    }
}
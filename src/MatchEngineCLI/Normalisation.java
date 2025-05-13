package MatchEngineCLI;

import java.text.Normalizer;
import java.util.List;

public class Normalisation implements Pretraiteur {
    @Override
    public List<String> pretraiter(String nom) {
        String nomTraite = Normalizer.normalize(nom, Normalizer.Form.NFD)
                .replaceAll("[\\p{M}]", "")
                .toLowerCase()
                .replaceAll("[^a-z]", "");
        return List.of(nomTraite);
    }
}
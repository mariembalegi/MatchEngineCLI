package MatchEngineCLI;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PretraiteurPhonetique implements Pretraiteur {
    private static final Map<String, String> REGLES_PHONETIQUES = new HashMap<>() {{
        put("ai", "ɛ");
        put("au", "o");
        put("ei", "ɛ");
        put("eu", "ø");
        put("ou", "u");
        put("oi", "wa");
        put("an", "ɑ̃");
        put("en", "ɑ̃");
        put("in", "ɛ̃");
        put("on", "ɔ̃");
        put("un", "œ̃");
        put("ch", "ʃ");
        put("gn", "ɲ");
        put("ph", "f");
        put("qu", "k");
    }};

    public List<String> pretraiter(String texte) {
        if (texte == null) {
            return new ArrayList<>();
        }
        texte = texte.toLowerCase().trim();
        StringBuilder motPhonetique = new StringBuilder();
        for (int i = 0; i < texte.length(); i++) {
            String phoneme = null;
            if (i < texte.length() - 1) {
                String deuxLettres = texte.substring(i, i + 2);
                phoneme = REGLES_PHONETIQUES.get(deuxLettres);
                if (phoneme != null) {
                    motPhonetique.append(phoneme);
                    i++;
                    continue;
                }
            }
            char caractere = texte.charAt(i);
            if (Character.isLetter(caractere)) {
                motPhonetique.append(caractere);
            }
        }
        List<String> resultat = new ArrayList<>();
        if (motPhonetique.length() > 0) {
            resultat.add(motPhonetique.toString());
        }
        return resultat;
    }
}
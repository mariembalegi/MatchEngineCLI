package MatchEngineCLI;

import java.util.ArrayList;
import java.util.List;

public class PretraiteurSoundex implements Pretraiteur {
    @Override
    public List<String> pretraiter(String nom) {
        String nomTraite = computeSoundex(nom);
        return List.of(nomTraite);
    }
    private String computeSoundex (String nom){
        nom = nom.toUpperCase().replaceAll("[^A-Z]", "");
        if (nom.isEmpty()) return "";
        StringBuilder soundex = new StringBuilder();
        soundex.append(nom.charAt(0));
        List<Integer> codes = new ArrayList<>(26);
        for (int i = 0; i < 26; i++) {
            codes.add(0);
        }
        codes.set('B' - 'A', 1); codes.set('P' - 'A', 1); codes.set('F' - 'A', 1);
        codes.set('V' - 'A', 1); codes.set('C' - 'A', 2); codes.set('S' - 'A', 2);
        codes.set('K' - 'A', 2); codes.set('G' - 'A', 2); codes.set('J' - 'A', 2);
        codes.set('Q' - 'A', 2); codes.set('X' - 'A', 2); codes.set('Z' - 'A', 2);
        codes.set('D' - 'A', 3); codes.set('T' - 'A', 3); codes.set('L' - 'A', 4);
        codes.set('M' - 'A', 5); codes.set('N' - 'A', 5); codes.set('R' - 'A', 6);
        int prevCode = -1;
        for (int i = 1; i < nom.length() && soundex.length() < 4; i++) {
            int code = codes.get(nom.charAt(i) - 'A');
            if (code != 0 && code != prevCode) {
                soundex.append(code);
            }
            prevCode = code;
        }
        while (soundex.length() < 4) {
            soundex.append('0');
        }
        return soundex.toString();
    }
}
package MatchEngineCLI;


import java.util.List;

public class Soundex implements Pretraiteur {
    @Override
    public List<Nom> pretraiter(String nom) {
        String nomTraite = computeSoundex(nom);
        return List.of(new Nom("1", nomTraite, List.of()));
    }

    private String computeSoundex(String nom) {
        if (nom == null || nom.isEmpty()) return "";

        nom = nom.toUpperCase().replaceAll("[^A-Z]", "");
        if (nom.isEmpty()) return "";

        StringBuilder soundex = new StringBuilder();
        soundex.append(nom.charAt(0));

        int[] codes = new int[26];
        codes['B' - 'A'] = codes['P' - 'A'] = codes['F' - 'A'] = codes['V' - 'A'] = 1;
        codes['C' - 'A'] = codes['S' - 'A'] = codes['K' - 'A'] = codes['G' - 'A'] =
                codes['J' - 'A'] = codes['Q' - 'A'] = codes['X' - 'A'] = codes['Z' - 'A'] = 2;
        codes['D' - 'A'] = codes['T' - 'A'] = 3;
        codes['L' - 'A'] = 4;
        codes['M' - 'A'] = codes['N' - 'A'] = 5;
        codes['R' - 'A'] = 6;

        int prevCode = -1;
        for (int i = 1; i < nom.length() && soundex.length() < 4; i++) {
            int code = codes[nom.charAt(i) - 'A'];
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
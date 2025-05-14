package MatchEngineCLI;



public class ComparateurNomsTraiteJaroWinkler implements Comparateur {
    @Override
    public double comparer(Nom nom1, Nom nom2) {
        double totalScore = 0;
        int count = 0;
        for (String s1 : nom1.getNomTraite()) {
            for (String s2 : nom2.getNomTraite()) {
                if (s1.equals(s2)) {
                    totalScore += 1.0;
                } else {
                    int len1 = s1.length(), len2 = s2.length();
                    if (len1 == 0 || len2 == 0) continue;
                    int matchDistance = Math.max(len1, len2) / 2 - 1;
                    boolean[] s1Matches = new boolean[len1];
                    boolean[] s2Matches = new boolean[len2];
                    int matches = 0;
                    for (int i = 0; i < len1; i++) {
                        int start = Math.max(0, i - matchDistance);
                        int end = Math.min(len2, i + matchDistance + 1);
                        for (int j = start; j < end; j++) {
                            if (s2Matches[j] || s1.charAt(i) != s2.charAt(j)) continue;
                            s1Matches[i] = true;
                            s2Matches[j] = true;
                            matches++;
                            break;
                        }
                    }
                    if (matches == 0) continue;
                    int transpositions = 0, k = 0;
                    for (int i = 0; i < len1; i++) {
                        if (!s1Matches[i]) continue;
                        while (!s2Matches[k]) k++;
                        if (s1.charAt(i) != s2.charAt(k)) transpositions++;
                        k++;
                    }
                    double jaro = ((matches / (double) len1) +
                            (matches / (double) len2) +
                            ((matches - transpositions / 2.0) / matches)) / 3.0;
                    int prefixLen = 0, maxPrefix = 4;
                    while (prefixLen < Math.min(s1.length(), s2.length()) &&
                            s1.charAt(prefixLen) == s2.charAt(prefixLen) &&
                            prefixLen < maxPrefix) {
                        prefixLen++;
                    }
                    double scalingFactor = 0.1;
                    totalScore += jaro + (prefixLen * scalingFactor * (1 - jaro));
                }
                count++;
            }
        }
        return count == 0 ? 0.0 : totalScore / count;
    }
}

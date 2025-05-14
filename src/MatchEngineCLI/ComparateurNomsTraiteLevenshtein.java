package MatchEngineCLI;

import java.util.List;

public class ComparateurNomsTraiteLevenshtein implements Comparateur {
    @Override
    public double comparer(Nom nom1, Nom nom2) {
        List<String> list1 = nom1.getNomTraite();
        List<String> list2 = nom2.getNomTraite();
        double total = 0.0;
        int count = 0;

        for (String s1 : list1) {
            double maxSim = 0.0;
            for (String s2 : list2) {
                int len1 = s1.length();
                int len2 = s2.length();
                int[][] dp = new int[len1 + 1][len2 + 1];

                // Initialisation du tableau
                for (int i = 0; i <= len1; i++) dp[i][0] = i;
                for (int j = 0; j <= len2; j++) dp[0][j] = j;

                // Calcul de la distance de Levenshtein
                for (int i = 1; i <= len1; i++) {
                    for (int j = 1; j <= len2; j++) {
                        int cost = s1.charAt(i - 1) == s2.charAt(j - 1) ? 0 : 1;
                        dp[i][j] = Math.min(
                                Math.min(dp[i - 1][j] + 1, dp[i][j - 1] + 1),
                                dp[i - 1][j - 1] + cost
                        );
                    }
                }

                // Calcul de la similarité
                int dist = dp[len1][len2];
                int maxLen = Math.max(len1, len2);
                double sim = maxLen == 0 ? 1.0 : 1.0 - ((double) dist / maxLen);

                // Mettre à jour maxSim si une similarité plus élevée est trouvée
                if (sim > maxSim) maxSim = sim;
            }
            total += maxSim;
            count++;
        }
        return total / count;
    }
}




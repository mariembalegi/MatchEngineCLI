package MatchEngineCLI;

public class ComparateurApproximatif implements Comparateur {

    @Override
    public double comparer(Nom nom1, Nom nom2) {
        
        if (nom1 == null || nom2 == null) {
            return 0.0;
        }

        String s1 = nom1.toString();
        String s2 = nom2.toString();

        
        if (s1.equals(s2)) {
            return 1.0;
        }

        
        int distance = levenshteinDistance(s1, s2);

        
        int maxLength = Math.max(s1.length(), s2.length());
        double score = 1.0 - ((double) distance / maxLength);

        return Math.max(0.0, score); 
    }


    private static int levenshteinDistance(String s1, String s2) {
        int[][] dp = new int[s1.length() + 1][s2.length() + 1];

        for (int i = 0; i <= s1.length(); i++) {
            dp[i][0] = i;
        }
        for (int j = 0; j <= s2.length(); j++) {
            dp[0][j] = j;
        }

        for (int i = 1; i <= s1.length(); i++) {
            for (int j = 1; j <= s2.length(); j++) {
                int cost = (s1.charAt(i - 1) == s2.charAt(j - 1)) ? 0 : 1;
                dp[i][j] = Math.min(
                    Math.min(dp[i - 1][j] + 1, dp[i][j - 1] + 1),
                    dp[i - 1][j - 1] + cost
                );
            }
        }

        return dp[s1.length()][s2.length()];
    }
}
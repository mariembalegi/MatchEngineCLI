package MatchEngineCLI;

import java.util.List;

public class ComparateurNomsTraiteExacte implements Comparateur {
        @Override

        public double comparer(Nom nom1, Nom nom2) {
            List<String> mots1 = nom1.nomTraite(), mots2 = nom2.nomTraite();
            int intersect = 0;
            for (String mot : mots1)
                if (mots2.contains(mot)) intersect++;
            int union = mots1.size() + mots2.size() - intersect;
            return union == 0 ? 0 : (double) intersect / union;
    }
}

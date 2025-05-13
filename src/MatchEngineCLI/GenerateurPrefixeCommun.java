package MatchEngineCLI;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GenerateurPrefixeCommun implements GenerateurCandidats {
    
    private static final int LONGUEUR_PREFIXE = 2;

    @Override
    public List<CoupleDeNoms> generer(List<Nom> liste1, List<Nom> liste2) {
        
        Map<String, List<Nom>> prefixeANoms = new HashMap<>();
        for (Nom nom : liste2) {
            String str = nom.toString();
            String prefixe = str.substring(0, Math.min(LONGUEUR_PREFIXE, str.length())).toLowerCase();
            
            prefixeANoms.computeIfAbsent(prefixe, k -> new ArrayList<>()).add(nom);
        }

        
        List<CoupleDeNoms> couples = new ArrayList<>();
        for (Nom nom1 : liste1) {
            String str1 = nom1.toString();
            String prefixe1 = str1.substring(0, Math.min(LONGUEUR_PREFIXE, str1.length())).toLowerCase();
            
            if (prefixeANoms.containsKey(prefixe1)) {
                for (Nom nom2 : prefixeANoms.get(prefixe1)) {
                    couples.add(new CoupleDeNoms(nom1, nom2));
                }
            }
        }
        
        return couples;
    }
}
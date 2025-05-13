import java.util.ArrayList;
import java.util.List;

public class GenerateurPrefixeCommun implements GenerateurCandidats {
    
    private static final int LONGUEUR_PREFIXE = 2; 

    @Override
    public List<CoupleDeNoms> generer(List<Nom> liste1, List<Nom> liste2) {
        List<CoupleDeNoms> couples = new ArrayList<>();
        
        for (Nom nom1 : liste1) {
            String str1 = nom1.toString();
            String prefixe1 = str1.substring(0, Math.min(LONGUEUR_PREFIXE, str1.length()));
            
            for (Nom nom2 : liste2) {
                String str2 = nom2.toString();
                String prefixe2 = str2.substring(0, Math.min(LONGUEUR_PREFIXE, str2.length()));
                
                if (prefixe1.equalsIgnoreCase(prefixe2)) {
                    couples.add(new CoupleDeNoms(nom1, nom2));
                }
            }
        }
        
        return couples;
    }
}
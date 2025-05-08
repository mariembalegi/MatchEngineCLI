import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GenerateurCouplesMemeTaille implements GenerateurDeCandidats {

    @Override
    public List<CoupleDeNoms> generer(List<Nom> liste1, List<Nom> liste2) {
        Map<Integer, List<Nom>> tailleANoms = new HashMap<>();
        for (Nom nom : liste2) {
            int taille = nom.toString().length();
            if (!tailleANoms.containsKey(taille)) {
            tailleANoms.put(taille, new ArrayList<>());
        }
        tailleANoms.get(taille).add(nom);
        }

        List<CoupleDeNoms> couples = new ArrayList<>();
        for (Nom nom1 : liste1) {
            int taille = nom1.toString().length();
            if (tailleANoms.containsKey(taille)) {
                for (Nom nom2 : tailleANoms.get(taille)) {
                    couples.add(new CoupleDeNoms(nom1, nom2));
                }
            }
        }

        return couples;
    }
}

/* 
public class GenerateurCouplesTailleApproximative implements GenerateurDeCandidats {
    

    private static final int TOLERANCE_TAILLE = 1;

    @Override
    public List<CoupleDeNoms> generer(List<Nom> liste1, List<Nom> liste2) {
        
        Map<Integer, List<Nom>> tailleANoms = new HashMap<>();
        for (Nom nom : liste2) {
            int taille = nom.toString().length();
            if (!tailleANoms.containsKey(taille)) {
            tailleANoms.put(taille, new ArrayList<>());
        }
        tailleANoms.get(taille).add(nom);
        }

        List<CoupleDeNoms> couples = new ArrayList<>();
        
        for (Nom nom1 : liste1) {
            int taille1 = nom1.toString().length();
            
   
            for (int i = -TOLERANCE_TAILLE; i <= TOLERANCE_TAILLE; i++) {
                int tailleCible = taille1 + i;
                
                if (tailleANoms.containsKey(tailleCible)) {
                    for (Nom nom2 : tailleANoms.get(tailleCible)) {
                        couples.add(new CoupleDeNoms(nom1, nom2));
                    }
                }
            }
        }

        return couples;
    }
}
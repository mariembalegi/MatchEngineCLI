
import MatchEngineCLI.*;


import java.util.List;

public class Main {
    public static void main(String[] args) {
        Pretraiteur pretraiteur = new PretraiteurPhonetique() ;
        Pretraiteur pretraiteur2 = new PretraiteurNormalisation();
        String nom = "á€¦á€¸á€±á€žá€¬á€„á€¹á€¸";
        List<String> phonetiques = pretraiteur.pretraiter(nom);
        System.out.println(phonetiques);
        System.out.println(pretraiteur2.pretraiter(phonetiques.get(0)));

    }
}

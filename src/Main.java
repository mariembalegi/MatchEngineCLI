
import MatchEngineCLI.*;


import java.text.Normalizer;
import java.util.List;
import java.util.ArrayList;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Pretraiteur pretraiteur = new PretraiteurPhonetique() ;
        Pretraiteur pretraiteur2 = new Normalisation();
        String nom = "á€¦á€¸á€±á€žá€¬á€„á€¹á€¸";
        List<String> phonetiques = pretraiteur.pretraiter(nom);
        System.out.println(phonetiques);
        System.out.println(pretraiteur2.pretraiter(phonetiques.get(0)));

    }
}

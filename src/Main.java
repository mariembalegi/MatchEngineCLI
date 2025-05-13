import MatchEngineCLI.Soundex;
import MatchEngineCLI.Pretraiteur;

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Pretraiteur Soundex = new Soundex();
        String nom= "Jean";
        List<String> resultat = Soundex.pretraiter(nom);
        System.out.println("Nom : " + nom + " -> Codes Soundex : " + resultat);


    }
}

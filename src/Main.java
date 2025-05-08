import MatchEngineCLI.*;

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        List<Pretraiteur> pretraiteurs= new ArrayList<>();
        Comparateur comparateur = new ComparateurExacte() ;
        Selectionneur selectionneur = new SelectionneurSansResultat() ;
        GenerateurCandidats generateur = new GenerateurTousCouples();

        Scanner scanner = new Scanner(System.in);
        System.out.print("Entrez votre nom : ");
        Nom nomRecherche = new Nom("123", new Scanner(System.in).nextLine(), List.of(new Scanner(System.in).nextLine()));
        scanner.close();

        List<Nom> noms = List.of(new Nom("159","ahmedbenahmed",List.of("ahmedbenahmed")), new Nom("147","marwenbenmarwen",List.of("marwenbenmarwen")));

        MoteurDeMatching moteurDeMatching = new MoteurDeMatching(pretraiteurs,comparateur,selectionneur,generateur);
        List<Nom> res=moteurDeMatching.rechercher(nomRecherche,noms);
        System.out.println(res.getFirst().getNomNonTraite());
        System.out.println(res.get(1).getNomNonTraite());
        System.out.println(res.size());




    }
}

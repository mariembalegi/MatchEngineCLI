package MatchEngineCLI;


import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class CSVRecuperateur implements Recuperateur {
    private final String filePath;
    private final List<Pretraiteur> pretraiteurs;

    public CSVRecuperateur(String filePath, List<Pretraiteur> pretraiteurs) {
        this.filePath = filePath;
        this.pretraiteurs = pretraiteurs != null ? pretraiteurs : new ArrayList<>();
    }

    @Override
    public List<Nom> recuperer() {
        List<Nom> result = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 2) {
                    String id = parts[0];
                    String nomNonTraite = parts[1];
                    List<Nom> nomTraite = new ArrayList<>();
                    List<Nom> currentNoms = List.of(new Nom(id, nomNonTraite, List.of()));
                    for (Pretraiteur pretraiteur : pretraiteurs) {
                        List<Nom> nextNoms = new ArrayList<>();
                        for (Nom nom : currentNoms) {
                            nextNoms.addAll(pretraiteur.pretraiter(nom.nomNonTraite()));
                        }
                        currentNoms = nextNoms;
                    }
                    nomTraite = currentNoms;
                    result.add(new Nom(id, nomNonTraite, nomTraite));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
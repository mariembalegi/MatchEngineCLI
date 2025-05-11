package MatchEngineCLI;



import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.FileList;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class DriveRecuperateur implements Recuperateur {
    private final Drive driveService;
    private final List<Pretraiteur> pretraiteurs;

    public DriveRecuperateur(Drive driveService, List<Pretraiteur> pretraiteurs) {
        this.driveService = driveService;
        this.pretraiteurs = pretraiteurs != null ? pretraiteurs : new ArrayList<>();
    }

    @Override
    public List<Nom> recuperer() {
        List<Nom> result = new ArrayList<>();
        try {
            FileList fileList = driveService.files().list()
                    .setQ("mimeType='text/csv'")
                    .setFields("files(id, name)")
                    .execute();

            for (File file : fileList.getFiles()) {
                try (BufferedReader reader = new BufferedReader(
                        new InputStreamReader(driveService.files().get( file.getId()).executeMediaAsInputStream()))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        String[] parts = line.split(",");
                        if (parts.length >= 2) {
                            String id = parts[0];
                            String nomNonTraite = parts[1];
                            List<Nom> nomTraite = new ArrayList<>();

                            // Initialisation avec le nomNonTraite
                            List<Nom> currentNoms = List.of(new Nom(id, nomNonTraite, List.of()));

                            // Application séquentielle des prétraiteurs
                            for (Pretraiteur pretraiteur : pretraiteurs) {
                                List<Nom> nextNoms = new ArrayList<>();
                                for (Nom nom : currentNoms) {
                                    nextNoms.addAll(pretraiteur.pretraiter(nom.nomNonTraite()));
                                }
                                currentNoms = nextNoms; // Mise à jour pour le prochain prétraiteur
                            }

                            nomTraite = currentNoms; // Résultat final
                            result.add(new Nom(id, nomNonTraite, nomTraite));
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
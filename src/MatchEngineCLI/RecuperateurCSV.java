package MatchEngineCLI;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;


public class RecuperateurCSV implements Recuperateur {
    private String filePath;

    public RecuperateurCSV(String filePath) {
        this.filePath = filePath;
    }
    @Override
    public List<Nom> recuperer() {
        try (Stream<String> lines = Files.lines(Paths.get(filePath))) {
            return lines.skip(1) // Skip the header line
                    .map(line -> line.split(","))
                    .filter(values -> values.length >= 2)
                    .map(values -> new Nom(values[0].trim(), values[1].trim(),List.of(values[1].trim())))
                    .toList();
        } catch (IOException e) {
            e.printStackTrace();
            return List.of();
        }
    }
}
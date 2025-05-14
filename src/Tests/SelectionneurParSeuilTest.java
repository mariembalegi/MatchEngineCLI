package Tests;
import MatchEngineCLI.*;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SelectionneurParSeuilTest {@Test
void selectionner_filtreNomsEntreprises() {
    // Arrange
    List<CoupleAvecScore> couples = List.of(
            new CoupleAvecScore(
                    new Nom("1", "Google LLC", List.of("google llc")),
                    new Nom("2", "Google Inc.", List.of("google inc")),
                    0.95),
            new CoupleAvecScore(
                    new Nom("1", "Google LLC", List.of("google llc")),
                    new Nom("3", "Googel", List.of("googel")),
                    0.80),
            new CoupleAvecScore(
                    new Nom("1", "Google LLC", List.of("google llc")),
                    new Nom("4", "Amazon Inc.", List.of("amazon inc")),
                    0.20),
            new CoupleAvecScore(
                    new Nom("1", "Google LLC", List.of("google llc")),
                    new Nom("5", "Alphabet Inc.", List.of("alphabet inc")),
                    0.40)
    );
    Selectionneur selectionneur = new SelectionneurParSeuil(0.75);

    // Act
    List<CoupleAvecScore> result = selectionneur.selectionner(couples);

    // Assert
    assertEquals(2, result.size());
    assertTrue(result.stream().anyMatch(c -> c.nom2().getNomNonTraite().equals("Google Inc.")));
    assertTrue(result.stream().anyMatch(c -> c.nom2().getNomNonTraite().equals("Googel")));
}
}

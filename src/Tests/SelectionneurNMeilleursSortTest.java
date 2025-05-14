package Tests;
import MatchEngineCLI.*;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
public class SelectionneurNMeilleursSortTest {
    @Test
    void selectionner_trieNomsInternationaux() {
        // Arrange
        List<CoupleAvecScore> couples = List.of(
                new CoupleAvecScore(
                        new Nom("1", "José García", List.of("jose garcia")),
                        new Nom("2", "Jose Garcia", List.of("jose garcia")),
                        0.99), // version sans accent
                new CoupleAvecScore(
                        new Nom("1", "José García", List.of("jose garcia")),
                        new Nom("3", "Joseph Garcia", List.of("joseph garcia")),
                        0.75), // version anglicisée
                new CoupleAvecScore(
                        new Nom("1", "José García", List.of("jose garcia")),
                        new Nom("4", "Juan García", List.of("juan garcia")),
                        0.60), // même nom de famille
                new CoupleAvecScore(
                        new Nom("1", "José García", List.of("jose garcia")),
                        new Nom("5", "José Martínez", List.of("jose martinez")),
                        0.85)  // même prénom
        );
        Selectionneur selectionneur = new SelectionneurNMeilleursSort(4);

        // Act
        List<CoupleAvecScore> result = selectionneur.selectionner(couples);

        // Assert
        assertEquals(4, result.size());
        assertEquals("Jose Garcia", result.get(0).nom2().getNomNonTraite());
        assertEquals("José Martínez", result.get(1).nom2().getNomNonTraite());
        assertEquals("Joseph Garcia", result.get(2).nom2().getNomNonTraite());
        assertEquals("Juan García", result.get(3).nom2().getNomNonTraite());
    }
}

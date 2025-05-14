package Tests;

import MatchEngineCLI.*;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SelectionneurNMeilleursMaxTest {
    @Test
    void selectionner_top3AvecDoublonsScores() {
        // Arrange - Deux couples avec le même score de 0.8
        List<CoupleAvecScore> couples = List.of(
                new CoupleAvecScore(
                        new Nom("1", "Martin Dubois", List.of("martin dubois")),
                        new Nom("2", "Martin Dubois", List.of("martin dubois")),
                        1.0), // match exact
                new CoupleAvecScore(
                        new Nom("1", "Martin Dubois", List.of("martin dubois")),
                        new Nom("3", "Martine Dubois", List.of("martine dubois")),
                        0.8), // féminisation
                new CoupleAvecScore(
                        new Nom("1", "Martin Dubois", List.of("martin dubois")),
                        new Nom("4", "Martin Du Bois", List.of("martin du bois")),
                        0.8), // orthographe similaire
                new CoupleAvecScore(
                        new Nom("1", "Martin Dubois", List.of("martin dubois")),
                        new Nom("5", "M. Dubois", List.of("m dubois")),
                        0.6)  // version abrégée
        );
        Selectionneur selectionneur = new SelectionneurNMeilleursMax(3);

        // Act
        List<CoupleAvecScore> result = selectionneur.selectionner(couples);

        // Assert
        assertEquals(3, result.size());
        assertEquals(1.0, result.get(0).score()); // match exact en premier

        // Les deux suivants peuvent être dans n'importe quel ordre car même score
        List<Double> scoresRestants = result.stream().skip(1).map(CoupleAvecScore::score).toList();
        assertTrue(scoresRestants.containsAll(List.of(0.8, 0.8)));
    }

    @Test
    void selectionner_gererListePlusPetiteQueN() {
        // Arrange - Seulement 2 éléments alors qu'on demande top 3
        List<CoupleAvecScore> couples = List.of(
                new CoupleAvecScore(
                        new Nom("1", "Élisabeth Lambert", List.of("elisabeth lambert")),
                        new Nom("2", "Elisabeth Lambert", List.of("elisabeth lambert")),
                        0.95),
                new CoupleAvecScore(
                        new Nom("1", "Élisabeth Lambert", List.of("elisabeth lambert")),
                        new Nom("3", "E. Lambert", List.of("e lambert")),
                        0.7)
        );
        Selectionneur selectionneur = new SelectionneurNMeilleursMax(3);

        // Act
        List<CoupleAvecScore> result = selectionneur.selectionner(couples);

        // Assert
        assertEquals(2, result.size()); // Ne retourne que les 2 disponibles
        assertEquals(0.95, result.get(0).score());
        assertEquals(0.7, result.get(1).score());
    }

    @Test
    void selectionner_gererListeVide() {
        // Arrange
        List<CoupleAvecScore> couples = List.of();
        Selectionneur selectionneur = new SelectionneurNMeilleursMax(3);

        // Act
        List<CoupleAvecScore> result = selectionneur.selectionner(couples);

        // Assert
        assertTrue(result.isEmpty());
    }



}
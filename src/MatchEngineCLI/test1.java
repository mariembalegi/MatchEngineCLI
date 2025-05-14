package MatchEngineCLI;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class test1 {

    @Test
    public void testSelectionnerRetourneCopieIdentique() {
        // Arrange

        CoupleAvecScore couple1 = new CoupleAvecScore(new Nom("123","Alice",List.of("Alice")), new Nom("123","Asma",List.of("Asma")), 0.8);
        CoupleAvecScore couple2 = new CoupleAvecScore(new Nom("123","Charlie",List.of("Charlie")), new Nom("123","Chasma",List.of("Chasma")), 0.6);
        List<CoupleAvecScore> input = List.of(couple1, couple2);

        SelectionneurSansResultat selectionneur = new SelectionneurSansResultat();

        // Act
        List<CoupleAvecScore> resultat = selectionneur.selectionner(input);

        // Assert
        assertEquals(input.size(), resultat.size());

        for (int i = 0; i < input.size(); i++) {
            CoupleAvecScore original = input.get(i);
            CoupleAvecScore copie = resultat.get(i);

            assertEquals(original.nom1(), copie.nom1());
            assertEquals(original.nom2(), copie.nom2());
            assertEquals(original.score(), copie.score());
            // S'assurer que ce sont des instances différentes
            assertNotSame(original, copie);
        }
    }
}
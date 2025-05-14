package TestLocale;
import MatchEngineCLI.*;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PretraiteurDecomposeurTest {

    @Test
    public void testPretraiter() {
        // Exemple de chaîne à tester
        String chaine = "DEMIDOVICH VASILIJ";

        // Créer une instance du prétraiteur
        PretraiteurDecomposeur pretraiteur = new PretraiteurDecomposeur();

        // Appliquer la méthode de décomposition
        List<String> resultat = pretraiteur.pretraiter(chaine);

        // Résultat attendu : ["DEMIDOVICH", "VASILIJ"]
        List<String> resultatAttendu = List.of("DEMIDOVICH", "VASILIJ");

        // Vérification du résultat
        assertEquals(resultatAttendu, resultat, "La décomposition du nom est incorrecte.");
    }
}
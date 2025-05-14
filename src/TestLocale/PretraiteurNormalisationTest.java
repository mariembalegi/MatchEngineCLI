package TestLocale;

import MatchEngineCLI.*;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PretraiteurNormalisationTest {

    @Test
    public void testPretraiter() {

        String chaine = "DEMIDOVICH VASILIJ";

        PretraiteurNormalisation pretraiteur = new PretraiteurNormalisation();

        List<String> resultat = pretraiteur.pretraiter(chaine);

        List<String> resultatAttendu = List.of("demidovich vasilij");

        assertEquals(resultatAttendu, resultat, "La normalisation du nom est incorrecte.");
    }
}
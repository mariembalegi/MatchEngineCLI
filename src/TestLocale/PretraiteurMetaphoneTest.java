package TestLocale;

import MatchEngineCLI.*;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PretraiteurMetaphoneTest {

    @Test
    public void testPretraiter() {
        // Exemple de chaîne à tester
        String chaine = "Geoffrey";

        // Créer une instance du prétraiteur Metaphone
        PretraiteurMetaphone pretraiteur = new PretraiteurMetaphone();

        // Appliquer la méthode de prétraitement
        List<String> resultat = pretraiteur.pretraiter(chaine);

        // Résultat attendu après application de Metaphone
        List<String> resultatAttendu = List.of("JF");

        // Vérification du résultat
        assertEquals(resultatAttendu, resultat, "Le code Metaphone est incorrect.");
    }}
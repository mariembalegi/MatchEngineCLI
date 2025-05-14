package MatchEngineCLI;

import java.util.ArrayList;
import java.util.List;


public class PretraiteurMetaphone implements Pretraiteur{
    @Override
    public List<String> pretraiter(String nom) {
        List<String> result = new ArrayList<>();
        // Nettoyer l'entrée : majuscules, supprimer les caractères non alphabétiques
        String cleanedInput = nom.trim().toUpperCase().replaceAll("[^A-Z]", "");
        if (cleanedInput.isEmpty()) {
            return result;
        }

        StringBuilder code = new StringBuilder();
        int i = 0;

        // Garder la première lettre (même si c'est une voyelle)
        if (cleanedInput.length() > 0) {
            code.append(cleanedInput.charAt(0));
            i++;
        }

        // Traiter le reste de la chaîne
        while (i < cleanedInput.length()) {
            char current = cleanedInput.charAt(i);
            char next = (i + 1 < cleanedInput.length()) ? cleanedInput.charAt(i + 1) : '\0';

            switch (current) {
                case 'B':
                    code.append('B');
                    break;
                case 'C':
                    if (next == 'H') {
                        code.append('X');
                        i++;
                    } else if (next == 'I' || next == 'E' || next == 'Y') {
                        code.append('S');
                        i++;
                    } else {
                        code.append('K');
                    }
                    break;
                case 'D':
                    if (next == 'G' && i + 2 < cleanedInput.length() &&
                            (cleanedInput.charAt(i + 2) == 'E' || cleanedInput.charAt(i + 2) == 'Y' || cleanedInput.charAt(i + 2) == 'I')) {
                        code.append('J');
                        i++;
                    } else {
                        code.append('T');
                    }
                    break;
                case 'F':
                    code.append('F');
                    break;
                case 'G':
                    if (next == 'H' || next == 'I' || next == 'E' || next == 'Y') {
                        code.append('J');
                        i++;
                    } else {
                        code.append('K');
                    }
                    break;
                case 'H':
                    if (i > 0 && (next == '\0' || "AEIOU".indexOf(next) == -1)) {
                        // Ignorer H si suivi d'une consonne ou en fin de mot
                    } else {
                        code.append('H');
                    }
                    break;
                case 'J':
                    code.append('J');
                    break;
                case 'K':
                    code.append('K');
                    break;
                case 'L':
                    code.append('L');
                    break;
                case 'M':
                    code.append('M');
                    break;
                case 'N':
                    code.append('N');
                    break;
                case 'P':
                    if (next == 'H') {
                        code.append('F');
                        i++;
                    } else {
                        code.append('P');
                    }
                    break;
                case 'Q':
                    code.append('K');
                    break;
                case 'R':
                    code.append('R');
                    break;
                case 'S':
                    if (next == 'H') {
                        code.append('X');
                        i++;
                    } else if (next == 'I' && i + 2 < cleanedInput.length() &&
                            cleanedInput.charAt(i + 2) == 'O') {
                        code.append('X');
                        i += 2;
                    } else {
                        code.append('S');
                    }
                    break;
                case 'T':
                    if (next == 'I' && i + 2 < cleanedInput.length() &&
                            cleanedInput.charAt(i + 2) == 'O') {
                        code.append('X');
                        i += 2;
                    } else if (next == 'H') {
                        code.append('0');
                        i++;
                    } else {
                        code.append('T');
                    }
                    break;
                case 'V':
                    code.append('F');
                    break;
                case 'W':
                    if (next == 'H' || "AEIOU".indexOf(next) != -1) {
                        code.append('W');
                    }
                    break;
                case 'X':
                    code.append('S');
                    break;
                case 'Y':
                    if ("AEIOU".indexOf(next) != -1) {
                        code.append('Y');
                    }
                    break;
                case 'Z':
                    code.append('S');
                    break;
                default:
                    // Ignorer les voyelles (A, E, I, O, U) sauf si elles sont nécessaires
                    break;
            }
            i++;
        }

        // Supprimer les voyelles après la première lettre, sauf dans certains cas
        StringBuilder finalCode = new StringBuilder();
        finalCode.append(code.charAt(0)); // Garder la première lettre
        for (int j = 1; j < code.length(); j++) {
            char c = code.charAt(j);
            if ("AEIOU".indexOf(c) == -1 || c == 'A' && j == 1) {
                finalCode.append(c);
            }
        }

        // Limiter à 4 caractères
        String metaphoneCode = finalCode.length() > 4 ? finalCode.substring(0, 4) : finalCode.toString();
        if (!metaphoneCode.isEmpty()) {
            result.add(metaphoneCode);
        }

        return result;
    }
}

package MatchEngineCLI;

import java.util.List;
public interface Selectionneur {
    List<CoupleDeNoms> selectionner(List<CoupleAvecScore> resultatsAvecScore);
    }


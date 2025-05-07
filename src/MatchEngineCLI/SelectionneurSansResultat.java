package MatchEngineCLI;
import java.util.List;

public class SelectionneurSansResultat implements Selectionneur {

    @Override
    public List<List<Object>> selectionner(List<List<Object>> resultatsAvecScore) {
        return resultatsAvecScore;
    }
}



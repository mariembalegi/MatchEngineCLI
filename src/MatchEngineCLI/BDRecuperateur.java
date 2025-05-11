package MatchEngineCLI;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BDRecuperateur implements Recuperateur {
    private final String url;
    private final String user;
    private final String password;
    private final List<Pretraiteur> pretraiteurs;

    public BDRecuperateur(String url, String user, String password, List<Pretraiteur> pretraiteurs) {
        this.url = url;
        this.user = user;
        this.password = password;
        this.pretraiteurs = pretraiteurs != null ? pretraiteurs : new ArrayList<>();
    }

    @Override
    public List<Nom> recuperer() {
        List<Nom> result = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(url, user, password);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT id, nom FROM noms_table")) {

            while (rs.next()) {
                String id = rs.getString("id");
                String nomNonTraite = rs.getString("nom");
                List<Nom> nomTraite = new ArrayList<>();
                List<Nom> currentNoms = List.of(new Nom(id, nomNonTraite, List.of()));
                for (Pretraiteur pretraiteur : pretraiteurs) {
                    List<Nom> nextNoms = new ArrayList<>();
                    for (Nom nom : currentNoms) {
                        nextNoms.addAll(pretraiteur.pretraiter(nom.nomNonTraite()));
                    }
                    currentNoms = nextNoms;
                }
                nomTraite = currentNoms;
                result.add(new Nom(id, nomNonTraite, nomTraite));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
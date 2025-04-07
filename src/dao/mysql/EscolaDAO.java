package dao.mysql;

import model.Escola;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EscolaDAO {
    private Connection connection;

    public EscolaDAO(Connection connection) {
        this.connection = connection;
    }

    public void crearEscola(Escola escola) throws SQLException {
        String query = "INSERT INTO escola (nom, poblacio, acces, num_vies, dificultat, regulacions) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, escola.getNom());
            stmt.setString(2, escola.getPoblacio());
            stmt.setString(3, escola.getAcces());
            stmt.setInt(4, escola.getNum_vies());
            stmt.setString(5, escola.getDificultat());
            stmt.setString(6, escola.getRegulacions());
            stmt.executeUpdate();
        }
    }

    public List<Escola> obtenerEscoles() throws SQLException {
        String query = "SELECT * FROM escoles";
        List<Escola> escoles = new ArrayList<>();
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Escola escola = new Escola(
                        rs.getString("nom"),
                        rs.getString("poblacio"),
                        rs.getString("acces"),
                        rs.getInt("num_vies"),
                        rs.getString("dificultat"),
                        rs.getString("regulacions")
                );
                escoles.add(escola);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return escoles;
    }
}

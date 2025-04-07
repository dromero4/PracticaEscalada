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
        String query = "INSERT INTO escoles (id, num_vies, nom, acces, poblacio, dificultat, regulacions) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, escola.getId());
            stmt.setInt(2, escola.getNum_vies());
            stmt.setString(3, escola.getNom());
            stmt.setString(4, escola.getAcces());
            stmt.setString(5, escola.getPoblacio());
            stmt.setString(6, escola.getDificultat());
            stmt.setString(7, escola.getRegulacions());
            stmt.executeUpdate();
        }
    }

    public List<Escola> obtenerEscoles() throws SQLException {
        String query = "SELECT * FROM escoles";
        List<Escola> escoles = new ArrayList<>();
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Escola escola = new Escola(
                        rs.getInt("id"),
                        rs.getInt("num_vies"),
                        rs.getString("nom"),
                        rs.getString("acces"),
                        rs.getString("poblacio"),
                        rs.getString("dificultat"),
                        rs.getString("regulacions")
                );
                escoles.add(escola);
            }
        }
        return escoles;
    }
}

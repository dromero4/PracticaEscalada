package dao.mysql;

import model.Via;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ViaDAO {
    private Connection connection;

    public ViaDAO(Connection connection) {
        this.connection = connection;
    }

    public void crearVia(Via via) throws SQLException {
        String query = "INSERT INTO vias (llargada, id_dificultat, id_escalador, id_escola, id_sector, nom, orientacio, estat, tipus_roca, estil) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setInt(2, via.getLlargada());
            stmt.setInt(3, via.getId_dificultat());
            stmt.setInt(4, via.getId_escalador());
            stmt.setInt(5, via.getId_escola());
            stmt.setInt(6, via.getId_sector());
            stmt.setString(7, via.getNom());
            stmt.setString(8, via.getOrientacio());
            stmt.setString(9, via.getEstat());
            stmt.setString(10, via.getTipus_roca());
            stmt.setString(11, via.getEstil());
            stmt.executeUpdate();
        }
    }

    public List<Via> obtenerVias() throws SQLException {
        String query = "SELECT * FROM vias";
        List<Via> vias = new ArrayList<>();
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Via via = new Via(
                        rs.getInt("llargada"),
                        rs.getInt("id_dificultat"),
                        rs.getInt("id_escalador"),
                        rs.getInt("id_escola"),
                        rs.getInt("id_sector"),
                        rs.getString("nom"),
                        rs.getString("orientacio"),
                        rs.getString("estat"),
                        rs.getString("tipus_roca"),
                        rs.getString("estil")
                );
                vias.add(via);
            }
        }
        return vias;
    }
}

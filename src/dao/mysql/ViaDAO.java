package dao.mysql;

import model.Escola;
import model.Via;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ViaDAO implements DAO<Via, Integer>{ //IMPLEMENTS DAO /IMPORTANTE
    private Connection connection;

    public ViaDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void inserir(Via via) {
        String query = "INSERT INTO via (nom, llargada, id_dificultat, orientacio, estat, id_escola, id_sector, tipus_roca, id_escalador, estil) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, via.getNom());

            stmt.setInt(2, via.getLlargada());
            stmt.setInt(3, via.getId_dificultat());
            stmt.setString(4, via.getOrientacio());
            stmt.setString(5, via.getEstat());
            stmt.setInt(6, via.getId_escola());
            stmt.setInt(7, via.getId_sector());
            stmt.setString(8, via.getTipus_roca());
            stmt.setInt(9, via.getId_escalador());
            stmt.setString(10, via.getEstil());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void modificar(Via via) {
        System.out.println("Not yet implemented");

    }

    @Override
    public void eliminar(Via via) throws Exception {
        String query = "DELETE FROM via WHERE id = ?";

        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1, via.getId());

            int rows = stmt.executeUpdate();

            if(rows == 0) throw new Exception("No s'ha trobat cap via amb ID: " + via.getId());
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public List<Via> obtenirTots() {
        String query = "SELECT * FROM via";
        List<Via> vias = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Via via = new Via(
                        rs.getString("nom"),
                        rs.getInt("llargada"),
                        rs.getInt("id_dificultat"),
                        rs.getString("orientacio"),
                        rs.getString("estat"),
                        rs.getInt("id_escola"),
                        rs.getInt("id_sector"),
                        rs.getString("tipus_roca"),
                        rs.getInt("id_escalador"),
                        rs.getString("estil")
                );
                vias.add(via);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return vias;
    }

    @Override
    public Via obtenir(Integer id) {
        String query = "SELECT * FROM via WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            // Establecer el parámetro `id` en el PreparedStatement
            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Via(
                            rs.getString("nom"),
                            rs.getInt("llargada"),
                            rs.getInt("id_dificultat"),
                            rs.getString("orientacio"),
                            rs.getString("estat"),
                            rs.getInt("id_escola"),
                            rs.getInt("id_sector"),
                            rs.getString("tipus_roca"),
                            rs.getInt("id_escalador"),
                            rs.getString("estil")
                    );

                } else {
                    throw new RuntimeException("No s'ha trobat cap via amb l'ID: " + id);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener la via con ID " + id, e);
        }
    }

    @Override
    public int obtenirID(String nom) throws Exception {
        String query = "SELECT id FROM via WHERE nom = ?";

        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, nom);

            try {
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    return rs.getInt("id");
                } else {
                    throw new RuntimeException("No s'ha trobat cap escola amb el nom '" + nom + "'.");
                }
            } catch (SQLException e){
                throw new RuntimeException(e);
            }
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }
}

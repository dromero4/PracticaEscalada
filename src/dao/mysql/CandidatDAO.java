package dao.mysql;

import model.Candidat;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CandidatDAO implements DAO<Candidat, Long> {
    private Connection connection;

    public CandidatDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void inserir(Candidat c) {
        String sql = "INSERT INTO Candidats (nom, edat) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, c.getNom());
            stmt.setInt(2, c.getEdat());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void modificar(Candidat c) {
        String sql = "UPDATE Candidats SET nom = ?, edat = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, c.getNom());
            stmt.setInt(2, c.getEdat());
            stmt.setLong(3, c.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void eliminar(Candidat c) {
        String sql = "DELETE FROM Candidats WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, c.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Candidat> obtenirTots() {
        String sql = "SELECT * FROM Candidats";
        List<Candidat> candidats = new ArrayList<>();
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Long id = rs.getLong("id");
                String nom = rs.getString("nom");
                int edat = rs.getInt("edat");
                candidats.add(new Candidat(id, nom, edat));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return candidats;
    }

    @Override
    public Candidat obtenir(Long id) {
        String sql = "SELECT * FROM Candidats WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String nom = rs.getString("nom");
                int edat = rs.getInt("edat");
                return new Candidat(id, nom, edat);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}

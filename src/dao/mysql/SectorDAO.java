package dao.mysql;

import model.Sector;
import model.Via;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SectorDAO implements DAO<Sector, Integer>{
    private Connection connection;

    public SectorDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void inserir(Sector sector) {
        String query = "INSERT INTO sector (nom, coordenades, acces, num_vies, dificultat, regulacions, id_escola) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, sector.getNom());
            stmt.setString(2, sector.getCoordenades());
            stmt.setString(3, sector.getAcces());
            stmt.setInt(4, sector.getNum_vies());
            stmt.setString(5, sector.getDificultat());
            stmt.setString(6, sector.getRegulacions());
            stmt.setInt(7, sector.getId_escola());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void modificar(Sector sector) {
        System.out.println("Not yet implemented");
    }

    @Override
    public void eliminar(Sector sector) {
        System.out.println("Not yet implemented ");
    }

    @Override
    public List<Sector> obtenirTots() {
        String query = "SELECT * FROM sector";
        List<Sector> sectores = new ArrayList<>();
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Sector sector = new Sector(
                        rs.getString("nom"),
                        rs.getString("coordenades"),
                        rs.getString("acces"),
                        rs.getInt("num_vies"),
                        rs.getString("dificultat"),
                        rs.getString("regulacions"),
                        rs.getInt("id_escola")
                );
                sectores.add(sector);
            }
        } catch (Exception error){
            throw new RuntimeException(error);
        }
        return sectores;
    }

    @Override
    public Sector obtenir(Integer id) {
        String query = "SELECT * FROM sector WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            // Establecer el parámetro `id` en el PreparedStatement
            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Sector(
                            rs.getString("nom"),
                            rs.getString("coordenades"),
                            rs.getString("acces"),
                            rs.getInt("num_vies"),
                            rs.getString("dificultat"),
                            rs.getString("regulacions"),
                            rs.getInt("id_escola")
                    );

                } else {
                    throw new RuntimeException("No s'ha trobat cap sector amb l'ID: " + id);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener el sector con ID " + id, e);
        }
    }

    @Override
    public int obtenirID(String nom) throws Exception {
        String query = "SELECT id FROM sector WHERE nom = ?";

        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, nom);

            try {
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    return rs.getInt("id");
                } else {
                    throw new RuntimeException("No s'ha trobat cap sector amb el nom '" + nom + "'.");
                }
            } catch (SQLException e){
                throw new RuntimeException(e);
            }
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }
}

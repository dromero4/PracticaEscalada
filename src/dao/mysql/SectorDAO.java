package dao.mysql;

import model.Escola;
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
        String sql = "UPDATE sector SET nom = ?, coordenades = ?, acces = ?, num_vies = ?, dificultat = ?, regulacions = ?, id_escola = ? WHERE id = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, sector.getNom());
            ps.setString(2, sector.getCoordenades());
            ps.setString(3, sector.getAcces());
            ps.setInt(4, sector.getNum_vies());
            ps.setString(5, sector.getDificultat());
            ps.setString(6, sector.getRegulacions());
            ps.setInt(7, sector.getId_escola());
            ps.setInt(8, sector.getId());

            int filesAfectades = ps.executeUpdate();

            if (filesAfectades == 0) {
                System.out.println("No s'ha modificat cap registre. Comprova que el nom sigui correcte.");
            }
        } catch (SQLException e) {
            System.out.println("Error al modificar el sector: " + e.getMessage());
        }
    }

    @Override
    public void eliminar(Sector sector) throws Exception {
        String queryVia = "DELETE FROM via WHERE id_sector = ?";
        String querySector = "DELETE FROM sector WHERE id = ?";

        try {
            // Primero, eliminamos las filas en 'via' que están relacionadas con este 'sector'
            PreparedStatement stmtVia = connection.prepareStatement(queryVia);
            stmtVia.setInt(1, sector.getId());
            int rowsVia = stmtVia.executeUpdate();

            // Verificamos si se han eliminado filas en 'via'
            if (rowsVia > 0) {
                System.out.println("S'han eliminat " + rowsVia + " vies relacionades amb aquest sector.");
            } else {
                System.out.println("No s'han trobat vies relacionades amb aquest sector.");
            }

            // Ahora, eliminamos el sector
            PreparedStatement stmtSector = connection.prepareStatement(querySector);
            stmtSector.setInt(1, sector.getId());
            int rowsSector = stmtSector.executeUpdate();

            if (rowsSector == 0) {
                throw new Exception("No s'ha trobat cap sector amb ID: " + sector.getId());
            } else {
                System.out.println("Sector eliminat correctament.");
            }
        } catch (Exception e) {
            throw new Exception("Error al eliminar el sector: " + e.getMessage(), e);
        }
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

    public List<Sector> sectorsVies(int nVies) {
        String query = "SELECT * FROM sector WHERE num_vies > ?";
        List<Sector> sectors = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            // Establecer el parámetro `nVies` en el PreparedStatement
            stmt.setInt(1, nVies);

            try (ResultSet rs = stmt.executeQuery()) {
                // Recorrer todas las filas del ResultSet
                while (rs.next()) {
                    sectors.add(new Sector(
                            rs.getString("nom"),
                            rs.getString("coordenades"),
                            rs.getString("acces"),
                            rs.getInt("num_vies"),
                            rs.getString("dificultat"),
                            rs.getString("regulacions"),
                            rs.getInt("id_escola")
                    ));
                }

                // Si no se ha encontrado ningún sector, lanzar excepción
                if (sectors.isEmpty()) {
                    throw new RuntimeException("No s'ha trobat cap sector amb més de " + nVies + " vies...");
                }

                return sectors;
            }
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener los sectores: " + e.getMessage());
        }
    }

}

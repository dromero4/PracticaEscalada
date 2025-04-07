package dao.mysql;

import model.Sector;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SectorDAO {
    private Connection connection;

    public SectorDAO(Connection connection) {
        this.connection = connection;
    }

    public void crearSector(Sector sector) throws SQLException {
        String query = "INSERT INTO sectors (num_vies, id_escola, nom, acces, dificultat, regulacions, coordenades) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setInt(2, sector.getNum_vies());
            stmt.setInt(3, sector.getId_escola());
            stmt.setString(4, sector.getNom());
            stmt.setString(5, sector.getAcces());
            stmt.setString(6, sector.getDificultat());
            stmt.setString(7, sector.getRegulacions());
            stmt.setFloat(8, sector.getCoordenades());
            stmt.executeUpdate();
        }
    }

    public List<Sector> obtenerSectores() throws SQLException {
        String query = "SELECT * FROM sectors";
        List<Sector> sectores = new ArrayList<>();
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Sector sector = new Sector(
                        rs.getInt("num_vies"),
                        rs.getInt("id_escola"),
                        rs.getString("nom"),
                        rs.getString("acces"),
                        rs.getString("dificultat"),
                        rs.getString("regulacions"),
                        rs.getFloat("coordenades")
                );
                sectores.add(sector);
            }
        }
        return sectores;
    }
}

package dao.mysql;

import model.Escola;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EscolaDAO implements DAO<Escola, Integer>{
    private Connection connection;

    public EscolaDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void inserir(Escola escola) {
        String query = "INSERT INTO escola (nom, poblacio, acces, num_vies, dificultat, regulacions) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, escola.getNom());
            stmt.setString(2, escola.getPoblacio());
            stmt.setString(3, escola.getAcces());
            stmt.setInt(4, escola.getNum_vies());
            stmt.setString(5, escola.getDificultat());
            stmt.setString(6, escola.getRegulacions());

            // Ejecutar la inserción
            int affectedRows = stmt.executeUpdate();

            // Verificar si se generaron claves
            if (affectedRows > 0) {
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        // Obtener el ID generado y asignarlo a la escuela
                        escola.setId(rs.getInt(1));
                    }
                }
            } else {
                throw new SQLException("La inserción no ha afectado ninguna fila.");
            }

        } catch (Exception e) {
            throw new RuntimeException("Error al insertar la escuela: " + e.getMessage(), e);
        }
    }


    @Override
    public void modificar(Escola escola) {
        String sql = "UPDATE escola SET nom = ?, poblacio = ?, acces = ?, num_vies = ?, dificultat = ?, regulacions = ? WHERE id = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, escola.getNom());
            ps.setString(2, escola.getPoblacio());
            ps.setString(3, escola.getAcces());
            ps.setInt(4, escola.getNum_vies());
            ps.setString(5, escola.getDificultat());
            ps.setString(6, escola.getRegulacions());
            ps.setInt(7, escola.getId());

            int filesAfectades = ps.executeUpdate();

            if (filesAfectades == 0) {
                System.out.println("No s'ha modificat cap registre. Comprova que l'ID sigui correcte.");
            }
        } catch (SQLException e) {
            System.out.println("Error al modificar l'escola: " + e.getMessage());
        }
    }


    @Override
    public void eliminar(Escola escola) throws Exception {
        String query = "DELETE FROM escola WHERE id = ?";

        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1, escola.getId());

            int rows = stmt.executeUpdate();

            if(rows == 0) throw new Exception("No s'ha trobat cap escola amb ID: " + escola.getId());
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public List<Escola> obtenirTots() {
        String query = "SELECT * FROM escola";
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

    @Override
    public int obtenirID(String nom) {
        String query = "SELECT id FROM escola WHERE nom = ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, nom);

            try {
                ResultSet rs = stmt.executeQuery() ;
                if (rs.next()) {
                    return rs.getInt("id");
                } else {
                    throw new RuntimeException("No s'ha trobat cap escola amb el nom '" + nom + "'.");
                }
            } catch (SQLException e){
                throw new RuntimeException(e);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Escola obtenir(Integer id) {
        String query = "SELECT * FROM escola WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            // Establecer el parámetro `id` en el PreparedStatement
            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Escola(
                            rs.getString("nom"),
                            rs.getString("poblacio"),
                            rs.getString("acces"),
                            rs.getInt("num_vies"),
                            rs.getString("dificultat"),
                            rs.getString("regulacions")
                    );
                } else {
                    throw new RuntimeException("No s'ha trobat cap escola amb l'ID: " + id);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener la escola con ID " + id, e);
        }
    }

}

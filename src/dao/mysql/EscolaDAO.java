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
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, escola.getNom());
            stmt.setString(2, escola.getPoblacio());
            stmt.setString(3, escola.getAcces());
            stmt.setInt(4, escola.getNum_vies());
            stmt.setString(5, escola.getDificultat());
            stmt.setString(6, escola.getRegulacions());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) escola.setId(rs.getInt(1));

        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void modificar(Escola escola) {
        System.out.println("Not yet implemented");

    }

    @Override
    public void eliminar(Escola escola) {


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

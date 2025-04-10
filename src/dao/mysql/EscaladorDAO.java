package dao.mysql;

import model.Escalador;
import model.Escola;
import model.Sector;

import javax.print.attribute.DocAttribute;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EscaladorDAO implements DAO<Escalador, Integer> {
    private Connection connection;

    public EscaladorDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void inserir(Escalador escalador) {
        String query = "INSERT INTO escalador (nom, nick, edat, nivell, via_favorita, estil) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setString(1, escalador.getNom());
            stmt.setString(2, escalador.getNick());
            stmt.setInt(3, escalador.getEdat());
            stmt.setString(4, escalador.getNivell());
            stmt.setString(5, escalador.getVia_favorita());
            stmt.setString(6, escalador.getEstil());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void modificar(Escalador escalador) {
        String sql = "UPDATE escalador SET nom = ?, nick = ?, edat = ?, nivell = ?, via_favorita = ?, estil = ? WHERE id = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, escalador.getNom());
            ps.setString(2, escalador.getNick());
            ps.setInt(3, escalador.getEdat());
            ps.setString(4, escalador.getNivell());
            ps.setString(5, escalador.getVia_favorita());
            ps.setString(6, escalador.getEstil());
            ps.setInt(7, escalador.getId());

            int filesAfectades = ps.executeUpdate();

            if (filesAfectades == 0) {
                System.out.println("No s'ha modificat cap registre. Comprova que el nom sigui correcte.");
            }
        } catch (SQLException e) {
            System.out.println("Error al modificar l'escalador: " + e.getMessage());
        }
    }

    @Override
    public void eliminar(Escalador escalador) throws Exception {
        // Primero, definimos las consultas para eliminar las filas relacionadas
        String queryVia = "DELETE FROM via WHERE id_escalador = ?";
        String queryEscalador = "DELETE FROM escalador WHERE id = ?";

        try {
            // Primero, eliminamos las filas en 'via' que están relacionadas con este 'escalador'
            PreparedStatement stmtVia = connection.prepareStatement(queryVia);
            stmtVia.setInt(1, escalador.getId());
            int rowsVia = stmtVia.executeUpdate();

            // Verificamos si se han eliminado filas en 'via'
            if (rowsVia > 0) {
                System.out.println("S'han eliminat " + rowsVia + " vies relacionades amb aquest escalador.");
            } else {
                System.out.println("No s'han trobat vies relacionades amb aquest escalador.");
            }

            // Ahora, eliminamos el escalador
            PreparedStatement stmtEscalador = connection.prepareStatement(queryEscalador);
            stmtEscalador.setInt(1, escalador.getId());
            int rowsEscalador = stmtEscalador.executeUpdate();

            if (rowsEscalador == 0) {
                throw new Exception("No s'ha trobat cap escalador amb ID: " + escalador.getId());
            } else {
                System.out.println("Escalador eliminat correctament.");
            }
        } catch (Exception e) {
            throw new Exception("Error al eliminar el escalador: " + e.getMessage(), e);
        }
    }


    @Override
    public List<Escalador> obtenirTots() {
        String query = "SELECT * FROM escalador";
        List<Escalador> escaladores = new ArrayList<>();
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Escalador escalador = new Escalador(
                        rs.getString("nom"),
                        rs.getString("nick"),
                        rs.getInt("edat"),
                        rs.getString("nivell"),
                        rs.getString("via_favorita"),
                        rs.getString("estil")
                );
                escaladores.add(escalador);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return escaladores;
    }

    @Override
    public Escalador obtenir(Integer id) {
        String query = "SELECT * FROM escalador WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            // Establecer el parámetro `id` en el PreparedStatement
            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Escalador(
                            rs.getString("nom"),
                            rs.getString("nick"),
                            rs.getInt("edat"),
                            rs.getString("nivell"),
                            rs.getString("via_favorita"),
                            rs.getString("estil")
                    );

                } else {
                    throw new RuntimeException("No s'ha trobat cap escalador amb l'ID: " + id);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener el escalador con ID " + id, e);
        }
    }

    @Override
    public int obtenirID(String nom) throws Exception {
        String query = "SELECT id FROM escalador WHERE nick = ?";

        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, nom);

            try {
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    return rs.getInt("id");
                } else {
                    throw new RuntimeException("No s'ha trobat cap escalador amb el nick '" + nom + "'.");
                }
            } catch (SQLException e){
                throw new RuntimeException(e);
            }
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }


}

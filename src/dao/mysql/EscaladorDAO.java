package dao.mysql;

import model.Escalador;

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
        System.out.println("Not yet implemented");

    }

    @Override
    public void eliminar(Escalador escalador) {
        System.out.println("Not yet implemented");

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
        System.out.println("Not yet implemented");
        return null;
    }
}

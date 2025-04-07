package dao.mysql;

import model.Escalador;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EscaladorDAO {
    private Connection connection;

    public EscaladorDAO(Connection connection) {
        this.connection = connection;
    }

    public void crearEscalador(Escalador escalador) throws SQLException {
        String query = "INSERT INTO escaladores (id, edat, nick, nivell, via_favorita, estil) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, escalador.getId());
            stmt.setInt(2, escalador.getEdat());
            stmt.setString(3, escalador.getNom());
            stmt.setString(4, escalador.getNick());
            stmt.setString(5, escalador.getNivell());
            stmt.setString(6, escalador.getVia_favorita());
            stmt.setString(7, escalador.getEstil());
            stmt.executeUpdate();
        }
    }

    public List<Escalador> obtenerEscaladores() throws SQLException {
        String query = "SELECT * FROM escaladores";
        List<Escalador> escaladores = new ArrayList<>();
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Escalador escalador = new Escalador(
                        rs.getInt("id"),
                        rs.getInt("edat"),
                        rs.getString("nom"),
                        rs.getString("nick"),
                        rs.getString("nivell"),
                        rs.getString("via_favorita"),
                        rs.getString("estil")
                );
                escaladores.add(escalador);
            }
        }
        return escaladores;
    }
}

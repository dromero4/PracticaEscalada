package dao.mysql;

import java.util.List;

public interface DAO<T, K> {
    void inserir(T t); // Insereix un objecte de tipus T
    void modificar(T t); // Modifica un objecte de tipus T
    void eliminar(T t) throws Exception; // Elimina un objecte de tipus T
    List<T> obtenirTots(); // Obté tots els objectes de tipus T
    T obtenir(K id) throws Exception; // Obté un objecte de tipus T per la clau primària K
    int obtenirID(String nom) throws Exception;
}


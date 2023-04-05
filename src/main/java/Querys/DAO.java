package main.java.Querys;

import java.sql.*;
import java.util.ArrayList;

public abstract class DAO<T> {
    private String url;
    private String username;
    private String password;

    // Constructeur pour initialiser les attributs de connexion

    public DAO(){}
    public DAO(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    // Méthode pour établir une connexion à la base de données
    protected Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }

    abstract ArrayList<T> getAll() throws SQLException;

    abstract int add(T elem) throws SQLException;

    abstract int deleteById(int id) throws SQLException;


}

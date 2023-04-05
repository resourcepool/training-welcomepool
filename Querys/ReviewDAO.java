package Querys;

import java.sql.*;
import java.util.ArrayList;


public class ReviewDAO {

    // Attributs pour la connexion à la base de données
    private String url;
    private String username;
    private String password;

    // Constructeur pour initialiser les attributs de connexion
    public ReviewDAO(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    // Méthode pour établir une connexion à la base de données
    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }

    // Méthode pour récupérer tous les membres de la table "members"
    public ArrayList<Member> getAllReviews() throws SQLException {
        ArrayList<Review> Review = new ArrayList<>();
        String sql = "SELECT * FROM code_review_schedule";
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Review review = new Review();
                review.setId(rs.getInt("idcode_review_schedule"));
                review.setName(rs.getString("name"));
                review.setDescription(rs.getString("description"));
                review.setDate(rs.getDate("datetime"));
                review.setClassId(rs.getInt("class_id"));
                review.add(review);
            }
        }
        return review;
    }

    // Méthode pour ajouter un membre à la table "members"
    public int addMember(Member member) throws SQLException {
        int memberId = 0;
        String sql = "INSERT INTO members (name, email, birthdate, class_id) VALUES (?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, member.getName());
            stmt.setString(2, member.getEmail());
            stmt.setDate(3, member.getBirthdate());
            stmt.setInt(4, member.getClassId());
            stmt.executeUpdate();
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    memberId = rs.getInt(1);
                }
            }
        }
        return memberId;
    }

    // Méthode pour supprimer un membre de la table "members" par son id
    public int deleteMemberById(int id) throws SQLException {
        String sql = "DELETE FROM members WHERE id=?";
        int rowsDeleted;
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            rowsDeleted = stmt.executeUpdate();
        }
        return rowsDeleted;
    }
}

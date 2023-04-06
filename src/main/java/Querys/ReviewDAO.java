package main.java.Querys;

import java.sql.*;
import java.util.ArrayList;


public class ReviewDAO extends DAO<Review> {

    public ReviewDAO(String url, String username, String password){
        super( url,  username,  password);
    }


    public ArrayList<Review> getAll() throws SQLException {
        ArrayList<Review> reviews = new ArrayList<>();
        String sql = "SELECT r.id, r.name, description, datetime, c.name FROM code_reviews as r INNER JOIN classes as c on r.class_id = c.id;";
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Review review = new Review();
                review.setId(rs.getInt("r.id"));
                review.setName(rs.getString("r.name"));

                review.setDescription(rs.getString("description"));
                review.setDate(rs.getTimestamp("datetime"));
                review.setPromotion(rs.getString("c.name"));
                reviews.add(review);
            }
        }
        return reviews;
    }

    public int add(Review review) throws SQLException {
        int reviewId = 0;
        String sql = "INSERT INTO code_reviews (name, description, datetime, class_id) VALUES (? , ?, ? , ?)";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {
            stmt.setString(1, review.getName());
            stmt.setString(2, review.getDescription());
            stmt.setTimestamp(3, review.getDate());
            stmt.setInt(4,review.getClassId());
            stmt.executeUpdate();
        }
        return 1;
    }

    public int deleteById(int id) throws SQLException {
        String sql = "DELETE FROM code_review_schedule WHERE id=?";
        int rowsDeleted;
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            rowsDeleted = stmt.executeUpdate();
        }
        return rowsDeleted;
    }
}
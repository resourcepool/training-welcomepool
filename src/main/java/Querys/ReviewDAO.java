package main.java.Querys;

import java.sql.*;
import java.util.ArrayList;


public class ReviewDAO extends DAO<Review> {

    public ReviewDAO(String url, String username, String password){
        super( url,  username,  password);
    }

    public ArrayList<Review> getAll() throws SQLException {
        ArrayList<Review> reviews = new ArrayList<>();
        String sql = "SELECT * FROM code_reviews";
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
                reviews.add(review);
            }
        }
        return reviews;
    }

    public int add(Review review) throws SQLException {
        int reviewId = 0;
        String sql = "INSERT INTO code_review_schedule (name, description, datetime, class_id) VALUES (?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {
            stmt.setString(1, review.getName());
            stmt.setString(2, review.getDescription());
            stmt.setDate(3, review.getDate());
            String getId = "SELECT class_id FROM class WHERE name=" + review.getPromotion();
            Statement stmt2 = conn.createStatement();
            ResultSet rs = stmt2.executeQuery(getId);

            if (rs.next()) stmt.setInt(4, rs.getInt("class_id"));
            stmt.executeUpdate();

            try (ResultSet rs2 = stmt.getGeneratedKeys()) {
                if (rs.next()) reviewId = rs2.getInt(1);
            }
        }
        return reviewId;
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
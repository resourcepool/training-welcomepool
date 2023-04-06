package main.java.Querys;

import java.sql.*;
import java.util.ArrayList;

public class PromotionDAO extends DAO<Promotion> {

    public PromotionDAO() {}

    public PromotionDAO(String url, String user, String password){
        super(url, user, password);
    }

    public ArrayList<Promotion> getAll() throws SQLException {
        MemberDAO mDAO = new MemberDAO(url, username, password);
        ArrayList<Promotion> promotions = new ArrayList<>();
        Connection conn = getConnection();
        String sql = "SELECT id, name FROM classes";

        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            Promotion promotion = new Promotion();
            int id = rs.getInt("id");
            promotion.setId(id);
            promotion.setName(rs.getString("name"));
            promotion.setNbMembres(mDAO.countByPromotion(id));
            promotions.add(promotion);

        }
        return promotions;
    }

    public int add(Promotion promotion) throws SQLException{
        System.out.println("add PromotionDAO");
        int promotionId = 0;
        //String getId;
        String sql = " insert into classes (name) values (?)";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, promotion.getName());
            stmt.executeUpdate();
            System.out.println(sql);
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    promotionId = rs.getInt(1);
                    System.out.println(promotionId);
                }
            }
        }
        return promotionId;
    }

    public int deleteById(int id) throws SQLException {
        String sql = "DELETE FROM classes WHERE id=?";
        int rowsDeleted;
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            rowsDeleted = stmt.executeUpdate();

            sql = "UPDATE memebers SET class_id = NULL WHERE class_id=?";
            PreparedStatement stmt2 = conn.prepareStatement(sql);
            stmt2.setInt(1, id);
            stmt2.execute();
        }
        return rowsDeleted;
    }
}

package main.java.Querys;

import java.sql.*;
import java.util.ArrayList;


public class MemberDAO extends DAO<Member> {


    public MemberDAO(String url, String username, String password){
        super( url,  username,  password);
    }

    public ArrayList<Member> getAll() throws SQLException {
        ArrayList<Member> members = new ArrayList<>();
        Connection conn = getConnection();
        String sql = "SELECT m.id, m.name, email, birthdate, class_id, c.name as class_name FROM members as m INNER JOIN classes as c ON m.class_id = c.id";

        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            Member member = new Member();
            member.setId(rs.getInt("m.id"));
            member.setName(rs.getString("m.name"));
            member.setEmail(rs.getString("email"));
            member.setBirthdate(rs.getDate("birthdate"));
            member.setClassId(rs.getInt("class_id"));
            member.setPromotion(rs.getString("class_name"));

            members.add(member);

        }
        return members;
    }

    public int add(Member member) throws SQLException {
        int memberId = 0;
        String getId;
        String sql = " insert into members (name,email,birthdate,class_id)"
                + " values ('"+ member.getName() +"', '"+ member.getEmail() +"', '"+ member.getBirthdate() +"', '"+ member.getClassId()+"')";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
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
    public int deleteById(int id) throws SQLException {
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

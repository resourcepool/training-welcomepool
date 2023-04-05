package main.java.Querys;

import java.sql.*;
import java.util.ArrayList;


public class MemberDAO extends DAO<Member> {

    public MemberDAO(){}

    public ArrayList<Member> getAll() throws SQLException {
        ArrayList<Member> members = new ArrayList<>();
        String sql = "SELECT * FROM members";
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Member member = new Member();
                member.setId(rs.getInt("id"));
                member.setName(rs.getString("name"));
                member.setEmail(rs.getString("email"));
                member.setBirthdate(rs.getDate("birthdate"));
                member.setClassId(rs.getInt("class_id"));
                sql = "SELECT name FROM class WHERE class_id="+member.getClassId();
                try (Statement stmt2 = conn.createStatement();
                     ResultSet result = stmt2.executeQuery(sql);) {
                    if (result.next()) member.setPromotion(result.getString("name"));
                }
                members.add(member);
            }
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

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class classes {

    private String name;

    public classes(String name){
        this.name = name;
    }

    public void insert_classes(Connection conn) throws SQLException{
        String sql = " insert into classes (name)"
        + " values ('"+ name +"')";

        PreparedStatement preparedStmt = conn.prepareStatement(sql);
        preparedStmt.execute();
        System.out.println("Données insérés dans la table...");         
    }

    public void delete_classes(Connection conn) throws SQLException{
        String sql = "delete from classes where name = '"+ name +"'";

        PreparedStatement preparedStmt = conn.prepareStatement(sql);
        preparedStmt.execute();
        System.out.println("Données enlevés dans la table...");     
    }

    public int count_member(Connection conn) throws SQLException{
        String id = "SELECT id from classes where name = '"+ name +"'";
        String sql = "SELECT count(*) from class_members where id = '" + id + "'";

        PreparedStatement preparedStmt = conn.prepareStatement(sql);
        ResultSet rs = preparedStmt.executeQuery();

        while(rs.next()){
            System.out.println(rs.getInt(1));
            return rs.getInt(1);
        }

        return rs.getInt(1);
    }

}
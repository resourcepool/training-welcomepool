import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
public class code_reviews {
    
    private String name;
    private String description;
    private Date date;
    private int class_id;

    public code_reviews(String name, String desc, Date date, int c_id){
        this.name = name;
        this.description = desc;
        this.date = date;
        this.class_id = c_id;
    }

    public void insert_code_reviews(Connection conn) throws SQLException{
        String sql = " insert into code_reviews (name,description,datetime,class_id)"
        + " values ('"+ name +"', '"+ description +"', '"+ date +"', '"+ class_id+"')";

        PreparedStatement preparedStmt = conn.prepareStatement(sql);
        preparedStmt.execute();
        System.out.println("Données insérés dans la table...");         
    }

    public void delete_code_recviews(Connection conn) throws SQLException{
        String sql = "delete from code_reviews where name = '"+ name +"' AND datetime = '"+ date +"'";
        
        PreparedStatement preparedStmt = conn.prepareStatement(sql);
        preparedStmt.execute();
        System.out.println("Données enlevés dans la table...");     
    }

}

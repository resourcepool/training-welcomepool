import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.junit.Assert.*;

public class main {
    
    public static void main(String[] args) {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/welcome_pool", "anthony", "1234");
            assertTrue(conn != null);

            classes c = new classes("Test00");
            c.insert_classes(conn);
            
            //c.delete_classes(conn);

            
            /*String str="2015-03-31";  
            Date date=Date.valueOf(str);//converting string into sql date  
            
            code_reviews t = new code_reviews("Code_review 1", "On parle de Test4", date, 2);
            //t.insert_code_reviews(conn);
            t.delete_code_recviews(conn);
            */
            conn.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}
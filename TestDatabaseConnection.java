import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.junit.Test;
import static org.junit.Assert.*;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.Date;


public class TestDatabaseConnection {

    @Test
    public void testSelectQuery() throws SQLException {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/welcomepool", "nimA", "123456");
            assertTrue(conn != null);
            
            // Exécuter la requête
            Statement stmt = conn.createStatement();
            String query = "SELECT * FROM members";
            ResultSet rs = stmt.executeQuery(query);
            
            // Vérifier si la requête retourne des résultats
            assertTrue(rs.next());
            
            // Traiter les résultats
            do {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                Date birthdate = rs.getDate("birthdate");
                int classId = rs.getInt("class_id");
                
                // Faire quelque chose avec les résultats (par exemple, les afficher à la console)
                System.out.println(id + ", " + name + ", " + email + ", " + birthdate + ", " + classId);
            } while (rs.next());
            
        } catch (SQLException e) {
            e.printStackTrace();
            fail("Connexion à la base de données échouée : " + e.getMessage());
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    @Test
    public void testInsertQuery() throws SQLException {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/welcomepool", "nimA", "123456");
            assertTrue(conn != null);

            // Insérer une nouvelle ligne dans la table "members"
            Statement stmt = conn.createStatement();
            String query = "INSERT INTO members (id,name, email, birthdate, class_id) VALUES (6,'Jean Dupont', 'jean.dupont@example.com', '1990-01-01', 1)";
            int rowsAffected = stmt.executeUpdate(query);
            assertTrue(rowsAffected == 1);

            // Vérifier que la nouvelle ligne a bien été ajoutée
            query = "SELECT * FROM members WHERE name='Jean Dupont'";
            ResultSet rs = stmt.executeQuery(query);
            assertTrue(rs.next());
            assertTrue(rs.getString("email").equals("jean.dupont@example.com"));
            assertTrue(rs.getDate("birthdate").toString().equals("1990-01-01"));
            assertTrue(rs.getInt("class_id") == 1);

        } catch (SQLException e) {
            e.printStackTrace();
            fail("Connexion à la base de données échouée : " + e.getMessage());
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    @Test
    public void testDeleteQuery() throws SQLException {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/welcomepool", "nimA", "123456");
            assertTrue(conn != null);

            // Insert a new member
            Statement stmt = conn.createStatement();
            String insertQuery = "INSERT INTO members (id,name, email, birthdate, class_id) VALUES (9,'frdi Does', 'frdi .does@example.com', '1990-01-01', 1)";
            int rowsAffected = stmt.executeUpdate(insertQuery, Statement.RETURN_GENERATED_KEYS);
            assertTrue(rowsAffected == 1);



            // Delete the member
            String deleteQuery = "DELETE FROM members WHERE id = 7 " ;
            rowsAffected = stmt.executeUpdate(deleteQuery);
            assertTrue(rowsAffected == 1);

        } catch (SQLException e) {
            e.printStackTrace();
            fail("Connexion à la base de données échouée : " + e.getMessage());
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

}


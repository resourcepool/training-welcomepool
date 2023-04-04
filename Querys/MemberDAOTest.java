package Querys;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class MemberDAOTest {

    
    private MemberDAO memberDAO;
    
    // Paramètres de connexion pour les tests
    private final String URL = "jdbc:mysql://localhost:3306/welcomepool";
    private final String USERNAME = "nimA";
    private final String PASSWORD = "123456";
    


    @Before
    public void setUp() throws Exception {
        // Initialisation de l'objet MemberDAO avec les paramètres de connexion pour les tests
        memberDAO = new MemberDAO(URL, USERNAME, PASSWORD);
    }

    @After
    public void tearDown() throws Exception {
        // Fermeture de la connexion à la fin des tests
        memberDAO = null;
    }

    @Test
    public void testGetAllMembers() {
        try {
            // Appel de la méthode getAllMembers() pour récupérer tous les membres de la table "members"
            ArrayList<Member> members = memberDAO.getAllMembers();

            // Vérification que la liste des membres n'est pas vide
            assertFalse(members.isEmpty());

            // Affichage des membres
            System.out.println("Nombre de membres dans la base de données : " + members.size());
            for (Member member : members) {
                System.out.println(member.getId() + " - " + member.getName() + " - " + member.getEmail() + " - " + member.getBirthdate() + " - " + member.getClassId());
            }

        } catch (SQLException e) {
            fail("Erreur lors de la récupération des membres : " + e.getMessage());
        }
    }
}
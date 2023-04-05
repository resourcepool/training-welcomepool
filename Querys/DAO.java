package main.webapp;

public abstract class DAO<T> {
    private String url;
    private String username;
    private String password;

    // Constructeur pour initialiser les attributs de connexion
    public DAO(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    // Méthode pour établir une connexion à la base de données
    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }

    abstract ArrayList<T> getAll();

    abstract int add(T elem);

    abstract int deleteById(int id);

    public class MemberDAO extends DAO<Member> {

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
                    members.add(member);
                }
            }
            return members;
        }

        public int add(Member member) throws SQLException {
            int memberId = 0;
            String sql = "INSERT INTO members (name, email, birthdate, class_id) VALUES (?, ?, ?, ?)";
            try (Connection conn = getConnection();
                 PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                stmt.setString(1, member.getName());
                stmt.setString(2, member.getEmail());
                stmt.setDate(3, member.getBirthdate());
                stmt.setInt(4, member.getClassId());
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

    public class ReviewDAO extends DAO<Review> {

        public ArrayList<Member> getAllReviews() throws SQLException {
            ArrayList<Review> Review = new ArrayList<>();
            String sql = "SELECT * FROM code_review_schedule";
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
                    review.add(review);
                }
            }
            return review;
        }

        public int add(Review review) throws SQLException {
            int reviewId = 0;
            String sql = "INSERT INTO code_review_schedule (name, description, datetime, class) VALUES (?, ?, ?, ?)";
            try (Connection conn = getConnection();
                 PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                stmt.setString(1, review.getName());
                stmt.setString(2, review.getDescription());
                stmt.setDate(3, review.getDate());
                stmt.setInt(4, review.getClassId());
                stmt.executeUpdate();
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        memberId = rs.getInt(1);
                    }
                }
            }
            return reviewId;
        }

        // Méthode pour supprimer un membre de la table "members" par son id
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
}

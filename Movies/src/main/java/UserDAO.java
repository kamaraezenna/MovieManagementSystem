import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public enum UserDAO {
     instance;

    private UserDAO() {
    	
    }

    public List<User> list() throws Exception {
        List<User> users = new ArrayList<>();
        Connection con = getConnection();
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM USERS");
		while(rs.next()) {
			String email= rs.getString("email");
			String password= rs.getString("password");
			String name = rs.getString("name");
			users.add(new User(email, password, name));
		}
		con.close();
		return users;

    }


    public Connection getConnection() throws Exception{
		Class.forName("org.hsqldb.jdbcDriver");
		Connection con;
		con = DriverManager.getConnection(
				"jdbc:hsqldb:hsql://localhost/oneDB","sa","");
		return con;
	}
    
    public static void main(String[] args) {
        try {
            // Retrieve the list of users from the DAO
            List<User> users = UserDAO.instance.list();
            
            // Loop through the users and print their name and email
            for (User user : users) {
                System.out.println("Name: " + user.getName() + " Email: " + user.getEmail());
            }
        } catch (Exception e) {
            // Print the exception stack trace if any error occurs
            e.printStackTrace();
        }
    }


    public void addUser(User user) throws Exception {
    	Connection con = getConnection();
    	PreparedStatement stmt = con.prepareStatement
         ("INSERT INTO USERS (email, password, name) VALUES (?, ?, ?)");
       
            stmt.setString(1, user.getEmail());
            stmt.setString(2, user.getPassword());
            stmt.setString(3, user.getName());
            stmt.executeUpdate();
    }

    public User getUserByEmail(String email) throws Exception {
        // Initialize the connection and prepared statement
        Connection connection = getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            // Prepare the SQL query
            stmt = connection.prepareStatement("SELECT * FROM USERS WHERE email = ?");
            stmt.setString(1, email);
            
            // Execute the query
            rs = stmt.executeQuery();
            
            // Check if a record is returned
            if (rs.next()) {
                User user = new User();
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                return user;
            }
        } finally {
            // Make sure to close resources
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        
        return null;
    }

    
}

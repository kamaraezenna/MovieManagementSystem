import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public enum MovieDAO {
    instance;

	public Connection getConnection() throws Exception{
		Class.forName("org.hsqldb.jdbcDriver");
		Connection con;
		con = DriverManager.getConnection(
				"jdbc:hsqldb:hsql://localhost/oneDB","sa","");
		return con;
	}

    public void addMovie(Movie movie) throws Exception {
        String sql = "INSERT INTO movies (title, director, year, userEmail) VALUES (?, ?, ?, ?)";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, movie.getTitle());
            stmt.setString(2, movie.getDirector());
            stmt.setInt(3, movie.getYear());
            stmt.setString(4, movie.getUserEmail());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error adding movie: " + e.getMessage());
        }
    }

    public void updateMovie(Movie movie) throws Exception {
        String sql = "UPDATE movies SET title = ?, director = ?, year = ?, userEmail = ? WHERE id = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, movie.getTitle());
            stmt.setString(2, movie.getDirector());
            stmt.setInt(3, movie.getYear());
            stmt.setString(4, movie.getUserEmail());
            stmt.setInt(5, movie.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error updating movie: " + e.getMessage());
        }
    }

    public void deleteMovie(int id) throws Exception {
        String sql = "DELETE FROM movies WHERE id = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error deleting movie: " + e.getMessage());
        }
    }

    public List<Movie> getMoviesByUserEmail(String email) throws Exception {
        List<Movie> movies = new ArrayList<>();
        String query = "SELECT * FROM MOVIES WHERE userEmail = ?";

        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(query)) {
            
            stmt.setString(1, email);
            
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String title = rs.getString("title");
                    String director = rs.getString("director");
                    int year = rs.getInt("year");
                    movies.add(new Movie(id, title, director, year, email));
                }
            }
        }
        return movies;
    }
    
    public Movie getMovieById(int id) throws Exception {
        String query = "SELECT * FROM movies WHERE id = ?";
        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String title = rs.getString("title");
                    String director = rs.getString("director");
                    int year = rs.getInt("year");
                    String userEmail = rs.getString("userEmail");
                    return new Movie(id, title, director, year, userEmail);
                }
            }
        }
        return null;
    }

}

import java.io.*;
import java.sql.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;



/**
 * Servlet implementation class Register
 */
@WebServlet("/UserController")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDAO userDAO = UserDAO.instance;
	private MovieDAO movieDAO = MovieDAO.instance;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    String action = request.getParameter("action");

	    try {
	        if ("login".equalsIgnoreCase(action)) {
	            String email = request.getParameter("email");
	            String password = request.getParameter("password");

	            User user = userDAO.getUserByEmail(email);

	            if (user != null && user.getPassword().equals(password)) {
	                HttpSession session = request.getSession();
	                session.setAttribute("userEmail", user.getEmail());
	                session.setAttribute("userName", user.getName());

	                request.setAttribute("success", "Welcome " + user.getName() + "!");
	                List<Movie> movies = movieDAO.getMoviesByUserEmail(user.getEmail());
	                request.setAttribute("movies", movies);
	                request.getRequestDispatcher("dashboard.jsp").forward(request, response);
	            } else {
	                request.setAttribute("error", "Invalid email or password.");
	                request.getRequestDispatcher("login.jsp").forward(request, response);
	            }
	        } else if ("register".equalsIgnoreCase(action)) {
	            String name = request.getParameter("name");
	            String email = request.getParameter("email");
	            String password = request.getParameter("password");

	            if (userDAO.getUserByEmail(email) != null) {
	                request.setAttribute("error", "Email is already registered.");
	                request.getRequestDispatcher("register.jsp").forward(request, response);
	            } else {
	                User newUser = new User(email, password, name);
	                userDAO.addUser(newUser);

	                request.setAttribute("success", "Registration successful! Please log in.");
	                request.getRequestDispatcher("login.jsp").forward(request, response);
	            }
	        } else if ("dashboard".equalsIgnoreCase(action)) {
	            HttpSession session = request.getSession(false);
	            if (session != null && session.getAttribute("userEmail") != null) {
	                String userEmail = (String) session.getAttribute("userEmail");
	                List<Movie> movies = movieDAO.getMoviesByUserEmail(userEmail);
	                request.setAttribute("movies", movies);
	                request.getRequestDispatcher("dashboard.jsp").forward(request, response);
	            } else {
	                response.sendRedirect("login.jsp");
	            }
	        } else if ("addPage".equalsIgnoreCase(action)) {
	            request.getRequestDispatcher("addMovie.jsp").forward(request, response);
	        } else if ("add".equalsIgnoreCase(action)) {
	            HttpSession session = request.getSession(false);
	            if (session == null || session.getAttribute("userEmail") == null) {
	                response.sendRedirect("login.jsp");
	                return;
	            }
	            String userEmail = (String) session.getAttribute("userEmail");
	            String title = request.getParameter("title");
	            String director = request.getParameter("director");
	            int year = Integer.parseInt(request.getParameter("year"));

	            Movie newMovie = new Movie(0, title, director, year, userEmail);
	            movieDAO.addMovie(newMovie);
	            request.setAttribute("success", "You have successfully added a book. Please login again.");
                request.getSession().invalidate();
                request.getRequestDispatcher("login.jsp").forward(request, response);
	        } else if ("updatePage".equalsIgnoreCase(action)) {
	            int id = Integer.parseInt(request.getParameter("id"));
	            Movie movie = movieDAO.getMovieById(id);
	            request.setAttribute("movie", movie);
	            request.getRequestDispatcher("updateMovie.jsp").forward(request, response);
	        } else if ("update".equalsIgnoreCase(action)) {
	            HttpSession session = request.getSession(false);
	            if (session == null || session.getAttribute("userEmail") == null) {
	                response.sendRedirect("login.jsp");
	                return;
	            }
	            String userEmail = (String) session.getAttribute("userEmail");
	            int id = Integer.parseInt(request.getParameter("id"));
	            String title = request.getParameter("title");
	            String director = request.getParameter("director");
	            int year = Integer.parseInt(request.getParameter("year"));

	            Movie updatedMovie = new Movie(id, title, director, year, userEmail);
	            movieDAO.updateMovie(updatedMovie);
	            request.setAttribute("success", "You have successfully updated a book. Please login again.");
                request.getSession().invalidate();
                request.getRequestDispatcher("login.jsp").forward(request, response);
	        } else if ("delete".equalsIgnoreCase(action)) {
	            int id = Integer.parseInt(request.getParameter("id"));
	            movieDAO.deleteMovie(id);
	            request.setAttribute("success", "You have successfully deleted a book. Please login again.");
                request.getSession().invalidate();
                request.getRequestDispatcher("login.jsp").forward(request, response);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        request.setAttribute("error", "An error occurred while processing your request.");
	        request.getRequestDispatcher("error.jsp").forward(request, response);
	    }
	}

}




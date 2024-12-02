
public class Movie {
    private int id;
    private String title;
    private String director;
    private int year;
    private String userEmail;

    public Movie() {}

    public Movie(String title, String director, int year, String userEmail) {
        this.title = title;
        this.director = director;
        this.year = year;
        this.userEmail = userEmail;
    }

    public Movie(int id, String title, String director, int year, String userEmail) {
        this.id = id;
        this.title = title;
        this.director = director;
        this.year = year;
        this.userEmail = userEmail;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDirector() { return director; }
    public void setDirector(String director) { this.director = director; }

    public int getYear() { return year; }
    public void setYear(int year) { this.year = year; }

    public String getUserEmail() { return userEmail; }
    public void setUserEmail(String userEmail) { this.userEmail = userEmail; }
}

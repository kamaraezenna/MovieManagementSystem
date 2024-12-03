# Movie Management System

## Description
A web-based application for managing movies, built using Java Servlets, JSP, and HSQLDB. The application allows users to log in, add, view, edit, and delete movie records, with user authentication and role-based access.

## Features
- Secure user login and authentication.
- CRUD operations for movie records.
- User-movie data relationship enforced using foreign keys.
- Dynamic dashboard with real-time updates.
- Clean, responsive UI built with JSP and CSS.

## Technologies Used
- **Backend**: Java Servlets, DAO Design Pattern.
- **Frontend**: JSP, HTML, CSS.
- **Database**: HSQLDB.
- **Tools**: GitHub, Apache Tomcat Server.

## Challenges and Solutions
- **Issue**: Blank JSP pages on form submission.  
  **Solution**: Debugged servlets and corrected action paths.  
- **Issue**: Maintaining data integrity in HSQLDB.  
  **Solution**: Used foreign key constraints and SQL cascade deletion.

## Future Enhancements
- Add search and filter functionality.
- Implement role-based access control for admins.
- Deploy on a cloud-based database.

## How to Run
1. Clone the repository: `git clone <repo-link>`.
2. Set up HSQLDB with provided SQL scripts.
3. Deploy the project on Apache Tomcat.
4. Access the app at `http://localhost:8080/`.

## Repository Structure
- `/src` - Java source code.
- `/webapp` - JSP files and static resources.
- `/sql` - SQL scripts.
- `/README.md` - Documentation.

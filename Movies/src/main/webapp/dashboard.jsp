<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Dashboard</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f9;
            margin: 0;
            padding: 20px;
        }
        h1 {
            text-align: center;
            color: #333;
        }
        p {
            text-align: center;
            font-size: 16px;
            color: #555;
        }
        .container {
            max-width: 800px;
            margin: 0 auto;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin: 20px 0;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            border-radius: 8px;
            overflow: hidden;
        }
        thead {
            background-color: #4CAF50;
            color: white;
        }
        th, td {
            padding: 12px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }
        tr:hover {
            background-color: #f1f1f1;
        }
        th {
            font-size: 16px;
            font-weight: bold;
        }
        td {
            font-size: 14px;
        }
        .actions {
            display: flex;
            gap: 10px;
        }
        button {
            background-color: #4CAF50;
            color: white;
            border: none;
            padding: 8px 12px;
            font-size: 14px;
            cursor: pointer;
            border-radius: 4px;
            transition: background-color 0.3s;
        }
        button:hover {
            background-color: #45a049;
        }
        .add-movie-btn {
            background-color: #007bff;
            padding: 10px 16px;
            display: block;
            margin: 20px auto;
            text-align: center;
        }
        .add-movie-btn:hover {
            background-color: #0056b3;
        }
        .no-movies {
            text-align: center;
            font-size: 16px;
            color: #555;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Welcome to Your Dashboard</h1>
        <p>${success}</p>

        <h2>Your Movies</h2>

        <!-- Display Movies -->
        <c:if test="${not empty movies}">
            <table>
                <thead>
                    <tr>
                        <th>Title</th>
                        <th>Director</th>
                        <th>Year</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="movie" items="${movies}">
                        <tr>
                            <td>${movie.title}</td>
                            <td>${movie.director}</td>
                            <td>${movie.year}</td>
                            <td>
                                <div class="actions">
                                    <!-- Update Button -->
                                    <form action="UserController" method="post">
                                        <input type="hidden" name="id" value="${movie.id}" />
                                        <input type="hidden" name="action" value="updatePage" />
                                        <button type="submit">Update</button>
                                    </form>

                                    <!-- Delete Button -->
                                    <form action="UserController" method="post">
                                        <input type="hidden" name="id" value="${movie.id}" />
                                        <input type="hidden" name="action" value="delete" />
                                        <button type="submit" style="background-color: #f44336;">Delete</button>
                                    </form>
                                </div>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>

        <!-- No Movies Message -->
        <c:if test="${empty movies}">
            <p class="no-movies">You have no movies in your collection.</p>
        </c:if>

        <!-- Add Movie Button -->
        <form action="UserController" method="post">
            <input type="hidden" name="action" value="addPage" />
            <button type="submit" class="add-movie-btn">Add Movie</button>
        </form>
    </div>
</body>
</html>

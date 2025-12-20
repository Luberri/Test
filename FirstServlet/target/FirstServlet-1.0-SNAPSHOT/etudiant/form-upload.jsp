<!-- filepath: g:\S5\Framework-Naina\Test\FirstServlet\src\main\webapp\etudiant\form-upload.jsp -->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Ajouter un étudiant avec photo</title>
</head>
<body>
    <h1>Ajouter un étudiant</h1>
    
    <form action="${pageContext.request.contextPath}/etudiant/ajouter-avec-photo" 
          method="post" 
          enctype="multipart/form-data">
        
        <label>Nom:</label>
        <input type="text" name="nom" required><br><br>
        
        <label>Prénom:</label>
        <input type="text" name="prenom" required><br><br>
        
        <label>Email:</label>
        <input type="email" name="mail" required><br><br>
        
        <label>Photo:</label>
        <input type="file" name="photo" accept="image/*"><br><br>
        
        <label>CV (PDF):</label>
        <input type="file" name="cv" accept=".pdf"><br><br>
        
        <button type="submit">Ajouter</button>
    </form>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Ajouter un étudiant</title>
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }
        
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            min-height: 100vh;
            padding: 20px;
            display: flex;
            align-items: center;
            justify-content: center;
        }
        
        .container {
            max-width: 600px;
            width: 100%;
            background-color: white;
            border-radius: 10px;
            box-shadow: 0 10px 30px rgba(0, 0, 0, 0.3);
            padding: 40px;
        }
        
        h1 {
            color: #333;
            margin-bottom: 30px;
            text-align: center;
            font-size: 2em;
        }
        
        .form-group {
            margin-bottom: 20px;
        }
        
        label {
            display: block;
            margin-bottom: 8px;
            color: #333;
            font-weight: 500;
        }
        
        input[type="text"],
        input[type="email"],
        input[type="date"] {
            width: 100%;
            padding: 12px;
            border: 2px solid #e0e0e0;
            border-radius: 5px;
            font-size: 16px;
            transition: border-color 0.3s;
        }
        
        input[type="text"]:focus,
        input[type="email"]:focus,
        input[type="date"]:focus {
            outline: none;
            border-color: #667eea;
        }
        
        .btn-container {
            display: flex;
            gap: 10px;
            margin-top: 30px;
        }
        
        .btn {
            flex: 1;
            padding: 12px;
            border: none;
            border-radius: 5px;
            font-size: 16px;
            cursor: pointer;
            text-decoration: none;
            text-align: center;
            transition: all 0.3s;
        }
        
        .btn-primary {
            background-color: #667eea;
            color: white;
        }
        
        .btn-primary:hover {
            background-color: #5568d3;
        }
        
        .btn-secondary {
            background-color: #e0e0e0;
            color: #333;
        }
        
        .btn-secondary:hover {
            background-color: #d0d0d0;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Ajouter un étudiant</h1>
        
        <form action="${pageContext.request.contextPath}/etudiant/ajouter" method="POST">
            <div class="form-group">
                <label for="nom">Nom :</label>
                <input type="text" id="nom" name="nom" required>
            </div>
            
            <div class="form-group">
                <label for="prenom">Prénom :</label>
                <input type="text" id="prenom" name="prenom" required>
            </div>

            <div class="form-group">
                <label for="mail">E-mail :</label>
                <input type="email" id="mail" name="mail" required>
            </div>

            <div class="form-group">
                <label for="dateNaissance">Date de naissance :</label>
                <input type="date" id="dateNaissance" name="dateNaissance" required>
            </div>

            <div class="form-group">
                <label for="numeroEtudiant">Numéro étudiant :</label>
                <input type="text" id="numeroEtudiant" name="numeroEtudiant" required>
            </div>

            <div class="form-group">
                <label for="promotion">Promotion :</label>
                <input type="text" id="promotion" name="promotion" required>
            </div>
            
            <div class="btn-container">
                <button type="submit" class="btn btn-primary">Ajouter</button>
                <a href="${pageContext.request.contextPath}/etudiant/list" class="btn btn-secondary">Annuler</a>
            </div>
        </form>
    </div>
</body>
</html>
# sprint2-BIS
## FirstServlet — Guide rapide

Ce dépôt contient un petit projet Java qui illustre la découverte de classes annotées avec `@Controller` via un scanner au runtime.

### Ce qu'on a fait
- Ajout / utilisation d'une annotation `com.example.annotation.Controller` (rétention RUNTIME).
- Ajout d'un scanner `com.example.ScannerMain` qui parcourt le classpath (répertoires et JARs), charge les classes et affiche celles annotées par `@Controller`.
- Quelques classes de test sous `com.example.test` :
	- `Test1` — annotée `@Controller` (devrait être détectée)
	- `Test2` — non annotée (ne doit pas être détectée)
	- `Test3` — annotée `@Controller` (devrait être détectée)

### Fichiers importants
- `pom.xml` — configuration Maven du projet.
- `src/main/java/com/example/ScannerMain.java` — le programme `main` qui scanne le classpath.
- `src/main/java/com/example/annotation/Controller.java` — l'annotation `@Controller`.
- `src/main/java/com/example/test/*` — classes de test (Test1, Test2, Test3).
- `deploy.bat` — script de déploiement (si présent, dépend du packaging Maven produit).

### Comment compiler
Ouvrez PowerShell à la racine du projet `FirstServlet` et lancez :

```powershell
mvn compile
```

Pour créer un artefact (JAR/WAR selon le packaging configuré) :

```powershell
mvn package
```

### Exécuter le scanner (sans créer de package)
Après `mvn compile`, lancez :

```powershell
java -cp target/classes com.example.ScannerMain
```

Remarques :
- Si vous avez des dépendances externes (JARs), vous pouvez ajouter leur chemin dans le classpath. Exemple simple pour exécuter avec tous les jars du répertoire `lib` :

```powershell
$cp = "target/classes;lib/*"
java -cp $cp com.example.ScannerMain
```

### Exemple de sortie attendue

```
Classes annotated with @Controller:
 - com.example.test.Test1
 - com.example.test.Test3
```

(`Test2` n'apparaîtra pas car elle n'est pas annotée.)

### Prochaines étapes / conseils
- Ajouter des logs ou options CLI au scanner (ex : filtrer par package, afficher classes non chargées, activer affichage des erreurs).
- Implémenter des tests unitaires pour vérifier la découverte d'annotations.
- Si vous voulez exécuter depuis le JAR produit, construisez avec `mvn package` et lancez avec le classpath qui inclut le JAR.

### Contact rapide
Si quelque chose ne fonctionne pas :
- Vérifiez que `target/classes` contient bien les `.class` après `mvn compile`.
- Vérifiez la valeur de `System.getProperty("java.class.path")` si vous avez des chemins personnalisés.

---
Rédigé automatiquement : mise à jour du README avec instructions de compilation et d'exécution du scanner d'annotations.

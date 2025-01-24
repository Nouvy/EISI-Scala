import scala.collection.mutable.ListBuffer
import scala.io.StdIn
import java.sql.{Connection, DriverManager, ResultSet}
import scala.util.Random
import org.apache.spark.sql.SparkSession

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
@main
def main(): Unit =

  // Configuration système pour Java 17
  val jvmOptions = Seq(
    "java.base/java.nio=ALL-UNNAMED",
    "java.base/sun.nio.ch=ALL-UNNAMED",
    "java.base/java.lang=ALL-UNNAMED",
    "java.base/java.util=ALL-UNNAMED"
  )

  jvmOptions.foreach(opt => System.setProperty("--add-opens", opt))

  System.setProperty("spark.driver.host", "localhost")
  System.setProperty("spark.driver.bindAddress", "localhost")

  System.setProperty("spark.driver.extraJavaOptions",
    "--add-opens=java.base/java.nio=ALL-UNNAMED --add-opens=java.base/sun.nio.ch=ALL-UNNAMED")

  var spark = SparkSession.builder()
    .appName("Analyse de stock")
    .master("local[*]")
    .getOrCreate()

  val url = "jdbc:mysql://192.168.194.152:3306/scala"
  val username = "root"
  val password = "secret"

  var connection: Connection = null

  try {
    // Connexion à la base de données
    connection = DriverManager.getConnection(url, username, password)

    println("Connexion réussie!")

  } catch {
    case e: Exception => e.printStackTrace()
  }

  //Constante non modifiable
  val constante, test = "Hello";
  //constante = "Test";

  //Valeurs modifiables
  var nombre: Int = 0;
  var nombre2 = 46;
  var chaine = "Bonjour";
  var chaine2 = "Bonjour";
  chaine = "Test";
  var resultat = 0;
  var choix = 1;
  val nombres = ListBuffer[Int]()
  var max = 0;
  var min = 1000;
  while (choix != 0) {
    println("-------MENU--------");
    println("1 - Test Scala");
    println("2 - Nombre entre 1 et 3")
    println("3 - Nombre entre 50 et 60")
    println("4 - Table de multiplication jusqu'à 40 WHILE")
    println("5 - Table de multiplication jusqu'à 40 FOR")
    println("6 - Trie")
    println("7 - Jeu du pendu")
    println("8 - Creer table users")
    println("9 - Inserer un user")
    println("10 - Afficher 10 premières lignes CSV")
    println("0 - QUITTER")
    choix = StdIn.readLine().toInt;

    choix match {
      case 0 =>
        if (connection != null) connection.close()
      case 1 =>
        println(chaine2 + chaine);
        println(s"Chaine 1 : $chaine, \nNombre 1 : $nombre Nombre 2 : $nombre2");

        println("Merci de saisir une chaine de caractere :");
        chaine = StdIn.readLine();
        println(chaine);

        println("Merci de saisir un nombre entier :");
        nombre = StdIn.readLine().toInt;
        println(nombre)
      case 2 =>
        nombre = 0;
        while (nombre < 1 || nombre > 3) {
          println("Merci de saisir un nombre entre 1 et 3 :")
          nombre = StdIn.readLine().toInt;
          if (nombre >= 1 && nombre <= 3)
            println("Bravo")
          else
            println("Erreur")
        }
      case 3 =>
        nombre = 0;
        while (nombre < 50 || nombre > 60) {
          println("Merci de saisir un nombre entre 50 et 60 :");
          nombre = StdIn.readLine().toInt;
          if (nombre < 50)
            println("Saisir plus grand")
          else if (nombre > 60)
            println("Saisir plus petit")
          else
            println("Bravo")
        }
      case 4 =>
        nombre = 0;
        println("Merci de saisir un nombre :");
        nombre = StdIn.readLine().toInt;
        var compteur = 1;

        while (compteur <= 40) {
          resultat = (compteur * nombre);
          println(s"$compteur x $nombre = $resultat");
          compteur = compteur + 1;
        }
      case 5 =>
        nombre = 0;
        println("Merci de saisir un nombre :");
        nombre = StdIn.readLine().toInt;
        var max = 40;
        for (i <- 1 to 40) {
          resultat = (i * nombre);
          println(s"$i x $nombre = $resultat");
        }

      case 6 =>
        nombre = 0;

        println("Veuillez entrer 10 nombres :")
        for (_ <- 1 to 10) {
          print("Entrez un nombre : ")
          nombre = StdIn.readLine().toInt;
          //if (nombre < min) min = nombre;
          //if (nombre > max) max = nombre;
          nombres += nombre
        }

        max = nombres.max
        min = nombres.min

        val nombresTries = nombres.sorted

        println(s"Le plus grand nombre est : $max")
        println(s"Le plus petit nombre est : $min")
        println(s"Liste triée par ordre croissant : ${nombresTries.mkString(", ")}")

      case 7 =>

        var mots = ListBuffer[String]();
        var lettre = "";
        mots += "bonjour";
        mots += "souris"

        val randomIndex = Random().nextInt(mots.length);
        val randomMot = mots(randomIndex);

        var motCache = "_ " * randomMot.length;
        println(motCache);

        println("Saisir une lettre");
        lettre = StdIn.readLine();

        if (randomMot.contains(lettre)) {
          println("Bonne réponse")

        }

      case 8 =>
        try {
          // Préparer une requête SQL
          val statement = connection.createStatement()
          val requete = "CREATE TABLE users\n(\n    id int,\n    name varchar(50),\n    email varchar(255),\n    password varchar(255)\n)"

          // Exécuter la requête et obtenir les résultats
          //val resultSet: ResultSet = statement.executeQuery(requete)
          statement.execute(requete)

          println("Table crée !")

        } catch {
          case e: Exception =>
            e.printStackTrace()
        }

      case 9 =>
        try {
          // Préparer une requête SQL
          val statement = connection.createStatement()
          val requete = "INSERT INTO users (id, name, email, password) " +
            "VALUES (1, 'Fabrice', 'fabrice@istp.com', 'password')"

          // Exécuter la requête et obtenir les résultats
          statement.execute(requete)

          println("User créé !")


        } catch {
          case e: Exception =>
            e.printStackTrace()
        }

      case 10 =>


        val stocksDF = spark.read.option("header", "true")
        .option("inferSchema", "true")
        .csv("stocks_large_with_model.csv")

        stocksDF.show(10)

        // Afficher le nombre total de lignes
        println(s"\n=== Nombre total de lignes : ${stocksDF.count()} ===")

        // Afficher quelques statistiques de base
        println("\n=== Statistiques de base ===")
        stocksDF.describe().show()

        // Si vous voulez voir toutes les colonnes sans troncature
        println("\n=== Données complètes (5 premières lignes) ===")
        stocksDF.show(5, false)  // false désactive la troncature
    }
  }












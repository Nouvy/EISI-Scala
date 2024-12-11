import scala.collection.mutable.ListBuffer
import scala.io.StdIn

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
@main
def main(): Unit =

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
    println("0 - QUITTER")
    choix = StdIn.readLine().toInt;

    choix match
      case 0 =>
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
  }












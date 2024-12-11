import scala.io.StdIn

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
@main
def main(): Unit =

  //Constante non modifiable
  val constante = "Hello";
  //constante = "Test";

  //Valeurs modifiables
  var nombre: Int = 0;
  var nombre2 = 46;
  var chaine = "Bonjour";
  var chaine2 = "Bonjour";
  chaine = "Test";



  var choix = 1;

  while (choix != 0) {
    println("-------MENU--------");
    println("1 - Test Scala");
    println("2 - Nombre entre 1 et 3")
    println("3 - Nombre entre 50 et 60")
    println("4 - Table de multiplication jusqu'à 40")
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
  }









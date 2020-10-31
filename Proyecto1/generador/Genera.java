package generador;

import java.util.Set;
import java.util.HashSet;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;


public class Genera {
  public static void generar() {
    File file = new File("Archivos");
    file.mkdir();

    Set<Persona> total = new HashSet<Persona>();
    Datos data = new Datos();

    int totalp = 1000;

    total = data.creador(totalp);

    try {

      FileWriter datos = new FileWriter("Archivos/F0.txt");

      for (Persona person : total) {
        // System.out.print(person.imprimePersona2());
        datos.write(person.imprimePersona2());
      }
      datos.close();
    } catch (IOException e) {
      System.out.println("Intenta de nuevo");
    }

  }
}

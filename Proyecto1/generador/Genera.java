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
    try {
      FileWriter datos = new FileWriter("Archivos/F0.txt");
      Set<Persona> total = new HashSet<Persona>();
      Datos data = new Datos();

      int totalp = 100;

      total = data.creador(totalp);


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

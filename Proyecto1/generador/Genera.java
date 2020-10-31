package generador;

import java.util.Set;
import java.util.HashSet;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;


public class Genera {
  public void generar(String path, int personas) {
    // File file = new File("Archivos");
    // file.mkdir();

    Set<Persona> total = new HashSet<Persona>();
    Datos data = new Datos();
    DataPlus dataFull = new DataPlus();
    total = dataFull.creador(personas);


    try {

      FileWriter datos = new FileWriter(path);

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

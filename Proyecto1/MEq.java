import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;
import generador.*;


public class MEq {
  public static void main(String[] args) throws FileNotFoundException {
    // Genera.generar();
    String path = "Archivos/F0.txt";
    // String npath1 = "Archivos/F1.txt";
    // String npath2 = "Archivos/F2.txt";
    String[] pathsAux = new String[2];

    Metodos prueba = new Metodos();

    // int gogo = prueba.inicia(path);
    // if (gogo > 1) {
      pathsAux = prueba.nombreI1(path); // Crea F1 y F2
    //   while (gogo != 1) {
        pathsAux = prueba.nombreI2(path, pathsAux[0] ,pathsAux[1]); // Modifica F0
        // gogo = prueba.inicia(path);
        // pathsAux = prueba.nombreI3(path, pathsAux[0] ,pathsAux[1]); // Modifica F1 y F2
    //   }
    // } else {
    //   System.out.println("El archivo no contiene los datos suficientes;");
    // }
  } // Fin del main
}

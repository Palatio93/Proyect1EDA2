import generador.*;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;


public class MEq {
  public static void main(String[] args) throws FileNotFoundException {
    Genera.generar();
    String path = "Archivos/F0.txt";
    String[] pathsAux = new String[3]; // en 0 es path de archivo original, en 1 es de F1 y en 2 es de F2

    /** TODO: Si F0 tiene una y solo una linea, entonces, se ha ordenado todo
    * el archivo, por lo que, vaciamos lo de F0  a un archivo temporal, y regresamos
    * todo a F0 con el formato toString().
    */

    Metodos prueba = new Metodos();

    pathsAux = prueba.nombreI1(path); // Crea F1 y F2, no puede estar dentro del ciclo;; {F0, F1, F2}
    int lineas = prueba.comprueba(path);
    System.out.println("Se han detectado " + lineas + " registros a ordenar");

    // Aqui debe empezar un ciclo, mientras que path no tenga 1 linea

    while (lineas > 1) {
      pathsAux = prueba.nombreI2(pathsAux[0] ,pathsAux[1], pathsAux[2]); // Modifica F0;; {F0, F1, F2}
      pathsAux = prueba.nombreI3(pathsAux[0], pathsAux[1], pathsAux[2]);  // Crea los bloques en F1 y F2 para proceder a ordenarlos despues
      lineas = prueba.comprueba(pathsAux[0]);  // Para revisar cuantas lineas tiene el archivo F0
      if (lineas == 1) {
        break;
      }
    }
    System.out.println("El archivo " + path + " ha sido ordenado y puede revisarse.");
    prueba.regresaAFormato(pathsAux[0]);

  } // Fin del main
}

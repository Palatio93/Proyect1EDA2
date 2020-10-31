import generador.*;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class MainMezclaEquilibrada {
  public static void main(String[] args) throws FileNotFoundException {
    Scanner sc = new Scanner(System.in);

    System.out.println("Dame el nombre del archivo a ordenar con la extension .txt. (Debe estar ubicado en el mismo directorio que este metodo)\nWARNING: se modificara el archivo original.");
    String path = sc.nextLine();
    Genera aGenerar = new Genera();
    aGenerar.generar(path, 1000);
    String[] pathsAux = new String[3]; // en 0 es path de archivo original, en 1 es de F1 y en 2 es de F2

    MezclaEquilibrada prueba = new MezclaEquilibrada();
    String path0 = prueba.clonOriginal(path);
    System.out.println("Ingresa 1 para ordenar por nombre, 2 para ordenar por apellido");
    int choice = Integer.parseInt(sc.nextLine());
    boolean porApellido = choice == 1 ? false : true;

    pathsAux = prueba.ordenaI1(path0, porApellido); // Crea F1 y F2, no puede estar dentro del ciclo;; {F0, F1, F2}
    int lineas = prueba.comprueba(path0);
    System.out.println("Se han detectado " + lineas + " registros a ordenar");

    // Aqui debe empezar un ciclo, mientras que path no tenga 1 linea

    while (lineas > 1) {
      pathsAux = prueba.ordenaI2(pathsAux[0] ,pathsAux[1], pathsAux[2], porApellido); // Modifica F0;; {F0, F1, F2}
      pathsAux = prueba.ordenaI3(pathsAux[0], pathsAux[1], pathsAux[2]);  // Crea los bloques en F1 y F2 para proceder a ordenarlos despues
      lineas = prueba.comprueba(pathsAux[0]);  // Para revisar cuantas lineas tiene el archivo F0
      if (lineas == 1) {
        break;
      }
    }
    System.out.println("El archivo " + path + " ha sido ordenado y puede revisarse en Archivos/F0.txt.");
    prueba.regresaAFormato(pathsAux[0]);



    // if(choice == 1){
    //   pathsAux = pruebaN.nombreI1(path); // Crea F1 y F2, no puede estar dentro del ciclo;; {F0, F1, F2}
    //   int lineas = pruebaN.comprueba(path);
    //   System.out.println("Se han detectado " + lineas + " registros a ordenar");
    //
    //   // Aqui debe empezar un ciclo, mientras que path no tenga 1 linea
    //
    //   while (lineas > 1) {
    //     pathsAux = pruebaN.nombreI2(pathsAux[0] ,pathsAux[1], pathsAux[2]); // Modifica F0;; {F0, F1, F2}
    //     pathsAux = pruebaN.nombreI3(pathsAux[0], pathsAux[1], pathsAux[2]);  // Crea los bloques en F1 y F2 para proceder a ordenarlos despues
    //     lineas = pruebaN.comprueba(pathsAux[0]);  // Para revisar cuantas lineas tiene el archivo F0
    //     if (lineas == 1) {
    //       break;
    //     }
    //   }
    //   System.out.println("El archivo " + path + " ha sido ordenado y puede revisarse.");
    //   pruebaN.regresaAFormato(pathsAux[0]);
    //
    // } else if (choice == 2) {
    //   pathsAux = pruebaAp.apellidoI1(path); // Crea F1 y F2, no puede estar dentro del ciclo;; {F0, F1, F2}
    //   int lineas = pruebaAp.comprueba(path);
    //   System.out.println("Se han detectado " + lineas + " registros a ordenar");
    //
    //   // Aqui debe empezar un ciclo, mientras que path no tenga 1 linea
    //
    //   while (lineas > 1) {
    //     pathsAux = pruebaAp.apellidoI2(pathsAux[0] ,pathsAux[1], pathsAux[2]); // Modifica F0;; {F0, F1, F2}
    //     pathsAux = pruebaAp.apellidoI3(pathsAux[0], pathsAux[1], pathsAux[2]);  // Crea los bloques en F1 y F2 para proceder a ordenarlos despues
    //     lineas = pruebaAp.comprueba(pathsAux[0]);  // Para revisar cuantas lineas tiene el archivo F0
    //     if (lineas == 1) {
    //       break;
    //     }
    //   }
    //   System.out.println("El archivo " + path + " ha sido ordenado y puede revisarse.");
    //   pruebaAp.regresaAFormato(pathsAux[0]);
    //
    // } else {
    //   System.out.println("Opcion no valida");
    // }


  } // Fin del main
}

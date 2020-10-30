import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.List;
import java.util.LinkedList;
import java.util.Queue;

public class Metodos {
  String dir = "Archivos/";
  String F1 = "F1";
  String F2 = "F2";
  String extension = ".txt";

  public int inicia(String path) throws FileNotFoundException {
    Scanner sc = new Scanner(new File(path));

    int cuentaLineas = 0;
    String line = "";
    while (sc.hasNextLine()) {
      line += sc.nextLine();
      cuentaLineas++;
    }
    if (cuentaLineas == 1) {
      return cuentaLineas;
    } else {
      return cuentaLineas;
    }
  }

  public String[] nombreI1(String path) throws FileNotFoundException {  // De F0 a F1 y F2
    int llamadas = 0;
    String pathF1 = dir + F1 + extension;
    String pathF2 = dir + F2 + extension;

    // Instancias
    Scanner sc = new Scanner(new File(path));
    List<Persona> personas = new LinkedList<>();

    int i = 1;
    llamadas++;
    while (sc.hasNextLine()) {
      String line1 = sc.nextLine();
      Persona per = new Persona();
      String[] addto = line1.split(",");
      per.setNombre(addto[0]);
      per.setApellido(addto[1]);
      per.setNumCuenta(Long.parseLong(addto[2].trim()));
      if (personas.isEmpty()) {
        personas.add(per);
      } else {
        if(personas.get(personas.size() - 1).getNombre().compareTo(per.getNombre()) <= 0) { // comparamos el ultimo con el leido, si es menor o del mismo nombre, se debe seguir agregando a personas
          personas.add(per);
        } else {  // Para imprimir en archivos
          if (i % 2 != 0) { // Para archivo F1
            try {
              FileWriter f1 = new FileWriter(pathF1, true);
              for (Persona per1 : personas) {
                f1.write(per1.impArchivoAux());
              }
              f1.write("\n");
              personas.clear();
              personas.add(per);
              f1.close();
              i++;
            } catch (IOException e) {
              System.out.println("Intenta de nuevo");
            }
          } else {
            try { // Para archivo F2
              FileWriter f2 = new FileWriter(pathF2, true);
              for (Persona per1 : personas) {
                f2.write(per1.impArchivoAux());
              }
              f2.write("\n");
              personas.clear();
              personas.add(per);
              f2.close();
              i++;
            } catch (IOException e) {
              System.out.println("Intenta de nuevo");
            } // Fin try-catch
          } // Fin else que imprime en archivo F2
        } // Fin else donde se imprime en archivos
      } // Fin primer else
    } // Fin del while

    System.out.println("Archivo " + path + " leido.");
    System.out.println("Se han creado: " + pathF1 + " y " + pathF2);

    String[] auxPaths = {pathF1, pathF2};
    return auxPaths;
  } // Fin metodo // Pasa de F0 a F1 y F2;

  public String[] nombreI2(String path0, String path1, String path2) throws FileNotFoundException { // Pasa de F1 y F2 a F0

    Scanner sc1 = new Scanner(new File(path1)); // Para leer en archivo
    Scanner sc2 = new Scanner(new File(path2)); // Para leer en archivo

    System.out.println("imprime paths");
    System.out.println(path0);
    System.out.println(path1);
    System.out.println(path2);

    try {
      // FileWriter f0 = new FileWriter(path0);  // Para limpiar el archivo F0
      // f0.close();
      List<Persona> personas = new LinkedList<>();
      while (sc1.hasNextLine() || sc2.hasNextLine()) {
        Queue<Persona> personasf1 = new LinkedList<Persona>();
        Queue<Persona> personasf2 = new LinkedList<Persona>();
        personasf1 = creaCola(sc1.nextLine().split("/"));
        personasf2 = creaCola(sc2.hasNextLine() ? sc2.nextLine().split("/") : new String[0]);

        boolean flag = personasf1.size() <= personasf2.size();

        if (flag) {
          personas = procesaColas(personasf1, personasf2);
        } else {
          personas = procesaColas(personasf2, personasf1);
        }
        FileWriter f0 = new FileWriter(path0, true);
        for (Persona per : personas) {
          f0.write(per.impArchivoAux());
        }
        f0.write("\n");
        personas.clear();
        f0.close();
      } // Fin while

      System.out.println("Se ha modificado el archivo: " + path0);
    } catch(Exception e) {
      System.out.println("Algo salio mal");
      e.printStackTrace();
    }
    String[] pathsAux  = {path0, path1, path2};
    System.out.println("Antes del return");
    for (String str : pathsAux) {
      System.out.println(str);
    }
    return pathsAux;
  } // Fin metodo

  public String[] nombreI3(String path0, String path1, String path2) throws FileNotFoundException {  // Lee de F0 para modificar F1 y F2
    String path = path0;
    Scanner sc = new Scanner(new File(path));
    try { // Para limpiar los archivos F1 y F2
      FileWriter f1 = new FileWriter(path1);
      FileWriter f2 = new FileWriter(path2);
      f1.close();
      f2.close();

    } catch(Exception e) {

    }

    int parametro = 1;

    try {
      String pathF1 = dir + F1 + extension;
      String pathF2 = dir + F2 + extension;

      while (sc.hasNextLine()) {
        if (parametro % 2 != 0) {
          FileWriter f1 = new FileWriter(pathF1, true);
          f1.write(sc.nextLine());
          f1.write("\n");
          f1.close();
          parametro++;
        } else {
          FileWriter f2 = new FileWriter(pathF2, true);
          f2.write(sc.nextLine());
          f2.write("\n");
          f2.close();
          parametro++;
        } // Fin if-else
      } // Fin del while
      System.out.println("Se han modificado " + pathF1 + " y " + pathF2);
    } catch(Exception e) {
      System.out.println("Algo salio mal");
    } // Fin del try-catch
    String[] pathsAux = {path, path1, path2};
    return pathsAux;
  } // Fin del metodo

  public List<Persona> procesaColas(Queue<Persona> colaMenor, Queue<Persona> colaMayor) { // Hace el ordenamiento entre colas
    List<Persona> personas = new LinkedList<>();
    int size = colaMayor.size();
    for (int i = 0; i < size; i++) {
      if (i <= colaMenor.size() && !colaMenor.isEmpty()) {
        personas = agregaPersona(personas, colaMenor.poll());
      } // Fin del if
      personas = agregaPersona(personas, colaMayor.poll());

    } // Fin del for
    return personas;
  }

  public Queue<Persona> creaCola(String[] linea) {
    // Instancias
    Queue<Persona> personasf = new LinkedList<Persona>();

    if (linea.length == 0) {
      return personasf;
    }
    // Creando lista de personas por linea del archivo
    for (String straux : linea) {
      String[] addto1 = straux.split(",");
      Persona per = new Persona();
      per.setNombre(addto1[0]);
      per.setApellido(addto1[1]);
      per.setNumCuenta(Long.parseLong(addto1[2].trim()));
      personasf.add(per);
    }
    return personasf;
  } // Fin metodo

  public List<Persona> agregaPersona(List<Persona> personas, Persona firstP) {  // Agrega la primera persona de la cola de donde se llamó
    if (personas.isEmpty()) {
      personas.add(firstP);
      return personas;
    }
    if (personas.get(0).getNombre().compareTo(firstP.getNombre()) >= 0) { // Si el que se compara es menor que el primero de personas, se agrega al inicio
      personas.add(0, firstP);
      return personas;
    } else {
      List<Persona> primerP = new LinkedList<Persona>();  // Crea una lista de personas vacias para almacenar el primero de personas
      primerP.add(personas.remove(0));
      primerP.addAll(agregaPersona(personas, firstP));  // Recursivamente se llama a si misma, con personas sin el primer elemento siempre, de tal forma que el caso base se cumpla, en donde se sacaron todos los menores al elemento que se quiere agregar y el primero de personas será mayor
      return primerP;
    }
  }

}

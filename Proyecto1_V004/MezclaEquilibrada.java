import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.util.Scanner;
import java.util.List;
import java.util.LinkedList;
import java.util.Queue;

public class MezclaEquilibrada {
  String dir = "Archivos";
  String F1 = "/F1";
  String F2 = "/F2";
  String extension = ".txt";

  public String clonOriginal(String path) {
    File file = new File(dir);
    file.mkdir();

    BufferedReader input = null;
    String path0 = dir + "/F0" + extension;

    try {
      FileReader copyF = new FileReader(path);
      input = new BufferedReader(copyF);
      FileWriter f0 = new FileWriter(path0);
      BufferedWriter escribef0 = new BufferedWriter(f0);
      String linea;
      while((linea = input.readLine()) != null) {
        escribef0.write(linea);
        escribef0.newLine();
      }
      escribef0.close();

    } catch(Exception e) {
      e.printStackTrace();
    }

    return path0;
  }

  public int comprueba(String path) throws FileNotFoundException {  // Revisa cuantas lineas tiene el archivo F0
    Scanner sc = new Scanner(new File(path));

    int contador = 0;

    try {
      while (sc.hasNextLine()) {
        sc.nextLine();
        contador++;
      }
      return contador;
    } catch(Exception e) {
      e.printStackTrace();
    }
    return contador;
  }

  public String[] ordenaI1(String path0, boolean porApellido){ // De F0 a F1 y F2, ESTE MÉTODO solo se llama 1 vez, al principio
    String path1 = dir + F1 + extension;
    String path2 = dir + F2 + extension;

    // Instancias
    // Scanner sc = new Scanner(new File(path));
    BufferedReader input = null;
    int alterna = 1;
    int comparacion = -1;

    try {
      FileReader file0 = new FileReader(path0);
      input = new BufferedReader(file0);
      String linea;
      List<Persona> personas = new LinkedList<>();
      while ((linea = input.readLine()) != null) {
        Persona per = new Persona();
        String[] addto = linea.split(",");
        per.setNombre(addto[0]);
        per.setApellido(addto[1]);
        per.setNumCuenta(Long.parseLong(addto[2].trim()));
        if (!personas.isEmpty()) {
          if (porApellido) {
            comparacion = personas.get(personas.size()-1).getApellido().compareTo(per.getApellido());
          } else {
            comparacion = personas.get(personas.size()-1).getNombre().compareTo(per.getNombre());
          }
        } // Fin if not empty
        if (personas.isEmpty()) {
          personas.add(per);

        } else if(comparacion < 0) { // comparamos el ultimo con el leido, si es menor o del mismo nombre, se debe seguir agregando a personas
          personas.add(per);

        } else {
          personas = escribeArchivoF1F2(personas, per, alterna, path1, path2);
          alterna++;
        } // Fin de impresion en archivos
     // Fin if-else en donde personas no es empty
    } // Fin while que lee
    escribeArchivoFinal(personas, alterna, path1, path2);
    } catch(Exception e) {
        System.out.println("Algo anda mal");
    } finally {
      try {
        if (input == null) {
          input.close();
        }
      } catch(Exception e) {
        System.out.println("Algo anda mal");
      }
    } // Termina el finally, que termina del try-catch

    // System.out.println("Archivo " + path + " leido.");
    // System.out.println("Se han creado: " + path1 + " y " + path2);

    String[] auxPaths = {path0, path1, path2};
    return auxPaths;
  } // Fin metodo // Pasa de F0 a F1 y F2;

  public String[] ordenaI2(String path0, String path1, String path2,boolean porApellido) { // Pasa de F1 y F2 a F0, ESTE debe llamarse varias

    BufferedReader inputf1 = null;
    BufferedReader inputf2 = null;

    try {
      FileWriter f0 = new FileWriter(path0);  // Para limpiar el archivo F0
      BufferedWriter escribef0 = new BufferedWriter(f0);
      escribef0.close();
      // System.out.println("Se limpio " + path0);
      List<Persona> personas = new LinkedList<>();

      FileReader filef1 = new FileReader(path1);
      inputf1 = new BufferedReader(filef1);
      FileReader filef2 = new FileReader(path2);
      inputf2 = new BufferedReader(filef2);

      String linef1 = null;
      String linef2 = null;
      int escrituras = 0;

      while ((linef1 = inputf1.readLine()) != null && (linef2 = inputf2.readLine()) != null) {
        Queue<Persona> personasf1 = new LinkedList<Persona>();
        Queue<Persona> personasf2 = new LinkedList<Persona>();

        personasf1 = creaCola(linef1.split("/"));
        personasf2 = creaCola(linef2.split("/"));

        boolean flag = personasf1.size() <= personasf2.size();

        if (flag) {
          personas = procesaColas(personasf1, personasf2, porApellido);
        } else {
          personas = procesaColas(personasf2, personasf1, porApellido);
        }

        f0 = new FileWriter(path0, true);
        escribef0 = new BufferedWriter(f0);

        for (Persona per : personas) {
          escribef0.write(per.impArchivoAux());
          escrituras++;
        }
        escribef0.newLine();
        escribef0.close();
        personas.clear();

      } // Fin while

      if (linef1 != null) {
        Queue<Persona> personasf1 = new LinkedList<Persona>();
        Queue<Persona> personasf2 = new LinkedList<Persona>();
        personasf1 = creaCola(linef1.split("/"));
        personas = procesaColas(personasf2, personasf1, porApellido);

        f0 = new FileWriter(path0, true);
        escribef0 = new BufferedWriter(f0);
        for (Persona per : personas) {
          escribef0.write(per.impArchivoAux());
          escrituras++;
        }
        escribef0.newLine();
        personas.clear();
        escribef0.close();
      }
      // System.out.println("Se escribieron " + escrituras + " veces");
      // System.out.println("Se ha modificado el archivo: " + path0);
    } catch(Exception e) {
      System.out.println("Algo salio mal");
      e.printStackTrace();
    }
    String[] pathsAux  = {path0, path1, path2};
    return pathsAux;
  } // Fin metodo

  public String[] ordenaI3(String path0, String path1, String path2) {  // Lee de F0 para modificar F1 y F2, separa por lineas de F0 en bloques

    int parametro = 1;
    BufferedReader inputf0 = null;

    try {   // Se elimina el contenido de F1 y F2, puesto que está dentro de F0 intercalado.
      FileWriter f1 = new FileWriter(path1);
      BufferedWriter escribef1 = new BufferedWriter(f1);
      escribef1.close();
      // System.out.println("Se elimino " + path1);
      FileWriter f2 = new FileWriter(path2);
      BufferedWriter escribef2 = new BufferedWriter(f2);
      escribef2.close();
      // System.out.println("Se elimino " + path2);

      FileReader filef0 = new FileReader(path0);
      inputf0 = new BufferedReader(filef0);

      String linef0;

      while ((linef0 = inputf0.readLine()) != null) {
        if (parametro % 2 != 0) {
          f1 = new FileWriter(path1, true);
          escribef1 = new BufferedWriter(f1);
          escribef1.write(linef0);  // ACA FUE EL CAMBIO
          escribef1.write("\n");
          escribef1.close();
          parametro++;
        } else {
          f2 = new FileWriter(path2, true);
          escribef2 = new BufferedWriter(f2);
          escribef2.write(linef0);  // ACA FUE EL CAMBIO
          escribef2.write("\n");
          escribef2.close();
          parametro++;
        } // Fin if-else
      } // Fin del while
      // System.out.println("Se han modificado " + path1 + " y " + path2);
    } catch(Exception e) {
      System.out.println("Algo salio mal");
    } // Fin del try-catch

    String[] pathsAux = {path0, path1, path2};
    return pathsAux;
  } // Fin del metodo

  public void regresaAFormato(String path0) {
    String pathtmp = dir + "/tmp" + extension;

    try {
      Scanner sc  = new Scanner(new File(path0));
      while(sc.hasNextLine()) { // Copia la unica linea de F0 a un tmp.txt
        String linea = sc.nextLine();
        FileWriter fAux = new FileWriter(pathtmp);
        fAux.write(linea);
        fAux.close();
        }
      } catch(Exception e) {

    } // Fin del try-catch

    try { // F0 ya fue copiado, se puede sobreescribir
      Scanner scAux = new Scanner(new File(pathtmp));
      FileWriter f0 = new FileWriter(path0);  // Se ha limpiado F0
      f0.close();
      while (scAux.hasNextLine()) {
        String[] personas = scAux.nextLine().split("/");
        for (String persona : personas) {
          String[] creaAdanEva = persona.split(",");
            Persona sujetoprueba = new Persona();
            sujetoprueba.setNombre(creaAdanEva[0]);
            sujetoprueba.setApellido(creaAdanEva[1]);
            sujetoprueba.setNumCuenta(Long.parseLong(creaAdanEva[2].trim()));
            f0 = new FileWriter(path0, true);
            f0.write(sujetoprueba.toString());
            f0.close();
          } // Fin foreach de cada sujeto
      } // fin while de lectura
    } catch(Exception e) {

    } // Fin del try-catch
  } // Fin del metodo

  public List<Persona> procesaColas(Queue<Persona> colaMenor, Queue<Persona> colaMayor, boolean porApellido) { // Hace el ordenamiento entre colas
    List<Persona> personas = new LinkedList<>();
    int sizeMayor = colaMayor.size();
    int sizeMenor = colaMenor.size();

    if (colaMenor.isEmpty()) {
      personas.addAll(colaMayor);
      return personas;
    }

    for (int i = 0; i < sizeMayor; i++) {
      if (i < sizeMenor) {
        personas = agregaPersona(personas, colaMenor.poll(), porApellido);
      } // Fin del if
      personas = agregaPersona(personas, colaMayor.poll(), porApellido);

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

  public List<Persona> agregaPersona(List<Persona> personas, Persona firstP, boolean porApellido) {  // Agrega la primera persona de la cola de donde se llamó
    int comparacion = -1;
    if (personas.isEmpty()) {
      personas.add(firstP);
      return personas;
    }
    if (porApellido) {
      comparacion = personas.get(0).getApellido().compareTo(firstP.getApellido());
    } else {
      comparacion = personas.get(0).getNombre().compareTo(firstP.getNombre());
    }

    if (comparacion >= 0) { // Si el que se compara es menor que el primero de personas, se agrega al inicio
      personas.add(0, firstP);
      return personas;
    } else {
      List<Persona> primerP = new LinkedList<Persona>();  // Crea una lista de personas vacias para almacenar el primero de personas
      primerP.add(personas.remove(0));
      primerP.addAll(agregaPersona(personas, firstP, porApellido));  // Recursivamente se llama a si misma, con personas sin el primer elemento siempre, de tal forma que el caso base se cumpla, en donde se sacaron todos los menores al elemento que se quiere agregar y el primero de personas será mayor
      return primerP;
    }
  }

  public List<Persona> escribeArchivoF1F2(List<Persona> personas, Persona per,int alterna,String path1,String path2) {
    if (alterna % 2 != 0) {
      try { // Para archivo F1
        FileWriter f1 = new FileWriter(path1, true);
        BufferedWriter escribe = new BufferedWriter(f1);
        for (Persona per1 : personas) {
          escribe.write(per1.impArchivoAux());
        }
        escribe.newLine();
        personas.clear();
        escribe.close();
        f1.close();
        personas.add(per);
      } catch (IOException e) {
        System.out.println("Algo anda mal");
      }
      return personas;
    } else {
      try { // Para archivo F2
        FileWriter f2 = new FileWriter(path2, true);
        BufferedWriter escribe = new BufferedWriter(f2);
        for (Persona per1 : personas) {
          escribe.write(per1.impArchivoAux());
        }
        escribe.newLine();
        personas.clear();
        escribe.close();
        f2.close();
        personas.add(per);
      } catch (IOException e) {
        System.out.println("Algo anda mal");
      } // Fin try-catch
      return personas;
    } // Fin else que imprime en archivo f2
  } // Fin del metodo

  public void escribeArchivoFinal(List<Persona> personas,int alterna,String path1,String path2) {
    if (alterna % 2 != 0) {
      try { // Para archivo F1
        FileWriter f1 = new FileWriter(path1, true);
        BufferedWriter escribe = new BufferedWriter(f1);
        for (Persona per1 : personas) {
          escribe.write(per1.impArchivoAux());
        }
        escribe.newLine();
        personas.clear();
        escribe.close();
        f1.close();
      } catch (IOException e) {
        System.out.println("Algo anda mal");
      }
    } else {
      try { // Para archivo F2
        FileWriter f2 = new FileWriter(path2, true);
        BufferedWriter escribe = new BufferedWriter(f2);
        for (Persona per1 : personas) {
          escribe.write(per1.impArchivoAux());
        }
        escribe.newLine();
        personas.clear();
        escribe.close();
        f2.close();
      } catch (IOException e) {
        System.out.println("Algo anda mal");
      } // Fin try-catch
    } // Fin else que imprime en archivo f2
  } // Fin del metodo

}

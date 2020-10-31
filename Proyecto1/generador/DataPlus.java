package generador;

import java.util.Random;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import java.io.BufferedReader;
import java.io.FileReader;


public class DataPlus {

  public DataPlus() {

  }

  public Set<Persona> creador(int n) {
    BufferedReader input = null;
    String pathH = "generador/NombresHombres.txt";
    String pathM = "generador/NombresMujeres.txt";
    String pathAp = "generador/Apellidos.txt";
    Random rand = new Random();

    String linea = "";
    String hombresLinea = "";
    String mujeresLinea = "";
    String apellidosLinea = "";
    
    try { // Hombres
      FileReader file = new FileReader(pathH);
      input = new BufferedReader(file);
      while((linea = input.readLine()) != null) {
        hombresLinea += linea + ",";
      }
      input.close();
    } catch(Exception e) {
      e.printStackTrace();
      System.out.println("Hubo error");
    }

    try {
      FileReader file = new FileReader(pathM);
      input = new BufferedReader(file);
      while((linea = input.readLine()) != null) {
        mujeresLinea += linea + ",";
      }
      input.close();
    } catch(Exception e) {
      e.printStackTrace();
      System.out.println("Hubo error");
    }
    try {
      FileReader file = new FileReader(pathAp);
      input = new BufferedReader(file);
      while((linea = input.readLine()) != null) {
        apellidosLinea += linea + ",";
      }
      input.close();
    } catch(Exception e) {
      e.printStackTrace();
      System.out.println("Hubo error");
    }

    String[] nombresHombres = hombresLinea.split(",");
    String[] nombresMujeres = mujeresLinea.split(",");
    String[] apellidos = apellidosLinea.split(",");

    Set<Persona> hombres = new HashSet<Persona>();

    int nH = n/2;
    int nM = n/2;
    long sumador = 100_000;

    while (hombres.size() < (nH * 0.5)) {
      String nombre = nombresHombres[rand.nextInt(nombresHombres.length)];
      String apellido = apellidos[rand.nextInt(apellidos.length)];
      String apellido2 = apellidos[rand.nextInt(apellidos.length)];
      int num_cta = rand.nextInt(800000);
      Persona hombre = new Persona(nombre, apellido, apellido2, num_cta + sumador);
      hombres.add(hombre);
    }

    while (hombres.size() < nH) {
      String nombre = nombresHombres[rand.nextInt(nombresHombres.length)];
      String nombre2 = nombresHombres[rand.nextInt(nombresHombres.length)];
      String apellido = apellidos[rand.nextInt(apellidos.length)];
      String apellido2 = apellidos[rand.nextInt(apellidos.length)];
      int num_cta = rand.nextInt(800000);
      Persona hombre = new Persona(nombre, nombre2, apellido, apellido2, num_cta + sumador);
      hombres.add(hombre);
    }

    Set<Persona> mujeres = new HashSet<Persona>();

    while (mujeres.size() < (nM * 0.5)) {
      String nombre = nombresMujeres[rand.nextInt(nombresMujeres.length)];
      String apellido = apellidos[rand.nextInt(apellidos.length)];
      String apellido2 = apellidos[rand.nextInt(apellidos.length)];
      int num_cta = rand.nextInt(800000);
      Persona mujer = new Persona(nombre, apellido, apellido2, num_cta + sumador);
      mujeres.add(mujer);
    }

    while (mujeres.size() < nM) {
      String nombre = nombresMujeres[rand.nextInt(nombresMujeres.length)];
      String nombre2 = nombresMujeres[rand.nextInt(nombresMujeres.length)];
      String apellido = apellidos[rand.nextInt(apellidos.length)];
      String apellido2 = apellidos[rand.nextInt(apellidos.length)];
      int num_cta = rand.nextInt(800000);
      Persona mujer = new Persona(nombre, nombre2, apellido, apellido2, num_cta + sumador);
      mujeres.add(mujer);
    }

    Set<Persona> union = new HashSet<Persona>(mujeres);
    union.addAll(hombres);

    return union;
  } // Fin del metodo




}

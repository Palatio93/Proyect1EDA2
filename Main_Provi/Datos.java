import java.util.Random;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;

public class Datos {

  public Set<Persona> creador(int n) {
    Random rand = new Random();

    String[] nombresHombres = {"Abel", "Adrian", "Alejandro", "Carlos", "Diego", "Enrique", "Fernando", "Joaquin", "Miguel", "Omar", "Victor", "Sebastian", "Santiago", "Salvador", "Rodrigo", "Raul", "Pedro", "Oscar"};
    String[] nombresMujeres = {"Sofia", "Lucia", "Maria", "Daniela", "Valeria", "Fernanda", "Tania", "Alba", "Laura", "Catalina", "Angelica", "Abril", "Valeria", "Victoria", "Abigail", "Irene", "Andrea", "Brenda"};

    String[] apellidos = {"Gonzalez", "Benitez", "Martinez", "Lopez", "Jimenez", "Vera", "Duarte", "Villalba", "Ramirez", "Fernandez", "Gomez", "Acosta", "Rojas", "Ortiz", "Torres"};


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

    // for (String nom : hombres) {
    //   System.out.println(nom);
    // }
    // for (String nom : mujeres) {
    //   System.out.println(nom);
    // }

    Set<Persona> union = new HashSet<Persona>(mujeres);
    union.addAll(hombres);

    // for (String nom : union) {
    //   System.out.println(nom);
    // }

    return union;
  }




}

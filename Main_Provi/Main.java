import java.util.Set;
import java.util.HashSet;

public class Main {
  public static void main(String[] args) {
    Set<Persona> total = new HashSet<Persona>();
    Datos data = new Datos();

    int totalp = 500;

    total = data.creador(totalp);


    for (Persona person : total) {
      System.out.println(person.imprimePersona2());
    }

  }
}

import java.util.Comparator;

public class Persona {
  String nombre;
  String apellido;
  long num_cuenta;

  public Persona() {

  }

  public Persona(String nombre, String apellido, long num_cuenta) {
    this.nombre = nombre;
    this.apellido = apellido;
    this.num_cuenta = num_cuenta;
  }

  /** Setters
  */
  public void setNombre(String nombre){
    this.nombre = nombre;
  }
  public void setApellido(String apellido){
    this.apellido = apellido;
  }
  public void setNumCuenta(long num_cuenta) {
    this.num_cuenta = num_cuenta;
  }

  /** Getters
  */
  public String getNombre() {
    return this.nombre;
  }
  public String getApellido() {
    return this.apellido;
  }
  public long getNumCuenta() {
    return this.num_cuenta;
  }

  /** Metodos
  */

  public String impArchivoAux() {
    return this.nombre + "," + apellido + ","  + num_cuenta + "/";
  }

  @Override
  public String toString() {
    return this.nombre + "," + apellido + ","  + num_cuenta + "\n";
  }


  //
  // /** Comparador para ordenar ascendentemente por nombre
  // */
  // public static Comparator<Persona> nombreComparador = new Comparator<Persona>() {
  //
  //   public int compare(Persona p1, Persona p2) {
  //     String nombrep1 = p1.getNombre();
  //     String nombrep2 = p2.getNombre();
  //
  //     return nombrep1.compareTo(nombrep2);
  //   }
  // };
  // /** Comparador para ordenar ascendentemente por apellido
  // */
  // public static Comparator<Persona> apellidoComparador = new Comparator<Persona>() {
  //
  //   public int compare(Persona p1, Persona p2) {
  //     String apellidop1 = p1.getApellido();
  //     String apellidop2 = p2.getApellido();
  //
  //     return apellidop1.compareTo(apellidop2);
  //   }
  // };
  // /** Comparador para ordenar ascendentemente por numero de cuenta
  // */
  // public static Comparator<Persona> numcuentaComparador = new Comparator<Persona>() {
  //
  //   public int compare(Persona p1, Persona p2) {
  //     String numcuentap1 = p1.getNumCuenta();
  //     String numcuentap2 = p2.getNumCuenta();
  //
  //     return numcuentap1 - numcuentap2;
  //   }
  // };



}

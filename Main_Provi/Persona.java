public class Persona {
  String nombre1;
  String nombre2;
  String apellido1;
  String apellido2;
  long n_cta;

  public Persona() {

  }

  public Persona(String nombre1, String apellido1, String apellido2, long n_cta) {
    this.nombre1 = nombre1;
    this.apellido1 = apellido1;
    this.apellido2 = apellido2;
    this.n_cta = n_cta;
  }
  public Persona(String nombre1, String nombre2, String apellido1, String apellido2, long n_cta) {
    this.nombre1 = nombre1;
    this.nombre2 = nombre2;
    this.apellido1 = apellido1;
    this.apellido2 = apellido2;
    this.n_cta = n_cta;
  }

  public void setNombre1(String nombre1){
    this.nombre1 = nombre1;
  }
  public void setNombre2(String nombre2){
    this.nombre2 = nombre2;
  }
  public void setApellido1(String apellido1){
    this.apellido1 = apellido1;
  }
  public void setApellido2(String apellido2){
    this.apellido2 = apellido2;
  }
  public void setNumCta(long n_cta){
    this.n_cta = n_cta;
  }

  public String getNombre1(){
    return this.nombre1;
  }
  public String getNombre2(){
    return this.nombre2;
  }
  public String getApellido1(){
    return this.apellido1;
  }
  public String getApellido2(){
    return this.apellido2;
  }
  public long getNumCta(){
    return this.n_cta;
  }

  public String imprimePersona1(){
    return  nombre1 + ", " +  apellido1 + " " + apellido2 + ", " + n_cta;
  }

  public String imprimePersona2(){
    if (this.nombre2 == null) {
      return imprimePersona1();
    } else
        return nombre1 + " "+ nombre2 + ", " +  apellido1 + " " + apellido2 + ", " + n_cta;
  }
}

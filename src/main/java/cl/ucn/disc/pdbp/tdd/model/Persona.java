/**
 * Copyright
 */
package cl.ucn.disc.pdbp.tdd.model;

/**
 * Clase Persona
 * @author Gerald López
 */
public class Persona {

    private final String nombre;
    private final String apellido;
    private final String rut;

    /**
     * Constructor de una persona
     * @param nombre a utilizar
     * @param apellido a usar
     * @param rut valido.
     */
    public Persona(String nombre, String apellido, String rut) {
        Validation.isPersonValid(nombre,apellido,rut);
        Validation.RutValido(rut);
        Validation.sizeName(nombre);
        Validation.sizeApellido(apellido);
        this.nombre = nombre;
        this.apellido = apellido;
        this.rut = rut;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return this.nombre;
    }

    /**
     * @return the apellido
     */
    public String getApellido() {
        return this.apellido;
    }

    /**
     * @return the RUT
     */
    public String getRut() {
        return this.rut;
    }

    /**
     * @return the nombre completo
     */
    public String getNombreApellido() {
        return this.nombre + " " + this.apellido;
    }


}

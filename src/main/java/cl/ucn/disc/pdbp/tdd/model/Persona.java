/**
 * Copyright
 */
package cl.ucn.disc.pdbp.tdd.model;

/**
 * Clase Persona
 * @author Gerald López
 */
public class Persona {
    /**
     * The Nombre of person
     */
    private final String nombre;
    /**
     * The Apellido of Person
     */
    private final String apellido;
    /**
     *The RUT
     */
    private final String rut;
    /**
     * The direccion
     */
    private final String direccion;
    /**
     * The telefono fijo
     */
    private final Integer telefonoFijo;
    /**
     * The teléfono móvil
     */
    private final Integer telefonoMovil;
    /**
     * The email
     */
    private final String email;

    /**
     * Constructor de una persona
     * @param nombre a utilizar
     * @param apellido a usar
     * @param rut valido.
     */

    public Persona(String nombre, String apellido, String rut, String direccion, Integer telefonoFijo, Integer telefonoMovil, String email) {
        Validation.isPersonValid(nombre,apellido,rut);

        Validation.sizeName(nombre);

        this.nombre = nombre;

        Validation.sizeApellido(apellido);

        this.apellido = apellido;

        Validation.RutValido(rut);

        this.rut = rut;

        this.direccion = direccion;

        this.telefonoFijo = telefonoFijo;
        this.telefonoMovil = telefonoMovil;
        this.email = email;
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

    public String getDireccion() {
        return this.direccion;
    }

    public Integer getTelefonoFijo() {
        return this.telefonoFijo;
    }

    public Integer getTelefonoMovil() {
        return this.telefonoMovil;
    }

    public String getEmail() {
        return this.email;
    }
}

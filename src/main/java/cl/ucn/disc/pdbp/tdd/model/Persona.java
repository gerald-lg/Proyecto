/**
 * Copyright
 */
package cl.ucn.disc.pdbp.tdd.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Clase Persona
 * @author Gerald López
 */
@DatabaseTable(tableName = "persona")
public class Persona {

    /**
     * The id
     */
    @DatabaseField(generatedId = true)
    private Long id;

    /**
     * The Nombre of person
     */
    @DatabaseField(canBeNull = false)
    private String nombre;
    /**
     * The Apellido of Person
     */
    @DatabaseField(canBeNull = false)
    private String apellido;
    /**
     *The RUT
     */
    @DatabaseField(canBeNull = false, index = true)
    private String rut;
    /**
     * The direccion
     */
    @DatabaseField(canBeNull = false)
    private String direccion;
    /**
     * The telefono fijo
     */
    private Integer telefonoFijo;
    /**
     * The teléfono móvil
     */
    private Integer telefonoMovil;
    /**
     * The email
     */
    private String email;

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

    /**
     *
     * @return the direccion
     */
    public String getDireccion() {
        return this.direccion;
    }

    /**
     *
     * @return the telefono fijo
     */
    public Integer getTelefonoFijo() {
        return this.telefonoFijo;
    }

    /**
     *
     * @return the telefono móvil.
     */
    public Integer getTelefonoMovil() {
        return this.telefonoMovil;
    }

    /**
     *
     * @return the email.
     */
    public String getEmail() {
        return this.email;
    }
}

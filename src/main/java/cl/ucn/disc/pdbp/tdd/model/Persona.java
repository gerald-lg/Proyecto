/*
 * MIT License
 *
 * Copyright (c) 2020 Gerald Lopez Gutiérrez <gerald.lopez@alumnos.ucn.cl>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package cl.ucn.disc.pdbp.tdd.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import utils.Validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    @DatabaseField(canBeNull = true)
    private Integer telefonoFijo;
    /**
     * The teléfono móvil
     */
    @DatabaseField(canBeNull = true)
    private Integer telefonoMovil;
    /**
     * The email
     */
    @DatabaseField(canBeNull = true)
    private String email;

    //TODO:Verificar el tamaño de los telefonos.
    /**
     * Patrón de un telefono fijo válido,
     */
    private final Pattern telFijoValido = Pattern.compile("^55[0-9]{6}$");
    /**
     * Patrón de un telefono movil válido.
     */
    private final Pattern telMovilValido = Pattern.compile("^9[0-9]{8}$");
    /**
     * Patrón de un correo electronico válido.
     */
    private final Pattern emailValido = Pattern.compile("^[_a-z0-9-]+(.[_a-z0-9-]+)*@[a-z0-9-]+(.[a-z0-9-]+)*(.[a-z]{2,4})$");

    /**
     * Constructor vacío de una persona.
     */
    Persona() {
        //Constructor vacío.
    }

    /**
     * Constructor de una persona
     * @param nombre a utilizar
     * @param apellido a usar
     * @param rut valido.
     */

    public Persona(String nombre, String apellido, String rut, String direccion, Integer telefonoFijo, Integer telefonoMovil, String email) {
        //TODO: Escribir el mensaje de las excepciones.
        if (nombre.equals(null) ||  apellido.equals(null) || rut.equals(null)) {
            throw new NullPointerException();
        }

        if (nombre.length() < 2) {
            throw new RuntimeException();
        }
        this.nombre = nombre;

        if (apellido.length() < 4) {
            throw new RuntimeException();
        }

        this.apellido = apellido;

        if(!Validation.isRutValid(rut)){
            throw new RuntimeException();
        }
        this.rut = rut;

        if(!direccion.equals(null) && direccion.length()<2) {
            throw new RuntimeException();
        }
        this.direccion = direccion;
        Matcher fijoMatch = telFijoValido.matcher(Integer.toString(telefonoFijo));
        if(telefonoFijo != null && fijoMatch.matches() == false) {
            throw new RuntimeException();
        }
        this.telefonoFijo = telefonoFijo;

        Matcher movilMatch = telMovilValido.matcher(Integer.toString(telefonoMovil));
        if(telefonoMovil != null && movilMatch.matches() == false){
            throw new RuntimeException();
        }
        this.telefonoMovil = telefonoMovil;

        Matcher emailMatch = emailValido.matcher(email);
        if(!email.equals(null) && emailMatch.matches() == false) {
            throw new RuntimeException();
        }
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

    public Long getId() {
        return this.id;
    }
}

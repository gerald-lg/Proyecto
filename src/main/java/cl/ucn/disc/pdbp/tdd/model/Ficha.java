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

import checkers.units.quals.C;
import cl.ucn.disc.pdbp.tdd.dao.ZonedDateTimeType;
import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 *Ficha Veterinaria
 *
 * @author Gerald López
 */
public final class Ficha {

    /**
     *
     */
    @DatabaseField(generatedId = true)
    private Long id;
    /**
     *Numero de ficha
     */
    @DatabaseField(unique = true)
    private Integer ficha;
    /**
     * Fecha de nacimiento
     */
    @DatabaseField(persisterClass = ZonedDateTimeType.class)
    private ZonedDateTime fechaNacimiento;
    /**
     * Nombre del paciente
     */
    @DatabaseField
    private String nombrePaciente;
    /**
     * Especie ej: canino
     */
    @DatabaseField
    private String especie;
    /**
     * Raza
     */
    @DatabaseField
    private String raza;

    /**
     * Sexo
     */
    @DatabaseField
     private Sexo sexo;
    /**
     * Color, ej: cobrizo
     */
    @DatabaseField
    private String color;

    /**
     * Tipo: interno/externo
     */
    @DatabaseField
    private Tipo tipo;
    /**
     * The Duenio
     */
    @DatabaseField(foreign = true, canBeNull = false, foreignAutoRefresh = true)
    private Persona duenio;

    /**
     * List of controles
     */
    @ForeignCollectionField(eager = true)
    private ForeignCollection<Control> controles;

    /**
     * Constructor vacio.
     */
    Ficha(){
        //nada aqui.
    }
    /**
     * The constructor
     * @param ficha
     * @param fechaNacimiento
     * @param nombrePaciente
     * @param especie
     * @param raza
     * @param sexo
     * @param color
     * @param tipo
     */
    public Ficha(Integer ficha, ZonedDateTime fechaNacimiento, String nombrePaciente, String especie, String raza, Sexo sexo, String color, Tipo tipo,Persona duenio) {
        //TODO:Agregar Validaciones.
        this.ficha = ficha;
        this.fechaNacimiento = fechaNacimiento;
        this.nombrePaciente = nombrePaciente;
        this.especie = especie;
        this.raza = raza;
        this.sexo = sexo;
        this.color = color;
        this.tipo = tipo;
        this.duenio = duenio;
    }

    /**
     *
     * @return the numero de ficha.
     */
    public long getFicha() {
        return this.ficha;
    }

    /**
     *
     * @return the fecha de nacimiento
     */
    public ZonedDateTime getFechaNacimiento() {
        return this.fechaNacimiento;
    }

    /**
     *
     * @return the nombre the paciente.
     */
    public String getNombrePaciente() {
        return this.nombrePaciente;
    }

    /**
     *
     * @return the nombre de la especie.
     */
    public String getEspecie() {
        return this.especie;
    }

    /**
     *
     * @return the raza.
     */
    public String getRaza() {
        return this.raza;
    }

    /**
     *
     * @return the Sexo.
     */
    public Sexo getSexo() {
        return this.sexo;
    }

    /**
     *
     * @return the color.
     */
    public String getColor() {
        return this.color;
    }

    /**
     *
     * @return the Tipo.
     */
    public Tipo getTipo() {

        return this.tipo;
    }

    /**
     *
     * @return the Duenio
     */
    public Persona getDuenio() {
        return this.duenio;
    }

    /**
     *
     * @return the Id
     */
    public Long getId() {
        return id;
    }

    /**
     *
     * @return the list of control
     */
    public List<Control> getControles() {
        return Collections.unmodifiableList(new ArrayList<>(controles));
    }
}

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

import java.time.ZonedDateTime;


/**
 *Ficha Veterinaria
 *
 * @author Gerald López
 */
public final class Ficha {
    /**
     *Numero de ficha
     */
    private final long ficha;
    /**
     * Fecha de nacimiento
     */
    private final ZonedDateTime fechaNacimiento;
    /**
     * Nombre del paciente
     */
    private final String nombrePaciente;
    /**
     * Especie ej: canino
     */
    private final String especie;
    /**
     * Raza
     */
    private final String raza;

    /**
     * Sexo
     */
     private final Sexo sexo;
    /**
     * Color, ej: cobrizo
     */
    private final String color;

    /**
     * Tipo: interno/externo
     */
    private final Tipo tipo;

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
    public Ficha(long ficha, ZonedDateTime fechaNacimiento, String nombrePaciente, String especie, String raza, Sexo sexo, String color, Tipo tipo) {
        //TODO:Agregar Validaciones.
        this.ficha = ficha;
        this.fechaNacimiento = fechaNacimiento;
        this.nombrePaciente = nombrePaciente;
        this.especie = especie;
        this.raza = raza;
        this.sexo = sexo;
        this.color = color;
        this.tipo = tipo;
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
}

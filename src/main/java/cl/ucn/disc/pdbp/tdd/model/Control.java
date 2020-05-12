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
 * Control del paciente
 * @author Gerald López
 */
public final class Control {
    /**
     * The id of control.
     */
    private long idControl;

    /**
     *The fecha of control
     */
    private final ZonedDateTime fecha;

    /**
     * The fecha of next control
     */
    private final ZonedDateTime fechaProximoControl;

    /**
     * The temperatura (Celsius)
     * Min: 25
     * Máx:
     */
    private final float temperatura;

    /**
     * The peso (KG)
     * Mín: 0 kg
     * Máx: 200 kg
     * TODO: Verificar el valor máximo
     */
    private final float peso;

    /**
     * The altura (cm)
     * Mín:
     * Máx:
     * TODO: Verificar la altura máxima y mínima.
     */
    private final float altura;

    /**
     * The diagnostico of paciente.
     */
    private final String diagnostico;

    /**
     * The veterinario.
     */
    private final Persona veterinario;

    public Control(ZonedDateTime fecha, ZonedDateTime fechaProximoControl, float temperatura, float peso, float altura, String diagnostico, Persona veterinario) {
       //TODO: Agregar las restricciones.
        this.fecha = fecha;
        this.fechaProximoControl = fechaProximoControl;
        this.temperatura = temperatura;
        this.peso = peso;
        this.altura = altura;
        this.diagnostico = diagnostico;
        this.veterinario = veterinario;
    }

    /**
     *
     * @return the fecha of control
     */
    public ZonedDateTime getFecha() {
        return this.fecha;
    }

    /**
     *
     * @return the fecha of next control
     */
    public ZonedDateTime getFechaProximoControl() {
        return this.fechaProximoControl;
    }

    /**
     *
     * @return the temperatura
     */
    public float getTemperatura() {
        return this.temperatura;
    }

    /**
     *
     * @return the peso
     */
    public float getPeso() {
        return this.peso;
    }

    /**
     *
     * @return the altura
     */
    public float getAltura() {
        return this.altura;
    }

    /**
     *
     * @return the diagnostico
     */
    public String getDiagnostico() {
        return this.diagnostico;
    }

    /**
     *
     * @return the Veterinario.
     */
    public Persona getVeterinario() {
        return this.veterinario;
    }
}

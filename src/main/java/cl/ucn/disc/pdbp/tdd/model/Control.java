package cl.ucn.disc.pdbp.tdd.model;

import java.time.ZonedDateTime;

/**
 * Control del paciente
 * @author Gerald López
 */
public final class Control {
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
     * @return
     */
    public ZonedDateTime getFecha() {
        return this.fecha;
    }

    /**
     *
     * @return
     */
    public ZonedDateTime getFechaProximoControl() {
        return this.fechaProximoControl;
    }

    /**
     *
     * @return
     */
    public float getTemperatura() {
        return this.temperatura;
    }

    /**
     *
     * @return
     */
    public float getPeso() {
        return this.peso;
    }

    /**
     *
     * @return
     */
    public float getAltura() {
        return this.altura;
    }

    /**
     *
     * @return
     */
    public String getDiagnostico() {
        return this.diagnostico;
    }

    /**
     *
     * @return
     */
    public Persona getVeterinario() {
        return this.veterinario;
    }
}

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

package cl.ucn.disc.pdbp.tdd;

import cl.ucn.disc.pdbp.tdd.model.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.ZonedDateTime;
import java.util.List;


public final class TestContratosImpl {

    /**
     * The Logger
     */
    private static final Logger log = LoggerFactory.getLogger(TestContratosImpl.class);
    /**
     * The data base url
     */
    private static String databaseUrl = "jdbc:sqlite:fivet_db";
    /**
     *
     */
    private static final Contratos contratos = new ContratosImpl(databaseUrl);


    @Test
    public void testRegistrarPaciente() {

        log.debug("Test Contrato Registrar Paciente");

        //Instance of Persona
        Persona duenio = new Persona("Gerald","Lopez","198221287","Fake 123", 55222222,944707032,"glopez@gmail.com");
        contratos.registrarPersona(duenio);

        //Atributes of ficha
        Integer numFicha = 123;
        ZonedDateTime fechaNacimiento = ZonedDateTime.now();
        String nombrePaciente = "Harry";
        String especie = "Felino";
        String raza = "Kiltro";
        Sexo sexo = Sexo.MACHO;
        String color = "Amarillo";
        Tipo tipo = Tipo.EXTERNO;

        Ficha ficha = new Ficha(numFicha,fechaNacimiento,nombrePaciente,especie,raza,sexo,color,tipo,duenio);
        Ficha fichaInput = contratos.registrarPaciente(ficha);

        //Test constructor and getters
        Assertions.assertEquals(numFicha,fichaInput.getNumFicha());
        //Assertions.assertEquals(fechaNacimiento,fichaInput.getFechaNacimiento());
        Assertions.assertEquals(nombrePaciente,fichaInput.getNombrePaciente());
        Assertions.assertEquals(especie,fichaInput.getEspecie());
        Assertions.assertEquals(raza,fichaInput.getRaza());
        Assertions.assertEquals(sexo,fichaInput.getSexo());
        Assertions.assertEquals(color,fichaInput.getColor());
        Assertions.assertEquals(tipo,fichaInput.getTipo());
        Assertions.assertEquals(duenio.getRut(),fichaInput.getDuenio().getRut());

        //Size of list
        List<Ficha> fichas = contratos.getAllFichas();
        Assertions.assertEquals(1,fichas.size(),"!= 1");

    }

    @Test
    public void testRegistrarPersona(){

        log.debug("Test Registrar Persona");
        //Instance of Personas
        {
            Persona persona = new Persona("Mauricio","Herrera","222485169","Fake 1515", 55222223,998763125,"mherrera@gmail.com");
            Persona personaDB = contratos.registrarPersona(persona);

            //Test getters
            Assertions.assertEquals(persona.getNombre(),personaDB.getNombre());
            Assertions.assertEquals(persona.getApellido(),personaDB.getApellido());
            Assertions.assertEquals(persona.getRut(),personaDB.getRut());
            Assertions.assertEquals(persona.getDireccion(),personaDB.getDireccion());
            Assertions.assertEquals(persona.getTelefonoFijo(),personaDB.getTelefonoFijo());
            Assertions.assertEquals(persona.getTelefonoMovil(),personaDB.getTelefonoMovil());
            Assertions.assertEquals(persona.getEmail(),personaDB.getEmail());
        }

        {
            Persona persona = new Persona("Gerald","Lopez","198221287","Fake 1213", 55223344,977233277,"glopez@gmail.com");
            Persona personaDB = contratos.registrarPersona(persona);
        }

        {
            Persona persona = new Persona("Brenda","Lopez","203984936","Fake 1213", 55223355,982765539,"blgut@gmail.com");
            Persona personaDB = contratos.registrarPersona(persona);
        }

        //Get size of list, should be 3
        List<Persona> personas = contratos.getAllPersonas();
        Assertions.assertEquals(3,personas.size(),"Size != 3");


    }

}

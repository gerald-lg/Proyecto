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
import io.javalin.http.Context;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.rmi.server.InactiveGroupException;

import java.time.ZonedDateTime;
import java.util.List;

/**
 *
 */
public final class ApiRestEndpoints {

    /**
     * The Logger
     */
    private static final Logger log = LoggerFactory.getLogger(ApiRestEndpoints.class);

    /**
     * The Contratos (using SQLite)
     */
    private static final Contratos CONTRATOS = new ContratosImpl("jdbc:sqlite:fivet.db");

    /**
     * Private constructor, empty
     */
    private ApiRestEndpoints(){
        // Nada aquí
    }

    /**
     * Get all the Fichas
     * @param ctx the Javalin {@link (Context)}
     */
    public static void getAllFichas(Context ctx){

        log.debug("Getting all the fichas ..");
        List<Ficha> fichas = CONTRATOS.getAllFichas();
        ctx.json(fichas);
    }

    /**
     * Get fichas by query
     * @param ctx the Javalin {@link (Context)}
     */
    public static void findFichas(Context ctx) {

        String query = ctx.pathParam("query");
        log.debug("Finding Fichas with query <{}> ..",query);

        List<Ficha> fichas = CONTRATOS.buscarFicha(query);
        ctx.json(fichas);
    }

    /**
     * Get list of Persona
     * @param ctx the Javalin {@link (Context)}
     */
    public static void getAllPersonas(Context ctx) {

        log.debug("Getting all the personas ..");
        List<Persona> personas = CONTRATOS.getAllPersonas();
        ctx.json(personas);
    }

    /**
     * Create a Persona
     * @param ctx the Javalin {@link (Context)}
     */
    public static void createPersona(Context ctx){

        log.debug("Create a Persona");
        String nombre = ctx.pathParam("nombre");
        String apellido = ctx.pathParam("apellido");
        String rut = ctx.pathParam("rut");
        String direccion = ctx.pathParam("direccion");
        Integer telefonoFijo = Integer.parseInt(ctx.formParam("telefonoFijo"));
        Integer telefonoMovil = Integer.parseInt(ctx.formParam("telefonoMovil"));
        String email = ctx.pathParam("email");

        Persona persona = new Persona(nombre,apellido,rut,direccion, telefonoFijo,telefonoMovil,email);
        CONTRATOS.registrarPersona(persona);
        ctx.json(persona);

    }

    /**
     * Get List of Controles by number of Ficha
     * @param ctx the Javalin {@link (Context)}
     */
    public static void getControlesByNumFicha(Context ctx) {

        log.debug("Getting all the Control by Numero of Ficha");
        Integer numero = Integer.parseInt(ctx.formParam("numeroFicha"));
        List<Control> controles = CONTRATOS.getControlesByNumeroFicha(numero);
        ctx.json(controles);

    }

    /**
     * Get Duenio of Ficha by numero of Ficha
     * @param ctx the Javalin {@link (Context)}
     */
    public static void getDuenioByNumFicha(Context ctx) {

        log.debug("Getting Duenio by Numero of Ficha");
        Integer numero = Integer.parseInt(ctx.formParam("numeroFicha"));
        Persona duenio = CONTRATOS.getDuenioByNumeroFicha(numero);
        ctx.json(duenio);
    }

    public static void createFicha(Context ctx) {

        //Get atributes of Ficha

        Integer numero = Integer.parseInt(ctx.formParam("numeroFicha"));
        String nombrePaciente = ctx.formParam("nombrePaciente");
        String especie = ctx.formParam("especie");
        String raza = ctx.formParam("raza");
        String color = ctx.formParam("color");
        ZonedDateTime fechaNacimiento = ZonedDateTime.parse(ctx.formParam("fechaNacimiento"));

        Sexo sexo;
        if(ctx.formParam("sexo").equalsIgnoreCase("hembra")){
            sexo = Sexo.HEMBRA;
        }
        else{
            sexo = Sexo.MACHO;
        }

        Tipo tipo;

        if(ctx.formParam("tipo").equalsIgnoreCase("interno")){
            tipo = Tipo.INTERNO;
        }
        else{
            tipo = Tipo.EXTERNO;
        }

        //Get duenio of Ficha
        Long duenioId = Long.parseLong(ctx.formParam("duenio"));
        Persona duenio = CONTRATOS.getPersonaById(duenioId);

        //Create ficha and insert in the data base
        Ficha ficha = new Ficha(numero,fechaNacimiento,nombrePaciente,especie,raza,sexo,color,tipo,duenio);
        CONTRATOS.registrarPaciente(ficha);

    }



}

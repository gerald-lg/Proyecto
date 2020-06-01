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

import checkers.units.quals.A;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.javalin.Javalin;
import io.javalin.apibuilder.ApiBuilder;
import io.javalin.core.util.RouteOverviewPlugin;
import io.javalin.plugin.json.JavalinJson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.ZonedDateTime;

/**
 * The class Application
 * @author Gerald Lopez
 */
public final class Application {

    /**
     * The Logger
     */
    private static final Logger log = LoggerFactory.getLogger(Application.class);

    /**
     * The private constructor, evita la instancia de esta clase.
     */
    private Application(){
        //Nothing here
    }

    /**
     * The main
     *
     * @param args from console
     */
   public static void main(String[] args) {

        //Persona -> Json via libreria Gson
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        JavalinJson.setFromJsonMapper(gson::fromJson);
        JavalinJson.setToJsonMapper(gson::toJson);

        log.debug("Starting Javalin..");
        Javalin javalin = Javalin.create(config ->{

            //Measure the time
            config.requestLogger((((ctx, executionTimeMs) -> {
                log.info("Served {} in {} ms.",ctx.fullUrl(),executionTimeMs);
                ctx.header("Server-Timing","total;dur=" + executionTimeMs);
            } )));

            //Enable routes helper
            config.registerPlugin(new RouteOverviewPlugin("routes"));
        }).routes(()->{

            // The version
            ApiBuilder.path("v1", () -> {

                // /fichas
                ApiBuilder.path("fichas", () -> {

                    //Get -> /fichas
                    ApiBuilder.get(ApiRestEndpoints::getAllFichas);

                    //Post ->/fichas/create
                    ApiBuilder.path("/create",() -> {
                        ApiBuilder.post(ApiRestEndpoints::createFicha);
                    });

                    //Get -> /fichas/find/{query}
                    ApiBuilder.path("find/:query", () -> {
                        ApiBuilder.get(ApiRestEndpoints::findFichas);
                    });

                    //Get ->/fichas/{numeroFicha}/controles
                    ApiBuilder.path(":numeroFicha/controles", () -> {
                        ApiBuilder.get(ApiRestEndpoints::getControlesByNumFicha);
                    });

                    //Get -> /fichas/{numeroFicha}/persona
                    ApiBuilder.path(":numeroFicha/persona", () -> {
                        ApiBuilder.get(ApiRestEndpoints::getDuenioByNumFicha);
                    });

                });

                //personas
                ApiBuilder.path("personas", () -> {

                    // Get -> /personas
                    ApiBuilder.get(ApiRestEndpoints::getAllPersonas);

                    //Get ->/personas?pageSize={size}&page={number}

                    // Post
                    //TODO:Revisar
                    ApiBuilder.path("/create", ()->{

                        ApiBuilder.post(ApiRestEndpoints::createPersona);

                    });


                });


            });

        }).start(7000);


    }

}

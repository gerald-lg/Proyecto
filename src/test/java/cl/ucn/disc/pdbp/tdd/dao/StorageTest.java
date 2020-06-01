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
package cl.ucn.disc.pdbp.tdd.dao;


import cl.ucn.disc.pdbp.tdd.Contratos;
import cl.ucn.disc.pdbp.tdd.model.*;
import cl.ucn.disc.pdbp.tdd.utils.Entity;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.sql.SQLException;
import java.time.ZonedDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 *Model Test
 *
 * @author Gerald López
 */
public final class StorageTest {

    /**
     * The Logger
     */
    private static final Logger log = LoggerFactory.getLogger(StorageTest.class);

    /**
     *
     */
    @Test
    public void testDatabase() throws SQLException {

        //The database to use (in RAM memory)
        String databaseUrl = "jdbc:h2:mem:fivet_db";

        //Connection source: autoclose with the try/catch
        try(ConnectionSource connectionSource = new JdbcConnectionSource(databaseUrl)) {

            //Create the table from the Persona annotations
            TableUtils.createTableIfNotExists(connectionSource, Persona.class);

            //The dao of Persona
            Dao<Persona,Long> daoPersona = DaoManager.createDao(connectionSource,Persona.class);

            //New Persona
            Persona persona = new Persona("Gerald","Lopez","198221287","Blumell 0644",55222222,944707039,"gerald.lopez@alumnos.ucn.cl");

            //Insert Persona into the database
            int tuples = daoPersona.create(persona);
            log.debug("Id: {}",persona.getId());

            Assertions.assertEquals(1,tuples,"Save tuples != 1");

            //Get from db

            Persona personaDb = daoPersona.queryForId((persona.getId()));

            Assertions.assertEquals(persona.getNombre(),personaDb.getNombre(), "Nombre not equals");
            Assertions.assertEquals(persona.getApellido(),personaDb.getApellido(),"Apellido not equals");
            Assertions.assertEquals(persona.getRut(),personaDb.getRut(),"Rut not equals");

            //Search by rut: SELECT * FROM 'persona' WHERE 'rut' = '198221287'
            List<Persona> personaList = daoPersona.queryForEq("rut","198221287");
            Assertions.assertEquals(1,personaList.size(),"Can't be more than one person");

            //Not found by rut
            Assertions.assertEquals(0,daoPersona.queryForEq("rut","19").size(),"Found something?");
        } catch (IOException e){
            log.error("Error", e);
        }

    }
    /**
     * Testing the repository
     */
    @Test
    public void testRepositoryPersona() {

        //The database to use (in RAM memory)
        String databaseUrl = "jdbc:h2:mem:fivet_db";

        try (ConnectionSource connectionSource = new JdbcConnectionSource(databaseUrl)) {

                //Create the table in the backend
                TableUtils.createTableIfNotExists(connectionSource, Persona.class);

                //Test: connection null
                assertThrows(RuntimeException.class,()-> new RepositoryOrmLite<>(null,Persona.class));

                //Test: Class null
                assertThrows(RuntimeException.class, () -> new RepositoryOrmLite<>(connectionSource,null));

                //Create the repository of Persona.
                Repository<Persona,Long> theRepo =  new RepositoryOrmLite<>(connectionSource,Persona.class);

                //Get the list of all. Size = 0
            {
                List<Persona> personas = theRepo.findAll();

                Assertions.assertEquals(0,personas.size(),"Size != 0");

            }

            //Test the insert v1: ok
            {
                //New Person
                Persona persona = new Persona("Gerald","Lopez","198221287","Blumell 0644",55212345,944707039,"gerald.lopez@alumnos.ucn.cl");
                if(!theRepo.create(persona)){
                    Assertions.fail("Can't insert!");
                }
                Assertions.assertNotNull(persona.getId(),"Id was null");
            }

            {
                //New Person
                Persona persona = new Persona("Gerald","Lopez","198221287","Blumell 0644",55212345,944707039,"gerald.lopez@alumnos.ucn.cl");
                Assertions.assertThrows(RuntimeException.class, () -> theRepo.create(persona));

            }

            // Get the list of all. Size == 1
            {
                List<Persona> personas = theRepo.findAll();
                //The size must be one.
                Assertions.assertEquals(1, personas.size(),"Size != 1");
            }

            //Delete persona.

            {
                if(!theRepo.delete(1L)){
                    Assertions.fail("Can't delete!");
                }
                Assertions.assertEquals(0,theRepo.findAll().size(),"Size != 0");
            }

        } catch(IOException | SQLException e) {
                throw  new RuntimeException(e);
        }

    }

    /**
     * Testing Ficha Repository.
     */
    @Test
    public void testRepositoryFicha(){

        //The database to use
        String databaseUrl = "jdbc:h2:mem:fivet_db";

        try(ConnectionSource connectionSource = new JdbcConnectionSource(databaseUrl)) {

            //Create table Persona in the backend
            TableUtils.createTableIfNotExists(connectionSource, Persona.class);
            //Create table Ficha in the backend
            TableUtils.createTableIfNotExists(connectionSource,Ficha.class);
            //Create table Control in the backend
            TableUtils.createTableIfNotExists(connectionSource, Control.class);

            //The Repository
            Repository<Ficha,Long> theRepoFicha = new RepositoryOrmLite<>(connectionSource, Ficha.class);
            {
                //Create a New persona
                Persona duenio = new Persona("Gerald","Lopez","198221287","Av Argentina 0351",55222222,944707039,"glopez.gutie@gmail.com");

                if(!new RepositoryOrmLite<Persona,Long>(connectionSource,Persona.class).create(duenio)){
                    Assertions.fail("Can't insert persona");
                }

                //Instanciar la Ficha

                Ficha ficha = new Ficha(123, ZonedDateTime.now(),"Firulais","Canino","Rottweiler", Sexo.MACHO,"Negro", Tipo.INTERNO,duenio);

                //Crear una ficha via repositorio.

                if(!theRepoFicha.create(ficha)){
                    Assertions.fail("Can't create ficha");
                }
            }

            {
                //Obtener una ficha y revisar si sus atributos son != null
                Ficha ficha = theRepoFicha.findById(1L);
                //Los atributos de ficha no pueden ser null.
                Assertions.assertNotNull(ficha,"Ficha wall null");
                Assertions.assertNotNull(ficha.getDuenio(),"Duenio de Ficha was null");
                Assertions.assertNotNull(ficha.getDuenio().getRut(),"Rut del Duenio de Ficha was null");
                Assertions.assertNotNull(ficha.getFechaNacimiento(),"Fecha nacimiento was null");

                //Imprimir los atributos de la ficha
                log.debug("Ficha: {}.", Entity.toString(ficha));
            }

        } catch (SQLException | IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    @Test
    public void testRepositoryControl(){

        //The database to use (in RAM memory)
        String databaseUrl = "jdbc:h2:mem:fivet_db";

        //Connection source: autoclose with the try/catch
        try(ConnectionSource connectionSource = new JdbcConnectionSource(databaseUrl)){

            //Create table Persona
            TableUtils.createTableIfNotExists(connectionSource, Persona.class);
            //Create table Ficha
            TableUtils.createTableIfNotExists(connectionSource, Ficha.class);
            //Create table Control
            TableUtils.createTableIfNotExists(connectionSource, Control.class);
            //Insert Personas on the table
            Repository<Persona,Long> theRepoPersona = new RepositoryOrmLite<>(connectionSource,Persona.class);

            Repository<Ficha,Long> repoFicha = new RepositoryOrmLite<>(connectionSource,Ficha.class);
            //Repository Control
            Repository<Control,Long> repoControl = new RepositoryOrmLite<>(connectionSource,Control.class);

            //Instanciar a Duenio
            Persona duenio = new Persona("Gerald","Lopez","198221287","Malleco 1204",55222222,944707039,"glopez@gmail.com");

            if(!theRepoPersona.create(duenio)){
                Assertions.fail("Can't insert persona");
            }

            //Instanciar una ficha
            Ficha ficha = new Ficha(123, ZonedDateTime.now(),"Firulais","Canino","Rottweiler", Sexo.MACHO,"Negro", Tipo.INTERNO,duenio);

            if(!repoFicha.create(ficha)){
                Assertions.fail("Can't create ficha");
            }

            Ficha ficha1 = repoFicha.findById(1L);

            //Instanciar a Veterinario
            Persona vet = new Persona("Mauricio","Contreras","116002825","Av Argentina 0351",55111111,987654321,"mauro.ctreras@gmail.com");

            if(!theRepoPersona.create(vet)){
                Assertions.fail("Can't insert persona");
            }

            //Instancia of Control
            Control control = new Control(ZonedDateTime.now(),ZonedDateTime.now(),36,40,68,"Dado de alta",vet,ficha1);

            //Crear un control via repositorio
            if(!repoControl.create(control)){
                Assertions.fail("Can't create control");
            }


            log.debug("Control: {}.", Entity.toString(control));

            log.debug("Ficha: {}",Entity.toString(ficha1));




        } catch (SQLException | IOException exception){
            throw new RuntimeException(exception);
        }


    }

}



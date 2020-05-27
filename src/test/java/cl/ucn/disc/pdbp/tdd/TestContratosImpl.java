package cl.ucn.disc.pdbp.tdd;

import checkers.units.quals.A;
import cl.ucn.disc.pdbp.tdd.dao.Repository;
import cl.ucn.disc.pdbp.tdd.dao.RepositoryOrmLite;
import cl.ucn.disc.pdbp.tdd.dao.ZonedDateTimeType;
import cl.ucn.disc.pdbp.tdd.model.*;
import cl.ucn.disc.pdbp.tdd.utils.Entity;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.management.modelmbean.DescriptorSupport;
import javax.swing.*;
import java.io.IOException;
import java.sql.SQLException;
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
    private static String databaseUrl = "jdbc:h2:mem:fivet_db";
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
        List<Ficha> fichas = contratos.getRepoFicha().findAll();
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
        List<Persona> personas = contratos.getRepoPersona().findAll();
        Assertions.assertEquals(3,personas.size(),"Size != 3");


    }

}

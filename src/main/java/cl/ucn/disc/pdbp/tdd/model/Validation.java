
package cl.ucn.disc.pdbp.tdd.model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Clase Validation.
 * @author Gerald López.
 */

public class Validation {

    /**
     * isPersonValid método que sirve para saber si el objeto persona a crear es válido.
     * @param nombre a utilizar
     * @param apellido a usar
     * @param rut valido
     */
    public static void isPersonValid(String nombre, String apellido, String rut) {
        if (nombre.equals(null) ||  apellido.equals(null) || rut.equals(null)) {
            throw new NullPointerException();
        }

    }


    /**
     * Valida que el rut ingresado sea correcto.
     * @param rut valido
     */

    public static boolean isRutValid(String rut) {

        if (rut == null) {
            return false;
        }
        Pattern pattern = Pattern.compile("^[0-9]+[0-9kK]{1}$");
        Matcher matcher = pattern.matcher(rut);
        if (matcher.matches() == false) {

            return false;
        }
        String digVerificador = Character.toString(rut.charAt(rut.length() - 1));
        String rutAux = rut.substring(0,rut.length() - 1);
        return digVerificador.toLowerCase().equals(Validation.dv(rutAux));
    }

    /**
     * dv Valida el dígito verificador
     * @param rut valido
     */
    public static String dv(String rut) {
        int M = 0;
        int S = 1;
        int T = Integer.parseInt(rut);
        for (;T != 0;T = (int) Math.floor(T /= 10)) {
            S = (S + T % 10 * (9 - M++ % 6)) % 11;
        }
        return (S > 0) ? String.valueOf(S - 1) : "k";
    }

    /**
     *
     * @param rut valido
     */
    public static void RutValido(String rut) {

        Pattern pattern = Pattern.compile("^[0-9]+[0-9kK]{1}$");
        Matcher matcher = pattern.matcher(rut);
        if (matcher.matches() == false) {

            throw new RuntimeException();
        }
        String digVerificador = Character.toString(rut.charAt(rut.length() - 1));
        String rutAux = rut.substring(0,rut.length() - 1);
        if (!digVerificador.toLowerCase().equals(Validation.dv(rutAux))) {
            throw  new RuntimeException();
        }

    }

    /**
     *
      * @param nombre a utilizar
     */
    public static void sizeName(String nombre) {
        if (nombre.length() < 2) {
            throw new RuntimeException();
        }
    }

    /**
     *
     * @param apellido a usar
     */
    public static void sizeApellido(String apellido) {
        if (apellido.length() < 4) {
            throw new RuntimeException();
        }

    }

}

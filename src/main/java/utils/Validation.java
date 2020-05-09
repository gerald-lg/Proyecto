
package utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Clase Validation.
 * @author Gerald López.
 */

public class Validation {

    /**
     * Patron que sigue el rut.
     */
    private static final Pattern pattern = Pattern.compile("^[0-9]+[0-9kK]{1}$");

    /**
     * Valida que el rut ingresado sea correcto.
     * @param rut valido
     */
    public static boolean isRutValid(String rut) {

        if (rut == null) {
            return false;
        }
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
    private static String dv(String rut) {
        int M = 0;
        int S = 1;
        int T = Integer.parseInt(rut);
        for (;T != 0;T = (int) Math.floor(T /= 10)) {
            S = (S + T % 10 * (9 - M++ % 6)) % 11;
        }
        return (S > 0) ? String.valueOf(S - 1) : "k";
    }


}

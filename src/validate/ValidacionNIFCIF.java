package validate;

import error.EstadoError;

import java.util.regex.Pattern;

public class ValidacionNIFCIF implements IValidacion{

      //A64908411
    private String nif;

    private static final Pattern cifPattern = Pattern.compile("^([ABCDEFGHJKLMNPQRSUVW])(\\d{7})([0-9A-J])$");
    private static final String CONTROL_SOLO_NUMEROS = "ABEH"; // Sólo admiten números como caracter de control
    private static final String CONTROL_SOLO_LETRAS = "KPQS"; // Sólo admiten letras como caracter de control
    private static final String CONTROL_NUMERO_A_LETRA = "JABCDEFGHI"; // Conversión de dígito a letra de control.

    public ValidacionNIFCIF(String nif){
        this.nif = nif;
    }



    @Override
    public int exec() {
        try {
            if (!cifPattern.matcher(this.nif).matches()) {
                // No cumple el patrón
                return EstadoError.ERROR_NOPATTERN.getId();
            }

            int parA = 0;
            for (int i = 2; i < 8; i += 2) {
                final int digito = Character.digit(this.nif.charAt(i), 10);
                if (digito < 0) {
                    return EstadoError.ERROR_NIF_8DIGIT_LETTER.getId();
                }
                parA += digito;
            }

            int nonB = 0;
            for (int i = 1; i < 9; i += 2) {
                final int digito = Character.digit(this.nif.charAt(i), 10);
                if (digito < 0) {
                    return EstadoError.ERROR_CIF_BAD.getId();
                }
                int nn = 2 * digito;
                if (nn > 9) {
                    nn = 1 + (nn - 10);
                }
                nonB += nn;
            }

            final int parcialC = parA + nonB;
            final int digitoE = parcialC % 10;
            final int digitoD = (digitoE > 0) ? (10 - digitoE) : 0;
            final char letraIni = this.nif.charAt(0);
            final char caracterFin = this.nif.charAt(8);

            final boolean esControlValido =
                    // ¿el caracter de control es válido como letra?
                    (CONTROL_SOLO_NUMEROS.indexOf(letraIni) < 0 && CONTROL_NUMERO_A_LETRA.charAt(digitoD) == caracterFin)
                            ||
                            // ¿el caracter de control es válido como dígito?
                            (CONTROL_SOLO_LETRAS.indexOf(letraIni) < 0 && digitoD == Character.digit(caracterFin, 10));
            if(esControlValido) return EstadoError.ERROR_NULL.getId();
                    else return EstadoError.ERROR_CIF_BAD.getId();

        } catch (Exception e) {
            return EstadoError.ERROR_MISSING.getId();
        }

    }
}

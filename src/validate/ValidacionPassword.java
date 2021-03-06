package validate;

import error.EstadoError;

public class ValidacionPassword extends ValidacionRegularExpression implements IValidacion {

    private static final String patron =  "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[$@$!-_%*?&])[A-Za-z\\d$@$!-_%*?&]{8,15}[\\S]$";

    // Minimo 8 caracteres y Maximo 15
   // Al menos una letra mayúscula y Al menos una letra minucula
   // Al menos un dígito y Al menos 1 caracter especial
   //  No espacios en blanco

    private static final String mensajeError = "Password Incorrecta";

    private String value;

    public ValidacionPassword(String value){

        this.value = value;

    }
    @Override
    public int exec(){

        if(super.validar(value,patron)) return EstadoError.ERROR_NULL.getId();
           else return EstadoError.ERROR_PASSWORD_BAD.getId();
    }


}

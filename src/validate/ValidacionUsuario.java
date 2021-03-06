package validate;

import error.EstadoError;

public class ValidacionUsuario extends ValidacionRegularExpression implements IValidacion {

    // Los usuarios tienen 7 dígitos.
    //El primer dígito es una letra.
    //Los dígitos 2 y 3 pueden ser letras o números.
    //Los 4 últimos son números.

    private static final String patron = "^[A-Za-z]{1}([A-Za-z]{2}|[0-9]{2}|[A-Za-z][0-9]{2})([0-9]{4})$";

    private static final String mensajeError = "Usuario Incorrecto";

    private String value;

    public ValidacionUsuario(String value){

        this.value = value;

    }
    @Override
    public int exec(){
        if (super.validar(value,patron)) return EstadoError.ERROR_NULL.getId();
         else return  EstadoError.ERROR_USUARIO_BAD.getId();
    }


}

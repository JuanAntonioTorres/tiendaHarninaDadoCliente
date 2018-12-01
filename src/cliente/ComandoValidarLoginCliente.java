package cliente;

import entity.LoginClienteHarnina;
import validate.ValidacionPassword;
import validate.ValidacionUsuario;

import java.util.ArrayList;

public class ComandoValidarLoginCliente implements ComandoValidarCliente {

    LoginClienteHarnina loginClienteHarnina = null;

   public ComandoValidarLoginCliente(LoginClienteHarnina loginClienteHarnina){
      this.loginClienteHarnina = loginClienteHarnina;
   }
    public  ArrayList<Integer> getCommands(){

        ArrayList<Integer> listaErrores = new ArrayList<Integer>();

        listaErrores.add(new ValidacionUsuario(loginClienteHarnina.getUsuarioCliente()).exec());
        listaErrores.add(new ValidacionPassword(loginClienteHarnina.getPasswordCliente()).exec());

        return listaErrores;

    }

}

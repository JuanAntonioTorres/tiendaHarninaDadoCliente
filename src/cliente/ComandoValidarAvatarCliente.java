package cliente;


import validate.ValidacionImagenNombre;

import java.util.ArrayList;

public class ComandoValidarAvatarCliente {
    String rutaImagen = null;

    public ComandoValidarAvatarCliente(String rutaImagen){
        this.rutaImagen = rutaImagen;
    }
    public ArrayList<Integer> getCommands(){

        ArrayList<Integer> listaErrores = new ArrayList<Integer>();

        listaErrores.addAll(new ValidacionImagenNombre(rutaImagen).exec());

        return listaErrores;

    }
}

package cliente;

import entity.DaperClienteEntity;
import validate.*;

import java.util.ArrayList;

public class ComandoValidarDaperCliente implements ComandoValidarCliente {

    DaperClienteEntity daperClienteEntity = null;

    public ComandoValidarDaperCliente(DaperClienteEntity daperClienteEntity){
        this.daperClienteEntity = daperClienteEntity;
    }

    @Override
    public ArrayList<Integer> getCommands() {

        ArrayList<Integer> listaErrores = new ArrayList<Integer>();

        listaErrores.add(new ValidacionDNINIECIF(daperClienteEntity.getNifCliente()).exec());
        listaErrores.add(new ValidacionLetrasConEspacio(daperClienteEntity.getNombreCliente()).exec());
        listaErrores.add(new ValidacionLongitud(daperClienteEntity.getNombreCliente(), 3, 50).exec());
        listaErrores.add(new ValidacionLetrasConEspacio(daperClienteEntity.getApellidosCliente()).exec());
        listaErrores.add(new ValidacionLongitud(daperClienteEntity.getApellidosCliente(), 15, 100).exec());
        listaErrores.add(new ValidacionCodigoPostal(daperClienteEntity.getCodigoPostalCliente()).exec());
        listaErrores.add(new ValidarDomicilio(daperClienteEntity.getDomicilioCliente()).exec());
        listaErrores.add(new ValidacionLongitud(daperClienteEntity.getDomicilioCliente(), 2, 100).exec());
        listaErrores.add(new ValidacionTelefonoSpain(daperClienteEntity.getTelefonoCliente()).exec());
        listaErrores.add(new ValidacionTelefonoSpain(daperClienteEntity.getMovilCliente()).exec());
        listaErrores.add(new ValidacionFecha(daperClienteEntity.getFechaNacimiento().toString()).exec());
        listaErrores.add(new ValidacionSexo(daperClienteEntity.getSexoCliente()).exec());
        listaErrores.add(new ValidacionEmail(daperClienteEntity.getEmailCliente()).exec());
        listaErrores.add(new VerificacionCodigoPostal(daperClienteEntity.getCodigoPostalCliente()).exec());

        return listaErrores;
    }
}

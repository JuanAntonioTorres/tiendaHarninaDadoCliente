package cliente;

import reflexion.RequestTransferSession;
import reflexion.SessionTransferObject;
import entity.LoginClienteHarnina;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.lang.reflect.InvocationTargetException;

public  class DataBundleCliente {

    LoginClienteHarnina loginClienteEntity = new LoginClienteHarnina();
    HttpSession session;


    public DataBundleCliente(HttpServletRequest request) throws InvocationTargetException, IllegalAccessException {

        session = request.getSession();

        new RequestTransferSession().guardarDatosSesion(request,session);

        new SessionTransferObject(session,loginClienteEntity);
    }

}

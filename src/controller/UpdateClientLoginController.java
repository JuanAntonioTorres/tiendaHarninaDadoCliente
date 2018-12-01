package controller;

import cliente.DataLoginCliente;
import dao.clienteDAO.ClienteDAO;
import error.EstadoError;
import cliente.ComandoValidarLoginCliente;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet("/valiCliUpdateLogin")
@MultipartConfig
public class UpdateClientLoginController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    HttpSession session;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        session = request.getSession();

        request.setCharacterEncoding("UTF-8");

        response.setContentType("text/html");

        DataLoginCliente dataLoginCliente = null;

        try {
            dataLoginCliente = new DataLoginCliente(request);

        } catch (InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }

        ComandoValidarLoginCliente comandosLogin = new ComandoValidarLoginCliente(dataLoginCliente.getLoginClienteEntity());

        ArrayList<Integer> listaErrores = new ArrayList<Integer>();

        listaErrores = comandosLogin.getCommands();

        String mensaje = "";

        for(Integer error:listaErrores){
            for (EstadoError estado : EstadoError.values()){
                if(error == estado.getId() & error != 0) mensaje = mensaje.concat(estado.getMsg());
            }
        }
        request.setAttribute("mensaje", mensaje);

        if(mensaje.equals("")) {
            ClienteDAO clienteDAO = new ClienteDAO();

            dataLoginCliente.setUsuarioCliente((String) session.getAttribute("nifClient"));

            try {
                if( clienteDAO.update_client_login(dataLoginCliente.getLoginClienteEntity())) request.setAttribute("mensaje", "Login Modificado ");
                else request.setAttribute("mensaje", "Login NO se ha podido modificar");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        RequestDispatcher rd = request.getRequestDispatcher("cliente/clienteIndex.jsp");
        rd.forward(request, response);

    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

}

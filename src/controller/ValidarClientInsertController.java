package controller;

import cliente.*;
import dao.clienteDAO.ClienteDAO;
import dao.clienteDAO.ClienteRoll;
import dao.cp.CPDAO;
import entity.ClienteEntity;
import error.EstadoError;
import utils.CargadorImagen;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet("/valiCliIn")
@MultipartConfig
public class ValidarClientInsertController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    HttpSession session;


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        session = request.getSession();

        request.setCharacterEncoding("UTF-8");

        response.setContentType("text/html");

        RequestDispatcher rd = request.getRequestDispatcher("cliente/clientInsert.jsp");

        DataPersonCliente dataPersonCliente = null;
        try {
            dataPersonCliente = new DataPersonCliente(request);

        } catch (InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }

        ArrayList<Integer> listaErrores = new ArrayList<Integer>();

        listaErrores = (new ComandoValidarDaperCliente(dataPersonCliente.getDaperClienteEntity()).getCommands());

        listaErrores.addAll(new ComandoValidarLoginCliente(dataPersonCliente.getLoginClienteEntity()).getCommands());


        String mensaje = "";

        for(Integer error:listaErrores){
            for (EstadoError estado : EstadoError.values()){
                if(error == estado.getId() & error != 0) mensaje = mensaje.concat(estado.getMsg());
            }
        }
        request.setAttribute("mensaje", mensaje);

        if(mensaje.equals("")) {


            new CargadorImagen(request.getPart("imagenCliente"),getServletContext().getRealPath("img/fotoClient/"),dataPersonCliente.getNifCliente()).clientFotoLoad();

            dataPersonCliente.setImagenCliente(dataPersonCliente.getNifCliente() + ".png");


            ClienteDAO clienteDAO = new ClienteDAO();

            if (clienteDAO.add_cliente_procedure(dataPersonCliente)) {
                request.setAttribute("mensaje", "Cliente add");
                rd = request.getRequestDispatcher("cliente/clienteIndex.jsp");
            } else request.setAttribute("mensaje", "Cliente NO add");

        }

       rd.forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }






}


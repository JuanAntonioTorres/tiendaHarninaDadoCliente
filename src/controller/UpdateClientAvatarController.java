package controller;

import utils.CargadorImagen;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/UpCliAvaCon")
@MultipartConfig
public class UpdateClientAvatarController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    HttpSession session;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        session = request.getSession();

        request.setCharacterEncoding("UTF-8");

        response.setContentType("text/html");

        ArrayList<Integer> listaErrores = new ArrayList<Integer>();

        Part filePart = request.getPart("imagenCliente");

        new CargadorImagen(filePart,getServletContext().getRealPath("img/fotoClient/"), (String) session.getAttribute("nifCliente")).clientFotoLoad();

        request.setAttribute("mensaje", "foto modificada");

        RequestDispatcher rd = request.getRequestDispatcher("cliente/clienteIndex.jsp");

        rd.forward(request, response);

    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

}

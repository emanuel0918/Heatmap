/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Chat;

import Mapa.MapaGeneral;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Alumno
 */
@WebServlet(name = "Buscar", urlPatterns = {"/Buscar"})
public class Buscar extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            HttpSession session = request.getSession();
            String usr = (String) session.getAttribute("usr");
            Usuario.Validaciones validaciones = new Usuario.Validaciones();
            String leng = (String) request.getParameter("leng");
            if (leng == null) {
                leng = (String) session.getAttribute("leng");
            }
            BD.cDatos sql = new BD.cDatos();
            String html = "";
            try {
                Usuario.VistaUsuario vista = new Usuario.VistaUsuario();
                String menu = vista.menu(usr, leng, ((String) request.getRequestURI()));
                String menuMap = vista.menuMap(usr, leng, "/heatmap/MapaGeneral");
                String popupLogin = vista.getPopupLogin(usr, leng);
                sql.conectar();
                ResultSet rLang = sql.consulta("select * from lenguaje where leng='" + leng + "';");
                if ((rLang.next())) {
                    rLang = sql.consulta("select * from lenguaje where leng='" + leng + "';");
                } else {
                    rLang = sql.consulta("select leng from lenguaje ;");
                    if (rLang.next()) {
                        rLang = sql.consulta("select * from lenguaje where leng='" + rLang.getString("leng") + "';");
                    }
                }
                while (rLang.next()) {
                    ResultSet r = sql.consulta("select * from usuario where usuario='" + usr + "';");
                    if (r.next()) {
                        session.setAttribute("usr", ((String) session.getAttribute("usr")));
                        session.setAttribute("leng", rLang.getString("leng"));
                        session.setAttribute("url", ((String) request.getRequestURI()));
                        html = "<!DOCTYPE html>\n"
                                + "<!--\n"
                                + "To change this license header, choose License Headers in Project Properties.\n"
                                + "To change this template file, choose Tools | Templates\n"
                                + "and open the template in the editor.\n"
                                + "-->\n"
                                + "<html>\n"
                                + "    <head>\n"
                                + "        <title>" + rLang.getString("sBuscador") + "</title>\n"
                                + "        <meta charset='UTF-8'>\n"
                                + "        <meta name='viewport' content='width=device-width, initial-scale=1.0'>\n"
                                + "        <link rel='icon' type='image/png' href='Imagenes/hsx32.png' />\n"
                                + "        <link rel='stylesheet' href='http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css'>\n"
                                + "        <link rel='stylesheet' href='http://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.4.0/css/font-awesome.min.css'>\n"
                                + "        <link href='vista/general.css' rel='stylesheet' type='text/css'/>\n"
                                + "        <script src='https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js'></script>\n"
                                + "        <script src='http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js'></script>\n"
                                + "        <script src='http://maps.googleapis.com/maps/api/js'></script>\n"
                                + "        <script src='JS/General.js'></script>\n"
                                + "        <script>\n  "
                                + "             function holamundo() {\n"
                                + "\n"
                                + "                if (event.keyCode === 13 && document.formName.query.value !== null){\n"
                                + "                document.getElementById('formId').submit();\n"
                                + "                }\n"
                                + "            }\n"
                                + "\n"
                                + "\n"
                                + "            function oc() {\n"
                                + "                if (document.formName.query.value !== null)\n"
                                + "                        document.getElementById('formId').submit();\n"
                                + "\n"
                                + "\n"
                                + "            }"
                                + "        </script>\n"
                                + "    </head>\n"
                                + "    <body>\n"
                                + "        <div id='all'>\n"
                                + "        <div class='head-index'>\n" + menu
                                + "        </div>\n"
                                + "        <div id='mapa' class='contenido-index'>\n"
                                + "\n"
                                + "\n"
                                + "    \n"
                                + "        <form name=\"formName\" id=\"formId\" action=\"Buscar\" method=\"post\">\n"
                                + "            <input type=\"text\" name=\"query\" value=\"\" onfocus=\"holamundo()\">\n"
                                + "            <input type=\"submit\" value=\"BUSCAR\">\n"
                                + "        </form>\n"
                                + "    \n"
                                + "\n"
                                + "    \n"
                                + "                    \n"
                                + "    <form name=\"ola2\" action=\"Agregar\"><input type=\"text\" name=\"idu\" hidden=\"\" value=\"5\"> <input type=\"submit\" value=\"AGREGAR A CONTACTOS\"></form>\n"
                                + "                    \n"
                                + "                    \n"
                                + "                    \n"
                                + "                    \n"
                                + "                    <br>\n"
                                + "     \n"
                                + "\n"
                                + "\n"
                                + ""
                                + "        </div>\n"
                                + "        <div class='foot-index'>\n"
                                + "            <center >           \n"
                                + "                <h4>HeatMap© by Quanthink CopyRigth 2015.</h4>\n"
                                + "            </center>\n"
                                + "        </div>\n"
                                + "        <input type='hidden' id='refreshed' value='no'>\n" + popupLogin
                                + "        </div>\n"
                                + "    </body>\n"
                                + "</html>\n"
                                + "\n"
                                + "\n"
                                + "";

                        out.print(html);
                    } else {

                        out.print("<script>location.replace('/heatmap/Error');</script>");
                    }
                }
            } catch (SQLException ex) {
                Logger.getLogger(MapaGeneral.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

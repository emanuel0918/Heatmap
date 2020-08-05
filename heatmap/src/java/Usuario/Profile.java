/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Usuario;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
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
@WebServlet(name = "Profile", urlPatterns = {"/Profile"})
public class Profile extends HttpServlet {

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
            /* TODO output your page here. You may use following sample code. */

            HttpSession session = request.getSession();
            if (!(session.getAttribute("idUsuario") == null)) {

                int idUsr = (int) session.getAttribute("idUsuario");
                BD.cDatos sql = new BD.cDatos();
                try {
                    sql.conectar();

                    ResultSet r = sql.consulta("select nombre,apellido from usuario"
                            + " where idusuario=" + idUsr + ";");

                    while (r.next()) {
                        String html = "\n"
                                + "        <section class='container-fluid'>\n"
                                + "            <div class='chat col-sm-3'>\n"
                                + "                <div class='titulo'>\n"
                                + "                    <h2 class='titulo-tex'>Amigos</h2>\n"
                                + "                </div>\n"
                                + "                <div class='persona-main'>\n"
                                + "                    <h3 class='persona-tex'>Disponibles</h3>\n"
                                + "                    <div class='persona'>\n"
                                + "                        <div class='img-frame'>\n"
                                + "                            <img src='http://i.imgur.com/6cY0syl.png' alt='usuario'/>\n"
                                + "                            <span>nombre de usuario</span>\n"
                                + "                        </div>\n"
                                + "                    </div>\n"
                                + "                </div>\n"
                                + "            </div>\n"
                                + "            <div class='mapa col-sm-9'>\n"
                                + "                <div class='menu'>\n"
                                + "                    <ul>\n"
                                + "                    </ul>\n"
                                + "                </div>\n"
                                + "                <div id='mapa-general' onclick='hola();'>\n"
                                + "                    \n"
                                + "                </div>\n"
                                + "                <input type='text' id='cuchi' name='a' hidden >\n"
                                + "            <input type='text' id='cuch' name='b' hidden>\n"
                                + "            </div>\n"
                                + "        </section>\n"
                                + "        <div id='myModal' class='modal fade' role='dialog'>\n"
                                + "            <div class='modal-dialog'>\n"
                                + "\n"
                                + "                <div class='modal-content'>\n"
                                + "                    <div class='modal-header'>\n"
                                + "                        <a class='close' style='font-size: 2.5em;' data-dismiss='modal'>&times;</a>\n"
                                + "                        <h1 class='modal-title'>Punto favorito</h1>\n"
                                + "                    </div>\n"
                                + "                    <div class='modal-body'>\n"
                                + "                        <h3>Llena los siguientes campos</h3>\n"
                                + "                        <form action=''>\n"
                                + "                            <table>\n"
                                + "                                <tr>\n"
                                + "                                    <td class='labeltd'>Tipo</td>\n"
                                + "                                    <td>\n"
                                + "                                        <select>\n"
                                + "                                            <option>Asalto</option>\n"
                                + "                                            <option>Carterista</option>\n"
                                + "                                            <option>Otro...</option>\n"
                                + "                                        </select>\n"
                                + "                                    </td>\n"
                                + "                                </tr>\n"
                                + "                                <tr><td class='labeltd'>Descripcion</td><td><input class='required' type='text' name='apellido' placeholder='Escribe tus apellidos' id='apellidoid' onkeypress='return soloLetras(event)'></td></tr>\n"
                                + "                                <tr><td class='labeltd'>horario</td><td><input class='required' type='text' name='email' placeholder='Escribe tu email' id='emailid' ></td></tr>\n"
                                + "                                <tr><td><td><td><input class='boton'  type='submit' value='Enviar' onclick='return camposValidos();'></td></tr>\n"
                                + "                            </table>\n"
                                + "                            \n"
                                + "                        </form>\n"
                                + "                        \n"
                                + "                    </div>\n"
                                + "                    \n"
                                + "                </div>\n"
                                + "            </div>\n"
                                + "        </div>\n"
                                + "        ";
                        out.print(pintaMenu(idUsr, (r.getString("nombre")),
                                (r.getString("apellido")), html));

                    }
                } catch (SQLException | NumberFormatException exceptions) {
                    out.println("Stack Trace:<br/>");
                    exceptions.printStackTrace(out);
                    out.println("<br/><br/>Stack Trace (for web display):</br>");
                    out.println(displayErrorForWeb(exceptions));

                }
            } else {
                response.sendRedirect("index.html");
            }
        }
    }

    public String displayErrorForWeb(Throwable t) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        t.printStackTrace(pw);
        String stackTrace = sw.toString();
        return stackTrace.replace(System.getProperty("line.separator"), "<br/>\n");
    }

    private String pintaMenu(int idusr, String nombreUsr, String apellido, String menuBody) {
        String html;
        html = "<html>\n"
                + "    <head>\n"
                + "        <title>" + nombreUsr + " " + apellido + "</title>\n"
                + "        <meta charset='UTF-8'>\n"
                + "        <meta name='viewport' content='width=device-width, initial-scale=1.0'>\n"
                + "<link rel='icon' type='image/png' href='http://i.imgur.com/mQI4Lqp.png' />"
                + "        <link rel='stylesheet' href='http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css'>\n"
                + "        <link rel='stylesheet' href='http://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.4.0/css/font-awesome.min.css'>\n"
                + "        <link href='vista/Reset.css' rel='stylesheet' type='text/css'/>\n"
                + "        <link href='vista/normalize.css' rel='stylesheet' type='text/css'/>\n"
                + "        <link href='vista/hojaGeneral.css' rel='stylesheet' type='text/css'/>\n"
                + "        <link href='vista/hoja6Usuario.css' rel='stylesheet' type='text/css'/>\n"
                + "        <script src='https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js'></script>\n"
                + "        <script src='http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js'></script>\n"
                + "        <script src='JS/FunctionsJS.js'></script>\n"
                + "    </head>\n"
                + "    <body  >\n"
                + "        <nav class='navbar navbar-default'>\n"
                + "            <div class='container'>\n"
                + "                <div class='navbar-header'>\n"
                + "                    <button type='button' class='navbar-toggle' data-toggle='collapse' data-target='#myNavBar'>\n"
                + "                        <span class='icon-bar'></span>\n"
                + "                        <span class='icon-bar'></span>\n"
                + "                    </button>\n"
                + "                    <a class='navbar-brand Inicio' href='index.html'>HeatMap</a>\n"
                + "                </div>\n"
                + "                <div class='collapse navbar-collapse' id='myNavBar'>\n"
                + "                    <ul class='nav navbar-nav'>\n"
                + "                        <li><a class='btn btn-sucess' href='MapaGeneral'>Ir al mapa</a></li>\n"
                + "                    </ul>\n"
                + "                    <ul class='nav navbar-nav navbar-right'>";
        BD.cDatos sql = new BD.cDatos();
        try {
            sql.conectar();
            ResultSet r = sql.consulta("call sp_Menu('" + idusr + "');");
            while (r.next()) {
                html += "<li><a href='" + r.getString("ruta") + "' "
                        + "class='btn btn-sucess resaltar'>" + r.getString("titulo") + "</a></li>";
            }
            html += "</ul>\n"
                    + "                </div>\n"
                    + "            </div>\n"
                    + "        </nav>\n" + menuBody
                    + "    </body>\n"
                    + "<input type='hidden' id='refreshed' value='no'>\n"
                    + "</html>\n"
                    + "";

        } catch (SQLException | NumberFormatException exceptions) {
            html = "Error del menu...";
        }

        return html;
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

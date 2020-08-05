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
@WebServlet(name = "OlvidarContrasena2", urlPatterns = {"/OlvidarContrasena2"})
public class OlvidarContrasena2 extends HttpServlet {

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
            Usuario.VistaUsuario vista = new Usuario.VistaUsuario();
            String usr = (String) session.getAttribute("usr");
            String token = (String) request.getParameter("token");
            BD.cDatos sql = new BD.cDatos();
            try {
                sql.conectar();
                String leng = (String) request.getParameter("leng");
                if (leng == null) {
                    leng = (String) session.getAttribute("leng");
                }
                String html = "";
                String menu = vista.menu(usr, leng, ((String) request.getRequestURI()));
                String popupLogin = vista.getPopupLogin(usr, leng);
                ResultSet rLang = sql.consulta("select * from lenguaje where leng='" + leng + "';");
                if ((rLang.next())) {
                    rLang = sql.consulta("select * from lenguaje where leng='" + leng + "';");
                } else {
                    rLang = sql.consulta("select leng from lenguaje ;");
                    if (rLang.next()) {
                        rLang = sql.consulta("select * from lenguaje where leng='" + rLang.getString("leng") + "';");
                    }
                }
                if (rLang.next()) {
                    ResultSet rUsr = sql.consulta("select * from usuario where usuario='" + usr + "';");
                    if (!(rUsr.next())) {
                        session.setAttribute("usr", ((String) session.getAttribute("usr")));
                        session.setAttribute("leng", rLang.getString("leng"));
                        session.setAttribute("url", ((String) session.getAttribute("url")));
                        html = "<!DOCTYPE html>\n"
                                + "<!--\n"
                                + "To change this license header, choose License Headers in Project Properties.\n"
                                + "To change this template file, choose Tools | Templates\n"
                                + "and open the template in the editor.\n"
                                + "-->\n"
                                + "<html>\n"
                                + "    <head>\n"
                                + "        <title>" + rLang.getString("sOlvidarContrasena2") + "</title>\n"
                                + "        <meta charset='UTF-8'>\n"
                                + "        <meta name='viewport' content='width=device-width, initial-scale=1.0'>\n"
                                + "        <link rel='icon' type='image/png' href='http://i.imgur.com/mQI4Lqp.png' />\n"
                                + "        <link rel='stylesheet' href='http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css'>\n"
                                + "        <link rel='stylesheet' href='http://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.4.0/css/font-awesome.min.css'>\n"
                                + "        <link href='vista/general.css' rel='stylesheet' type='text/css'/>\n"
                                + "        <script src='https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js'></script>\n"
                                + "        <script src='http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js'></script>\n"
                                + "        <script src='JS/General.js'></script>\n"
                                + "    </head>\n"
                                + "    <body   >\n"
                                + "        <div id='all'>\n"
                                + "        <div class='head-index'>\n"
                                + menu
                                + "        </div>\n"
                                + "        <div id='main-home' class='contenido-index'>\n"
                                + "        <center>\n"
                                + "        <h1>" + rLang.getString("sOlvidarContrasena2") + "</h1>\n"
                                + "        <form  action='" + Usuario.OlvidarContrasena3.class.getSimpleName()
                                + "' method='post'>\n"
                                + "        <blockquote>              \n"
                                + "            <table>\n"
                                + "                <tr><td class='labeltd'>" + rLang.getString("sNuevaContrasena") + ":</td><td><input required type='password' id='passid' class='required' name='newpsw' ></td></tr>\n"
                                + "                <tr><td class='labeltd'>" + rLang.getString("sConfirmarContrasena") + ":</td><td><input required type='password' id='pass2id' class='required' name='newpsw2' ></td></tr>\n"
                                + "                <tr><td class='labeltd'></td><td><input type='submit' id='cambiaPassId' class='enviar' value='" + rLang.getString("sEntrar") + "'></td></tr>\n"
                                + "            </table>\n"
                                + "        </blockquote> <input type='hidden' name='token' value='" + token + "'> \n"
                                + "        \n"
                                + "        </form>\n"
                                + "</div>\n"
                                + "        \n"
                                + "        <input type='hidden' id='refreshed' value='no'>\n"
                                + "        \n"
                                + popupLogin
                                + "        </div>\n"
                                + "    </body>\n"
                                + "</html>\n"
                                + "\n"
                                + "\n"
                                + "";

                    } else {
                        session.setAttribute("usr", ((String) session.getAttribute("usr")));
                        session.setAttribute("leng", rLang.getString("leng"));
                        session.setAttribute("url", ((String) session.getAttribute("url")));
                        response.sendRedirect(((String) session.getAttribute("url")));
                    }
                }
                out.print(html);
            } catch (SQLException ex) {
                Logger.getLogger(OlvidarContrasena2.class.getSimpleName()).log(Level.SEVERE, null, ex);
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

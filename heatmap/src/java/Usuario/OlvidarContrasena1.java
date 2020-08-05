/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Usuario;

import BD.cDatos;
import JavaMail.SendMail;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.InetAddress;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Elena
 */
@WebServlet(name = "OlvidarContrasena1", urlPatterns = {"/OlvidarContrasena1"})
public class OlvidarContrasena1 extends HttpServlet {

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
            String email = (String) request.getParameter("correo");
            HttpSession session = request.getSession();
            Usuario.VistaUsuario vista = new Usuario.VistaUsuario();
            String usr = (String) session.getAttribute("usr");
            String leng = (String) session.getAttribute("leng");
            BD.cDatos sql = new BD.cDatos();
            try {
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
                if (rLang.next()) {
                    ResultSet r = sql.consulta("select * from usuario where usuario='" + usr + "';");
                    if (!(r.next())) {
                        session.setAttribute("usr", ((String) session.getAttribute("usr")));
                        session.setAttribute("leng", rLang.getString("leng"));
                        session.setAttribute("url", ((String) request.getRequestURI()));
                        r = sql.consulta("select * from usuario where correo='" + email + "';");
                        if ((r.next())) {
                            Security.Codigo codigo = new Security.Codigo();
                            String token = codigo.getCodigo();
                            sql.actualizar("call sp_SolicitudModificaPaswordUsuario('" + token + "','" + email + "');");
                            String url = InetAddress.getLocalHost().getHostAddress()+":8080/heatmap/"
                                    + Usuario.OlvidarContrasena2.class.getSimpleName()
                                    + "?token=" + token;
                            String cuerpoEmail = " \n"
                                    + "            <h1 style='color:#367d39; font-size: 350% '>" + rLang.getString("sOlvidarContrasena2") + "</h1>  \n"
                                    + "        <br><br>  \n"
                                    + "             \n" + rLang.getString("sCorreoEnviadoRegistroCuerpo")
                                    + "\n\n<br><br>" + url + " <br><br>HeatMap&#169; by Quanthink CopyRigth 2015 \n"
                                    + "    ";
                            out.print("<script>location.replace('" + Usuario.HeatMap.class.getSimpleName() + "');"
                                    + "alert('" + rLang.getString("sCorreoEnviadoRegistroAlert") + "');</script>");
                            try {
                                JavaMail.SendMail.generateAndSendEmail(email, cuerpoEmail, rLang.getString("sOlvidarContrasena1"));
                            } catch (MessagingException ex) {
                                Logger.getLogger(RegistroUsuario2.class.getSimpleName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    } else {

                        session.setAttribute("usr", ((String) session.getAttribute("usr")));
                        session.setAttribute("leng", rLang.getString("leng"));
                        session.setAttribute("url", ((String) session.getAttribute("url")));
                        response.sendRedirect(((String) session.getAttribute("url")));
                    }
                }

            } catch (SQLException ex) {
                Logger.getLogger(OlvidarContrasena.class.getSimpleName()).log(Level.SEVERE, null, ex);
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

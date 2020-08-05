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
@WebServlet(name = "OlvidarContrasena3", urlPatterns = {"/OlvidarContrasena3"})
public class OlvidarContrasena3 extends HttpServlet {

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
            String pass1 = (String) request.getParameter("newpsw");
            String pass2 = (String) request.getParameter("newpsw2");
            String token = (String) request.getParameter("token");
            HttpSession session = request.getSession();
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
                        session.setAttribute("url", ((String) session.getAttribute("url")));
                        if ((pass2.equals(pass1))) {
                            r = sql.consulta("call sp_ModificaPaswordUsuario('" + token + "','" + pass1 + "');");
                            while (r.next()) {
                                if (r.getString("idStatus").equals("1")) {
                                    out.print("<script>alert('"
                                            + rLang.getString(r.getString("mensaje"))
                                            + "');location.replace('"
                                            + Usuario.HeatMap.class.getSimpleName()
                                            +"');</script>");
                                } else {
                                    out.print("<script>alert('"
                                            + rLang.getString(r.getString("mensaje"))
                                            + "');location.replace('"
                                            + Usuario.OlvidarContrasena2.class.getSimpleName()
                                            + "?token=" + token + "');</script>");
                                }
                            }
                        } else {
                            out.print("<script>alert('"
                                    + rLang.getString("sContrasenaIgualNo")
                                    + "');location.replace('"
                                    + Usuario.OlvidarContrasena2.class.getSimpleName()
                                    + "?token=" + token + "');</script>");
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

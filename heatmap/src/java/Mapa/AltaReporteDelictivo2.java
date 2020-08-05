/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mapa;

import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "AltaReporteDelictivo2", urlPatterns = {"/AltaReporteDelictivo2"})
public class AltaReporteDelictivo2 extends HttpServlet {

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
            Usuario.Validaciones validaciones = new Usuario.Validaciones();
            String usr = (String) session.getAttribute("usr");
            String url = (String) session.getAttribute("url");
            session.setAttribute("usr", usr);
            String leng = (String) request.getParameter("leng");
            if (leng == null) {
                leng = (String) session.getAttribute("leng");
            }
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
                    if (r.next()) {
                        String coorx = (String) request.getParameter("coorx");
                        String coory = (String) request.getParameter("coory");
                        String titulo = (String) request.getParameter("titulo");
                        String desc = (String) request.getParameter("desc");
                        String tipo = (String) request.getParameter("tipo");
                        String urlParametros = Mapa.AltaReporteDelictivo1.class.getSimpleName();
                        if (!(titulo.equals("") || tipo.equals(""))) {
                            if (validaciones.decimalValido(coorx) && validaciones.decimalValido(coory)) {
                                urlParametros += "?cx=" + coorx + "&cy=" + coory;
                                if ((tipo.equals(rLang.getString("sTipo1"))
                                        || tipo.equals(rLang.getString("sTipo2"))
                                        || tipo.equals(rLang.getString("sTipo3"))
                                        || tipo.equals(rLang.getString("sTipo4")))) {
                                    if (tipo.equals(rLang.getString("sTipo1"))) {
                                        tipo = "tipo1";
                                    }
                                    if (tipo.equals(rLang.getString("sTipo2"))) {
                                        tipo = "tipo2";
                                    }
                                    if (tipo.equals(rLang.getString("sTipo3"))) {
                                        tipo = "tipo3";
                                    }
                                    if (tipo.equals(rLang.getString("sTipo4"))) {
                                        tipo = "tipo4";
                                    }
                                    r = sql.consulta("call sp_AltaReporteDelictivo('" + usr + "',"
                                            + "'" + titulo + "','" + desc + "','" + coorx + "', '" + coory + "','" + tipo + "');");
                                    while (r.next()) {

                                        if (r.getString("idStatus").equals("1")) {
                                            out.print("<script>alert('" + rLang.getString(r.getString("mensaje")) + "');"
                                                    + "location.replace('" + Mapa.MapaDelictivo.class.getSimpleName() + "');</script>");
                                        }
                                    }
                                    out.print("<script>alert('" + rLang.getString(r.getString("mensaje")) + "');"
                                            + "location.replace('" + urlParametros + "');</script>");
                                } else {
                                    out.print("<script>alert('" + rLang.getString("sTipoNoValido") + "');"
                                            + "location.replace('" + urlParametros + "');</script>");

                                }
                            } else {
                                out.print("<script>alert('" + rLang.getString("sCoordenadaNoValida") + "');"
                                        + "location.replace('" + urlParametros + "');</script>");

                            }
                        } else {
                            out.print("<script>alert('" + rLang.getString("sNoCamposVacios") + "');"
                                    + "location.replace('" + urlParametros + "');</script>");

                        }
                    }

                }
            } catch (SQLException | NumberFormatException exceptions) {

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

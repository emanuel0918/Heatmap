/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Usuario;

import Security.AlgoritmoSHA1;
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
@WebServlet(name = "Login", urlPatterns = {"/Login"})
public class Login extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @throws java.sql.SQLException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            HttpSession session = request.getSession();
            String url = (String) session.getAttribute("url");
            if (url == null
                    || ((url.length() > 0))
                    && ((url.substring((url.length() - 6)).equals("/Error")))) {
                url = "/heatmap/HeatMap";
            }
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
                    if (session.getAttribute("usr") == null) {
                        String usuario = request.getParameter("usr");
                        String pass = request.getParameter("pasw");
                        pass = AlgoritmoSHA1.resumen(pass);
                        HeatmapWebService.HeatmapWebServiceUsuario
                                webService = new 
                                HeatmapWebService.HeatmapWebServiceUsuario();
                        
                        if (webService.validaUsuario(usuario, pass) == 1) {
                            usuario=webService.getUsuario(usuario);
                            session.setAttribute("usr", usuario);
                            session.setAttribute("leng", rLang.getString("leng"));
                            session.setAttribute("url", ((String) session.getAttribute("url")));
                            response.sendRedirect(url);
                        } else {
                            session.setAttribute("leng", rLang.getString("leng"));
                            out.println("<script>alert('" + rLang.getString("sUsuarioContrasenaIncorrecto") + "');</script>");
                            out.print("<script>location.replace('" + url + "');</script>");

                        }
                    } else {
                        out.print("<script>location.replace('" + url + "');</script>");
                    }
                }

            } catch (SQLException ex) {
                Logger.getLogger(HeatMap.class.getSimpleName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    protected void processRequestGET(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //me llega la url "proyecto/login/out"
        String action = (request.getPathInfo() != null ? request.getPathInfo() : "");
        HttpSession sesion = request.getSession();
        if (action.equals("/out")) {
            sesion.invalidate();
            response.sendRedirect("InicioSesion");
        } else {

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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(Login.class.getSimpleName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(Login.class.getSimpleName()).log(Level.SEVERE, null, ex);
        }
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
/**
 * * if (Integer.parseInt(rsUsr.getString("idusuario")) > 0) {
 * menuUsuario(Integer.parseInt(rsUsr.getString("idusuario"))); } else {
 * out.println("<script>alert('hola');</script>");
 *
 * } **
 *///

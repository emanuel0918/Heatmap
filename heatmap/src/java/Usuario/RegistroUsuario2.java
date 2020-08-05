/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Usuario;

import Security.Codigo;
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
@WebServlet(name = "RegistroUsuario2", urlPatterns = {"/RegistroUsuario2"})
public class RegistroUsuario2 extends HttpServlet {

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
            HttpSession session = request.getSession();
            String usr = (String) session.getAttribute("usr");
            BD.cDatos sql = new BD.cDatos();
            try {
                sql.conectar();
                String leng = (String) request.getParameter("leng");
                if (leng == null) {
                    leng = (String) session.getAttribute("leng");
                }
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
                    if (!rUsr.next()) {
                        session.setAttribute("usr", ((String) session.getAttribute("usr")));
                        session.setAttribute("leng", rLang.getString("leng"));
                        session.setAttribute("url", ((String) session.getAttribute("url")));
                        String correo = request.getParameter("email");
                        String usuario = request.getParameter("usuario");
                        String pass = request.getParameter("pasword");
                        String pass2 = request.getParameter("pasword2");
                        String nombre = request.getParameter("nombre");
                        String apellido = request.getParameter("apellido");
                        String sexo = request.getParameter("sex");
                        String fecha = request.getParameter("fecha");
                        String telefono = request.getParameter("telefono");
                        if (!(correo.equals("") || usuario.equals("")
                                || pass.equals("") || nombre.equals("")
                                || apellido.equals("") || fecha.equals(""))) {
                            Usuario.Validaciones validaciones = new Usuario.Validaciones();
                            if (validaciones.correoValido(correo)) {
                                if (validaciones.usuarioValido(usuario)) {
                                    if (validaciones.fechaValida(fecha)) {
                                        if (validaciones.paswordValida(pass)) {
                                            if (pass.equals(pass2)) {

                                                if (validaciones.telefonoValido(telefono) || telefono.equals("")) {
                                                    if (nombre.length() <= 15) {
                                                        if (sexo.equals("M") || sexo.equals("F")) {
                                                            nombre = nombre.replace("'", "&#39;");
                                                            apellido = apellido.replace("'", "&#39;");

                                                            Codigo codigo = new Codigo(25);
                                                            String clue = codigo.getCodigo();
                                                            pass = Security.AlgoritmoSHA1.resumen(pass);

                                                            ResultSet r = sql.consulta("call sp_ValidaUsuario('" + usuario + "');");
                                                            if (r.next()) {
                                                                if (r.getString("idStatus").equals("1")) {
                                                                    r = sql.consulta("call sp_AltaUsuario('" + usuario + "',\n"
                                                                            + "'" + pass + "','" + correo + "','" + nombre + "', \n"
                                                                            + "'" + apellido + "','" + fecha + "', '" + sexo + "','','" + telefono + "',null,null,'" + clue + "');");
                                                                    if (r.next()) {
                                                                        if (r.getString("idStatus").equals("1")) {
                                                                            String url = InetAddress.getLocalHost().getHostAddress() + ":8080/heatmap/ConfirmaUsuario?token=" + clue;
                                                                            String cuerpoEmail = " \n"
                                                                                    + "            <h1 style='color:#367d39; font-size: 350% '>" + rLang.getString("sBienvenido") + "</h1>  \n"
                                                                                    + "        <br><br>  \n"
                                                                                    + "             \n" + rLang.getString("sCorreoEnviadoRegistroCuerpo")
                                                                                    + "\n\n<br><br>" + url + " <br><br>HeatMap&#169; by Quanthink CopyRigth 2015 \n"
                                                                                    + "    ";
                                                                            out.print("<script>location.replace('" + Usuario.HeatMap.class.getSimpleName() + "');"
                                                                                    + "alert('" + rLang.getString("sCorreoEnviadoRegistroAlert") + "');</script>");
                                                                            try {
                                                                                JavaMail.SendMail.generateAndSendEmail(correo, cuerpoEmail, rLang.getString("sCorreoEnviadoRegistroTitulo"));
                                                                            } catch (MessagingException ex) {
                                                                                Logger.getLogger(RegistroUsuario2.class.getSimpleName()).log(Level.SEVERE, null, ex);
                                                                            }
                                                                        }
                                                                    } else {
                                                                        out.print("<script>alert('" + rLang.getString(r.getString("mensaje")) + "');location.replace('" + Usuario.RegistroUsuario1.class.getSimpleName() + "');</script>");
                                                                    }

                                                                } else {
                                                                    out.print("<script>alert('" + rLang.getString(r.getString("mensaje")) + "');location.replace('" + Usuario.RegistroUsuario1.class.getSimpleName() + "');</script>");
                                                                }
                                                            }
                                                        } else {
                                                            out.print("<script>alert('" + rLang.getString("sSexoNoValido") + "');location.replace('" + Usuario.RegistroUsuario1.class.getSimpleName() + "');</script>");
                                                        }
                                                    } else {
                                                        out.print("<script>alert('" + rLang.getString("sNombreLargo") + "');location.replace('" + Usuario.RegistroUsuario1.class.getSimpleName() + "');</script>");
                                                    }
                                                } else {
                                                    out.print("<script>alert('" + rLang.getString("sTelefonoNoValido") + "');location.replace('" + Usuario.RegistroUsuario1.class.getSimpleName() + "');</script>");
                                                }
                                            } else {
                                                out.print("<script>alert('" + rLang.getString("sContrasenasNoIguales") + "');location.replace('" + Usuario.RegistroUsuario1.class.getSimpleName() + "');</script>");
                                            }
                                        } else {
                                            out.print("<script>alert('" + rLang.getString("sContrasenaNoValida") + "');location.replace('" + Usuario.RegistroUsuario1.class.getSimpleName() + "');</script>");
                                        }
                                    } else {
                                        out.print("<script>alert('" + rLang.getString("sFechaNoValida") + "');location.replace('" + Usuario.RegistroUsuario1.class.getSimpleName() + "');</script>");
                                    }
                                } else {
                                    out.print("<script>alert('" + rLang.getString("sUsuarioNoValido") + "');location.replace('" + Usuario.RegistroUsuario1.class.getSimpleName() + "');</script>");
                                }
                            } else {
                                out.print("<script>alert('" + rLang.getString("sCorreoNoValido") + "');location.replace('" + Usuario.RegistroUsuario1.class.getSimpleName() + "');</script>");
                            }
                        } else {
                            //Campos vacios
                            out.print("<script>alert('" + rLang.getString("sNoCamposVacios") + "');location.replace('" + Usuario.RegistroUsuario1.class.getSimpleName() + "');</script>");
                        }

                    } else {

                        session.setAttribute("usr", ((String) session.getAttribute("usr")));
                        session.setAttribute("leng", rLang.getString("leng"));
                        session.setAttribute("url", ((String) session.getAttribute("url")));
                        response.sendRedirect(((String) session.getAttribute("url")));
                    }
                }
            } catch (SQLException ex) {
                Logger.getLogger(RegistroUsuario1.class.getSimpleName()).log(Level.SEVERE, null, ex);
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
        try {
            processRequest(request, response);

        } catch (SQLException ex) {
            Logger.getLogger(RegistroUsuario2.class
                    .getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(RegistroUsuario2.class
                    .getName()).log(Level.SEVERE, null, ex);
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

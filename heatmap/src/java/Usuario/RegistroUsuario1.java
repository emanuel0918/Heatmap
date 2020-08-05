/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Usuario;

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
@WebServlet(name = "RegistroUsuario1", urlPatterns = {"/RegistroUsuario1"})
public class RegistroUsuario1 extends HttpServlet {

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
                        session.setAttribute("url", ((String) request.getRequestURI()));
                        html = "<!DOCTYPE html>\n"
                                + "<!--\n"
                                + "To change this license header, choose License Headers in Project Properties.\n"
                                + "To change this template file, choose Tools | Templates\n"
                                + "and open the template in the editor.\n"
                                + "-->\n"
                                + "<html>\n"
                                + "    <head>\n"
                                + "        <title>" + rLang.getString("sRegistrate") + "</title>\n"
                                + "        <meta charset='UTF-8'>\n"
                                + "        <meta name='viewport' content='width=device-width, initial-scale=1.0'>\n"
                                + "        <link rel='icon' type='image/png' href='http://i.imgur.com/mQI4Lqp.png' />\n"
                                + "        <link rel='stylesheet' href='http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css'>\n"
                                + "        <link rel='stylesheet' href='http://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.4.0/css/font-awesome.min.css'>\n"
                                + "        <link href='vista/registro.css' rel='stylesheet' type='text/css'/>\n"
                                + "        <link href='vista/general.css' rel='stylesheet' type='text/css'/>\n"
                                + "        <script src='https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js'></script>\n"
                                + "        <script src='http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js'></script>\n"
                                + "        <script src='JS/General.js'></script>\n"
                                + "        <script src='JS/Registro.js'></script>\n"
                                + " <script>\n"
                                + "function cambiaPassword() {\n"
                                + "    valido = false;\n"
                                + "    if (document.getElementById('passid').value ===\n"
                                + "            document.getElementById('pass2id').value) {\n"
                                + "        if (paswordValida(document.getElementById('passid').value)) {\n"
                                + "            valido = true;\n"
                                + "        } else {\n"
                                + "            alert('" + rLang.getString("sContrasenaFormato") + "');\n"
                                + "            document.getElementById('passid').className = 'required2';\n"
                                + "            document.getElementById('pass2id').className = 'required2';\n"
                                + "        }\n"
                                + "    } else {\n"
                                + "        alert('" + rLang.getString("sContrasenaIgualNo") + "');\n"
                                + "        document.getElementById('passid').className = 'required2';\n"
                                + "        document.getElementById('pass2id').className = 'required2';\n"
                                + "    }\n"
                                + "    return valido;\n"
                                + "}\n"
                                + "window.onload = function () {\n"
                                + "    document.getElementById('registroUsuario').onclick =  function () {\n"
                                + "        return camposValidos();\n"
                                + "    };\n"
                                + "    document.getElementById('nombreid').onkeypress = function (event) {\n"
                                + "        soloLetras(event);\n"
                                + "    };\n"
                                + "    document.getElementById('apellidoid').onkeypress = function (event) {\n"
                                + "        soloLetras(event);\n"
                                + "    };\n"
                                + "    document.getElementById('telefonoid').onkeypress = function (event) {\n"
                                + "        soloNumeros(event);\n"
                                + "    };\n"
                                + "};\n"
                                + "function camposValidos() {\n"
                                + "    document.getElementById('nombreid').className = 'required';\n"
                                + "    document.getElementById('apellidoid').className = 'required';\n"
                                + "    document.getElementById('usuarioid').className = 'required';\n"
                                + "    document.getElementById('emailid').className = 'required';\n"
                                + "    document.getElementById('paswordid').className = 'required';\n"
                                + "    document.getElementById('pasword2id').className = 'required';\n"
                                + "    document.getElementById('fechaid').className = 'required';\n"
                                + "    valido = false;\n"
                                + "    if (document.getElementById('nombreid').value === ''\n"
                                + "            || document.getElementById('apellidoid').value === ''\n"
                                + "            || document.getElementById('emailid').value === ''\n"
                                + "            || document.getElementById('usuarioid').value === ''\n"
                                + "            || document.getElementById('paswordid').value === ''\n"
                                + "            || document.getElementById('pasword2id').value === ''\n"
                                + "            || document.getElementById('fechaid').value === '') {\n"
                                + "                    alert('" + rLang.getString("sCamposObligatorios") + "');\n"
                                + "        if (document.getElementById('usuarioid').value === '') {\n"
                                + "            document.getElementById('usuarioid').className = 'required2';\n"
                                + "        }\n"
                                + "        if (document.getElementById('nombreid').value === '') {\n"
                                + "            document.getElementById('nombreid').className = 'required2';\n"
                                + "        }\n"
                                + "        if (document.getElementById('apellidoid').value === '') {\n"
                                + "            document.getElementById('apellidoid').className = 'required2';\n"
                                + "        }\n"
                                + "        if (document.getElementById('emailid').value === '') {\n"
                                + "            document.getElementById('emailid').className = 'required2';\n"
                                + "        }\n"
                                + "        if (document.getElementById('paswordid').value === '') {\n"
                                + "            document.getElementById('paswordid').className = 'required2';\n"
                                + "        }\n"
                                + "        if (document.getElementById('pasword2id').value === '') {\n"
                                + "            document.getElementById('pasword2id').className = 'required2';\n"
                                + "        }\n"
                                + "        if (document.getElementById('fechaid').value === '') {\n"
                                + "            document.getElementById('fechaid').className = 'required2';\n"
                                + "        }\n"
                                + "\n"
                                + "    } else {\n"
                                + "        if (emailValido(document.getElementById('emailid').value)) {\n"
                                + "\n"
                                + "            if (document.getElementById('paswordid').value ===\n"
                                + "                    document.getElementById('pasword2id').value) {\n"
                                + "                if (paswordValida(document.getElementById('paswordid').value)) {\n"
                                + "                    valido = true;\n"
                                + "                } else {\n"
                                + "                    alert('" + rLang.getString("sContrasenaFormato") + "');\n"
                                + "                    document.getElementById('paswordid').className = 'required2';\n"
                                + "                    document.getElementById('pasword2id').className = 'required2';\n"
                                + "                }\n"
                                + "            } else {\n"
                                + "                alert('" + rLang.getString("sContrasenaIgualNo") + "');\n"
                                + "                document.getElementById('paswordid').className = 'required2';\n"
                                + "                document.getElementById('pasword2id').className = 'required2';\n"
                                + "            }\n"
                                + "        } else {\n"
                                + "            alert('" + rLang.getString("sCorreoNoValido") + "');\n"
                                + "            document.getElementById('emailid').className = 'required2';\n"
                                + "        }\n"
                                + "    }\n"
                                + "    return valido;\n"
                                + "}</script>\n"
                                + "    </head>\n"
                                + "    <body   >\n"
                                + "        <div id='all'>\n"
                                + "        <div class='head-index'>\n"
                                + menu
                                + "        </div>\n"
                                + "        <div id='main-home' class='contenido-index'>\n"
                                + "        <center>\n"
                                + "        <h1>" + rLang.getString("sRegistrate") + "</h1>\n"
                                + "        <form action='RegistroUsuario2' method='post'>\n"
                                + "\n"
                                + "            <table>\n"
                                + "                <tr><td>" + rLang.getString("sNombre") + ":</td><td><input class='required' type='text' maxlength='15' name='nombre' placeholder='" + rLang.getString("sNombre") + "' id='nombreid' ></td></tr>\n"
                                + "                <tr><td>" + rLang.getString("sApellido") + ":</td><td><input class='required' type='text' name='apellido' placeholder='" + rLang.getString("sApellido") + "' id='apellidoid' ></td></tr>\n"
                                + "                <tr><td>" + rLang.getString("sUsuario") + ":</td><td><input class='required' type='text' name='usuario' placeholder='" + rLang.getString("sUsuario") + "' id='usuarioid' ></td></tr>\n"
                                + "                <tr><td>" + rLang.getString("sCorreo") + ":</td><td><input class='required' type='text' name='email' placeholder='" + rLang.getString("sCorreo") + "' id='emailid' ></td></tr>\n"
                                + "                <tr><td>" + rLang.getString("sContrasena") + ":</td><td><input class='required' type='password' name='pasword' placeholder='" + rLang.getString("sContrasena") + "' id='paswordid' ></td></tr>\n"
                                + "                <tr><td>" + rLang.getString("sConfirmarContrasena") + ":</td><td><input class='required' type='password' name='pasword2' placeholder='" + rLang.getString("sConfirmarContrasena") + "' id='pasword2id' ></td></tr>\n"
                                + "                <tr><td>" + rLang.getString("sTelefono") + ":</td><td><input class='required' type='text' name='telefono' placeholder='" + rLang.getString("sTelefono") + "' id='telefonoid'  maxlength='15'></td></tr>\n"
                                + "                <tr><td>" + rLang.getString("sFechaNacimiento") + ":</td><td><input class='required' type='date' name='fecha'  id='fechaid'></td></tr>\n"
                                + "                <tr><td>" + rLang.getString("sSexo") + ":</td><td><table class='nopadding'><tr><td><input type='radio' name='sex' id='sex1id' value='M' checked></td><td>" + rLang.getString("sMasculino") + "</td></tr><tr><td><input type='radio' name='sex' id='sex2id' value='F'></td><td>" + rLang.getString("sFemenino") + "</td></tr></table></td></tr>\n"
                                + "                <tr><td></td><td><input class='enviar'  type='submit' value='" + rLang.getString("sEnviar") + "' id='registroUsuario'></td></tr>\n"
                                + "            </table>\n"
                                + "        </form></center><br><br>\n"
                                + "        </div>\n"
                                + "        <input type='hidden' id='refreshed' value='no'>\n"
                                + "        \n"
                                + popupLogin
                                + "        </div>\n"
                                + "    </body>\n"
                                + "</html>\n"
                                + "\n"
                                + "\n"
                                + "";
                        out.print(html);

                    } else {

                        session.setAttribute("usr", (String) session.getAttribute("usr"));
                        session.setAttribute("url", (String) session.getAttribute("url"));
                        session.setAttribute("leng", leng);
                        response.sendRedirect((String) session.getAttribute("url"));
                    }
                }
            } catch (SQLException ex) {
                Logger.getLogger(RegistroUsuario1.class.getSimpleName()).log(Level.SEVERE, null, ex);
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

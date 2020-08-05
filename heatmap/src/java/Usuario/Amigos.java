/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Usuario;

import Mapa.MapaGeneral;
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
 * @author luigi
 */
@WebServlet(name = "Amigos", urlPatterns = {"/Amigos"})
public class Amigos extends HttpServlet {

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
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */

            HttpSession session = request.getSession();
            int i=0;
            if (!(session.getAttribute("usr") == null)) {
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
                String menuMap = vista.menuMap(usr, leng,((String) request.getRequestURI()));
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
                                + "        <title>"+rLang.getString("sAmigos")+"</title>\n"
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
                                ;
                        
                        
                        int idUsr=0;
                
               
                    
                    ResultSet rz = sql.consulta("select f_idUsuario('"+usr+"')");
              while (rz.next()){
                idUsr=Integer.parseInt(rz.getString(1));
            }

                    ResultSet r = sql.consulta("select nombre,apellido from usuario"
                            + " where idusuario=" + idUsr + ";");
                    
                    ResultSet rs = sql.consulta("call sp_Amigos(0,"+idUsr+",0)");

                    
                  
                    

                
             
            
        
                        
                        
                        
                                html
                                += "    <body>\n"
                                + "        <div id='all'>\n"
                                + "        <div class='head-index'>\n" + menu
                                + "        </div>\n"
                                + "        <div id='mapa' class='contenido-index'>\n"
                                   
                                        
                                        
                                        
                                        
                                ;
                
                out.print(html);
                while (r.next()) {
                        out.print("<script src='JS/FunctionsJS.js'></script>");
                        
                        
                        while (rs.next()){ 
                        ResultSet x = sql.consulta("call sp_Quecon("+idUsr+","+rs.getString("idUsuario2")+")");
                        String mes="";
                        if (x.next()){
                            mes=x.getString("idConversacion");
                        }
                        out.println("<form id='oc"+i+"' action='UserNameServlet' method=post> <a  onclick=document.getElementById('oc"+i+"').submit();>"+rs.getString("nombre")+" "+rs.getString("apellido")+"</a><input name=idConversacion type='text' hidden value="+mes+"><input name=amigo type='text' hidden value="+rs.getString("usuario")+"><input name=ami type='text' hidden value='"+rs.getString("nombre")+" "+rs.getString("apellido")+"'> </form>");
                        out.println("<img src='"+rs.getString("imagen")+"' style='width:50px; height:50px; position:relative; right:5px;' >");
                        
                        i++;
                        }
                        out.print("<input type='hidden' id='refreshed' value='no'>");
                        

                    }
                out.print( "        </div> <div class='foot-index'> <center >           <h4>HeatMap© by Quanthink CopyRigth 2015.</h4></center></div><input type='hidden' id='refreshed' value='no'>" + popupLogin+"        </div></body></html>");
                                 
                                
                
                }
            }
            
            
             catch ( SQLException | NumberFormatException exceptions) {
                Logger.getLogger(MapaGeneral.class.getName()).log(Level.SEVERE, null, exceptions);
                     out.println("Stack Trace:<br/>");
                    exceptions.printStackTrace(out);
                    out.println("<br/><br/>Stack Trace (for web display):</br>");
                    out.println(displayErrorForWeb(exceptions));
            }
            }           
            else {
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
            Logger.getLogger(Amigos.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(Amigos.class.getName()).log(Level.SEVERE, null, ex);
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

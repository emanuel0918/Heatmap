/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Chat;

import BD.cDatos;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.SQLException;
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
@WebServlet(name = "Chat", urlPatterns = {"/Chat"})
public class Chat extends HttpServlet {

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
              HttpSession session = request.getSession(true);
        String idconversacion=request.getParameter("idConversacion");
        String idUser = (String) session.getAttribute("usr").toString();
        int idUsr=0;
        
       // String idUss=request.getParameter("idami");
       
        String Usr=idUser;
        String emisor="emisor";
        String receptor="receptor";
        String mensaje="mensaje";
        
        
        BD.cDatos con= new cDatos();
            
            try{
                con.conectar();
                con.consulta("call sp_NuevoMensaje('"+emisor+"','"+receptor+"','"+mensaje+"')");
            }
            catch (SQLException | NumberFormatException exceptions) {
                    out.println("Stack Trace:<br/>");
                    exceptions.printStackTrace(out);
                    out.println("<br/><br/>Stack Trace (for web display):</br>");
                    out.println(displayErrorForWeb(exceptions));

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

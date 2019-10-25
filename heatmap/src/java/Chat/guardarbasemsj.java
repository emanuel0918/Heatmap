/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Chat;

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
import javax.ws.rs.core.Response;

/**
 *
 * @author Alumno
 */
@WebServlet(name = "guardarbasemsj", urlPatterns = {"/guardarbasemsj"})
public class guardarbasemsj extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
//    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException, SQLException {
//        response.setContentType("text/html;charset=UTF-8");
//        String opc=request.getParameter("opc");
//        HttpSession session = request.getSession(true);
//         String usr = (String) session.getAttribute("usr");
//        String receptor=request.getParameter("receptor");
//        String msj=request.getParameter("msj");
//        System.out.println("MENSAJE: "+msj);
//        System.out.println("quechowwasdlfkjasdklfjasdklfjasdklfjasdkljfklasdfjasdkljfghghdfjvnjfrngj0000000000000000000000");
//        ;
//        
//        try (PrintWriter out = response.getWriter()) {
//           BD.cDatos con=new BD.cDatos();
//           con.conectar();
//           switch(opc){
//               case "1":
//                   con.consulta("call sp_NuevoMensaje('"+usr+"','"+receptor+"','"+msj+"')");
//                   break;
//           }
//           
//           con.cierraConexion();
//        }
//    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        try {
//            processRequest(request, response);
//        } catch (SQLException ex) {
//            Logger.getLogger(guardarbasemsj.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }

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
        response.setContentType("text/html;charset=UTF-8");
        String opc=request.getParameter("opc");
        HttpSession session = request.getSession(true);
         String usr = (String) session.getAttribute("usr");
        String receptor=request.getParameter("receptor");
        String msj=request.getParameter("msj");
        System.out.println("Emisor"+ usr);
        System.out.println("MSJ:"+msj);
        
        
        try (PrintWriter out = response.getWriter()) {
           BD.cDatos con=new BD.cDatos();
            con.conectar();
           switch(opc){
               case "1":
                   
                   con.consulta("call sp_NuevoMensaje('"+usr+"','"+receptor+"','"+msj+"');");
                   
                   break;
           }
           
           con.cierraConexion();
        } catch (SQLException ex) {
            System.out.println("Receptor: "+receptor);
            Logger.getLogger(guardarbasemsj.class.getName()).log(Level.SEVERE, null, ex);
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

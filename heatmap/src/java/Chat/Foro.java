/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Chat;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.logging.Level;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
import Usuario.HeatMap;

/**
 *
 * @author Elena
 */
@WebServlet(name = "Foro", urlPatterns = {"/Foro"})
public class Foro extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        String username = request.getParameter("username");
        PrintWriter Pw = response.getWriter();
        if (username != null) {
            generateUsernamePage(username, request, response);
        } else {
            generateUsernamePage(request, response);
        }

    }

    private void generateUsernamePage(String username, HttpServletRequest request, HttpServletResponse response) throws UnknownHostException, IOException {
        HttpSession session = request.getSession(true);
        String roomName = request.getParameter("roomSelect");

        session.setAttribute("username", username);
        try (PrintWriter out = response.getWriter()) {

            String usr = (String) session.getAttribute("usr");
            String leng = (String) request.getParameter("leng");
            if (leng == null) {
                leng = (String) session.getAttribute("leng");
            }
            BD.cDatos sql = new BD.cDatos();
            String html = "";
            try {
                Usuario.VistaUsuario vista = new Usuario.VistaUsuario();
                String menu = vista.menu(usr, leng, ((String) request.getRequestURI()));
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
                            + "        <title>HeatMap</title>\n"
                            + "        <meta charset='UTF-8'>\n"
                            + "        <meta name='viewport' content='width=device-width, initial-scale=1.0'>\n"
                            + "        <link rel='icon' type='image/png' href='Imagenes/hsx32.png' />\n"
                            + "        <link rel='stylesheet' href='http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css'>\n"
                            + "        <link rel='stylesheet' href='http://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.4.0/css/font-awesome.min.css'>\n"
                            + "        <link href='vista/general.css' rel='stylesheet' type='text/css'/>\n"
                            + "        <script src='https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js'></script>\n"
                            + "        <script src='http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js'></script>\n"
                            + "        <script src='JS/General.js'></script>\n"
                            + "        <script src='JS/Home.js'></script>\n"
                            + "    </head>\n"
                            + "    <body onload='indexHeatmap();' >\n"
                            + "        <div id='all'>\n"
                            + "        <div class='head-index'>\n"
                            + menu
                            + "        </div>\n"
                            + "        <div id='main-home' class='contenido-index'>\n"
                            + "<mark>Nickname: " + username + "|Sala de chat: " + roomName + "</mark><br>\n"
                            + "<textarea id=\"messagesTextArea\" readonly rows='10' cols='45'></textarea><br>\n"
                            + "<input type='text' id='messageText' name='messageText' size='50'>\n"
                            + "<input type='button' value='send' onclick='sendMessage();'>\n"
                            + "<script>\n"
                            + "var websocket=new WebSocket('ws://" + InetAddress.getLocalHost().getHostAddress() + ":8080/heatmap/echo2/" + roomName + "');"
                            + "websocket.onopen=function(event){if(event.data === undefined){return;}}\n"
                            + "websocket.onmessage=function processMessage(message){\n"
                            + "var jsonData=JSON.parse(message.data);\n"
                            + "if(jsonData.message!=null){messagesTextArea.value+=jsonData.message+\"\\n\";}}\n"
                            + "function sendMessage(){\n"
                            + "websocket.send(messageText.value);\n"
                            + "messageText.value=\"\";}\n"
                            + "</script>    </div>\n"
                            + "        <div class='foot-index'>\n"
                            + "            <center >           \n"
                            + "                <h4>HeatMap© by Quanthink CopyRigth 2015.</h4>\n"
                            + "            </center>\n"
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

                }
                out.print(html);
            } catch (SQLException ex) {
                Logger.getLogger(HeatMap.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

                                                                                               //         out.println("var websocket=new WebSocket('ws://" + InetAddress.getLocalHost().getHostAddress() + ":8080/heatmap/echo2/" + roomName + "');");
    /**
     * **  <mark>User:hola|Room:sala 2 Actualmente hay 0 operadores en
     * línea</mark><br>
     * <textarea id="messagesTextArea" readonly rows='10' cols='45'></textarea><br>
     * <input type='text' id='messageText' name='messageText' size='50'>
     * <input type='button' value='send' onclick='sendMessage();'>
     * <script>
     * var websocket=new WebSocket('ws://192.168.0.2:8080/heatmap/echo2/sala 2
     * Actualmente hay 0 operadores en línea');
     * websocket.onopen=function(event){if(event.data === undefined){return;}}
     * websocket.onmessage=function processMessage(message){ var
     * jsonData=JSON.parse(message.data);
     * if(jsonData.message!=null){messagesTextArea.value+=jsonData.message+"\n";}}
     * function sendMessage(){ websocket.send(messageText.value);
     * messageText.value="";}
     * </script>
    }**
     */
    private void generateUsernamePage(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            HttpSession session = request.getSession();
            String usr = (String) session.getAttribute("usr");
            String leng = (String) request.getParameter("leng");
            if (leng == null) {
                leng = (String) session.getAttribute("leng");
            }
            BD.cDatos sql = new BD.cDatos();
            String html = "";
            try {
                Usuario.VistaUsuario vista = new Usuario.VistaUsuario();
                String menu = vista.menu(usr, leng, ((String) request.getRequestURI()));
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
                            + "        <title>HeatMap</title>\n"
                            + "        <meta charset='UTF-8'>\n"
                            + "        <meta name='viewport' content='width=device-width, initial-scale=1.0'>\n"
                            + "        <link rel='icon' type='image/png' href='Imagenes/hsx32.png' />\n"
                            + "        <link rel='stylesheet' href='http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css'>\n"
                            + "        <link rel='stylesheet' href='http://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.4.0/css/font-awesome.min.css'>\n"
                            + "        <link href='vista/general.css' rel='stylesheet' type='text/css'/>\n"
                            + "        <script src='https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js'></script>\n"
                            + "        <script src='http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js'></script>\n"
                            + "        <script src='JS/General.js'></script>\n"
                            + "        <script src='JS/Home.js'></script>\n"
                            + "    </head>\n"
                            + "    <body onload='indexHeatmap();' >\n"
                            + "        <div id='all'>\n"
                            + "        <div class='head-index'>\n"
                            + menu
                            + "        </div>\n"
                            + "        <div id='main-home' class='contenido-index'>\n"
                            + "   <form name=\"submitForm\">\n"
                            + "            <p>Seleccione una sala</p>\n"
                            + "            <select id=\"roomSelect\" name=\"roomSelect\" onchange=\"handleNewRoom()\">\n"
                            + "                <option>Sala 1 Actualmente hay 2 operadores en línea  </option> \n"
                            + "                <option>Sala 2 Actualmente hay 0 operadores en línea </option>  \n"
                            + "                <option>Sala 3 Actualmente hay 0 operadores en línea</option>\n"
                            + "                <option>Sala 4 Actualmente hay 0 operadores en línea</option>\n"
                            + "                <option>Sala 5 Actualmente hay 0 operadores en línea</option>\n"
                            + "                            </select>\n"
                            + "            <br>\n"
                            + "            <div id=\"newRoomDivId\" style=\"display: none;\">\n"
                            + "                <mark>Please enter a room name:</mark><br>\n"
                            + "                <input type=\"text\" name=\"newRoomName\" size=\"20\">\n"
                            + "            </div>\n"
                            + "            <mark>Proporcione un nickname</mark>\n"
                            + "            <input type=\"text\" name=\"username\" size=\"20\">\n"
                            + "            <input type=\"submit\" value=\"Enter\">\n"
                            + "        </form>\n"
                            + "        <script>\n"
                            + "            function handleNewRoom(){\n"
                            + "                var roomSelect =document.getElementById(\"roomSelect\");\n"
                            + "                var roomSelectOption=roomSelect.options[roomSelect.selectedIndex].value;\n"
                            + "                if(roomSelectOption===\"newRoomOption\"){\n"
                            + "                    document.getElementById(\"newRoomDivId\").style.display=\"block\";\n"
                            + "                }\n"
                            + "                else{\n"
                            + "                    document.getElementById(\"newRoomDivId\").style.display=\"none\";\n"
                            + "                }\n"
                            + "            }\n"
                            + "            \n"
                            + "            window.onload=function(){\n"
                            + "                document.submitForm.action=submitAction();\n"
                            + "            };\n"
                            + "            function submitAction(){\n"
                            + "                return document.location.pathname;\n"
                            + "            }\n"
                            + "            \n"
                            + "        </script>     </div>\n"
                            + "        <div class='foot-index'>\n"
                            + "            <center >           \n"
                            + "                <h4>HeatMap© by Quanthink CopyRigth 2015.</h4>\n"
                            + "            </center>\n"
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

                }
                out.print(html);
            } catch (SQLException ex) {
                Logger.getLogger(HeatMap.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}

/**
 * ****
 * out.write("\n"); out.write("\n"); out.write("\n");
 * out.write("<!DOCTYPE html>\n"); out.write("<html>\n"); out.write("
 * <head>\n"); out.write("
 * <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
 * out.write("        <title>JSP Page</title>\n"); out.write("    </head>\n"); out.write("
 * <body>\n"); out.write("        <form name=\"submitForm\">\n"); out.write("
 * <p>
 * Seleccione una sala</p>\n"); out.write("
 * <select id=\"roomSelect\" name=\"roomSelect\" onchange=\"handleNewRoom()\">\n");
 * out.write("                <option>sala 1 Actualmente hay 2 operadores en línea  </option>
 * \n"); out.write("                <option>sala 2 Actualmente hay 0 operadores en línea
 * </option> \n"); out.write("                <option>sala 3 Actualmente hay 0 operadores en
 * línea</option>\n"); out.write("                <option>sala 4 Actualmente hay 0 operadores
 * en línea</option>\n"); out.write("                <option>sala 5 Actualmente hay 0
 * operadores en línea</option>\n"); out.write(" "); out.write("            </select>\n");
 * out.write("            <br>\n"); out.write("
 * <div id=\"newRoomDivId\" style=\"display: none;\">\n"); out.write("
 *                <mark>Please enter a room name:</mark><br>\n"); out.write("
 * <input type=\"text\" name=\"newRoomName\" size=\"20\">\n"); out.write("
 *            </div>\n"); out.write("            <mark>Proporcione un nickname</mark>\n");
 * out.write("            <input type=\"text\" name=\"username\" size=\"20\">\n");
 * out.write("            <input type=\"submit\" value=\"Enter\">\n"); out.write("
 * </form>\n"); out.write("        <script>\n"); out.write(" function
 * handleNewRoom(){\n"); out.write(" var roomSelect
 * =document.getElementById(\"roomSelect\");\n"); out.write(" var
 * roomSelectOption=roomSelect.options[roomSelect.selectedIndex].value;\n");
 * out.write(" if(roomSelectOption===\"newRoomOption\"){\n"); out.write("
 * document.getElementById(\"newRoomDivId\").style.display=\"block\";\n");
 * out.write(" }\n"); out.write(" else{\n"); out.write("
 * document.getElementById(\"newRoomDivId\").style.display=\"none\";\n");
 * out.write(" }\n"); out.write(" }\n"); out.write(" \n"); out.write("
 * window.onload=function(){\n"); out.write("
 * document.submitForm.action=submitAction();\n"); out.write(" };\n");
 * out.write(" function submitAction(){\n"); out.write(" return
 * document.location.pathname;\n"); out.write(" }\n"); out.write(" \n");
 * out.write("        </script>\n"); out.write("    </body>\n"); out.write("</html>\n"); }
 *
 */

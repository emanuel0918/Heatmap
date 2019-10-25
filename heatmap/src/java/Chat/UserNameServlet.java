/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Chat;

import BD.cDatos;
import Usuario.HeatMap;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.InetAddress;
import java.net.UnknownHostException;
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
 * @author Silvester
 */
@WebServlet("/UserNameServlet")
public class UserNameServlet extends HttpServlet {

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
            
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter oc= response.getWriter();
        
        
        generateUsernamePage(oc , request );
    }

    private void generateUsernamePage(PrintWriter out, HttpServletRequest request) throws UnknownHostException {
        HttpSession session = request.getSession(true);
        String idconversacion=request.getParameter("idConversacion");
        String idUser = (String) session.getAttribute("usr").toString();
        int idUsr=0;
        
       // String idUss=request.getParameter("idami");
       
        String Usr=idUser;
        String nomu="";
        String nome="";
        String nomm="";
        String amigo =request.getParameter("amigo");
        String ami =request.getParameter("ami");
        String usr = (String) session.getAttribute("usr");
            String leng = (String) request.getParameter("leng");
            if (leng == null) {
                leng = (String) session.getAttribute("leng");
            }
            BD.cDatos con = new BD.cDatos();
            String html = "";
            try {
                Usuario.VistaUsuario vista = new Usuario.VistaUsuario();
                String menu = vista.menu(usr, leng, ((String) request.getRequestURI()));
                String popupLogin = vista.getPopupLogin(usr, leng);
                con.conectar();
                ResultSet rLang = con.consulta("select * from lenguaje where leng='" + leng + "';");
                if ((rLang.next())) {
                    rLang = con.consulta("select * from lenguaje where leng='" + leng + "';");
                } else {
                    rLang = con.consulta("select leng from lenguaje ;");
                    if (rLang.next()) {
                        rLang = con.consulta("select * from lenguaje where leng='" + rLang.getString("leng") + "';");
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
                            + "        <head><title>"+rLang.getString("sConvCon")+ami+"</title></head>\n"
                            + "        <meta charset='UTF-8'>\n"
                            + "        <meta name='viewport' content='width=device-width, initial-scale=1.0'>\n"
                            + "        <link rel='icon' type='image/png' href='Imagenes/hsx32.png' />\n"
                            + "        <link rel='stylesheet' href='http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css'>\n"
                            + "        <link rel='stylesheet' href='http://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.4.0/css/font-awesome.min.css'>\n"
                            + "        <link href='vista/general.css' rel='stylesheet' type='text/css'/>\n"
                            + "        <script src=\"https://ajax.googleapis.com/ajax/libs/jquery/2.2.0/jquery.min.js\"></script>\n"
                            + "        <script src='http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js'></script>\n"
                            + "        <script src='JS/General.js'></script>\n"
                            + "        <script src='JS/Home.js'></script>\n"
                            + "        <script src='JS/Jquery.js'></script>\n"
                            + "    </head>\n"
                            + "    <body onload='indexHeatmap();' >\n"
                            + "        <div id='all'>\n"
                            + "        <div class='head-index'>\n"
                            + menu
                            + "        </div>\n"
                            + "       <div id='main-home' class='contenido-index'><center> <textarea id=\"messagesTextArea\" readonly rows='10' cols='45'></textarea><br> <input type='text' id='messageText' name='messageText' onclick='oncli();' size='50'>\n"
                            
                            + " <input type='button' value='Enviar' onclick='sendMessage();'>     <input type='text'id=bot value=0 onclick='onclick();' hidden > </center> </div>\n"
                            + "        <div class='foot-index'>\n"
                            + "            <center>           \n"
                            + "                <h4>HeatMap© by Quanthink CopyRigth 2015.</h4>\n"
                            + "            </center>\n"
                            + "        </div>\n";
                            
                } out.print(html); 
                
        out.println("<script>");
                    out.println("function onclick(){");
                    out.println("if(document.getElementById(\"bot\").value===1);");
                    out.println("window.location.reload(true);}");
                    out.println("</script>");
        out.println("<script>");
        out.println("var mensaje=Document.getElementById(\"messageText\");");
        out.println("var emisor="+Usr+";");
        out.println("var receptor="+amigo+";");
        
        out.println("</script>");
        
        out.println("<script type=\"text/javascript\">\n" +
"  $(document).ready(function() {\n" +
"    $('.contenido-index').keydown(function(event) {\n" +
"        if (event.keyCode == 13) {\n" +
"            sendMessage();\n" +
"            return false;\n" +
"         }\n" +
"    });\n" +
"  });\n" +
"</script>");
        out.println("<script>");
        
        out.println("var websocket=new WebSocket('ws://"+InetAddress.getLocalHost().getHostAddress()+":8080/heatmap/echo2/"+idconversacion+"');");
        out.println("websocket.onopen=function(event){if(event.data === undefined){return;}};");
        out.println("websocket.onmessage=function processMessage(message){");
        out.println("var jsonData=JSON.parse(message.data);");
        out.println("if(jsonData.message!=null){messagesTextArea.value+=jsonData.message+\"\\n\";}};");
        out.println("function sendMessage(){");
        out.println("var emisor=\""+Usr+"\";");
        //out.println("alert(emisor);");
        out.println("$.post( \"guardarbasemsj\", { msj:messageText.value ,usr:\" "+usr+"\",receptor:\""+amigo+"\", opc:\"1\"} , function(status){});");
        out.println("websocket.send(messageText.value);");
        out.println("messageText.value=\"\";};");
        out.println("</script>");
                
            ResultSet rz = con.consulta("select f_idUsuario('"+idUser+"')");
              while (rz.next()){
                idUsr=Integer.parseInt(rz.getString(1));
            }
              int idAm=0;
            ResultSet raza = con.consulta("select f_idUsuario ('"+amigo+"')");
                while (raza.next()){
                idAm=Integer.parseInt(raza.getString(1));
                }
                
            int par1=0;
            int par2=0;
            
            ResultSet men = con.consulta("call sp_Chat('"+idUsr+"','"+idAm+"')");
            
            while (men.next()){
                par1=men.getInt("sum");
                par2=men.getInt("multi");
            }
            
            
            
             
            ResultSet rs = con.consulta("select usuario, nombre, apellido from usuario where idUsuario="+idUsr);
            if (rs.next()){
                Usr=rs.getString("usuario");
                nome=rs.getString("nombre");
                nomm=rs.getString("apellido");
                nomu=(nome+" "+nomm);
            }
            ResultSet r = con.consulta("call sp_ConsultaMensajes('"+par1+"','"+par2+"')");
            while (r.next()){
                if(r.getString("a").equals("2")){
                    if(r.getString("receptor").equals(Usr))
                        out.println("<script> document.getElementById('messagesTextArea').value+='"+nomu+" : "+r.getString("mensaje")+"    "+r.getString("fecha_hora")+"'+\"\\n\";</script> ");
                    else{
                       out.println("<script> document.getElementById('messagesTextArea').value+='"+ami+" : "+r.getString("mensaje")+"    "+r.getString("fecha_hora")+"'+\"\\n\";</script>");

                    }
                }
                
                
                else{
                    out.println(r.getString("msj"));
                    out.println("<script>");
                    out.println("function oncli(){");
                    out.println("document.getElementById(\"bot\").value=1;");
                    out.println("}");
                    out.println("</script>");
                }
            }
        
       session.setAttribute("username",nomu);
      
        out.print( "        </div> <div class='foot-index'> <center >           <h4>HeatMap© by Quanthink CopyRigth 2015.</h4></center></div><input type='hidden' id='refreshed' value='no'>" + popupLogin+"        </div></body></html>");

        
            }
        
        catch (SQLException | NumberFormatException exceptions) {
            
                    Logger.getLogger(HeatMap.class.getName()).log(Level.SEVERE, null, exceptions);
                    out.println("Stack Trace:<br/>");
                    exceptions.printStackTrace(out);
                    out.println("<br/><br/>Stack Trace (for web display):</br>");
                    out.println(displayErrorForWeb(exceptions));

        }
        
        
    }

    private void generateUsernamePage(PrintWriter out) {
        out.write("\n");
        out.write("\n");
        out.write("\n");
        out.write("<!DOCTYPE html>\n");
        out.write("<html>\n");
        out.write("    <head>\n");
        out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
        out.write("        <title>JSP Page</title>\n");
        out.write("    </head>\n");
        out.write("    <body>\n");
        out.write("        <form name=\"submitForm\">\n");
        out.write("            <mark>Select a room</mark>\n");
        out.write("            <select id=\"roomSelect\" name=\"roomSelect\" onchange=\"handleNewRoom()\">\n");
        out.write("                <option>room1</option>\n");
        out.write("                <option>room2</option>\n");
        out.write("                <option>room3</option>\n");
        out.write("                <option>room4</option>\n");
        out.write("                <option>room5</option>\n");
        out.write("                <option>newRoomOption</option>\n");
        out.write("            </select>\n");
        out.write("            <br>\n");
        out.write("            <div id=\"newRoomDivId\" style=\"display: none;\">\n");
        out.write("                <mark>Please enter a room name:</mark><br>\n");
        out.write("                <input type=\"text\" name=\"newRoomName\" size=\"20\">\n");
        out.write("            </div>\n");
        out.write("            <mark>Please supply a username</mark>\n");
        out.write("            <input type=\"text\" name=\"username\" size=\"20\">\n");
        out.write("            <input type=\"submit\" value=\"Enter\">\n");
        out.write("        </form>\n");
        out.write("        <script>\n");
        out.write("            function handleNewRoom(){\n");
        out.write("                var roomSelect =document.getElementById(\"roomSelect\");\n");
        out.write("                var roomSelectOption=roomSelect.options[roomSelect.selectedIndex].value;\n");
        out.write("                if(roomSelectOption===\"newRoomOption\"){\n");
        out.write("                    document.getElementById(\"newRoomDivId\").style.display=\"block\";\n");
        out.write("                }\n");
        out.write("                else{\n");
        out.write("                    document.getElementById(\"newRoomDivId\").style.display=\"none\";\n");
        out.write("                }\n");
        out.write("            }\n");
        out.write("            \n");
        out.write("            window.onload=function(){\n");
        out.write("                document.submitForm.action=submitAction();\n");
        out.write("            };\n");
        out.write("            function submitAction(){\n");
        out.write("                return document.location.pathname;\n");
        out.write("            }\n");
        out.write("            \n");
        out.write("        </script>\n");
        out.write("    </body>\n");
        out.write("</html>\n");
    }
    
    public String displayErrorForWeb(Throwable t) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        t.printStackTrace(pw);
        String stackTrace = sw.toString();
        return stackTrace.replace(System.getProperty("line.separator"), "<br/>\n");
    }
    
    

}

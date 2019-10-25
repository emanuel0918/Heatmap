package Chat;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.SQLException;
import BD.cDatos;
import Usuario.HeatMap;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.annotation.WebServlet;
@WebServlet(name = "Buscadorr", urlPatterns = {"/Buscador"})
public final class Buscadorr extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {


        public String displayErrorForWeb(Throwable t) {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            t.printStackTrace(pw);
            String stackTrace = sw.toString();
            
            return stackTrace.replace(System.getProperty("line.separator"), "<br/>\n");
        }


    
  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      response.setHeader("X-Powered-By", "JSP/2.3");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");
      
      
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
                            + "        <title>" + rLang.getString("sBuscador") + "</title>\n"
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
                            + "        <div id='main-home' class='contenido-index'>\n";
                            
                    
                    
                    
                    
                    
                    
                    
                    
                }
                out.print(html);
                
                
                
                
                out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <script>\n");
      out.write("            function holamundo() {\n");
      out.write("\n");
      out.write("                if (event.keyCode === 13 && document.1.query.value !== null){\n");
      out.write("                document.getElementById('1').submit();\n");
      out.write("                }\n");
      out.write("            }\n");
      out.write("\n");
      out.write("\n");
      out.write("            function oc() {\n");
      out.write("                if (document.1.query.value !== null)\n");
      out.write("                        document.getElementById('1').submit();\n");
      out.write("\n");
      out.write("\n");
      out.write("            }\n");
      out.write("        </script> \n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <title>JSP Page</title>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("\n");
      out.write("    <body>\n");
      out.write("       <center> <form  name='1' id='1' action='Buscador' method='post'>\n");
      ResultSet rLan = sql.consulta("select * from lenguaje where leng='" + leng + "';");
      while (rLan.next()){
      out.write("            <input type='text' name='query' value='' onfocus='holamundo()' style='width:1000px; text-align:center;'/><br>\n");
      out.write("            <input type='submit' VALUE='"+rLan.getString("sBuscador")+"'></center>\n");
      }
      out.write("        </form>\n");
      out.write("    </body>\n");
      out.write("\n");
      out.write("    ");
      out.write("\n");
      out.write("    ");

        String query = request.getParameter("query");
        String url = request.getRequestURL().toString();
        String form;
        String fori;
        HttpSession sesion = request.getSession();
        String idd =  sesion.getAttribute("usr").toString();
        int idUser=0;
        String idUs="";
        int i=0;
        int bandera=0;
        BD.cDatos con = new cDatos();
        try {
            con.conectar();
            ResultSet rs = con.consulta("call sp_buscador('" + query + "')");
            
            ResultSet rz = con.consulta("select f_idUsuario('"+idd+"')");
            
            while (rz.next()){
                idUs=rz.getString(1);
                idUser=Integer.parseInt(rz.getString(1));
            }
            
            out.println("<center>");
            ResultSet r= con.consulta("call sp_Amigos(0,"+idUser+",0)");
            while (rs.next()) {
                i++;
                
                if (rs.getString("msj").equals("1")) {
                    String idi=rs.getString("idUsuario");
                    
                    while(r.next()){
                        
                        if(idi.equals(r.getString("idUsuario2"))){
                            bandera=1;
                            
                        }
                        else{
                            bandera=0;
                        }
                        if (bandera==1)
                            break;
                        
                        if (idi.equals(idUs)){
                            bandera=2;
                        }
                       
                    }
                    r.beforeFirst();
                    if (bandera==0){
                    
                    String nom = (rs.getString("nombre") + " " + rs.getString("apellido"));
                    
                    out.println("<a href=''>" + nom + "</a>");
                    out.println("<img src='"+rs.getString("imagen")+"' style='width:50px; height:50px; position:relative; right:5px;' >");
                    out.println(rs.getString("sexo"));
                    
                    form=("ola"+i);
                    fori=("document."+form+".submit();");
                    
    
    
     
      out.write("    <form name=");
      out.print(form);
      out.write(" action=\"Agregar\"><input type=\"text\" name=\"idu\" hidden value=");
      out.print(idi);
      out.write(" ><BR> <input type=\"submit\" value=\"AGREGAR A CONTACTOS\" style='position:relative; left:177px; top:-57px;'></form>\n");
      out.write("                    \n");
      out.write("                    \n");
      out.write("                    \n");
      out.write("                    \n");
      out.write("                    ");

                    out.println("<br>");
                    
                } 
                
                    if (bandera==1){
                
                    idi=rs.getString("idUsuario");
                    
                    String nom = (rs.getString("nombre") + " " + rs.getString("apellido"));
                    out.println("<a href=''>" + nom + "</a>");
                    out.println("<img src='"+rs.getString("imagen")+"' style='width:50px; height:50px; position:relative; left:5px;' >");
                    out.println(rs.getString("sexo"));
                    out.println("Ya son amigos");
                    form=("ola"+i);
                    fori=("document."+form+".submit();");
                    out.println("<br>");
                    out.println("<br>");
                     out.println("<br>");
                    out.println("<br>");
                    }  
                    if (bandera==2){
                
                    idi=rs.getString("idUsuario");
                    
                    String nom = (rs.getString("nombre") + " " + rs.getString("apellido"));
                    out.println("<a href=''>" + nom + "</a>");
                    out.println("<img src='"+rs.getString("imagen")+"' style='width:50px; height:50px; position:relative; left:5px;' >");
                    out.println(rs.getString("sexo"));
                    out.println(" Tú ");
                    form=("ola"+i);
                    fori=("document."+form+".submit();");
                    out.println("<br>");
                    out.println("<br>");
                     out.println("<br>");
                    out.println("<br>");
                    } 
                    
                }
                
                    else {
                    out.println("<script>alert('" + rs.getString("msj") + "'); </script>");
                }
                
            } out.println("</center>");

        } catch (SQLException exceptions) {
            out.println("Stack Trace:<br/>");
            exceptions.printStackTrace();
            out.println("<br/><br/>Stack Trace (for web display):</br>");
            out.println(displayErrorForWeb(exceptions));

        }  

        
                        out.print( "        </div> <div class='foot-index'> <center >           <h4>HeatMap© by Quanthink CopyRigth 2015.</h4></center></div><input type='hidden' id='refreshed' value='no'>" + popupLogin+"        </div></body></html>");


    
      
            } catch (SQLException ex) {
                Logger.getLogger(HeatMap.class.getName()).log(Level.SEVERE, null, ex);
            }
      
      
      
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}

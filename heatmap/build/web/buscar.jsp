<%@page import="java.io.PrintWriter"%>
<%@page import="java.io.StringWriter"%>
<%@page import="java.sql.SQLException"%>
<%@page import="BD.cDatos"%>
<%@page import="java.sql.ResultSet"%>

<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%-- 
    Document   : buscar
    Created on : 04-dic-2015, 21:05:14
    Author     : Elena
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <script>
            function holamundo() {

                if (event.keyCode === 13 && document.1.query.value !== null){
                document.getElementById('1').submit();
                }
            }


            function oc() {
                if (document.1.query.value !== null)
                        document.getElementById('1').submit();


            }
        </script> 
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>

    <body>
        <form  name='1' id='1' action='buscar.jsp' method='post'>
            <input type='text' name='query' value='' onfocus='holamundo()' />
            <input type='submit' VALUE='BUSCAR'>
        </form>
    </body>

    <%!
        public String displayErrorForWeb(Throwable t) {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            t.printStackTrace(pw);
            String stackTrace = sw.toString();
            
            return stackTrace.replace(System.getProperty("line.separator"), "<br/>\n");
        }


    %>
    <%
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
                    out.println(rs.getString("imagen"));
                    out.println(rs.getString("sexo"));
                    out.println("Agregar a contactos");
                    form=("ola"+i);
                    fori=("document."+form+".submit();");
                    
    
    %> 
                    
    <form name=<%=form%> action="Agregar"><input type="text" name="idu" hidden value=<%=idi%> > <input type="submit" value="AGREGAR A CONTACTOS"></form>
                    
                    
                    
                    
                    <%
                    out.println("<br>");
                    
                } 
                
                    if (bandera==1){
                
                    idi=rs.getString("idUsuario");
                    
                    String nom = (rs.getString("nombre") + " " + rs.getString("apellido"));
                    out.println("<a href=''>" + nom + "</a>");
                    out.println(rs.getString("imagen"));
                    out.println(rs.getString("sexo"));
                    out.println("Ya son amigos");
                    form=("ola"+i);
                    fori=("document."+form+".submit();");
                    out.println("<br>");
                    out.println("<br>");
                    }  
                    if (bandera==2){
                
                    idi=rs.getString("idUsuario");
                    
                    String nom = (rs.getString("nombre") + " " + rs.getString("apellido"));
                    out.println("<a href=''>" + nom + "</a>");
                    out.println(rs.getString("imagen"));
                    out.println(rs.getString("sexo"));
                    out.println("Éste eres tú rey ggggggg");
                    form=("ola"+i);
                    fori=("document."+form+".submit();");
                    out.println("<br>");
                    out.println("<br>");
                    } 
                    
                }
                
                    else {
                    out.println("<script>alert('" + rs.getString("msj") + "'); </script>");
                }
                
            }

        } catch (SQLException exceptions) {
            out.println("Stack Trace:<br/>");
            exceptions.printStackTrace();
            out.println("<br/><br/>Stack Trace (for web display):</br>");
            out.println(displayErrorForWeb(exceptions));

        }


    %>     
</body>
</html>

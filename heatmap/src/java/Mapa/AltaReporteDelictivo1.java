/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mapa;

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
 * @author Elena
 */
@WebServlet(name = "AltaReporteDelictivo1", urlPatterns = {"/AltaReporteDelictivo1"})
public class AltaReporteDelictivo1 extends HttpServlet {

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
            Usuario.Validaciones validaciones = new Usuario.Validaciones();
            String coox = (String) (request.getParameter("cx"));
            String cooy = request.getParameter("cy");
            String usr = (String) session.getAttribute("usr");
            String url = ((String) session.getAttribute("url"));
            session.setAttribute("usr", usr);
            String leng = (String) request.getParameter("leng");
            if (leng == null) {
                leng = (String) session.getAttribute("leng");
            }
            BD.cDatos sql = new BD.cDatos();
            String html = "";
            try {
                Usuario.VistaUsuario vista = new Usuario.VistaUsuario();
                String menu = vista.menu(usr, leng, ((String) request.getRequestURI()));
                String menuMap = vista.menuMap(usr, leng, "/heatmap/MapaGeneral");
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
                if (rLang.next()) {
                    session.setAttribute("leng", rLang.getString("leng"));
                    session.setAttribute("url", ((String) request.getRequestURI()));
                    ResultSet r = sql.consulta("select * from usuario where usuario='" + usr + "';");
                    if (r.next()) {
                        html = "<!DOCTYPE html>\n"
                                + "<!--\n"
                                + "To change this license header, choose License Headers in Project Properties.\n"
                                + "To change this template file, choose Tools | Templates\n"
                                + "and open the template in the editor.\n"
                                + "-->\n"
                                + "<html>\n"
                                + "    <head>\n"
                                + "        <title>" + rLang.getString("sAltaReporteDelictivo") + "</title>\n"
                                + "        <meta charset='UTF-8'>\n"
                                + "        <meta name='viewport' content='width=device-width, initial-scale=1.0'>\n"
                                + "        <link rel='icon' type='image/png' href='http://i.imgur.com/mQI4Lqp.png' />\n"
                                + "        <link rel='stylesheet' href='http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css'>\n"
                                + "        <link rel='stylesheet' href='http://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.4.0/css/font-awesome.min.css'>\n"
                                + "        <link href='vista/general.css' rel='stylesheet' type='text/css'/>\n"
                                + "        <link href='vista/registro.css' rel='stylesheet' type='text/css'/>\n"
                                + "        <script src='https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js'></script>\n"
                                + "        <script src='http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js'></script>\n"
                                + "        <script src='http://maps.googleapis.com/maps/api/js'></script>\n"
                                + "        <script src='JS/General.js'></script>\n"
                                + "        <script src='JS/Registro.js'></script>\n"
                                + "        <script>\n"
                                + "window.onload=function(){\n"
                                + "\n"
                                + "            document.getElementById('comboid').onchange=function(){\n"
                                + "                document.getElementById('tipoid').value=\n"
                                + "                        document.getElementById('comboid').value;\n"
                                + "            };"
                                + "  document.getElementById('registroReporteid').onclick=function(){\n"
                                + "        return reporteValido();  \n"
                                + "  };\n"
                                + "};\n"
                                + "function cargaPaginaMp() {\n"
                                + "    window.open(\n"
                                + "            'https://mpvirtual.pgjdf.gob.mx/'\n"
                                + "            + 'CiberDenuncia/FormatoActaEspecial.aspx?F=2', '_blank');\n"
                                + "}\n"
                                + "\n"
                                + "function reporteValido() {\n"
                                + "    esValido = false;\n"
                                + "    document.getElementById('tituloid').className = 'required';\n"
                                + "    document.getElementById('descid').className = 'text-area-1';\n"
                                + "    document.getElementById('mapa').className = 'map-style';\n"
                                + "    document.getElementById('comboid').className = 'combo-style-1';\n"
                                + "    if (document.getElementById('tituloid').value === '' ||\n"
                                + "            document.getElementById('descid').value === '' ||\n"
                                + "            document.getElementById('comboid').selectedIndex === 0"
                                + " || (!(decimalValido(document.getElementById('cx').value) "
                                + " && decimalValido(document.getElementById('cy').value))) "
                                + ") {\n"
                                + "        alert('" + rLang.getString("sCamposObligatorios") + "');\n"
                                + "        if (document.getElementById('tituloid').value === '') {\n"
                                + "            document.getElementById('tituloid').className = 'required2';\n"
                                + "        }\n"
                                + "        if (document.getElementById('descid').value === '')\n"
                                + "\n"
                                + "        {\n"
                                + "            document.getElementById('descid').className = 'text-area-2';\n"
                                + "\n"
                                + "        }\n"
                                + "        if ((document.getElementById('comboid').selectedIndex === 0)) {\n"
                                + "            document.getElementById('comboid').className = 'combo-style-2';\n"
                                + "        }\n"
                                + "        if (!(decimalValido(document.getElementById('cx').value) "
                                + "&& decimalValido(document.getElementById('cy').value))) {\n"
                                + "            document.getElementById('mapa').className = 'map-style-2';\n"
                                + "        }\n"
                                + "    } else {\n"
                                + "        esValido = true;\n"
                                + "    }\n"
                                + "    return esValido;\n"
                                + "}";
                        if ((validaciones.decimalValido(coox) && validaciones.decimalValido(cooy))) {
                            html += "\n"
                                    + "                var clickPosition = {\n"
                                    + "                    lat: " + coox + ",\n"
                                    + "                    lng: " + cooy + "\n"
                                    + "                };";
                        }
                        html += "            var map;\n\n";
                        if ((validaciones.decimalValido(coox) && validaciones.decimalValido(cooy))) {
                            html += "            var coordenadax = '" + coox + "';\n"
                                    + "            var coordenaday = '" + cooy + "';\n";
                        } else {
                            html += "            var coordenadax = '19.432388';\n"
                                    + "            var coordenaday = '-99.133244';\n";

                        }
                        html += "            var mapdivmenu = " + menuMap + ";\n"
                                + "                var clickPos = {\n"
                                + "                    lat: 19.432388,\n"
                                + "                    lng: -99.133244\n"
                                + "                };"
                                + "            var click = new google.maps.Marker({\n"
                                + "                icon: 'http://i.imgur.com/vcQ3no3.png'\n"
                                + "            });\n"
                                + "            function initMap() {\n"
                                + "                map = new google.maps.Map(document.getElementById('mapa'), {\n"
                                + "                    center: {lat: 19.432388, lng: -99.133244},\n"
                                + "                    zoom: 14,\n"
                                + "                    mapTypeId: google.maps.MapTypeId.ROADMAP\n"
                                + "\n"
                                + "                });\n"
                                + "                click.setMap(map);\n"
                                + "                     click.setPosition(clickPos);"
                                + "                google.maps.event.addListener(click, 'rightclick', function (event) {\n"
                                + "                    showContextMenu(event.latLng, mapdivmenu);\n\n"
                                + "                    coordenadax = event.latLng.lat();\n"
                                + "                    coordenaday = event.latLng.lng();"
                                + "                });\n"
                                + "\n"
                                + "\n"
                                + "                // Try HTML5 geolocation.\n"
                                + "                if (navigator.geolocation) {\n"
                                + "                    navigator.geolocation.getCurrentPosition(function (position) {\n"
                                + "                        var pos = {\n"
                                + "                            lat: position.coords.latitude,\n"
                                + "                            lng: position.coords.longitude\n"
                                + "                        };\n"
                                + "\n";
                        if (!(validaciones.decimalValido(coox) && validaciones.decimalValido(cooy))) {
                            html += "                        map.setCenter(pos);\n";
                        }
                        html += "                        var gps = new google.maps.Marker({\n"
                                + "                            icon: 'http://i.imgur.com/q6fRcjs.png'\n"
                                + "                        });\n"
                                + "\n"
                                + "                        gps.setMap(map);\n"
                                + "                        gps.setPosition(pos);\n"
                                + "                        google.maps.event.addListener(gps, 'rightclick', function (event) {\n"
                                + "                            showContextMenu(event.latLng, mapdivmenu);\n"
                                + "                    coordenadax = event.latLng.lat();\n"
                                + "                    coordenaday = event.latLng.lng();\n"
                                + "                        });\n\n" + "\n"
                                + "\n"
                                + "                    }, function () {\n"
                                + "                    });\n"
                                + "                }"
                                + "                google.maps.event.addListener(click, 'rightclick', function (event) {\n"
                                + "                    showContextMenu(event.latLng, mapdivmenu);\n"
                                + "                    coordenadax = event.latLng.lat();\n"
                                + "                    coordenaday = event.latLng.lng();\n"
                                + "                });"
                                + "                        google.maps.event.addListener(map, 'click', function (event) {\n"
                                + "                            click.setPosition(event.latLng);\n"
                                + "                            $('.contextmenu').remove();\n"
                                + "                    coordenadax = event.latLng.lat();\n"
                                + "                    coordenaday = event.latLng.lng();\n"
                                + "document.getElementById('cx').value=event.latLng.lat();"
                                + "document.getElementById('cy').value=event.latLng.lng();"
                                + "\n"
                                + "                        });\n";
                        if ((validaciones.decimalValido(coox) && validaciones.decimalValido(cooy))) {
                            html += " map.setCenter(clickPosition);"
                                    + "click.setPosition(clickPosition);\n";
                        }
                        html += "\n"
                                + "            }\n"
                                + "\n"
                                + "            function handleLocationError(browserHasGeolocation, infoWindow, pos) {\n"
                                + "                infoWindow.setPosition(pos);\n"
                                + "                infoWindow.setContent(browserHasGeolocation ?\n"
                                + "                        'Error: The Geolocation service failed.' :\n"
                                + "                        'Error: Your browser doesn\\'t support geolocation.');\n"
                                + "            }\n"
                                + "\n"
                                + "\n"
                                + "            function showContextMenu(currentPosition, contxt) {\n"
                                + "                var projection;\n"
                                + "                var contextmenuDir;\n"
                                + "                projection = map.getProjection();\n"
                                + "                $('.contextmenu').remove();\n"
                                + "                contextmenuDir = document.createElement('div');\n"
                                + "                contextmenuDir.className = 'contextmenu';\n"
                                + "                contextmenuDir.id = 'div-map';\n"
                                + "                contextmenuDir.innerHTML =contxt;\n"
                                + "                $(map.getDiv()).append(contextmenuDir);\n"
                                + "                var mapWidth = $('#mapa').width();\n"
                                + "                var mapHeight = $('#mapa').height();\n"
                                + "                var menuWidth = $('.contextmenu').width();\n"
                                + "                var menuHeight = $('.contextmenu').height();\n"
                                + "                var scale = Math.pow(2, map.getZoom());\n"
                                + "                var nw = new google.maps.LatLng(\n"
                                + "                        map.getBounds().getNorthEast().lat(),\n"
                                + "                        map.getBounds().getSouthWest().lng()\n"
                                + "                        );\n"
                                + "                var worldCoordinateNW = map.getProjection().fromLatLngToPoint(nw);\n"
                                + "                var worldCoordinate = map.getProjection().fromLatLngToPoint(currentPosition);\n"
                                + "                var currentPositionOffset = new google.maps.Point(\n"
                                + "                        Math.floor((worldCoordinate.x - worldCoordinateNW.x) * scale),\n"
                                + "                        Math.floor((worldCoordinate.y - worldCoordinateNW.y) * scale)\n"
                                + "                        );\n"
                                + "                var clickedPosition = currentPositionOffset;\n"
                                + "                var x = clickedPosition.x;\n"
                                + "                var y = clickedPosition.y;\n"
                                + "\n"
                                + "                if ((mapWidth - x) < menuWidth)\n"
                                + "                    x = x - menuWidth;\n"
                                + "                if ((mapHeight - y) < menuHeight)\n"
                                + "                    y = y - menuHeight;\n"
                                + "                $('.contextmenu').css('left', x);\n"
                                + "                $('.contextmenu').css('top', y);\n"
                                + "                contextmenuDir.style.visibility = 'visible';\n"
                                + "            }\n"
                                + "            google.maps.event.addDomListener(window, 'load', initMap);\n"
                                + "        </script>\n"
                                + "    </head>\n"
                                + "    <body>\n"
                                + "        <div id='all'>\n"
                                + "        <div class='head-index'>\n" + menu
                                + "        </div>\n"
                                + "        <div  class='contenido-index'>\n"
                                + "        <center><h1>" + rLang.getString("sAltaReporteDelictivo") + "</h1><div>\n"
                                + "            <form action='AltaReporteDelictivo2' method='post' ><br>\n";
                        if ((validaciones.decimalValido(coox) && validaciones.decimalValido(cooy))) {
                            html += "                <input type='text' hidden id='cx' name='coorx' value='" + coox + "'>\n"
                                    + "                <input type='text' hidden id='cy' name='coory' value='" + cooy + "'>\n";
                        } else {
                            html += "                <input type='text' hidden id='cx' name='coorx' value=''>\n"
                                    + "                <input type='text' hidden id='cy' name='coory' value=''>\n";

                        }
                        html += "                <br>\n"
                                + "                <table width='50%' >\n"
                                + "                    <tr><td>" + rLang.getString("sReporteMp") + ":</td><td><img src='http://i.imgur.com/0vB9QGU.jpg' onclick='cargaPaginaMp();' class='mp-imagen' alt='Ministerio Publico'></td></tr>\n"
                                + "                    <tr><td>" + rLang.getString("sNombre") + ":</td><td><input class='required' type='text' name='titulo' placeholder='Titulo del reporte' id='tituloid'></td></tr>\n"
                                + "                    <tr height='200'><td>" + rLang.getString("sLugar") + ":</td><td id='mapa' class='map-style'></td></tr>\n"
                                + "                    <tr><td>" + rLang.getString("sDescripcion") + ":</td><td><textarea cols='30' rows='15' placeholder='Descripcion' name='desc' class='text-area-1' id='descid'></textarea></td></tr>\n"
                                + "                    <tr><td>" + rLang.getString("sTipoDelito") + ":</td><td><select class='combo-style-1' id='comboid' >\n"
                                + "                                <option value='0'>" + rLang.getString("sSeleccionarCombobox") + "</option>       \n"
                                + "                                <option value='" + rLang.getString("sTipo1") + "'>" + rLang.getString("sTipo1") + "</option>\n"
                                + "                                <option value='" + rLang.getString("sTipo2") + "'>" + rLang.getString("sTipo2") + "</option>\n"
                                + "                                <option value='" + rLang.getString("sTipo3") + "'>" + rLang.getString("sTipo3") + "</option>\n"
                                + "                                <option value='" + rLang.getString("sTipo4") + "'>" + rLang.getString("sTipo4") + "</option>"
                                + "                            </select></td></tr>\n"
                                + "                    <tr><td></td><td><input class='enviar' type='submit' value='Enviar' id='registroReporteid'></td></tr>\n"
                                + "                </table></center><br><br><input type='text' hidden name='tipo' id='tipoid'>\n"
                                + "            </form></div>\n"
                                + "            </center>\n"
                                + "        </div>\n"
                                + "        <input type='hidden' id='refreshed' value='no'>\n" + popupLogin
                                + "        </div>\n"
                                + "    </body>\n"
                                + "</html>\n"
                                + "\n"
                                + "\n"
                                + "";
                    } else {

                        session.setAttribute("leng", rLang.getString("leng"));
                        session.setAttribute("url", url);
                        response.sendRedirect("HeatMap");
                    }
                }
                out.print(html);
            } catch (SQLException ex) {
                Logger.getLogger(AltaReporteDelictivo1.class.getSimpleName()).log(Level.SEVERE, null, ex);
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

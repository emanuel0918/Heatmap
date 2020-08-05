/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Usuario;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Alumno
 */
public class VistaUsuario {

    //Usuario
    public String getPopupLogin(String usr, String leng) {
        String loginHtml = "";
        BD.cDatos sql = new BD.cDatos();
        try {
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
                ResultSet r = sql.consulta("select * from usuario where usuario='" + usr + "';");
                if (!(r.next())) {
                    loginHtml = "<div class='modal fade' id='login' role='dialog'>\n"
                            + "                <div class='modal-dialog' id='login-dialog'>\n"
                            + "                    <div class='modal-content' id='login-contenido'>\n"
                            + "                        <form action='Login' method='post' id='formLogin'>\n"
                            + "                            <div class='modal-body' id='login-body'>\n"
                            + "                                <div id='login-body-head'>                                    \n"
                            + "                                <button type='button' class='close' data-dismiss='modal'>&times;</button>\n"
                            + "                                " + rLang.getString("sIniciarSesion") + "\n"
                            + "                                </div>\n"
                            + "                                <div id='login-body-content'>\n"
                            + "                                    <div class='login-body-content-cell-left'>" + rLang.getString("sUsuario") + ":</div>\n"
                            + "                                    <div class='login-body-content-cell-right'>\n"
                            + "                                        <input type='text' id='login-input' placeholder='" + rLang.getString("sPlaceHolderUsuario") + "' name='usr' required>\n"
                            + "                                    </div>\n"
                            + "                                    <div class='login-body-content-cell-left'>" + rLang.getString("sContrasena") + ":</div>\n"
                            + "                                    <div class='login-body-content-cell-right'>\n"
                            + "                                        <input type='password' id='login-input' placeholder='" + rLang.getString("sContrasena") + "' name='pasw' required>\n"
                            + "                                    </div>\n"
                            + "                                    <div class='login-body-content-cell-left'></div>\n"
                            + "                                    <div class='login-body-content-cell-right'>\n"
                            + "                                        <input type='submit' value='" + rLang.getString("sEntrar") + "' id='login-input' class='btn btn-default'>\n"
                            + "                                    </div>\n"
                            + "                                </div>\n"
                            + "                                <div id='login-body-footer'>\n"
                            + "                                    <br>\n"
                            + "                                    <a href='OlvidarContrasena'>" + rLang.getString("sOlvidarContrasena2") + "</a>\n"
                            + "                                        <br>\n"
                            + "                                        " + rLang.getString("sNoTienesUnaCuentaEnHeatmap") + " <a\n"
                            + "                                            href='RegistroUsuario1'>" + rLang.getString("sRegistrateAqui") + "</a>\n"
                            + "                                </div>\n"
                            + "                            </div>\n"
                            + "                        </form>\n"
                            + "                    </div>\n"
                            + "                </div>\n"
                            + "            </div>";
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(HeatMap.class.getSimpleName()).log(Level.SEVERE, null, ex);
        }
        return loginHtml;
    }

    public String menu(String usr, String leng, String url) {
        String htmlMenu = "";
        String nombreUsuario, imagenUsuario;
        BD.cDatos sql = new BD.cDatos();
        try {
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
                ResultSet r = sql.consulta("select * from usuario where usuario='" + usr + "';");
                if (r.next()) {
                    r = sql.consulta("select * from usuario where usuario='" + usr + "';");
                    while (r.next()) {
                        nombreUsuario = (String) r.getString("nombre");
                        imagenUsuario = (String) r.getString("imagen");
                        //<editor-fold desc="Submenu en Menu"> 
                        int index = 0;
                        r = sql.consulta("call sp_Menu('" + usr + "','nvar');");
                        while (r.next()) {
                            index = index + 1;
                        }
                        float posiciones[] = new float[index];
                        int tipos[] = new int[index];
                        r = sql.consulta("call sp_Menu('" + usr + "','nvar');");
                        index = 0;
                        while (r.next()) {
                            posiciones[index] = (float) Float.parseFloat((String) r.getString("posicion"));
                            index++;
                        }
                        if (posiciones.length > 0) {
                            for (int i = 0; i < posiciones.length; i++) {
                                if (i == 0) {
                                    if (i == ((posiciones.length) - 1)) {
                                        tipos[0] = 0;
                                    } else {
                                        if (((int) posiciones[i])
                                                == ((int) posiciones[i + 1])) {
                                            tipos[i] = 1;
                                        } else {
                                            tipos[i] = 0;
                                        }
                                    }
                                } else if (i == ((posiciones.length) - 1)) {
                                    if (((int) posiciones[i])
                                            == ((int) posiciones[i - 1])) {
                                        tipos[i] = 3;
                                    } else {
                                        tipos[i] = 0;
                                    }
                                } else {
                                    if ((((int) posiciones[i])
                                            == ((int) posiciones[i - 1]))
                                            && (((int) posiciones[i])
                                            == ((int) posiciones[i + 1]))) {
                                        tipos[i] = 2;
                                    } else if (((int) posiciones[i])
                                            == ((int) posiciones[i + 1])) {
                                        tipos[i] = 1;
                                    } else if (((int) posiciones[i])
                                            == ((int) posiciones[i - 1])) {
                                        tipos[i] = 3;
                                    } else {
                                        tipos[i] = 0;
                                    }
                                }
                            }

                        }
                        //</editor-fold>
                        r = sql.consulta("call sp_Menu('" + usr + "','nvar');");
                        htmlMenu = "<nav class='navbar navbar-default'>\n"
                                + "                <div class='container central' >\n"
                                + "                    <div class='navbar-header'>\n"
                                + "                        <button type='button' class='navbar-toggle' id='hamburguer-menu' data-toggle='collapse' data-target='#menu'>\n"
                                + "                            <span class='icon-bar' id='hamburguer-bar'></span>\n"
                                + "                            <span class='icon-bar' id='hamburguer-bar'></span>\n"
                                + "                            <span class='icon-bar' id='hamburguer-bar'></span>\n"
                                + "                        </button>\n"
                                + "                        <a class='navbar-brand Inicio' href='HeatMap'>Heatmap</a>\n"
                                + "                    </div>\n"
                                + "                    <div class='collapse navbar-collapse' id='menu'>\n"
                                + "                        <ul class='nav navbar-nav navbar-right'>\n"
                                + "                            <li class='dropdown' id='li-scaled-img'>\n"
                                + "                            <button class='btn btn-sucess dropdown-toggle' \n"
                                + "                                    type='button' data-toggle='dropdown' id='button-scaled-img'>\n"
                                + "                                <img alt='Usuario' src='" + imagenUsuario + "'\n"
                                + "                                     class='img-circular'/>" + nombreUsuario + "\n"
                                + "                                <span class='caret'></span>                                                                \n"
                                + "                            </button>\n"
                                + "                            <ul class='dropdown-menu'>\n"
                                + "                                <li><a href='/heatmap/Profile'><span class='opv fa fa-child'></span>&nbsp;&nbsp;Mi Perfil</a></li>\n"
                                + "                                <li><a  href='/heatmap/Config'><span class='opv fa fa-cog'></span>&nbsp;&nbsp;Configuracion</a></li>\n"
                                + "                                <li><a  class='style1' href='/heatmap/Logout'><span class='glyphicon glyphicon-log-out'></span>&nbsp;&nbsp;Cerrar Sesión</a></li>\n"
                                + "                            </ul>\n"
                                + "                            </li>";
                        index = 0;
                        String span = "";
                        while (r.next()) {
                            if (!(r.getString("icono").equals(""))) {
                                span = "<span class='" + r.getString("icono") + "'></span>&nbsp; &nbsp;";
                            }
                            switch (tipos[index]) {
                                case 0:
                                    htmlMenu += "<li ><a href='"
                                            + r.getString("ruta")
                                            + "'>"
                                            + span
                                            + rLang.getString(r.getString("titulo"))
                                            + "</a></li>";
                                    break;
                                case 1:
                                    htmlMenu += "<li class='dropdown'>\n"
                                            + "                            <button class='btn btn-sucess dropdown-toggle' \n"
                                            + "                                    type='button' data-toggle='dropdown'>\n"
                                            + "                                " + span + rLang.getString(r.getString("titulo")) + "\n"
                                            + "                                <span class='caret'></span>                                                                \n"
                                            + "                            </button>\n"
                                            + "                            <ul class='dropdown-menu'>"
                                            + "\n";
                                    break;
                                case 2:
                                    htmlMenu += "<li><a href='"
                                            + r.getString("ruta")
                                            + "'>" + span
                                            + rLang.getString(r.getString("titulo"))
                                            + "</a></li>";
                                    break;
                                case 3:
                                    htmlMenu += "<li><a href='"
                                            + r.getString("ruta")
                                            + "'>" + span
                                            + rLang.getString(r.getString("titulo"))
                                            + "</a></li></ul></li>";
                                    break;
                            }
                            span = "";
                            index++;
                        }

                        htmlMenu += idiomasList(leng, url) + "                        </ul>\n"
                                + "                    </div>\n"
                                + "                </div>\n"
                                + "            </nav>";
                    }
                } else {
                    htmlMenu = "<nav class='navbar navbar-default'>\n"
                            + "                <div class='container central' >\n"
                            + "                    <div class='navbar-header'>\n"
                            + "                        <button type='button' class='navbar-toggle' id='hamburguer-menu' data-toggle='collapse' data-target='#menu'>\n"
                            + "                            <span class='icon-bar' id='hamburguer-bar'></span>\n"
                            + "                            <span class='icon-bar' id='hamburguer-bar'></span>\n"
                            + "                            <span class='icon-bar' id='hamburguer-bar'></span>\n"
                            + "                        </button>\n"
                            + "                        <a class='navbar-brand Inicio' href='HeatMap'>Heatmap</a>\n"
                            + "                    </div>\n"
                            + "                    <div class='collapse navbar-collapse' id='menu'>\n"
                            + "                        <ul class='nav navbar-nav navbar-right'>\n"
                            + "                            <li >\n"
                            + "                            <button class='btn btn-sucess dropdown-toggle' \n"
                            + "                                    type='button' data-toggle='modal' \n"
                            + "                                    data-target='#login'>\n"
                            + "                                " + rLang.getString("sIniciarSesion") + "                                                              \n"
                            + "                            </button>\n"
                            + "                            </li>\n"
                            + "                            <li class='dropdown'>\n"
                            + "                            <button class='btn btn-sucess dropdown-toggle' \n"
                            + "                                    type='button' data-toggle='dropdown'>\n"
                            + "                                " + rLang.getString("sMapa") + "\n"
                            + "                                <span class='caret'></span>                                                                \n"
                            + "                            </button>\n"
                            + "                            <ul class='dropdown-menu'>\n"
                            + "                                <li><a  class='style1' href='" + Mapa.MapaGeneral.class.getSimpleName() + "'>" + rLang.getString("sMapaGeneral") + "</a></li>\n"
                            + "                                <li><a  class='style1' href='" + Mapa.MapaDelictivo.class.getSimpleName() + "'>" + rLang.getString("sMapaDelictivo") + "</a></li>\n"
                            + "                                <li><a  class='style1' href='" + Mapa.Rutas.class.getSimpleName() + "'>" + rLang.getString("sRutas") + "</a></li>\n"
                            + "                            </ul>\n"
                            + "                            </li>\n"
                            + idiomasList(leng, url) + "                        </ul>\n"
                            + "                    </div>\n"
                            + "                </div>\n"
                            + "            </nav>";
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(HeatMap.class.getSimpleName()).log(Level.SEVERE, null, ex);
        }
        return htmlMenu;
    }

    public String menuMap(String usr, String leng, String url) {
        String htmlMenu = "";
        BD.cDatos sql = new BD.cDatos();
        try {

            try {
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
                    ResultSet r = sql.consulta("select * from usuario where usuario='" + usr + "';");
                    if (r.next()) {
                        htmlMenu = "'\\n'";
                        r = sql.consulta("call sp_menu('" + usr + "','mapa');");
                        while (r.next()) {
                            htmlMenu += "\n"
                                    + "                    + '<label class=\"link\" onclick=\"location.replace(\\'" + r.getString("ruta") + "?cx=\\'+ coordenadax+ \\'&cy=\\'+coordenaday);\">'\n"
                                    + "                    + '" + rLang.getString(r.getString("titulo")) + "</label><br><br> \\n'";
                        }
                        htmlMenu += "\n"
                                + "                    + '<label class=\"link\" onclick=\"prompt'\n"
                                + "                    + '(\\'Compartir Lugar\\',\\'" + InetAddress.getLocalHost().getHostAddress() + ":8080/heatmap/MapaGeneral?'\n"
                                + "                    + 'cx=\\'+coordenadax+\\'&cy=\\'+coordenaday+\\'\\');\">'\n"
                                + "                    + '"+rLang.getString("sCompartirLugar")+"</label><br>\\n'";
                    } else {
                        htmlMenu = "'<a id=\"div-link\">'+\n"
                                + "'<div class=\"context-menu-mapa\" id=\"div-context\">'+\n";
                        htmlMenu += "'<label class=\"link\" onclick=\"prompt'\n"
                                + "                    + '(\\'" + rLang.getString("sCompartirLugar") + "\\',\\'" + InetAddress.getLocalHost().getHostAddress() + ":8080" + url + "?'\n"
                                + "                    + 'cx=\\'+coordenadax+\\'&cy=\\'+coordenaday+\\'\\');\">'\n"
                                + "                    + '" + rLang.getString("sCompartirLugar") + "</label> \\n";

                        htmlMenu += "</div></a>'";
                    }
                }
            } catch (SQLException ex) {
                Logger.getLogger(HeatMap.class.getSimpleName()).log(Level.SEVERE, null, ex);
            }
        } catch (UnknownHostException ex) {
            Logger.getLogger(VistaUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return htmlMenu;
    }

    public String idiomasList(String leng, String pagina) {
        String htmlLeng = "";
        BD.cDatos sql = new BD.cDatos();
        try {
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
                ResultSet lengs = sql.consulta("select leng,sLenguaje from lenguaje order by sLenguaje;");
                htmlLeng = "\n<li class='dropdown'>\n"
                        + "                            <button class='btn btn-sucess dropdown-toggle' \n"
                        + "                                    type='button' data-toggle='dropdown'>\n"
                        + "                                " + rLang.getString("sCambiarLenguaje") + "\n"
                        + "                                <span class='caret'></span>                                                                \n"
                        + "                            </button>\n"
                        + "                            <ul class='dropdown-menu'>\n";
                while (lengs.next()) {
                    htmlLeng += "<li><a  class='style1'"
                            + " href='" + pagina + "?leng="
                            + lengs.getString("leng") + "'>" + lengs.getString("sLenguaje")
                            + "</a></li>\n";
                }
                htmlLeng += "                            </ul>\n"
                        + "                            </li>\n";
            }
        } catch (SQLException ex) {
            Logger.getLogger(HeatMap.class.getSimpleName()).log(Level.SEVERE, null, ex);
        }

        return htmlLeng;
    }

    public String alertify(String title, String body, String button) {
        String alert = "<div class='modal fade' id='alertify' role='dialog'>\n"
                + "    <div class='modal-dialog' id='alertify-diaog'>\n"
                + "      <div class='modal-content' id='alertify-contenido'>\n"
                + "        <div class='modal-header'>\n"
                + "          <button type='button' class='close' data-dismiss='modal'>&times;</button>\n"
                + "          <h4 class='modal-title'>" + title + "</h4>\n"
                + "        </div>\n"
                + "        <div class='modal-body'>\n"
                + "          <p>" + body + "</p>\n"
                + "        </div>\n"
                + "        <div class='modal-footer'>\n"
                + "          <button type='button' class='btn btn-default' data-dismiss='modal'>" + button + "</button>\n"
                + "        </div>\n"
                + "      </div>      \n"
                + "    </div>\n"
                + "  </div>";

        return alert;
    }

    //Mapa delictivo
    public String zonasDeRiesgo(String cx, String cy, String estadisticas) {
        int datos_grafica = 10;
        String docHTML = "";
        float escalaColor;
        int cuadrosy = 38, cuadrosx = 16;
        double tamaniocuadro = 0.01;
        Usuario.Validaciones validaciones = new Usuario.Validaciones();
        if (!(validaciones.decimalValido(cx)
                && validaciones.decimalValido(cy))) {
            cx = "19.482388";
            cy = "-99.133244";
        }
        double coordenadaInicioX = (Double.parseDouble(cx) + 0.021);
        double coordenadaInicioY = (Double.parseDouble(cy) - 0.058);
        boolean coloreo;
        String[] arregloColores = {"#80ff00", "#bfff00",
            "#ffff00", "#ffbf00", "#ff8000", "#ff4000", "#ff0000"};
        int cuadrostotal = (cuadrosx * cuadrosy) + 5;
        int dias[][] = new int[2][datos_grafica];
        int reportes = 0;
        int index;
        int dia;
        BD.cDatos sql = new BD.cDatos();
        try {
            docHTML += "            var long='" + tamaniocuadro + "';var lat=long*1.1;\n";
            sql.conectar();
            ResultSet r;
            r = sql.consulta("select count(*) as 'size' from vw_ReportesDelictivos;");
            while (r.next()) {
                reportes = Integer.parseInt(r.getString("size"));
            }
            for (int y = 0; y < cuadrosy; y++) {
                for (int x = 0; x < cuadrosx; x++) {
                    for (int i = 0; i < datos_grafica; i++) {
                        dias[0][i] = 0;
                        r = sql.consulta("select day(date_sub(curdate(),interval " + i + " day)) as 'day';");
                        while (r.next()) {
                            dias[1][i] = Integer.parseInt(r.getString("day"));
                        }
                    }
                    coloreo = false;
                    escalaColor = 0;
                    index = 0;
                    r = sql.consulta("select * from vw_ReportesDelictivos;");
                    while (r.next()) {
                        index++;
                        dia = Integer.parseInt(r.getString("dia"));
                        if ((Double.parseDouble(r.getString("coordenadax")) <= (coordenadaInicioX
                                + ((x * tamaniocuadro) * (-1)))) && (Double.parseDouble(r.getString("coordenadax")) > (coordenadaInicioX
                                + (((x + 1) * tamaniocuadro) * (-1)))) && (Double.parseDouble(r.getString("coordenaday")) >= (coordenadaInicioY
                                + (y * tamaniocuadro * 1.1))) && (Double.parseDouble(r.getString("coordenaday")) < (coordenadaInicioY
                                + ((y + 1) * tamaniocuadro * 1.1)))) {
                            for (int n = 0; n < datos_grafica; n++) {
                                if (dia == dias[1][n]) {
                                    dias[0][n] = (dias[0][n]) + 1;
                                }
                            }
                            escalaColor += 1.0f;
                            coloreo = true;
                        }

                        if (escalaColor >= 7) {
                            escalaColor = 6;
                        }
                        if (coloreo && index == (reportes)) {
                            docHTML += "\nx" + cuadrostotal + " =(" + (coordenadaInicioX
                                    + ((x * tamaniocuadro) * (-1))) + ");\n"
                                    + "y" + cuadrostotal + " = " + (coordenadaInicioY
                                    + (y * tamaniocuadro * 1.1)) + ";\n"
                                    + "var myTrip" + cuadrostotal + "=[new google.maps.LatLng("
                                    + "x" + cuadrostotal + ",y" + cuadrostotal + "),\n"
                                    + "new google.maps.LatLng(x" + cuadrostotal + ","
                                    + "y" + cuadrostotal + "+lat),\n"
                                    + "new google.maps.LatLng(x" + cuadrostotal + "-long,"
                                    + "y" + cuadrostotal + "+lat),\n"
                                    + "new google.maps.LatLng(x" + cuadrostotal + "-long,"
                                    + "y" + cuadrostotal + "),];\n"
                                    + "var flightPath" + cuadrostotal + "="
                                    + "new google.maps.Polygon({\n"
                                    + "  path:myTrip" + cuadrostotal + ",\n"
                                    + "  strokeWeight:0,\n"
                                    + "  fillColor:'"
                                    + arregloColores[(int) (escalaColor)] + "',\n"
                                    + "  fillOpacity:0.4\n"
                                    + "  });\n"
                                    + "flightPath" + cuadrostotal + ".setMap(map);";
                            docHTML += "google.maps.event.addListener(flightPath" + cuadrostotal + ", 'click', function (event) {\n"
                                    + "                    click.setPosition(event.latLng);\n"
                                    + "                    $('.contextmenu').remove();\n"
                                    + "                    coordenadax = event.latLng.lat();\n"
                                    + "                    coordenaday = event.latLng.lng();\n"
                                    + "\n"
                                    + "                });\n"
                                    + "                google.maps.event.addListener(flightPath" + cuadrostotal + ", 'rightclick', function (event) {\n"
                                    + "                    showContextMenu(event.latLng, \"<div style='width:200px; height: 120px;'><h7>" + estadisticas + "</h7><br><canvas style='width:100%; height: 100%;' id='canvas" + cuadrostotal + "' ></canvas></div>\");\n"
                                    + "                    flightChart" + cuadrostotal + "();\n"
                                    + "                });\n"
                                    + "\n"
                                    + "                var lineChartData" + cuadrostotal + " = {\n"
                                    + "                    labels: [";
                            for (int i = (datos_grafica - 1); i >= 0; i--) {
                                if (!(i == 0)) {
                                    docHTML += "'" + dias[1][i] + "',";
                                } else {
                                    docHTML += "'" + dias[1][i] + "'";
                                }
                            }
                            docHTML += "],\n"
                                    + "                    datasets: [\n"
                                    + "                        {\n"
                                    + "                            label: 'My First dataset',\n"
                                    + "                            fillColor: 'rgba(76,175,80,1)',\n"
                                    + "                            strokeColor: 'rgba(76,175,80,1)',\n"
                                    + "                            pointColor: 'rgba(76,175,80,1)',\n"
                                    + "                            pointStrokeColor: '#fff',\n"
                                    + "                            pointHighlightFill: '#fff',\n"
                                    + "                            pointHighlightStroke: 'rgba(76,175,80,1)',\n"
                                    + "                            data: [";
                            for (int i = (datos_grafica - 1); i >= 0; i--) {
                                if (!(i == 0)) {
                                    docHTML += dias[0][i] + ",";
                                } else {
                                    docHTML += dias[0][i];
                                }
                            }
                            docHTML += "]\n"
                                    + "                        }\n"
                                    + "                    ]\n"
                                    + "\n"
                                    + "                };\n"
                                    + "\n"
                                    + "                function flightChart" + cuadrostotal + "() {\n"
                                    + "                    var ctx = document.getElementById('canvas" + cuadrostotal + "').getContext('2d');\n"
                                    + "                    window.myLine = new Chart(ctx).Line(lineChartData" + cuadrostotal + ", {\n"
                                    + "                        responsive: true\n"
                                    + "                    });\n"
                                    + "                }";
                            cuadrostotal--;
                        }
                    }
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(VistaUsuario.class.getSimpleName()).log(Level.SEVERE, null, ex);
        }
        return docHTML;
    }

    //Lugares Favoritos
    public String lugares(String usr) {
        String lugares = "";
        int index = 0;
        BD.cDatos sql = new BD.cDatos();
        try {
            sql.conectar();
            ResultSet r = sql.consulta("call sp_LugaresUsuario('" + usr + "');");
            while (r.next()) {
                lugares += "var point" + index + " = new google.maps.Marker({\n"
                        + "                    icon: 'http://i.imgur.com/4gh1tVv.png'\n"
                        + "                });\n"
                        + "\n"
                        + "                point" + index + ".setMap(map);\n"
                        + "                point" + index + ".setPosition(new google.maps.LatLng(" + r.getString("coordenadax") + "," + r.getString("coordenaday") + "));\n"
                        + "\n"
                        + "                google.maps.event.addListener(point" + index + ", 'click', function (event) {\n"
                        + "                    showContextMenu(event.latLng,\n"
                        + "                            '<center><h2>" + r.getString("titulo") + "</h2><img src=\"" + r.getString("imagen") + "\"\\n\\\n"
                        + "                            width=\"50\" height=\"50\"></center>');\n"
                        + "                    coordenadax = event.latLng.lat();\n"
                        + "                    coordenaday = event.latLng.lng();\n"
                        + "                });\n"
                        + "\n"
                        + "                google.maps.event.addListener(point" + index + ", 'rightclick', function (event) {\n"
                        + "                    showContextMenu(event.latLng, mapdivmenu);\n"
                        + "                    coordenadax = event.latLng.lat();\n"
                        + "                    coordenaday = event.latLng.lng();\n"
                        + "                });";
                index++;
            }
        } catch (SQLException ex) {
            Logger.getLogger(VistaUsuario.class.getSimpleName()).log(Level.SEVERE, null, ex);
        }
        return lugares;
    }
}

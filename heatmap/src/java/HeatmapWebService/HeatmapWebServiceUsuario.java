/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HeatmapWebService;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author Alumno
 */
@WebService(serviceName = "HeatmapWebServiceUsuario")
public class HeatmapWebServiceUsuario {

    /**
     * @param usr
     * @param psw
     * @return 
     */
    @WebMethod(operationName = "validaUsuario")
    public int validaUsuario(@WebParam(name = "usr") String usr,@WebParam(name = "psw") String psw) {
        int login=0;
        BD.cDatos sql = new BD.cDatos();
        try {
            sql.conectar();
            ResultSet r = sql.consulta("call sp_Login('"+usr+"','"+psw+"');");
            while(r.next()){
                login = Integer.parseInt(r.getString("idStatus"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(HeatmapWebServiceUsuario.class.getSimpleName()).log(Level.SEVERE, null, ex);
        }
        return login;
    }

    /**
     * @param usr
     * @return 
     */
    @WebMethod(operationName = "getUsuario")
    public String getUsuario(@WebParam(name = "usr") String usr) {
        String usuario=null;
        BD.cDatos sql = new BD.cDatos();
        try {
            sql.conectar();
            ResultSet r = sql.consulta("call sp_ConsultaUsuario('"+usr+"');");
            while(r.next()){
                if(Integer.parseInt(r.getString("idStatus"))==1){
                    usuario=r.getString("usuario");
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(HeatmapWebServiceUsuario.class.getSimpleName()).log(Level.SEVERE, null, ex);
        }
        return usuario;
    }
    
}

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

/**
 *
 * @author Alumno
 */
@WebService(serviceName = "HeatmapWebServiceMapa")
public class HeatmapWebServiceMapa {

    /**
     * This is a sample web service operation
     * @return 
     */
    @WebMethod(operationName = "mapaDelictivo")
    public Object mapaDelictivo() {
        Object mapaDelictivo= null;
        BD.cDatos sql = new BD.cDatos();
        try {
            sql.conectar();
            ResultSet resultMapa = sql.consulta("select * from vw_ReportesDelictivos;");
            while(resultMapa.next()){
                mapaDelictivo= (Object) resultMapa;
            }
        } catch (SQLException ex) {
            Logger.getLogger(HeatmapWebServiceMapa.class.getSimpleName()).log(Level.SEVERE, null, ex);
        }
        return mapaDelictivo;
    }
}

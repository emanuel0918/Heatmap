/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Security;

/**
 *
 * @author Alumno
 */
public class Codigo {

    private String Codigo;

    public String getCodigo() {
        return Codigo;
    }

    public void setCodigo(String codigo) {
        this.Codigo = codigo;
    }

    public Codigo() {
        char caracteres[];
        int size=(int)((Math.random()*15)+10);
        caracteres = new char[size];
        String valores = "QWERTYUIOPASDFGHJKLZXCVBNMqwe"
                + "rtyuiopasdfghjklzxcvbnm123456789";
        for (int i = 0; i < size; i++) 
            caracteres[i]=valores.charAt((int)(Math.random()*valores.length()));
        this.Codigo=String.valueOf(caracteres);
        
    }
    public Codigo(int size) {
        char caracteres[];
        caracteres = new char[size];
        String valores = "abcdefgh123456789";
        for (int i = 0; i < size; i++) 
            caracteres[i]=valores.charAt((int)(Math.random()*valores.length()));
        this.Codigo=String.valueOf(caracteres);
        
    }
}

package Security;


import java.math.BigInteger;

import java.security.MessageDigest; 

import java.security.NoSuchAlgorithmException; 

import javax.swing.JOptionPane; 
import javax.swing.JTextArea;

public class AlgoritmoSHA1 { 

 //Funcion Hash para crear la contarseña

 public static String resumen(String contraseña){ 

 String sen = ""; 

 @SuppressWarnings("UnusedAssignment")

 MessageDigest md = null; 

 try { 

 md = MessageDigest.getInstance("SHA1"); 

 BigInteger hash = new BigInteger(1, md.digest(contraseña.getBytes())); 

 sen = hash.toString(16); 

 } catch (NoSuchAlgorithmException e) { 
 } 

 return sen; 

 } 

 public static void main(String[] args) { 
     int continuar = 0;
     while(continuar==0){

 String contraseña = JOptionPane.showInputDialog("Escriba una contraseña:"); 

 String salida = "Entrada: " + contraseña + "\nContraseña SHA1: " + resumen(contraseña); 
         JTextArea textArea = new JTextArea(salida);
 JOptionPane.showConfirmDialog(null,textArea, "Resultado", JOptionPane.CLOSED_OPTION); 
 continuar = JOptionPane.showConfirmDialog (null, 
             "¿Deseas resumir otra vez?",
             "Resumen",JOptionPane.YES_NO_OPTION);
     }
 

 } 

}
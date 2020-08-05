/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Procesos;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import javax.servlet.http.Part;

/**
 *
 * @author luigi
 */
public class ImageUploader {

    public void imageUploader(Object imagen, String ruta) {
        Part archivoPartImagen = (Part) imagen;
        String nombre = archivoPartImagen.getSubmittedFileName();
        nombre = nombre.replace(" ", "_");
        nombre = nombre.replace(".png", "");
        nombre = nombre.replace(".PNG", "");
        nombre = nombre.replace(".jpg", "");
        nombre = nombre.replace(".JPG", "");
        nombre = nombre.replace(".bmp", "");
        nombre = nombre.replace(".BMP", "");
        nombre = nombre.replace(".ico", "");
        nombre = nombre.replace(".ICO", "");
        if (!(nombre.equals(""))) {
            try {
                Procesos.ExecuterCMD cmdExecuter =
                        new Procesos.ExecuterCMD();
                String comando = "md "+ruta;
                cmdExecuter.executeCmd(comando);
                try (InputStream inputStream = archivoPartImagen.getInputStream()) {
                    File archivo = new File(ruta + nombre + ".jpg");
                    try (FileOutputStream outputStream
                            = new FileOutputStream(archivo)) {
                        int bits = inputStream.read();
                        while (bits != (-1)) {
                            outputStream.write(bits);
                            bits = inputStream.read();
                        }
                        inputStream.close();
                        outputStream.close();
                    }
                } catch (java.io.FileNotFoundException e) {
                }
            } catch (java.io.IOException ex) {
            }

        }
    }
    public String getImageUploaded(Object imagen, String ruta) {
        String rutaImagen="";
        Part archivoPartImagen = (Part) imagen;
        String nombre = archivoPartImagen.getSubmittedFileName();
        nombre = nombre.replace(" ", "_");
        nombre = nombre.replace(".png", "");
        nombre = nombre.replace(".PNG", "");
        nombre = nombre.replace(".jpg", "");
        nombre = nombre.replace(".JPG", "");
        nombre = nombre.replace(".bmp", "");
        nombre = nombre.replace(".BMP", "");
        nombre = nombre.replace(".ico", "");
        nombre = nombre.replace(".ICO", "");
        if (!(nombre.equals(""))) {
            rutaImagen=ruta+nombre+".jpg";
            try {
                Procesos.ExecuterCMD cmdExecuter =
                        new Procesos.ExecuterCMD();
                String comando = "md "+ruta;
                cmdExecuter.executeCmd(comando);
                try (InputStream inputStream = archivoPartImagen.getInputStream()) {
                    File archivo = new File(rutaImagen);
                    rutaImagen=rutaImagen.replace("\\", "/");
                    rutaImagen="file:///"+rutaImagen;
                    try (FileOutputStream outputStream
                            = new FileOutputStream(archivo)) {
                        int bits = inputStream.read();
                        while (bits != (-1)) {
                            outputStream.write(bits);
                            bits = inputStream.read();
                        }
                        inputStream.close();
                        outputStream.close();
                    }
                } catch (java.io.FileNotFoundException e) {
                }
            } catch (java.io.IOException ex) {
            }

        }
    return rutaImagen;
    }
}

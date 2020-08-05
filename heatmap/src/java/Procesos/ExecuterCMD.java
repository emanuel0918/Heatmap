/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Procesos;

/**
 *
 * @author Alumno
 * 
 */
public class ExecuterCMD {

    private String cmdComando;

    public void executeCmd(String comando) {
        try {
            this.cmdComando = comando;
            ProcessBuilder builder = new ProcessBuilder(
                    "cmd.exe", "/c", cmdComando);
            builder.redirectErrorStream(true);
            Process p = builder.start();
            java.io.BufferedReader r
                    = new java.io.BufferedReader(
                            new java.io.InputStreamReader(p.getInputStream()));
            String line;
            while (true) {
                line = r.readLine();
                if (line == null) {
                    break;
                }
                System.out.println(line);
            }
        } catch (java.io.IOException ex) {
        }
    }
}

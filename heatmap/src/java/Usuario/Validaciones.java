/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Usuario;

/**
 *
 * @author Alumno
 */
public class Validaciones { 
    
    public boolean fechaValida(String fecha) {
        boolean valido = false;
        if (!(fecha == null)) {
            Validaciones validaciones = new Validaciones();
            if (fecha.length() == 10) {
                if ((validaciones.charEsNumero(fecha.charAt(0)))
                        && (validaciones.charEsNumero(fecha.charAt(1)))
                        && (validaciones.charEsNumero(fecha.charAt(2)))
                        && (validaciones.charEsNumero(fecha.charAt(3)))
                        && (validaciones.charEsNumero(fecha.charAt(5)))
                        && (validaciones.charEsNumero(fecha.charAt(6)))
                        && (validaciones.charEsNumero(fecha.charAt(8)))
                        && (validaciones.charEsNumero(fecha.charAt(9)))
                        && (fecha.charAt(4) == '-') && (fecha.charAt(7) == '-')) {
                    valido = true;
                }
            }
        }
        return valido;
    }

    public boolean horarioValido(String horario) {
        boolean valido = false;
        if (!(horario == null)) {
            Validaciones validaciones = new Validaciones();
            if (horario.length() == 4 || horario.length() == 5) {
                if ((horario.length() == 4
                        && (validaciones.charEsNumero(horario.charAt(0)))
                        && (validaciones.charEsNumero(horario.charAt(2)))
                        && (validaciones.charEsNumero(horario.charAt(3)))
                        && (horario.charAt(1) == ':'))
                        || ((horario.length() == 5
                        && (validaciones.charEsNumero(horario.charAt(0)))
                        && (validaciones.charEsNumero(horario.charAt(1)))
                        && (validaciones.charEsNumero(horario.charAt(3)))
                        && (validaciones.charEsNumero(horario.charAt(4)))
                        && (horario.charAt(2) == ':')))) {
                    valido = true;
                }
            }
        }
        return valido;
    }

    public boolean paswordValida(String pasword) {
        boolean valido = false;
        if (!(pasword == null)) {
            String mayusculas = "QWERTYUIOPASDFGHJKLZXCVBNM";
            String minusculas = "qwertyuiopasdfghjklzxcvbnm";
            String numeros = "0123456789";
            if (pasword.length() >= 8) {
                for (int i = 0; i < pasword.length(); i++) {
                    for (int n = 0; n < mayusculas.length(); n++) {
                        if ((pasword.charAt(i) == mayusculas.charAt(n))) {
                            valido = true;
                            i = pasword.length();
                            n = mayusculas.length();
                        }
                    }
                }
                if (valido) {
                    valido = false;

                    for (int i = 0; i < pasword.length(); i++) {
                        for (int n = 0; n < minusculas.length(); n++) {
                            if ((pasword.charAt(i) == minusculas.charAt(n))) {
                                valido = true;
                                i = pasword.length();
                                n = minusculas.length();
                            }
                        }
                    }
                }
                if (valido) {
                    valido = false;

                    for (int i = 0; i < pasword.length(); i++) {
                        for (int n = 0; n < numeros.length(); n++) {
                            if ((pasword.charAt(i) == numeros.charAt(n))) {
                                valido = true;
                                i = pasword.length();
                                n = numeros.length();
                            }
                        }
                    }
                }
            }
        }
        return valido;
    }

    public boolean correoValido(String correo) {
        boolean valido = false;
        if (!(correo == null)) {
            String especiales = "qwertyuiopasdfghjklzxcvbnm0123456789@.-_";
            String caracteres = "qwertyuiopasdfghjklzxcvbnm0123456789";
            int index = 0;
            if (correo.length() > 5) {
                for (int i = 1; i < ((correo.length()) - 1); i++) {
                    if (correo.charAt(i) == '@') {
                        valido = true;
                        index = i;
                        i = correo.length();
                    }
                }
                if (valido) {
                    for (int i = (index + 1); i < correo.length(); i++) {
                        if (correo.charAt(i) == '@') {
                            valido = false;
                            i = correo.length();
                        }
                    }
                    if (valido) {
                        valido = false;
                        for (int i = (index + 2); i < ((correo.length()) - 1); i++) {
                            if (correo.charAt(i) == '.') {
                                valido = true;
                                i = correo.length();
                            }
                        }
                        if (valido) {
                            valido = false;
                            for (int i = 0; i < caracteres.length(); i++) {
                                if ((correo.charAt(0) == caracteres.charAt(i))) {
                                    valido = true;
                                }
                            }
                            if (valido) {
                                valido = false;
                                for (int i = 0; i < caracteres.length(); i++) {
                                    if ((correo.charAt((index + 1)) == caracteres.charAt(i))) {
                                        valido = true;
                                    }
                                }
                                if (valido) {
                                    valido = false;
                                    for (int i = 0; i < caracteres.length(); i++) {
                                        if ((correo.charAt(((correo.length()) - 1)) == caracteres.charAt(i))) {
                                            valido = true;
                                        }
                                    }
                                }
                            }
                            if (valido) {
                                for (int n = 0; n < correo.length(); n++) {
                                    valido = false;
                                    for (int i = 0; i < especiales.length(); i++) {
                                        if (!(correo.charAt(n) == especiales.charAt(i))) {
                                            valido = true;
                                        }
                                    }
                                    if (!valido) {
                                        n = correo.length();
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return valido;
    }

    public boolean telefonoValido(String telefono) {
        boolean valido = false;
        if (!(telefono == null)) {
            String numeros = "0123456789";
            if (telefono.length() >= 8) {
                for (int i = 0; i < telefono.length(); i++) {
                    valido = false;
                    for (int n = 0; n < numeros.length(); n++) {
                        if (telefono.charAt(i) == numeros.charAt(n)) {
                            valido = true;
                        }
                    }
                    if (!valido) {
                        i = telefono.length();
                    }
                }
            }
        }
        return valido;
    }

    public boolean usuarioValido(String user) {
        boolean valido = false;
        if (!(user == null)) {
            String caracteres = "qwertyuiopasdfghjklzxcvbnm";
            String permitidos = "qwertyuiopasdfghjklzxcvbnm0123456789-_";
            if (user.length() >= 5) {
                for (int i = 0; i < user.length(); i++) {
                    for (int n = 0; n < caracteres.length(); n++) {
                        if ((user.charAt(i) == caracteres.charAt(n))) {
                            valido = true;
                            i = user.length();
                            n = caracteres.length();
                        }
                    }
                }
                if (valido) {
                    for (int i = 0; i < user.length(); i++) {
                        valido = false;
                        for (int n = 0; n < permitidos.length(); n++) {
                            if (user.charAt(i) == permitidos.charAt(n)) {
                                valido = true;
                            }
                        }
                        if (!valido) {
                            i = user.length();
                        }
                    }
                }
            }
        }
        return valido;
    }

    public boolean decimalValido(String decimal) {
        boolean valido = false;
        if (!(decimal == null)) {
            String permitidos = "0123456789.";
            String numeros = "0123456789";
            int index = 0;
            for (int i = 0; i < decimal.length(); i++) {
                valido = false;
                for (int n = 0; n < permitidos.length(); n++) {
                    if (decimal.charAt(i) == permitidos.charAt(n)) {
                        valido = true;
                    } else if (i == 0) {
                        if (decimal.charAt(0) == '+' || decimal.charAt(0) == '-') {
                            valido = true;
                        }
                    }
                }
                if (!valido) {
                    i = decimal.length();
                }
            }
            if (valido) {
                for (int i = 0; i < decimal.length(); i++) {
                    if (decimal.charAt(i) == '.') {
                        valido = true;
                        index = i;
                        i = decimal.length();
                    }
                }
                if (valido) {
                    for (int i = (index + 1); i < decimal.length(); i++) {
                        if (decimal.charAt(i) == '.') {
                            valido = false;
                            i = decimal.length();
                        }
                    }
                    if (valido) {
                        valido = false;
                        for (int i = 0; i < decimal.length(); i++) {
                            for (int n = 0; n < numeros.length(); n++) {
                                if (decimal.charAt(i) == numeros.charAt(n)) {
                                    valido = true;
                                }
                            }

                        }
                    }

                }
            }
        }
        return valido;
    }

    private boolean charEsNumero(char numero) {
        boolean valido = false;
        String numeros = "0123456789";
        for (int i = 0; i < numeros.length(); i++) {
            if (numero == numeros.charAt(i)) {
                valido = true;
                break;
            }
        }
        return valido;
    }
}

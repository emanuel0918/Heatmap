/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function fechaValida(fecha) {
    valido = false;
    if (!(fecha === null)) {
        if (fecha.length === 10) {
            if ((charEsNumero(fecha.charAt(0)))
                    && (charEsNumero(fecha.charAt(1)))
                    && (charEsNumero(fecha.charAt(2)))
                    && (charEsNumero(fecha.charAt(3)))
                    && (charEsNumero(fecha.charAt(5)))
                    && (charEsNumero(fecha.charAt(6)))
                    && (charEsNumero(fecha.charAt(8)))
                    && (charEsNumero(fecha.charAt(9)))
                    && (fecha.charAt(4) === '-') && (fecha.charAt(7) === '-')) {
                valido = true;
            }
        }
    }
    return valido;
}

function horarioValido(horario) {
    valido = false;
    if (!(horario === null)) {
        if (horario.length === 4 || horario.length === 5) {
            if ((horario.length === 4
                    && (charEsNumero(horario.charAt(0)))
                    && (charEsNumero(horario.charAt(2)))
                    && (charEsNumero(horario.charAt(3)))
                    && (horario.charAt(1) === ':'))
                    || ((horario.length === 5
                            && (charEsNumero(horario.charAt(0)))
                            && (charEsNumero(horario.charAt(1)))
                            && (charEsNumero(horario.charAt(3)))
                            && (charEsNumero(horario.charAt(4)))
                            && (horario.charAt(2) === ':')))) {
                valido = true;
            }
        }
    }
    return valido;
}

function paswordValida(pasword) {
    valido = false;
    if (!(pasword === null)) {
        mayusculas = "QWERTYUIOPASDFGHJKLZXCVBNM";
        minusculas = "qwertyuiopasdfghjklzxcvbnm";
        numeros = "0123456789";
        if (pasword.length >= 8) {
            for (i = 0; i < pasword.length; i++) {
                for (n = 0; n < mayusculas.length; n++) {
                    if ((pasword.charAt(i) === mayusculas.charAt(n))) {
                        valido = true;
                        i = pasword.length;
                        n = mayusculas.length;
                    }
                }
            }
            if (valido) {
                valido = false;

                for (i = 0; i < pasword.length; i++) {
                    for (n = 0; n < minusculas.length; n++) {
                        if ((pasword.charAt(i) === minusculas.charAt(n))) {
                            valido = true;
                            i = pasword.length;
                            n = minusculas.length;
                        }
                    }
                }
            }
            if (valido) {
                valido = false;

                for (i = 0; i < pasword.length; i++) {
                    for (n = 0; n < numeros.length; n++) {
                        if ((pasword.charAt(i) === numeros.charAt(n))) {
                            valido = true;
                            i = pasword.length;
                            n = numeros.length;
                        }
                    }
                }
            }
        }
    }
    return valido;
}

function correoValido(correo) {
    valido = false;
    if (!(correo === null)) {
        especiales = "qwertyuiopasdfghjklzxcvbnm0123456789@.-_";
        caracteres = "qwertyuiopasdfghjklzxcvbnm0123456789";
        index = 0;
        if (correo.length > 5) {
            for (i = 1; i < ((correo.length) - 1); i++) {
                if (correo.charAt(i) === '@') {
                    valido = true;
                    index = i;
                    i = correo.length;
                }
            }
            if (valido) {
                for (i = (index + 1); i < correo.length; i++) {
                    if (correo.charAt(i) === '@') {
                        valido = false;
                        i = correo.length;
                    }
                }
                if (valido) {
                    valido = false;
                    for (i = (index + 2); i < ((correo.length) - 1); i++) {
                        if (correo.charAt(i) === '.') {
                            valido = true;
                            i = correo.length;
                        }
                    }
                    if (valido) {
                        valido = false;
                        for (i = 0; i < caracteres.length; i++) {
                            if ((correo.charAt(0) === caracteres.charAt(i))) {
                                valido = true;
                            }
                        }
                        if (valido) {
                            valido = false;
                            for (i = 0; i < caracteres.length; i++) {
                                if ((correo.charAt((index + 1)) === caracteres.charAt(i))) {
                                    valido = true;
                                }
                            }
                            if (valido) {
                                valido = false;
                                for (i = 0; i < caracteres.length; i++) {
                                    if ((correo.charAt(((correo.length) - 1)) === caracteres.charAt(i))) {
                                        valido = true;
                                    }
                                }
                            }
                        }
                        if (valido) {
                            for (n = 0; n < correo.length; n++) {
                                valido = false;
                                for (i = 0; i < especiales.length; i++) {
                                    if (!(correo.charAt(n) === especiales.charAt(i))) {
                                        valido = true;
                                    }
                                }
                                if (!valido) {
                                    n = correo.length;
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

function telefonoValido(telefono) {
    valido = false;
    if (!(telefono === null)) {
        numeros = "0123456789";
        if (telefono.length >= 8) {
            for (i = 0; i < telefono.length; i++) {
                valido = false;
                for (n = 0; n < numeros.length; n++) {
                    if (telefono.charAt(i) === numeros.charAt(n)) {
                        valido = true;
                    }
                }
                if (!valido) {
                    i = telefono.length;
                }
            }
        }
    }
    return valido;
}

function usuarioValido(user) {
    valido = false;
    if (!(user === null)) {
        caracteres = "qwertyuiopasdfghjklzxcvbnm";
        permitidos = "qwertyuiopasdfghjklzxcvbnm0123456789-_";
        if (user.length >= 5) {
            for (i = 0; i < user.length; i++) {
                for (n = 0; n < caracteres.length; n++) {
                    if ((user.charAt(i) === caracteres.charAt(n))) {
                        valido = true;
                        i = user.length;
                        n = caracteres.length;
                    }
                }
            }
            if (valido) {
                for (i = 0; i < user.length; i++) {
                    valido = false;
                    for (n = 0; n < permitidos.length; n++) {
                        if (user.charAt(i) === permitidos.charAt(n)) {
                            valido = true;
                        }
                    }
                    if (!valido) {
                        i = user.length;
                    }
                }
            }
        }
    }
    return valido;
}

function decimalValido(decimal) {
    valido = false;
    if (!(decimal === null)) {
        permitidos = "0123456789.";
        numeros = "0123456789";
        index = 0;
        for (i = 0; i < decimal.length; i++) {
            valido = false;
            for (n = 0; n < permitidos.length; n++) {
                if (decimal.charAt(i) === permitidos.charAt(n)) {
                    valido = true;
                } else if (i === 0) {
                    if (decimal.charAt(0) === '+' || decimal.charAt(0) === '-') {
                        valido = true;
                    }
                }
            }
            if (!valido) {
                i = decimal.length;
            }
        }
        if (valido) {
            for (i = 0; i < decimal.length; i++) {
                if (decimal.charAt(i) === '.') {
                    valido = true;
                    index = i;
                    i = decimal.length;
                }
            }
            if (valido) {
                for (i = (index + 1); i < decimal.length; i++) {
                    if (decimal.charAt(i) === '.') {
                        valido = false;
                        i = decimal.length;
                    }
                }
                if (valido) {
                    valido = false;
                    for (i = 0; i < decimal.length; i++) {
                        for (n = 0; n < numeros.length; n++) {
                            if (decimal.charAt(i) === numeros.charAt(n)) {
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

function charEsNumero(numero) {
    valido = false;
    numeros = "0123456789";
    for (i = 0; i < numeros.length; i++) {
        if (numero === numeros.charAt(i)) {
            valido = true;
            break;
        }
    }
    return valido;
}
function readURL(input) {
    if (input.files && input.files[0]) {
        var reader = new FileReader();
        reader.onload = function (e) {
            $('#imagen-id').attr('src', e.target.result).width(300).height(200);
        };
        reader.readAsDataURL(input.files[0]);
    }
}
function soloNumeros(e) {
    key = e.keyCode || e.which;
    tecla = String.fromCharCode(key).toLowerCase();
    letras = ' 0123456789';
    especiales = '8-37-39-46';
    tecla_especial = false;
    for (var i in especiales) {
        if (key === especiales[i]) {
            tecla_especial = true;
            break;
        }
    }

    if (letras.indexOf(tecla) === -1 && !tecla_especial) {
        return false;
    }
}
function soloLetras(e) {
    key = e.keyCode || e.which;
    tecla = String.fromCharCode(key).toLowerCase();
    letras = ' áéíóúabcdefghijklmnñopqrstuvwxyz';
    especiales = '8-37-39-46';
    tecla_especial = false;
    for (var i in especiales) {
        if (key === especiales[i]) {
            tecla_especial = true;
            break;
        }
    }

    if (letras.indexOf(tecla) === -1 && !tecla_especial) {
        return false;
    }
}


function emailValido(email) {
    esValido = false;
    expr = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
    if (expr.test(email))
        esValido = true;
    return esValido;
}




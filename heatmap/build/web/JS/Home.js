/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var imagenes;
if (window.screen.availHeight < 2306 &&
        window.screen.availWidth < 4098) {
    imagenes = ['http://i.imgur.com/BTHkNYr.jpg',
        'http://i.imgur.com/icnB0bW.jpg',
        'http://i.imgur.com/8cBTIcw.jpg',
        'http://i.imgur.com/1Cp3VtO.jpg',
        'http://i.imgur.com/EOMCz38.jpg',
        'http://i.imgur.com/BT2UdRj.jpg',
        'http://i.imgur.com/ETMdArP.jpg'];
}
contadorCarrucel = 0;
function indexHeatmap() {
    loop = setTimeout("indexHeatmap()", 5000);
    if (contadorCarrucel % 2 === 0) {
        $("#main-home img.desvanece").toggleClass("transparent");
        document.getElementById("img-index2").src = imagenes[contadorCarrucel];
    } else {
        $("#main-home img.desvanece").toggleClass("transparent");
        document.getElementById("img-index").src = imagenes[contadorCarrucel];
    }
    if (contadorCarrucel === (imagenes.length - 2) &&
            imagenes.length % 2 === 1) {
        contadorCarrucel = 0;
    } else if (contadorCarrucel === (imagenes.length - 1) &&
            imagenes.length % 2 === 0) {
        contadorCarrucel = 0;
    } else {
        contadorCarrucel++;
    }
}
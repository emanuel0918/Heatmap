/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



function backButton() {
    var e = document.getElementById('refreshed');
    if (!(e.value === 'yes'))
        e.value = 'yes';
    else {
        e.value = 'no';
        location.reload();
    }

}

/*-----------De preferencia no usar----------*/
function nobackbutton() {
    window.location.hash = "no-back-button";
    window.location.hash = "Again-No-back-button";
    window.onhashchange = function () {
        window.location.hash = "no-back-button";
    };
}

/*---------------Mapa------------------*/

function handleLocationError(browserHasGeolocation, infoWindow, pos) {
    infoWindow.setPosition(pos);
    infoWindow.setContent(browserHasGeolocation ?
            'Error: The Geolocation service failed.' :
            'Error: Your browser doesn\'t support geolocation.');
}


